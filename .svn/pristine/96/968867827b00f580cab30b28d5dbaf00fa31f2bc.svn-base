<%@ page language="java" import="java.util.*"
	import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>系统管理-角色管理-设置权限</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/permission/easyui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/permission/easyui/themes/icon.css"/>
<%-- <script type="text/javascript" src="${ctx}/resources/permission/jquery-2.1.1.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/permission/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/resources/permission/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/permission/easyui/easyui-lang-zh_CN.js"></script>

<link type="text/css" href="${ctx}/resources/css/base.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/common.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
</head>
<style type="text/css">

.wordCount{ position:relative; }
.wordCount .wordwrap{ position:absolute; margin-right: 8px;}
.wordCount .word{ color: red; padding: 0 4px;}
</style>
<script type="text/javascript">
$(function() {
	 var id = $("#rid").val();
	 var insId = $("#insId").val();
	 $('#tree').tree({
       url:'${ctx}/role/findPermissionTree?id='+id+'&insId='+insId,
       checkbox:true,
       onLoadSuccess: function(e, node){ 
         $('#tree').tree('select', node.target);  
       }
     });
});


function savePermission(){
  var nodes = $('#tree').tree('getChecked');
  //定义功能权限ID数组
  var third_stage_array = '';
  //定义二级菜单ID数组
  var second_stage_array='';
  //定义一级菜单ID数组
  var first_stage_array='';
  //定义权限数组
  var permission_array='';
  //遍历出选中的功能权限
  for (var i = 0; i < nodes.length; i++) {
      if (third_stage_array != ''){
      	third_stage_array += ',';
      }
      //获取功能权限ID
      third_stage_array += nodes[i].id;
     
  	//获取二级菜单节点
      var secondNode = $("#tree").tree("getParent", nodes[i].target);
      if(null!=secondNode){
      	if (second_stage_array != ''){
      		second_stage_array += ',';
          }
      	//获取二级菜单ID
      	second_stage_array += secondNode.id
      	
      	//获取一级菜单节点
      	var firstNode = $("#tree").tree("getParent", secondNode.target);
      	if(null!=firstNode){
      		if (first_stage_array != ''){
          		first_stage_array += ',';
              }
          	//获取一级菜单ID
          	first_stage_array += firstNode.id
      	}
      }
  }
  
  permission_array=first_stage_array+","+second_stage_array+","+third_stage_array;
  $("#permission_array").val(permission_array);
  var rid = $("#rid").val();
  $.ajax({
		url : '${ctx}/role/addRPermission',
		data:JSON.stringify(
   		{
   			"rid":rid,
   			"permission_array":permission_array
  		    }
   	),
		datatype: 'json',
	    contentType: 'application/json',
	    type : "POST",
	    cache: true,
	    async : true,
	   success: function(result){
	    	result=eval(result);
	    	if (result=="0000") {
	    		fnDelete("#popupp","权限分配成功!");
			}else if(result=="888"){
				fnDelete("#popupp","传参错误!");
			}else{
				fnDelete("#popupp","权限分配失败!");
			}
     } 
  });  
}
</script>
<body class="body_bg">
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
                <strong>/</strong>
                <a href="${ctx}/role/selectPermissionByRoleId?id=${permissionAddRoleID }" class="active">设置权限</a>
            </h3>
            <div class="module_box">
            	<div class="information_title">
                	<a href="${ctx}/role/manager">返回</a>
                </div>
                <div class="main_table_box jurisdiction_box">
                	<form>
                        <table class="main_table main_table2">
                            <tbody>
                                <tr>
                                    <td class="main_table_td1">
                                        <strong>角色名称：</strong>
                                    </td>
                                    <td>
                                    	${role.name }
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_table_td1" style="vertical-align:top; line-height:18px;">
                                        <strong>角色描述：</strong>
                                    </td>
                                    <td><p class="jurisdiction">${role.description }<p></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="easyui-panel" >
					<ul class="easyui-tree" id="tree" >
						
					</ul>
				</div>
                <div class="addBody_btn clear">
                	<a href="javaScript:savePermission();" class="addBody_btn_a1">保存</a>
                    <a href="${ctx}/role/manager" class="addBody_btn_a2">取消</a>
                </div>
                <form action="" id="form1" method="get">
				 	<input type="hidden" name="rid" id="rid" value="${permissionAddRoleID }"/>
				 	<input type="hidden" name="insId" id="insId" value="${role.companyId }"/>
				 	<input type="hidden" id="permission_array" name="permission_array"/>
				</form>	
            </div>
        </div>
        <!-- 右侧内容.html end -->
    </div>
    <!-- center.html end -->
    <!-- 遮罩层.html start -->
	<div class="layer" id="layer"></div>
	<!-- 遮罩层.html end -->
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