package com.ccx.models.api.datafile;

import com.ccx.models.model.datafile.ModelsFileIvTopn;

import java.util.*;
	
public interface ModelsFileIvTopnApi {
		
	//主键获取	
	ModelsFileIvTopn getById(Long id);
		
	//获取无参list	
	List<ModelsFileIvTopn> getList();	
		
	//获取有参数list	
	List<ModelsFileIvTopn> getList(ModelsFileIvTopn model);	
		
	//获取带分页list	
	//Page<ModelsFileIvTopn> getPageList(Page<ModelsFileIvTopn> page);
		
	//通过条件获取	
	ModelsFileIvTopn getByModel(ModelsFileIvTopn model);	
	
	//保存对象	
	int save(ModelsFileIvTopn model);	
	
	//更新对象	
	int update(ModelsFileIvTopn model);	
		
	//删除对象	
	int deleteById(Long id);	
		
	//其他查询	
	Map<String, Object> getOther();

	List<ModelsFileIvTopn> getIvListByFileIdAndVarName(Integer dataFileId, String varName);
}	
