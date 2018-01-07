<%@ page language="java" import="java.util.*"
         import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>500</title>
    <link type="text/css" href="${ctx}/resources/css/base.css" rel="stylesheet" />
    <link type="text/css" href="${ctx}/resources/css/common.css" rel="stylesheet" />
    <!--<script type="text/javascript" src="${ctx}/resources/js/jquery-1.12.4.js"></script>-->
    <style type="text/css">
        .login_tab .login_icon1{
            width:300px;
            height: 36px;
        }
        .login_tab .login_icon2{
            width:300px;
            height: 36px;
        }
        .login_tab .login_icon3{
            width:140px;
            height: 36px;
        }
    </style>
</head>
<body class="login_body"  onkeydown="enterlogin();">
<div class="login_box">
    <div class="login_logo fl">
<%--
        <img src="${ctx}/resources/image/login_logo.png" alt="logo" />
--%>
        <div style="color: white;font-family: \5b8b\4f53;font-size: 30px;">
            您访问的页面出错了！
        </div>

    </div>

</div>
</body>
</html>
