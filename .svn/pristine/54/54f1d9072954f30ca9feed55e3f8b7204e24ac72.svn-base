package com.ccx.models.service.impl.datafile;

import com.ccx.models.mapper.datafile.GetFileValueMapper;
import com.ccx.models.model.datafile.ModelsDataFile;
import com.ccx.models.model.datafile.ModelsFileInfo;
import com.ccx.models.model.datafile.ModelsFileRowValue;
import com.ccx.models.model.datafile.ModelsFileValue;
import com.ccx.models.util.JsonUtils;

import java.util.*;

/**
  * @author Created by xzd on 2017/11/22.
  * @Description 公共方法获取文件数据
 */
public abstract class GetFileValue<K,V> {
    private GetFileValueMapper fileValueMapper;

    //dataFileId
    private Integer dataFileId;

    //获取文件数据
    private CommonFileValue.DataFile dataFile = new CommonFileValue.DataFile();

    //K模型存储
    private K k;

    //V模型存储
    private V v;

    /**
     * 构造函数行初始化
     * @param fileValueMapper
     * @param dataFileId
     * @param isRow
     */
    public GetFileValue(GetFileValueMapper fileValueMapper, Integer dataFileId, boolean isRow) {
        this.fileValueMapper = fileValueMapper;
        this.dataFileId = dataFileId;

        if (isRow) {
            rowInit();
        } else {
            init();
        }
    }

    //初始化方法
    private void init(){
        //文件变量
        Map<Integer, List<CommonFileValue.FileValue>> dataFileValue = new HashMap<>();
        //文件变量-成行的文件变量值
        Map<Integer, List<CommonFileValue.FileValue>> dataRowFileValue = new HashMap<>();

        //文件信息
        ModelsDataFile modelsDataFile = fileValueMapper.findById(dataFileId);
        dataFile.setDataFile(modelsDataFile);

        //文件中变量信息
        List<CommonFileValue.FileInfo> fileInfoList = new ArrayList<>();
        List<ModelsFileInfo> fileInfoByDataFileId = fileValueMapper.findFileInfoByDataFileId(dataFileId);

        //变量数量
        Integer columnNumber = fileInfoByDataFileId.size();

        //文件中变量值
        List<ModelsFileValue> fileValueByDataFileId = fileValueMapper.findFileValueByDataFileId(dataFileId, columnNumber*10);

        //将fileValueByDataFileId放入Map容器中
        dealWithFileValues(fileValueByDataFileId, dataFileValue, dataRowFileValue);

        for(ModelsFileInfo myFileInfo:fileInfoByDataFileId){
            CommonFileValue.FileInfo fileInfo = new CommonFileValue.FileInfo();
            fileInfo.setFileInfo(myFileInfo);
            //将文件变量的值放入变量中
            fileInfo.setFileValueList(dataFileValue.get(myFileInfo.getId()));
            fileInfoList.add(fileInfo);
        }

        //处理行变量值
        List<CommonFileValue.RowFileInfo> rowFileInfoList = dealWithRowFileValues(dataRowFileValue);

        dataFile.setFileInfoList(fileInfoList);
        dataFile.setRowFileInfoList(rowFileInfoList);
    }

    /**
     * 行初始化
     */
    private void rowInit(){
        //文件变量
        Map<Integer, List<CommonFileValue.FileValue>> dataFileValue = new HashMap<>();
        //文件变量-成行的文件变量值
        Map<Integer, List<CommonFileValue.FileValue>> dataRowFileValue = new HashMap<>();

        //文件信息
        ModelsDataFile modelsDataFile = fileValueMapper.findById(dataFileId);
        dataFile.setDataFile(modelsDataFile);

        //文件中变量信息
        List<CommonFileValue.FileInfo> fileInfoList = new ArrayList<>();
        List<ModelsFileInfo> fileInfoByDataFileId = fileValueMapper.findFileInfoByDataFileId(dataFileId);

        //文件中行信息
        List<ModelsFileRowValue> fileRowValueList = fileValueMapper.findFileRowValueByDataFileId(dataFileId);

        dealWithFileRowValueList(fileRowValueList, fileInfoByDataFileId, dataRowFileValue);

        for(ModelsFileInfo myFileInfo:fileInfoByDataFileId){
            CommonFileValue.FileInfo fileInfo = new CommonFileValue.FileInfo();
            fileInfo.setFileInfo(myFileInfo);
            //将文件变量的值放入变量中
            fileInfo.setFileValueList(dataFileValue.get(myFileInfo.getId()));
            fileInfoList.add(fileInfo);
        }

        //处理行变量值
        List<CommonFileValue.RowFileInfo> rowFileInfoList = dealWithRowFileValues(dataRowFileValue);

        dataFile.setFileInfoList(fileInfoList);
        dataFile.setRowFileInfoList(rowFileInfoList);
    }

