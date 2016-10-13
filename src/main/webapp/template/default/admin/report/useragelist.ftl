<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/common.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/report/report.js" type="text/javascript"></script>

<div id="rightlist">
	<form id="searchForm">
		<table width="100%" cellspacing="0" class="search_form">
			<tbody>
				<tr>
					<td>
						<div class="explain_col">
							<select name="ugenter" class="J_cate_select mr10">
								<option value="">-性别-</option>
								<option value="1">-男-</option>
								<option value="2">-女-</option>
								<option value="3">-保密-</option>
							</select>
							<select name="uverification" class="J_cate_select mr10">
								<option value="">-认证状态-</option>
								<option value="1">已认证</option>
								<option value="2">未认证</option>
							</select>
							<input type="button" value="搜索" class="btn" name="search" onclick="searchAgeCustomerCounts(true)">
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
		searchAgeCustomerCounts(true);
	});
</script>
