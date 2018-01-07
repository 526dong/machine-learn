package com.ccx.models.mapper;

import com.ccx.models.model.ModelsExtractTargetValue;

import java.util.List;

public interface ModelsExtractTargetValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(List<ModelsExtractTargetValue> list);

    int insertSelective(ModelsExtractTargetValue record);

    ModelsExtractTargetValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelsExtractTargetValue record);

    int updateByPrimaryKey(ModelsExtractTargetValue record);
}