package com.ccx.models.service.impl.datafile;
	
import com.ccx.models.api.datafile.ModelsFileIvTopnApi;

import com.ccx.models.mapper.datafile.ModelsFileIvTopnMapper;
import com.ccx.models.model.datafile.ModelsFileIvTopn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	
import java.util.*;	
	
@Service	
public class ModelsFileIvTopnServiceImpl implements ModelsFileIvTopnApi {
		
	@Autowired	
    private ModelsFileIvTopnMapper modelsFileIvTopnMapper;
		
	//主键获取	
	@Override	
	public ModelsFileIvTopn getById(Long id) {
		return modelsFileIvTopnMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsFileIvTopn> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsFileIvTopn> getList(ModelsFileIvTopn model) {	
		return null;	
	}	
		
	//获取带分页list	
	/*@Override
	public Page<ModelsFileIvTopn> getPageList(Page<ModelsFileIvTopn> page) {
		return null;	
	}*/
		
	//通过条件获取	
	@Override	
	public ModelsFileIvTopn getByModel(ModelsFileIvTopn model) {	
		return null;	
	}	
	
	//保存对象	
	@Override	
	public int save(ModelsFileIvTopn model) {	
		return modelsFileIvTopnMapper.insert(model);	
	}	
	
	//更新对象	
	@Override	
	public int update(ModelsFileIvTopn model) {	
		return modelsFileIvTopnMapper.updateByPrimaryKey(model);	
	}	
		
	//删除对象	
	@Override	
	public int deleteById(Long id) {	
		return modelsFileIvTopnMapper.deleteByPrimaryKey(id);	
	}	
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}

	@Override
	public List<ModelsFileIvTopn> getIvListByFileIdAndVarName(Integer dataFileId, String varName) {
		return modelsFileIvTopnMapper.getIvListByFileIdAndVarName(dataFileId, varName);
	}
}	
