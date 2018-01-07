<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--shiro 标签 --%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<TITLE>ZTREE DEMO - checkbox</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/resources/css/system/demo.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctx}/resources/css/system/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${ctx}/resources/js/system/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/system/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/system/jquery.ztree.excheck.js"></script>

	<script type="text/javascript" src="${ctx}/resources/js/system/jquery.ztree.exedit.js"></script>

<SCRIPT type="text/javascript">

var demoMsg = {
		async:"正在进行异步加载，请等一会儿再点击...",
		expandAllOver: "全部展开完毕",
		asyncAllOver: "后台异步加载完毕",
		asyncAll: "已经异步加载完毕，不再重新加载",
		expandAll: "已经异步加载完毕，使用 expandAll 方法"
	}
	var setting = {
		view: {
			selectedMulti: false
		},
		data: {
			key: {
				title:"t"
			},
			simpleData: {
				enable: true
			}
		},
		
		check : {
			enable : true,
			autoCheckTrigger : true
		}, 
		async: {
			enable: true,
			url:"${ctx}/role/async",
			autoParam:["id", "name=n", "level=lv"],
			otherParam:{"roleId":"${roleId}"},
			dataFilter: filter,
			type: "post"
		},
		callback: {
			beforeAsync: beforeAsync,
			onAsyncSuccess: onAsyncSuccess,
			onAsyncError: onAsyncError,
			beforeClick: beforeClick,
			beforeCollapse: beforeCollapse,
			beforeExpand: beforeExpand,
			onCollapse: onCollapse,
			onExpand: onExpand,
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	function beforeAsync() {
		curAsyncCount++;
	}
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		curAsyncCount--;
		if (curStatus == "expand") {
			expandNodes(treeNode.children);
		} else if (curStatus == "async") {
			asyncNodes(treeNode.children);
		}

		if (curAsyncCount <= 0) {
			if (curStatus != "init" && curStatus != "") {
				$("#demoMsg").text((curStatus == "expand") ? demoMsg.expandAllOver : demoMsg.asyncAllOver);
				asyncForAll = true;
			}
			curStatus = "";
		}
	}

	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		curAsyncCount--;

		if (curAsyncCount <= 0) {
			curStatus = "";
			if (treeNode!=null) asyncForAll = true;
		}
	}

	var curStatus = "init", curAsyncCount = 0, asyncForAll = false,
	goAsync = false;
	function expandAll() {
		if (!check()) {
			return;
		}
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		if (asyncForAll) {
			$("#demoMsg").text(demoMsg.expandAll);
			zTree.expandAll(true);
		} else {
			expandNodes(zTree.getNodes());
			if (!goAsync) {
				$("#demoMsg").text(demoMsg.expandAll);
				curStatus = "";
			}
		}
	}
	function expandNodes(nodes) {
		if (!nodes) return;
		curStatus = "expand";
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		for (var i=0, l=nodes.length; i<l; i++) {
			zTree.expandNode(nodes[i], true, false, false);
			if (nodes[i].isParent && nodes[i].zAsync) {
				expandNodes(nodes[i].children);
			} else {
				goAsync = true;
			}
		}
	}

	function asyncAll() {
		if (!check()) {
			return;
		}
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		if (asyncForAll) {
			$("#demoMsg").text(demoMsg.asyncAll);
		} else {
			asyncNodes(zTree.getNodes());
			if (!goAsync) {
				$("#demoMsg").text(demoMsg.asyncAll);
				curStatus = "";
			}
		}
	}
	function asyncNodes(nodes) {
		if (!nodes) return;
		curStatus = "async";
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		for (var i=0, l=nodes.length; i<l; i++) {
			if (nodes[i].isParent && nodes[i].zAsync) {
				asyncNodes(nodes[i].children);
			} else {
				goAsync = true;
				zTree.reAsyncChildNodes(nodes[i], "refresh", true);
			}
		}
	}

	function reset() {
		if (!check()) {
			return;
		}
		asyncForAll = false;
		goAsync = false;
		$("#demoMsg").text("");
		$.fn.zTree.init($("#treeDemo"), setting);
	}

	function check() {
		if (curAsyncCount > 0) {
			$("#demoMsg").text(demoMsg.async);
			return false;
		}
		return true;
	}
	
	var log, className = "dark";
	function beforeClick(treeId, treeNode) {
		if (treeNode.isParent) {
			return true;
		} else {
			return false;
		}
	}
	function beforeCollapse(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeCollapse ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name );
		return (treeNode.collapse !== false);
	}
	function onCollapse(event, treeId, treeNode) {
		showLog("[ "+getTime()+" onCollapse ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
	}		
	function beforeExpand(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name );
		return (treeNode.expand !== false);
	}
	function onExpand(event, treeId, treeNode) {
		showLog("[ "+getTime()+" onExpand ]&nbsp;&nbsp;&nbsp;&nbsp;" + treeNode.name);
	}
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}
	
	function expandNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		type = e.data.type,
		nodes = zTree.getSelectedNodes();
		if (type.indexOf("All")<0 && nodes.length == 0) {
			alert("请先选择一个父节点");
		}
	}

	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), py = $("#py").attr(
				"checked") ? "p" : "s", sy = $("#sy").attr("checked") ? "s"
				: "", pn = $("#pn").attr("checked") ? "p" : "s", sn = $("#sn")
				.attr("checked") ? "s" : "p", type = {
			"Y" : py + sy,
			"N" : pn + sn
		};
		zTree.setting.check.chkboxType = type;
	}
	var zNodes = ${array};
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$("#expandBtn").bind("click", {type:"expand"}, expandNode);
		$("#collapseBtn").bind("click", {type:"collapse"}, expandNode);
		$("#toggleBtn").bind("click", {type:"toggle"}, expandNode);
		$("#expandSonBtn").bind("click", {type:"expandSon"}, expandNode);
		$("#collapseSonBtn").bind("click", {type:"collapseSon"}, expandNode);
		$("#expandAllBtn").bind("click", {type:"expandAll"}, expandNode);
		$("#collapseAllBtn").bind("click", {type:"collapseAll"}, expandNode); 
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
	});
	
	
	function permissionAssignSave() {
		var ids = [];
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo"), nodes = treeObj
				.getCheckedNodes(true), v = "";
		for (var i = 0; i < nodes.length; i++) {
			v += nodes[i].name + ",";
			ids.push(nodes[i].id);
		}

		var data = {
			resIds : ids.join(','),
			roleId : '${roleId }'
		};
		$.ajax({
			type : 'post',
			url : '${ctx}/role/assignResource',
			dataType : 'json',
			data : {
				resIds : ids.join(','),
				roleId : '${roleId }'
			},
			success : function(data) {
				if (data.result != 1) {
					console.log('分配权限失败！');

					//window.location.href = "${ctx}/role/manager";

					alert("分配权限失败！");

				} else {
					//location.href = "${ctx}/role/manager";

					alert("分配权限成功！");
				}
			}
		})
	}
</SCRIPT>
</HEAD>
<body>

	<a href="javaScript:;" onclick="permissionAssignSave()">保存</a>

	<input id="array" type="hidden" value="${array}" />
	<input type="hidden" name="roleId" value="${roleId }"></input>
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
</HTML>