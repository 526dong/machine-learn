package com.ccx.models.model;

public class ModelsOutputPath {
    private Integer id;

    private Long programId;

    private Long arithmeticId;

    private String logPath;

    private String predictResPath;

    private String analysisReport;

    private String requstId;

    private String modelPath;

    public ModelsOutputPath() {
    }

    public ModelsOutputPath(Integer id, Long programId, Long arithmeticId, String logPath, String predictResPath, String analysisReport, String requstId, String modelPath) {
        this.id = id;
        this.programId = programId;
        this.arithmeticId = arithmeticId;
        this.logPath = logPath;
        this.predictResPath = predictResPath;
        this.analysisReport = analysisReport;
        this.requstId = requstId;
        this.modelPath = modelPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath == null ? null : logPath.trim();
    }

    public String getPredictResPath() {
        return predictResPath;
    }

    public void setPredictResPath(String predictResPath) {
        this.predictResPath = predictResPath == null ? null : predictResPath.trim();
    }

    public String getAnalysisReport() {
        return analysisReport;
    }

    public void setAnalysisReport(String analysisReport) {
        this.analysisReport = analysisReport == null ? null : analysisReport.trim();
    }

    public String getRequstId() {
        return requstId;
    }

    public void setRequstId(String requstId) {
        this.requstId = requstId == null ? null : requstId.trim();
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath == null ? null : modelPath.trim();
    }
}