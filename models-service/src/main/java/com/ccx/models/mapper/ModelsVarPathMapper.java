package com.ccx.models.mapper;

import com.ccx.models.model.ModelsVarPath;

public interface ModelsVarPathMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelsVarPath record);

    int insertSelective(ModelsVarPath record);

    ModelsVarPath selectByPrimaryKey(Integer id);

    /**
     * 通过数据文件id查询html_url
     */
    String selectHtmlUrlByDataFileId(Integer dataFileId);

    /**
     * 通过数据文件id查询iv_url
     */
    String selectIVUrlByDataFileId(Integer dataFileId);

    int updateByPrimaryKeySelective(ModelsVarPath record);

    int updateByPrimaryKey(ModelsVarPath record);
}