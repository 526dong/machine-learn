package com.ccx.models.service.impl.dataexchange;

import com.ccx.models.Constants;
import com.ccx.models.api.dataexchange.ModelExtractService;
import com.ccx.models.bean.*;
import com.ccx.models.manager.HttpDataExchangeManager;
import com.ccx.models.mapper.*;
import com.ccx.models.mapper.dataexchange.DataExchangeMapper;
import com.ccx.models.mapper.datafile.ModelsDataFileMapper;
import com.ccx.models.mapper.modelsLibrary.ModelsLibraryMapper;
import com.ccx.models.message.MsgPush;
import com.ccx.models.model.*;
import com.ccx.models.model.datafile.ModelsDataFile;
import com.ccx.models.util.CSVUtil;
import com.ccx.models.util.JsonUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.ccx.models.bean.RecivePutOutBean.object2BigDecimal;

/**
 * @Description: 数据交换-变量、iv值及重要变量展示
 * @author:lilong
 * @Date: 2017/11/22
 */
@Service("VariableExchangeApi")
public class MoldelExtractServiceImpl implements ModelExtractService {

    Logger logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());

    @Autowired
    private HttpDataExchangeManager httpDataExchangeManager;

    @Autowired
    private ModelsExtractInfoMapper modelsExtractInfoMapper;

    @Autowired
    private  ModelsOutputPathMapper modelsOutputPathMapper;
    @Autowired
    private ModelsDataFileMapper modelsDataFileMapper;
    @Autowired
    private ModelsProgramMapper modelsProgramMapper;

    @Autowired
    private DataExchangeMapper dataExchangeMapper;

    @Autowired
    private ModelsExtractTargetValueMapper modelsExtractTargetValueMapper;
    @Autowired
    private ModelsExtractOutPathMapper modelsExtractOutPathMapper;

    @Autowired
    private MsgPush push;

    @Autowired
    private ModelsLibraryMapper modelsLibraryMapper;

    /**
     *
     * @param file 预测模型信息
     * @param userName
     * @return
     */
    @Override
    public Map<String, Object> saveForModelSend(ModelsExtractTestRecord file, String userName) {
        Map<String,Object> map=new HashMap<>();
        if(file==null) {
            map.put("rsCode","10000");
            map.put("rsMsg","参数有误");
            return  map;
        }
        Long fileId=file.getId();
        try{
            //提取模型信息
            ModelsExtractInfo info=modelsExtractInfoMapper.selectByPrimaryKey(file.getModelsExtractInfoId());
            ModelsOutputPath outputPath= modelsOutputPathMapper.getModelOutputPath(info.getProgramId(),info.getArithmeticId());
            String reqId=new StringBuilder(fileId.toString()).append("-").append(System.currentTimeMillis()).toString();

            ModelsDataFile dataFile= modelsDataFileMapper.selectByPrimaryKey(info.getDataFileId().intValue());
            Base base = new Base(file.getId().intValue(),file.getFileName(),null,outputPath.getRequstId().split("-")[0]);
            ExtractOutBean outBean=new ExtractOutBean(reqId,outputPath.getRequstId(),outputPath.getModelPath(),base,file.getTestType());
            //获取http请求数据
            String bean= httpDataExchangeManager.sendMsgToModel(dataFile,modelsProgramMapper.selectByPrimaryKey(info.getProgramId().intValue()),outBean,file.getFilePath());
            bean=bean.replace("//10.0.5.136/","d:/data/");

            if(file.getTestType()==0){
                ExtractInBean inBean= JsonUtils.fromJson(bean,ExtractInBean.class);
                if("200".equals(inBean.getCode())){
                    save(file.getId(),inBean,fileId);
                    // push.sendMsgToUser(new MsgBean("0000","成功",file), Constants.PUSH_CHANNEL_EXTRACT,push.getConnId());
                }
            }else {
                ExtractTestYesInBean inBean= JsonUtils.fromJson(bean,ExtractTestYesInBean.class);
                if("200".equals(inBean.getCode())){
                    saveExtractYes(file.getId(),inBean,fileId);
                    // push.sendMsgToUser(new MsgBean("0000","成功",file), Constants.PUSH_CHANNEL_EXTRACT,push.getConnId());
                }
            }
           // push.sendMsgToUser(new MsgBean("9999","模型计算失败",file), Constants.PUSH_CHANNEL_EXTRACT,push.getConnId());
        }catch (Exception e){
            logger.error("报错变量信息异常，id为："+file.getId(),e);
            //push.sendMsgToUser(new MsgBean("9999","系统异常",file), Constants.PUSH_CHANNEL_EXTRACT,push.getConnId());
            throw new RuntimeException("保存出错");
        }
        return map;
    }

    private void save(Long extractId,ExtractInBean inBean,Long id){
        List<String[]> list= CSVUtil.read(inBean.getPredictResPath());
        Map<String,String[]> data=listToMap(list);
        List<Map<String ,Object>> map = dataExchangeMapper.getContractValue(id);
        List<List<ModelsExtractTargetValue>> lists=new ArrayList<>();
        List<ModelsExtractTargetValue> list1=new ArrayList<>();
        int i=1;
        for(Map<String ,Object> str:map){
            String indexName=(String)str.get("index_name");
            list1.add(new ModelsExtractTargetValue(
                    Integer.valueOf(str.get("id").toString())
                    ,indexName,new BigDecimal(data.get(indexName)[1]),extractId));
            if(i++%1000==0){
                lists.add(list1);
                list1=new ArrayList<>();
            }
        }
        if(list1.size()>0)  lists.add(list1);
        new HttpDataExchangeManager.Save<List<ModelsExtractTargetValue>, ModelsExtractTargetValueMapper>() {
            @Override
            public void insert(List<ModelsExtractTargetValue> info, ModelsExtractTargetValueMapper mapper) {
                mapper.insert(info);
            }
        }.insertBatch(lists,modelsExtractTargetValueMapper);

        new HttpDataExchangeManager.Save<ModelsExtractOutPath, ModelsExtractOutPathMapper>() {
            @Override
            public void insert(ModelsExtractOutPath info, ModelsExtractOutPathMapper mapper) {
                    mapper.insert(info);
            }
        }.insert(new ModelsExtractOutPath(extractId,inBean.getPredictResPath()),modelsExtractOutPathMapper);
    }

    private Map<String,String[]> listToMap(List<String[]> list){
        Map<String,String[]> map = new HashMap<>();
        for (String[] str :list){
            map.put(str[0],str);
        }
        return map;
    }

    private void saveExtractYes(Long extractId,ExtractTestYesInBean inBean,Long id){
        //有监督测试
        int type = 1;
        try {
            List<List<ModelsExtractTargetValue>> lists= getCsv(inBean,id,extractId);

            Map<String,Object> paramMap = createBeanData(extractId,inBean,type,id);
            //文件路径实体类
            ModelsExtractOutPath modelsExtractOutPath = (ModelsExtractOutPath) paramMap.get("modelsExtractOutPath");
            if(null != modelsExtractOutPath){
                modelsLibraryMapper.insertModelsExtractOutPath(modelsExtractOutPath);
            }
            //入模信息实体类
            ModelsExtractDataAnalyInfo dataAnalyInfo = (ModelsExtractDataAnalyInfo) paramMap.get("dataAnalyInfo");
            if(null != modelsExtractOutPath){
                modelsLibraryMapper.insertDataAnalyInfo(dataAnalyInfo);
            }
            //指标信息实体
            ModelsExtractDataEvaluateIndex dataEvaluateIndex = (ModelsExtractDataEvaluateIndex) paramMap.get("dataEvaluateIndex");
            if(null != modelsExtractOutPath){
                modelsLibraryMapper.insertDataEvaluateIndex(dataEvaluateIndex);
            }
            //分箱信息实体
            List<ModelsExtractScoreGroupStatistics> scoreGroupStatisticsL = (List<ModelsExtractScoreGroupStatistics>) paramMap.get("scoreGroupStatisticsL");
            if(null != modelsExtractOutPath){
                modelsLibraryMapper.insertScoreGroupStatistics(scoreGroupStatisticsL);
            }

            new HttpDataExchangeManager.Save<List<ModelsExtractTargetValue>, ModelsExtractTargetValueMapper>() {
                @Override
                public void insert(List<ModelsExtractTargetValue> info, ModelsExtractTargetValueMapper mapper) {
                    mapper.insert(info);
                }
            }.insertBatch(lists,modelsExtractTargetValueMapper);
        } catch (Exception e) {
            logger.error("模型返回数据转换为实体信息并且保存报错",e);
        }
    }
    private List<List<ModelsExtractTargetValue>> getCsv(ExtractTestYesInBean inBean,Long id,Long extractId){
        List<List<ModelsExtractTargetValue>> lists=new ArrayList<>();
        List<String[]> list= CSVUtil.read(inBean.getPredictResPath());
        Map<String,String[]> data=listToMap(list);
        List<Map<String ,Object>> map = dataExchangeMapper.getContractValue(id);
        List<ModelsExtractTargetValue> list1=new ArrayList<>();
        int i=1;
        for(Map<String ,Object> str:map){
            String indexName=(String)str.get("index_name");
            list1.add(new ModelsExtractTargetValue(
                    Integer.valueOf(str.get("id").toString())
                    ,indexName,new BigDecimal(data.get(indexName)[1]),extractId,new BigDecimal(data.get(indexName)[2])));
            if(i++%1000==0){
                lists.add(list1);
                list1=new ArrayList<>();
            }
        }
        if(list1.size()>0)  lists.add(list1);
        return lists;
    }

    public Map<String,Object> createBeanData(Long extractId,ExtractTestYesInBean inBean,int type,Long id){
        Map<String,Object> paramMap = new HashMap<String,Object>();

        //文件路径实体类
        ModelsExtractOutPath modelsExtractOutPath = new ModelsExtractOutPath();
        modelsExtractOutPath.setModelsExtractTestRecordId(id);
        modelsExtractOutPath.setType(type);
        modelsExtractOutPath.setFilePath(inBean.getPredictResPath());
        modelsExtractOutPath.setCreateTime(new Date());

        //入模信息实体类
        ModelsExtractDataAnalyInfo dataAnalyInfo = new ModelsExtractDataAnalyInfo();
        Map<String,Object> dataAnalyInfoList = inBean.getModeldataInfo();
        //0表示预测集
        dataAnalyInfo.setType(0);
        dataAnalyInfo.setSampleNum(object2BigDecimal(dataAnalyInfoList.get("样本量")));
        dataAnalyInfo.setSumDimensionality(object2BigDecimal(dataAnalyInfoList.get("总维度")));
        dataAnalyInfo.setIntoDimensionality(object2BigDecimal(dataAnalyInfoList.get("入模维度")));
        dataAnalyInfo.setImportanceVar(object2BigDecimal(dataAnalyInfoList.get("重要变量")));
        dataAnalyInfo.setPlusMinusRate(object2BigDecimal(dataAnalyInfoList.get("正负样本比")));
        dataAnalyInfo.setPlusRate(object2BigDecimal(dataAnalyInfoList.get("正样本比例")));
        dataAnalyInfo.setModelsExtractTestRecordId(id);
        dataAnalyInfo.setCreateTime(new Date());

        //指标信息实体
        ModelsExtractDataEvaluateIndex dataEvaluateIndex = new ModelsExtractDataEvaluateIndex();
        Map<String,Object> dataEvaluateIndexList = inBean.getModelreport();
        //0表示预测集
        dataEvaluateIndex.setType(0);
        dataEvaluateIndex.setAuc(object2BigDecimal(dataEvaluateIndexList.get("AUC")));
        dataEvaluateIndex.setKs(object2BigDecimal(dataEvaluateIndexList.get("KS")));
        dataEvaluateIndex.setPrecisions(object2BigDecimal(dataEvaluateIndexList.get("precision")));
        dataEvaluateIndex.setRecall(object2BigDecimal(dataEvaluateIndexList.get("recall")));
        dataEvaluateIndex.setF1Score(object2BigDecimal(dataEvaluateIndexList.get("f1-score")));
        dataEvaluateIndex.setSupport(object2BigDecimal(dataEvaluateIndexList.get("support")));
        dataEvaluateIndex.setGini(object2BigDecimal(dataEvaluateIndexList.get("gini")));
        dataEvaluateIndex.setModelsExtractTestRecordId(id);
        dataEvaluateIndex.setCreateTime(new Date());

        //分箱信息实体
        List<ModelsExtractScoreGroupStatistics> scoreGroupStatisticsL = new ArrayList<ModelsExtractScoreGroupStatistics>();
        List<Map<String,Object>> scoreGroupStatisticsList = inBean.getPvalueReport();
        if(null !=scoreGroupStatisticsList && !scoreGroupStatisticsList.isEmpty()){
            for (Map<String,Object> map:scoreGroupStatisticsList) {
                ModelsExtractScoreGroupStatistics scoreGroupStatistics = new ModelsExtractScoreGroupStatistics();
                scoreGroupStatistics.setModelsExtractTestRecordId(id);
                scoreGroupStatistics.setBinsScore((String)map.get("bins"));
                scoreGroupStatistics.setGood(object2BigDecimal(map.get("good")));
                scoreGroupStatistics.setBad(object2BigDecimal(map.get("bad")));
                scoreGroupStatistics.setTotal(object2BigDecimal(map.get("total")));
                scoreGroupStatistics.setFactor(object2BigDecimal(map.get("factor_per")));
                scoreGroupStatistics.setIv(object2BigDecimal(map.get("IV")));
                scoreGroupStatistics.setBadPer(object2BigDecimal(map.get("bad_per")));
                scoreGroupStatistics.setModelPvalue(object2BigDecimal(map.get("model_pvalue")));
                scoreGroupStatistics.setCreateTime(new Date());
                scoreGroupStatistics.setModelDataName((String)map.get("modelDataName"));
                scoreGroupStatisticsL.add(scoreGroupStatistics);
            }
        }
        paramMap.put("modelsExtractOutPath",modelsExtractOutPath);
        paramMap.put("dataAnalyInfo",dataAnalyInfo);
        paramMap.put("dataEvaluateIndex",dataEvaluateIndex);
        paramMap.put("scoreGroupStatisticsL",scoreGroupStatisticsL);
        return paramMap;
    }
}
