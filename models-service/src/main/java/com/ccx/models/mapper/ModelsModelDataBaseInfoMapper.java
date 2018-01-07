package com.ccx.models.mapper;

import com.ccx.models.model.ModelsModelDataBaseInfo;

public interface ModelsModelDataBaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 通过数据文件删除
     */
    int deleteByDataFileId(Integer dataFileId);

    int insert(ModelsModelDataBaseInfo record);

    int insertSelective(ModelsModelDataBaseInfo record);

    ModelsModelDataBaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ModelsModelDataBaseInfo record);

    int updateByPrimaryKey(ModelsModelDataBaseInfo record);

    ModelsModelDataBaseInfo selectJoinDataFile(Long id);
}