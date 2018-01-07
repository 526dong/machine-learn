package com.ccx.models.model;

import com.ccx.models.util.DateUtils;

import java.util.Date;

public class ModelsExtractTestRecord {
    private Long id;

    private Long modelsExtractInfoId;

    private String fileName;

    private String filePath;

    private Integer fileSize;

    private String fileType;

    private String description;

    private Integer sampleNum;

    private Integer testType;

    private Integer delFlag;

    private String creater;

    private Date createTime;

    private String titleValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelsExtractInfoId() {
        return modelsExtractInfoId;
    }

    public void setModelsExtractInfoId(Long modelsExtractInfoId) {
        this.modelsExtractInfoId = modelsExtractInfoId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSampleNum() {
        return sampleNum;
    }

    public void setSampleNum(Integer sampleNum) {
        this.sampleNum = sampleNum;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue == null ? null : titleValue.trim();
    }

    public String getCreateTimeStr() throws Exception {
        if (createTime != null) {
            return DateUtils.getDateFromString(createTime);
        } else {
            return null;
        }
    }
}