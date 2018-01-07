package com.ccx.models.message;

import com.ccx.models.model.User;
import com.ccx.models.util.CookieUtil;
import com.ccx.models.util.TimerConcurrentHashMap;
import org.comet4j.core.CometConnection;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 
 * @ClassName:  MsgPushUtil   
 * @Description:TODO(comet4j推送消息)   
 * @author: 李龙 
 * @date:   2017年3月30日 下午2:17:26
 */
@Component
public class MsgPush {
	public static Map<String, Object> comet_map=new TimerConcurrentHashMap<>(1000*60*60*2l,10000);
		/**
		 * 
		 * @Title: sendMsgToClient   
		 * @Description: TODO(全部推送)   
		 * @param: @param msg
		 * @param: @param channel
		 * @param: @return      
		 * @return: boolean      
		 * @throws
		 */
	    public  boolean sendMsgToClient(Object msg, String channel){
	        CometEngine engine = CometContext.getInstance().getEngine();
	        System.out.println(engine==null);
	        if(engine!=null&&msg!=null&&channel!=null){
	        	engine.sendToAll(channel,msg);  
	        }
	         return true;  
	    }  
	    /**
	     * 
	     * @Title: sendMsgToUser   
	     * @Description: TODO(单人推送)   
	     * @param: @param msg
	     * @param: @param channel
	     * @param: @param connId
	     * @param: @return      
	     * @return: boolean      
	     * @throws
	     */
	    public  boolean sendMsgToUser(Object msg, String channel, String connId){
	    	CometEngine engine = CometContext.getInstance().getEngine(); 
	    	 engine.sendTo(channel, engine.getConnection(connId),msg);  
	    	return true;  
	    }

	/**
	 * 保存连接信息
	 * @param conn
	 */
	public static void saveConnection(CometConnection conn){
			Cookie c = CookieUtil.getCookieByName(conn.getRequest(),"uname");
			comet_map.put(c.getValue(),conn.getId());
	}
	public static String getConnId(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String userName=(String) request.getSession().getAttribute("userName");
		return (String) comet_map.get(userName);
	}
}
