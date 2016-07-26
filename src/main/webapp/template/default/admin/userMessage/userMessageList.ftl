<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/common.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/userMessage/userMessage.js" type="text/javascript"></script>

<div id="rightlist">
	<form id="searchform">
		<table width="100%" cellspacing="0" class="search_form">
			<tbody>
				<tr>
					<td>
						<div class="explain_col">
						<input type="button" value="批量删除" class="btn" name="search" onclick="batchDelete()">
							发布时间：
							<input type="text" value="" style="width:100px;" size="12" class="easyui-datebox" id="beginDate" name="beginDate">
							-<input type="text" value="" style="width:100px;" size="12" class="easyui-datebox" id="endDate" name="endDate">
							&nbsp;&nbsp;
							<select name="msgType" class="J_cate_select mr10">
								<option value="-1">-消息类别-</option>
								<option value="1">系统消息</option>
								<option value="2">订单消息</option>
								<option value="3">门店申请消息</option>
							</select>
							<select name="status" class="J_cate_select mr10">
								<option value="-1">-状态-</option>
								<option value="1">未读</option>
								<option value="2">已读</option>
							</select>
							&nbsp;&nbsp;题目 :
							<input type="text" value="" size="25" class="input-text" name="title">
							&nbsp;&nbsp;用户名 :
							<input type="text" value="" size="10" class="input-text" name="username">
							<input type="button" value="搜索" class="btn" name="search" onclick="searchUserMessage(true)">
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<form id="userMessageForm">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
	    searchUserMessage(true);
	});
</script>
