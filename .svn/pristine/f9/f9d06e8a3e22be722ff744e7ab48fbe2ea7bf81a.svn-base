package com.ccx.models.api.modelsLibrary;

import java.util.List;
import java.util.Map;

import com.ccx.models.model.SectionCount;

/**
 * 模型详情
 */
public interface ModelsLibraryDataApi {

	
	/**
	 * 模型测试结果列表
	 * @return
	 */
	public List<List< String >> modelTextResult(String id);

	/**
	 * 模型库-统计区间
	 * @return
	 */
	public SectionCount modelTextCount(String id);

	/**
	 * 模型库-开始预测
	 * @return
	 */
	public Map<String, String> saveModelTest(Map<String, Object> params);

}
