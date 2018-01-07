package com.ccx.models.service.impl.modelsLibrary;

import com.ccx.models.api.modelsLibrary.ModelsLibraryApi;
import com.ccx.models.mapper.ModelsExtractTestRecordMapper;
import com.ccx.models.mapper.modelsLibrary.ModelsLibraryMapper;
import com.ccx.models.model.ModelsExtract;
import com.ccx.models.model.ModelsExtractTestRecord;
import com.ccx.models.util.JsonUtils;
import com.ccx.models.util.UsedUtil;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ModelsLibraryApi")
public class ModelsLibraryServiceImpl implements ModelsLibraryApi {

	private static Logger logger = LogManager.getLogger(ModelsLibraryServiceImpl.class);
		
	@Autowired
    private ModelsLibraryMapper modelsLibraryMapper;

	@Autowired
	ModelsExtractTestRecordMapper modelsExtractTestRecordMapper;

    /**
     * @Description: 根据模型name获取模型list
     * @Author: wxn
     * @Date: 2017/12/12 11:13:04
     * @Param:modelName
     * @Return
     */
	@Override
	public List<ModelsExtract> getModelsExtractListByName(String modelName) {
		return modelsLibraryMapper.getModelsExtractListByName(modelName);
	}

	/**
	 * @Description:通过模型项目id和算法id查找模型list
	 * @Author: wxn
	 * @Date: 2017/12/12 13:21:02
	 * @Param: [programId, arithmeticId]
	 * @Return java.util.List<com.ccx.models.model.ModelsExtract>
	 */
	@Override
	public List<ModelsExtract> getModelsExtractListById(Long programId,Long arithmeticId){
		return modelsLibraryMapper.getModelsExtractListById(programId,arithmeticId);
	}

	/**
	 * @Description:保存模型信息
	 * @Author: wxn
	 * @Date: 2017/12/12 13:33:22
	 * @Param: [modelsExtract]
	 * @Return int
	 */
	@Override
	public int saveModelsExtract(ModelsExtract modelsExtract){
		return modelsLibraryMapper.saveModelsExtract(modelsExtract);
	}

    /**
     * @Description: 分页查询模型列表
     * @Author: wxn
     * @Date: 2017/12/12 14:44:43
     * @Param: [paramMap]
     * @Return com.github.pagehelper.PageInfo<com.ccx.models.model.ModelsExtract>
     */
    @Override
    public PageInfo<ModelsExtract> getModelsPageList(Map<String, Object> paramMap){
        List<ModelsExtract> modelsPageList = modelsLibraryMapper.getModelsPageList(paramMap);
        PageInfo<ModelsExtract> pageInfo = new PageInfo<>(modelsPageList);
        return pageInfo;
    }
    

    @Override
	public List<ModelsExtract> getModelsAllList() {
    	List<ModelsExtract> list = modelsLibraryMapper.getModelsAllList();
    	return list;
	}

	/**
     * @Description: 通过模型id删除模型
     * @Author: wxn
     * @Date: 2017/12/12 16:10:00
     * @Param: [modelsExtract]
     * @Return int
     */
    @Override
    public int updateModelById(ModelsExtract modelsExtract){
        return modelsLibraryMapper.updateModelById(modelsExtract);
    }

    /**
     * @Description: 通过id获取模型基本信息
     * @Author: wxn
     * @Date: 2017/12/12 16:25:12
     * @Param: [modelId]
     * @Return com.ccx.models.model.ModelsExtract
     */
    @Override
    public ModelsExtract getModelById(Long modelId){
        return modelsLibraryMapper.getModelById(modelId);
    }

	/**
	 * @Description: 获取模型测试记录列表
	 * @Author: wxn
	 * @Date: 2017/12/13 11:52:21
	 * @Param: [paramMap]
	 * @Return com.github.pagehelper.PageInfo<com.ccx.models.model.ModelsExtractTestRecord>
	 */
	@Override
	public PageInfo<ModelsExtractTestRecord> getModelTestRecordList(Map<String, Object> paramMap){
		List<ModelsExtractTestRecord> modelTestRecordList = modelsLibraryMapper.getModelTestRecordList(paramMap);
		PageInfo<ModelsExtractTestRecord> pageInfo = new PageInfo<>(modelTestRecordList);
		return pageInfo;
	}

