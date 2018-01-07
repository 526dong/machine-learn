package com.ccx.models.model;

import java.math.BigDecimal;
import java.util.Date;

public class ModelsExtractTargetValue {
    private Integer id;

    private String indexName;

    private BigDecimal predictProb;

    private BigDecimal actualProb;


    private Date createDate;

    private Long modelsExtractTestRecordId;


    public ModelsExtractTargetValue() {
    }

    public ModelsExtractTargetValue(Integer id, String indexName, BigDecimal predictProb, Long modelsExtractTestRecordId) {
        this.id = id;
        this.indexName = indexName;
        this.predictProb = predictProb;
        this.modelsExtractTestRecordId = modelsExtractTestRecordId;
    }
    public ModelsExtractTargetValue(Integer id, String indexName, BigDecimal predictProb, Long modelsExtractTestRecordId,BigDecimal actualProb) {
        this.id = id;
        this.indexName = indexName;
        this.predictProb = predictProb;
        this.modelsExtractTestRecordId = modelsExtractTestRecordId;
        this.actualProb=actualProb;
    }

    public BigDecimal getActualProb() {
        return actualProb;
    }

    public void setActualProb(BigDecimal actualProb) {
        this.actualProb = actualProb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }

    public BigDecimal getPredictProb() {
        return predictProb;
    }

    public void setPredictProb(BigDecimal predictProb) {
        this.predictProb = predictProb;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModelsExtractTestRecordId() {
        return modelsExtractTestRecordId;
    }

    public void setModelsExtractTestRecordId(Long modelsExtractTestRecordId) {
        this.modelsExtractTestRecordId = modelsExtractTestRecordId;
    }
}