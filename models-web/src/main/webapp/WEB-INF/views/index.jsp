<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--<%@ include file="commons/taglibs.jsp"%>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
    request.setAttribute("ctx",basePath);
%>
<!doctype html>
<html lang="en">
<head>
    <link rel="icon" href="${ctx}/resources/img/favicon.ico" type="image/x-icon">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>机器学习</title>
    <script src="${ctx}/resources/js/jquery-1.7.1.min.js"></script>
    <link rel="stylesheet" href="${ctx}/resources/css/swiper.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <script src="${ctx}/resources/js/main.js"></script>
    <script src="${ctx}/resources/js/swiper.min.js"></script>

</head>
<body>
<img id="website-bgImg" class="website-bg website-bg-show" src="${ctx}/resources/img/bg.jpg" alt="星空万象">
<!-- //website-bg -->
<div class="j-container">
    <div class="header" id="header">
        <%--头文件====开始--%>
        <jsp:include page="commons/topHead.jsp"/>
        <%--头文件====结束--%>
    </div>
    <!-- //header -->
    <div class="content index-two">
        <div class="swiper-container swiper-container1" id="swiper-container1">
            <div class="swiper-wrapper">
                <div class="swiper-slide swiper-slide1" data-history="slide1">
                    <div class="demo" style="cursor: pointer" onclick="toFunctionPage('开发者','')">
                        <img src="${ctx}/resources/img/in_bg4.png" alt="">
                        <div class="pop">
                            <span>开发者</span>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide swiper-slide2" data-history="slide2">
                    <div class="demo" style="cursor: pointer" onclick="toFunctionPage('0','/modelsDataFile/importDataFile')">
                        <img src="${ctx}/resources/img/in_bg1.png" alt="">
                        <div class="pop">
                            <span>数据<br>文件</span>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide swiper-slide3" data-history="slide3">
                    <div class="demo" style="cursor: pointer" onclick="toFunctionPage('2','/program/toAddProgramPage')">
                        <img src="${ctx}/resources/img/in_bg2.png" alt="">
                        <div class="pop">
                            <span>实验室</span>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide swiper-slide4" data-history="slide4">
                    <div class="demo" style="cursor: pointer" onclick="toFunctionPage('4','/modelsLibrary/toModelsListPage')">
                        <img src="${ctx}/resources/img/in_bg3.png" alt="">
                        <div class="pop">
                            <span>模型库</span>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide swiper-slide5" data-history="slide5">
                    <div class="demo" style="cursor: pointer" onclick="toFunctionPage('5','/user/toUserManagerPage')">
                        <img src="${ctx}/resources/img/in_bg5.png" alt="">
                        <div class="pop">
                            <span>个人<br>中心</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="swiper-container swiper-container2" id="swiper-container2">
            <div class="swiper-wrapper">
                <div class="swiper-slide swiper-slide1" data-history="slide1">
                    <div class="demo">
                        <div><span>|</span>开发者</div>
                        <p>
                            技术文档
                        </p>
                    </div>
                </div>
                <div class="swiper-slide swiper-slide2" data-history="slide2">
                    <div class="demo">
                        <div><span>|</span>数据文件</div>
                        <p>
                            数据导入、统计、变量分析
                            <br>
                            1、数据导入:系统支持CSV、TXT两种格式文件导入,可解析GBK、UTF-8两种编码类型，用户自定
                            义分隔符、缺失值、样本唯一索引、目标变量等。
                            <br>
                            2、变量分析:数据导入成功后，支持数据预览、变量类型识别及调整、变量分析等功能，分析维度
                            包括IV值、变量关联性、离散性和连续变量的分类统计等，并支持结果下载。
                        </p>
                    </div>
                </div>
                <div class="swiper-slide swiper-slide3" data-history="slide3">
                    <div class="demo">
                        <div><span>|</span>实验室</div>
                        <p>
                            建模项目的创建、配置、模型训练及结果展示
                            <br>
                            1、创建项目:选择需要建模的数据文件，并标记样本唯一索引、目标变量，选择要执行的算法，并进行模式配
                            置(演示、快速、精准、高可靠)
                            <br>
                            2、模型报告:项目执行完成后，可查询模型报告，包括数据概览、模型评价指标、ROC/KS曲线(训练
                            集合测试集)、SCORE分组详情/概率分箱图(训练集和测试集)、TOP20变量IV值、变量重要性排序等
                            <br>
                            3、用户可下载详细模型报告，也可将模型提取至模型库
                        </p>
                    </div>
                </div>
                <div class="swiper-slide swiper-slide4" data-history="slide4">
                    <div class="demo">
                        <div><span>|</span>模型库</div>
                        <p>
                            模型的预测及对比
                            <br>
                            1、模型对比:提供两两模型间的数据集基本信息、模型评价指标、预测概率、重要变量对比等。
                            <br>
                            2、模型预测：提供有监督测试和无监督测试两种，页面展示预测结果并支持下载
                        </p>
                    </div>
                </div>
                <div class="swiper-slide swiper-slide5" data-history="slide5">
                    <div class="demo">
                        <div><span>|</span>个人中心</div>
                        <p>
                            用户、角色管理
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //content -->
</div>
<!-- j-container -->
<script language="javascript">
    var Swiper1 = new Swiper('#swiper-container1',{
        mousewheel: true,
        initialSlide :2,
        slideToClickedSlide:true,
        effect : 'coverflow',
        slidesPerView: 4,
        centeredSlides: true,
        coverflowEffect: {
            rotate: 0,
            depth: 100,
            stretch: 0,
            modifier: 2,
            slideShadows : false
        },
    });
    var Swiper2 = new Swiper('#swiper-container2',{
        initialSlide :2,
        mousewheel: true,
        effect : 'cube',
    })
    Swiper1.controller.control = Swiper2;//Swiper1控制Swiper2，需要在Swiper2初始化后
    Swiper2.controller.control = Swiper1;//Swiper2控制Swiper1，需要在Swiper1初始化后



    function toFunctionPage(index,url){
        if(null != url && "" != url){
            window.location.href = "${ctx}"+url + "?menuIndex="+index;
        }
    }
</script>


</body>
</html>