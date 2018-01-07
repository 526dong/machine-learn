package com.ccx.models.mapper;

import com.ccx.models.model.ModelsExtractInfo;

public interface ModelsExtractInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ModelsExtractInfo record);

    int insertSelective(ModelsExtractInfo record);

    ModelsExtractInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ModelsExtractInfo record);

    int updateByPrimaryKey(ModelsExtractInfo record);
}