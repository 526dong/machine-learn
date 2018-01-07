package com.ccx.models.api.laboratory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型报告业务层接口
 */
public interface ModelsReportApi {


	/**
	 * 根据项目id获取项目基本信息
	 * @param programId
	 * @return
	 */
	LinkedHashMap<String, Object> getProgramMapById(Long programId);

	/**
	 * 根据项目id获取模型数据分析结果list
	 * @param programId
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getModelDateAnalyInfoById(Long programId);

	/**
	 * 根据项目id获取模型数据评价指标list
	 * @param programId
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getModelDateEvaluateIndexById(Long programId);

	/**
	 * 根据项目id获取Score分组详情list
	 * @param scoreParamMap
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getScoreGroupListById(Map<String,Object> scoreParamMap);

	/**
	 * @Description: 根据项目id获取按钮下载链接
	 * @Author: wxn
	 * @Date: 2017/11/30 20:35:34
	 */
	List<LinkedHashMap<String,Object>> getModelDownloadUrlById(Long programId);

	/**
	 * 根据项目id获取概率分箱list
	 * @param programId
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getProbabilityBinningListById(Long programId);

	/**
	 * 根据数据文件id获取变量重要性排序list
	 * @param programId
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getVarImportanceSortById(Long programId);

	/**
	 * @Description: 根据数据文件id获取IV前20个 list
	 * @Author: wxn
	 * @Date: 2017/12/7 09:43:04
	 * @Param:
	 * @Return
	 */
	List<LinkedHashMap<String,Object>> getModelIVListById(Long programId,Long arithmeticId);

	/**
	 * 根据项目id和算法id获取该项目下该算法下的曲线图1的数据
	 * @param paramMap
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getRocKsLineList1ById(Map<String,Integer> paramMap);

	/**
	 * 根据项目id和算法id获取该项目下该算法下的曲线图2的数据
	 * @param paramMap
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getRocKsLineList2ById(Map<String,Integer> paramMap);

	/**
	 * 根据项目id和算法id获取该项目下该算法下的曲线图3的数据
	 * @param paramMap
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getRocKsLineList3ById(Map<String,Integer> paramMap);

	/**
	 * 根据项目id和算法id获取该项目下该算法下的曲线图4的数据
	 * @param paramMap
	 * @return
	 */
	List<LinkedHashMap<String,Object>> getRocKsLineList4ById(Map<String,Integer> paramMap);

	/**
	 * @Description: 根据项目id和算法id获取下载url
	 * @Author: wxn
	 * @Date: 2017/11/30 21:31:32
	 */
	Map<String,Object> getDownloadUrlById(Long programId,Long arithmeticId);

	/**
	 * @Description: 根据fileId查找中英文对应list
	 * @Author: wxn
	 * @Date: 2017/12/20 14:44:54
	 * @Param:
	 * @Return
	 */
	List<LinkedHashMap<String,Object>> getChinsesAndEnglish(Long fileId);



}
