package com.ccx.models.mapper;

import com.ccx.models.model.ModelsModelDataAnalyInfo;

public interface ModelsModelDataAnalyInfoMapper extends BaseMapper<ModelsModelDataAnalyInfo>{
    int deleteByPrimaryKey(Long id);

    int insert(ModelsModelDataAnalyInfo record);

    int insertSelective(ModelsModelDataAnalyInfo record);

    ModelsModelDataAnalyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ModelsModelDataAnalyInfo record);

    int updateByPrimaryKey(ModelsModelDataAnalyInfo record);
}