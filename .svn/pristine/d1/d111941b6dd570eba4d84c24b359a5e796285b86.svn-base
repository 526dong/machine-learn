package com.ccx.models.shiro;
import com.ccx.models.api.*;
import com.ccx.models.model.PermissionBean;
import com.ccx.models.model.Role;
import com.ccx.models.model.User;
import com.ccx.models.util.JsonUtils;
import com.ccx.models.util.UsedUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description：shiro权限认证
 */
public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private UserApi userApi;

	@Autowired
	private UserRoleApi userRoleApi;

	@Autowired
	private RoleRessourceApi roleResApi;

	@Autowired
	private ResourceApi resApi;

	@Autowired
	private RoleApi roleApi;
	
     Logger log = LogManager.getLogger(ShiroDbRealm.class);

//	// 构造器：加密
//	public ShiroDbRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
//		super(cacheManager, matcher);
//	}

//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		User user = (User) principals.getPrimaryPrincipal();
//		// 获取用户角色id
//		Long roleId = userRoleApi.selectRoleId(user.getId());
//		// 获取用户角色
//		Role role = roleApi.selectRoleById(roleId);
//		// 根据角色id获取资源id
//		List<Long> resIds = roleResApi.selectResIds(roleId);
//		// 根据资源id获取资源
//		Set<String> urlSet = new HashSet<>();
//		for (Long resId : resIds) {
//			PermissionBean res = resApi.selectResourceById(resId);
//			if(res != null){
//				String url = res.getPathUrl();
//				if(null!=url&&!"".equals(url.trim())){
//					urlSet.add(url);
//				}
//			}
//		}
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addRole(role.getName());
//		info.addStringPermissions(urlSet);
//		log.info("拥有权限："+JsonUtils.toJson(info));
//		return info;
//	}

	/**
	 * Shiro登录认证 原理： 用户提交 用户名和密码 shiro 封装令牌 realm 通过用户名将密码查询返回 shiro
	 * 自动去比较查询出密码和用户输入密码是否一致 进行登陆控制
	 */

//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
//			throws AuthenticationException {
//		UsernamePasswordToken upToken = (UsernamePasswordToken) authcToken;
//		String loginName = upToken.getUsername();
//		User user = userApi.getUserByName(loginName);
//		Object principal = user;
//		Object hashedCredentials = user.getPassword();
//		String realmName = getName();
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, realmName);
//		return info;
//	}
	
	/**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录时输入的用户名
        String account=(String) principalCollection.fromRealm(getName()).iterator().next();
        //到数据库查是否有此对象
        User user= userApi.getUserByName(account);
        if(UsedUtil.isNotNull(user)){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            // 获取用户角色id
            Long roleId = userRoleApi.selectRoleId(user.getId());
            // 获取用户角色
            Role role = roleApi.selectRoleById(roleId);
            System.out.println("+++++++++++++++++++++++++"+role==null);
            // 根据角色id获取资源id
            List<Long> resIds = roleResApi.selectResIds(roleId);
            // 根据资源id获取资源
            Set<String> urlSet = new HashSet<>();
            for (Long resId : resIds) {
                PermissionBean res = resApi.selectResourceById(resId);
                if(res != null){
                    String url = res.getPathUrl();
                    if(null!=url&&!"".equals(url.trim())){
                        urlSet.add(url);
                        urlSet.add(RolesOrAuthorizationFilter.getPermittedUrl(url));
                    }
                }
            }
            info.addRole(role.getName());
            info.addStringPermissions(urlSet);
            log.info("拥有权限："+ JsonUtils.toJson(info));
            return info;
        }
        return null;
    }
	/**
     * 登录认证;
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        if(UsedUtil.isNotNull(token.getUsername())){
        	User user = userApi.getUserByName(token.getUsername());
            if(UsedUtil.isNotNull(user)){
                return new SimpleAuthenticationInfo(
                        user.getLoginName(),
                        user.getPassword(),
                        this.getName());
            }
        }
        return null;
    }
	

	@Override
	public void onLogout(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
		/*ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();*/
		removeUserCache(principals.getPrimaryPrincipal());
	}

	/**
	 * 清除用户缓存
	 * 
	 * @param user
	 */
	public void removeUserCache(Object user) {
		removeUserCache((String) user);
	}

	/**
	 * 清除用户缓存
	 * 
	 * @param loginName
	 */
	public void removeUserCache(String loginName) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		principals.add(loginName, super.getName());
		super.clearCachedAuthenticationInfo(principals);
	}
	
	 /**
     * 缓存
     */
    public void clearAuthenticationInfo(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
