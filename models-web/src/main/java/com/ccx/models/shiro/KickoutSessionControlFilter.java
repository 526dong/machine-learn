package com.ccx.models.shiro;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class KickoutSessionControlFilter  extends AccessControlFilter{
    private String kickoutUrl; //踢出后到的地址  
    private boolean kickoutAfter; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户  
    private int maxSession; //同一个帐号最大会话数 默认1  
    private SessionManager sessionManager;  
    private Cache<String, Deque<Serializable>> cache;  
  
    public void setKickoutUrl(String kickoutUrl) {  
        this.kickoutUrl = kickoutUrl;  
    }  
  
    public void setKickoutAfter(boolean kickoutAfter) {  
        this.kickoutAfter = kickoutAfter;  
    }  
  
    public void setMaxSession(int maxSession) {  
        this.maxSession = maxSession;  
    }  
  
    public void setSessionManager(SessionManager sessionManager) {  
        this.sessionManager = sessionManager;  
    }  
  
    public void setCacheManager(CacheManager cacheManager) {  
        this.cache = cacheManager.getCache("shiro-activeSessionCache");  
    }  
     /** 
      * 是否允许访问，返回true表示允许 
      */  
    @Override  
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {  
    	HttpServletRequest httpRequest = ((HttpServletRequest)request);
		String url = httpRequest.getRequestURI();
		Subject subject = getSubject(request, response);
		//如果是相关目录 or 如果没有登录 就直接return true
		if(url.startsWith("/open/") || (!subject.isAuthenticated() && !subject.isRemembered())){
			return Boolean.TRUE;
		}
		Session session = subject.getSession();
		/**
		 * 判断是否已经踢出
		 * 1.如果是Ajax 访问，那么给予json返回值提示。
		 * 2.如果是普通请求，直接跳转到登录页
		 */
		Boolean marker = (Boolean)session.getAttribute("kickout");
		if (null != marker && marker ) {
			Map<String, String> resultMap = new HashMap<String, String>();
			//判断是不是Ajax请求
			if (isAjax(request) ) {
				//当前用户已经在其他地方登录，并且是Ajax请求！
				resultMap.put("user_status", "300");
				resultMap.put("message", "您的账号在另外的设备登录，您已下线。若非本人操作，请马上重新登录并修改密码！");
				out(response, resultMap);
			}
			return  Boolean.FALSE;
		}
    	return false;  
    }  
    /** 
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。 
     */  
    @Override  
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {  
        Subject subject = getSubject(request, response);  
        if(!subject.isAuthenticated()) {  
            //如果没有登录，直接进行之后的流程  
            return true;  
        }  
  
        Session session = subject.getSession();  
        String username = (String) subject.getPrincipal();  
        Serializable sessionId = session.getId();  
  
        // 初始化用户的队列放到缓存里  
        Deque<Serializable> deque = cache.get(username);  
        if(deque == null) {  
            deque = new LinkedList<Serializable>();  
            cache.put(username, deque);  
        }  
  
        //如果队列里没有此sessionId，且用户没有被踢出；放入队列  
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {  
            deque.push(sessionId);  
        }  
  
        //如果队列里的sessionId数超出最大会话数，开始踢人  
        while(deque.size() > maxSession) {  
            Serializable kickoutSessionId = null;  
            if(kickoutAfter) { //如果踢出后者  
                kickoutSessionId=deque.getFirst();  
                kickoutSessionId = deque.removeFirst();  
            } else { //否则踢出前者  
                kickoutSessionId = deque.removeLast();  
            }  
            try {  
            	DefaultSessionKey defaultSessionKey = new DefaultSessionKey(kickoutSessionId);
                Session kickoutSession = sessionManager.getSession(defaultSessionKey);  
                if(kickoutSession != null) {  
                    //设置会话的kickout属性表示踢出了  
                    kickoutSession.setAttribute("kickout", true);  
                }  
            } catch (Exception e) {//ignore exception  
                e.printStackTrace();  
            }  
        }  
  
        //如果被踢出了，直接退出，重定向到踢出后的地址  
        if (session.getAttribute("kickout") != null) {  
            //会话被踢出了  
            try {  
                subject.logout();  
            } catch (Exception e) {
            	e.printStackTrace();
            }  
            WebUtils.issueRedirect(request, response, kickoutUrl);  
            return false;  
        }  
        return true;  
    }  
    
    /**
     * 是否是Ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
    	String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
    	//当前请求Ajax请求
    	if("XMLHttpRequest".equalsIgnoreCase(header)){
    		return Boolean.TRUE;
    	}
    	//当前请求非Ajax请求
    	return Boolean.FALSE;
    }
    
    /**
     *  使用	response 输出JSON
     * @param hresponse
     * @param resultMap
     * @throws IOException
     */
    private void out(ServletResponse hresponse, Map<String, String> resultMap)
			throws IOException {
		try {
			hresponse.setCharacterEncoding("UTF-8");
			hresponse.setContentType("application/json");
			PrintWriter out = hresponse.getWriter();
			out.println(JSON.toJSONString(resultMap));
			out.flush();
			out.close();
		} catch (Exception e) {
			//LoggerUtils.error(getClass(), "KickoutSessionFilter.class 输出JSON异常，可以忽略。");
		}
	}
}  