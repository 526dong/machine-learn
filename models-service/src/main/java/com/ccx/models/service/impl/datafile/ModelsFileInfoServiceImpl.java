package com.ccx.models.service.impl.datafile;

import com.ccx.models.api.datafile.ModelsFileInfoApi;
import com.ccx.models.model.datafile.ModelsFileIvTopn;
import com.ccx.models.mapper.datafile.ModelsFileInfoMapper;
import com.ccx.models.model.datafile.ModelsFileInfo;
import com.ccx.models.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @Description 文件变量信息
 * @author Created by xzd on 2017/12/5.
 */
@Service("modelsFileInfoApi")
public class ModelsFileInfoServiceImpl implements ModelsFileInfoApi {
		
	@Autowired	
    private ModelsFileInfoMapper modelsFileInfoMapper;
		
	//主键获取	
	@Override	
	public ModelsFileInfo getById(Integer id) {
		return modelsFileInfoMapper.selectByPrimaryKey(id);	
	}	
		
	//获取无参list	
	@Override	
	public List<ModelsFileInfo> getList() {	
		return null;	
	}	
		
	//获取有参数list	
	@Override	
	public List<ModelsFileInfo> getList(ModelsFileInfo model) {	
		return null;	
	}	
		
	//获取带分页list	
	@Override	
	public List<ModelsFileInfo> getPageList(Page<ModelsFileInfo> page) {
		return null;	
	}	
		
	//通过条件获取	
	@Override	
	public ModelsFileInfo getByModel(ModelsFileInfo model) {	
		return null;	
	}	
	
	//保存对象	
	@Override	
	public int save(ModelsFileInfo model) {	
		return modelsFileInfoMapper.insert(model);	
	}	
	
	//更新对象	
	@Override	
	public int update(ModelsFileInfo model) {	
		return modelsFileInfoMapper.updateByPrimaryKey(model);	
	}

	//修改变量类型：离散/连续
	@Override
	public int updateTypeById(ModelsFileInfo modelsFileInfo) {
		return modelsFileInfoMapper.updateTypeById(modelsFileInfo);
	}

	//删除对象	
	@Override	
	public int deleteById(Integer id) {	
		return modelsFileInfoMapper.deleteByPrimaryKey(id);	
	}	
		
	//其他查询	
	@Override	
	public Map<String, Object> getOther() {	
		return null;	
	}

	@Override
	public List<ModelsFileInfo> selectByFileId(Integer fileId) {
		return modelsFileInfoMapper.selectByFileId(fileId);
	}

	@Override
	public List<ModelsFileInfo> getVarListByFileId(Integer dataFileId) {
		List<ModelsFileInfo> fileInfoList = new ArrayList<>();
		List<ModelsFileInfo> varListByFileId = modelsFileInfoMapper.getVarListByFileId(dataFileId);

		for (ModelsFileInfo fileInfo :varListByFileId) {
			if (fileInfo != null && fileInfo.getIv() != null) {
				BigDecimal iv = fileInfo.getIv();

				if (iv.compareTo(BigDecimal.ZERO) == 0) {
					fileInfo.setIv(BigDecimal.ZERO);
				} else {
					fileInfo.setIv(iv.stripTrailingZeros());
				}

				fileInfoList.add(fileInfo);
			}
		}

		return fileInfoList;
	}

}	
