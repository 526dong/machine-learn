package com.ccx.models.model.vo;

import com.ccx.models.model.Role;
import com.ccx.models.util.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserVo implements Serializable {

	private static final long serialVersionUID = -4705690842473949705L;

	private Long id;

	private String loginName;

	private String name;
	
	 private String password;

	private String roleName;

	private String phone;

	private String email;

	private Date createTime;

	private Integer status;

	private List<Role> list;

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreateTime() throws Exception {
		if (createTime != null) {
			return DateUtils.formatDateStr(createTime);
		} else {
			return null;
		}
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}
	

}
