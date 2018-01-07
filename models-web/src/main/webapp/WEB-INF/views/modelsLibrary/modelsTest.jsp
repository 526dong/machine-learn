<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>模型库-模型测试</title>
    <link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/ajaxfileupload.js"></script>
</head>
<style type="text/css">
    .model-search-zq input {
        width: 240px;
    }
</style>
<script type="text/javascript">
    /*页面初始化*/
    $(function(){
        //文件名称赋值、校验
        fileNameVal();

        /*列表数据*/
        getData();
        
        $(".algorithm-switch span").click(function() {
        	var index = $(this).index();
        	if(index == 0){
        		$("#testType").val(0);
        		$("#span1").addClass("active");
        		$("#span2").removeClass("active");
        	}else{
        		$("#span1").removeClass("active");
        		$("#span2").addClass("active");
        		$("#testType").val(1);
        	}
        	
		})
    });

    //数据文件-录入
    function submitImportDataFile() {
        //文件名称
        var fileName = $('#source_file_name').val();
        $("#file").val(fileName);
        if(null == $("#file").val() || "" == $("#file").val()){
            jAlert("请上传文件后再执行导入操作！");
            return;
        }else{
            if(!fileNameValidate(fileName)){
                jAlert("请选择后缀名为.csv或者.txt的文件！");
                return;
            }else{
                var listUrl = "${ctx}/modelsLibrary/modelsTest";
                $.ajaxFileUpload({
                    url : "${ctx}/modelsLibrary/doImportModelFile?modelId=${modelId}",
                    secureuri : false,//是否需要安全协议
                    fileElementId : "dataFile",
                    dataType : "txt",
                    type : "POST",
                    async : false,
                    success : function(data) {
                        data = eval("("+data+")");
                        if(null != data && null != data.code && "" != data.code){
                            if(888 == data.code){
                                jAlert("导入成功！");
                            }else if(101 == data.code){
                                jAlert("传参有误！");
                            }else if(102 == data.code){
                                jAlert("导入失败！");
                            }else{
                                jAlert(decodeURI(data.msg));
                            }
                        }else{
                            jAlert("文件导入失败！");
                        }
                    }
                });
            }
        }
    }
    //文件名称赋值
    function fileNameVal() {
        $(document).on("change", "#dataFile", function(){
            var _this = $(this);
            var filename = _this.get(0).files[0].name;

            //校验文件格式
            var flag = fileNameValidate(filename);

            if (flag) {
                $('#source_file_name').val(filename);
                //隐藏的文件input赋值
                $("#file").val(filename);
            } else {
                jAlert("请选择后缀名为.csv或者.txt的文件！");
                return;
            }
        });
    }
    //导入校验后缀
    function fileNameValidate(fileName) {
        var flag = false;
        var isCsv = fileName.indexOf('.csv');
        var isTxt = fileName.indexOf('.txt');
        if (isCsv > 0 || isTxt > 0) {
            flag = true;
        }
        return flag;
    }

    /*列表数据*/
    function getData(){
        $.ajax({
            url:"${ctx}/modelsLibrary/getModelTestRecordList",
            type:'POST',
            data:{
                "modelId":'${modelId}',
                "pageNum":$("#currentPage").val()//当前页
            },
            async: false,
            success:function(data){
                if (200 == data.code) {
                    var modelList = data.data;
                    loader.style.display = 'none';
                    $("#htmlContent").html("");
                    var htmlContent = createTable(modelList.list);
                    $("#htmlContent").html(htmlContent);
                    var pageStr = createPage(modelList.total, modelList.pageNum,modelList.pages);
                    $("#pageDiv").html(pageStr);
                } else {
                    jAlert(data.msg);
                    console.error(data.msg);
                }
            }
        });
    }

    //数据模型列表
    function createTable(data){
        var htmlContent = "";
        if(null == data || data.length == 0){
            htmlContent += "<tr><td colspan='5'>暂无数据</td></tr>";
        }
        for(var i = 0;i < data.length;i++) {
            htmlContent += "<tr onclick='gotoModelResult("+data[i].id+","+data[i].testType+");' style='cursor: pointer'>";
            htmlContent += "<td>"+(i+1)+"</td>";
            htmlContent += "<td>"+(null == data[i].fileName ?'':data[i].fileName)+"</td>";
            htmlContent += "<td>"+(null == data[i].fileSize ?'':data[i].fileSize)+"</td>";
            var testType = "";
            if(null != data[i].testType){
                testType = 0==data[i].testType?"无监督测试":"有监督测试";
            }
            htmlContent += "<td>"+testType+"</td>";
            htmlContent += "<td>"+(null == data[i].createTimeStr ?'':data[i].createTimeStr)+"</td>";
            htmlContent += "</tr>";
        }
        return htmlContent;
    }

    function gotoModelResult(id,type){
    	window.location.href="${ctx}/modelsLibraryData/toModelResult?id="+id+"&modelId=${modelId}&type="+type;
    }
    
    //判断是否为空
    function isValueNull(obj) {
        var flag = true;
        if (obj == null || obj == "" || obj == undefined) {
            flag = false;
        }
        return flag;
    }

    //分页跳转
    function jumpToPage(curPage){
        if(typeof(curPage) != "undefined"){
            $("#currentPage").val(curPage);
        }else{
            $("#currentPage").val(1);
        }
        //查询
        getData();
    }
