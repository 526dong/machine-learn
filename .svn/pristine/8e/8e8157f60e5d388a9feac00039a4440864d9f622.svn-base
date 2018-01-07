<%@ page language="java" import="java.util.*" import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$(document).on( "click" ,".leftsideBar_list a",function(){
			var menuIndex1 = $(".leftsideBar_list a").index($(this));
			var menuIndex2 = $(this).attr("data-id");
			var href = $(this).attr("data-url");
			window.location.href = href + "?menuIndex1="+menuIndex1+ "&&menuIndex2="+menuIndex2;
		})
		//控制左侧菜单
		contrMenu('${menuIndex1}','${menuIndex2}');	
		//控制左侧菜单
		function contrMenu(index1,index2){
			if(null == index1 || ""==index1 || null==index2 || ""==index2){
				//进入首页首次加载左边导航栏
				var firstIndex = 0;	
				var myselfId = $("#header_nav a").eq(firstIndex).attr("id");
				getLeftNavigation(myselfId,firstIndex);
			}else{
				var strs= new Array(); //定义一数组 
				strs= index2.split("|"); //字符分割 
// 				console.log("666"+strs[0]);
// 				console.log("666"+strs[1]);
				
				getLeftNavigation(strs[1],strs[0]);
				//选中当前菜单
				
				$("#header_nav li").attr('class','');
				$("#header_nav a").eq(strs[0]).parent().attr('class','active');
				
				$(".leftsideBar_list a").parent().parent().attr("class","");
				$(".leftsideBar_list a").eq(index1).parent().parent().attr("class","active");
			}
		}
	})

//点击头部导航栏
function clickHeader(myselfId,index){
	var menuIndex2 = 0;
	$("#header_nav a").each(function(){
	   if($(this).attr("id") == myselfId){
		   menuIndex2 = $("#header_nav a").index($(this));
		   return false;
	   }
    });
	getLeftNavigation(myselfId,menuIndex2);
	//加载左侧导航栏的第一个url,犹豫左侧只有一级导航，所以第一个url所在a标签的下表为0
	var herf = $(".leftsideBar_list a").eq(0).attr("data-url");
	var menuIndex1 = 0;
	menuIndex2 = menuIndex2+"|"+myselfId;
	window.location.href = herf+"?menuIndex1="+menuIndex1+"&menuIndex2="+menuIndex2;
}

//动态生成左侧导航
function getLeftNavigation(obj1,obj2){
	var myselfId = obj1;
	var userId = "${risk_crm_user.id }";
	$.ajax({
        url:"${ctx}/getLeftNavigation",
        type:"post",
        data:{
        	  userId: userId,//当前登陆人id
        	  myselfId: myselfId,//模块标识
              },
        dataType: "json",
        async : false,
        success: function(data) {//回调函数，result，返回值
        	var htmlContent = '';
        	if(null!=data&&data.length>0){    
	        	for(var i = 0 ;i<data.length;i++){
	                if(parseInt(data[i].level) == 2){
	                	htmlContent += '<li>';
	                    htmlContent += '<h2>';
	                    htmlContent += '<a style="cursor: pointer;" href="javascript:void(0)" data-id="'+obj2+'|'+myselfId+'" data-url="${ctx}'+data[i].pathUrl+'">';
	                    htmlContent += '<span class="'+data[i].iconUrl+'"></span>';
	                    htmlContent += data[i].permission_name;
	                    htmlContent += '</a>';
	                    htmlContent += '</h2>';
	                    htmlContent += '</li>';
	                }
	            }
	        	$("#leftContent").html(htmlContent);
	        	$("#leftContent li").eq(0).attr('class','active');
	        	//document.getElementById("iframeId").setAttribute("src", data[0].pathUrl);
        	}else{
        		$("#leftContent").html(htmlContent);
        	}
        }
    });
}



</script>
<!-- 左侧导航.html start -->
<div class="leftsideBar">
    <ul class="leftsideBar_list" id="leftContent" >
<!--         <li class="active"> -->
<!--             <h2> -->
<%--                 <a href="${ctx}/enterprise/list"> --%>
<!--                     <span class="sideBar_span1"></span> -->
<!--                     评级主体数据录入及更新 -->
<!--                 </a> -->
<!--             </h2> -->
<!--         </li> -->
<!--         <li> -->
<!--             <h2> -->
<%--                 <a href="${ctx}/ratingApply/list"> --%>
<!--                     <span class="sideBar_span2"></span> -->
<!--                     评级申请提交 -->
<!--                 </a> -->
<!--             </h2> -->
<!--         </li> -->
<!--         <li> -->
<!--             <h2> -->
<%--                 <a href="${ctx}/riskCheck/list"> --%>
<!--                     <span class="sideBar_span3"></span> -->
<!--                     评级申请审批 -->
<!--                 </a> -->
<!--             </h2> -->
<!--         </li> -->
<!--         <li> -->
<!--             <h2> -->
<%--                 <a href="${ctx}/asset/list"> --%>
<!--                     <span class="sideBar_span4"></span> -->
<!--                     资产创建 -->
<!--                 </a> -->
<!--             </h2> -->
<!--         </li> -->
<!--         <li> -->
<!--             <h2> -->
<%--                 <a href="${ctx}/asset/assetPrice"> --%>
<!--                     <span class="sideBar_span5"></span> -->
<!--                     资产定价 -->
<!--                 </a> -->
<!--             </h2> -->
<!--         </li> -->
<!--         <li> -->
<!--             <h2> -->
<%--                 <a href="${ctx}/enterprise/evaluateList"> --%>
<!--                     <span class="sideBar_span6"></span> -->
<!--                     受评主体数据库 -->
<!--                 </a> -->
<!--             </h2> -->
<!--         </li> -->
<!--         <li> -->
<!--             <h2> -->
<!--                 <a href="service.html"> -->
<!--                     <span class="sideBar_span7"></span> -->
<!--                     征信服务 -->
<!--                 </a> -->
<!--             </h2> -->
<!--         </li> -->
    </ul>
</div>
<!-- 左侧导航.html end -->
   