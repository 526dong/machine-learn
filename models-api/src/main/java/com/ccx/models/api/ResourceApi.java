package com.ccx.models.api;

import java.util.List;
import java.util.Map;

import com.ccx.models.model.PermissionBean;

/**
 * 
 * @description Resource 表数据服务层接口
 * @author zxr
 * @date 2017 下午2:32:36
 */
public interface ResourceApi {

	/**
	 * 
	 * @Title: findAllPermission  
	 * @author: WXN
	 * @Description: 获取权限列表(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	String findAllPermission();
	
	/**
	 * 
	 * @Title: addPermissions  
	 * @author: WXN
	 * @Description: 增加权限(这里用一句话描述这个方法的作用)   
	 * @param: @param permissionBean
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	String addPermissions(PermissionBean permissionBean);
	
	/**
	 * 
	 * @Title: modifypermission  
	 * @author: WXN
	 * @Description: 编辑权限(这里用一句话描述这个方法的作用)   
	 * @param: @param permissionBean
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	String modifypermission(PermissionBean permissionBean);
	
	/**
	 * 
	 * @Title: modifypermissionState  
	 * @author: WXN
	 * @Description: 删除权限（更改权限状态）(这里用一句话描述这个方法的作用)   
	 * @param: @param permissionBean
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	String modifypermissionState(PermissionBean permissionBean);

	/**
	 * 根据资源id获取资源
	 * @param resId
	 * @return
	 */
	PermissionBean selectResourceById(Long resId);
	
	/**
	 * 
	 * @Title: getLeftNavigation  
	 * @author: WXN
	 * @Description: 根据模块获取左侧导航栏 
	 * @param: @param params
	 * @param: @return      
	 * @return: List<PermissionBean>      
	 * @throws
	 */
	List<PermissionBean> getLeftNavigation(Map<String,Object> params);
	

	
	/**
	 * 
	 * @Title: saveRolePermission  
	 * @author: WXN
	 * @Description: 保存分配后的权限(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @param perIdList
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int saveRolePermission(String id, List<String> perIdList);

	/**
	 * 查询权限树
	 * @param roleId
	 * @return
	 */
	String finRolePermissionTree(Long roleId);

}
