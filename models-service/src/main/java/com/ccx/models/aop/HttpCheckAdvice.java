package com.ccx.models.aop;


import javax.servlet.http.HttpServletRequest;
import com.ccx.models.annotation.CheckHttpRequst;
import com.ccx.models.util.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * 校验接口请求
 * @author long.li 
 * 
 */  
@Component
@Aspect
public class HttpCheckAdvice  {
	Logger log_free = LogManager.getLogger("freebatchinfo");
	
	 //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
   @Pointcut("execution(* com.ccx.models.*.*.*(..))")
	//@Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void aspect(){
    	System.out.println(111);
    }


    
    @After("@annotation(type)&&args(bean, request)")
    public void after(CheckHttpRequst type,Object bean,
			HttpServletRequest request){

    }
    @Before("@annotation(type)&&args(bean, request)")
    public void around(CheckHttpRequst type, Object bean,
					   HttpServletRequest request)
    		throws Throwable {
        if(type.type().equals("ip")) {
            String ip = HttpUtil.getIpAddr(request);
        }
    }

}
