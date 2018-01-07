package com.ccx.models.api.datafile;

import com.ccx.models.model.datafile.ModelsFileRowValue;
import com.ccx.models.util.Page;

import java.util.List;
import java.util.Map;

/**
 * @author created by xzd on 2017/12/4
 */
public interface ModelsFileRowValueApi {
		
	//主键获取	
	ModelsFileRowValue getById(Integer id);
		
	//获取无参list	
	List<ModelsFileRowValue> getList();
		
	//获取有参数list	
	List<ModelsFileRowValue> getList(ModelsFileRowValue model);
		
	//获取带分页list	
	List<ModelsFileRowValue> getPageList(Page<ModelsFileRowValue> page);
		
	//通过条件获取	
	ModelsFileRowValue getByModel(ModelsFileRowValue model);
	
	//保存对象	
	int save(ModelsFileRowValue model);

	//批量保存
	void batchInsert(List<ModelsFileRowValue> list);
	
	//更新对象	
	int update(ModelsFileRowValue model);
		
	//删除对象	
	int deleteById(Integer id);	
		
	//其他查询	
	Map<String, Object> getOther();	
}	
