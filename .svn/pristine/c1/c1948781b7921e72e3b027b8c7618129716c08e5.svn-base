package com.ccx.models.modelsLibrary;

import com.alibaba.fastjson.JSON;
import com.ccx.models.api.datafile.ModelsDataFileApi;
import com.ccx.models.api.laboratory.ModelsReportApi;
import com.ccx.models.api.modelsLibrary.ModelsLibraryApi;
import com.ccx.models.base.BaseController;
import com.ccx.models.model.ModelsExtract;
import com.ccx.models.model.ModelsExtractTestRecord;
import com.ccx.models.model.User;
import com.ccx.models.model.datafile.ModelsDataFile;
import com.ccx.models.service.impl.datafile.CommonFileValue;
import com.ccx.models.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.zip.ZipOutputStream;

/**
 * 模型报告
 */
@Controller
@RequestMapping("/modelsLibrary")
public class ModelsLibraryController extends BaseController {

    private static Logger logger = LogManager.getLogger(ModelsLibraryController.class);

    @Autowired
    private ModelsLibraryApi modelsLibraryApi;

    @Autowired
    private ModelsReportApi modelsReportApi;

    @Autowired
    private ModelsDataFileApi modelsDataFileApi;


    /**
     * 模型提取
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/extractModels", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult extractModels(ModelsExtract modelsExtract, HttpServletRequest request) {
        if (null != modelsExtract) {
            try {
                //通过模型名称查找模型list
                List<ModelsExtract> modelsExtractList1 = modelsLibraryApi.getModelsExtractListByName(modelsExtract.getModelName());
                if (null == modelsExtractList1 || modelsExtractList1.isEmpty()) {
                    //通过模型项目id和算法id查找模型list
                    List<ModelsExtract> modelsExtractList2 = modelsLibraryApi.getModelsExtractListById(modelsExtract.getProgramId(), modelsExtract.getArithmeticId());
                    if (null == modelsExtractList2 || modelsExtractList2.isEmpty()) {
                        User user = ControllerUtil.getSessionUser(request);
                        if (null != user) {
                            modelsExtract.setCreater(user.getLoginName());
                            //状态 0:正常 1：删除
                            modelsExtract.setDelFlag(0);
                            //模型提供 提取路径
                            modelsExtract.setModelPath("");
                            modelsExtract.setCreateTime(new Date());
                            //保存模型信息
                            int msg = modelsLibraryApi.saveModelsExtract(modelsExtract);
                            if (msg > 0) {
                                return JsonResult.ok("模型提取成功！");
                            }
                        }
                    } else {
                        logger.error("模型提取失败，失败原因============该项目与算法下已经提取过模型，不能重复提取！");
                        return JsonResult.error("模型已存在，不能重复提取！");
                    }
                } else {
                    logger.error("模型提取失败，失败原因============数据库中已经存在相同名称的模型！");
                    return JsonResult.error("模型名称已存在！");
                }
            } catch (Exception e) {
                logger.error("模型提取失败，失败原因:", e);
                return JsonResult.error("模型提取失败！");
            }
        } else {
            logger.error("模型提取失败，失败原因============传参有误！");
            return JsonResult.error("模型提取失败！");
        }
        return JsonResult.error("模型提取失败！");
    }

    /**
     * 校验模型名称唯一性
     *
     * @return
     */
    @PostMapping("/isOnlyModelName")
    @ResponseBody
    public Map<String, String> isOnlyModelName(HttpServletRequest request) {
        String modelName = request.getParameter("modelName") == null ? "" : request.getParameter("modelName");
        Map<String, String> map = new HashMap<String, String>();
        if (UsedUtil.isNotNull(modelName)) {
            //通过模型名称查找模型list
            List<ModelsExtract> modelsExtractList1 = modelsLibraryApi.getModelsExtractListByName(modelName);
            if (null != modelsExtractList1 && modelsExtractList1.size() > 0) {
                map.put("result", "0");
            } else {
                map.put("result", "1");
            }
        } else {
            map.put("result", "0");
        }
        return map;
    }

