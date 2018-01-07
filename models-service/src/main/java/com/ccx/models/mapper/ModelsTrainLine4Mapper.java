package com.ccx.models.mapper;

import com.ccx.models.model.ModelsTrainLine4;

import java.util.List;

public interface ModelsTrainLine4Mapper extends BaseMapper<List<ModelsTrainLine4>>{
    int deleteByPrimaryKey(Long id);

    int insert(List<ModelsTrainLine4> list);

    int insertSelective(ModelsTrainLine4 record);

    ModelsTrainLine4 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ModelsTrainLine4 record);

    int updateByPrimaryKey(ModelsTrainLine4 record);
}