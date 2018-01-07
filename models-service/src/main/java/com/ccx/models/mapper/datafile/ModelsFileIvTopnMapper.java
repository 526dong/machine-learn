package com.ccx.models.mapper.datafile;


import com.ccx.models.model.datafile.ModelsFileIvTopn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelsFileIvTopnMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 通过数据文件删除
     */
    int deleteByDataFileId(Integer dataFileId);

    int insert(ModelsFileIvTopn record);

    int insertSelective(ModelsFileIvTopn record);

    ModelsFileIvTopn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ModelsFileIvTopn record);

    int updateByPrimaryKey(ModelsFileIvTopn record);


    List<ModelsFileIvTopn> getIvListByFileIdAndVarName(@Param("dataFileId") Integer dataFileId, @Param("varName") String varName);
}