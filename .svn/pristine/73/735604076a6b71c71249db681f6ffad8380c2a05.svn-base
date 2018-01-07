package com.ccx.models.util;


import com.ccx.models.model.Role;
import com.ccx.models.model.User;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取用户信息和请求参数
 * @author xzd
 * @date 2017/6/12
 * @version
 */
public class ControllerUtil {

	/**
	 * 获取请求参数
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static  Map<String,Object> requestMap(HttpServletRequest request){
		Enumeration emu = request.getParameterNames();
		Map<String,Object> requestMap = new HashMap<String,Object>();
		while(emu.hasMoreElements()){
			String name = (String)emu.nextElement();
			String value = request.getParameter(name);
			
			boolean flag = (value == null || "".equals(value.toString().trim()));
			
			if(!flag){
				requestMap.put(name, value.trim());
			}			
		}		
		return requestMap;
	}
	
	/**
	 * 获取登陆的用户信息
	 * @param req HttpServletRequest
	 * @return user 
	 */ 
	public static User getSessionUser(HttpServletRequest req){
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("risk_crm_user");
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
    	return user;
	}

	/**
	 * @Description: 获取登陆的用户信息
	 * @Author: wxn
	 * @Date: 2017/11/28 16:23:09
	 */
	public static User getUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return(User) request.getSession().getAttribute("risk_crm_user");
	}

	/**
	 * @Description: 获取登陆的用户角色信息
	 * @Author: wxn
	 * @Date: 2017/11/28 16:23:09
	 */
	public static Role getUserRole(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return(Role) request.getSession().getAttribute("risk_crm_user_role");
	}

	/**
	 * @Description: 获取登陆的用户角色信息
	 * @Author: wxn
	 * @Date: 2017/11/28 16:23:09
	 */
	public static String getRoleType(){
		String roleType = null;
		Role role = getUserRole();
		User user = getUser();
		//如果登录人为超管，则可以查找所有列表，否则只能查找自己下的列表，0：超管
		if(null!=role){
			if(role.getRoleType()!=0){
				roleType = user.getLoginName();
			}
		}
		return roleType;
	}

	
}
