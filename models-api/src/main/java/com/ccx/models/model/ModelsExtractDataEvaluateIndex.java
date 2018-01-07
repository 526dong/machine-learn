package com.ccx.models.model;

import java.math.BigDecimal;
import java.util.Date;

public class ModelsExtractDataEvaluateIndex {
    private Long id;

    private int type;

    private BigDecimal auc;

    private BigDecimal ks;

    private BigDecimal precisions;

    private BigDecimal recall;

    private BigDecimal f1Score;

    private BigDecimal support;

    private BigDecimal gini;

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

    public BigDecimal getAuc() {
        return auc;
    }

    public void setAuc(BigDecimal auc) {
        this.auc = auc;
    }

    public BigDecimal getKs() {
        return ks;
    }

    public void setKs(BigDecimal ks) {
        this.ks = ks;
    }

    public BigDecimal getPrecisions() {
        return precisions;
    }

    public void setPrecisions(BigDecimal precisions) {
        this.precisions = precisions;
    }

    public BigDecimal getRecall() {
        return recall;
    }

    public void setRecall(BigDecimal recall) {
        this.recall = recall;
    }

    public BigDecimal getF1Score() {
        return f1Score;
    }

    public void setF1Score(BigDecimal f1Score) {
        this.f1Score = f1Score;
    }

    public BigDecimal getSupport() {
        return support;
    }

    public void setSupport(BigDecimal support) {
        this.support = support;
    }

    public BigDecimal getGini() {
        return gini;
    }

    public void setGini(BigDecimal gini) {
        this.gini = gini;
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