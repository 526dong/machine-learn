package com.ccx.models.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: PermissionTreeBean 
* @Description: 权限树形结构实体(这里用一句话描述这个类的作用) 
* @author wuXiangNan  
* @date 2016年7月29日 上午10:04:25 
*
 */
public class PermissionTreeBean {
	
	/**
	 * 节点ID
	 */
	private String id;  
    
    /**
     * 节点文本
     */
    private String text;  
    
    /**
     * 是否被选中
     */
    private boolean  checked;
      
    /**
     * 若干子节点
     */
    private List<PermissionTreeBean> children = new ArrayList<PermissionTreeBean>();
      
    /**
     * 节点状态 ：open 或者 closed  默认open
     */
    private String state;
    
    /**
     * 父级ID
     */
    private String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<PermissionTreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<PermissionTreeBean> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}  
    
    
    
}
