<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>测试结果</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css"/>
    <script type="text/javascript" src="${ctx}/resources/js/echarts.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
</head>
<script type="text/javascript">
    $(function() {
    	modelTextResult();//获取测试结果数据
    	modelTextCount();//统计区间
    });

    //统计区间
    function modelTextCount(){
    	var id = '${id}';
    	$.ajax({
            url : '${ctx}/modelsLibraryData/modelTextCount?id='+id,
            datatype: 'json',
            contentType: 'application/json',
            type : "POST",
            async : true,
            success: function(data){
            	modelChart(data);
            }
        });
        
    }
    
	//获取测试结果数据
    function modelTextResult(){
    	var id = '${id}';
    	$.ajax({
            url : '${ctx}/modelsLibraryData/modelTextResult?id='+id,
            datatype: 'JSON',
            type : "POST",
            async : true,
            success: function(data){
				var html = "";
				var html1="";
				if(data != null ){
					for (var i = 0; i < data.length; i++) {
						var str=data[i];
						if(i==0){
							for (var j = 0; j < str.length; j++) {
								html = html + "<th>" + str[j] + "</th>";
							}
							$("#testRes").html(html);
						}else{
							html1 = html1 + "<tr>";
							for (var j = 0; j < str.length; j++) {
                                html1 = html1 + "<td>" + str[j] + "</td>";
							}
							html1 = html1 + "</tr>";
						}
					}
					$("#testResBody").html(html1);
				}
            	
               
            }
        });
        
    }
	
	
	
    
	//柱状图
	function modelChart(data){
	    var myChart = echarts.init(document.getElementById("linebar_mixed_chart_1"));
	    var option = {
	        color: ['#3398DB'],
	        tooltip : {
	            trigger: 'axis',
	            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	            }
	        },
	        grid: {
	            left: '0',
	            right: '4%',
	            top: '3%',
	            bottom: '3%',
	            containLabel: true
	        },
	        xAxis : [
	            {
	                axisLine:{
	                  lineStyle:{
	                      color: '#124173'
	                  }
	                },
	                type : 'category',
	                data : ['(0-0.1]','(0.1-0.2]','(0.2-0.3]','(0.3-0.4]','(0.4-0.5]',
	                		'(0.5-0.6]','(0.6-0.7]','(0.7-0.8]','(0.8-0.9]','(0.9-1]'],
	                axisTick: {
	                    alignWithLabel: true
	                }
	            }
	        ],
	        yAxis : [
	            {
	                axisLine:{
	                    lineStyle:{
	                        color: '#124173'
	                    }
	                },
	                type : 'value',
	                splitLine:{
	                    show:true,
	                    lineStyle: {
	                        color: '#483d8b',
	                        type: 'dashed',
	                        width: "1"
	                    }
	                }
	            }
	        ],
	        series : [
	            {
	                name:'直接访问',
	                type:'bar',
	                barWidth: '30%',
	                data:[data.section1,data.section2,data.section3,data.section4,data.section5,
	                      data.section6,data.section7,data.section8,data.section9,data.section10]
	            }
	        ]
	    };
	    myChart.setOption(option);
	};
</script>
<style type="text/css">
    .page-title p a{
        line-height: 1;
        font-size: 14px;
        color: #7684a6;
    }
    .main-header>a{
        float: right;
    }
</style>
<body>
<img id="website-bgImg" class="website-bg website-bg-show" src="${ctx}/resources/img/bg.jpg" alt="星空万象"><!-- //website-bg -->
<div class="j-container">
    <%--头文件====开始--%>
    <jsp:include page="../commons/topHead.jsp"/>
    <%--头文件====结束--%>
    <div class="content">
        <%--导航栏====开始--%>
        <jsp:include page="../commons/leftNavigation.jsp"/>
        <%--导航栏====结束--%>
        
        <div class="main">
            <div class="main-header clearfix">
                <div class="page-title">
                    <h3>模型库</h3>
                    <p>
                        <a href="${ctx}/modelsLibrary/toModelsListPage">模型列表</a>
                        >>
                        <a href="${ctx}/modelsLibrary/toModelsDetailPage?modelId=${modelId}">模型详情</a>
                        >>
                        <a href="${ctx}/modelsLibrary/toModelTestPage?modelId=${modelId}">模型测试</a>
                        >>
                        <a href="${ctx}/modelsLibraryData/toModelResult?modelId=${modelId}&id=${id}&type=${type}">无监督测试结果</a>
                    </p>
                </div>
            </div>
            <!-- //main-header -->
            <div class="model-report-details">
                <div class="model-report-contents">
                    <div class="model-report-content" style="display: block">
                        <div class="module-subtitle">
                            <i></i>
                            <span>SCORE分组详情-预测</span>
                        </div>
                        <div class="linebar-mixed">
                            <div class="linebar-mixed-chart" id="linebar_mixed_chart_1"></div>
                        </div>
                        <div class="module-subtitle">
                            <i></i>
                            <span>预测分数预览</span>
                        </div>
                        <div class="value-info">
	                    <div class="value-info-list">
	                        <table class="j-table">
	                            <thead>
	                                <tr id="testRes">
	                                </tr>
	                            </thead>
	                            <tbody id="testResBody">
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
                    </div>
                </div>
                <!-- //model-report-contents -->
                <div class="model-report-btns" id="download">
                    <a href="${ctx}/modelsLibraryData/downTextResult?modelId=${id}"><span class="j-button">下载测试结果</span></a>
                </div>

            </div>
            <!-- //model-report-details -->
        </div>
        <!-- //main -->
    </div>
    <!-- content -->
</div>
<!-- j-container -->

</body>
</html>