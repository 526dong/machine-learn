package com.ccx.models.api.datafile;
	
import com.ccx.models.model.datafile.ModelsFileValue;
import com.ccx.models.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
	
public interface ModelsFileValueApi {
		
	//主键获取	
	ModelsFileValue getById(Integer id);
		
	//获取无参list	
	List<ModelsFileValue> getList();	
		
	//获取有参数list	
	List<ModelsFileValue> getList(ModelsFileValue model);	
		
	//获取带分页list	
	List<ModelsFileValue> getPageList(Page<ModelsFileValue> page);
		
	//通过条件获取	
	ModelsFileValue getByModel(ModelsFileValue model);	
	
	//保存对象	
	int save(ModelsFileValue model);

	/*//批量保存
	void batchInsert(List<ModelsFileValue> list);*/
	
	//更新对象	
	int update(ModelsFileValue model);	
		
	//删除对象	
	int deleteById(Integer id);	
		
	//其他查询	
	Map<String, Object> getOther();	
}	
