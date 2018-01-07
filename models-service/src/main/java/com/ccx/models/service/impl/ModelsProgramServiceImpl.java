package com.ccx.models.service.impl;

import com.ccx.models.api.ModelsProgramApi;
import com.ccx.models.mapper.ModelsProgramMapper;
import com.ccx.models.mapper.datafile.ModelsDataFileMapper;
import com.ccx.models.model.ModelsArithmetic;
import com.ccx.models.model.ModelsProgram;
import com.ccx.models.model.datafile.ModelsDataFile;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service	
public class ModelsProgramServiceImpl implements ModelsProgramApi {
		
	@Autowired	
    private ModelsProgramMapper modelsProgramMapper;

	@Autowired
	private ModelsDataFileMapper modelsDataFileMapper;
		
	//主键获取	
	@Override	
	public ModelsProgram getById(Integer id) {	
		return modelsProgramMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsProgram> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsProgram> getList(ModelsProgram model) {	
		return null;
	}	
		
	//获取带分页list	
	@Override	
	public PageInfo<ModelsProgram> getPageList(Map<String, Object> params) {
		List<ModelsProgram> list = modelsProgramMapper.selectPageList(params);
		PageInfo<ModelsProgram> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<ModelsProgram> getList(Map<String, Object> params) {
		List<ModelsProgram> list = modelsProgramMapper.selectPageList(params);
		return list;
	}

	//通过条件获取	
	@Override	
	public ModelsProgram getByModel(ModelsProgram model) {	
		return null;	
	}

	/**
	 * @Description: 根据项目名称查询数据库中的已存在的项目条数
	 * @Author: wxn
	 * @Date: 2017/12/5 18:05:02
	 * @Param: [name]
	 * @Return java.util.List<com.ccx.models.model.ModelsProgram>
	 */
	@Override
	public List<ModelsProgram> getListByName(String name){
		return modelsProgramMapper.getListByName(name);
	}
	
	//保存对象	
	@Override	
	public int save(ModelsProgram model) {
		if (-1 == model.getId().intValue()) {
			//新增
			model.setId(null);
			Integer fileId = model.getDataFileId();
			ModelsDataFile dataFile = modelsDataFileMapper.selectByPrimaryKey(fileId);
			model.setDataSampleNum(dataFile.getSize());
			model.setCreateTime(new Date());
			model.setDeleteFlag((short) 0);
			startProgramIfNecessary(model);
			modelsProgramMapper.insert(model);
		} else {
			//更新
			startProgramIfNecessary(model);
			modelsProgramMapper.updateByPrimaryKeySelective(model);
		}
		return 0;
	}

	private void startProgramIfNecessary(ModelsProgram program) {
		if (1 == program.getStatus().intValue()) {
			//调用开始
		}
	}
	
	//更新对象	
	@Override	
	public int update(ModelsProgram model) {	
		return modelsProgramMapper.updateByPrimaryKey(model);	
	}	
		
	//删除对象	
	@Override	
	public int deleteById(Integer id) {
		stopProgram(id);
		ModelsProgram program = new ModelsProgram();
		program.setId(id);
		program.setDeleteFlag((short) 1);
		return modelsProgramMapper.updateByPrimaryKeySelective(program);
	}

	private void stopProgram(Integer programId) {
		//调用开始
	}
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}

	@Override
	public List<ModelsArithmetic> selectArithmeticList() {
		return modelsProgramMapper.selectArithmeticList();
	}
}	
