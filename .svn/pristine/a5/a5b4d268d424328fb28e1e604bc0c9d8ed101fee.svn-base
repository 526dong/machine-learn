<%@ page language="java" import="java.util.*" import="java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 报表列表 start-->
<%--标题--%>
<div class="little_title">
    <h2 class="fl little_icon4">报表数据</h2>
    <div class="fr little_box" id="btn3">
        <a href="javaScript:;" class="little_btn1 little_btn5 fr" onClick="fnback();">
            <span>返回</span>
        </a>
        <a href="javaScript:;" class="little_btn little_w little_btn6 fr" style="width: 126px;" onclick="downloadOrExportReportDataExcel('0');">
            <span>导出整套报表</span>
        </a>
        <%--<a href="javaScript:;" class="little_btn3  little_w little_btn8 fr" onclick="downLoadReportExcelModel();">
            <span>导出模板</span>
        </a>--%>
    </div>
</div>
<%--列表--%>
<div class="statementData">
    <form>
        <input id="reportId" type="hidden" name="id" value="${enterprise.id}">
        <table class="module_table1">
            <thead>
            <tr>
                <th class="table_width40">序号</th>
                <th>报表时间</th>
                <th>报表口径</th>
                <th>报表类型</th>
                <th>报表周期</th>
                <th>报表币种</th>
                <th>是否审计</th>
                <th>添加时间</th>
                <c:if test="${method == null}">
                    <th>录入状态</th>
                </c:if>
                <th>评级状态</th>
                <th class="table_width90">操作</th>
            </tr>
            </thead>
            <tbody id="report_info">
            </tbody>
        </table>
    </form>
</div>
<!-- 报表列表 end -->
   