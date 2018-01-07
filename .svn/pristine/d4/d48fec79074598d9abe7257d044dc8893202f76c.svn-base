package com.ccx.models.service.impl;

import java.util.List;

import com.ccx.models.api.RoleRessourceApi;
import com.ccx.models.mapper.RoleResourceMapper;
import com.ccx.models.model.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleResApi")
public class RoleRessourceApiImpl implements RoleRessourceApi {

	@Autowired
	private RoleResourceMapper roleResMapper;
	
	@Override
	public List<Long> selectResIds(Long roleId) {
		List<Long> roleList = roleResMapper.selectResIds(roleId);
		return roleList;
	}

	@Override
	public List<RoleResource> selectByRoleId(Long roleId) {
		List<RoleResource> resList = roleResMapper.selectByRoleId(roleId);
		return resList;
	}
}
