<%@ page language="java" import="java.util.*"
	import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/global.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
 	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>重置密码</title>
	<link href="${ctx}/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/resources/css/custom.css" rel="stylesheet">
    <link href="${ctx}/resources/css/animate.css" rel="stylesheet">
    <link href="${ctx}/resources/css/style.css?v=4.1.0" rel="stylesheet">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript">

function saveIt() {
	var oldpwd = $.trim($("#oldpwd").val());
	var newpwd = $.trim($("#password").val());
	var confpwd = $.trim($("#confpwd").val());
		if (oldpwd =="" && newpwd!="") {
			alert("请填写原密码！");
			return false;
		}
		if(!flag){
			return false;
		}
		if(newpwd == ""){
			alert("新密码不能为空！");
			return false;
		}
		if (newpwd != "" || confpwd != "") {
			//var reg=/^[a-zA-Z0-9]{8,18}$/; 
			var reg=/^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{8,18}$/
			if(reg.test(newpwd)==false){
				alert("密码必须8-16位,数字字母组合");
				return false;
			}
			if (newpwd != confpwd) {
				alert("两次密码不一致！");
				return false;
			}
			if (newpwd == oldpwd) {
				alert("新密码与原密码不能相同！");
				return false;
			}
		}
		$.ajax({
			type : 'post',
			url : '${ctx}/user/doReset',
			async : false,
			//dataType : 'json',
			data : {
				"id" : '${userId }',
				"password" : newpwd
			},
			success : function(data) {
				if (data.result != 1) {
					alert("密码修改失败！");
				} else {
					window.location.href = "${ctx}/user/manager";
					alert("密码修改成功！");
				}
			}
		})
	}

var flag;
$(function(){
	 $("#oldpwd").change(function() {
		var oldpwd = $.trim($("#oldpwd").val());
		$.ajax({
			type : 'POST',
			url : '${ctx}/user/check',
			data : {
				"id" : '${userId}',
				"oldpwd" : oldpwd
			},
			success : function(data) {
				if (data.result != 1) {
					flag = false;
					alert("原密码不正确");
				}else{
					flag = true;
				}
			}
		})
	})
});
</script>

</head>
<body>

				<div style="width: 30%;text-align:center;margin-left: 35%;margin-top: 10%;">
				<input type="hidden" name="id" value="${userId }"></input>
				<div class="form-group" >
					<input type="password" id="oldpwd" class="form-control"
						placeholder="请输入原密码" required="required">
				</div>
				<div class="form-group">
					<input type="password" name="password" id="password"
						class="form-control" placeholder="请输入新密码" required="required">
				</div>
				<div class="form-group">
					<input type="password" id="confpwd" class="form-control"
						placeholder="请再次输入密码" required="required">
				</div>
				<div class="form-group text-left">
					<div class="checkbox i-checks">
						<label class="no-padding"></label>

					</div>
				</div>
				<button type="button" class="btnGreen" onclick="saveIt()">确定</button>
				</div>
				
<script src="${ctx}/resources/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/resources/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>