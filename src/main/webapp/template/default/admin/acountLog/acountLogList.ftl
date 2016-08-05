<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/common.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/acountLog/acountLog.js" type="text/javascript"></script>

<div id="rightlist">
	<form id="searchform">
		<table width="100%" cellspacing="0" class="search_form">
			<tbody>
				<tr>
					<td>
						<div class="explain_col">
							账户余额：<label style="color:red;">￥${amount?c}元</label>
							&nbsp;&nbsp;&nbsp;&nbsp;
							变更时间：
							<input type="text" value="" style="width:100px;" size="12" class="easyui-datebox" id="beginDate" name="beginDate">
							-<input type="text" value="" style="width:100px;" size="12" class="easyui-datebox" id="endDate" name="endDate">
							<input type="button" value="搜索" class="btn" name="search" onclick="searchAcountLog(true)">
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<form id="acountLogListForm">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
		searchAcountLog(true);
	});
</script>
