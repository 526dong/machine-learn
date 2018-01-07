<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="commons/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>机器学习</title>
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
</head>
<style type="text/css">
    .login-header{
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        z-index: 999;
    }
    .login-item input{
        width: 245px;
        height: 37px;
    }
    #login_bg{
        position: relative;
        min-height: 200px;
        min-width: 244px;
        background-image: url('${ctx}/resources/img/demo-1-bg.jpg');
        background-repeat: no-repeat;
        background-size: 100% 100%;
        overflow: hidden;
    }
    .login-form{
        margin: 0;
    }
    .login-box{
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
    }
    .login-item input::-webkit-input-placeholder{
        font-size: 12px;
        color: #999;
    }
    .login-item input{
        border: rgba(255,255,255,0.3) 1px solid;
        border-radius: 50px;
        background: none;
        color: #ddd;
        outline: none;
    }
    .login-item input:focus{
        border: rgba(255,255,255,0.6) 1px solid;
    }
    .login-submit{
        border: rgba(255,255,255,0.3) 1px solid;
        border-radius: 50px;
        color: #999;
        background: none;
        text-shadow: -1px 0px 1px rgba(255,255,255,.8), 1px 0px 1px rgba(0,0,0,.8);
        transition: all ease-in 0.3s;
    }
    .login-submit:hover{
        color: #ddd;
        background: none;
        border: rgba(255,255,255,0.6) 1px solid;
    }
    .login-form label i{
        background: url(${ctx}/resources/img/login_ico.png);
    }
    .login-form .username-label i{
        background-position:  -100px 0;
    }
    .login-form .password-label i{
        background-position:  -100px -28px;
    }
    .login-tips{
        position: absolute;
        left: 0;
        bottom: -20px;
        width: 100%;
        font-size: 14px;
        text-align: center;
        color: #ddd;
    }
</style>
<body onkeydown="enterlogin();">

<div class="j-container" id="login_bg" >
    <div class="login-header" style="background: #0B0E1E;">
        <a class="login-header-logo" href="${ctx}/login"></a>
    </div>
    <canvas id="login_canvas"></canvas>
    <!-- //login_canvas -->
    <div class="login-box">
        <form action="" class="login-form" autocomplete="off" id="loginForm">
            <div class="login-item">
                <label class="username-label">
                    <i></i>
                    <input type="text" autocomplete="off" value="" id="username" name="username" placeholder="请输入登录账号">
                </label>
                <p class="form-item-err" id="username_error" style="color:red;"></p>
            </div>
            <div class="login-item">
                <label class="password-label">
                    <i></i>
                    <input type="password" autocomplete="off" value="" id="password" name="password" placeholder="请输入登录密码">
                </label>
                <p class="form-item-err" id="password_error" style="color:red;"></p>
            </div>
            <div class="login-item">
                <a href="javascript:loginSubmit();" ><span class="login-submit">登录</span></a>
            </div>
        </form>
        <p class="login-tips" id="error" style="color:red;"></p>
    </div>
    <!-- //login-box -->
</div>
<!-- //j-container -->
<script src="${ctx}/resources/js/TweenLite.min.js"></script>
<script src="${ctx}/resources/js/EasePack.min.js"></script>
<script src="${ctx}/resources/js/rAF.js"></script>
<script src="${ctx}/resources/js/logon_bg.js"></script>
<script src="${ctx}/resources/js/main.js"></script>
<script type="text/javascript">
    $(function(){
        var href=location.href;
        if(href.indexOf("kickout")>0){
            onlinePersonjAlert('您的账号在其它设备登录，您已下线，请重新登录！',function(){
                $.post('${ctx}/logout', function(result) {
                    window.location.href='${ctx }/login';
                }, 'json');
            })
        }


        $("#loginForm").validate({
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username:{
                    required:"请输入登录账号"
                },
                password:{
                    required:"请输入登录密码"
                }
            },
            errorPlacement: function(error, element) {
                if(element.is("input[name=username]")){
                    error.appendTo($("#username_error"));
                }
                if(element.is("input[name=password]")){
                    error.appendTo($("#password_error"));
                }
            },
        });
    })

    /*
     登录
     */
    function loginSubmit(){
        if($("#loginForm").valid()){
            var username=$("#username").val();
            var password=$("#password").val();
            if(isNull(username)){
                $("#username_error").html("请输入用户名!");
                return;
            }
            if(isNull(password) ){
                $("#password_error").html("请输入密码!");
                return;
            }
            //检测用户状态
            var checkNameData = checkLName(username);
            if (checkNameData.code != "0000" )  {
                $("#error").html(checkNameData.msg);
                return;
            }
            $.ajax({
                url :"${ctx}/checkLogin",
                data : {
                    "username":username,
                    "password":password
                },
                type : "POST",
                dataType : 'json',
                success : function(data) {
                    console.log(data.result);
                    if (data.result == 1) {
                        window.location.href = "${ctx}/index";
                    }else{
                        $("#error").html(data.result);
                    }
                }
            });
        }
    }

    //检测用户状态
    function checkLName(username){
        var result = null;
        $.ajax({
            url :"${ctx }/validateCode/selectByNameOrMobile",
            data:{
                "unameOrMobile":username
            },
            datatype: 'json',
            type: 'post',
            async : false,
            success : function(data) {
                result = data;
            }
        });
        return result;
    }

    //判空
    function isNull(data){
        if(null == data || "" == data || "undefined" == typeof(data) || 0 == data){
            return true;
        }else{
            return false;
        }
    }

    //回车登录
    function enterlogin(){
        if (event.keyCode == 13){
            event.returnValue=false;
            event.cancel = true;
            loginSubmit();
        }
    }
</script>
</body>
</html>