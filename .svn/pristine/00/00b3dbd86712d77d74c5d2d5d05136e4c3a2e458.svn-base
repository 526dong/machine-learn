package com.ccx.models.service.impl;
	
import com.ccx.models.api.ModelsModelDataEvaluateIndexService;
import com.ccx.models.mapper.ModelsModelDataEvaluateIndexMapper;
import com.ccx.models.model.ModelsModelDataEvaluateIndex;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	
import java.util.*;	
	
@Service	
public class ModelsModelDataEvaluateIndexServiceImpl implements ModelsModelDataEvaluateIndexService {
		
	@Autowired	
    private ModelsModelDataEvaluateIndexMapper modelsModelDataEvaluateIndexMapper;
		
	//主键获取	
	@Override	
	public ModelsModelDataEvaluateIndex getById(Long id) {	
		return modelsModelDataEvaluateIndexMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsModelDataEvaluateIndex> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsModelDataEvaluateIndex> getList(ModelsModelDataEvaluateIndex model) {	
		return null;	
	}	
		
	//获取带分页list	
	@Override	
	public Page<ModelsModelDataEvaluateIndex> getPageList(Map<String, Object> paramMap) {
		return null;	
	}	
		
	//通过条件获取	
	@Override	
	public ModelsModelDataEvaluateIndex getByModel(ModelsModelDataEvaluateIndex model) {	
		return null;	
	}	
	
	//保存对象	
	@Override	
	public int save(ModelsModelDataEvaluateIndex model) {	
		return modelsModelDataEvaluateIndexMapper.insert(model);	
	}	
	
	//更新对象	
	@Override	
	public int update(ModelsModelDataEvaluateIndex model) {	
		return modelsModelDataEvaluateIndexMapper.updateByPrimaryKey(model);	
	}	
		
	//删除对象	
	@Override	
	public int deleteById(Long id) {	
		return modelsModelDataEvaluateIndexMapper.deleteByPrimaryKey(id);	
	}	
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}	
}	
