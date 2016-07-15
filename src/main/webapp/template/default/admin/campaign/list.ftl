<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/common.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/campaign/usercampaign.js" type="text/javascript"></script>

<div id="rightlist">
	<form id="searchConditionForm">
		<table width="100%" cellspacing="0" class="search_form">
			<tbody>
				<tr>
					<td>
						<div class="explain_col">
							<input type="button" class="btn" value="批量删除" onclick="btnDelete()">
							<#--地区  begin-->
							<span id="campaign_firstDistrictLevel"></span>
							<span id="campaign_secondDistrictLevel"></span>
							<span id="campaign_thirdDistrictLevel"></span>
							<#--地区  end-->
							
							<a href="javascript:;" class="easyui-linkbutton" onclick="searchUserCampaign(true)">搜索</a>
						</div>
						<#--根据三级联动设置隐藏值-->
						<input type="hidden" id="campaign_region_id" name="regionId" value="">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<form id="campaignForm">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
	    getDistrictSelections(0, 0, "campaign");
	    searchUserCampaign(true);
	});
</script>
