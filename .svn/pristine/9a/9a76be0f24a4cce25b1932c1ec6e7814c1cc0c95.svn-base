package com.ccx.models.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.baomidou.mybatisplus.plugins.Page;
import com.ccx.models.model.PageInfo;
import com.ccx.models.model.ShiroUser;
import com.ccx.models.util.Result;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description：基础 controller
 * @author zxr
 *
 */
public abstract class BaseController {

    Logger logger = LogManager.getLogger(BaseController.class);
    ThreadLocal<HttpServletRequest> threadLocalRequest = new InheritableThreadLocal<>();
    ThreadLocal<HttpServletResponse> threadLocalResponse = new InheritableThreadLocal<>();

    @ExceptionHandler
    public String exp(HttpServletRequest request, Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            ex.printStackTrace(pw);

            Map<String, String[]> params = request.getParameterMap();
            StringBuffer url = new StringBuffer(request.getRequestURI());
            url.append("?");
            Iterator<Map.Entry<String, String[]>> it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String[]> entry = it.next();
                url.append(entry.getKey()).append("=").append(entry.getValue()[0]).append("&");
            }
            logger.error(url.toString().substring(0,url.length()-1));
            logger.error(sw.toString());
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

	// 控制器本来就是单例，这样似乎更加合理
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    /**
     * 获取当前登录用户对象
     * @return {ShiroUser}
     */
    public ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前登录用户id
     * @return {Long}
     */
    public Long getUserId() {
        return this.getShiroUser().getId();
    }

    /**
     * 获取当前登录用户名
     * @return {String}
     */
    public String getStaffName() {
        return this.getShiroUser().getName();
    }

    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }
    
    /**
     * bean validation异常
     * 
     * 此处只是粗略的构造了错误信息，只处理了第一条错误。
     * 
     * 如果要做的完美，需要将所有的错误信息展示于页面。
     * 
     * @param result
     * @return
     */
    public Object renderError(BindingResult result) {
        FieldError error = result.getFieldError();
        StringBuilder errorMsg = new StringBuilder(100);
        errorMsg.append("$(form).find(\"[name=\\\"");
        errorMsg.append(error.getField());
        errorMsg.append("\\\"]\").closest(\"td\").prev().text() + \"，");
        errorMsg.append(error.getDefaultMessage());
        errorMsg.append("\"");
        return renderError(errorMsg.toString());
    }
    
    /**
     * ajax成功
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
    
    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }
    
    public <T> Page<T> getPage(int current, int size, String sort, String order){
        Page<T> page = new Page<T>(current, size, sort);
        if ("desc".equals(order)) {
            page.setAsc(false);
        } else {
            page.setAsc(true);
        }
        return page;
    }
    
    public <T> PageInfo pageToPageInfo(Page<T> page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setOrder(page.getOrderByField());
        return pageInfo;
    }


	/**
	 * redirect跳转
	 * @param url 目标url
	 */
	protected String redirect(String url) {
		return new StringBuilder("redirect:").append(url).toString();
	}

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        threadLocalRequest.set(request);
        threadLocalResponse.set(response);
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
