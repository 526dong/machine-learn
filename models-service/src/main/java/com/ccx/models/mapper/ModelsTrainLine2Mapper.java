package com.ccx.models.mapper;

import com.ccx.models.model.ModelsTrainLine1;
import com.ccx.models.model.ModelsTrainLine2;

import java.util.List;

public interface ModelsTrainLine2Mapper  extends BaseMapper<List<ModelsTrainLine2>>{
    int deleteByPrimaryKey(Long id);

    int insert(List<ModelsTrainLine2> list);

    int insertSelective(ModelsTrainLine2 record);

    ModelsTrainLine2 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ModelsTrainLine2 record);

    int updateByPrimaryKey(ModelsTrainLine2 record);
}