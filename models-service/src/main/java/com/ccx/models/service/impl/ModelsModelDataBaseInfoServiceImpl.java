package com.ccx.models.service.impl;
	
import com.ccx.models.api.ModelsModelDataBaseInfoService;
import com.ccx.models.mapper.ModelsModelDataBaseInfoMapper;
import com.ccx.models.model.ModelsModelDataBaseInfo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	
import java.util.*;	
	
@Service	
public class ModelsModelDataBaseInfoServiceImpl implements ModelsModelDataBaseInfoService {
		
	@Autowired	
    private ModelsModelDataBaseInfoMapper modelsModelDataBaseInfoMapper;
		
	//主键获取	
	@Override	
	public ModelsModelDataBaseInfo getById(Long id) {	
		return modelsModelDataBaseInfoMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsModelDataBaseInfo> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsModelDataBaseInfo> getList(ModelsModelDataBaseInfo model) {	
		return null;	
	}	
		
	//获取带分页list	
	@Override	
	public Page<ModelsModelDataBaseInfo> getPageList(Map<String, Object> paramMap) {
		return null;	
	}	
		
	//通过条件获取	
	@Override	
	public ModelsModelDataBaseInfo getByModel(ModelsModelDataBaseInfo model) {	
		return null;	
	}	
	
	//保存对象	
	@Override	
	public int save(ModelsModelDataBaseInfo model) {	
		return modelsModelDataBaseInfoMapper.insert(model);	
	}	
	
	//更新对象	
	@Override	
	public int update(ModelsModelDataBaseInfo model) {	
		return modelsModelDataBaseInfoMapper.updateByPrimaryKey(model);	
	}	
		
	//删除对象	
	@Override	
	public int deleteById(Long id) {	
		return modelsModelDataBaseInfoMapper.deleteByPrimaryKey(id);	
	}	
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}

	@Override
	public ModelsModelDataBaseInfo getByJoinDataFile(Long id) {
		return modelsModelDataBaseInfoMapper.selectJoinDataFile(id);
	}
}	
