<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理 </title>
<link href="${ctx}/resources/css/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/permission/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/permission/easyui/themes/icon.css"/>
<script type="text/javascript" src="${ctx}/resources/js/permission/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/permission/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/permission/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/permission/easyui/easyui-lang-zh_CN.js"></script>

<!-- bootstrap组件引用 -->
<script src="${ctx}/resources/h+/js/bootstrap.min.js?v=3.3.6"></script>
<link href="${ctx}/resources/h+/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<!-- bootstrap table组件以及中文包的引用 -->
<script src="${ctx}/resources/h+/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<link href="${ctx}/resources/h+/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<script src="${ctx}/resources/h+/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<link href="${ctx}/resources/h+/css/animate.css" rel="stylesheet">
<link href="${ctx}/resources/h+/css/font-awesome.css?v=4.4.0" rel="stylesheet">

<!-- Data Tables -->
<script src="${ctx}/resources/h+/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="${ctx}/resources/h+/js/plugins/dataTables/dataTables.bootstrap.js"></script>
<link href="${ctx}/resources/h+/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<script	src="${ctx}/resources/h+/js/plugins/layer/laydate/laydate.js"></script>

<!-- Data Tables -->
<script src="${ctx}/resources/h+/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx}/resources/h+/js/plugins/validate/messages_zh.min.js"></script>

<script src="${ctx}/resources/h+/js/plugins/sweetalert/sweetalert.min.js"></script>
<link href="${ctx}/resources/h+/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<!-- bootstrap-select -->
<script src="${ctx}/resources/h+/js/plugins/bootstrap-select/bootstrap-select.js"></script>
<link href="${ctx}/resources/h+/css/plugins/bootstrap-select/bootstrap-select.css" rel="stylesheet">


