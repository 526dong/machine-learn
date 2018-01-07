package com.ccx.models.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import com.ccx.models.util.DateUtils;
import com.ccx.models.util.UsedUtil;


public class Module implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer institutionId;

    private String name;

    private String myselfId;

    private Date startDate;

    private Date endDate;

    private String description;
    
    private String allor;

    private Date allorTime;

    private Integer state;

    private Integer isDel;

    private String creater;

    private Date createTime;
    
    private String iconUrl;
    
    

    public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMyselfId() {
        return myselfId;
    }

    public void setMyselfId(String myselfId) {
        this.myselfId = myselfId == null ? null : myselfId.trim();
    }

    public String getStartDate() throws ParseException {
        if (UsedUtil.isNotNull(startDate)) {
    		return DateUtils.formatDateStr(startDate);
		}else{
			return null;
		}
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() throws ParseException {
        if (UsedUtil.isNotNull(endDate)) {
    		return DateUtils.formatDateStr(endDate);
		}else{
			return null;
		}
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
  
    public String getAllor() {
		return allor;
	}

	public void setAllor(String allor) {
		this.allor = allor;
	}

	public String getAllorTime() throws ParseException {
        if (UsedUtil.isNotNull(allorTime)) {
    		return DateUtils.formatDateStr(allorTime);
		}else{
			return null;
		}
    }

	public void setAllorTime(Date allorTime) {
		this.allorTime = allorTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "Module [id=" + id + ", institutionId=" + institutionId + ", name=" + name + ", myselfId=" + myselfId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description + ", allor="
				+ allor + ", allorTime=" + allorTime + ", state=" + state + ", isDel=" + isDel + ", creater=" + creater
				+ ", createTime=" + createTime + "]";
	}
    
}