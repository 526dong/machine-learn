package com.ccx.models.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccx.models.Constants;
import com.ccx.models.api.ResourceApi;
import com.ccx.models.api.RoleApi;
import com.ccx.models.api.UserApi;
import com.ccx.models.base.BaseController;
import com.ccx.models.model.Module;
import com.ccx.models.model.PermissionBean;
import com.ccx.models.model.Role;
import com.ccx.models.model.User;
import com.ccx.models.shiro.ShiroDbRealm;
import com.ccx.models.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @description 登录控制
 * @author zxr
 * @data 2017年6月6日 下午5:55:32
 */
@Controller
public class LoginController extends BaseController {

	private static Logger logger = LogManager.getLogger(LoginController.class);
	
	// 中诚信信用风险管理平台 redis 前缀
	private final static String KEY_CCX_CREDITRISK_WEB_REG_SMS = "ABS:ccx_credit_risk:REG_SMS_";
	// 中诚信信用风险管理平台短信次数输入连续 错误 count 数值
	private static final Integer CREDITRISK_SENDSMS_SERIESERRCOUNT=Integer.parseInt(PropertiesUtil.getProperty("credit_risk_sendSms_SeriesErrcount"));
	// 中诚信信用风险管理平台消息提醒
	private final static String KEY_CCX_CREDITRISK_WEB_WARNMSG = "ABS:ccx_credit_risk:warnMsg:moduleName";
	
	@Autowired
	private UserApi userApi;
	
	@Autowired
	private ResourceApi resourceApi;

	@Autowired
	private ShiroDbRealm dbRealm;

	@Autowired
	private RoleApi roleApi;
	

	/**
	 * 首页
	 *
	 * @return
	 */
	@GetMapping("/")
	public String index() {
		logger.info("已经登录，去主页");
		return "redirect:/index";
	}

