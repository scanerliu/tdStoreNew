<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/core.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/product/product.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/validator.js" type="text/javascript"></script>
<div id="rightlist">
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
						<select name="status" class="J_cate_select mr10">
							<option value="">-商品状态-</option>
							<option value="1">正常</option>
							<option value="2">待审核</option>
							<option value="3">审核不通过</option>
						</select>
						<select name="onshelf" class="J_cate_select mr10">
							<option value="">-商品上架状态-</option>
							<option value="true">上架中</option>
							<option value="false">已下架</option>
						</select>
						&nbsp;&nbsp;
						商品名称:<input type="text" value="" size="25" class="input-text" name="name">
						&nbsp;&nbsp;供应商ID:<input type="text" value="" size="25" class="input-text" name="uid">
						<input type="button" value="搜索" class="btn" name="search" onclick="searchProduct(true)">
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
	    searchProduct(true);
	});
</script>
