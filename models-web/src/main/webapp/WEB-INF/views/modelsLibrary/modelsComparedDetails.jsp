<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>模型库-模型详情</title>
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
	<script src="${ctx}/resources/js/main.js"></script>
	<script type="text/javascript">
	
		/*页面初始化*/
	    $(function(){
	        /*获取对比分析信息*/
	        getCombaseList();
	        //加载模型名称
	        loddingModels();
	        //加载所有模型名称供选择
	        modelNameList();
	        $("#modelNameList li").click(function(){
	        	$("#role").attr("attr-id",$(this).attr("id"));
	        	$("#role").attr("attr-name",$(this).attr("name"));
	        })
	    });
		
	    var modelNameArr = '${modelNameArr}';
		var nameList = modelNameArr.split(",");
	    var idArr = '${idArr}';
	    var idList = idArr.split(",");
		var programNameArr = '${programNameArr}';
		var programNameList = programNameArr.split(",");
		
	  	//加载模型名称
		function loddingModels(){
			
			var htmlContent = '';
			for (var i = 0; i < nameList.length; i++) {
				htmlContent += "<div class='lab-contrast-item'><div class='lab-item-content  selected'>"
				+"<div class='data1'>"+nameList[i]+"</div><div class='data2'>算法:"+programNameList[i]+"</div>"
				+"<span class='close' onclick='removeIdList("+idList[i]+");'>×</span></div></div>";
			}
			
			$("#comList").append(htmlContent);
			labBegin();
		}
	  	
		//加载所有模型名称供选择
	  	function modelNameList(){
	  		$.ajax({
	            url:"${ctx}/modelsCompared/getModelsAllList",
	            type:'POST',
	            async: false,
	            success:function(data){
	            	var html = '';
	            	for (var i = 0; i < data.length; i++) {
	            		html = html + "<li id='"+data[i].id+"' name ='"+data[i].arithmeticName+"'>"
	            			+data[i].modelName+"</li>";
					}
	            	$("#modelNameList").html(html);
	            }
	        });
	  	}
		
		//添加模型进行对比 保存按钮
		function saveModelName(){
			var len = idList.length;
			if(len > 4){
				jAlert("模型最多可选择5条进行对比，请检查！");
				return;
			}
			var modelName = $("#role").text();
			var modelId = $("#role").attr("attr-id");
			var modelProName = $("#role").attr("attr-name");
			if(null != modelName && "" != modelName && undefined != modelName){
				for (var i = 0; i < nameList.length; i++) {
					if(nameList[i] == modelName){
						jAlert("您已经选择此模型，请选择其他模型！");
						return;
					}
				}
				nameList.push(modelName);
				idList.push(modelId);
				programNameList.push(modelProName);
				$("#comBaseList").html('');
				$("#comTargetList").html('');
				$("#contractList").html('');
				$("#sortList").html('');
				int = 0;
				$("#comList").html('<span class="j-button">添加模型</span>');
				getCombaseList();//重新加载对比数据
				//加载模型名称
				loddingModels();
			}else{
				jAlert("您尚未选择模型，请选择！");
				return;
			}
			
		}
		
		
	    //移除模型
		function removeIdList(id){
			for (var i = 0; i < idList.length; i++) {
				if(idList[i] == id){
					idList.splice(i,1);
					nameList.splice(i,1);
				}
			}
			$("#comBaseList").html('');
			$("#comTargetList").html('');
			$("#contractList").html('');
			$("#sortList").html('');
			int = 0;
			getCombaseList();
		}
		
		//获取对比分析信息
		function getCombaseList(){
			
			var len = idList.length;
			for (var i = 0; i < len; i++) {
				$.ajax({
	 	            url:"${ctx}/modelsCompared/showComparedDetails",
		            type:'POST',
		            data:{
		            	idList : idList[i]
		            },
		            dataType : "json",
		            async: false,
		            success:function(data){
		            	//展示企业基本信息模块
		                showComBaseList(data.programBasicInfoMap,data.modelDateAnalyMap,
		                		data.arithmeticIdList,nameList[i],i);
		            	//初始化模型指标评价数据
		            	showTargetList(data.modelDateEvaluateIndexList,data.arithmeticIdList,nameList[i],i);
		            	//预测概率对比
		            	chanceContrast(data.scoreGroupList,data.arithmeticIdList,data.scoreGroupList1,nameList[i],i);
		            	//变量重要性排序
		            	showSortList(data.varImportanceSortList,data.arithmeticIdList,nameList[i],i);
		            	labBegin();
		            }
		        });
			}
			
		}
		
		//展示企业基本信息模块
		function showComBaseList(data,modelDateAnalyInfoList,arithmeticIdList,nameList,i){
			var htmlContent = '';
			var index = 0;
			if(null != data && "" != data){
				if(i == 0){
					htmlContent += '<div class="item item1"><div>项目名称</div><div>数据表</div><div>样本量</div>'
						+'<div>算法</div><div>模型配置</div><div>创建时间</div><div>训练用时</div><div>训练集样本量</div>'
						+'<div>测试集样本量</div><div>模型总维度</div><div>入模维度</div><div>重要变量</div><div>正负样本比</div>'
						+'<div>正样本率</div></div>';
				}
				
				 htmlContent += '<div class="item">';
                 htmlContent += '<div>'+nameList+'</div>';
                 htmlContent += '<div>'+data.dataFileName+'</div>';
                 htmlContent += '<div>'+data.dataSampleNum+'</div>';
                 htmlContent += '<div>'+data.arithmeticNames+'</div>';
                 htmlContent += '<div>'+data.modelConf+'</div>';
                 htmlContent += '<div>'+data.createTime+'</div>';
                 htmlContent += '<div>'+data.castTime+'</div>';

                 if(null != modelDateAnalyInfoList && modelDateAnalyInfoList.length>0){
                	 
                     for(var i = 0; i<arithmeticIdList.length;i++){
                         for(var j = 0; j<modelDateAnalyInfoList.length;j++){
                             if(arithmeticIdList[i] == modelDateAnalyInfoList[j].arithmeticId ){
                                 //类型：0：训练集；1：测试集
                                 if(modelDateAnalyInfoList[j].type == 0){
                                     htmlContent += '<div>'+(modelDateAnalyInfoList[j].sampleNum == null ? "" : modelDateAnalyInfoList[j].sampleNum)+'</div>';
//                                      htmlContent += '<div>'+(modelDateAnalyInfoList[j].sumDimensionality == null ? "" : modelDateAnalyInfoList[j].sumDimensionality)+'</div>';
//                                      htmlContent += '<div>'+(modelDateAnalyInfoList[j].intoDimensionality == null ? "" : modelDateAnalyInfoList[j].intoDimensionality)+'</div>';
//                                      htmlContent += '<div>'+(modelDateAnalyInfoList[j].importanceVar == null ? "" : modelDateAnalyInfoList[j].importanceVar)+'</div>';
//                                      htmlContent += '<div>'+(modelDateAnalyInfoList[j].plusMinusRate == null ? "" : parseFloat(modelDateAnalyInfoList[j].plusMinusRate).toFixed(4))+'</div>';
//                                      htmlContent += '<div>'+(modelDateAnalyInfoList[j].plusRate == null ? "" : parseFloat((modelDateAnalyInfoList[j].plusRate)*100).toFixed(2)+'%')+'</div>';
                                     index += 1;
                                 }
                                 if(modelDateAnalyInfoList[j].type == 1){
                                     htmlContent += '<div>'+(modelDateAnalyInfoList[j].sampleNum == null ? "" : modelDateAnalyInfoList[j].sampleNum)+'</div>';
                                     htmlContent += '<div>'+(modelDateAnalyInfoList[j].sumDimensionality == null ? "" : modelDateAnalyInfoList[j].sumDimensionality)+'</div>';
                                     htmlContent += '<div>'+(modelDateAnalyInfoList[j].intoDimensionality == null ? "" : modelDateAnalyInfoList[j].intoDimensionality)+'</div>';
                                     htmlContent += '<div>'+(modelDateAnalyInfoList[j].importanceVar == null ? "" : modelDateAnalyInfoList[j].importanceVar)+'</div>';
                                     htmlContent += '<div>'+(modelDateAnalyInfoList[j].plusMinusRate == null ? "" : parseFloat(modelDateAnalyInfoList[j].plusMinusRate).toFixed(4))+'</div>';
                                     htmlContent += '<div>'+(modelDateAnalyInfoList[j].plusRate == null ? "" : parseFloat((modelDateAnalyInfoList[j].plusRate)*100).toFixed(2)+'%')+'</div>';
                                     index += 1;
                                 }
                             }else{
                            	 if(index == 0 && j == modelDateAnalyInfoList.length-1){
                            		 htmlContent += '<div></div><div></div><div></div><div></div><div></div><div></div>';
                                     htmlContent += '<div></div><div></div><div></div><div></div><div></div><div></div>';
                            	 }
                             }
                         }
                     }
                 }
                 
                 htmlContent += '</div>';
			}
			$("#comBaseList").append(htmlContent);
		}
		
		/**
         * 初始化模型指标评价数据
         */
		function showTargetList(modelDateEvaluateIndexList,arithmeticIdList,nameList,i){
			var htmlContent = '';
			var index = 0;
            if(null != modelDateEvaluateIndexList && modelDateEvaluateIndexList.length>0){
            	if(i == 0){
					htmlContent += '<div class="item item1"><div>项目名称</div><div>训练集AUG</div><div>训练集KS</div><div>训练集gini</div>'
						+'<div>训练集precision</div><div>训练集recall</div><div>训练集f1-score</div><div>测试集AUC</div><div>测试集KS</div>'
						+'<div>测试集gini</div><div>测试集precision</div><div>测试集recall</div><div>测试集f1-score</div></div>';
				}
            	
           	 	htmlContent += '<div class="item">';
            	htmlContent += '<div>'+nameList+'</div>';
                for(var i = 0; i<arithmeticIdList.length;i++){
                    
                    for(var j = 0; j<modelDateEvaluateIndexList.length;j++){
                        if(arithmeticIdList[i] == modelDateEvaluateIndexList[j].arithmeticId ){
                            //类型：0：训练集；1：测试集
                            if(modelDateEvaluateIndexList[j].type == 0){
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].auc == null ? "" : parseFloat(modelDateEvaluateIndexList[j].auc).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].ks == null ? "" : parseFloat(modelDateEvaluateIndexList[j].ks).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].gini == null ? "" : parseFloat(modelDateEvaluateIndexList[j].gini).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].precisions == null ? "" : parseFloat(modelDateEvaluateIndexList[j].precisions).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].recall == null ? "" : parseFloat(modelDateEvaluateIndexList[j].recall).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].f1Score == null ? "" : parseFloat(modelDateEvaluateIndexList[j].f1Score).toFixed(4))+'</div>';
                                index += 1;
                            }
                            if(modelDateEvaluateIndexList[j].type == 1){
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].auc == null ? "" : parseFloat(modelDateEvaluateIndexList[j].auc).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].ks == null ? "" : parseFloat(modelDateEvaluateIndexList[j].ks).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].gini == null ? "" : parseFloat(modelDateEvaluateIndexList[j].gini).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].precisions == null ? "" : parseFloat(modelDateEvaluateIndexList[j].precisions).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].recall == null ? "" : parseFloat(modelDateEvaluateIndexList[j].recall).toFixed(4))+'</div>';
                                htmlContent += '<div>'+(modelDateEvaluateIndexList[j].f1Score == null ? "" : parseFloat(modelDateEvaluateIndexList[j].f1Score).toFixed(4))+'</div>';
                                index += 1;
                            }
                        }else{
	                       	 if(index == 0 && j == modelDateEvaluateIndexList.length-1){
	                    		 htmlContent += '<div></div><div></div><div></div><div></div><div></div><div></div>';
	                             htmlContent += '<div></div><div></div><div></div><div></div><div></div><div></div>';
	                    	 }
	                     }
                    }
                }
                htmlContent += '</div>';
            }else{
            	htmlContent += '<div><div></div><div></div><div></div><div></div><div></div><div></div>';
            	htmlContent += '<div></div><div></div><div></div><div></div><div></div><div></div></div>';
            }
            $("#comTargetList").append(htmlContent);
		}
		var contrastIV = 0;
		var badPerFC = 0;//训练集均方差
		var modelPvalue = 0;//标准误差
		var contrastIVCS = 0;//测试
		var badPerFCCS = 0;//测试集均方差
		var modelPvalueCS = 0;//测试集标准误差
		 /**
         * 预测概率对比
         */
         function chanceContrast(scoreGroupList,arithmeticIdList,scoreGroupList1,nameList,i){
			var htmlContent = '';
			if(i == 0){
				htmlContent += '<div class="item item1"><div>项目名称</div><div>训练集预测概率IV值</div><div>训练集预测概率均方差</div>'
					+'<div>训练集预测概率标准误差</div><div>测试集预测概率IV值</div><div>测试集预测概率均方差</div><div>测试集预测概率标准误差</div></div>';
			}
			htmlContent = htmlContent + "<div class='item'>"
			htmlContent = htmlContent + "<div>"+nameList+"</div>";
			var index = 0;
			var badPerF = 0;
			var modelPvalue1 = 0;
			var badPerArr = new Array();//训练正样本量
			var modelArr = new Array();//训练预测正样本量占比
			if(null != scoreGroupList && scoreGroupList.length>0){
				//计算预测概率
                for(var i = 0; i<arithmeticIdList.length;i++){
                    for(var j = 0; j<scoreGroupList.length;j++){
                        if(arithmeticIdList[i] == scoreGroupList[j].arithmeticId ){
                        	index = index +1;
                        	contrastIV = scoreGroupList[j].iv;
                        	badPerArr.push(scoreGroupList[j].badPer);
                        	modelArr.push(scoreGroupList[j].modelPvalue);
                        }
                    }
                }
                if(index >= 2){//数据必须有两条以上 这里有一个All的情况
	                for (var i = 0; i < index-1; i++) {
	                	badPerF = badPerF + Math.abs(badPerArr[i] - modelArr[i]);
					}
	                var badPerF2= badPerF/(index-1);//不带小数的均方差
	                badPerFC = parseFloat((badPerF/(index-1))).toFixed(4);//训练集均方差
	                for (var i = 0; i < index-1; i++) {
	                	modelPvalue1 = modelPvalue1 +Math.abs((modelArr[i]-badPerF2))*2;
					}
	                modelPvalue = parseFloat((modelPvalue1/(index-1))).toFixed(4);//标准误差
                }
                htmlContent = htmlContent + "<div>"+(contrastIV == 0 ? "":contrastIV)+"</div>";
                htmlContent = htmlContent + "<div>"+(badPerFC == 0 ? "":badPerFC)+"</div>";
                htmlContent = htmlContent + "<div>"+(modelPvalue == 0 ? "":modelPvalue)+"</div>";
                
                
                var indexCS = 0;
    			var badPerFCS = 0;
    			var modelPvalue1CS = 0;
    			var badPerArrCS = new Array();//训练正样本量
    			var modelArrCS = new Array();//训练预测正样本量占比
                //计算测试概率对比
                for(var i = 0; i<arithmeticIdList.length;i++){
                    for(var j = 0; j<scoreGroupList1.length;j++){
                        if(arithmeticIdList[i] == scoreGroupList1[j].arithmeticId ){
                        	indexCS = indexCS +1;
                        	contrastIVCS = scoreGroupList1[j].iv;
                        	badPerArrCS.push(scoreGroupList1[j].badPer);
                        	modelArrCS.push(scoreGroupList1[j].modelPvalue);
                        }
                    }
                }
                if(indexCS >= 2){//数据必须有两条以上 这里有一个All的情况
	                for (var i = 0; i < indexCS-1; i++) {
	                	badPerFCS = badPerFCS + Math.abs(badPerArrCS[i] - modelArrCS[i]);
	                	
					}
	                var badPerFCS1 = badPerFCS/(indexCS-1);//不带小数的均方差
	                badPerFCCS = parseFloat((badPerFCS/(indexCS-1))).toFixed(4);//训练集均方差
	                for (var i = 0; i < indexCS-1; i++) {
	                	modelPvalue1CS = modelPvalue1CS +Math.abs((modelArrCS[i]-badPerFCS1))*2;
					}
	                modelPvalueCS = parseFloat((modelPvalue1CS/(indexCS-1))).toFixed(4);//标准误差
                }
               
                htmlContent = htmlContent + "<div>"+(contrastIVCS == 0 ? "":contrastIVCS)+"</div>";
                htmlContent = htmlContent + "<div>"+(badPerFCCS == 0 ? "":badPerFCCS)+"</div>";
                htmlContent = htmlContent + "<div>"+(modelPvalueCS == 0 ? "":modelPvalueCS)+"</div>";
                htmlContent = htmlContent + "</div>"
            }else{
            	htmlContent = htmlContent +"<div colspan='6'>暂无数据</div>";
            }
			$("#contractList").append(htmlContent);
		}
       //序号
		var int = 0;
		
		//变量重要性排序
		function showSortList(varImportanceSortList,arithmeticIdList,nameList,i){
			var htmlContent = '';
			if(i == 0){
				htmlContent += '<div class="item item1"><div colspan="30"'
				+' style="position:absolute;top:50%;width:100%;font-size:20px;border:none;">变量名</div></div>';
			}
			htmlContent = htmlContent + "<div class='item'>"
		    if(null != varImportanceSortList && varImportanceSortList.length>0){
		      
               for(var i = 0; i<arithmeticIdList.length;i++){
                  
                   for(var j = 0; j<varImportanceSortList.length;j++){
                       if(arithmeticIdList[i] == varImportanceSortList[j].arithmeticId ){
                           int += 1;
//                            htmlContent += '<div>'+int+'</div>';
                           htmlContent += '<div>'+(varImportanceSortList[j].varName == null ? "" : varImportanceSortList[j].varName)+'</div>';
                       }
                   }
               }
              
           }
		    $("#sortList").append(htmlContent);
		}
	</script>
