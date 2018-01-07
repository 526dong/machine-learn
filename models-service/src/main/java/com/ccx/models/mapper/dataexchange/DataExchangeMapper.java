package com.ccx.models.mapper.dataexchange;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ccx.models.bean.Base;
import com.ccx.models.bean.Field;
import com.ccx.models.model.ModelsOutputPath;
import com.ccx.models.model.Module;
import com.ccx.models.model.User;
import com.ccx.models.model.UserNum;
import com.ccx.models.model.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @description User 表数据库控制层接口
 * @author zxr
 * @date 2017 上午11:57:12
 */
public interface DataExchangeMapper {

	/**
	 * 获取传给模型组的数据
	 * @param id
	 * @return
	 */
	Base selectModelData(@Param("id") Integer id);

	/**
	 * 获取传给模型组的字段信息
	 * @param id
	 * @return
	 */
	List<Field> selectModelFieldData(@Param("id") Integer id);

	/**
	 * 通过模型测试数据文件Id获取数据信息
	 * @param id
	 * @return
	 */
	List<Map<String,Object>> getContractValue(Long id);
}
