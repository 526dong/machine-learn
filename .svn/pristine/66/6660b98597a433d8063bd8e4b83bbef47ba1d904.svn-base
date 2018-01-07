package com.ccx.models.mapper;

import com.ccx.models.model.ModelsVarNumricCount;

import java.util.List;

public interface ModelsVarNumricCountMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 通过数据文件删除
     */
    int deleteByDataFileId(Integer dataFileId);

    int insert(ModelsVarNumricCount record);

    int insertSelective(ModelsVarNumricCount record);

    ModelsVarNumricCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelsVarNumricCount record);

    int updateByPrimaryKey(ModelsVarNumricCount record);

    List<ModelsVarNumricCount> selectListByModel(ModelsVarNumricCount model);
}