</head>

<body class="body_bg" >
<!-- <div id="loader"> -->
<!--     <div class="cssload-loader">加载中</div> -->
<!-- </div> -->
<img id="website-bgImg" class="website-bg website-bg-show" src="${ctx}/resources/img/bg.jpg" alt="星空万象">
<!--弹窗-->
<div class="popUp">
    <div class="popContent">
        <div class="create-account">
            <div class="form-item clearfix">
                <label for="" class="j-label">选择数据</label>
                <div class="form-item-content">
                    <div class="j-select">
                        <i class="j-select-arrow active"></i>
                        <div class="j-select-placeholder" id="role" attr-id="" attr-name=""></div>
                        <div class="j-select-options" style="display: block;">
                            <ul id ="modelNameList">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <span class="j-button okBtn" onclick="saveModelName();">保存</span>
        </div>
    </div>
</div>
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
                    <h3>实验室</h3>
                    <p>模型库>>模型对比>>对比详情</p>
                </div>
            </div>
            <!-- //main-header -->

            <div class="model-report-details">
                <div class="model-report-contents">
                    <div class="model-report-content" style="display: block">
                    	<!-- 模型名称 -->
                        <div class="lab-contrast-content" id="comList">
                        	<span class="j-button">添加模型</span>
                        </div>
                        <!--基本信息-->
                        <div class="lab-contrast-data">
                            <div><span class="drop-btn">+基本信息</span></div>
                            <div id="comBaseList">
                            </div>
                        </div>
                        <!--模型评估指标-->
                        <div class="lab-contrast-data">
                            <div><span class="drop-btn">+模型评估指标</span></div>
                            <div id="comTargetList">
                            </div>
                        </div>
                        <!--预测概率对比-->
                        <div class="lab-contrast-data">
                            <div><span class="drop-btn">+预测概率对比</span></div>
                            <div id="contractList">
                            </div>
                        </div>
                        <!--需要变量次序-->
                        <div class="lab-contrast-data">
                            <div><span class="drop-btn">+重要变量次序</span></div>
                            <div id="sortList">
                            </div>
                        </div>
                    </div>
                    <!-- //Xgbost -->
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



