package com.ccx.models.api;
	
import com.ccx.models.model.ModelsModelDataEvaluateIndex;
import com.github.pagehelper.Page;

import java.util.*;	
	
public interface ModelsModelDataEvaluateIndexService {	
		
	//主键获取	
	ModelsModelDataEvaluateIndex getById(Long id);	
		
	//获取无参list	
	List<ModelsModelDataEvaluateIndex> getList();	
		
	//获取有参数list	
	List<ModelsModelDataEvaluateIndex> getList(ModelsModelDataEvaluateIndex model);	
		
	//获取带分页list	
	Page<ModelsModelDataEvaluateIndex> getPageList(Map<String, Object> paramMap);
		
	//通过条件获取	
	ModelsModelDataEvaluateIndex getByModel(ModelsModelDataEvaluateIndex model);	
	
	//保存对象	
	int save(ModelsModelDataEvaluateIndex model);	
	
	//更新对象	
	int update(ModelsModelDataEvaluateIndex model);	
		
	//删除对象	
	int deleteById(Long id);	
		
	//其他查询	
	Map<String, Object> getOther();	
}	
