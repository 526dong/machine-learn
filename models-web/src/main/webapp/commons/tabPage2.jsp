<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
/**
 * 创建分页
 * total : 总数量
 * pageNo:当前页码
 * totalpage: 总页数
 */
function creatPage2(total,pageNo,totalpage){
	$("#currentNum").val(pageNo);//当前页数
	$("#totalRecords2").text(total); //总数
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
		pageStr += " <a class='page_fan_a1' href='javaScript:jumpToPage2("+(pageNo-1)+");'>上一页</a>";
	}else{
		/* 原先版本 pageStr += " <a class='page_fan_a1' href='javaScript:jumpToPage2(1);'>上一页</a>"; */
		pageStr += " <a class='page_fan_a1'>上一页</a>";
	}
	if(pageNo-1 > 0){
		pageStr += "<a href='javaScript:jumpToPage2("+(pageNo-1)+");' class='page_fan_a2' >ad</a>";
	}else{
		/* pageStr += "<a href='javaScript:jumpToPage2(1);' class='page_fan_a2' >ad</a>"; */
		pageStr += "<a class='page_fan_a2' >ad</a>";
	}
	if(1 < starPage){
		pageStr += "<span class='page_pg'>...</span>";
	}
	for(var i=starPage;i<=endPage;i++){
		if(pageNo==i){
			pageStr += "<a href='javaScript:;' class='page_pg_active'>"+pageNo+"</a>";
		}else{
			pageStr += "<a href=\"javaScript:jumpToPage2("+i+");\" class='page_pg'>"+i+"</a>";
		}
	}
	if(starPage > endPage){
		pageStr += "<a href='javaScript:jumpToPage2("+pageNo+");' class='page_pg_active'>"+pageNo+"</a>";
	}
	if(endPage < totalpage){
		pageStr += "<span class='page_pg'>...</span>";
	}
	if(pageNo+1 > totalpage){
		/* pageStr += "<a href='javaScript:jumpToPage2("+totalpage+");' class='page_fan_a3' >as</a>"; */
		pageStr += "<a class='page_fan_a3' >as</a>";
	}else{
		pageStr += "<a href='javaScript:jumpToPage2("+(pageNo+1)+");' class='page_fan_a3' >as</a>";
	}
	if(pageNo+1 > totalpage){
		/* pageStr += " <a class='page_fan_a4' href='javaScript:jumpToPage2("+(totalpage==0?1:totalpage)+");'>下一页</a>"; */
		pageStr += " <a class='page_fan_a4'>下一页</a>";
	}else{
		pageStr += " <a class='page_fan_a4' href='javaScript:jumpToPage2("+(pageNo+1)+");'>下一页</a>";
	}
	return pageStr;
}
</script>
<div class="pagination clear">
	<div class="pagination_left fl"  >
	    <p class="fl">共<span id="totalRecords2">0</span>条记录,每页显示</p>
	    <div class="down_select_box fl"  >
	        <div class="next_select" id="next1_select2">
	            <span>10</span>
	        </div>
	        <div class="select_box" id="select1_box2" >
	            <div>
	                <p data-id="1">10</p>
	                <p data-id="2">20</p>
	                <p data-id="3">50</p>
	                <p data-id="4">100</p>
	            </div>
	        </div>
	    </div>
	    <p class="fl">条记录</p>
	</div>
    <div class="page_fan clear fr">
	    <p class="fr" style="padding-left: 36px">跳转至<input type="text" class="page_tiao" value="1" id="gotoPage">页 , 共<font id="totalPage2">1</font>页</p>
	     <p class="fr clear" id="page_p2">
	     </p>
	</div>
</div>