package com.ccx.models.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ccx.models.model.PermissionBean;
import com.ccx.models.model.vo.UserVo;
import com.ccx.models.util.FileOperate;
import com.ccx.models.util.MD5;
import com.ccx.models.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ccx.models.api.UserApi;
import com.ccx.models.mapper.UserMapper;
import com.ccx.models.model.Module;
import com.ccx.models.model.User;
import com.ccx.models.model.UserNum;
import com.github.pagehelper.PageInfo;
@Service("userApi")
public class UserServiceImpl implements UserApi{

	@Autowired
    private UserMapper userMapper;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	public List<User> selectByLoginName(User userVo) {
		User user = new User();
        user.setLoginName(userVo.getLoginName());
        return userMapper.selectListByLoginName(user.getLoginName());
	}

	@Override
	public PageInfo<UserVo> findAll(Map<String,Object> params) {
		List<UserVo> list = userMapper.findAll(params);
		PageInfo<UserVo> pages = new PageInfo<UserVo>(list);
		return pages;
	}
		

	


	@Override
	public User selectUserById(Long id) {
		User user = userMapper.selectUserById(id);
		return user;
	}

	@Override
	public String updateTO(User user) {
		String result = "999";
		try {
			int msg = userMapper.updateTO(user);
			if(msg>0){
				result = "0000";
			}else{
				result = "999";
			}
		} catch (Exception e) {
			result = "999";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void doAddUser(User user) {
		userMapper.doAddUser(user);
		createFile(user.getLoginName());
	}

	/**
	 * 创建模型放置路径
	 * @param userName
	 */
	private void createFile(String userName){
		try {
			//用户文件路径
			String baseFilePath= PropertiesUtil.getProperty("model_user_fileBasePath");
			//公共配置文件路径
			String configBasePath=PropertiesUtil.getProperty("model_config_path");
			//复制配置文件
			FileOperate.copyFolder(configBasePath,baseFilePath+userName+"/conf/");
			System.out.println("=====================================================");
			FileOperate.createFolder(baseFilePath+userName+"/ccxboost/");
			FileOperate.createFolder(baseFilePath+userName+"/ccxgbm/");
			FileOperate.createFolder(baseFilePath+userName+"/ccxrf/");
			FileOperate.createFolder(baseFilePath+userName+"/datafile/");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void resetPassword(User user) {
		userMapper.updateTO(user);
	}

	@Override
	public User getUserByName(String loginName) {
		User user = userMapper.getUserByName(loginName);
		return user;
	}

	/**
	 * @Description: 校验登录ip是否存在于数据库，如果不存在，不准登入。
	 * @Author: wxn
	 * @Date: 2017/12/27 18:20:59
	 * @Param:
	 * @Return
	 */
	@Override
	public List<String> getIPList(String loginIp){
		return userMapper.getIPList(loginIp);
	}
	
	@Override
	public List<User> getUserListByName(String loginName){
		return userMapper.getUserListByName(loginName);
	}

	@Override
	public String freezeUser(Long id) {
		String result = "999";
		try {
			int msg = userMapper.freezeUser(id);
			if(msg>0){
				result = "0000";
			}else{
				result = "999";
			}
		} catch (Exception e) {
			result = "999";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User getUser(HttpServletRequest request, User user) {
		Integer organizationId = Integer.parseInt(request.getParameter("organizationId"));
		// 获取当前时间
		Date createTime = new Date();
		String password = MD5.encryption(request.getParameter("password"));
		// 向角色表中添加创建时间
		user.setCreateTime(createTime);
		user.setPassword(password);
		user.setUserType(0);
		user.setStatus(0);
		user.setIsDel(0);
		user.setInstitutionId(organizationId);
		return user;
	}

	@Override
	public User getEditUser(HttpServletRequest request, User user) {
		long id = Long.parseLong(request.getParameter("id"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		String name = request.getParameter("name");

		user.setId(id);
		user.setStatus(status);
		user.setName(name);
		return user;
	}

	@Override
	public String unfreeze(Long id) {
		String result = "999";
		try {
			int msg = userMapper.unfreezeUser(id);
			if(msg>0){
				result = "0000";
			}else{
				result = "999";
			}
		} catch (Exception e) {
			result = "999";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void lockUser(Long id) {
		userMapper.lockUser(id);
	}

	@Override
	public void unlock(Long id, Cache cache) {
		//User user = userMapper.selectUserById(id);
		cache.clear();
		userMapper.unfreezeUser(id);
	}

	@Override
	public String deleteUser(Long id) {
		String result = "999";
		try {
			int msg = userMapper.deleteUser(id);
			if(msg>0){
				result = "0000";
			}else{
				result = "999";
			}
		} catch (Exception e) {
			result = "999";
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据用户ID查询权限（permission）
	 * @param id
	 * @return
	 */
	@Override
	public List<PermissionBean> findUserMenuPermission(Long id){
		return userMapper.findUserMenuPermission(id);
	}

	
	
}
 