<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>数据文件-文件详情</title>
	<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx}/resources/js/echarts.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
</head>
<script type="text/javascript">
    // 当前显示的iv图的序号
	$(function(){
        /**
         * iv选择
         */
        $(document).on('click','.analysis-switch span',function(){
            var _this = $(this);
            var index = _this.index();
            var td = $("#ivContent tr:first").find("td:first");

            _this.addClass('active').siblings('.active').removeClass('active')
            $('.tab-panel-item').eq(index).fadeIn(200).siblings('.tab-panel-item:visible').fadeOut(200);

            if (1 == index) {
                loadRchartsAndTable(td);
            }
        });

        // 查看全部
        $(document).on('click','.check-all-value',function(){
            var _this = $(this);
            var parent = _this.parents('.sub-module');
            var target = parent.find('.value-list');
            if(target.find('tbody').find('tr').size() < 4){
                return ;
            }
            var offset;
            if(_this.hasClass('active')){
                _this.removeClass('active');
                _this.text('查看全部');
                target.css('maxHeight','160px');
            }else{
                _this.addClass('active');
                _this.text('收起');
                target.css('maxHeight','none');
                offset = target.children('.value-list-end').offset();
                $("body,html").animate({
                    scrollTop:offset.top //让body的scrollTop等于pos的top，就实现了滚动
                })
            }
        })

        // 点击iv列表，切换右侧内容
        $(document).on('click','.clickable-var',function(){
            var _this = $(this);
            var parent = _this.parent();

            $("#varName").text(_this.text());
            _this.addClass('active');
            parent.siblings('tr').find('.active').removeClass('active');
            loadRchartsAndTable(_this);
        });
	});

    function loadRchartsAndTable(_obj){
        var myChart = echarts.init(document.getElementById("iv_chart"));
        //myChart.showOnLoad();
        $.ajax({
            url:"${ctx}/modelsDataFile/varIvList",
            type:"POST",
            dataType:"json",
            data: {"varName":_obj.text(), "dataFileId": _obj.find("input").val()},
            success:function (data) {
                if (200 == data.code) {
                    var ivArr = data.data;
                    var xArr = new Array();
                    var totalArr = new Array();
                    var perArr = new Array();
                    var trHtml = "";

                    $.each(ivArr, function (i, iv) {
                        if("All"!=iv.bins){
                            xArr.push(iv.bins);
                            totalArr.push(iv.total);
                            perArr.push(iv.badPer);
                        }

                        trHtml += "<tr>";
                        trHtml += "<td>"+iv.bins+"</td>";
                        trHtml += "<td>"+iv.good+"</td>";
                        trHtml += "<td>"+iv.bad+"</td>";
                        trHtml += "<td>"+iv.total+"</td>";
                        trHtml += "<td>"+(iv.badPer == null ? "" : parseFloat((iv.badPer)*100).toFixed(2)+'%')+"</td>";
                        trHtml += "</tr>";

                    });

                    //显示echarts
                    var option = {
                        title: {show:false},
                        color:['#028de2','#fcdc6f'],
                        grid:[{
                            left: 60,bottom: 30,right: 70,top: 50
                        }],
//                        tooltip:{show:true,trigger:'axis'},
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
                                return name+'<br /><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + params[0].color + '"></span>' + seriesName + ': '+value+'<br /><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:' + params[1].color + '"></span>'+ seriesName1 + ': '+value1+'<br />'
                            }
                        },
                        legend:{show:false},
                        xAxis:{
                            axisLine:{lineStyle:{color: '#103763'}},
                            axisTick:{lineStyle:{color: '#7c808c'}},
                            axisLabel:{fontSize:12,color: '#c0d7ff'},
                            data:xArr
                        },
                        yAxis:[{
                            axisLine:{lineStyle:{color: '#103763'}},
                            axisTick:{lineStyle:{color: '#7c808c'}},
                            axisLabel:{fontSize:12,color: '#c0d7ff',margin: 60,textStyle:{align:'left'}},
                            splitLine:{show:false},

                        },{
                            axisLine:{lineStyle:{color: '#103763'}},
                            axisTick:{lineStyle:{color: '#7c808c'}},
                            axisLabel:{fontSize:12,color: '#c0d7ff'},
                            splitLine:{show:false}
                        }],
                        series:[
                            {
                                type: 'bar',
                                name: '样本数量',
                                barWidth: '30%',
                                data:totalArr
                            },
                            {
                                type: 'line',
                                name: '正样本率',
                                yAxisIndex:1,
                                symbol:'none',
                                data:perArr
                            }
                        ]
                    };
                    myChart.setOption(option);
                    //myChart.hideLoading();
                    //显示table
                    $("#binsBody").html(trHtml);
                } else {
                    jAlert("数据加载失败");
                }
            }
        });
    };
    //数据标记
    function dataMark() {
        //数据文件id
        var id = '${dataFileId}';
        window.location.href = "${ctx}/modelsDataFile/dataMark?id="+id;
    }
    //变量分析
    function dataAnalysis() {
        //数据文件id
        var id = '${dataFileId}';
        window.location.href = "${ctx}/modelsDataFile/varAnalysis?dataFileId="+id;
    }
    //下载html
    function downloadHtml() {
        //数据文件id
        var id = '${dataFileId}';
        window.location.href = "${ctx}/modelsDataFile/downloadHtml?dataFileId="+id;
    }

    //下载html
    function downloadIV() {
        //数据文件id
        var id = '${dataFileId}';
        window.location.href = "${ctx}/modelsDataFile/downloadIV?dataFileId="+id;
    }


    /**
     * 通过表头对表列进行排序
     *
     * @param sTableID
     *            要处理的表ID<table id=''>
     * @param iCol
     *            字段列id eg: 0 1 2 3 ...
     * @param sDataType
     *            该字段数据类型 int,float,date 缺省情况下当字符串处理
     */
    function  orderByName(sTableID, iCol, sDataType) {
        var  oTable = document.getElementById(sTableID);
        // var  oTBody = oTable.tBodies[0];
        var  colDataRows = oTable.rows;
        var  aTRs =  new  Array;
        for  (  var  i = 0; i < colDataRows.length; i++) {
            aTRs[i] = colDataRows[i];
        }
        if  (oTable.sortCol == iCol) {
            aTRs.reverse();
        }  else  {
            aTRs.sort(generateCompareTRs(iCol, sDataType));
        }
        var  oFragment = document.createDocumentFragment();
        for  (  var  j = 0; j < aTRs.length; j++) {
            oFragment.appendChild(aTRs[j]);
        }
        oTable.appendChild(oFragment);
        oTable.sortCol = iCol;
    }


    /**
     * 处理排序的字段类型
     *
     * @param sValue
     *            字段值 默认为字符类型即比较ASCII码
     * @param sDataType
     *            字段类型 对于date只支持格式为mm/dd/yyyy或mmmm dd,yyyy(January 12,2004)
     * @return
     */
    function  convert(sValue, sDataType) {
        switch  (sDataType) {
            case   "int" :
                if("" == sValue){
                    return  parseInt(0);
                }
                return  parseInt(sValue);
            case   "float" :
                if("" == sValue){
                    return  parseFloat(0);
                }
                return  parseFloat(sValue);
            case   "date" :
                if("" == sValue){
                    return  "";
                }
                return   new  Date(Date.parse(sValue));
            default :
                return  sValue.toString();
        }
    }


    /**
     * 比较函数生成器
     *
     * @param iCol
     *            数据行数
     * @param sDataType
     *            该行的数据类型
     * @return
     */
    function  generateCompareTRs(iCol, sDataType) {
        return   function  compareTRs(oTR1, oTR2) {
            // oTR1.cells[iCol].firstChild.nodeValue
            var a1 = getInnerText(oTR1.cells[iCol]);
            var a2 = getInnerText(oTR2.cells[iCol]);
            vValue1 = convert(getInnerText(oTR1.cells[iCol]), sDataType);
            vValue2 = convert(getInnerText(oTR2.cells[iCol]), sDataType);
            if  (vValue1 < vValue2) {
                return  -1;
            }  else   if  (vValue1 > vValue2) {
                return  1;
            }  else  {
                return  0;
            }
        };
    }
    var ie5 = (document.getElementsByTagName && document.all) ? true : false;
    function getInnerText(el) {
        if (ie5) return el.innerText; //Not needed but it is faster

        var str = "";

        var cs = el.childNodes;
        var l = cs.length;
        for (var i = 0; i < l; i++) {
            switch (cs[i].nodeType) {
                case 1: //ELEMENT_NODE
                    str += getInnerText(cs[i]);
                    break;
                case 3: //TEXT_NODE
                    str += cs[i].nodeValue;
                    break;
            }

        }

        return str;
    }
