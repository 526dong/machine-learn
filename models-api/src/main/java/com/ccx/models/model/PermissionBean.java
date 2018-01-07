package com.ccx.models.model;

import java.io.Serializable;

public class PermissionBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -757111854498620793L;
	
	private long id;//数据库id
	private String myselfId;//唯一标识
	private String parentId;//父级资源项ID
	private String permissionName;//权限名称
	private int type;//资源类型
	private int level;//层级
	private String pathUrl;//资源路径
	private String iconUrl;//图标路径
	private int sequenceNum;//排序
	private int permissionState;//状态
	private String description;//资源项描述
	private String openMode;//打开方式
	private String institutionId;//公司id
	private String createTime;//创建时间
	private String creater;//创建人
	private String modifier;//修改人
	private String modifyTime;//修改时间

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMyselfId() {
		return myselfId;
	}

	public void setMyselfId(String myselfId) {
		this.myselfId = myselfId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPathUrl() {
		return pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public int getPermissionState() {
		return permissionState;
	}

	public void setPermissionState(int permissionState) {
		this.permissionState = permissionState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}
