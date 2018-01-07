<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>模型库-模型列表</title>
    <link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
</head>
<script type="text/javascript">
    /*页面初始化*/
    $(function(){
        /*列表数据*/
        getData();
    });

    /*列表数据*/
    function getData(){
        $.ajax({
            url:"${ctx}/modelsLibrary/getModelsList",
            type:'POST',
            data:{
                "keyWord":$("#keyWord").val(),//关键字搜索
                "pageSize":12,//每页展示数
                "pageNum":$("#currentPage").val(),//当前页
            },
            async: false,
            success:function(data){
                if (200 == data.code) {
                    var modelList = data.data;

                    $("#dataFileContent").html("");
                    var htmlContent = createTable(modelList.list);
                    $("#modelListContent").html(htmlContent);
                    var pageStr = createPage(modelList.total, modelList.pageNum,modelList.pages);
                    $("#pageDiv").html(pageStr);
                    begin();
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
        for(var i = 0;i < data.length;i++) {
            htmlContent += "<li>";
            htmlContent += "<div class='file-card'>";
            htmlContent += "<input type='hidden' class='idSelect' value='"+data[i].id+"'/>";
           
            htmlContent += "<div class='file-chose' id='"+data[i].id+"' name='"+data[i].modelName+"'"
            	+" attr-name='"+data[i].arithmeticName+"'"
            	+" onclick='addThisId(this,"+data[i].id+",1);'></div>";
            htmlContent += "<i class='file-card-del' ' onclick='delModelInfo("+data[i].id+")'></i>";
            htmlContent += "<div class='file-info'>";
            htmlContent += "<div class='file-abstact'>";

            if (!isValueNull(data[i].modelName)) {
                htmlContent += "<em></em>";
            } else {
                var modelName = data[i].modelName;
                if(modelName.length>8){
                    modelName = modelName.substring(0,8)+"...";
                }
                htmlContent += "<em title='"+data[i].modelName+"' >"+modelName+"</em>"
            }

            htmlContent += "<i>" + (data[i].createTimeStr == null ? '' : data[i].createTimeStr) + "</i>";
            htmlContent += "<div class='file-size-date'><b>所属项目</b>";
            if (!isValueNull(data[i].programName)) {
                htmlContent += "<i></i>";
            } else {
                var programName = data[i].programName;
                if(programName.length>14){
                    programName = programName.substring(0,14)+"...";
                }
                htmlContent += "<i title='"+data[i].programName+"'>"+programName+"</i>";
            }
            htmlContent += "</div>";
            htmlContent += "<div class='file-size-date'><b>所属算法</b>";
            if (!isValueNull(data[i].arithmeticName)) {
                htmlContent += "<i></i>";
            } else {
                var arithmeticName = data[i].arithmeticName;
                if(arithmeticName.length>10){
                    arithmeticName = arithmeticName.substring(0,9)+"...";
                }
                htmlContent += "<i title='"+data[i].arithmeticName+"'>"+arithmeticName+"</i>";
            }
            htmlContent += "</div>";
            htmlContent += "</div>";
            htmlContent += "</div>";
            htmlContent += "<div class='file-desc'>";
            htmlContent += "<span class='file-desc-label'>描述:</span>";
            htmlContent += "<div class='file-desc-right'>";
            if (!isValueNull(data[i].description)) {
                htmlContent += "<p >暂无描述</p>";
            } else {
                var description = data[i].description;
                if(description.length>10){
                    description = description.substring(0,10)+"...";
                }
                htmlContent += "<p title='"+data[i].description+"'>"+description+"</p>";
            }
            htmlContent += "<a href='${ctx}/modelsLibrary/toModelsDetailPage?modelId="+data[i].id+"'>详情>></a></div>";
            htmlContent += "</div>";
            htmlContent += "</div>";
            htmlContent += "</li>";
        }
        return htmlContent;
    }

    //删除文件信息
    function delModelInfo(id) {
        //列表url
        var listUrl = "${ctx}/modelsLibrary/toModelsListPage";
        if (isValueNull(id)) {
            jConfirm('确认删除？',function(){
                $.ajax({
                    url:"${ctx}/modelsLibrary/deleteModelById",
                    type:'POST',
                    data:{
                        "modelId":id,
                    },
                    async: false,
                    success:function(data){
                        if (200 == data.code) {
                            jAlert("删除成功！", listUrl);
                        } else {
                            jAlert("删除失败！", listUrl);
                            console.error(data.msg);
                        }
                    }
                });
            });
        } else {
            jAlert("删除失败！");
            console.error("模型id为空！")
        }
    }
    //判断是否为空
    function isValueNull(obj) {
        var flag = true;
        if (obj == null || obj == "" || obj == undefined) {
            flag = false;
        }
        return flag;
    }
    //条件查询
    function searchFile() {
        getData();
    }
    //回车查询
    function enterSearch(){
        if (event.keyCode == 13){
            event.returnValueS = false;
            event.cancel = true;
            getData();
        }
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
	var idArr = new Array();//id集合
	var modelNameArr = new Array();//项目名字集合
	var programNameArr = new Array();//所属项目集合
	
	function addThisId(obj,id,type){
		var len = idArr.length;
		if(len > 4){
			jAlert("模型最多可选择5条进行对比，请检查！");
			return;
		}
		if(type == 1){
			if($(obj).attr("class")== "file-chose selected"){
				//checkbox取消选择移除当前项
		  		for (var i = 0; i < idArr.length; i++) {
					if(idArr[i] == obj.id){
						idArr.splice(i,1);
						modelNameArr.splice(i,1);
						programNameArr.splice(i,1);
					}
				}
		  	}else{
		  		idArr.push(obj.id);
		   		modelNameArr.push($(obj).attr("name"));
		   		programNameArr.push($(obj).attr("attr-name"));
		  	}
		}else if(type == 2){
			for (var i = 0; i < idArr.length; i++) {
				if(idArr[i] == id){
					idArr.splice(i,1);
					modelNameArr.splice(i,1);
					programNameArr.splice(i,1);
				}
			}
		}
		
		
	}
	
		//全选操作
	function checkedAll() {
		var len = idArr.length;
		if(len <= 5){
			if(len != 0){
				window.location.href = "${ctx}/modelsCompared/toComparedDetailsPage?idArr="
						+ idArr + "&modelNameArr=" + modelNameArr + "&programNameArr=" + programNameArr;
			} else {
				jAlert("您尚未选择，请选择模型进行对比");
			}
		} else {
			jAlert("模型最多可选择5条进行对比，请检查！");
		}

	}
</script>
<body>
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
                    <p>模型列表</p>
<%--                     <a href="${ctx }/modelsCompared/toComparedListPage">模型对比列表</a> --%>
                </div>
                <!-- //page-title -->
                <div class="query-box">
                    <div class="srch-box">
                        <i class="input-enter-icon" onclick="searchFile()"></i>
                        <input class="j-input srch-ipt" type="text" id="keyWord" placeholder="请输入模型名称/所属项目名称"
                               onkeydown="enterSearch();" />
                        <div class="srch-match">
                            <ul >
                                <li>匹配结果1</li>
                                <li>匹配结果1</li>
                                <li>匹配结果1</li>
                                <li>匹配结果1</li>
                            </ul>
                        </div>
                    </div>
                    <a class="j-button create-project-btn" href="javascript:;">
                        模型对比
                    </a>
                    <!--下拉框-->
                    <div class="model-contrast-drop">
                        <div>模型对比</div>
                        <div class="triangle"></div>
                        <div class="box">
                            <div class="item">
<!--                                 <div> -->
<!--                                     <span>申请评分模型</span> -->
<!--                                     <span class="close">×</span> -->
<!--                                 </div> -->
<!--                                 <span>xgboost</span> -->
                            </div>
                        </div>
                        <a class="j-button" href="javascript:;" onclick="checkedAll();">开始对比</a>
                        <div>
                            <span>最多对比5个模型</span>
                            <span class="empty" style="cursor:pointer;">清空</span>
                        </div>
                    </div>
                </div>
                <!-- //query-box -->
            </div>
            <!-- //main-header -->
            <div class="file-list">
                <ul id="modelListContent">

                </ul>
            </div>
            <!-- file-list -->
            <!-- 分页.html start -->
            <input type="hidden" id="currentPage" name="currentPage" value="1"/>
            <%@ include file="../commons/tabPage.jsp"%>
            <!-- 分页.html end -->
            <!-- file-list -->
        </div>
        <!-- //main -->
    </div>
    <!-- content -->
</div>
<!-- j-container -->
<script>
    <!--模型对比下拉框-->
    function begin() {
        var dropEle = $(".model-contrast-drop");
        var btn = $(".create-project-btn");
        var choseEles = $(".file-chose");
        var box = $(".model-contrast-drop .box");
        var closes = $(".model-contrast-drop .close");
        var empty = $(".empty");

        //给choseEles编号，便于增删改查

        for(var i=0;i<choseEles.length;i++){
            choseEles[i].n=i;
        }

        //点击模型对比，出现下拉框和选择框
        btn.click(function () {
            dropEle.slideToggle();
            choseEles.toggle();
        })
        //选择item
        choseEles.click(function () {
            var chosedEles = $(".file-chose.selected");
            if(chosedEles.length<5){
                $(this).toggleClass("selected");

            }else{
                $(this).removeClass("selected");
            }
            var have = $(this).attr("class")== "file-chose selected";
            if(box.children().length<=5&&have){
                var itemName = $(this).parent()[0].querySelector(".file-abstact em").innerHTML;
                var itemMsg = $(this).parent()[0].querySelector(".file-size-date i").innerHTML;
                var item = document.createElement("div");
                item.className = "item";
                item.innerHTML = "<div><span>"+itemName+
                    "</span><span class='close'>×</span></div><span>"+itemMsg+
                    "</span>";
                item.n=$(this)[0].n;
                box.append(item);
            }
            if(have==false){
                for(var i=0;i<box.children().length;i++){
                    if($(this)[0].n == box.children()[i].n){
                        $(box.children()[i]).remove();
                    }
                }
            }
            closes = $(".model-contrast-drop .close");
            closes.click(function () {
                var closeItem = $(this).parent().parent();
                closeItem.remove();
                for(var i=0;i<choseEles.length;i++){
                    if($(this).parent().parent()[0].n == choseEles[i].n){
                        $(choseEles[i]).removeClass("selected");
                        var id =$(choseEles[i]).parent()[0].querySelector(".idSelect").value;
                    	 addThisId("",id,2);
                    }
                }
            })
        })
        //清空
        empty.click(function () {
            box.empty();
            choseEles.removeClass("selected");
            idArr = new Array();//id集合清空
        	modelNameArr = new Array();//项目名字集合清空
        	programNameArr = new Array();//所属项目集合
        })
    };
    
</script>
</body>
</html>