<%@ page language="java" import="java.util.*"
    import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<%-- <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> --%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>系统管理-角色管理</title>
<link type="text/css" href="${ctx}/resources/css/base.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/common.css" rel="stylesheet" />
<!--<script type="text/javascript" src="${ctx}/resources/js/jquery-1.12.4.js"></script>-->
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
</head>
<script type="text/javascript">
/*页面初始化*/
$(function() {
    /*列表数据*/
    findRoleList();
    fnTab();//隔行变色
    //每页显示条数
	$("#select_box0").on("click","li",function(){
		$("#next_select0 span").html($(this).attr("data-id"));
		$("#currentPage").val(1);
		findRoleList();
	})
	//页数跳转
	$("#currentNum").blur(function(){
		var currentNum=  $("#currentNum").val();
		$("#currentPage").val(currentNum);
		findRoleList();
	})
    
 
	
});

//查询
function search() {
	findRoleList();
}

/*列表数据*/
function findRoleList() {
    $.ajax({
        url : "${ctx}/role/findAll",
        type : 'POST',
        data : {
        	pageSize:$("#next_select0 span").text(),//每页展示数
        	currentPage : $("#currentPage").val(),//当前页
            keyWord : $("#keyWord").val(),//关键字搜索
        },
        async : false,
        success : function(data) {
            var htmlStr = createTable(data.list);
            $("#htmlContent").html(htmlStr);
            var pageStr = creatPage(data.total, data.pageNum,data.pages);
            $("#pageDiv").html(pageStr);
        }
    });
}


//创建用户列表
function createTable(data) {
    var htmlContent = "";
    for (var i = 0; i < data.length; i++) {
    	var name = data[i].name;
        if(null==name||typeof(name)=="undefined"||""==name){
        	name = "";
        }
        var description = data[i].description;
        var descriptionnew;
        if(null==description||typeof(description)=="undefined"||""==description){
        	descriptionnew = "";
        }else{
        	if(description.length>24){
        		descriptionnew = description.substring(0,24)+"...";
        	}else{
        		descriptionnew = description;
        	}
        }
        var createTime = data[i].createTimeDate;
        if(null==createTime||typeof(createTime)=="undefined"||""==createTime){
        	createTime = "";
        }
        htmlContent += "<tr>";
        htmlContent += "<td class='audit_td1'>" + (parseInt(i) + 1)
                + "</td>";
        htmlContent += "<td style='display:none' >" + data[i].id + "</td>";
        htmlContent += "<td >" + name + "</td>";
        htmlContent += "<td title='"+ data[i].description +"'>" + descriptionnew + "</td>";
        htmlContent += "<td>" + createTime + "</td>";
        
        htmlContent += "<td class='module_operate'>";
        htmlContent += "<shiro:hasPermission name='/role/update'>";
        htmlContent += "<a title='修改' class='operate_a10 left0' href='javaScript:update("+data[i].id+");'></a>";
        htmlContent += "</shiro:hasPermission>";
        htmlContent += "<shiro:hasPermission name='/role/selectPermissionByRoleId'>";
        htmlContent += "<a title='设置权限' class='operate_a15 left38' href='javaScript:assign("+data[i].id+");'></a>";
        htmlContent += "</shiro:hasPermission>";
        htmlContent += "<shiro:hasPermission name='/role/deleteRole'>";
        htmlContent += "<a title='删除' class='operate_a3 left72' href='javaScript:isDel("+data[i].id+");'></a>";
        htmlContent += "</shiro:hasPermission>";
        htmlContent += "</td>";
        htmlContent += "</tr>";
    }
    return htmlContent;
}

//创建分页
function creatPage(total,pageNo,totalpage){
// 	if(totalpage>1){//总页数大于一才显示分页
		$(".page_parent").show();
         //当前页数
		$("#currentNum").val(pageNo);
         //总数
		$("#sumCount").text(total);
		var starPage = pageNo-2;
		var endPage = pageNo+2;
		if(starPage<1){
			starPage=1;
			endPage=5;
			if(endPage>totalpage){
				endPage = totalpage;
			}
		}
		if(endPage>totalpage){
			endPage = totalpage;
			starPage = totalpage-4;
			if(starPage<1){
				starPage=1;
			}
		}
		var pageStr ="";
		if(pageNo-1 > 0){
	        pageStr += '<a href="javaScript:;" onclick="jumpToPage('+(pageNo-1)+');" class="triangle_left"></a>';
		}else{
	        pageStr += '<a class="triangle_left"></a>';
		}
		if(1 < starPage){
			pageStr += '<span>...</span>';
		}
		for(var i=starPage;i<=endPage;i++){
			if(pageNo==i){
				pageStr += '<a href="javaScript:;" class="active">'+pageNo+'</a>';
			}else{
				pageStr += '<a href="javaScript:;" onclick="jumpToPage('+i+');">'+i+'</a>';
			}
		}
		if(starPage > endPage){
			pageStr += '<a href="javaScript:;" onclick="jumpToPage('+pageNo+');" class="active">'+pageNo+'</a>';
		}
		if(endPage < totalpage){
			pageStr += '<span>...</span>';
		}
		if(pageNo+1 > totalpage){
			pageStr += '<a class="triangle_right"></a>';
		}else{
			pageStr += '<a class="triangle_right" href="javaScript:;" onclick="jumpToPage('+(pageNo+1)+');"></a>';
		}
		return pageStr;
}

