package com.ccx.models.mapper;

import com.ccx.models.model.ModelsArithmetic;
import com.ccx.models.model.ModelsProgram;

import java.util.List;
import java.util.Map;

public interface ModelsProgramMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelsProgram record);

    int insertSelective(ModelsProgram record);

    ModelsProgram selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelsProgram record);

    int updateByPrimaryKey(ModelsProgram record);

    List<ModelsProgram> selectPageList(Map<String, Object> params);

    List<ModelsArithmetic> selectArithmeticList();

    /**
     * @Description: 根据项目名称查询数据库中的已存在的项目条数
     * @Author: wxn
     * @Date: 2017/12/5 18:05:02
     * @Param: [name]
     * @Return java.util.List<com.ccx.models.model.ModelsProgram>
     */
    List<ModelsProgram> getListByName(String name);
}