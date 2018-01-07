package com.ccx.models.service.impl;
	
import com.ccx.models.api.ModelsModelDataAnalyInfoService;
import com.ccx.models.mapper.ModelsModelDataAnalyInfoMapper;
import com.ccx.models.model.ModelsModelDataAnalyInfo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	
import java.util.*;	
	
@Service	
public class ModelsModelDataAnalyInfoServiceImpl implements ModelsModelDataAnalyInfoService {
		
	@Autowired	
    private ModelsModelDataAnalyInfoMapper modelsModelDataAnalyInfoMapper;
		
	//主键获取	
	@Override	
	public ModelsModelDataAnalyInfo getById(Long id) {	
		return modelsModelDataAnalyInfoMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsModelDataAnalyInfo> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsModelDataAnalyInfo> getList(ModelsModelDataAnalyInfo model) {	
		return null;	
	}	
		
	//获取带分页list	
	@Override	
	public Page<ModelsModelDataAnalyInfo> getPageList(Map<String, Object> paramMap) {
		return null;	
	}	
		
	//通过条件获取	
	@Override	
	public ModelsModelDataAnalyInfo getByModel(ModelsModelDataAnalyInfo model) {	
		return null;	
	}	
	
	//保存对象	
	@Override	
	public int save(ModelsModelDataAnalyInfo model) {	
		return modelsModelDataAnalyInfoMapper.insert(model);	
	}	
	
	//更新对象	
	@Override	
	public int update(ModelsModelDataAnalyInfo model) {	
		return modelsModelDataAnalyInfoMapper.updateByPrimaryKey(model);	
	}	
		
	//删除对象	
	@Override	
	public int deleteById(Long id) {	
		return modelsModelDataAnalyInfoMapper.deleteByPrimaryKey(id);	
	}	
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}	
}	