<style type="text/css">
.popup_table1{ width:100%;}
.popup_table1 td{ height:40px; line-height:40px; border:1px solid #f0f0f6;}
.popup_table1 .popup_td1{ background:#f9f9fb; width:98px; text-indent:16px;}
.popup_table1 .popup_td2{text-indent:16px;}

.wordCount{ position:relative; }
.wordCount .wordwrap{ position:absolute; right: 6px; margin-right: 8px;}
.wordCount .word{ color: red; padding: 0 4px;}
</style>
<!--      谷歌记录历史输入选中，输入框黄色修改  -->
<style type="text/css">
  	input:-webkit-autofill {
	-webkit-box-shadow: 0 0 0px 1000px white inset !important;  
 	-webkit-text-fill-color: #333;
	}
</style>
<script type="text/javascript">
/**
*资源类型
*/
function formatType(value,row,index) {
	  switch (value) {
	  case 1:
	    return '菜单';
	  case 2:
	    return '按钮';
	}
}
/**
 *格式化状态，如果为1，显示正常，为0显示禁用
 */
function formatState(value,row,index) {
  switch (value) {
	  case 1:
	    return '启用';
	  case 2:
	    return '停用';
	  case 3:
		return '删除';
  }
}
/**
 *格式化操作，在每行的操作栏显示编辑和删除操作
 */
function formatAction(value, row, index) {
  var str = '';
  if (true) {
    str += '<a href="javascript:onUpdate()" >编辑</a>';
  }
  str += ' | ';
  if (true) {
    str += '<a href="javascript:onDelete()">删除</a>';
  }
  str += ' | ';
  if (true) {
    str += '<a href="javascript:addPermission()">添加下级菜单</a>';
  }

  return str;
}

//简略描述  
function natureshortMemo(value, row, index){
	 if (value != null){  
		if(value.length>8){
			return "<span title='"+value+"'>"+value.substring(0,8)+"..."+"</span>";  
		}else{
			return "<span title='"+value+"'>"+value.substring(0,value.length)+"</span>";  
		}
    }
}


//$(function() {
	 //$('#resourcelist').treegrid({
    //   onLoadSuccess: function(row, data){ 
    //	   $.each(data, function (i, val) {
    //			   $('#resourcelist').treegrid('collapseAll', data[i].myselfId);
    //		})
     //  }  
    // });
//});
</script>
</head>
<body class="easyui-layout body_bg" data-options="fit:true,border:false" >
<div class="row" >
     <div class="col-sm-12">
          <div class="ibox float-e-margins">
		    	<div class="ibox-title">
		        	<h5>位置：系统管理<span>／</span>权限管理</h5>
		     	</div>



<div id="tt" style="padding1:1px; font-size:14px;" fit="true" border="false" >
	<table id="resourcelist" class="easyui-treegrid" 
		data-options="
			url:'${ctx}/resource/permissionListPage' ,
			method: 'post',
			idField:'myselfId',
			treeField:'permission_name',
			toolbar:'#tb',
			border:false,
			rownumbers: true">
	  <thead frozen="true">
			<tr>
				<th field="permission_name" width="250px" align="left">资源名称</th>
			</tr>
	  </thead>
	  <thead>
	    <tr>
	      <th data-options="field:'myselfId',width:120">资源ID</th>
	      <th data-options="field:'pathUrl',width:200">资源路径</th>
	      <th data-options="field:'iconUrl',width:200">图标路径</th>
	      <th data-options="field:'type',width:80,formatter:formatType">资源类型</th>
	      <th data-options="field:'sequenceNum',width:40">排序</th>
	      <th data-options="field:'parentId',width:150">上级资源ID</th>
<!-- 	      <th data-options="field:'permission_state',width:40,formatter:formatState">状态</th> -->
	      <th data-options="field:'description',width:100,formatter:natureshortMemo">描述 </th>
	      <th data-options="field:'action',width:160, formatter:formatAction">操作</th>
	    </tr>
	  </thead>
	</table>
</div>



<div class="modal" id="permissionDetail-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-header" >
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">详情</h4>
			</div>
			<div class="modal-body">
				<form>
		        	<table class="popup_table1">
		            	<tbody>
		                	<tr>
		                        <td class="popup_td1">权限名称</td>
		                        <td class="popup_td2"><span id="permissionName2"></span></td>
		                    </tr>
		                    <tr>
		                        <td class="popup_td1">权限URL</td>
		                        <td class="popup_td2"><span id="permissionUrl2"></span></td>
		                    </tr>
		                    <tr>
		                        <td class="popup_td1">创建人</td>
		                        <td class="popup_td2"><span id="creator2"></span></td>
		                    </tr>
		                    <tr>
		                        <td class="popup_td1">创建时间</td>
		                        <td class="popup_td2"><span id="createTime2"></span></td>
		                    </tr>
		                </tbody>
		            </table>
		        </form>
			</div>
			<div class="modal-footer" style="display: table; width: auto;margin-left: auto; margin-right: auto;">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>    
<div class="modal" id="permissionEditInfo-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 450px;">
		<div class="modal-content animated flipInY">
			<div class="modal-header" style="padding:10px;">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">权限编辑</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="permissionEditForm">
					 <input  type="hidden" id="permissionId"  name="permissionId" >
		        	<div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;">上级资源名称：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="parentNameEdit" name="parentNameEdit"  readonly="readonly"/>
                        	<input type="hidden" id="parentEditId" value=""/>
                        </div>
                        <div style="margin-top: 10px;text-align:left;color: #f5725a" id="parentNameEdit_error"></div>
                    </div>
                    <div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;">资源名称：</label>
                        <div class="col-sm-8">
                           <input type="text" class="form-control" id="permissionNameEdit" name="permissionNameEdit"  />
                           <i id="permissionNameEdit_error"></i>
                        </div>
                    </div>
                    <div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;">资源类型：</label>
                        <div class="col-sm-8">
                           <select class="form-control" name="typeEdit" id="typeEdit" >
                           		<option value = '1'>菜单 </option>
								<option value = '2'>按钮 </option>
                           </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;" >资源链接路径：</label>
                        <div class="col-sm-8">
                           <input type="text" class="form-control" id="pathUrlEdit" name="pathUrlEdit"  />
                            <i id="pathUrlEdit_error"></i>
                        </div>
                    </div>
                    <div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;" >资源图标：</label>
                        <div class="col-sm-8">
                           <input type="text" class="form-control" id="iconUrlEdit" name="iconUrlEdit"  />
                            <i id="iconUrlEdit_error"></i>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-sm-3 control-label" style="width: 30%;">资源描述：</label>
                        <div class="col-sm-8 wordCount">
                           <textarea class="form-control" rows="2" id="descriptionEdit" name="descriptionEdit"  aria-required="true"
                           onkeydown="checkMaxInput(this,50)" onkeyup="checkMaxInput(this,50)" onfocus="checkMaxInput(this,50)" onblur="checkMaxInput(this,50);"></textarea> 
                           	<span class="wordwrap"><var class="word" id="desEdit">50</var>/50</span>
                        </div>
                    </div>
		        </form>
			</div>
			<div class="modal-footer" style="display: table; width: auto;margin-left: auto; margin-right: auto;">
				<a href="javaScript:;" class="btn btn-primary" onclick="saveEditorPermission()">保存</a>
				<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div> 
<div class="modal" id="permissionAddInfo-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 450px;">
		<div class="modal-content animated flipInY">
			<div class="modal-header" style="padding:10px;">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" >新增权限</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="permissionAddForm">
		        	<div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;">上级资源名称：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="parentNameAdd" name="parentNameAdd" readonly="readonly" />
                        	<input type="hidden" id="parentAddId" value=""/>
                        </div>
                        <div style="margin-top: 5px;text-align:left;color: #f5725a" id="parentNameAdd_error"></div>
                    </div>
                    <div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;">资源名称：</label>
                        <div class="col-sm-8">
                           <input type="text" class="form-control" id="permissionNameAdd" name="permissionNameAdd"  />
                           <i id="permissionNameAdd_error"></i>
                        </div>
                    </div>
                    <div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;">资源类型：</label>
                        <div class="col-sm-8">
                           <select class="form-control" name="type" id="type" >
                           		<option value = '1'>菜单 </option>
								<option value = '2'>按钮 </option>
                           </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;" >资源链接路径：</label>
                        <div class="col-sm-8">
                           <input type="text" class="form-control" id="pathUrlAdd" name="pathUrlAdd"  />
                            <i id="pathUrlAdd_error"></i>
                        </div>
                    </div>
                    <div class="form-group" style="margin-bottom:5px;">
                        <label class="col-sm-3 control-label" style="width: 30%;" >资源图标：</label>
                        <div class="col-sm-8">
                           <input type="text" class="form-control" id="iconUrlAdd" name="iconUrlAdd"  />
                            <i id="iconUrlAdd_error"></i>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-sm-3 control-label" style="width: 30%;">资源描述：</label>
                        <div class="col-sm-8 wordCount">
                           <textarea class="form-control" rows="2" id="descriptionAdd" name="descriptionAdd"  aria-required="true"
                           onkeydown="checkMaxInput(this,50)" onkeyup="checkMaxInput(this,50)" onfocus="checkMaxInput(this,50)" onblur="checkMaxInput(this,50);"></textarea> 
                           	<span class="wordwrap"><var class="word" id="desAdd">50</var>/50</span>
                        </div>
                    </div>
		        </form>
			</div>
			<div class="modal-footer" style="display: table; width: auto;margin-left: auto; margin-right: auto;">
				<a href="javaScript:;" class="btn btn-primary" onclick="saveAddPermission()">保存</a>
				<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>

          </div>
     </div>
</div>
<script type="text/javascript">
$(function() {
	//表单验证
	$("#permissionAddForm").validate({
		rules: {
			permissionNameAdd:{
				required: true
			},pathUrlAdd:{
				required: true
			},iconUrlAdd:{
				required: true
			}
		},
	 	errorPlacement: function(error, element) { 
	 		if(element.is("input[name=permissionNameAdd]")){
	 			error.appendTo($("#permissionNameAdd_error")); 
	 		}
	 		if(element.is("input[name=pathUrlAdd]")){
	 			error.appendTo($("#pathUrlAdd_error")); 
	 		}
	 		if(element.is("input[name=iconUrlAdd]")){
	 			error.appendTo($("#iconUrlAdd_error")); 
	 		}
	 	},
	});
	
	//表单验证
	$("#permissionEditForm").validate({
		rules: {
			permissionNameEdit:{
				required: true
			},pathUrlEdit:{
				required: true
			},iconUrlEdit:{
				required: true
			}
		},
	 	errorPlacement: function(error, element) { 
	 		if(element.is("input[name=permissionNameEdit]")){
	 			error.appendTo($("#permissionNameEdit_error")); 
	 		}
	 		if(element.is("input[name=pathUrlEdit]")){
	 			error.appendTo($("#pathUrlEdit_error")); 
	 		}
	 		if(element.is("input[name=iconUrlEdit]")){
	 			error.appendTo($("#iconUrlEdit_error")); 
	 		}
	 	},
	});

	
	
})


//新增权限
function addPermission(){
	$("#desAdd").html(50); 
	$("#permissionNameAdd").val("");
	$("#pathUrlAdd").val("");
	$("#iconUrlAdd").val("");
	$("#descriptionAdd").val("");
	$("#parentAddId").val("");
	//验证
	$("#permissionNameAdd").rules("add",{required:true});
	$("#pathUrlAdd").rules("add",{required:false});
	$("#iconUrlAdd").rules("add",{required:false});
	$("#permissionNameAdd_error").html("");
	$("#pathUrlAdd_error").html("");
	$("#iconUrlAdd_error").html("");
	var row = $('#resourcelist').datagrid("getSelected");
	$("#parentAddId").val(row.myselfId);
	$("#parentNameAdd").val(row.permission_name);
	$('#permissionAddInfo-modal').modal();
}
//保存新增权限
function saveAddPermission(){
	if($("#permissionAddForm").valid()){
		var dtd = $.Deferred(); //新建一个延迟对象
	    $.ajax({
	    	url: "<%=path %>/resource/addpermission",
	    	 data: {permission_name:$("#permissionNameAdd").val(),type:$("#type").val(),pathUrl:$("#pathUrlAdd").val(),iconUrl:$("#iconUrlAdd").val(),
	    		 description:$("#descriptionAdd").val(),parentId:$("#parentAddId").val()},
	         type: 'post',
	         dataType: "json",
	         async: false,
	         success: function(data){
	        	 $('#permissionAddInfo-modal').modal('hide');
	        	 var data = eval(data);
	        	 if (data=="1000") {
	        		dtd.resolve(); // 改变Deferred对象的执行状态
 					swal({
		                    title: "添加成功！",
		                    text: "",
		                    type: "success"
		                }, function(){
		                	$('#resourcelist').treegrid('reload');
		                });
 				}else{
 					dtd.resolve(); // 改变Deferred对象的执行状态
 					swal({
		                    title: "添加失败！",
		                    text: "",
		                    type: "error"
		                }, function(){
		                	$('#resourcelist').treegrid('reload');
		                });
 				}
	         }
	    });
	}
}

//编辑权限
function onUpdate(){
	var rows = $('#resourcelist').datagrid("getSelected");
	if(rows.myselfId == "manage-0"){
		swal({
            title: "不能编辑根权限",
            confirmButtonText: "确定"
        });
	}else{
		$("#permissionNameEdit").val("");
		$("#pathUrlEdit").val("");
		$("#iconUrlEdit").val("");
		$("#descriptionEdit").val("");
		$("#parentEditId").val("");
		//验证
		$("#permissionNameEdit").rules("add",{required:true});
		$("#pathUrlEdit").rules("add",{required:false});
		$("#iconUrlEdit").rules("add",{required:false});
		$("#permissionNameEdit_error").html("");
		$("#pathUrlEdit_error").html("");
		$("#iconUrlEdit_error").html("");
		var row = $('#resourcelist').datagrid("getSelected");
		var parentRow = $('#resourcelist').treegrid('getParent', row.myselfId);
		$("#parentEditId").val(row.parentId);
		$("#parentNameEdit").val(parentRow.permission_name);
		$("#permissionNameEdit").val(row.permission_name);
		$("#typeEdit").val(row.type);
		$("#pathUrlEdit").val(row.pathUrl);
		$("#iconUrlEdit").val(row.iconUrl);
		$("#descriptionEdit").val(row.description);
		var _val = row.description;
		$("#desEdit").html(50 - _val.length); 
		$('#permissionEditInfo-modal').modal();
	}
}
//触发编辑方法
function saveEditorPermission(){
	var row = $('#resourcelist').datagrid("getSelected");
	if($("#permissionEditForm").valid()){
	
    	var dtd = $.Deferred(); //新建一个延迟对象
	    $.ajax({
	    	 url: "<%=path %>/resource/saveEditPermission",
	    	 data: {id:row.id,permission_name:$("#permissionNameEdit").val(),type:$("#typeEdit").val(),pathUrl:$("#pathUrlEdit").val(),iconUrl:$("#iconUrlEdit").val(),
                 description:$("#descriptionEdit").val(),parentId:$("#parentEditId").val()},
	         type: 'post',
	         dataType: "json",
	         async: false,
	         success: function(data){
	        	 $('#permissionEditInfo-modal').modal('hide');
	        	 var data = eval(data);
	        	 if (data=="1000") {
	        		dtd.resolve(); // 改变Deferred对象的执行状态
 					swal({
		                    title: "编辑成功！",
		                    text: "",
		                    type: "success"
		                }, function(){
		                	$('#resourcelist').treegrid('reload');
		                });
 				}else{
 					dtd.resolve(); // 改变Deferred对象的执行状态
 					swal({
		                    title: "编辑失败！",
		                    text: "",
		                    type: "error"
		                }, function(){
		                	$('#resourcelist').treegrid('reload');
		                });
 				}
	         }
	    });
    }
}

//删除
function onDelete(){
	var rows = $('#resourcelist').datagrid("getSelected");
	if(rows.myselfId == "manage-0"){
		swal({
            title: "不能删除根权限",
            confirmButtonText: "确定"
        });
	}else{
		swal({
	        title: "你确认删除吗?",
	        text: "",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        cancelButtonText: "取消",
	        confirmButtonText: "确定",
	        closeOnConfirm: false
	    }, function () {
	    	var dtd = $.Deferred(); //新建一个延迟对象
			var row = $('#resourcelist').datagrid("getSelected");
	    	$.ajax({
	    		url: "<%=path %>/resource/modifypermissionState",
		        data: {myselfId:row.myselfId,permission_state:3},
			    datatype: 'json',  
			    type: 'post',
			    cache: true,
			    async : true,
				success : function(data) {
					var data = eval(data);
					if (data=="1000") {
						swal({
		                    title: "删除成功！",
		                    text: "",
		                    type: "success",
		                    confirmButtonText: "确定"
		                }, function(){
		                	dtd.resolve(); // 改变Deferred对象的执行状态
			    			$('#resourcelist').treegrid('reload');
		                });
					}else{
						swal({
		                    title: "删除失败！",
		                    text: "",
		                    type: "error",
		                    confirmButtonText: "确定"
		                }, function(){
		                	dtd.resolve(); // 改变Deferred对象的执行状态
			    			$('#resourcelist').treegrid('reload');
		                });
					}
				}
			});
	    });
	}
}



//多行文本输入框剩余字数计算  
function checkMaxInput(obj, maxLen) {  
    if (obj == null || obj == undefined || obj == "") {  
        return;  
    }  
    if (maxLen == null || maxLen == undefined || maxLen == "") {  
        maxLen = 100;  
    }  
    var strResult;  
    var $obj = $(obj);   

    if (obj.value.length > maxLen) { //如果输入的字数超过了限制  
        obj.value = obj.value.substring(0, maxLen); //就去掉多余的字  
        $("#desAdd").html(0); //计算并显示剩余字数  
        $("#desEdit").html(0); //计算并显示剩余字数  
    }else {  
    	$("#desAdd").html(maxLen - obj.value.length); //计算并显示剩余字数 
    	$("#desEdit").html(maxLen - obj.value.length); //计算并显示剩余字数 
    }  

}  

</script>
</body>
</html>
