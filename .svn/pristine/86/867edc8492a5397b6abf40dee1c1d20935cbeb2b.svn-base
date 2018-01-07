<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
/**
 * 创建分页
 * total : 总数量
 * pageNo:当前页码
 * totalpage: 总页数
 */
function createPage2(total, pageNo, totalpage){
	$("#totalRecords2").text(total); //总数
    $("#currentPage2").val(pageNo);//当前页数
	$("#totalPage2").text((totalpage==0?1:totalpage));//总页数
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
        pageStr += '<a href="javaScript:;" onclick="jumpToPage2('+(pageNo-1)+');" class="triangle_left"></a>';
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
			pageStr += '<a href="javaScript:;" onclick="jumpToPage2('+i+');">'+i+'</a>';
		}
	}
	if(starPage > endPage){
		pageStr += '<a href="javaScript:;" onclick="jumpToPage2('+pageNo+');" class="active">'+pageNo+'</a>';
	}
	if(endPage < totalpage){
		pageStr += '<span>...</span>';
	}
	if(pageNo+1 > totalpage){
		pageStr += '<a class="triangle_right"></a>';
	}else{
		pageStr += '<a class="triangle_right" href="javaScript:;" onclick="jumpToPage2('+(pageNo+1)+');"></a>';
	}
	return pageStr;
}
</script>
<!-- 分页.html 2 start -->
<div class="page_parent">
	<div class="fl page_left">
		<p class="fl">共<span id="totalRecords2">0</span>条，每页显示</p>
		<div class="page_menu select_btn fl">
			<span>10</span>
			<input id="pageSize2" name="pageSize2" type="hidden" value="10"/>
		</div>
		<ul class="page_down_div select_list pagesize_change2">
			<li class="active" data-id="10">10</li>
			<li data-id="20">20</li>
			<li data-id="50">50</li>
			<li data-id="100">100</li>
		</ul>
		<p class="fl">条记录</p>
	</div>
	<div class="fr page_right">
		<div class="fr">
			<span>跳转至</span>
			<input type="text" value="1" onchange="inputPage2(this);"/>
			<span>页，共</span>
			<span id="totalPage2">1</span>
			<span>页</span>
		</div>
		<p class="fr page" id="page_p2"></p>
	</div>
</div>
<!-- 分页.html 2 end -->