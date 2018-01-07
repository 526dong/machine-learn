package com.ccx.models.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 公司表
 * 
 * @author zxr
 *
 */
@TableName("abs_company")
public class Company {

	/** 企业id */
	private Integer id;

	/** 工商注册号 */
	private String code;

	/** 公司名称 */
	private String companyName;

	/** 公司电话 */
	private String telephone;

	/** 公司邮箱 */
	private String email;

	/** 公司网址 */
	private String url;

	/** 公司地址 */
	private String address;

	/** 法人姓名 */
	private String legalPerson;

	/** 公司类型 */
	private String type;

	/** 负责人 */
	private String principal;

	/** 负责人电话 */
	private String principalPhone;

	/** 社会信用代号 */
	private String creditCode;

	/** 创建日期 */
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
