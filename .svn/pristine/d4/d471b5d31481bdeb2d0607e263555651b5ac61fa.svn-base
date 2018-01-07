<%@ page language="java" import="java.util.*"
	import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>系统管理-角色管理-编辑角色</title>
<link type="text/css" href="${ctx}/resources/css/base.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/common.css" rel="stylesheet" />
<!--<script type="text/javascript" src="${ctx}/resources/js/jquery-1.12.4.js"></script>-->
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.validate.js"></script>
</head>
<script type="text/javascript">
$(function(){
	var num = 50;
	$("#addRole_word span").html(num - '${role.description }'.length);
	
	$.validator.addMethod("checkRoleName", function(value,element) {
		var roleName = $.trim($("#roleName").val());
		var oldRoleName = "${role.name }";
		var flag = false;
		if(oldRoleName != roleName){
			$.ajax({
				type : 'POST',
				url : '${ctx}/role/checkRoleName',
				async:false, 
				data : {
					"roleName" : roleName
				},
				success : function(data) {
					if (data.result == "false") {
						flag = false;
					}else{
						flag = true;
					}
				}
			})
		}else{
			flag = true;
		}
		return flag;
	}, "角色已存在");
	$.validator.addMethod("checkRoleName2", function(value,element) {
		var roleName = $.trim($("#roleName").val());
		//var reg=/^[a-zA-Z0-9]{8,18}$/; 
		var reg=/^[\u4e00-\u9fa5a-zA-Z]{1,30}$/
		if(reg.test(roleName)){
			return true;
		}else{
			return false;
		}
		return true;
	}, "中英文不限，30字内");
	$("#roleUpdate").validate({
	    rules: {
	      roleName: {
	      	required: true,
	      	checkRoleName: true,
	      	checkRoleName2: true
	      }
	    },
	    messages: {
	      roleName:{
              required:"请输入角色名称"
          }
	    },
	    errorPlacement: function(error, element) { 
	 		if(element.is("input[name=roleName]")){
	 			error.appendTo($("#roleName_error")); 
	 		}
	 	},
    });
})

//保存编辑的用户
function saveUpdateRole(){
// 	var flag = false;
	var description = $("#description").val();
// 	if (null == description || "" == description){
// 		$("#description_error").html("请输入角色描述");
// 		flag = false;
// 	}else{
// 		flag = true;
// 	}
// 	if($("#roleUpdate").valid() && flag){
	if($("#roleUpdate").valid()){
		var roleId=$("#roleId").val();
    	var roleName=$("#roleName").val();
    	var description=$("#description").val();
		$.ajax({
			url : "${ctx}/role/updateTo",
			type : 'POST',
			data : {
					"id":roleId,
					"name":roleName,
					"description":description
				},
			success : function(data) {
				if (data.result == "success") {
					fnDelete("#popupp","角色编辑成功!");
				} else {
					fnDelete("#popupp","角色编辑失败!");
				}
			}
		});
	}
}

//多行文本输入框剩余字数计算  
function checkMaxInput(obj, maxLen) {  
    if (obj == null || obj == undefined || obj == "") {  
        return;  
    }  
    if (maxLen == null || maxLen == undefined || maxLen == "") {  
        maxLen = 50;  
    }  
    var strResult;  
    var $obj = $(obj);   

    if (obj.value.length > maxLen) { //如果输入的字数超过了限制  
        obj.value = obj.value.substring(0, maxLen); //就去掉多余的字  
        $("#addRole_word span").html(0);//计算并显示剩余字数  
    }else {  
    	$("#addRole_word span").html(maxLen - obj.value.length);//计算并显示剩余字数  
    }  
} 
</script>
<body class="body_bg">
<div class="main">
	<!-- header.html start -->
    <%@ include file="/WEB-INF/views/commons/topHead.jsp"%>
    <!-- header.html end -->
    <!-- center.html start -->
    <div class="main_center">
    	<!-- 左侧导航.html start -->
    	<%@ include file="/WEB-INF/views/commons/leftNavigation.jsp"%>
        <!-- 左侧导航.html end -->
        <!-- 右侧内容.html start -->
        <div class="right_content">
            <h3 class="place_title">
                <span>当前位置：</span>
                <a href="javascript:void(0);">系统管理</a>
                <strong>/</strong>
                <a href="${ctx}/role/manager" class="active">角色管理</a>
                <strong>/</strong>
                <a href="${ctx }/role/update?id=${roleId }" class="active">编辑角色</a>
            </h3>
            <div class="module_box">
            	<div class="information_title">
                	<a href="${ctx}/role/manager">返回</a>
                </div>
                <div class="main_table_box">
                    <div class="main_table_box addRole">
	                    <form id="roleUpdate">
	                    	<input type="hidden" id="roleId" value="${roleId }">	
	                        <table class="main_table main_table2">
	                            <tbody>
	                                <tr>
	                                    <td class="main_table_td1">
	                                        <i>*</i><strong>角色名称</strong>
	                                    </td>
	                                    <td>
	                                        <input id="roleName" name="roleName" value="${role.name }" placeholder="请输入角色名称" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
	                                        <p class="error" id="roleName_error"></p>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td class="main_table_td1" style="vertical-align:top; line-height:50px;">
	                                        &nbsp;&nbsp;&nbsp;<strong>角色描述</strong>
	                                    </td>
	                                    <td>
	                                        <textarea id="description" name="description" placeholder="请输入角色描述" id="textarea"
	                                         onkeydown="checkMaxInput(this,50)" onkeyup="checkMaxInput(this,50)" onfocus="checkMaxInput(this,50)" onblur="checkMaxInput(this,50);">${role.description }</textarea>
	                                        <p class="error" style="top:148px;" id="description_error"></p>
	                                        <div class="addRole_word" id="addRole_word"><span>50</span>字以内</div>
	                                    </td>
	                                </tr>
	                            </tbody>
	                        </table>
	                    </form>
                    </div>
                </div>
                <div class="addBody_btn clear">
                	<a href="javaScript:saveUpdateRole();" class="addBody_btn_a1">保存</a>
                    <a href="${ctx}/role/manager" class="addBody_btn_a2">取消</a>
                </div>	
            </div>
        </div>
        <!-- 右侧内容.html end -->
    </div>
    <!-- center.html end -->
    
    <!-- 遮罩层.html start -->
	<div class="layer" id="layer"></div>
	<!-- 遮罩层.html end -->
	<!-- 成功标识.html start -->
	<div class="popup" id="popupp">
		<a href="${ctx}/role/manager" class="colse"></a>
	    <p class="popup_word"> </p>
	    <div class="addBody_btn popup_btn clear" style="width:100px;">
	        <a href="${ctx}/role/manager" class="addBody_btn_a1">确认</a>
	    </div>
	</div>
	<!-- 成功标识.html end -->
</div>
</body>
</html>