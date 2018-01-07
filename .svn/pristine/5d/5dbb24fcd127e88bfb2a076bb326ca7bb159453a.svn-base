<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>设置权限</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/permission/easyui/themes/black/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/permission/easyui/themes/icon.css"/>
    <script type="text/javascript" src="${ctx}/resources/permission/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/resources/permission/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/permission/easyui/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <script src="${ctx}/resources/js/main.js"></script>
</head>
<style type="text/css">
    .easyui-tree span{
        font-size: 14px;
    }
</style>
<script type="text/javascript">
    $(function() {
        var id = $("#rid").val();
        $('#tree').tree({
            url:'${ctx}/role/findPermissionTree?id='+id,
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
        var href="${ctx}/role/toRoleManagerPage";
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
                    jAlert("权限分配成功!",href);
                }else if(result=="888"){
                    jAlert("传参错误!",href);alert("传参错误!");
                }else{
                    jAlert("权限分配失败!",href);
                }
            }
        });
    }
</script>
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
                    <h3>角色管理</h3>
                    <p>设置权限</p>
                </div>
            </div>
            <!-- //main-header -->
            <div class="create-role">
                <form action="" class="create-role-form" >
                    <input type="hidden" name="rid" id="rid" value="${permissionAddRoleID }"/>
                    <input type="hidden" id="permission_array" name="permission_array"/>
                    <div class="form-item clearfix">
                        <label class="j-label">角色名称：</label>
                        <div class="form-item-content">
                            <input style="background:transparent;border:0" readonly="readonly" class="j-input" value="${role.name }"  type="text">
                        </div>
                    </div>
                    <div class="easyui-panel" style="background:url('${ctx}/resources/img/bg.jpg')">
                        <ul class="easyui-tree" id="tree" >

                        </ul>
                    </div>
                    <div class="clearfix">
                        <a href="javaScript:savePermission();" ><span class="j-button" style="margin-top: 30px;">保存</span></a>
                        <a href="${ctx}/role/toRoleManagerPage" ><span class="j-button" style="margin-top: 30px;">取消</span></a>
                    </div>
                </form>
            </div>
            <!-- //create_role -->
        </div>
        <!-- //main -->
    </div>
    <!-- //content -->
</div>
<!-- //j-container -->
</body>
</html>