package com.ccx.models.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.ccx.models.mapper.UserRoleMapper;
import com.ccx.models.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.ccx.models.api.UserRoleApi;

@Service("userRoleApi")
public class UserRoleServiceImpl implements UserRoleApi {
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public void updateRoleId(UserRole userRole) {
		userRoleMapper.updateRoleId(userRole);
	}

	/**
	 * 保存用户角色
	 */
	@Override
	public void addRoleToUser(UserRole userRole) {
		userRoleMapper.addRoleToUser(userRole);
	}

	@Override
	public UserRole selectUserRole(Long id) {
		UserRole userRolr = userRoleMapper.selectUserRole(id);
		return userRolr;
	}

	@Override
	public Long selectRoleId(Long userId) {
		Long roleId = userRoleMapper.selectRoleId(userId);
		return roleId;
	}

	@Override
	public UserRole getUserRole(HttpServletRequest request) {
		long id = Long.parseLong(request.getParameter("id"));
		Long roleId = Long.parseLong(request.getParameter("roleId"));

		UserRole userRole = new UserRole();
		userRole.setUserId(id);
		userRole.setRoleId(roleId);
		return userRole;
	}
}
