<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>有监督测试结果</title>
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <script src="${ctx}/resources/js/echarts.min.js"></script>
    <script src="${ctx}/resources/js/main.js"></script>
</head>
<style type="text/css">
    .j-container .j-button {
        width: auto;
        padding: 0 10px;
    }
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
<img id="website-bgImg" class="website-bg website-bg-show" src="${ctx}/resources/img/bg.jpg" alt="星空万象">
<!-- //网站背景 -->
<div class="j-container">
    <%--头文件====开始--%>
    <jsp:include page="../commons/topHead.jsp"/>
    <%--头文件====结束--%>
    <div class="content">
        <%--导航栏====开始--%>
        <jsp:include page="../commons/leftNavigation.jsp"/>
        <%--导航栏====结束--%>
        <!-- //side-nav -->
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
                        <a href="${ctx}/modelsLibraryData/toModelResult?modelId=${modelId}&id=${id}&type=${type}">有监督测试结果</a>
                    </p>
                </div>
                <div class="value-analysis-btn fr">
                    <span class="j-button" onclick="toLookPreaReport()">查看预测集模型报告</span>
                    <span class="j-button" onclick="downloadFile()">下载预测结果</span>
                </div>
            </div>
            <!-- //main-header -->
            <div class="file-details">

                <div class="module-subtitle">
                    <i></i>
                    <span>概念分箱图</span>
                </div>
                <div class="linebar-mixed">
                    <div class="linebar-mixed-legend">
                        <i></i><span>样本数</span><b></b>
                        <span>实际正样本率</span><b class="b-red"></b>
                        <span>预计正样本率</span>
                    </div>
                    <div class="linebar-mixed-chart" id="linebar_mixed_chart">

                    </div>
                </div>
                <!-- //file-details-header -->
                <div class="module-subtitle">
                    <i></i>
                    <span>预测分数预览</span>
                </div>
                <div class="value-info">
                    <div class="value-info-list">
                        <table class="j-table">
                            <thead id="preaScoreTitleContent">

                            </thead>
                            <tbody id="preaScoreHtmlContent">

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- //value-info -->
            </div>
            <!-- //file-details -->
        </div>
        <!-- //main -->
    </div>
    <!-- //content -->
</div>
<!-- //j-container -->

