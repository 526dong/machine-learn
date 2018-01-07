package com.ccx.models.api.modelsLibrary;

import com.ccx.models.model.ModelsExtract;
import com.ccx.models.model.ModelsExtractTestRecord;
import com.ccx.models.util.TimerConcurrentHashMap;
import com.github.pagehelper.PageInfo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型提取业务层接口
 */
public interface ModelsLibraryApi {

    //定义数据存放位置
    public static TimerConcurrentHashMap<String,Object> timerConcurrentHashMap = new TimerConcurrentHashMap<String,Object>(new Long((long)(10*60*1000)),100000);

    /**
     * @Description: 通过模型名称查找模型list
     * @Author: wxn
     * @Date: 2017/12/12 13:19:57
     * @Param:
     * @Return
     */
	List<ModelsExtract> getModelsExtractListByName(String modelName);

    /**
     * @Description: 通过模型项目id和算法id查找模型list
     * @Author: wxn
     * @Date: 2017/12/12 13:19:57
     * @Param:
     * @Return
     */
    List<ModelsExtract> getModelsExtractListById(Long programId,Long arithmeticId);

    /**
     * @Description: 保存模型信息
     * @Author: wxn
     * @Date: 2017/12/12 13:32:22
     * @Param:
     * @Return
     */
    int saveModelsExtract(ModelsExtract modelsExtract);

    /**
     * @Description: 分页查询模型列表
     * @Author: wxn
     * @Date: 2017/12/12 14:42:57
     * @Param:
     * @Return
     */
    PageInfo<ModelsExtract> getModelsPageList(Map<String, Object> params);
    
    /**
     * @Description: 获取所有数据
     * @Author: wuchao
     * @Date: 2017年12月27日16:21:52
     * @Param:
     * @Return
     */
    List<ModelsExtract> getModelsAllList();

    /**
     * @Description: 通过模型id删除模型
     * @Author: wxn
     * @Date: 2017/12/12 16:09:33
     * @Param:modelsExtract
     * @Return
     */
    int updateModelById(ModelsExtract modelsExtract);

    /**
     * @Description: 通过id获取模型基本信息
     * @Author: wxn
     * @Date: 2017/12/12 16:23:57
     * @Param:
     * @Return
     */
    ModelsExtract getModelById(Long modelId);

    /**
     * @Description: 获取模型测试记录列表
     * @Author: wxn
     * @Date: 2017/12/13 11:51:09
     * @Param:
     * @Return
     */
    PageInfo<ModelsExtractTestRecord> getModelTestRecordList(Map<String, Object> params);

    /**
     * @Description:根据模型id获取Score分组详情list
     * @Author: wxn
     * @Date: 2017/12/25 15:02:09
     * @Param:
     * @Return
     */
    List<LinkedHashMap<String, Object>> getModelTestLineListById(Long modelRecordId);

    /**
     * @Description: 根据模型id获取获取有监督测试结果数据List
     * @Author: wxn
     * @Date: 2017/12/25 15:02:53
     * @Param:
     * @Return
     */
    List<List<String>> getModelTestYesResultDataList(Long modelRecordId);

    /**
     * @Description: 根据模型id和测试类型和下载类型获取下载url
     * @Author: wxn
     * @Date: 2017/12/26 11:38:18
     * @Param:
     * @Return
     */
    Map<String,Object> getDownloadUrlById(Long modelRecordId,Long type);

    /**
     * @Description: 根据模型测试记录id获取获取基本信息
     * @Author: wxn
     * @Date: 2017/12/26 15:08:57
     * @Param:
     * @Return
     */
    Map<String,Object> getModelComparisonBasicData(Long testRecordId);

    /**
     * @Description: 根据模型测试记录id获取获取评价指标信息
     * @Author: wxn
     * @Date: 2017/12/26 15:08:57
     * @Param:
     * @Return
     */
    Map<String,Object> getModelDateEvaluateData(Long testRecordId);

    /**
     * @Description: 根据模型测试记录id获取变量重要性排序list
     * @Author: wxn
     * @Date: 2017/12/26 17:04:00
     * @Param:
     * @Return
     */
    List<LinkedHashMap<String,Object>> getModelVarImportanceSortList(Long testRecordId);



}