    /**
     * 处理文件变量存储值
     * @param fileRowValueList
     * @return
     */
    private void dealWithFileRowValueList(List<ModelsFileRowValue> fileRowValueList, List<ModelsFileInfo> fileInfoByDataFileId,
                Map<Integer, List<CommonFileValue.FileValue>> dataRowFileValue){
        //处理行数据
        for (int i = 0; i < fileRowValueList.size(); i++){
            ModelsFileRowValue modelsFileRowValue = fileRowValueList.get(i);
            String fileRowValue = modelsFileRowValue.getRowValue();

            if (fileRowValue == null) {
                throw new RuntimeException("数据存储有问题！");
            }

            String rowValue = fileRowValue.substring(1, fileRowValue.length()-1).replace("\"","");

            //行号-变量值分类封包
            List<CommonFileValue.FileValue> rowList = null;

            //空处理
            if(dataRowFileValue.get(i) == null){
                rowList = new ArrayList<>();
                dataRowFileValue.put(i, rowList);
            }
            rowList = dataRowFileValue.get(i);

            if (rowValue.contains(",")) {
                String[] split = rowValue.split(",");

                if (split.length == fileInfoByDataFileId.size()) {
                    for (int j = 0; j < split.length; j++) {
                        ModelsFileValue fileValue = new ModelsFileValue();

                        fileValue.setValue(split[j]);

                        CommonFileValue.FileValue myRowFileValue = new CommonFileValue.FileValue();
                        myRowFileValue.setFileValue(fileValue);

                        rowList.add(myRowFileValue);
                    }
                } else {
                    throw new RuntimeException("数据存储有问题！");
                }
            } else {
                ModelsFileValue fileValue = new ModelsFileValue();

                fileValue.setValue(rowValue);

                CommonFileValue.FileValue myRowFileValue = new CommonFileValue.FileValue();
                myRowFileValue.setFileValue(fileValue);

                rowList.add(myRowFileValue);
            }
        }
    }

    /**
     * 处理文件变量存储值
     * @param fileValueList
     * @return
     */
    private void dealWithFileValues(List<ModelsFileValue> fileValueList, Map<Integer, List<CommonFileValue.FileValue>> dataFileValue,
                Map<Integer, List<CommonFileValue.FileValue>> dataRowFileValue){
        for (ModelsFileValue fileVal:fileValueList){
            if (fileVal != null && fileVal.getFileInfoId() != null) {
                //变量-变量值分类封包
                List<CommonFileValue.FileValue> list = null;
                //文件变量id
                Integer fileInfoId = Integer.valueOf(fileVal.getFileInfoId().toString());

                //空处理
                if(dataFileValue.get(fileInfoId) == null){
                    list = new ArrayList<>();
                    dataFileValue.put(fileInfoId, list);
                }
                list = dataFileValue.get(fileInfoId);

                CommonFileValue.FileValue myFileValue = new CommonFileValue.FileValue();
                myFileValue.setFileValue(fileVal);

                list.add(myFileValue);

                //行号-变量值分类封包
                List<CommonFileValue.FileValue> rowList = null;
                //行号
                Integer rowNumber = fileVal.getRowNumber();

                //空处理
                if(dataRowFileValue.get(rowNumber) == null){
                    rowList = new ArrayList<>();
                    dataRowFileValue.put(rowNumber, rowList);
                }
                rowList = dataRowFileValue.get(rowNumber);

                CommonFileValue.FileValue myRowFileValue = new CommonFileValue.FileValue();
                myRowFileValue.setFileValue(fileVal);

                rowList.add(myRowFileValue);
            }
        }
    }

    /**
     * 处理文件变量行变量值
     * @return
     */
    private List<CommonFileValue.RowFileInfo> dealWithRowFileValues(Map<Integer, List<CommonFileValue.FileValue>> dataRowFileValue){
        //行集合
        List<CommonFileValue.RowFileInfo> rowFileInfoList = new ArrayList<>();

        Iterator it = dataRowFileValue.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Integer, List<CommonFileValue.FileValue>> rowMap = (Map.Entry<Integer, List<CommonFileValue.FileValue>>) it.next();

            Integer rowNumber = rowMap.getKey();
            List<CommonFileValue.FileValue> rowValue = rowMap.getValue();

            CommonFileValue.RowFileInfo rowFileInfo = new CommonFileValue.RowFileInfo();
            rowFileInfo.setRowNumber(rowNumber);
            //将文件变量的值放入行中
            rowFileInfo.setFileValueList(rowValue);

            rowFileInfoList.add(rowFileInfo);
        }

        return rowFileInfoList;
    }

    public K getK() {
        if(k == null) {
            initK();
        }
        return k;
    }

    public V getV() {
        if(v == null) {
            initV();
        }
        return v;
    }

    public abstract void initK();

    public abstract void initV();

    public Integer getDataFileId() {
        return dataFileId;
    }

    public void setDataFileId(Integer dataFileId) {
        this.dataFileId = dataFileId;
    }

    public CommonFileValue.DataFile getDataFile() {
        return dataFile;
    }

    public void setDataFile(CommonFileValue.DataFile dataFile) {
        this.dataFile = dataFile;
    }

    public void setK(K k) {
        this.k = k;
    }

    public void setV(V v) {
        this.v = v;
    }
}