<script>
    //初始化数据
    $(function(){
        //获取数据
        getModelTestYesDataList();
    })

    function getModelTestYesDataList() {
        $.ajax({
            type : 'POST',
            url : '${ctx}/modelsLibrary/getModelTestYesDataList',
            data : {
                "modelId" : ${modelId},
                "modelRecordId" : ${id}
            },
            success : function(data) {
                data = eval("("+data+")");
                if(200 == data.code){
                    //柱状图
                    var ModelTestYesScoreGroupList = data.ModelTestYesScoreGroupList;
                    creatModelChart(ModelTestYesScoreGroupList);
                    //预测分数概览
                    var ModelTestYesResultList = data.ModelTestYesResultList;
                    creatPreaScore(ModelTestYesResultList);
                }
            }
        })
    }

    function creatModelChart(ModelTestYesScoreGroupList) {
        if(null != ModelTestYesScoreGroupList && ModelTestYesScoreGroupList.length>0){
            var dataList1 = new Array();
            var dataList2 = new Array();
            var dataList3 = new Array();
            var dataList4 = new Array();
            for(var i = 0; i<ModelTestYesScoreGroupList.length;i++){
                if("All" != ModelTestYesScoreGroupList[i].binsScore){
                    dataList1.push((ModelTestYesScoreGroupList[i].binsScore == null ? "" : ModelTestYesScoreGroupList[i].binsScore));
                    dataList2.push((ModelTestYesScoreGroupList[i].total == null ? "" : ModelTestYesScoreGroupList[i].total));
                    dataList3.push((ModelTestYesScoreGroupList[i].badPer == null ? "" : ModelTestYesScoreGroupList[i].badPer));
                    dataList4.push((ModelTestYesScoreGroupList[i].modelPvalue == null ? "" : ModelTestYesScoreGroupList[i].modelPvalue));
                }
            }
            probabilityChart('linebar_mixed_chart',dataList1,dataList2,dataList3,dataList4);
        }
    }

    function creatPreaScore(ModelTestYesResultList) {
        var preaScoreTitleContentStr = "";
        var preaScoreHtmlContentStr = "";
        if(null != ModelTestYesResultList && ModelTestYesResultList.length>0){
            for(var i=0;i<ModelTestYesResultList.length;i++){
                preaScoreTitleContentStr += "<tr>";
                preaScoreHtmlContentStr += "<tr>";
                var list = ModelTestYesResultList[i];
                if(null != list && list.length>0){
                    for(var j=0;j<list.length;j++){
                        if(i==0){
                            preaScoreTitleContentStr += "<th>";
                            preaScoreTitleContentStr += null==list[j]?"":list[j];
                            preaScoreTitleContentStr += "</th>";
                        }else{
                            preaScoreHtmlContentStr += "<td>";
                            preaScoreHtmlContentStr += null==list[j]?"":list[j];
                            preaScoreHtmlContentStr += "</td>";
                        }
                    }
                }
                preaScoreTitleContentStr += "</tr>";
                preaScoreHtmlContentStr += "</tr>";
            }
        }
        $("#preaScoreTitleContent").html(preaScoreTitleContentStr);
        $("#preaScoreHtmlContent").html(preaScoreHtmlContentStr);
    }

    //下载模型报告
    function downloadFile() {
        //type:测试类型：0无监督测试；1有监督测试
        var type = 1;
        window.location.href="${ctx}/modelsLibrary/downloadFiles?modelRecordId=${id}&type="+type;
    }
    //查看模型报告
    function toLookPreaReport() {
        window.location.href="${ctx}/modelsLibrary/toPreModelsReportPage?modelId=${modelId}&id=${id}&type=${type}";
    }


    /**
     * 折现柱图混合图
     */
    function probabilityChart(id,dataList1,dataList2,dataList3,dataList4){
        var myChart = echarts.init(document.getElementById(id));
        var option = {
            title: {show:false},
            color:['#028de2','#fcdc6f','#e93e40'],
            grid:[{
                left: 70,bottom: 30,right: 70,top: 50
            }],
//            tooltip:{show:true,trigger:'axis'},
            tooltip: {
                trigger: 'axis',
                formatter: function (params, ticket, callback) {
                    //x轴名称
                    var name = params[0].name;
                    //图表title名称
                    var seriesName = params[0].seriesName;
                    //值
                    var value = params[0].value;
                    //图表title名称
                    var seriesName1 = params[1].seriesName;
                    //值
                    var value1 = parseFloat((params[1].value)*100).toFixed(2)+'%';
                    //图表title名称
                    var seriesName2 = params[2].seriesName;
                    //值
                    var value2 = parseFloat((params[2].value)*100).toFixed(2)+'%';
                    return name+'<br /><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + params[0].color + '"></span>' + seriesName + ': '+value+'<br /><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + params[1].color + '"></span>'+ seriesName1 + ': '+value1+'<br /><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + params[2].color + '"></span>'+ seriesName2 + ': '+value2+'<br />'
                }
            },
            legend:{show:false},
            xAxis:{
                axisLine:{lineStyle:{color: '#103763'}},
                axisTick:{lineStyle:{color: '#7c808c'}},
                axisLabel:{fontSize:12,color: '#c0d7ff'},
                data:dataList1
            },
            yAxis:[{
                axisLine:{lineStyle:{color: '#103763'}},
                axisTick:{lineStyle:{color: '#7c808c'}},
                axisLabel:{fontSize:12,color: '#c0d7ff'},
                splitLine:{show:false}
            },{
                axisLine:{lineStyle:{color: '#103763'}},
                axisTick:{lineStyle:{color: '#7c808c'}},
                axisLabel:{fontSize:12,color: '#c0d7ff'},
                splitLine:{show:false}
            }],
            series:[
                {
                    type: 'bar',
                    name: '样本数',
                    barWidth: '30%',
                    data:dataList2
                },
                {
                    type: 'line',
                    name: '实际正样本率',
                    yAxisIndex:1,
                    smooth:true,
                    symbol:'none',
                    data:dataList3
                },
                {
                    type: 'line',
                    name: '预测正样本率',
                    yAxisIndex:1,
                    smooth:true,
                    symbol:'none',
                    data:dataList4
                }
            ]
        };
        myChart.setOption(option);
    };

</script>
</body>
</html>