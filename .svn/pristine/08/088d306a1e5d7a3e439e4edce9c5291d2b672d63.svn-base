package com.ccx.models.service.impl;
	
import com.ccx.models.api.ModelsVarCategoryCountService;
import com.ccx.models.mapper.ModelsVarCategoryCountMapper;
import com.ccx.models.model.ModelsVarCategoryCount;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	
import java.util.*;	
	
@Service	
public class ModelsVarCategoryCountServiceImpl implements ModelsVarCategoryCountService {
		
	@Autowired	
    private ModelsVarCategoryCountMapper modelsVarCategoryCountMapper;
		
	//主键获取	
	@Override	
	public ModelsVarCategoryCount getById(Integer id) {	
		return modelsVarCategoryCountMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsVarCategoryCount> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsVarCategoryCount> getList(ModelsVarCategoryCount model) {	
		return modelsVarCategoryCountMapper.selectListByModel(model);
	}	
		
	//获取带分页list	
	@Override	
	public Page<ModelsVarCategoryCount> getPageList(Map<String, Object> paramMap) {
		return null;	
	}	
		
	//通过条件获取	
	@Override	
	public ModelsVarCategoryCount getByModel(ModelsVarCategoryCount model) {	
		return null;	
	}	
	
	//保存对象	
	@Override	
	public int save(ModelsVarCategoryCount model) {	
		return modelsVarCategoryCountMapper.insert(model);	
	}	
	
	//更新对象	
	@Override	
	public int update(ModelsVarCategoryCount model) {	
		return modelsVarCategoryCountMapper.updateByPrimaryKey(model);	
	}	
		
	//删除对象	
	@Override	
	public int deleteById(Integer id) {	
		return modelsVarCategoryCountMapper.deleteByPrimaryKey(id);	
	}	
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}	
}	
