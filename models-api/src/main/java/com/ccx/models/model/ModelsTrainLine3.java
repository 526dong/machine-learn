package com.ccx.models.model;

import java.math.BigDecimal;
import java.util.Date;

public class ModelsTrainLine3 {
    private Long id;

    private Long programId;

    private Long arithmeticId;

    private BigDecimal abscissa;

    private BigDecimal tpr;

    private BigDecimal ks;

    private BigDecimal fpr;

    private String creater;

    private Date createTime;

    public ModelsTrainLine3() {
    }

    public ModelsTrainLine3(Long programId, Long arithmeticId, BigDecimal abscissa, BigDecimal tpr, BigDecimal ks, BigDecimal fpr, String creater, Date createTime) {
        this.programId = programId;
        this.arithmeticId = arithmeticId;
        this.abscissa = abscissa;
        this.tpr = tpr;
        this.ks = ks;
        this.fpr = fpr;
        this.creater = creater;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getArithmeticId() {
        return arithmeticId;
    }

    public void setArithmeticId(Long arithmeticId) {
        this.arithmeticId = arithmeticId;
    }

    public BigDecimal getAbscissa() {
        return abscissa;
    }

    public void setAbscissa(BigDecimal abscissa) {
        this.abscissa = abscissa;
    }

    public BigDecimal getTpr() {
        return tpr;
    }

    public void setTpr(BigDecimal tpr) {
        this.tpr = tpr;
    }

    public BigDecimal getKs() {
        return ks;
    }

    public void setKs(BigDecimal ks) {
        this.ks = ks;
    }

    public BigDecimal getFpr() {
        return fpr;
    }

    public void setFpr(BigDecimal fpr) {
        this.fpr = fpr;
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
}