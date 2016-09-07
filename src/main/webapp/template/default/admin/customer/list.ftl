<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/core.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/customer/customerlist.js" type="text/javascript"></script>
<div id="rightlist">
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="editCustomer(0)"><em>添加会员</em></a>&#12288;<a href="javascript:;" class="add fb J_showdialog" onclick="sendUserMessage()"><em>发送系统消息</em></a></div></div>

<form id="searchform">
<table width="100%" cellspacing="0" class="search_form">
	<tbody>
		<tr>
			<td>
				<div class="explain_col">
					<!--发布时间：
					<input type="text" value="" size="12" class="date" id="time_start" name="time_start">
					-<input type="text" value="" size="12" class="date" id="time_end" name="time_end">
					&nbsp;&nbsp;-->
					<select name="ustatus" class="J_cate_select mr10">
						<option value="">-会员状态-</option>
						<option value="1">启用</option>
						<option value="2">禁用</option>
					</select>
					<select name="uverification" class="J_cate_select mr10">
						<option value="">-会员认证状态-</option>
						<option value="1">已认证</option>
						<option value="2">未认证</option>
					</select>
					&nbsp;&nbsp;
					<select name="tempsupplier">
						<option value="">-供应商资格-</option>
						<option value="0">未获得</option>
						<option value="1">临时供应商</option>
						<option value="2">付费供应商</option>
					</select>
					&nbsp;&nbsp;
					<select name="supplierType">
						<option value="">-供应商认证-</option>
						<option value="0">未认证</option>
						<option value="1">个人资质供应商</option>
						<option value="2">公司资质供应商</option>
					</select>
					&nbsp;&nbsp;<label><input type="checkbox" value="true" name="agent">单类代理</label>
					&nbsp;&nbsp;<label><input type="checkbox" value="true" name="branch">分公司</label>
					&nbsp;&nbsp;用户名 :
					<input type="text" value="" size="25" class="input-text" name="uname">
					<input type="button" value="搜索" class="btn" name="search" onclick="searchCustomers(true)">
				</div>
			</td>
		</tr>
	</tbody>
</table>
</form>
<form id="listform">
<div id="results"></div>
</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
$(function(){
    searchCustomers(true);
});
</script>
