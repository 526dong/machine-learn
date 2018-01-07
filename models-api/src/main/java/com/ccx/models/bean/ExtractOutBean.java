package com.ccx.models.bean;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/12/12
 */
public class ExtractOutBean {

    private  String reqId;

    private String modelreqId;

    private String modelPath;

    private Base base;

    private Integer type;


    public ExtractOutBean() {
    }

    public ExtractOutBean(String reqId, String modelreqId, String modelPath, Base base,Integer type) {
        this.reqId = reqId;
        this.modelreqId = modelreqId;
        this.modelPath = modelPath;
        this.base = base;
        this.type=type;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getModelreqId() {
        return modelreqId;
    }

    public void setModelreqId(String modelreqId) {
        this.modelreqId = modelreqId;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Base getBase() {
        return base;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
