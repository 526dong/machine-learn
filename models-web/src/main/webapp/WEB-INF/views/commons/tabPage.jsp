<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    //创建分页
    /**
     * 创建分页
     * total : 总数量
     * pageNo:当前页码
     * totalpage: 总页数
     */
    function createPage(total, pageNo, totalpage) {
        if(totalpage > 1){
            $("#currentPage").val(pageNo);//当前页数
            $("#totalRecords").text(total); //总数
            $("#totalPage").text((totalpage == 0 ? 1 : totalpage));//总页数
            var starPage = pageNo - 2;
            var endPage = pageNo + 2;
            if (starPage < 1) {
                starPage = 1;
                endPage = 5;
                if (endPage > totalpage) {
                    endPage = totalpage;
                }
            }
            if (endPage > totalpage) {
                endPage = totalpage;
                starPage = totalpage - 4;
                if (starPage < 1) {
                    starPage = 1;
                }
            }
            var pageStr = "";
            pageStr += "<p class='page_list'> ";
            if (pageNo - 1 > 0) {
                pageStr += "<strong class='page_left' onclick='javaScript:jumpToPage(" + (pageNo - 1) + ")'></strong>";
            }
            if (1 < starPage) {
                pageStr += "<span >...</span>";
            }
            for (var i = starPage; i <= endPage; i++) {
                if (pageNo == i) {
                    pageStr += "<a href='javaScript:;' class='active'>" + pageNo + "</a>";
                } else {
                    pageStr += "<a href=\"javaScript:jumpToPage(" + i + ");\" class='page_pg'>" + i + "</a>";
                }
            }
            if (starPage > endPage) {
                pageStr += "<a href='javaScript:jumpToPage(" + pageNo + ");' >" + pageNo + "</a>";
            }
            if (endPage < totalpage) {
                pageStr += "<span>...</span>";
            }
            if (pageNo + 1 <= totalpage) {
                pageStr += "<strong class='page_right' onclick='javaScript:jumpToPage(" + (pageNo + 1) + ")'></strong>";
            }
            pageStr += "</p>";
            return pageStr;
        }else{
            var pageStr = "";
            return pageStr;
        }

    }
</script>
<div class="page_parent" id="pageDiv"></div>