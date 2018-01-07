package com.ccx.models.mapper.datafile;


import com.ccx.models.model.datafile.ModelsFileIvTopn;
import com.ccx.models.model.datafile.ModelsFileInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelsFileInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelsFileInfo record);

    int batchInsert(List<ModelsFileInfo> list);

    ModelsFileInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ModelsFileInfo record);

    //修改变量类型：离散/连续
    int updateTypeById(ModelsFileInfo modelsFileInfo);

    List<ModelsFileInfo> selectByFileId(Integer fileId);

    List<ModelsFileInfo> getVarListByFileId(Integer dataFileId);
}