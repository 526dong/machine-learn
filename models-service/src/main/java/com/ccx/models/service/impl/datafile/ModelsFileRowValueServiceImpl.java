package com.ccx.models.service.impl.datafile;

import com.ccx.models.api.datafile.ModelsFileRowValueApi;
import com.ccx.models.mapper.datafile.ModelsFileRowValueMapper;
import com.ccx.models.model.datafile.ModelsFileRowValue;
import com.ccx.models.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xzd
 */
@Service
public class ModelsFileRowValueServiceImpl implements ModelsFileRowValueApi {
		
	@Autowired	
    private ModelsFileRowValueMapper modelsFileRowValueMapper;

	@Override
	public ModelsFileRowValue getById(Integer id) {
		return null;
	}

	@Override
	public List<ModelsFileRowValue> getList() {
		return null;
	}

	@Override
	public List<ModelsFileRowValue> getList(ModelsFileRowValue model) {
		return null;
	}

	@Override
	public List<ModelsFileRowValue> getPageList(Page<ModelsFileRowValue> page) {
		return null;
	}

	@Override
	public ModelsFileRowValue getByModel(ModelsFileRowValue model) {
		return null;
	}

	@Override
	public int save(ModelsFileRowValue model) {
		return 0;
	}

	@Override
	public void batchInsert(List<ModelsFileRowValue> list) {
		modelsFileRowValueMapper.batchInsert(list);
	}

	@Override
	public int update(ModelsFileRowValue model) {
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		return 0;
	}

	@Override
	public Map<String, Object> getOther() {
		return null;
	}
}
