package com.ccx.models.model;

import java.math.BigDecimal;
import java.util.Date;

public class ModelsExtractDataAnalyInfo {
    private Long id;

    private int type;

    private BigDecimal sampleNum;

    private BigDecimal sumDimensionality;

    private BigDecimal intoDimensionality;

    private BigDecimal importanceVar;

    private BigDecimal plusMinusRate;

    private BigDecimal plusRate;

    private Long modelsExtractTestRecordId;

    private String creater;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getSampleNum() {
        return sampleNum;
    }

    public void setSampleNum(BigDecimal sampleNum) {
        this.sampleNum = sampleNum;
    }

    public BigDecimal getSumDimensionality() {
        return sumDimensionality;
    }

    public void setSumDimensionality(BigDecimal sumDimensionality) {
        this.sumDimensionality = sumDimensionality;
    }

    public BigDecimal getIntoDimensionality() {
        return intoDimensionality;
    }

    public void setIntoDimensionality(BigDecimal intoDimensionality) {
        this.intoDimensionality = intoDimensionality;
    }

    public BigDecimal getImportanceVar() {
        return importanceVar;
    }

    public void setImportanceVar(BigDecimal importanceVar) {
        this.importanceVar = importanceVar;
    }

    public BigDecimal getPlusMinusRate() {
        return plusMinusRate;
    }

    public void setPlusMinusRate(BigDecimal plusMinusRate) {
        this.plusMinusRate = plusMinusRate;
    }

    public BigDecimal getPlusRate() {
        return plusRate;
    }

    public void setPlusRate(BigDecimal plusRate) {
        this.plusRate = plusRate;
    }

    public Long getModelsExtractTestRecordId() {
        return modelsExtractTestRecordId;
    }

    public void setModelsExtractTestRecordId(Long modelsExtractTestRecordId) {
        this.modelsExtractTestRecordId = modelsExtractTestRecordId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}