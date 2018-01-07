package com.ccx.models.mapper.datafile;

import com.ccx.models.model.datafile.ModelsFileRowValue;
import com.ccx.models.model.datafile.ModelsFileValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelsFileRowValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModelsFileRowValue record);

    int batchInsert(List<ModelsFileRowValue> list);

    ModelsFileRowValue selectByPrimaryKey(Integer id);

    /**
     * 通过数据文件id查询每行数据
     * @param dataFileId
     * @return
     */
    List<ModelsFileRowValue> selectByDataFileId(@Param("dataFileId") Integer dataFileId);

    int updateByPrimaryKey(ModelsFileRowValue record);
}