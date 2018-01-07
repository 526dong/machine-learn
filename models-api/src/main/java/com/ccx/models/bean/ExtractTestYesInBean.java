package com.ccx.models.bean;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/12/12
 */
public class ExtractTestYesInBean {

    private String code;

    private String reqId;

    private String predictResPath;

    private String reqTime;

    private Map<String,Object> modeldataInfo;

    private Map<String,Object> modelreport;

    private List<Map<String,Object>> pvalueReport;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getPredictResPath() {
        return predictResPath;
    }

    public void setPredictResPath(String predictResPath) {
        this.predictResPath = predictResPath;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public Map<String, Object> getModeldataInfo() {
        return modeldataInfo;
    }

    public void setModeldataInfo(Map<String, Object> modeldataInfo) {
        this.modeldataInfo = modeldataInfo;
    }

    public Map<String, Object> getModelreport() {
        return modelreport;
    }

    public void setModelreport(Map<String, Object> modelreport) {
        this.modelreport = modelreport;
    }

    public List<Map<String, Object>> getPvalueReport() {
        return pvalueReport;
    }

    public void setPvalueReport(List<Map<String, Object>> pvalueReport) {
        this.pvalueReport = pvalueReport;
    }
}
