<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/common.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/report/report.js" type="text/javascript"></script>

<div id="rightlist">
	<form id="searchform">
		<table width="100%" cellspacing="0" class="search_form">
			<tbody>
				<tr>
					<td>
						<div class="explain_col">
							下单时间：
							<input type="text" value="" style="width:100px;" size="12" class="easyui-datebox" id="beginTime" name="beginTime">
							-<input type="text" value="" style="width:100px;" size="12" class="easyui-datebox" id="endTime" name="endTime">
							&nbsp;&nbsp;商品名称 :
							<input type="text" value="" size="25" class="input-text" name="productName">
							&nbsp;&nbsp;
							<select name="isAsc" class="J_cate_select mr10">
								<option value="false">-销量降序-</option>
								<option value="true">-销量升序-</option>
							</select>
							<input type="button" value="搜索" class="btn" name="search" onclick="searchSale(true)">
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<form id="saleListForm">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
		searchSale(true);
	});
</script>
