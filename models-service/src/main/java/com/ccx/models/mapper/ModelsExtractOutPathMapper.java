package com.ccx.models.mapper;

import com.ccx.models.model.ModelsExtractOutPath;

public interface ModelsExtractOutPathMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ModelsExtractOutPath record);

    int insertSelective(ModelsExtractOutPath record);

    ModelsExtractOutPath selectByPrimaryKey(Long id);

    ModelsExtractOutPath selectByModelId(Long modelId);

    int updateByPrimaryKeySelective(ModelsExtractOutPath record);

    int updateByPrimaryKey(ModelsExtractOutPath record);
}