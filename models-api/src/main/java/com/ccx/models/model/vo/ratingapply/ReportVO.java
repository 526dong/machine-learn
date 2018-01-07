package com.ccx.models.model.vo.ratingapply;

import java.io.Serializable;

/**
 * 
 * @author sgs
 * @date 2017/6/23
 */
public class ReportVO implements Serializable{
	private static final long serialVersionUID = -1685987188826876445L;

	private Integer id;

	
	/*报表时间:主要指年份*/
	private String reportTime;
	
	/*口径*/
	private String cal;
	
	/*类型*/
	private String type;
	
	/*周期*/
	private String cycle;
	
	/*币种*/
	private String currency;

	/*是否审计：0-是，1-否*/
	private Integer audit;
	
	/*创建时间*/
	private String createDate;
	
	//报表概述
	private String reportOutline;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getReportOutline() {
		return reportOutline;
	}

	public void setReportOutline(String reportOutline) {
		this.reportOutline = reportOutline;
	}
	
	

}
