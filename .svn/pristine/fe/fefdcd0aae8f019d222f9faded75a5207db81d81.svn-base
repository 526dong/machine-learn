package com.ccx.models.bean;

import com.ccx.models.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:模型输出bean
 * @author:lilong
 * @Date: 2017/11/21
 */
public class ProgramOutPutBean {

    //reqId
    private String reqId;
    //请求类型,0,变量统计、iv值、重要变量相关数据  1、模型输出相关数据
    private Integer type;
    //项目基础信息
    private Base base;
    //当前用户模型文件存放路径
    private String userPath;

    //项目字段信息
    private List<Field> fields;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public static void main(String[] args) {
        ProgramOutPutBean programOutPutBean=new ProgramOutPutBean();
        programOutPutBean.setType(1);
        Base base = new Base(1,"现金贷模型","d://xx.csv","csv","utf-8",",","null",10000,"name","cid", "Xgbost");
        List<Field> fields=new ArrayList<>();
        fields.add(new Field("name",1));
        fields.add(new Field("age",0));
        fields.add(new Field("cid",1));
        fields.add(new Field("bankCardId",1));
        programOutPutBean.setBase(base);
        programOutPutBean.setFields(fields);
        System.out.println(JsonUtils.toJson(programOutPutBean));
    }
}
