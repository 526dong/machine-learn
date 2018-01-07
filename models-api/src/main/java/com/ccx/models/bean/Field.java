package com.ccx.models.bean;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/11/22
 */

import java.util.List;

/**
 * 字段信息
 */
public  class Field{
    //字段名称
    private String fileName;

    //字段类型 0-离散型变量，1-连续型变量
    private Integer fieldType;

    public Field() {
    }

    public Field(String fileName, Integer fieldType) {
        this.fileName = fileName;
        this.fieldType = fieldType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }
}

