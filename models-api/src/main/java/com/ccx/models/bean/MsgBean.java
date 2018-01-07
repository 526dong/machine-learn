package com.ccx.models.bean;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/12/6
 */
public class MsgBean {
    private String code;
    private String msg;
    private Object data;

    public MsgBean(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