<script src="js/jquery-1.7.1.min.js"></script>
<script src="js/echarts.min.js"></script>
<script src="js/main.js"></script>
<script>
function labBegin() {
    var closes = $(".lab-contrast-item .close");
    var labInputs = $(".lab-contrast-item input");
    var dropBtns = $(".drop-btn");
    
    //弹窗重置，默认不显示下拉
    $(".j-select-options").hide();
    closes.click(function () {
    	console.log($(this)[0]);
        $(this).parent().next().children().val("");
        $(this).parent().removeClass("selected");
        $(this).parent().parent().empty();
    })

    //输入框输入内容，按下回车键，判断是否有值，有填入
    labInputs.keydown(function () {
        if(event.keyCode == "13"){
            //获取值
            var labValue = $(this).val();
            //判断值在数据库里存不存在
            if(labValue){
                $(this).parent().prev().addClass("selected");
                //下面两个内容须改变
                $(this).parent().prev().html("<div class=\"data1\">申请评分模型</div>\n" +
                                                "<div class=\"data2\">算法:xgboost</div><span class=\"close\">×</span>");
                closes = $(".lab-contrast-item .close");
                console.log(closes[0])
                closes.click(function () {
                	console.log($(this)[0]);
                    $(this).parent().next().children().val("");
                    $(this).parent().removeClass("selected");
                    $(this).parent().parent().empty();
                })
            }
        }
    })
    //默认需要下拉
    $(".lab-contrast-data").css({
        height:"176px"
    })
    //信息框下拉
    var have = true;
    dropBtns.click(function () {
    	var text1 = $(this).text();
    	text1 = text1.slice(1,text1.length);
        if(have){
            have = false;
            $(this).text("-"+text1);
            $(this).parent().parent().css({
                height:"auto"
            })
        }else{
            have = true;
            $(this).text("+"+text1);
            $(this).parent().parent().css({
                height:"176px"
            })
        }
    })
    
    //点击增加 出现弹窗
    var addBtn = $(".lab-contrast-content .j-button");
    var okBtn = $(".popUp .okBtn");
    var popUp = $(".popUp");
    addBtn.click(function () {
        popUp.show();
    })
    //确定按钮，获取数据并关闭弹窗
    okBtn.click(function () {
       
        popUp.hide();
    })
}

</script>
</body>
</html>