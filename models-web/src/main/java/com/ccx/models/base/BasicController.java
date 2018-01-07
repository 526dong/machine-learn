package com.ccx.models.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * @author xzd
 * @date 2017/6/21
 */
public class BasicController {
    //
	ThreadLocal<HttpServletRequest> threadLocalRequest = new InheritableThreadLocal<>();
	ThreadLocal<HttpServletResponse> threadLocalResponse = new InheritableThreadLocal<>();

    Logger logger = LogManager.getLogger(BaseController.class);


    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        threadLocalRequest.set(request);
        threadLocalResponse.set(response);
    }

    @ExceptionHandler
    public String exp(HttpServletRequest request, Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            ex.printStackTrace(pw);
        } finally {
            pw.close();
        }
        // 根据不同错误输出不同信息
        // if(ex instanceof RuntimeException) {
        //
        // }else if(ex instanceof Exception) {
        //
        // } else {
        //
        // }
        return "error";
    }
    /**
     * 页数
     * @return
     */
    public int getPageNum() {
        HttpServletRequest request = threadLocalRequest.get();
        int pageNum;
        if("".equals(request.getParameter("pageNum")) || request.getParameter("pageNum") == null){
            pageNum = 1;
        }else{
            pageNum=Integer.parseInt(request.getParameter("pageNum"));
        }
        return pageNum;
    }
    
    /**
     * 页面数据条数
     * @return
     */
    public int getPageSize() {
        HttpServletRequest request = threadLocalRequest.get();
        int pageSize;
        if("".equals(request.getParameter("pageSize")) || request.getParameter("pageSize") == null){
            pageSize = 10;//默认每页显示10
        }else{
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }
        return pageSize;
    }

}
