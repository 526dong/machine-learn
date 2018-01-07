package com.ccx.models.service.impl.datafile;
	
import com.ccx.models.api.datafile.ModelsFileValueApi;
import com.ccx.models.mapper.datafile.ModelsFileValueMapper;
import com.ccx.models.model.datafile.ModelsFileValue;
import com.ccx.models.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
	
@Service	
public class ModelsFileValueServiceImpl implements ModelsFileValueApi {
		
	@Autowired	
    private ModelsFileValueMapper modelsFileValueMapper;
		
	//主键获取	
	@Override	
	public ModelsFileValue getById(Integer id) {
		return modelsFileValueMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsFileValue> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsFileValue> getList(ModelsFileValue model) {	
		return null;	
	}	
		
	//获取带分页list	
	@Override	
	public List<ModelsFileValue> getPageList(Page<ModelsFileValue> page) {
		return null;	
	}	
		
	//通过条件获取	
	@Override	
	public ModelsFileValue getByModel(ModelsFileValue model) {	
		return null;	
	}	
	
	//保存对象	
	@Override	
	public int save(ModelsFileValue model) {	
		return modelsFileValueMapper.insert(model);	
	}

	/**
	 * 批量插入数据文件中的变量数据
	 * @param
	@Override
	public void batchInsert(List<ModelsFileValue> list) {
		modelsFileValueMapper.batchInsert(list);
	}*/

	//更新对象
	@Override	
	public int update(ModelsFileValue model) {	
		return modelsFileValueMapper.updateByPrimaryKey(model);	
	}	
		
	//删除对象	
	@Override	
	public int deleteById(Integer id) {	
		return modelsFileValueMapper.deleteByPrimaryKey(id);	
	}	
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}	
}	
