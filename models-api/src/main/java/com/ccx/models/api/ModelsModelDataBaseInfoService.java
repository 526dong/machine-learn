package com.ccx.models.api;
	
import com.ccx.models.model.ModelsModelDataBaseInfo;
import com.github.pagehelper.Page;

import java.util.*;	
	
public interface ModelsModelDataBaseInfoService {	
		
	//主键获取	
	ModelsModelDataBaseInfo getById(Long id);	
		
	//获取无参list	
	List<ModelsModelDataBaseInfo> getList();	
		
	//获取有参数list	
	List<ModelsModelDataBaseInfo> getList(ModelsModelDataBaseInfo model);	
		
	//获取带分页list	
	Page<ModelsModelDataBaseInfo> getPageList(Map<String, Object> paramMap);
		
	//通过条件获取	
	ModelsModelDataBaseInfo getByModel(ModelsModelDataBaseInfo model);	
	
	//保存对象	
	int save(ModelsModelDataBaseInfo model);	
	
	//更新对象	
	int update(ModelsModelDataBaseInfo model);	
		
	//删除对象	
	int deleteById(Long id);	
		
	//其他查询	
	Map<String, Object> getOther();

    ModelsModelDataBaseInfo getByJoinDataFile(Long id);
}
