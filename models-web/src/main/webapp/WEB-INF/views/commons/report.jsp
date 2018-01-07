<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="table_content_son" style="display:block;">
	<div class="operate_btn clear">
		<a href="javaScript:;" onclick="momentSaveReport();">暂存</a>
	</div>
	<div class="main_table_box">
		<form id="reportForm">
			<!-- 报表概况id -->
			<input id="reportId" type="hidden" name="id" value="0"/>
			<table class="main_table report_table">
				<tbody>
				<tr>
					<td class="main_table_td1">
						<i>*</i><strong>报表时间</strong>
					</td>
					<td class="main_table_td2">
						<input type="text" id="reportTime" name="reportTime" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})"/>
						<p id="reportTimeError" class="error"></p>
					</td>
					<td class="main_table_td1">
						<i>*</i><strong>口径</strong>
					</td>
					<td class="main_table_td2">
						<div class="select_parent fl">
							<div class="main_select select_btn">
								<span>请选择</span>
								<input type="hidden" id="cal" name="cal" />
							</div>
							<ul class="main_down select_list">
								<li class="active" data-id="">请选择</li>
								<li data-id="1">合并</li>
								<li data-id="0">母公司</li>
							</ul>
						</div>
						<p id="calError" class="error"></p>
					</td>
				</tr>
				<tr>
					<td class="main_table_td1">
						<i></i><strong>类型</strong>
					</td>
					<td class="main_table_td2">
						<%--隐藏 传到后台的值--%>
						<input id="reportType" name="type" type="hidden" />
						<span id="reportTypeVal"></span>
					</td>
					<td class="main_table_td1">
						<i>*</i><strong>周期</strong>
					</td>
					<td class="main_table_td2">
						<div class="select_parent fl">
							<div class="main_select select_btn">
								<span>请选择</span>
								<input type="hidden" id="cycle" name="cycle" />
							</div>
							<ul class="main_down select_list">
								<li class="active" data-id="">请选择</li>
								<li data-id="1">年报</li>
							</ul>
						</div>
						<p id="cycleError" class="error"></p>
					</td>
				</tr>
				<tr>
					<td class="main_table_td1">
						<i>*</i><strong>是否审计</strong>
					</td>
					<td class="main_table_td2">
						<div class="select_parent fl">
							<div class="main_select select_btn">
								<span>请选择</span>
								<input type="hidden" id="audit" name="audit" />
							</div>
							<ul class="main_down select_list audit_change">
								<li class="active" data-id="">请选择</li>
								<li data-id="1">是</li>
								<li data-id="0">否</li>
							</ul>
						</div>
						<p id="auditError" class="error"></p>
					</td>
					<td class="main_table_td1">
						<i>*</i><strong>币种</strong>
					</td>
					<td class="main_table_td2">
						<div class="select_parent fl">
							<div class="main_select select_btn">
								<span>请选择</span>
								<input type="hidden" id="currency" name="currency" />
							</div>
							<ul class="main_down select_list">
								<li class="active" data-id="">请选择</li>
								<li data-id="1">人民币</li>
							</ul>
						</div>
						<p id="currencyError" class="error"></p>
					</td>
				</tr>
				<tr>
					<td class="main_table_td1">
						<i></i><strong>审计单位</strong>
					</td>
					<td class="main_table_td2">
						<input id="auditUnit" name="auditUnit" placeholder="请输入审计单位" type="text" maxlength="50">
						<p id="auditUnitError" class="error"></p>
					</td>
				</tr>
				<tr class="main_table_tr">
					<td class="main_table_td1">
						<i></i><strong>审计意见</strong>
					</td>
					<td class="main_table_td2" colspan="3">
						<textarea id="auditOpinion" name="auditOpinion" placeholder="请输入审计意见" maxlength="100"></textarea>
						<p id="auditOpinionError" class="error"></p>
					</td>
				</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>