	/**
	 * @Description:根据模型id获取Score分组详情list
	 * @Author: wxn
	 * @Date: 2017/12/25 15:02:09
	 * @Param:
	 * @Return
	 */
	@Override
	public List<LinkedHashMap<String, Object>> getModelTestLineListById(Long modelRecordId){
		return modelsLibraryMapper.getModelTestLineListById(modelRecordId);
	}

	/**
	 * @Description: 根据模型id获取获取有监督测试结果数据List
	 * @Author: wxn
	 * @Date: 2017/12/25 15:02:53
	 * @Param:
	 * @Return
	 */
	@Override
	public List<List<String>> getModelTestYesResultDataList(Long modelRecordId){
		List<List<String>> resultList = new ArrayList();
		//模型id获取获取有监督测试结果数据List（包含表头，但是在数据的rowValue中应该排除类似indexName的一列）
		List<LinkedHashMap<String, Object>> ModelTestYesResultList = new ArrayList<LinkedHashMap<String, Object>>();
		//需要排除的一列
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			ModelTestYesResultList = modelsLibraryMapper.getModelTestYesResultDataList(modelRecordId);
			map = modelsExtractTestRecordMapper.selectIndexName(modelRecordId);
			if(null != ModelTestYesResultList && !ModelTestYesResultList.isEmpty() && null != map && !map.isEmpty()){
				//要排除的一列
				String indexName=(String)map.get("index_name");
				//文件的一行表头数据
				String titleRow = (String) ModelTestYesResultList.get(0).get("rowValue");
				//获取要排除的列名所在的下标
				int flag = -1;
				//如果获取的要排除的列名不为空并且文件表头数据包含这个排除对象，则对ModelTestYesResultList进行移除操作
				if(UsedUtil.isNotNull(indexName)){
					//列名list
					List<String> rows = JsonUtils.fromJson(titleRow, List.class);
					//获取要排除的列名所在的下标
					flag = rows.indexOf(indexName);
				}
				//将查询结果转换为最后返回结果
				for (LinkedHashMap<String, Object> paramMap:ModelTestYesResultList) {
					List<String> list = new ArrayList<>();
					list.add((String)paramMap.get("indexName"));
					list.add((String)paramMap.get("predictProb"));
					list.add((String)paramMap.get("actualProb"));
					List<String> rowss = new ArrayList<String>();
					rowss = JsonUtils.fromJson((String)paramMap.get("rowValue"),List.class);
					for (int i = 0; i < rowss.size(); i++) {
						if(i!=flag){
							list.add(rowss.get(i));
						}
					}
					resultList.add(list);
				}
			}
		} catch (Exception e) {
			logger.info("获取有监督测试数据失败,失败原因===========", e);
		}
		return resultList;
	}

	/**
	 * @Description: 根据模型id和测试类型和下载类型获取下载url
	 * @Author: wxn
	 * @Date: 2017/12/26 11:38:18
	 * @Param:
	 * @Return
	 */
	@Override
	public Map<String,Object> getDownloadUrlById(Long modelRecordId,Long type){
		return modelsLibraryMapper.getDownloadUrlById(modelRecordId,type);
	}

	/**
	 * @Description: //根据模型测试记录id获取获取基本信息
	 * @Author: wxn
	 * @Date: 2017/12/26 15:08:57
	 * @Param:
	 * @Return
	 */
	@Override
	public Map<String,Object> getModelComparisonBasicData(Long testRecordId){
		return modelsLibraryMapper.getModelComparisonBasicData(testRecordId);
	}

	/**
	 * @Description: 根据模型测试记录id获取获取评价指标信息
	 * @Author: wxn
	 * @Date: 2017/12/26 15:08:57
	 * @Param:
	 * @Return
	 */
	@Override
	public Map<String,Object> getModelDateEvaluateData(Long testRecordId){
		return modelsLibraryMapper.getModelDateEvaluateData(testRecordId);
	}

	/**
	 * @Description: 根据模型测试记录id获取变量重要性排序list
	 * @Author: wxn
	 * @Date: 2017/12/26 17:04:00
	 * @Param:
	 * @Return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getModelVarImportanceSortList(Long testRecordId){
		return modelsLibraryMapper.getModelVarImportanceSortList(testRecordId);
	}



}
