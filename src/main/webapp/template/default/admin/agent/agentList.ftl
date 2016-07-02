<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/common.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/agent/agent.js" type="text/javascript"></script>

<div id="rightlist">
	<form id="searchConditionForm">
		<table width="100%" cellspacing="0" class="search_form">
			<tbody>
				<tr>
					<td>
						<div class="explain_col">
							<input type="button" class="btn" value="批量删除" onclick="batchDelete()">
							<#--产品类别  begin-->
							<span id="firstProductType"></span>
							<span id="secondProductType"></span>
							<span id="thirdProductType"></span>
							<#--产品类别  end-->
							<#--地区  begin-->
							<span id="firstDistrictLevel"></span>
							<span id="secondDistrictLevel"></span>
							<span id="thirdDistrictLevel"></span>
							<#--地区  end-->
							<#--非联动条件 begin-->
							<select style="width: 100px;" name="level">
							    <option value="-1">-代理级别-</option>
							    <option value="1">全国代理</option>
							    <option value="2">省市代理</option>
							    <option value="3">区县代理</option>
							    <option value="3">体验店</option>
							</select>
							<input type="text" placeholder="用户名..." name="username" value="">
							<#--非联动条件 end-->
							
							<input type="button" value="搜索" onclick="searchAgent(true)">
						</div>
						<#--根据三级联动设置隐藏值-->
						<input type="hidden" id="region_id" name="regionId" value="-1">
						<input type="hidden" id="product_type_id" name="productTypeId" value="-1">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<form id="agentForm">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
	    getProductTypeSelections(0, 0);
	    getDistrictSelections(0, 0);
	    searchAgent(true);
	});
</script>
