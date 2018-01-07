package com.ccx.models.api;
	
import com.ccx.models.model.ModelsVarCategoryCount;
import com.github.pagehelper.Page;

import java.util.*;	
	
public interface ModelsVarCategoryCountService {	
		
	//主键获取	
	ModelsVarCategoryCount getById(Integer id);	
		
	//获取无参list	
	List<ModelsVarCategoryCount> getList();	
		
	//获取有参数list	
	List<ModelsVarCategoryCount> getList(ModelsVarCategoryCount model);	
		
	//获取带分页list	
	Page<ModelsVarCategoryCount> getPageList(Map<String, Object> paramMap);
		
	//通过条件获取	
	ModelsVarCategoryCount getByModel(ModelsVarCategoryCount model);	
	
	//保存对象	
	int save(ModelsVarCategoryCount model);	
	
	//更新对象	
	int update(ModelsVarCategoryCount model);	
		
	//删除对象	
	int deleteById(Integer id);	
		
	//其他查询	
	Map<String, Object> getOther();	
}	
