package com.ccx.models.util.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ccx.models.util.ControllerUtil;
import com.ccx.models.util.UsedUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



/**
 * 
* @ClassName: MenuIndexAspect 
* @Description: 菜单脚标定位切面
* @author WXN 
* @date 2017年8月10日 下午1:59:59 
*
 */
@Aspect
@Component
public class MenuIndexAspect {

	private static Logger logger = LogManager.getLogger(MenuIndexAspect.class);

	@Pointcut("execution(* com.ccx.models..*Controller.*(..))")
	//@Pointcut("execution(* com.ccx.antifraud.controller.*..*.*(..))")
	public void menuIndexAspect() {
	}

	@Before("menuIndexAspect()")
	public void doBefore(JoinPoint joinPoint)  {
		//获取request
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();  
		//获取请求方式
		String method = request.getMethod();
		//获取请求参数
		Map<String,Object> map = ControllerUtil.requestMap(request);
		//获取菜单脚标
		String menuIndex = (String) map.get("menuIndex");
		if(method.toLowerCase().equals("get") && UsedUtil.isNotNull(menuIndex) ){
			logger.debug("菜单脚标 - "+menuIndex);
			request.getSession().setAttribute("menuIndex", menuIndex);
		}
	}
	
	@Around(value="menuIndexAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
	    return joinPoint.proceed();
	}
	
	@After(value = "menuIndexAspect()")
	public void doAfter(JoinPoint joinPoint) {
	}
	
}