</script>
<script type="text/javascript">
	//开始测试
	function startModelTest(){
        var loader = document.getElementById('loader');
        loader.style.display = 'block';
		$("#testType").val();
		$.ajax({
            url : "${ctx}/modelsLibraryData/startModelTest",
            data:{modelId:'${modelId}',testType:$("#testType").val()},
            dataType : "json",
            type : "POST",
            async : true,
            success : function(data) {
                loader.style.display = 'none';
            	if(data.status == '1'){
            		jAlert("获取数据失败");
            	}else if(data.status == '2'){
            		jAlert("检测成功");
            		getData();
            	}else if(data.status == '3'){
            		jAlert("检测失败");
            	}
            }
        });
	}

</script>
<style type="text/css">
    .line-chart-wrap{
        position: relative;
        margin: 0 150px -60px 0;
        width: 30%;
        height: 400px;
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
<div id="loader">
    <div class="cssload-loader">加载中</div>
</div>
<img id="website-bgImg" class="website-bg website-bg-show" src="${ctx}/resources/img/bg.jpg" alt="星空万象">
<!-- //website-bg -->
<div class="j-container">
    <%--头文件====开始--%>
    <jsp:include page="../commons/topHead.jsp"/>
    <!-- //header -->
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
                    </p>
                </div>
            </div>
            <!-- 增加 -->
            <div class="tab-switch algorithm-switch">
                <span class="j-button active" id="span1">无监督测试</span>
                <span class="j-button" id="span2">有监督测试</span>
                <input type="hidden" value="0" id="testType"/>
            </div>

            <div class="model-search-zq">
                <input type="text" id="source_file_name" class="j-input" readonly="readonly">
                <input id="file" type="hidden">
                <div class="browser-file">
                    <input id="dataFile" name="dataFile" class="browser-file-ipt" type="file">
                    <div class="j-button browser-file-btn">
                        <i class="browser-file-icon"></i>
                        <span>浏览</span>
                    </div>
                </div>
                <div>
                    <span class="j-button select-file-submit" onclick="submitImportDataFile();">导入</span>
                    <span class="j-button select-file-submit" onclick="startModelTest();">开始预测</span>
                </div>
            </div>


            <div class="model-report-details">
                <div class="model-report-contents">
                    <div class="model-report-content" style="display: block">

                        <div class="module-subtitle">
                            <i></i>
                            <span>测试记录</span>
                        </div>
                        <div class="score-group">
                            <table class="j-table">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>测试数据文件名称</th>
                                    <th>样本量</th>
                                    <th>测试类型</th>
                                    <th>时间</th>
                                </tr>
                                </thead>
                                <tbody id="htmlContent">

                                </tbody>
                            </table>
                        </div>
                        <!-- 分页.html start -->
                        <input type="hidden" id="currentPage" name="currentPage" value="1"/>
                        <%@ include file="../commons/tabPage.jsp"%>
                        <!-- 分页.html end -->
                    </div>
                </div>
                <!-- //model-report-contents -->
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