    /**
     * 校验模型是否重复提交
     *
     * @return
     */
    @PostMapping("/isRepetitionExtract")
    @ResponseBody
    public Map<String, String> isRepetitionExtract(HttpServletRequest request) {
        String programIds = request.getParameter("programIds") == null ? "" : request.getParameter("programIds");
        String arithmeticIds = request.getParameter("arithmeticIds") == null ? "" : request.getParameter("arithmeticIds");
        Map<String, String> map = new HashMap<String, String>();
        if (UsedUtil.isNotNull(programIds) && UsedUtil.isNotNull(arithmeticIds)) {
            ///通过模型项目id和算法id查找模型list
            List<ModelsExtract> modelsExtractList = modelsLibraryApi.getModelsExtractListById(Long.valueOf(programIds), Long.valueOf(arithmeticIds));
            if (null != modelsExtractList && modelsExtractList.size() > 0) {
                map.put("result", "0");
            } else {
                map.put("result", "1");
            }
        } else {
            map.put("result", "0");
        }
        return map;
    }


    /**
     * @Description: 跳转至模型列表页
     * @Author: wxn
     * @Date: 2017/12/12 14:29:39
     * @Param:
     * @Return
     */
    @RequestMapping(value = "/toModelsListPage", method = RequestMethod.GET)
    public String toModelsListPage(HttpServletRequest request) {
        return "modelsLibrary/modelsList";
    }

    /**
     * 分页查询模型列表
     *
     * @return
     */
    @PostMapping("/getModelsList")
    @ResponseBody
    public JsonResult getModelsList(HttpServletRequest request) {
        //获取查询条件
        Map<String, Object> params = ControllerUtil.requestMap(request);
        //分页
        PageInfo<ModelsExtract> pages = new PageInfo<ModelsExtract>();
        PageHelper.startPage(getPageNum(), getPageSize());
        //角色
        String roleType = ControllerUtil.getRoleType();
        params.put("roleType", roleType);
        try {
            pages = modelsLibraryApi.getModelsPageList(params);
            //成功标识
            return JsonResult.ok(pages);
        } catch (Exception e) {
            logger.error("查询模型列表失败:", e);
            return JsonResult.error("查询模型列表失败！");
        }
    }

    /**
     * 通过模型id删除模型
     *
     * @return
     */
    @PostMapping("/deleteModelById")
    @ResponseBody
    public JsonResult deleteModelById(HttpServletRequest request) {
        String idStr = request.getParameter("modelId") == null ? "" : request.getParameter("modelId");
        if (UsedUtil.isNotNull(idStr)) {
            try {
                ModelsExtract modelsExtract = new ModelsExtract();
                modelsExtract.setId(Long.valueOf(idStr));
                //删除标志
                modelsExtract.setDelFlag(1);
                int msg = modelsLibraryApi.updateModelById(modelsExtract);
                if (msg > 0) {
                    return JsonResult.ok("删除成功！");
                }
            } catch (Exception e) {
                logger.error("通过模型id删除模型失败,失败原因：", e);
                return JsonResult.error("删除失败！");
            }
        } else {
            logger.error("通过模型id删除模型失败,失败原因================没有获取到参数");
            return JsonResult.error("删除失败！");
        }
        return JsonResult.error("删除失败！");
    }

