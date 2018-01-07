<%@ page language="java" import="java.util.*"
	import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>系统管理-用户管理-新增用户</title>
<link type="text/css" href="${ctx}/resources/css/base.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/common.css" rel="stylesheet" />
<%-- <!--<script type="text/javascript" src="${ctx}/resources/js/jquery-1.12.4.js"></script>--> --%>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resources/js/jquery.validate.js"></script> --%>
<style type="text/css">
.main_table input{width:200px;}
</style>
</head>
<script type="text/javascript">
$(function(){
	$("#roleId_select_box").on("click","li",function(){
		var roleId = $(this).attr("data-id");
		$("#roleId").val($(this).attr("data-id"));
	})


	$.validator.addMethod("isOnlyLoginName", function(value,element) {
		var loginName = $.trim($("#loginName").val());
		var flag = false;
		$.ajax({
			type : 'POST',
			url : '${ctx}/user/checkLoginName',
			async:false, 
			data : {
				"loginName" : loginName
			},
			success : function(data) {
				if (data.result == 0) {
					flag = false;
				}else{
					flag = true;
				}
			}
		})
		return flag;
	}, "此用户名已存在，请尝试其它用户名");
	$.validator.addMethod("checkLoginName", function(value,element) {
		var loginName = $.trim($("#loginName").val());
		//var reg=/^[a-zA-Z0-9]{8,18}$/; 
		var reg=/^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{1,18}$/
		var regg=/^[a-zA-Z]{1,18}$/
		if(reg.test(loginName) || regg.test(loginName)){
			return true;
		}else{
			return false;
		}
		return true;
	}, "18位以内,英文或英文加数字组合");
	$.validator.addMethod("checkName", function(value,element) {
		var name = $.trim($("#name").val());
		//var reg=/^[a-zA-Z0-9]{8,18}$/; 
		//var reg=/^[\u4E00-\u9FA5]{1,18}$/
		var reg=/^[\u4E00-\u9FA5]{1,18}(?:·[\u4E00-\u9FA5]{1,18})*$/;
		var regg=/^[a-zA-Z]{1,18}$/
		if(reg.test(name)==false && regg.test(name)==false){
			return false;
		}
		return true;
	}, "姓名必须为18位以内的英文或中文");
	$.validator.addMethod("isPassword2", function(value,element) {
		var password = $("#password").val();
		//var reg=/^[a-zA-Z0-9]{8,18}$/; 
		var reg=/^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{8,18}$/
		if(reg.test(password)==false){
			return false;
		}
		return true;
	}, "密码必须为8-18位,数字字母组合");
	$.validator.addMethod("passwordSame2", function(value,element) {
		var password = $("#password").val();
		var password2 = $("#password2").val();
		if (password == password2) return true;
		return false;
	}, "两次密码输入不一致");
	$.validator.addMethod("isRoleId", function(value,element) {
		var roleId = $("#roleId").val();
		if (null!=roleId&&""!=roleId&&"0000"!=roleId) return true;
		return false;
	}, "请选择角色");
	$.validator.addMethod("isMobPhone", function(value,element) {
		var passport = /(^1[34578]{1}[0-9]{9}$)/;
		return this.optional(element) || (passport.test(value));
	}, "请输入正确的手机号码");
	$.validator.addMethod("isEmail", function(value,element) {
		var passport = /^([0-9A-Za-z_\-])+(\.[0-9A-Za-z_\-]+)*@([0-9A-Za-z_\-])+((\.\w+)+)$/;
		return this.optional(element) || (passport.test(value));
	}, "请输入正确的电子邮箱");
	$("#userAdd").validate({
	    ignore: "",
	    rules: {
	      loginName: {
	      	required: true,
	      	isOnlyLoginName:true,
			checkLoginName: true
	      },
	      name:{
	    	  required: true,
	    	  checkName:true
	      },
	      password: {
	      	required: true,
	      	isPassword2:true
	      },
	      password2:{
	      	required: true,
	      	passwordSame2: true
	      },
	      roleId:{
	      	isRoleId: true
	      },
	      phone:{
	    	required: true,
	      	isMobPhone: true
	      },
	      email: {
	        isEmail: true
	      }
	    },
	    messages: {
	      loginName:{
              required:"请输入用户名称"
          },
          name:{
              required:"请输入姓名"
          },
	      password: {
	        required: "请输入密码"
	      },
	      password2: {
	        required: "请确认密码"
	      },
	      phone: {
	        required: "请输入手机号"
	      }
	    },
	    errorPlacement: function(error, element) { 
	 		if(element.is("input[name=loginName]")){
	 			error.appendTo($("#loginName_error")); 
	 		}
	 		if(element.is("input[name=name]")){
	 			error.appendTo($("#name_error")); 
	 		}
	 		if(element.is("input[name=password]")){
	 			error.appendTo($("#password_error")); 
	 		}
	 		if(element.is("input[name=password2]")){
	 			error.appendTo($("#password2_error")); 
	 		}
	 		if(element.is("input[name=roleId]")){
	 			error.appendTo($("#roleId_error")); 
	 		}
	 		if(element.is("input[name=phone]")){
	 			error.appendTo($("#phone_error")); 
	 		}
	 		if(element.is("input[name=email]")){
	 			error.appendTo($("#email_error")); 
	 		}
	 	},
    });
})

