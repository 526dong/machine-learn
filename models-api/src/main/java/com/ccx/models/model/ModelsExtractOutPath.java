package com.ccx.models.model;

import java.util.Date;

public class ModelsExtractOutPath {
    private Long id;

    private Long modelsExtractTestRecordId;

    private int type;

    private String filePath;

    private Date createTime;

    public ModelsExtractOutPath() {
    }

    public ModelsExtractOutPath( Long modelsExtractTestRecordId, String filePath) {
        this.modelsExtractTestRecordId = modelsExtractTestRecordId;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelsExtractTestRecordId() {
        return modelsExtractTestRecordId;
    }

    public void setModelsExtractTestRecordId(Long modelsExtractTestRecordId) {
        this.modelsExtractTestRecordId = modelsExtractTestRecordId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}