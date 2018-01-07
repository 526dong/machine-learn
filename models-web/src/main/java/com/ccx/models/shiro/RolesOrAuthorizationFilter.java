package com.ccx.models.shiro;


import com.ccx.models.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 
 * @ClassName:  RolesOrAuthorizationFilter   
 * @Description:TODO(自定义权限控制)   
 * @author: lilong 
 * @date:   2017年7月13日 上午10:20:02
 */
public class RolesOrAuthorizationFilter extends AuthorizationFilter {
	
	private static Map<String, String> repurls=null;
   Logger logger = LogManager.getLogger(RolesOrAuthorizationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
            ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated()){
        	return false;
        }
        HttpServletRequest re=(HttpServletRequest) request;
        try {
            String url=getPermittedUrl(re.getServletPath());
            for (String str:url.split(",")){
                if(subject.isPermitted(str)){
                    return true;
                }
            }
            logger.info("url:"+re.getServletPath()+",there is no authority");
        } catch (Exception e) {
			e.printStackTrace();
		}
       // TimeUnit.SECONDS.sleep(6);
        return true;
    }
    public static String getPermittedUrl(String url){
        if(repurls==null){
        	synchronized (RolesOrAuthorizationFilter.class) {
        		if(repurls==null){
        			repurls= PropertiesUtil.getData("prop/authority-config");
        		}
			}
        }
        String newurl= repurls.get(url);
        if(newurl!=null){
            return newurl;
        }
    	return url;
    }
}