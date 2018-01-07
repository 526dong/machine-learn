package com.ccx.models.mapper.modelsLibrary;

import com.ccx.models.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ModelsLibraryMapper {

    /**
     * @Description: 根据模型name获取模型list
     * @Author: wxn
     * @Date: 2017/12/12 11:13:04
     * @Param:modelName
     * @Return
     */
    List<ModelsExtract> getModelsExtractListByName(@Param("modelName")String modelName);

    /**
     * @Description:通过模型项目id和算法id查找模型list
     * @Author: wxn
     * @Date: 2017/12/12 13:21:02
     * @Param: [programId, arithmeticId]
     * @Return java.util.List<com.ccx.models.model.ModelsExtract>
     */
    List<ModelsExtract> getModelsExtractListById(@Param("programId")Long programId,@Param("arithmeticId")Long arithmeticId);

    /**
     * @Description:保存模型信息
     * @Author: wxn
     * @Date: 2017/12/12 13:33:22
     * @Param: [modelsExtract]
     * @Return int
     */
    int saveModelsExtract(ModelsExtract modelsExtract);

    /**
     * @Description: 分页查询模型列表
     * @Author: wxn
     * @Date: 2017/12/12 14:44:43
     * @Param: [paramMap]
     * @Return com.github.pagehelper.PageInfo<com.ccx.models.model.ModelsExtract>
     */
    List<ModelsExtract> getModelsPageList(Map<String, Object> paramMap);
    
    /**
     * @Description: 查询所有数据
     * 2017年12月27日16:42:12
     */
    List<ModelsExtract> getModelsAllList();

    /**
     * @Description: 通过模型id删除模型
     * @Author: wxn
     * @Date: 2017/12/12 16:10:00
     * @Param: [modelsExtract]
     * @Return int
     */
    int updateModelById(ModelsExtract modelsExtract);

    /**
     * @Description: 通过id获取模型基本信息
     * @Author: wxn
     * @Date: 2017/12/12 16:25:12
     * @Param: [modelId]
     * @Return com.ccx.models.model.ModelsExtract
     */
    ModelsExtract getModelById(Long modelId);

    /**
     * @Description: 获取模型测试记录列表
     * @Author: wxn
     * @Date: 2017/12/13 11:52:21
     * @Param: [paramMap]
     * @Return com.github.pagehelper.PageInfo<com.ccx.models.model.ModelsExtractTestRecord>
     */
    List<ModelsExtractTestRecord> getModelTestRecordList(Map<String, Object> paramMap);

    /**
     * @Description: 根据模型id获取Score分组详情list
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
    List<LinkedHashMap<String, Object>> getModelTestYesResultDataList(Long modelRecordId);

    /**
     * @Description: 根据模型id和测试类型和下载类型获取下载url
     * @Author: wxn
     * @Date: 2017/12/26 11:38:18
     * @Param:
     * @Return
     */
    Map<String,Object> getDownloadUrlById(@Param("modelRecordId") Long modelRecordId,@Param("type")Long type);

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

    /**
     * @Description: 文件路径实体类
     * @Author: wxn
     * @Date: 2017/12/27 15:44:14
     * @Param:
     * @Return
     */
    void insertModelsExtractOutPath(ModelsExtractOutPath modelsExtractOutPath);
    /**
     * @Description: 入模信息实体类
     * @Author: wxn
     * @Date: 2017/12/27 15:44:14
     * @Param:
     * @Return
     */
    void insertDataAnalyInfo(ModelsExtractDataAnalyInfo dataAnalyInfo);
    /**
     * @Description: 指标信息实体
     * @Author: wxn
     * @Date: 2017/12/27 15:44:14
     * @Param:
     * @Return
     */
    void insertDataEvaluateIndex(ModelsExtractDataEvaluateIndex dataEvaluateIndex);
    /**
     * @Description: 分箱信息实体
     * @Author: wxn
     * @Date: 2017/12/27 15:44:14
     * @Param:
     * @Return
     */
    void insertScoreGroupStatistics(List<ModelsExtractScoreGroupStatistics> scoreGroupStatisticsL);

}