//分页跳转
function jumpToPage(curPage){
	if(typeof(curPage) != "undefined"){
    	$("#currentPage").val(curPage);
	}else{
		$("#currentPage").val(1);
	}
	//查询
	findRoleList();
}

/*
编辑
*/
function update(obj) {
    window.location.href = "${ctx}/role/update?id="+obj;
}

/*
设置权限
*/
function assign(obj) {
    window.location.href = "${ctx}/role/selectPermissionByRoleId?id="+obj;
}


/*
删除
*/
function isDel(obj) {
	$("#roleId").val(obj);
	fnDelete("#popup","删除角色后不可恢复，确认删除？");
}

//确认删除
function confirmDelete(){
	$.ajax({
		url : "${ctx}/role/deleteRole",
		type : 'POST',
		data : {
				"id":$("#roleId").val()
			},
		success : function(data) {
			if (data == "0000") {
				fnDelete("#popupp","角色删除成功!");
				//window.location.href = "${ctx}/role/manager";
			} else if (data == "888") {
				fnDelete("#popupp","该角色下存在正常用户，无法删除！");
				//window.location.href = "${ctx}/role/manager";
			}else{
				fnDelete("#popupp","角色删除失败！");
				//window.location.href = "${ctx}/role/manager";
			}
		}
	});
}

//回车查询
function enterSearch(){
    if (event.keyCode == 13){
        event.returnValue=false;
        event.cancel = true;
      	//查询
    	findRoleList();
    }
}

</script>
<body class="body_bg" onkeydown="enterSearch();">
<div class="main">
    <!-- header.html start -->
    <%@ include file="/WEB-INF/views/commons/topHead.jsp"%>
    <!-- header.html end -->
    <!-- center.html start -->
    <div class="main_center">
        <!-- 左侧导航.html start -->
        <%@ include file="/WEB-INF/views/commons/leftNavigation.jsp"%>
        <!-- 左侧导航.html end -->
        <!-- 右侧内容.html start -->
         
         
         <div class="right_content">
            <h3 class="place_title">
                <span>当前位置：</span>
                <a href="javascript:void(0);">系统管理</a>
                <strong>/</strong>
                <a href="${ctx}/role/manager" class="active">角色管理</a>
            </h3>
            <div class="module_box">
                <div class="module_search">
<!--                     <div class="search_box fl"> -->
<!--                         <input type="text" id="keyWord" placeholder="角色名称" /> -->
<!--                         <a href="javaScript:search();" ></a>  -->
<!--                     </div> -->
                    <shiro:hasPermission name='/role/addRole'>
                    <div class="fl assets_btn">
                        <a href="${ctx}/role/addRole" class="newAddBody role_btn">创建角色</a>
                    </div> 
                    </shiro:hasPermission>
                </div>
                <div class="module_table" id="tableContent">
                    <form>
                        <table class="table_list">
                            <thead>
                                <tr>
                                    <th class="table_width50">序号</th>
                                    <th>角色名称</th>
                                    <th>角色描述</th>
                                    <th>创建时间</th>
                                    <th  class="table_width90">操作</th>
                                </tr>
                            </thead>
                            <tbody class="tbody_tr" id="htmlContent">
                                
                            </tbody>
                        </table>
                    </form>
                </div>
                <!-- 分页.html start -->
                <input id="currentPage" name="currentPage" style="display: none;" type="text">
				<div class="page_parent" style="display: none">
                	<div class="fl page_left">
                    	<p class="fl">共<span id="sumCount">0</span>条，每页显示</p>
                        <div class="page_menu select_btn fl" id="next_select0">
                        	<span>10</span>
                        </div>
                        <ul class="page_down_div select_list" id="select_box0">
                        	<li class="active" data-id="10">10</li>
                            <li data-id="20">20</li>
                            <li data-id="50">50</li>
                            <li data-id="100">100</li>
                        </ul>
                    </div>
                    <div class="fr page_right">
                    	<div class="fr">
                        	<span>第</span>
                            <input type="text" id="currentNum"name="currentNum" />
                            <span>页</span>
                        </div>
                   	  	<p class="fr page" id="pageDiv">
                   	  	
                        </p>
                    </div>
                </div>
                <!-- 分页.html end -->
            </div>
        </div>
         
         
        <!-- 右侧内容.html end -->
    </div>
    <!-- center.html end -->
    
    <!-- 遮罩层.html start -->
	<div class="layer" id="layer"></div>
	<!-- 遮罩层.html end -->
	<!-- 删除.html start -->
	<div class="popup" id="popup">
		<input type="hidden" id="roleId" />
		<a href="javaScript:;" class="colse"></a>
	    <p class="popup_word"> </p>
	    <div class="addBody_btn popup_btn clear">
	        <a href="javaScript:confirmDelete();" class="addBody_btn_a1">确认</a>
	        <a href="javaScript:fnColse('#popup');" class="addBody_btn_a2">取消</a>
	    </div>
	</div>
	<!-- 删除.html end -->
	<!-- 成功标识.html start -->
	<div class="popup" id="popupp">
		<a href="${ctx}/role/manager" class="colse"></a>
	    <p class="popup_word"> </p>
	    <div class="addBody_btn popup_btn clear" style="width:100px;">
	        <a href="${ctx}/role/manager" class="addBody_btn_a1">确认</a>
	    </div>
	</div>
	<!-- 成功标识.html end -->
</div>
</body>
</html>
