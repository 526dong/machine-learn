<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>有监督测试模型报告</title>
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <script src="${ctx}/resources/js/echarts.min.js"></script>
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
<body  class="test-fruit2">
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
                        >>
                        <a href="${ctx}/modelsLibrary/toPreModelsReportPage?modelId=${modelId}&id=${id}&type=${type}">有监督测试模型报告</a>
                    </p>
                </div>
            </div>
            <!-- //main-header -->
            <div class="model-report-details">
                <div class="model-report-contents">
                    <div class="model-report-content" style="display: block">

                        <div class="module-subtitle">
                            <i></i>
                            <span>基本信息</span>
                        </div>
                        <div class="score-group">
                            <table class="j-table">
                                <thead>
                                <tr>
                                    <th class="limit">名称</th>
                                    <th>训练集</th>
                                    <th>测试集</th>
                                    <th>预测集</th>
                                </tr>
                                </thead>
                                <tbody >
                                    <tr>
                                        <td>数据表</td>
                                        <td>${basicInfoMap.dataFileName1}</td>
                                        <td>${basicInfoMap.dataFileName2}</td>
                                        <td>${basicInfoMap.dataFileName3}</td>
                                    </tr>
                                    <tr>
                                        <td>样本量</td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.yangbenliang1}" groupingUsed="false"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.yangbenliang2}" groupingUsed="false"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.yangbenliang3}" groupingUsed="false"/></td>
                                    </tr>
                                    <tr>
                                        <td>算法</td>
                                        <td>${basicInfoMap.arithmeticName}</td>
                                        <td>${basicInfoMap.arithmeticName}</td>
                                        <td>${basicInfoMap.arithmeticName}</td>
                                    </tr>
                                    <tr>
                                        <td>模型配置</td>
                                        <td>${basicInfoMap.modelConf}</td>
                                        <td>${basicInfoMap.modelConf}</td>
                                        <td>${basicInfoMap.modelConf}</td>
                                    </tr>
                                    <tr>
                                        <td>模型总维度</td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zongweidu1}"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zongweidu2}"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zongweidu3}"/></td>
                                    </tr>
                                    <tr>
                                        <td>入模维度</td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.rumoweidu1}"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.rumoweidu2}"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.rumoweidu3}"/></td>
                                    </tr>
                                    <tr>
                                        <td>重要变量</td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zhongyaobianliang1}" groupingUsed="false"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zhongyaobianliang2}" groupingUsed="false"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zhongyaobianliang3}" groupingUsed="false"/></td>
                                    </tr>
                                    <tr>
                                        <td>正负样本比</td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zhengfuyangbenbi1}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zhengfuyangbenbi2}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${basicInfoMap.zhengfuyangbenbi3}" pattern="0.0000" maxFractionDigits="4"/></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- //model-report-contents -->
            </div>
            <div class="model-report-details">
                <div class="model-report-contents">
                    <div class="model-report-content" style="display: block">

                        <div class="module-subtitle">
                            <i></i>
                            <span>模型评价指标</span>
                        </div>
                        <div class="score-group">
                            <table class="j-table">
                                <thead>
                                <tr>
                                    <th class="limit">名称</th>
                                    <th>训练集</th>
                                    <th>测试集</th>
                                    <th>预测集</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>AUC</td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.auc1}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.auc2}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.auc3}" pattern="0.0000" maxFractionDigits="4"/></td>
                                    </tr>
                                    <tr>
                                        <td>KS</td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.ks1}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.ks2}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.ks3}" pattern="0.0000" maxFractionDigits="4"/></td>
                                    </tr>
                                    <tr>
                                        <td>gini</td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.gini1}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.gini2}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.gini3}" pattern="0.0000" maxFractionDigits="4"/></td>
                                    </tr>
                                    <tr>
                                        <td>precision</td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.precisions1}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.precisions2}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.precisions3}" pattern="0.0000" maxFractionDigits="4"/></td>
                                    </tr>
                                    <tr>
                                        <td>recall</td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.recall1}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.recall2}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.recall3}" pattern="0.0000" maxFractionDigits="4"/></td>
                                    </tr>
                                    <tr>
                                        <td>f1-scor</td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.f1Score1}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.f1Score2}" pattern="0.0000" maxFractionDigits="4"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.f1Score3}" pattern="0.0000" maxFractionDigits="4"/></td>
                                    </tr>
                                    <tr>
                                        <td>support</td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.support1}" maxFractionDigits="4" groupingUsed="false"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.support2}" maxFractionDigits="4" groupingUsed="false"/></td>
                                        <td><fmt:formatNumber type="number" value="${modelDateEvaluateMap.support3}" maxFractionDigits="4" groupingUsed="false"/></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- //model-report-contents -->
            </div>
            <div class="model-report-details">
                <div class="model-report-contents">
                    <div class="model-report-content" style="display: block">

                        <div class="module-subtitle">
                            <i></i>
                            <span>重要变量次序</span>
                        </div>
                        <div class="score-group">
                            <table class="j-table">
                                <thead>
                                <tr>
                                    <th class="limit">名称</th>
                                    <th>训练集</th>
                                    <th>测试集</th>
                                    <th>预测集</th>
                                </tr>
                                </thead>
                                <tbody id="varImportantHtml">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- //model-report-contents -->
            </div>

            <!-- //account-management -->
        </div>
        <!-- //main -->
    </div>
    <!-- //content -->
</div>
<!-- //j-container -->
<script type="text/javascript">
    //初始化数据
    $(function(){
        //获取模型对比数据
        getModelComparisonData();
    })

    function getModelComparisonData() {
        $.ajax({
            type : 'POST',
            url : '${ctx}/modelsLibrary/getModelComparisonData',
            data : {
                "testRecordId" : ${id}
            },
            success : function(data) {
                data = eval("("+data+")");
                if(200 == data.code){
                    //变量重要性排序
                    var varImportanceSortList = data.varImportanceSortList;
                    createVarImportanceSort(varImportanceSortList);
                }
            }
        })
    }

    function createVarImportanceSort(varImportanceSortList) {
        var htmlContent = "";
        if(null != varImportanceSortList && varImportanceSortList.length>0){
            for(var i=0;i<varImportanceSortList.length;i++){
                htmlContent += "<tr>";
                htmlContent += "<td>"+(varImportanceSortList[i].varName == null ? "" : varImportanceSortList[i].varName)+"</td>";
                htmlContent += "<td>"+(varImportanceSortList[i].pctImportance == null ? "" : parseFloat((varImportanceSortList[i].pctImportance)*100).toFixed(2)+'%')+"</td>";
                htmlContent += "<td>"+(varImportanceSortList[i].pctImportance == null ? "" : parseFloat((varImportanceSortList[i].pctImportance)*100).toFixed(2)+'%')+"</td>";
                htmlContent += "<td>"+(varImportanceSortList[i].pctImportance == null ? "" : parseFloat((varImportanceSortList[i].pctImportance)*100).toFixed(2)+'%')+"</td>";
                htmlContent += "</tr>";
            }
        }else{
            htmlContent += "<tr><td colspan='4'>暂无数据</td></tr>";
        }
        $("#varImportantHtml").html(htmlContent);
    }
</script>
</body>
</html>