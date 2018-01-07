package com.ccx.models.model;

import java.util.Date;

import com.ccx.models.util.DateUtils;

public class Ip {
    private Integer id;

    private String ipAddress;

    private Date createTime;

    private String creator;

    private String createPlantform;

    private Integer state;
    
    private Integer ipUpperLimit;

    public Integer getIpUpperLimit() {
		return ipUpperLimit;
	}

	public void setIpUpperLimit(Integer ipUpperLimit) {
		this.ipUpperLimit = ipUpperLimit;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getCreateTime() throws Exception {
    	if (createTime != null) {
     		return DateUtils.formatDateStr(createTime);
 		}else{
 			return null;
 		}
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreatePlantform() {
        return createPlantform;
    }

    public void setCreatePlantform(String createPlantform) {
        this.createPlantform = createPlantform == null ? null : createPlantform.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}