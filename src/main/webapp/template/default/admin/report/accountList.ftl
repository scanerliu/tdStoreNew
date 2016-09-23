<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/common.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/report/report.js" type="text/javascript"></script>

<div id="rightlist">
	<div class="subnav">
		<div class="content_menu ib_a blue line_x">
			用户钱包总金额：${totalAmount!'0'}元
		</div>
	</div>
	<form id="searchForm">
		<table width="100%" cellspacing="0" class="search_form">
			<tbody>
				<tr>
					<td>
						<div class="explain_col">
							会员账号 :
							<input type="text" value="" size="25" class="input-text" name="username">
							&nbsp;&nbsp;
							<select name="sortBy" class="J_cate_select mr10">
								<option value="1">-金额降序-</option>
								<option value="2">-金额升序-</option>
							</select>
							<input type="button" value="搜索" class="btn" name="search" onclick="searchUserAccounts(true)">
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<form id="listForm">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
		searchUserAccounts(true);
	});
</script>
