package com.ccx.models.mapper;

import java.util.List;
import java.util.Map;

import com.ccx.models.model.Ip;
import org.apache.ibatis.annotations.Param;

public interface IPMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ip record);

    Ip selectByPrimaryKey(Integer id);

    List<Ip> findIPList(Map<String, Object> params);

    int updateByPrimaryKey(Map<String, Object> map);

	void updateState(Integer id);

	Integer selectIPCount();

	void setUpperLimit(Integer ipLimit);
	
	int checkIP(@Param("loginIp") String loginIp, @Param("insId") long insId);
	
}