</script>
<body>
<img id="website-bgImg" class="website-bg website-bg-show" src="${ctx}/resources/img/bg.jpg" alt="星空万象">
<!-- //网站背景 -->
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
					<h3>数据文件</h3>
					<p>变量分析</p>
				</div>
                <div class="value-analysis-btn fr">
                    <span class="j-button" onclick="dataMark();">类型标记</span>
                    <span class="j-button" onclick="dataAnalysis();">变量分析</span>
                </div>
			</div>
			<!-- //main-header -->

			<div class="file-details">
				<div class="file-details-header">
					<div class="file-details-icon"></div>
					<div class="file-details-header-right">
						<div class="file-details-name-size">
							<em>${dataBaseInfo.modelDataName}</em>
							<b>样本量：${dataBaseInfo.sampleNum}</b>
							<b>维度：${dataBaseInfo.dimensionality}</b>
							<%--<b>缺失率：${dataBaseInfo.deficiencyRate}</b>--%>
                            <span class="evaluate-date"><fmt:formatDate value="${dataBaseInfo.createTime}" pattern="yyy-MM-dd" /></span>
						</div>
						<p class="file-details-desc">
							描述:
							<span>${dataBaseInfo.description}</span>
						</p>
					</div>
				</div>
				<!-- //file-details-header -->

                <div class="value-analysis">
                    <div class="tab-switch analysis-switch">
                        <span class="j-button active">变量统计</span>
                        <span class="j-button">IV值分析</span>
                        <a class="j-button fr" onclick="downloadHtml();">下载HTML</a>
                        &nbsp;&nbsp;
                        <a class="j-button fr" onclick="downloadIV();">下载IV</a>&nbsp;&nbsp;
                    </div>

                    <div class="tab-panel">
                        <div class="tab-panel-item" style="display:block;">
                            <div class="sub-module">
                                <div class="module-subtitle">
                                    <i></i>
                                    <span>离散型变量</span>
                                    <b class="j-button check-all-value fr">查看全部</b>
                                </div>
                                <div class="value-list">
                                    <table class="j-table">
                                        <thead>
                                        <tr>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',0,'float');">序号</th>
                                            <th >变量名</th>
                                            <th >数据类型</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',3,'float');">缺失个数</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',4,'float');">缺失率</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',5,'float');">取值个数</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',6,'float');">top1值</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',7,'float');">top1值占比</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',8,'float');">top2值</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',9,'float');">top2值占比</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',10,'float');">top3值</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',11,'float');">top3值占比</th>
                                            <th>取值列表</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTab',13,'float');">IV</th>
                                        </tr>
                                        </thead>
                                        <tbody id="resultTab">
                                        <c:forEach items="${varCategoryCountList}" var="varCategoryCount" varStatus="status">
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${varCategoryCount.varName}</td>
                                                <td>${varCategoryCount.type}</td>
                                                <td>${varCategoryCount.missingN}</td>
                                                <td><c:if test="${!empty varCategoryCount.missingPct}"><fmt:formatNumber value="${varCategoryCount.missingPct}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>%</c:if></td>
                                                <td>${varCategoryCount.nunique}</td>
                                                <td>${varCategoryCount.top1}</td>
                                                <td><c:if test="${!empty varCategoryCount.top1Pct}"><fmt:formatNumber value="${varCategoryCount.top1Pct}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>%</c:if></td>
                                                <td>${varCategoryCount.top2}</td>
                                                <td><c:if test="${!empty varCategoryCount.top2Pct}"><fmt:formatNumber value="${varCategoryCount.top2Pct}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>%</c:if></td>
                                                <td>${varCategoryCount.top3}</td>
                                                <td><c:if test="${!empty varCategoryCount.top3Pct}"><fmt:formatNumber value="${varCategoryCount.top3Pct}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>%</c:if></td>
                                                <td>${varCategoryCount.vList}</td>
                                                <td><fmt:formatNumber value="${varCategoryCount.iv}" pattern="##.####" minFractionDigits="4" ></fmt:formatNumber></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="value-list-end"></div>
                                </div>
                                <!-- //value-info -->
                            </div>

                            <div class="sub-module">
                                <div class="module-subtitle">
                                    <i></i>
                                    <span>连续型变量</span>
                                    <b class="j-button check-all-value fr">查看全部</b>
                                </div>
                                <div class="value-list">
                                    <table class="j-table">
                                        <thead>
                                        <tr>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',0,'float');">序号</th>
                                            <th>变量名</th>
                                            <th>数据类型</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',3,'float');">缺失个数</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',4,'float');">缺失率</th>
                                            <th >取值范围</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',6,'float');">均值</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',7,'float');">标准差</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',8,'float');">最小值</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',9,'float');">四分之一分位数</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',10,'float');">中位数</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',11,'float');">四分之三分位数</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',12,'float');">最大值</th>
                                            <th style="cursor:pointer;" onclick="orderByName('resultTabs',13,'float');">IV</th>
                                        </tr>
                                        </thead>
                                        <tbody id="resultTabs">
                                        <c:forEach items="${varNumricCountList}" var="varNumricCount" varStatus="status">
                                            <tr>
                                                <td>${status.index+1}</td>
                                                <td>${varNumricCount.varName}</td>
                                                <td>${varNumricCount.type}</td>
                                                <td>${varNumricCount.missingN}</td>
                                                <td><c:if test="${!empty varNumricCount.missingPct}">${varNumricCount.missingPct}%</c:if></td>
                                                <td>${varNumricCount.range}</td>
                                                <td><fmt:formatNumber value="${varNumricCount.mean}" pattern="##.####" minFractionDigits="4" ></fmt:formatNumber></td>
                                                <td><fmt:formatNumber value="${varNumricCount.std}" pattern="##.####" minFractionDigits="4" ></fmt:formatNumber></td>
                                                <td>${varNumricCount.min}</td>
                                                <td>${varNumricCount.oneFour}</td>
                                                <td>${varNumricCount.twoFour}</td>
                                                <td>${varNumricCount.threeFour}</td>
                                                <td>${varNumricCount.theMax}</td>
                                                <td><fmt:formatNumber value="${varNumricCount.iv}" pattern="##.####" minFractionDigits="4" ></fmt:formatNumber></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="value-list-end"></div>
                                </div>
                                <!-- //value-info -->
                            </div>
                        </div>

                        <div class="tab-panel-item">
                            <div class="clearfix" style="margin-top: 30px">
                                    <div class="iv-left fl">
                                        <table class="j-table">
                                            <thead>
                                            <tr>
                                                <th>变量名</th>
                                                <th>IV</th>
                                            </tr>
                                            </thead>
                                            <tbody id="ivContent">
                                                <c:forEach items="${varList}" var="var" varStatus="status">
                                                    <c:if test="${0 == status.index}">
                                                        <tr>
                                                            <td class="clickable-var active">${var.name}<input type="hidden" name="dataFileId" value="${var.fileId}" /></td>
                                                            <%--<td><fmt:formatNumber value="${var.iv}" pattern="##.####" minFractionDigits="4" ></fmt:formatNumber></td>--%>
                                                            <td>${var.iv}</td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${0 != status.index}">
                                                        <tr>
                                                            <td class="clickable-var">${var.name}<input type="hidden" name="dataFileId" value="${var.fileId}" /></td>
                                                            <%--<td><fmt:formatNumber value="${var.iv}" pattern="##.####" minFractionDigits="4" ></fmt:formatNumber></td>--%>
                                                            <td>${var.iv}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="iv-right active">
                                        <div id="varName" style="font-size: 14px; color: #fff;"></div>
                                        <div class="linebar-mixed">
                                            <div class="linebar-mixed-legend"><i></i><span>样本数量</span><b></b><span>正样本率</span></div>
                                            <div class="linebar-mixed-chart" id="iv_chart"></div>
                                        </div>
                                        <div style="max-height: 442px; margin-top: 30px;">
                                            <table class="j-table">
                                                <thead>
                                                <tr>
                                                    <th>类目/区间</th>
                                                    <th>负（0）样本量</th>
                                                    <th>正（1）样本量</th>
                                                    <th>样本量</th>
                                                    <th>正样本占比</th>
                                                </tr>
                                                </thead>
                                                <tbody id="binsBody"></tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                        </div>
			        </div>
                </div>
            <!-- //file-details -->
		    </div>
		<!-- //main -->
        </div>
	</div>
	<!-- //content -->
</div>
<!-- //j-container -->
</body>
</html>