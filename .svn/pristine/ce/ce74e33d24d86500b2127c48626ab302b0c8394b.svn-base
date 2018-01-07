<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>中诚信征信信用风险管理平台</title>
    <link type="text/css" href="${ctx}/resources/css/base.css" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/css/common.css" rel="stylesheet" />
    <!--<script type="text/javascript" src="${ctx}/resources/js/jquery-1.12.4.js"></script>-->
    <script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
</head>
<script type="text/javascript">

</script>
<body class="body_bg" >
<div class="main">
<input type="hidden" id="isNeedWarnFlag" value="${isNeedWarnFlag }">
    <!-- header.html start -->
    <%@ include file="/WEB-INF/views/commons/topHead.jsp"%>
    <!-- header.html end -->
    <!-- center.html start -->
    <div class="main_center">
        <!-- 左侧导航.html start -->
        <%@ include file="/WEB-INF/views/commons/leftNavigation.jsp"%>
        <!-- 左侧导航.html end -->
        <!-- 右侧内容.html start -->
	     
        <!-- 右侧内容.html end -->
    </div>
    <!-- center.html end -->
    

</div>
<script type="text/javascript">
$(document).ready(function(){
	   var menuIndex1;//左侧导航栏下表
	   var menuIndex2;//头部导航栏下表
	   var herf = $(".leftsideBar_list a").eq(0).attr("data-url");
	   if(""!=herf && typeof(herf) != "undefined"){
		   menuIndex1 = 0;
		   $(".leftsideBar_list a").each(function(){
			   if($(this).attr("data-url") == herf){
				   menuIndex1 = $(".leftsideBar_list a").index($(this));
				   return false;
			   }
		   });
		   //window.location.href = herf+"?menuIndex="+menuIndex1;
	  }
	  var menuIndex2 = 0;
	  var id = $(".header_nav a").eq(menuIndex2).attr("id");
	  if(menuIndex1 >= 0 && menuIndex2 >= 0){
		  menuIndex2 = menuIndex2+"|"+id;
		  var url = herf+"?menuIndex1="+menuIndex1+"&menuIndex2="+menuIndex2;
		  var isNeedWarnFlag = $("#isNeedWarnFlag").val();
		  if(null==isNeedWarnFlag||typeof(isNeedWarnFlag)=="undefined"||""==isNeedWarnFlag||"noNeed"==isNeedWarnFlag){
			  window.location.href = url;
		  }else{
			  $.post('${ctx}/getWarnMsg', function(result) {
				  	console.log(result.warnMsgList);
				  	var warnMsgList = result.warnMsgList;
				  	if(null !=warnMsgList && warnMsgList.length>0){
				  		var warnMsghtml = "";
				  		for(var i = 0 ;i<warnMsgList.length;i++){
				  			var moduleName = warnMsgList[i].moduleName;
				  			var moduleDays = warnMsgList[i].moduleDays;
				  	        if(null==moduleName||typeof(moduleName)=="undefined"||""==moduleName){
				  	        	moduleName = "";
				  	        }
				  	      	if(null==moduleDays||typeof(moduleDays)=="undefined"||""==moduleDays){
				  	      		moduleDays = "";
				  	        }
				  	      	if(0 == moduleDays || "0" == moduleDays){
				  	      		warnMsghtml += "<li title='"+moduleName+"'>"+moduleName+"<span>今天即将到期</span></li>";
				  	      	}else{
				  	      		warnMsghtml += "<li title='"+moduleName+"'>"+moduleName+"<span>剩余"+moduleDays+"天</span></li>";
				  	      	}
				  		}
				  		$("#warnMsgHtmlContent").html(warnMsghtml);
				  		$("#urlIndexWarnMsg").val(url);
				  		myFnPopupIndexx('#popupIndexWarnMsg');
				  	}else{
				  		window.location.href = url;
				  	}
				}, 'json'); 
		  }
		  //window.location.href = herf+"?menuIndex1="+menuIndex1+"&&menuIndex2="+menuIndex2;
	  }
	})
	
function confirmWarnMsgIndexx(){
	var urlIndexWarnMsg = $("#urlIndexWarnMsg").val();
	window.location.href = urlIndexWarnMsg;
}
	//打开弹窗
function myFnPopupIndexx(obj){
	$('#layerIndexWarnMsg').show();
	$(obj).show();
	$(obj).find('a:eq(0)').click(function(){
		myFnShutDownIndexx(obj);
	})
// 	$(obj).find('.a1').click(function(){
// 		fnShutDown(obj);
// 	});
	$(obj).find('.a2').click(function(){
		myFnShutDownIndexx(obj);
	});
}
//关闭弹窗
function myFnShutDownIndexx(obj){
	$('#layerIndexWarnMsg').hide();
	$(obj).hide();
}
</script>
<div class="layerIndex" id="layerIndexWarnMsg"></div>
<div class="popup popup3" id="popupIndexWarnMsg">
	<a href="javaScript:confirmWarnMsgIndexx();" class="colse"></a>
    <h3 class="popup_title">到期提醒</h3>
    <div class="refer_box">
    	<h2>您现在使用的模块即将到期,建议您续费使用以免业务受到影响</h2>
        <ul class="refer_box_list" id="warnMsgHtmlContent" style="height:144px;left: 0;position: relative;">
        	
        </ul>
    </div>
    <div class="addBody_btn user_popup clear" style="padding-top:0;">
        <input type="hidden" id="urlIndexWarnMsg">
        <a href="javaScript:confirmWarnMsgIndexx();" class="addBody_btn_a1">确认</a>
    </div>
</div>
</body>
</html>