package com.ccx.models.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ccx.models.model.UserRole;

/**
 * 
 * @description UserRole 表数据库控制层接口
 * @author zxr
 * @date 2017 上午11:56:49
 */
public interface UserRoleMapper {

	/**
	 * 根据用户ID查询角色id
	 * @param userId
	 * @return
	 */
	List<Long> selectRoleIdListByUserId(@Param("userId") Long userId);

	/**
	 * 更改用户角色id
	 * @param id
	 * @param roleId
	 */
	void updateRoleId(UserRole userRole);

	/**
	 * 保存用户角色
	 * @param userRole
	 */
	void addRoleToUser(UserRole userRole);

	/**
	 * 根据userId查询userRole
	 * @param id
	 * @return
	 */
	UserRole selectUserRole(@Param("id") Long id);

	/**
	 * 根据userId查询roleId
	 * @param userId
	 * @return
	 */
	Long selectRoleId(Long userId);
}
