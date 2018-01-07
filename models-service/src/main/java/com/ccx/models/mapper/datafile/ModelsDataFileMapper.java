package com.ccx.models.mapper.datafile;


import com.ccx.models.model.datafile.ModelsDataFile;

import java.util.List;
import java.util.Map;

public interface ModelsDataFileMapper {
    int insert(ModelsDataFile record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(ModelsDataFile record);

    ModelsDataFile selectByPrimaryKey(Integer id);

    //校验文件名称唯一性
    int validateName(String name);

    List<ModelsDataFile> getList();

    List<ModelsDataFile> getListByParams(Map<String, Object> params);

    List<ModelsDataFile> selectPageList(Map<String, Object> paramMap);
}