package com.ccx.models.mapper;

import java.util.List;
import java.util.Map;

import com.ccx.models.model.PermissionTreeBean;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ccx.models.model.PermissionBean;

/**
 * 
 * @description Resource 表数据库控制层接口
 * @author zxr
 * @date 2017 下午2:37:18
 */
public interface ResourceMapper extends BaseMapper<PermissionBean>{

	//获取资源列表
	List<PermissionBean> findAllRes();

	//删除资源
	void deleteResourceById(@Param("id") Long id);

	//查询资源详细信息
	PermissionBean selectByPrimaryKey(@Param("id") Long id);

	//保存资源的修改信息
	void updateTO(PermissionBean resource);

	//保存新增资源信息
	void doAddRes(PermissionBean resource);
	
	/**
	 * 
	 * @Title: findAllPermission  
	 * @author: WXN
	 * @Description: 获取权限列表(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: List<PermissionBean>      
	 * @throws
	 */
	List<PermissionBean> findAllPermission();
	
	/**
	 * 
	 * @Title: getPermissionbyMyselfID  
	 * @author: WXN
	 * @Description: 查询(这里用一句话描述这个方法的作用)   
	 * @param: @param myselfID
	 * @param: @return      
	 * @return: List<PermissionBean>      
	 * @throws
	 */
	List<PermissionBean> getPermissionbyMyselfID(String myselfID);
	
	/**
	 * 
	 * @Title: addPermissions  
	 * @author: WXN
	 * @Description: 新增权限(这里用一句话描述这个方法的作用)   
	 * @param: @param permissionBean
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int addPermissions(PermissionBean permissionBean);
	
	/**
	 * 
	 * @Title: findPermissionSequencreNum  
	 * @author: WXN
	 * @Description: 获取权限列表（通过父级id）(这里用一句话描述这个方法的作用)   
	 * @param: @param permissionBean
	 * @param: @return      
	 * @return: Integer      
	 * @throws
	 */
	List<PermissionBean> getPermissionbyParentId(String parentId);
	
	/**
	 * 
	 * @Title: addPermissions  
	 * @author: WXN
	 * @Description: 新增权限(这里用一句话描述这个方法的作用)   
	 * @param: @param permissionBean
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int modifypermission(PermissionBean permissionBean);
	
	
	/**
	 * 
	 * @Title: modifypermissionState  
	 * @author: WXN
	 * @Description: 删除权限（更改权限状态）(这里用一句话描述这个方法的作用)   
	 * @param: @param permissionBean
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int modifypermissionState(PermissionBean permissionBean);
	

	//获取资源分页模型
	List<PermissionBean> findAll(Map<String, Object> params);

	/**
	 * 根据parentId查询子资源
	 * @param parentId
	 * @return
	 */
	List<PermissionBean> selectResByPidd(long insId);
	List<PermissionBean> selectResByPid(String parentId);

	/**
	 * 根据id查询资源
	 * @param resId
	 * @return
	 */
	PermissionBean selectResourceById(Long resId);

	//根据myselfId查询资源id
	Long selectByMyself(String myselfId);
	
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
	 * @Title: findInsPermissionTreeMy 
	 * @author: WXN
	 * @Description: 利用mysql函数查询该机构下的权限树(这里用一句话描述这个方法的作用)   
	 * @param: @param roleId
	 * @param: @return      
	 * @return: List<PermissionTreeBean>
	 * @throws
	 */
	List<PermissionTreeBean> findInsPermissionTreeMy(@Param("roleId")long roleId, @Param("myselfId")String myselfId);
	
	
	/**
	 * 
	 * @Title: finRolePermissionTree  
	 * @author: WXN
	 * @Description: 保存分配的权限(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return      
	 * @return: List<PermissionBean>      
	 * @throws
	 */
	//保存权限之前先删除
	public int delRolePermissionByRole(Long id);
	public int saveRolePermission(@Param("id")Long id, @Param("perId")Long perId);
	public int getPermissionId(String perId);


	/**
	 * 查询权限树
	 * @param id
	 * @return
	 */
	List<PermissionTreeBean> finRolePermissionTree(@Param("id") Long id);

}
