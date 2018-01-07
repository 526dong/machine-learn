package com.ccx.models.service.impl;
	
import com.ccx.models.api.ModelsVarNumricCountService;
import com.ccx.models.mapper.ModelsVarNumricCountMapper;
import com.ccx.models.model.ModelsVarNumricCount;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	
import java.util.*;	
	
@Service	
public class ModelsVarNumricCountServiceImpl implements ModelsVarNumricCountService {
		
	@Autowired	
    private ModelsVarNumricCountMapper modelsVarNumricCountMapper;
		
	//主键获取	
	@Override	
	public ModelsVarNumricCount getById(Integer id) {	
		return modelsVarNumricCountMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsVarNumricCount> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsVarNumricCount> getList(ModelsVarNumricCount model) {
		return modelsVarNumricCountMapper.selectListByModel(model);
	}	
		
	//获取带分页list	
	@Override	
	public Page<ModelsVarNumricCount> getPageList(Map<String, Object> paramMap) {
		return null;	
	}	
		
	//通过条件获取	
	@Override	
	public ModelsVarNumricCount getByModel(ModelsVarNumricCount model) {	
		return null;	
	}	
	
	//保存对象	
	@Override	
	public int save(ModelsVarNumricCount model) {	
		return modelsVarNumricCountMapper.insert(model);	
	}	
	
	//更新对象	
	@Override	
	public int update(ModelsVarNumricCount model) {	
		return modelsVarNumricCountMapper.updateByPrimaryKey(model);	
	}	
		
	//删除对象	
	@Override	
	public int deleteById(Integer id) {	
		return modelsVarNumricCountMapper.deleteByPrimaryKey(id);	
	}	
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}	
}	