    /**
     * @Description: 跳转至模型详情页
     * @Author: wxn
     * @Date: 2017/12/12 14:29:39
     * @Param:
     * @Return
     */
    @RequestMapping(value = "/toModelsDetailPage", method = RequestMethod.GET)
    public String toModelsDetailPage(HttpServletRequest request) {
        String idStr = request.getParameter("modelId") == null ? "" : request.getParameter("modelId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        //模型id
        Long modelId = null;
        //项目id
        Long programId = null;
        //文件id
        Long dataFileId = null;
        //根据项目id获取项目基本信息
        LinkedHashMap<String, Object> programBasicInfoMap = new LinkedHashMap<String, Object>();
        //根据项目id获取模型数据分析结果list
        List<LinkedHashMap<String, Object>> modelDateAnalyInfoList = new ArrayList<LinkedHashMap<String, Object>>();
        //根据项目id获取模型数据评价指标list
        List<LinkedHashMap<String, Object>> modelDateEvaluateIndexList = new ArrayList<LinkedHashMap<String, Object>>();
        //根据数据文件id获取变量重要性排序list
        List<LinkedHashMap<String, Object>> varImportanceSortList = new ArrayList<LinkedHashMap<String, Object>>();
        //根据项目id获取Score分组详情list ----训练集
        List<LinkedHashMap<String, Object>> scoreGroupList = new ArrayList<LinkedHashMap<String, Object>>();
        //根据项目id获取Score分组详情list ----测试集
        List<LinkedHashMap<String, Object>> scoreGroupList2 = new ArrayList<LinkedHashMap<String, Object>>();
        //该项目采用算法name list
        List<String> arithmeticList = new ArrayList<String>();
        //该项目采用算法id list
        List<String> arithmeticIdList = new ArrayList<String>();
        if (UsedUtil.isNotNull(idStr)) {
            try {
                //通过id获取模型基本信息
                modelId = Long.valueOf(idStr);
                ModelsExtract modelsExtract = modelsLibraryApi.getModelById(modelId);
                programId = modelsExtract.getProgramId();
                dataFileId = modelsExtract.getDataFileId();
                programBasicInfoMap = modelsReportApi.getProgramMapById(programId);
                //判断项目信息是否为空
                if (null != programBasicInfoMap && !programBasicInfoMap.isEmpty()) {
                    modelDateAnalyInfoList = modelsReportApi.getModelDateAnalyInfoById(programId);
                    modelDateEvaluateIndexList = modelsReportApi.getModelDateEvaluateIndexById(programId);
                    Map<String, Object> scoreParamMap = new HashMap<String, Object>();
                    scoreParamMap.put("programId", programId);
                    scoreParamMap.put("type", "train");
                    scoreGroupList = modelsReportApi.getScoreGroupListById(scoreParamMap);
                    scoreParamMap.put("type", "test");
                    scoreGroupList2 = modelsReportApi.getScoreGroupListById(scoreParamMap);
                    varImportanceSortList = modelsReportApi.getVarImportanceSortById(programId);
                    arithmeticList.add(modelsExtract.getArithmeticName());
                    arithmeticIdList.add(String.valueOf(modelsExtract.getArithmeticId()));
                    programBasicInfoMap.put("arithmeticNames", modelsExtract.getArithmeticName());
                    //该项目创建时间
                    Date createTime = (Date) programBasicInfoMap.get("createTime");
                    if (null != createTime) {
                        programBasicInfoMap.put("createTime", formatter.format(createTime));
                    }
                }
            } catch (Exception e) {
                logger.info("模型库-----模型详情-----根据项目id获取项目基本信息失败", e);
            }
        }
        request.setAttribute("modelId", modelId);
        request.setAttribute("programId", programId);
        request.setAttribute("dataFileId", dataFileId);
        request.setAttribute("programBasicInfoMap", programBasicInfoMap);
        request.setAttribute("arithmeticList", arithmeticList);
        request.setAttribute("arithmeticNameList", JSON.toJSONString(arithmeticList));
        request.setAttribute("arithmeticIdList", JSON.toJSONString(arithmeticIdList));
        request.setAttribute("modelDateAnalyInfoList", JSON.toJSONString(modelDateAnalyInfoList));
        request.setAttribute("modelDateEvaluateIndexList", JSON.toJSONString(modelDateEvaluateIndexList));
        request.setAttribute("varImportanceSortList", JSON.toJSONString(varImportanceSortList));
        request.setAttribute("scoreGroupList", JSON.toJSONString(scoreGroupList));
        request.setAttribute("scoreGroupList2", JSON.toJSONString(scoreGroupList2));
        return "modelsLibrary/modelsDetail";
    }

    /**
     * @Description: 跳转至模型测试页
     * @Author: wxn
     * @Date: 2017/12/12 14:29:39
     * @Param:
     * @Return
     */
    @RequestMapping(value = "/toModelTestPage", method = RequestMethod.GET)
    public String toModelTestPage(HttpServletRequest request) {
        String idStr = request.getParameter("modelId") == null ? "" : request.getParameter("modelId");
        request.setAttribute("modelId", idStr);
        return "modelsLibrary/modelsTest";
    }

    /**
     * 获取模型测试记录列表
     *
     * @return
     */
    @PostMapping("/getModelTestRecordList")
    @ResponseBody
    public JsonResult getModelTestRecordList(HttpServletRequest request) {
        //获取查询条件
        Map<String, Object> params = ControllerUtil.requestMap(request);
        //分页
        PageInfo<ModelsExtractTestRecord> pages = new PageInfo<ModelsExtractTestRecord>();
        PageHelper.startPage(getPageNum(), getPageSize());
        try {
            pages = modelsLibraryApi.getModelTestRecordList(params);
            //成功标识
            return JsonResult.ok(pages);
        } catch (Exception e) {
            logger.error("获取模型测试记录列表失败:", e);
            return JsonResult.error("获取模型测试记录列表失败！");
        }
    }

    /**
     * 模型文件导入
     * 101 参数错误
     * 102 导入失败，请联系管理员
     *
     * @return
     */
    @RequestMapping(value = "/doImportModelFile", method = RequestMethod.POST)
    @ResponseBody
    public String doImportModelFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("dataFile") MultipartFile dataFile) {
        String idStr = request.getParameter("modelId") == null ? "" : request.getParameter("modelId");
        //返回的结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (UsedUtil.isNotNull(idStr)) {
            Lock lock = null;
            try {
                //通过id获取模型基本信息
                ModelsExtract modelsExtract = modelsLibraryApi.getModelById(Long.valueOf(idStr));
                if (null != modelsExtract && null != modelsExtract.getDataFileId()) {
                    //锁文件id
                    lock = LockUtils.getLock(String.valueOf(modelsExtract.getDataFileId()));
                    lock.lock();
                    //通过文件id获取文件信息
                    ModelsDataFile modelsDataFile = modelsDataFileApi.getById(Integer.valueOf(String.valueOf(modelsExtract.getDataFileId())));
                    if (null != modelsDataFile) {
                        /**
                         * 调取文件校验接口
                         */
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("fileName", modelsDataFile.getName());
                        params.put("fileType", modelsDataFile.getType());
                        params.put("fileCode", modelsDataFile.getCodeType());
                        params.put("splitCode", modelsDataFile.getFileDelimiter());
                        params.put("nullValue", modelsDataFile.getNullValue());
                        params.put("targetName", modelsDataFile.getTargetName());
                        params.put("indexName", modelsDataFile.getIndexName());
                        params.put("description", modelsDataFile.getDescription());
                        ImportUtils importUtils = new ImportUtils();
                        //调取文件校验
                        CommonFileValue.DataFile commonFileValue = null;
                        logger.info("模型文件导入,调用校验逻辑，校验开始");
                        try {
                            commonFileValue = importUtils.importCsvFile(dataFile, params, false);
                        } catch (Exception e) {
                            if (e instanceof MyRuntimeException) {
                                logger.info("模型文件导入失败,失败原因===========验证不通过，具体信息：" + e.getMessage(), e);
                                String msg = e.getMessage();
                                resultMap.put("code", 999);
                                resultMap.put("msg", URLEncoder.encode(msg.split("::")[1], "UTF-8"));
                                return JSON.toJSONString(resultMap);
                            }
                            logger.info("模型文件导入失败,失败原因===========调用接口失败", e);
                            resultMap.put("code", 102);
                            return JSON.toJSONString(resultMap);
                        }
                        logger.info("模型文件导入,调用校验逻辑，校验结束");
                        if (null != commonFileValue) {
                            //定义数据存放位置
                            TimerConcurrentHashMap<String, Object> timerConcurrentHashMap = ModelsLibraryApi.timerConcurrentHashMap;
                            timerConcurrentHashMap.put("commonFileValue", commonFileValue);
                            logger.info("模型文件导入成功");
                            resultMap.put("code", 888);
                        } else {
                            logger.info("模型文件导入失败,失败原因===========通过文件id没有获取到文件信息");
                            resultMap.put("code", 102);
                        }
                    } else {
                        logger.info("模型文件导入失败,失败原因===========通过文件id没有获取到文件信息");
                        resultMap.put("code", 102);
                    }
                } else {
                    logger.info("模型文件导入失败,失败原因===========通过id没有获取到模型基本信息");
                    resultMap.put("code", 102);
                }
            } catch (Exception e) {
                logger.info("模型文件导入失败,失败原因", e);
                resultMap.put("code", 102);
                return JSON.toJSONString(resultMap);
            } finally {
                lock.unlock();
            }
        } else {
            logger.info("模型文件导入失败,失败原因===========参数有误");
            resultMap.put("code", 101);
        }
        return JSON.toJSONString(resultMap);
    }


    /**
     * 获取有监督测试数据
     *
     * @return
     */
    @RequestMapping(value="/getModelTestYesDataList", method = RequestMethod.POST,produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String getModelTestYesDataList(HttpServletRequest request) {
        String modelIdStr = request.getParameter("modelId") == null ? "" : request.getParameter("modelId");
        String modelRecordId = request.getParameter("modelRecordId") == null ? "" : request.getParameter("modelRecordId");
        //返回的结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (UsedUtil.isNotNull(modelIdStr)&&UsedUtil.isNotNull(modelRecordId)) {
            //根据模型id获取Score分组详情list
            List<LinkedHashMap<String, Object>> ModelTestYesScoreGroupList = new ArrayList<LinkedHashMap<String, Object>>();
            //根据模型id获取获取有监督测试结果数据List
            List<List<String>> ModelTestYesResultList = new ArrayList<List<String>>();
            try {
                ModelTestYesScoreGroupList = modelsLibraryApi.getModelTestLineListById(Long.valueOf(modelRecordId));
                ModelTestYesResultList = modelsLibraryApi.getModelTestYesResultDataList(Long.valueOf(modelRecordId));

                resultMap.put("code", 200);
                resultMap.put("ModelTestYesScoreGroupList", ModelTestYesScoreGroupList);
                resultMap.put("ModelTestYesResultList", ModelTestYesResultList);
                return JSON.toJSONString(resultMap);
            } catch (Exception e) {
                logger.info("获取有监督测试数据失败,失败原因===========", e);
            }
        }
        resultMap.put("code", 404);
        return JSON.toJSONString(resultMap);
    }

    /**
     * @Description: get请求下载文件
     * @Author: wxn
     * @Date: 2017/12/8 15:12:44
     * @Param:
     * @Return
     */
    @RequestMapping(value = "/downloadFiles", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFiles(HttpServletRequest request,HttpServletResponse response) {
        String modelRecordId = request.getParameter("modelRecordId");
        //测试类型：0无监督测试；1有监督测试
        String type = request.getParameter("type");
        if(UsedUtil.isNotNull(modelRecordId)&&UsedUtil.isNotNull(type)){
            try {
                //根据模型id和测试类型和下载类型获取下载url
                Map<String,Object> downloadUrlMap = modelsLibraryApi.getDownloadUrlById(Long.valueOf(modelRecordId),Long.valueOf(type));
                if(null != downloadUrlMap && !downloadUrlMap.isEmpty()){
                    String filePath = (String)downloadUrlMap.get("filePath");
                    if(UsedUtil.isNotNull(filePath)){
                        try{
                            //文件名称
                            String fileName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
                            if(UsedUtil.isNotNull(fileName)){
                                //下载
                                DownloadUtil.download(filePath,fileName,response);
                            }
                        }catch (Exception e){
                            logger.info("下载失败，失败原因==========",e);
                        }
                    }
                }
            } catch (Exception e) {
                logger.info("下载失败，失败原因==========",e);
            }
        }
    }

    /**
     * @Description: 跳转至模型列表页
     * @Author: wxn
     * @Date: 2017/12/12 14:29:39
     * @Param:
     * @Return
     */
    @RequestMapping(value = "/toPreModelsReportPage", method = RequestMethod.GET)
    public String toPreModelsReportPage(HttpServletRequest request) {
        Map<String,Object> basicInfoMap = new HashMap<String,Object>();
        Map<String,Object> modelDateEvaluateMap = new HashMap<String,Object>();
        String testRecordId = request.getParameter("id");
        if(UsedUtil.isNotNull(testRecordId)){
            try {
                //根据模型测试记录id获取获取基本信息
                basicInfoMap = modelsLibraryApi.getModelComparisonBasicData(Long.valueOf(testRecordId));
                //根据模型测试记录id获取获取评价指标信息
                modelDateEvaluateMap = modelsLibraryApi.getModelDateEvaluateData(Long.valueOf(testRecordId));
            } catch (Exception e) {
                logger.info("根据模型测试记录id获取信息失败",e);
            }
        }
        request.setAttribute("basicInfoMap", basicInfoMap);
        request.setAttribute("modelDateEvaluateMap", modelDateEvaluateMap);
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("modelId", request.getParameter("modelId"));
        request.setAttribute("type", request.getParameter("type"));
        return "modelsLibrary/modelsReportPre";
    }

    /**
     * @Description: 获取模型对比数据
     * @Author: wxn
     * @Date: 2017/12/26 14:00:53
     * @Param:
     * @Return
     */
    @RequestMapping(value="/getModelComparisonData", method = RequestMethod.POST,produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String getModelComparisonData(HttpServletRequest request) {
        String testRecordId = request.getParameter("testRecordId");
        //返回的结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (UsedUtil.isNotNull(testRecordId)) {
            //根据模型测试记录id获取变量重要性排序list
            List<LinkedHashMap<String,Object>> varImportanceSortList = new ArrayList<LinkedHashMap<String,Object>>();
            try {
                varImportanceSortList = modelsLibraryApi.getModelVarImportanceSortList(Long.valueOf(testRecordId));
                resultMap.put("code", 200);
                resultMap.put("varImportanceSortList", varImportanceSortList);
                return JSON.toJSONString(resultMap);
            } catch (Exception e) {
                logger.info("根据模型测试记录id获取变量重要性排序失败，失败原因==========",e);
            }
        }
        resultMap.put("code", 404);
        return JSON.toJSONString(resultMap);
    }



}
