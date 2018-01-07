package com.ccx.models.model.vo.enterprise;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author sgs
 * @date 2017/6/21
 */
public class SelectEnterpriseVO implements Serializable{
	private static final long serialVersionUID = -1685987188826876445L;

	private Integer id;
	
	/*企业名称*/
	private String name;
	
	/*同意信用代码*/
	private String creditCode;

	/*报表时间:主要指年份*/
	private Date reportTime;
	
	/*口径*/
	private String cal;
	
	/*类型*/
	private Integer type;
	
	/*周期*/
	private String cycle;

	/*是否审计：0-是，1-否*/
	private Integer isAudit;
	
	/*币种*/
	private String currency;
	
	/*创建时间*/
	private Date createDate;
	
	/*是否评级  2已评级*/
	private Integer approvalStatus;
	
	/*企业id*/
	private Integer enterpriseId;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
}