//保存新增的用户
function saveAddUser(){
	if($("#userAdd").valid()){
		var loginName =$("#loginName").val();
    	var name=$("#name").val();
    	var password=$("#password").val();
    	var roleId=$("#roleId").val();
    	var phone=$("#phone").val();
    	var email=$("#email").val();
		$.ajax({
			url : "${ctx}/user/doAdd",
			type : 'POST',
			data : {
					"loginName":loginName,
					"name":name,
					"password":password,
					"roleId":roleId,
					"phone":phone,
					"email":email
				},
			success : function(data) {
				if (data.result != 1) {
					fnDelete("#popupp","用户添加失败！");
				} else {
					fnDelete("#popupp","用户添加成功！");
				}
			}
		});
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
                <a href="${ctx}/user/manager" class="active">用户管理</a>
                <strong>/</strong>
                <a href="${ctx }/user/addUser" class="active">新增用户</a>
            </h3>
            <div class="module_box">
            	<div class="information_title">
                	<a href="${ctx}/user/manager">返回</a>
                </div>
                <div class="main_table_box">
                    <form id="userAdd">
                        <table class="main_table">
                            <tbody>
                                <tr>
                                    <td class="main_table_td1">
                                        <i>*</i><strong>用户名称</strong>
                                    </td>
                                    <td>
                                        <input id="loginName" name="loginName" value="" placeholder="请输入用户名称" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
                                        <p class="error" id="loginName_error"></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_table_td1">
                                        <i>*</i><strong>姓名</strong>
                                    </td>
                                    <td>
                                        <input id="name" name="name" value="" placeholder="请输入姓名" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
                                        <p class="error" id="name_error"></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_table_td1">
                                        <i>*</i><strong>密码</strong>
                                    </td>
                                    <td>
                                        <input id="password" name="password" value="" placeholder="请输入密码" type="password" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
                                        <p class="error" id="password_error"></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_table_td1">
                                        <i>*</i><strong>确认密码</strong>
                                    </td>
                                    <td>
                                        <input id="password2" name="password2" value="" placeholder="请确认密码" type="password" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
                                        <p class="error" id="password2_error"></p>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="main_table_td1">
                                    	<i>*</i><strong>角色</strong>
                                    </td>
                                    <td>
                                    	<div class="select_parent fl">
                                            <div class="main_select select_btn">
                                                <input type="hidden" id="roleId"  name="roleId" >
                                                <span>请选择</span>
                                            </div>
                                            <ul class="main_down select_list" id="roleId_select_box">
                                                <li class="active" data-id="0000">请选择</li>
                                                <c:forEach var="item" items="${roleList }">
													<li data-id="${item.id }">${item.name }</li>
												</c:forEach>
                                            </ul>
                                        </div>
                                        <p class="error" id="roleId_error"></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_table_td1">
                                        <i>*</i><strong>手机号码</strong>
                                    </td>
                                    <td>
                                        <input id="phone" name="phone" value="" placeholder="请输入手机号码" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
                                        <p class="error" id="phone_error"></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_table_td1">
                                        &nbsp;&nbsp;<strong>邮箱</strong>
                                    </td>
                                    <td>
                                        <input id="email" name="email" value="" placeholder="请输入邮箱" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
                                        <p class="error" id="email_error"></p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="addBody_btn clear">
                	<a href="javaScript:saveAddUser();" class="addBody_btn_a1">保存</a>
                    <a href="${ctx}/user/manager" class="addBody_btn_a2">取消</a>
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
		<a href="${ctx}/user/manager" class="colse"></a>
	    <p class="popup_word"> </p>
	    <div class="addBody_btn popup_btn clear" style="width:100px;">
	        <a href="${ctx}/user/manager" class="addBody_btn_a1">确认</a>
	    </div>
	</div>
	<!-- 成功标识.html end -->
	
</div>
</body>
</html>