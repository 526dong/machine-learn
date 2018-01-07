package com.ccx.models.mapper;

import com.ccx.models.model.ModelsOutputPath;
import org.apache.ibatis.annotations.Param;

public interface ModelsOutputPathMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelsOutputPath record);

    int insertSelective(ModelsOutputPath record);

    ModelsOutputPath selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModelsOutputPath record);

    int updateByPrimaryKey(ModelsOutputPath record);

    /**
     * 获取模型输出信息
     * @param programId
     * @param arithmeticId
     * @return
     */
    ModelsOutputPath getModelOutputPath(@Param("programId") Long programId, @Param("arithmeticId") Long arithmeticId);

}