package com.ccx.models.service.impl.laboratory;

import com.ccx.models.api.laboratory.ModelsReportApi;
import com.ccx.models.mapper.laboratory.ModelsReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("modelsReportApi")
public class ModelsReportServiceImpl implements ModelsReportApi {
		
	@Autowired
    private ModelsReportMapper modelsReportMapper;

	/**
	 * 根据项目id获取项目基本信息
	 * @param programId
	 * @return
	 */
	@Override
	public LinkedHashMap<String, Object> getProgramMapById(Long programId) {
		return modelsReportMapper.getProgramMapById(programId);
	}

	/**
	 * 根据项目id获取模型数据分析结果list
	 * @param programId
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getModelDateAnalyInfoById(Long programId){
		return modelsReportMapper.getModelDateAnalyInfoById(programId);
	}

	/**
	 * 根据项目id获取模型数据评价指标list
	 * @param programId
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getModelDateEvaluateIndexById(Long programId){
		return modelsReportMapper.getModelDateEvaluateIndexById(programId);
	}

	/**
	 * 根据项目id获取Score分组详情list
	 * @param scoreParamMap
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getScoreGroupListById(Map<String,Object> scoreParamMap){
		return modelsReportMapper.getScoreGroupListById(scoreParamMap);
	}

	/**
	 * @Description: 根据项目id获取按钮下载链接
	 * @Author: wxn
	 * @Date: 2017/11/30 20:35:34
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getModelDownloadUrlById(Long programId){
		return modelsReportMapper.getModelDownloadUrlById(programId);
	}

	/**
	 * 根据项目id获取概率分箱list
	 * @param programId
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getProbabilityBinningListById(Long programId){
		return modelsReportMapper.getProbabilityBinningListById(programId);
	}

	/**
	 * 根据数据文件id获取变量重要性排序list
	 * @param programId
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getVarImportanceSortById(Long programId){
		return modelsReportMapper.getVarImportanceSortById(programId);
	}

	/**
	 * @Description:根据数据文件id获取IV前20个 list
	 * @Author: wxn
	 * @Date: 2017/12/7 09:43:44
	 * @Param: [programId,arithmeticId]
	 * @Return java.util.List<java.util.LinkedHashMap<java.lang.String,java.lang.Object>>
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getModelIVListById(Long programId,Long arithmeticId){
		return modelsReportMapper.getModelIVListById(programId,arithmeticId);
	}

	/**
	 * 根据项目id和算法id获取该项目下该算法下的曲线图1的数据
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getRocKsLineList1ById(Map<String,Integer> paramMap){
		return modelsReportMapper.getRocKsLineList1ById(paramMap);
	}

	/**
	 * 根据项目id和算法id获取该项目下该算法下的曲线图2的数据
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getRocKsLineList2ById(Map<String,Integer> paramMap){
		return modelsReportMapper.getRocKsLineList2ById(paramMap);
	}

	/**
	 * 根据项目id和算法id获取该项目下该算法下的曲线图3的数据
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getRocKsLineList3ById(Map<String,Integer> paramMap){
		return modelsReportMapper.getRocKsLineList3ById(paramMap);
	}

	/**
	 * 根据项目id和算法id获取该项目下该算法下的曲线图4的数据
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getRocKsLineList4ById(Map<String,Integer> paramMap){
		return modelsReportMapper.getRocKsLineList4ById(paramMap);
	}

	/**
	 * @Description: 根据项目id和算法id获取下载url
	 * @Author: wxn
	 * @Date: 2017/11/30 21:31:32
	 */
	@Override
	public Map<String,Object> getDownloadUrlById(Long programId,Long arithmeticId){
		return modelsReportMapper.getDownloadUrlById(programId,arithmeticId);
	}

	/**
	 * @Description: 根据fileId查找中英文对应list
	 * @Author: wxn
	 * @Date: 2017/12/20 14:44:54
	 * @Param:
	 * @Return
	 */
	@Override
	public List<LinkedHashMap<String,Object>> getChinsesAndEnglish(Long fileId){
		return modelsReportMapper.getChinsesAndEnglish(fileId);
	}




}
