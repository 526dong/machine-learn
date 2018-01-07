<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--shiro 标签 --%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
request.setAttribute("ctx",basePath);
%>
<script src="${ctx}/resources/js/jquery.min.js?v=2.1.4"></script>
<script	src="${ctx}/resources/js/jquery.validate.js"></script>
<script type="text/javascript">
//全局ajax session 过期 跳转登陆	
jQuery(function($){  
	 //首先备份下jquery的ajax方法  
    var _ajax=$.ajax;  
       
    //重写jquery的ajax方法  
    $.ajax=function(opt){  
        //备份opt中error和success方法  
        var fn = {  
            error:function(XMLHttpRequest, textStatus, errorThrown){},  
            success:function(data, textStatus){}  
        }  
        if(opt.error){  
            fn.error=opt.error;  
        }  
        if(opt.success){  
            fn.success=opt.success;  
        }  
           
        //扩展增强处理  
        var_opt = $.extend(opt,{  
            error:function(XMLHttpRequest, textStatus, errorThrown){  
                //错误方法增强处理  
                fn.error(XMLHttpRequest, textStatus, errorThrown);  
            },  
            success:function(data, textStatus){  
                //成功回调方法增强处理  
                if("300" == data.user_status){
                	fnOnLineLoginPerson('#onLineLoginPerson');
            	}else{
            		fn.success(data, textStatus);  
            	}
            } 
        });  
        return _ajax(opt);  
    }; 
 
});
//显示弹窗操作
function fnOnLineLoginPerson(obj,hint,b){
	$('#onLineLoginPersonLayer').show();
	$(obj).show();
	$(obj).find('a').eq(0).click(function(){
		fnOnLineLoginPersonColse(obj);
	});
	if(hint){
		$(obj).find('p').eq(0).html(hint);
	}
	$(obj).find('.popup_btn a:eq(1)').click(function(){
		fnOnLineLoginPersonColse(obj);
	});
}
//关闭弹窗
function fnOnLineLoginPersonColse(obj){
	$('#onLineLoginPersonLayer').hide();
	$(obj).hide();
}
//退出登录
function onlinePersonLoginOut(){
	$.post('${ctx}/logout', function(result) {
        window.location.href='${ctx }/login';
	}, 'json');
}
</script>
<div class="layer" id="onLineLoginPersonLayer"></div>
<div class="popup" id="onLineLoginPerson" style="z-index: 9999">
	<a href="javaScript:;" class="colse"></a>
   <div class="password_bg"></div>
   <p class="password_info">您的账号在另外的设备登录，您已下线！</p>
   <p class="password_info">若非本人操作，请马上重新登录并修改密码！</p>
   <div class="addBody_btn popup_btn popup_btn1 clear">
       <a href="javaScript:onlinePersonLoginOut();" class="addBody_btn_a1">确认</a>
   </div>
</div>