package com.ccx.models.model;

import java.util.Date;

/**
 * @author zhaotm
 * 项目表
 */
public class ModelsProgram {
    private Integer id;

    private String name;

    private Integer dataFileId;

    private String dataFileName;

    private Integer dataSampleNum;

    private Integer targetId;

    private String targetName;

    private Integer indexId;

    private String indexName;

    private String arithmeticIds;

    private String arithmeticNames;

    private String creator;

    private Date createTime;

    private String createTimeStr;

    private Date finishTime;

    private String castTime;

    private Short status;

    private Integer modelConf;

    private Short deleteFlag;

    public ModelsProgram() {
    }

    public ModelsProgram(Integer id, Date finishTime, String castTime, Short status) {
        this.id = id;
        this.finishTime = finishTime;
        this.castTime = castTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDataFileId() {
        return dataFileId;
    }

    public void setDataFileId(Integer dataFileId) {
        this.dataFileId = dataFileId;
    }

    public String getDataFileName() {
        return dataFileName;
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName == null ? null : dataFileName.trim();
    }

    public Integer getDataSampleNum() {
        return dataSampleNum;
    }

    public void setDataSampleNum(Integer dataSampleNum) {
        this.dataSampleNum = dataSampleNum;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName == null ? null : targetName.trim();
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }

    public String getArithmeticIds() {
        return arithmeticIds;
    }

    public void setArithmeticIds(String arithmeticIds) {
        this.arithmeticIds = arithmeticIds == null ? null : arithmeticIds.trim();
    }

    public String getArithmeticNames() {
        return arithmeticNames;
    }

    public void setArithmeticNames(String arithmeticNames) {
        this.arithmeticNames = arithmeticNames == null ? null : arithmeticNames.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime == null ? null : castTime.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getModelConf() {
        return modelConf;
    }

    public void setModelConf(Integer modelConf) {
        this.modelConf = modelConf;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}