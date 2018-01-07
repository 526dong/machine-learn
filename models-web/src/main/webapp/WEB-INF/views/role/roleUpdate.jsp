<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>编辑角色</title>
	<link rel="stylesheet" href="${ctx}/resources/css/style.css">
	<script src="${ctx}/resources/js/main.js"></script>
</head>
<style type="text/css">
	.create-role .j-input{
		width: 270px;
	}
	.create-role .j-select-placeholder{
		width: 230px;
	}
	.create-role .clearfixx{
		margin-top:100px;
	}
</style>
<script type="text/javascript">
    $(function(){

        $("#roleType_select_box").on("click", "li", function () {
            $("#roleType").val($(this).attr("data-id"));
        })


        $.validator.addMethod("checkRoleName", function(value,element) {
            var roleName = $.trim($("#roleName").val());
            var oldRoleName = "${role.name }";
            var flag = false;
            if(oldRoleName != roleName){
                $.ajax({
                    type : 'POST',
                    url : '${ctx}/role/checkRoleName',
                    async:false,
                    data : {
                        "roleName" : roleName
                    },
                    success : function(data) {
                        if (data.result == "false") {
                            flag = false;
                        }else{
                            flag = true;
                        }
                    }
                })
            }else{
                flag = true;
            }
            return flag;
        }, "此角色名称已存在");
        $.validator.addMethod("checkRoleName2", function(value,element) {
            var roleName = $.trim($("#roleName").val());
            //var reg=/^[a-zA-Z0-9]{8,18}$/;
            var reg=/^[\u4e00-\u9fa5a-zA-Z]{1,30}$/
            if(reg.test(roleName)){
                return true;
            }else{
                return false;
            }
            return true;
        }, "中英文不限，30字内");
        $.validator.addMethod("roleTypeRequired", function(value,element) {
            var roleType = $("#roleType").val();
            if (null!=roleType&&""!=roleType&&"0000"!=roleType) return true;
            return false;
        }, "请选择角色类型");
        $("#roleUpdate").validate({
            rules: {
                roleName: {
                    required: true,
                    checkRoleName: true,
                    checkRoleName2: true
                },
                roleType:{
                    roleTypeRequired: true
                }
            },
            messages: {
                roleName:{
                    required:"请输入角色名称"
                }
            },
            errorPlacement: function(error, element) {
                if(element.is("input[name=roleName]")){
                    error.appendTo($("#roleName_error"));
                }
                if(element.is("input[name=roleType]")){
                    error.appendTo($("#roleType_error"));
                }
            },
        });
    })

    //保存编辑的用户
    function saveUpdateRole(){
        var href="${ctx}/role/toRoleManagerPage";
        if($("#roleUpdate").valid()){
            var roleId=$("#roleId").val();
            var roleName=$("#roleName").val();
            var roleType=$("#roleType").val();
            $.ajax({
                url : "${ctx}/role/updateTo",
                type : 'POST',
                data : {
                    "id":roleId,
                    "name":roleName,
                    "roleType":roleType
                },
                success : function(data) {
                    if (data.result == "success") {
                        jAlert("角色编辑成功!",href);
                    } else {
                        jAlert("角色编辑失败!",href);
                    }
                }
            });
        }
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
					<p>编辑角色</p>
				</div>
			</div>
			<!-- //main-header -->
			<div class="create-role">
				<form action="" class="create-role-form" id="roleUpdate">
					<input type="hidden" id="roleId" value="${roleId }">
					<div class="form-item clearfix">
						<label class="j-label">角色名称*</label>
						<div class="form-item-content">
							<input class="j-input" id="roleName" name="roleName" value="${role.name }" placeholder="请输入角色名称" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
							<p class="form-item-err" id="roleName_error"></p>
						</div>
					</div>
					<div class="form-item clearfix">
						<label class="j-label">角色类型*</label>
						<div class="form-item-content">
							<div class="j-select">
								<i class="j-select-arrow"></i>
								<input type="hidden" id="roleType"  name="roleType" value="${role.roleType }">
								<div class="j-select-placeholder">
									<c:choose>
										<c:when test="${role.roleType==0 }">
											超级管理员
										</c:when>
										<c:otherwise>
											普通管理员
										</c:otherwise>
									</c:choose>
								</div>
								<div class="j-select-options">
									<ul id="roleType_select_box">
										<li class="active" data-id="0">超级管理员</li>
										<li data-id="1">普通管理员</li>
									</ul>
								</div>
							</div>
							<p class="form-item-err" id="roleType_error"></p>
						</div>
					</div>
					<div class="clearfix clearfixx" >
						<a href="javaScript:saveUpdateRole();" ><span class="j-button" style="margin-top: 30px;margin-left: 50px;">保存</span></a>
						<a href="${ctx}/role/toRoleManagerPage" ><span class="j-button" style="margin-top: 30px;margin-left: 50px;">取消</span></a>
					</div>
				</form>
			</div>
			<!-- //create_role -->
		</div>
		<!-- //main -->
	</div>
	<!-- //content -->
</div>

</body>
</html>