	/**
	 * 首页
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

	/**
	 * get方式请求登录
	 * @return
	 */
	@GetMapping("/login")
	public String login() {

        logger.info("get请求登录：初始请求");
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index";
		}
		return "login";
	}

	/**
	 * POST 登录 shiro
	 *
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return {Object}
	 */
	@PostMapping("/checkLogin")
	@ResponseBody
	public Map<String, String> checkLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		Map<String, String> map = new HashMap<>();
		String result = "请求异常";
		// 改为全部抛出异常，避免ajax,token被刷新
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			User userbean = userApi.getUserByName(username);
			User newUserBean = new User();
			//得到主体
			Subject subject = SecurityUtils.getSubject();
			try {
				dbRealm.clearAuthenticationInfo();
				password = MD5.encryption(password);
				// 获取用户凭证信息
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				// "已记住"和"已认证"是有区别的,并非是认证通过的用户
				token.setRememberMe(true);
				try {
					
					//登录验证成功
					//验证登录ip
					//获取登录ip
					String loginIp = HttpUtil.getIpAddr(request);
					logger.info("============当前登录IP:"+loginIp);
					//校验登录ip是否存在于数据库，如果不存在，不准登入。
					List<String> ipPermitList = userApi.getIPList(loginIp);

//					if(null == ipPermitList || ipPermitList.isEmpty()){
//						map.put("result", "IP访问受限，请联系中诚信征信!");
//						return map;
//					}

					if(userbean.getIsDel() == 1){
						map.put("result", "该用户已注销!");
						return map;
					}else{
						if(userbean.getStatus()==1){
							map.put("result", "该用户已停用!");
							return map;
						}else if(userbean.getStatus()==2){
							map.put("result", "该用户已锁定!");
							return map;
						}
					}


					//查询当前登录人的角色信息，如果是超管，则有所有权限，如果是普通管理员，则没有个人中心权限
					Role role = roleApi.getRoleByUserId(userbean.getId());
					//查询当前用户所拥有的权限信息---type=1，只查询二级菜单
					List<PermissionBean> permissionList = userApi.findUserMenuPermission(userbean.getId());
					//0超管 1普通管理员
					if(1 == role.getRoleType()){
						permissionList = reflushPermissionList(permissionList);
					}
					logger.info("============当前登录用户所拥有的权限:"+JSON.toJSON(permissionList));
					if(null==permissionList||permissionList.isEmpty()){
						logger.info("账号无访问权限，角色未分配权限");
						map.put("result", "账号无访问权限，角色未分配权限!");
						return map;
					}

			       	
					//登录验证
					subject.login(token);
					//验证是否登录成功
					if(subject.isAuthenticated()){
						//登录成功后将登录错误次数重置为0
//						newUserBean.setId(userbean.getId());
//						newUserBean.setLoginNum(0);
//						newUserBean.setLoginTime(new Date());
//						userApi.updateTO(newUserBean);
						
						map.put("result", "1");

						//用户信息放入session
						request.getSession().setAttribute("risk_crm_user", userbean);
						request.getSession().setAttribute("userName", userbean.getLoginName());
						request.getSession().setAttribute("risk_crm_user_role", role);
						request.getSession().setAttribute("user_m_permission", permissionList);
						request.getSession().setAttribute("meun_home_permission", JSON.toJSON(permissionList));
						CookieUtil.addCookie(response,"uname",userbean.getLoginName(),null,1000*60*60,null);
					}else{
						token.clear();
					}
				} catch (UnknownAccountException e) {
					result = "账号不存在！";
					throw new RuntimeException("账号不存在！", e);
				} catch (DisabledAccountException e) {
					result = "账号未启用！";
					throw new RuntimeException("账号未启用或被锁定！", e);
				} catch (ExcessiveAttemptsException e) {
					throw new RuntimeException("账号被锁定！", e);
				} catch (IncorrectCredentialsException e) {
					int loginNum = userbean.getLoginNum();
					//密码输错超过五次将被锁定，即第六次会被锁定
//					if(loginNum>=5){
//						loginNum += 1;
//						newUserBean.setId(userbean.getId());
//						newUserBean.setStatus(2);
//						newUserBean.setLoginNum(loginNum);
//						newUserBean.setLoginTime(new Date());
//						userApi.updateTO(newUserBean);
//						result = "密码输入错误5次以上,账号被锁定！请联系管理员!";
//						throw new RuntimeException("密码错误！", e);
//					}else if(loginNum<5){
//						loginNum += 1;
//						newUserBean.setId(userbean.getId());
//						newUserBean.setLoginNum(loginNum);
//						newUserBean.setLoginTime(new Date());
//						userApi.updateTO(newUserBean);
//						result = "密码错误,再错"+(6-loginNum)+"次将被锁定!";
						result = "密码错误！";
						throw new RuntimeException("密码错误！", e);
//					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", result);
			}
		} else {
			map.put("result", "用户名或密码不能空！");
		}
		return map;
	}

	/**
	 * 未授权
	 * 
	 * @return {String}
	 */
	@GetMapping("/unauth")
	public String unauth() {
		if (SecurityUtils.getSubject().isAuthenticated() == false) {
			return "redirect:/login";
		}
		return "unauth";
	}

	/**
	 * 退出
	 * 
	 * @return {Result}
	 */
	@PostMapping("/logout")
	@ResponseBody
	public Map<String,String> logout(HttpServletRequest request) {
		Map<String,String> map=new HashMap<>();
		logger.info("登出");
		try {
			Subject subject = SecurityUtils.getSubject();
			request.getSession().removeAttribute("menuIndex1");//左侧菜单定位
			request.getSession().removeAttribute("menuIndex2");//左侧菜单定位
			request.getSession().removeAttribute("risk_crm_user");//用户信息
			subject.logout();
			map.put("rsCode","0000");
		} catch (Exception e) {
			map.put("rsCode","9999");
			e.printStackTrace();
		}
		return  map;
	}
	
	/**
	 * 
	 * @Title: getLeftNavigation  
	 * @author: WXN
	 * @Description: 根据模块获取左侧导航栏 
	 * @param: @param request
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@ResponseBody
    @RequestMapping(value="/getLeftNavigation", method=RequestMethod.POST,produces = "application/json; charset=utf-8")  
    public String getLeftNavigation(HttpServletRequest request){
    	//获取请求参数
    	Map<String,Object> map = ControllerUtil.requestMap(request);
    	String userId = (String)map.get("userId");
    	String myselfId = (String)map.get("myselfId");
    	if(UsedUtil.isNotNull(userId)&&UsedUtil.isNotNull(myselfId)){
    		Map<String,Object> params = new HashMap<String,Object>();
    		params.put("userId", Integer.parseInt(userId.trim()));
    		params.put("myselfId", myselfId.trim());
    		//查询左侧导航栏 
    		List<PermissionBean> result = resourceApi.getLeftNavigation(params);
        	return JSON.toJSONString(result);
    	}
		return null;
    }
	
	@SuppressWarnings("unchecked")
	@PostMapping("/getWarnMsg")
	@ResponseBody
    public Map<String,Object> getWarnMsg(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> resultList = null;
		try {
			resultList = (List<Map<String, Object>>) RedisCacheUtil.getRedis(KEY_CCX_CREDITRISK_WEB_WARNMSG);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("warnMsgList", resultList);
		return map;
    }




		
	//计算日期相差天数
	public static int daysOfTwo(Date startDate, Date endDate) {
		if (startDate == null || endDate == null){
			return 0;
		}
		long baseNum = 3600 * 1000 * 24;
		long chashu = startDate.getTime() - endDate.getTime();
		if (chashu<0){
			return -1;
		}
		long absNum = Math.abs(chashu);
		if(absNum<0){
			return 0;
		}
//		long mod = absNum % baseNum;
		int num = (int) (absNum / baseNum);
//		if (mod > 0)
//			num++;
		return num;
    }
	
	/**
	 * 获取验证码
	 * 
	 * @return {Result}
	 */
	@RequestMapping(value="/getVerifyCode",method=RequestMethod.POST)
	@ResponseBody
	public String getVerifyCode(HttpServletRequest request) {
		String verifyCode = request.getParameter("verifyCode");//前台输入的验证码
		String verCode = (String) request.getSession().getAttribute("verCode");//系统生成的验证码
		String result = "notNull";
		if(null == verifyCode || "" == verifyCode){
			result = "notNull";
		}else {
			verifyCode = verifyCode.toLowerCase();
			if(verifyCode == verCode || verifyCode.equals(verCode) ){
				result = "pass";
			}else if(verifyCode != verCode && !verifyCode.equals(verCode) ){
				result = "notSame";
			}
		}
		return result;
	}
	
	/**
	 * 验证手机验证码
	 * 
	 * @return {Result}
	 */
	@RequestMapping(value="/checkVerifyCode",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkVerifyCode(HttpServletRequest request) {
		String mobileCode = request.getParameter("mobileCode");//前台输入的手机验证码
		// 短信验证码验证
		User user = ControllerUtil.getSessionUser(request);
		String phone = user.getPhone();
		String loginName = user.getLoginName();
		String smsRedisKey = KEY_CCX_CREDITRISK_WEB_REG_SMS+loginName+":"+phone;;
		Integer failMillisecond = 1000 * 60 * 5;
		String resCode = null;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if (!resCode.equals(EnumSysManage.SUCCESS.getCode())) {
			resultMap.put("code", EnumSysManage.FAILE.getCode());
			resultMap.put("msg", "短信验证码超时或不正确");
			return resultMap;
		}
		return resultMap;
	}





	/**
	 * @Description: 处理普通管理员下的权限列表
	 * @Author: wxn
	 * @Date: 2017/11/29 12:30:04
	 */
	public static List<PermissionBean> reflushPermissionList(List<PermissionBean> permissionList) {
		List<PermissionBean> newPermissionList = new ArrayList<PermissionBean>();
		if(null != permissionList && !permissionList.isEmpty()){
			for (int i=0;i<permissionList.size();i++){
				PermissionBean permissionBean = permissionList.get(i);
				String myselfId = permissionBean.getMyselfId();
				String parentId = permissionBean.getParentId();
				if("manage-14" != myselfId && !"manage-14".equals(myselfId) && "manage-14" != parentId && !"manage-14".equals(parentId)){
					newPermissionList.add(permissionBean);
				}
			}
		}
		return newPermissionList;
	}
}
