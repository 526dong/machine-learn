package com.ccx.models.mapper;

import java.util.List;

import com.ccx.models.model.RoleResource;

/**
 * 
 * @description RoleResource 表数据库控制层接口
 * @author zxr
 * @date 2017 下午12:06:36
 */
public interface RoleResourceMapper {

	//根据角色id删除原有的数据
	void deleteByRoleId(Long roleId);

	//插入新的角色资源数据
	int insertSelective(RoleResource roleRes);

	//获取角色对应的资源列表
	List<RoleResource> selectByRoleId(Long id);

	//根据roleId查询资源resId的集合
	List<Long> selectResIds(Long roleId);

	//根据MyselfId查询资源id
	Long selectByMyself(String string);

}
