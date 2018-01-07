package com.ccx.models.mapper;

import com.ccx.models.model.ModelsVarCategoryCount;

import java.util.List;

public interface ModelsVarCategoryCountMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 通过数据文件删除
     */
    int deleteByDataFileId(Integer dataFileId);

    int insert(ModelsVarCategoryCount record);

    int insertSelective(ModelsVarCategoryCount record);

    ModelsVarCategoryCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelsVarCategoryCount record);

    int updateByPrimaryKey(ModelsVarCategoryCount record);

    List<ModelsVarCategoryCount> selectListByModel(ModelsVarCategoryCount model);
}