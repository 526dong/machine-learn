package com.ccx.models.util;
/*
 * @Description:  json返回
 * @Author:       zhaotm 
 * @CreateDate:   2017/9/27 11:32  
 * @Version:      v1.0
 */

public class JsonResult {
    private Integer code;

    private Object data;

    private String msg;

    private JsonResult() {
    }

    public JsonResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public JsonResult(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(200, data);
    }

    public static JsonResult error(String msg) {
        return new JsonResult(500, null, msg);
    }

    public static JsonResult error(Integer code, Object data, String msg) {
        return new JsonResult(code, data, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
