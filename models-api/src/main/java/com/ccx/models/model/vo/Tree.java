package com.ccx.models.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @description 自定义TreeVo
 * @author zxr
 * @date 2017 下午1:49:52
 */
public class Tree implements Serializable {

    private static final long serialVersionUID = 980682543891282923L;
    
    private Long id;
    
    /** 资源名称 */
    private String text;
    
    /** 开启状态：open,closed */
    private String state = "open";
    
    private boolean checked = false;
    
    /** 资源地址 */
    private Object attributes;
    
    /** 子列表： null不输出 */
    @JsonInclude(Include.NON_NULL)
    private List<Tree> children;
    
    /** 图标地址 */
    private String iconCls;
    
    /** 父节点 */
    private Long pid;
    
    /** 打开方式：ajax,iframe */
    private String openMode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public void setState(Integer opened) {
        this.state = (null != opened && opened == 1) ? "open" : "closed";
    }
    
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getOpenMode() {
        return openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

}
