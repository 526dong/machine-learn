package com.ccx.models.mapper.datafile;

import com.ccx.models.model.datafile.ModelsFileValue;

import java.util.List;

public interface ModelsFileValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelsFileValue record);

    int insertSelective(ModelsFileValue record);

    int batchInsert(List<ModelsFileValue> list);

    ModelsFileValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelsFileValue record);

    int updateByPrimaryKey(ModelsFileValue record);
}