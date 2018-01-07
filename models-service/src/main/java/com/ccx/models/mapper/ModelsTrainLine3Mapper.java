package com.ccx.models.mapper;

import com.ccx.models.model.ModelsTrainLine3;

import java.util.List;

public interface ModelsTrainLine3Mapper extends BaseMapper<List<ModelsTrainLine3>>{
    int deleteByPrimaryKey(Long id);

    int insert(List<ModelsTrainLine3> list);

    int insertSelective(ModelsTrainLine3 record);

    ModelsTrainLine3 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ModelsTrainLine3 record);

    int updateByPrimaryKey(ModelsTrainLine3 record);
}