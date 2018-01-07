package com.ccx.models.service.impl;

import java.util.List;
import java.util.Map;

import com.ccx.models.api.ResourceApi;
import com.ccx.models.mapper.ResourceMapper;
import com.ccx.models.mapper.UserMapper;
import com.ccx.models.model.PermissionBean;
import com.ccx.models.model.PermissionTreeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service("resourceApi")
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, PermissionBean> implements ResourceApi {
	
	@Autowired
    private ResourceMapper resourceMapper;
	@Autowired
    private UserMapper userMapper;

//    // 按类型获取资源
//    public List<Resource> selectByType(Integer type) {
//        EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
//        Resource resource = new Resource();
//        wrapper.setEntity(resource);
//        wrapper.addFilter("resource_type = {0}", type);
//        wrapper.orderBy("seq");
//        return resourceMapper.selectList(wrapper);
//    }
//	
//	@Override
//	public List<Tree> selectTree(ShiroUser shiroUser) {
//		// 存放资源树
//		List<Tree> trees = new ArrayList<>();
//		
//		// 获取shiro中缓存的角色信息
//		Set<String> roles = shiroUser.getRoles();
//		if (roles == null) {
//			return trees;
//		}
//		
//		// 管理员
//		if (roles.contains("admin")) {
//			List<Resource> resourceList = this.selectByType(RESOURCE_MENU);
//            if (resourceList == null) {
//                return trees;
//            }
//            for (Resource resource : resourceList) {
//                Tree tree = new Tree();
//                tree.setId(resource.getId());
//                tree.setPid(resource.getPid());
//                tree.setText(resource.getName());
//                tree.setIconCls(resource.getIcon());
//                tree.setAttributes(resource.getUrl());
//                tree.setOpenMode(resource.getOpenMode());
//                tree.setState(resource.getOpened());
//                trees.add(tree);
//            }
//            return trees;
//		}
//		
//		// 普通用户
//		List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
//        if (roleIdList == null) {
//            return trees;
//        }
//        System.out.println("******************************************" + roleIdList);
//        List<Resource> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList);
//        if (resourceLists == null) {
//            return trees;
//        }
//        for (Resource resource : resourceLists) {
//            Tree tree = new Tree();
//            tree.setId(resource.getId());
//            tree.setPid(resource.getPid());
//            tree.setText(resource.getName());
//            tree.setIconCls(resource.getIcon());
//            tree.setAttributes(resource.getUrl());
//            tree.setOpenMode(resource.getOpenMode());
//            tree.setState(resource.getOpened());
//            trees.add(tree);
//        }
//        return trees;
//	}
//
//	/**
//	 * 获取资源列表
//	 */
//	@Override
//	public List<Resource> findAllRes() {
//		List<Resource> list = resourceMapper.findAllRes();
//		return list;
//	}
//
//	/**
//	 * 根据资源id删除资源(逻辑删除)
//	 */
//	@Override
//	public void deleteResourceById(Long id) {
//		resourceMapper.deleteResourceById(id);
//	}
//
//	@Override
//	public Resource selectResourceById(Long id) {
//		Resource resource = resourceMapper.selectByPrimaryKey(id);
//		return resource;
//	}
//
//	@Override
//	public void updateTO(Resource resource) {
//		resourceMapper.updateTO(resource);
//	}
//
//	@Override
//	public void doAddRes(Resource resource) {
//		resourceMapper.doAddRes(resource);
//	}
	
	/**
	 * 
	 * @Title: findAllPermission  
	 * @author: WXN
	 * @Description: 获取权限列表(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@Override
	public String findAllPermission(){
		//找到该系统的所有资源权限。
		List<PermissionBean> list = resourceMapper.findAllPermission();
		return createPTreeJson(list);
	}
	
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
	@Override
	public String addPermissions(PermissionBean permissionBean){
		System.err.println("前台获取实体=====》》》》》》》》》》"+permissionBean.toString());
		String result = "1000";
		try {
			//获得父级节点
			List<PermissionBean> permission1  = resourceMapper.getPermissionbyMyselfID(permissionBean.getParentId());
			permissionBean.setLevel(permission1.get(0).getLevel()+1);
			//查询以sysPermissionBean.getParentId()为父级的权限条数，即同级权限数量
			List<PermissionBean> permission2  = resourceMapper.getPermissionbyParentId(permissionBean.getParentId());
			//设置序列
			//设置权限编码
			if(null != permission2 && permission2.size()>0){
				permissionBean.setSequenceNum((permission2.size()+1));
				String myselfId = permission2.get(permission2.size()-1).getMyselfId();
				String myselfId1 = myselfId.substring(myselfId.indexOf("-")+1, myselfId.length());
				int myselfId2 = Integer.parseInt(myselfId1)+1;
				permissionBean.setMyselfId("manage-"+String.valueOf(myselfId2));
			}else{
				permissionBean.setSequenceNum(1);
				String myselfId = permissionBean.getParentId();
				if(myselfId.equals("manage-0")){
					myselfId = myselfId.substring(0,myselfId.indexOf("-")+1);
					permissionBean.setMyselfId(String.valueOf(myselfId)+"10");
				}else{
					permissionBean.setMyselfId(String.valueOf(myselfId)+"01");
				}
			}
			System.err.println("新增实体=====》》》》》》》》》》"+permissionBean.toString());
			//新增资源
			int s = resourceMapper.addPermissions(permissionBean);
			if(s>0){
				result = "1000";
			}else{
				result = "9999";
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			 result = "9999";
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: ResourceServiceImpl   
	 * @Description: 编辑权限(这里用一句话描述这个方法的作用)   
	 * @param: @param permissionBean
	 * @param: @return
	 * @throws
	 */
	@Override
	public String modifypermission(PermissionBean permissionBean){
		String result = "1000";
		try {
			//获得父级节点
			List<PermissionBean> permission1  = resourceMapper.getPermissionbyMyselfID(permissionBean.getParentId());
			permissionBean.setLevel(permission1.get(0).getLevel()+1);
			//查询以sysPermissionBean.getParentId()为父级的权限条数，即同级权限数量
			List<PermissionBean> permission2  = resourceMapper.getPermissionbyParentId(permissionBean.getParentId());
			//设置序列
			boolean flag = false;
			int intFlag = 0;
			if(null != permission2 && permission2.size()>0){
				for (int i = 0; i < permission2.size(); i++) {
					String id = String.valueOf(permission2.get(i).getId());
					String ids = String.valueOf(permissionBean.getId());
					if(id.equals(ids)){
						flag = true;
						intFlag = i;
					}
				}
				if(flag == false){
					permissionBean.setSequenceNum(permission2.size()+1);
					String myselfId = permission2.get(permission2.size()-1).getMyselfId();
					String myselfId1 = myselfId.substring(myselfId.indexOf("-")+1, myselfId.length());
					int myselfId2 = Integer.parseInt(myselfId1)+1;
					permissionBean.setMyselfId("manage-"+String.valueOf(myselfId2));
				}else{
					permissionBean.setSequenceNum(permission2.get(intFlag).getSequenceNum());
					permissionBean.setMyselfId(permission2.get(intFlag).getMyselfId());
				}
			}else{
				permissionBean.setSequenceNum(1);
				String myselfId = permissionBean.getParentId();
				if(myselfId.equals("antifraud-0")){
					myselfId = myselfId.substring(0,myselfId.indexOf("-")+1);
					permissionBean.setMyselfId(String.valueOf(myselfId)+"10");
				}else{
					permissionBean.setMyselfId(String.valueOf(myselfId)+"01");
				}
			}
			
			//保存编辑后的资源
			int s = resourceMapper.modifypermission(permissionBean);
			if(s>0){
				result = "1000";
			}else{
				result = "9999";
			}
		} catch (Exception e) {
			e.printStackTrace();
			 result = "9999";
		}
		return result;
	}
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
	@Override
	public String modifypermissionState(PermissionBean permissionBean){
		String result = "1000";
		try {
			int s = resourceMapper.modifypermissionState(permissionBean);
			if(s>0){
				result = "1000";
			}else{
				result = "9999";
			}
		} catch (Exception e) {
			e.printStackTrace();
			 result = "9999";
		}
		return result;
	}
	
	
	
	 /**
	   * 创建一颗树，以json字符串形式返回
	   * @param list 原始数据列表
	   * @return 树
	   */
	  private String createPTreeJson(List<PermissionBean> list) {
	    JSONArray rootArray = new JSONArray();
	    for (int i=0; i<list.size(); i++) {
	    	PermissionBean resource = list.get(i);
	      if (resource.getParentId().equals("0")) {
	        JSONObject rootObj = createPBranch(list, resource);
	        rootArray.add(rootObj);
	      }
	    }
	    
	    return rootArray.toString();
	  }
	  
	  /**
	   * 递归创建分支节点Json对象
	   * @param list 创建树的原始数据
	   * @param currentNode 当前节点
	   * @return 当前节点与该节点的子节点json对象
	   */
	  private JSONObject createPBranch(List<PermissionBean> list, PermissionBean currentNode) {
	    /*
	     * 将javabean对象解析成为JSON对象
	     */
	    JSONObject currentObj = (JSONObject) JSON.toJSON(currentNode);
	    JSONArray childArray = new JSONArray();
	    /*
	     * 循环遍历原始数据列表，判断列表中某对象的父id值是否等于当前节点的id值，
	     * 如果相等，进入递归创建新节点的子节点，直至无子节点时，返回节点，并将该
	     * 节点放入当前节点的子节点列表中
	     */
	    for (int i=0; i<list.size(); i++) {
	    	PermissionBean newNode = list.get(i);
	      if (newNode.getParentId()!=null && newNode.getParentId().compareTo(currentNode.getMyselfId()) == 0) {
	        JSONObject childObj = createPBranch(list, newNode);
	        childArray.add(childObj);
	      }
	    }
	    
	    /*
	     * 判断当前子节点数组是否为空，不为空将子节点数组加入children字段中
	     */
	    if (!childArray.isEmpty()) {
	      currentObj.put("children", childArray);
	      currentObj.put("state", "closed");
	    }
	    
	    return currentObj;
	  }

	@Override
	public PermissionBean selectResourceById(Long resId) {
		PermissionBean res = resourceMapper.selectResourceById(resId);
		return res;
	}
	
	/**
	 * 
	 * @Title: UserServiceImpl   
	 * @Description: 根据模块获取左侧导航栏 
	 * @param: @param params
	 * @param: @return
	 * @throws
	 */
	@Override
	public List<PermissionBean> getLeftNavigation(Map<String,Object> params){
		return resourceMapper.getLeftNavigation(params);
	}
	

	
	/**
	 * 
	 * @Title: ResourceServiceImpl   
	 * @Description: 保存分配后的权限(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @param perIdList
	 * @param: @return
	 * @throws
	 */
	@Override
	public int saveRolePermission(String id, List<String> perIdList){
		int flag=1;
		//保存角色权限之前先删除该角色之前拥有的权限
		int msg = resourceMapper.delRolePermissionByRole(Long.parseLong(id));
		if(msg>=0){
			for(String perId:perIdList){
				if(null!=perId && !"".equals(perId) && !perId.equals("-1")){
					//perId为权限唯一标识，根据标识拿出主键id
					int perIdd = resourceMapper.getPermissionId(perId);
					int flag2 = resourceMapper.saveRolePermission(Long.parseLong(id),Long.valueOf(perIdd));
					if(flag2 < 0){
						flag=0;
					}
				}
			}	
		}else{
			flag=0;
		}
		return flag;
	}

	/**
	 * 查询权限树
	 * @param roleId
	 * @return
	 */
	@Override
	public String finRolePermissionTree(Long roleId){
		List<PermissionTreeBean> list = resourceMapper.finRolePermissionTree(roleId);
		return createTreeJson(list);
	}
	
	
	/**
	   * 创建一颗树，以json字符串形式返回
	   * @param list 原始数据列表
	   * @return 树
	   */
	  private String createTreeJson(List<PermissionTreeBean> list) {
	    JSONArray rootArray = new JSONArray();
	    for (int i=0; i<list.size(); i++) {
			PermissionTreeBean resource = list.get(i);
	      if (resource.getParentId().equals("0")) {
	        JSONObject rootObj = createBranch(list, resource);
	        rootArray.add(rootObj);
	      }
	    }
	    
	    return rootArray.toString();
	  }
	  
	  /**
	   * 递归创建分支节点Json对象
	   * @param list 创建树的原始数据
	   * @param currentNode 当前节点
	   * @return 当前节点与该节点的子节点json对象
	   */
	  private JSONObject createBranch(List<PermissionTreeBean> list, PermissionTreeBean currentNode) {
	    /*
	     * 将javabean对象解析成为JSON对象
	     */
	    JSONObject currentObj = (JSONObject) JSON.toJSON(currentNode);
	    JSONArray childArray = new JSONArray();
	    /*
	     * 循环遍历原始数据列表，判断列表中某对象的父id值是否等于当前节点的id值，
	     * 如果相等，进入递归创建新节点的子节点，直至无子节点时，返回节点，并将该
	     * 节点放入当前节点的子节点列表中
	     */
	    for (int i=0; i<list.size(); i++) {
			PermissionTreeBean newNode = list.get(i);
	      if (newNode.getParentId()!=null && newNode.getParentId().compareTo(currentNode.getId()) == 0) {
	        JSONObject childObj = createBranch(list, newNode);
	        childArray.add(childObj);
	      }
	    }
	    
	    /*
	     * 判断当前子节点数组是否为空，不为空将子节点数组加入children字段中
	     */
	    if (!childArray.isEmpty()) {
	    	currentObj.remove("checked");
	    	currentObj.put("children", childArray);
	    	if("0" == (String)currentObj.get("parentId") || "0".equals((String)currentObj.get("parentId"))){
	    		currentObj.put("state", "open");
	    	}else{
	    		currentObj.put("state", "closed");
	    	}
	        //currentObj.put("state", "closed");
	    }
	    return currentObj;
	  }

}
