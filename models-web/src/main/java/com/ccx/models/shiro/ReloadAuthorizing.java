package com.ccx.models.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

public class ReloadAuthorizing {

        /**
         * 重新赋值权限(在比如:给一个角色临时添加一个权限,需要调用此方法刷新权限,否则还是没有刚赋值的权限)
         * @param username
         */
            public static void reloadAuthorizing(String username){
                RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
                ShiroDbRealm shiroRealm = (ShiroDbRealm)rsm.getRealms().iterator().next();
                Subject subject = SecurityUtils.getSubject();
                String realmName = subject.getPrincipals().getRealmNames().iterator().next();

                //shiroRealm.clearAllCachedAuthorizationInfo2();//清楚所有用户权限
                //第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户
                SimplePrincipalCollection principals = new SimplePrincipalCollection(username,realmName);
                subject.runAs(principals);
                shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
                shiroRealm.getAuthorizationCache().remove(username);
                subject.releaseRunAs();
            }
}
