<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/district/districtlist.js" type="text/javascript"></script>
<div id="rightlist">
	<div class="subnav">
		<div class="content_menu ib_a blue line_x">
			<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
			<label>请选择地区:</label>
			<span id="provincespn"></span><span id="cityspn"></span><span id="regionspn"></span><span id="townspn"></span><span id="villagespn"></span>
			<form id="searchform">
			<table width="100%" cellspacing="0" class="search_form">
			<tbody>
			<tr>
				<td>
					<div class="explain_col">
					<input type="text" id="newDistrict" name="newDistrict" value="" placeholder="请输入地区名..."><input type="button" value="添加" onclick="addDistrict()">
					<input type="hidden" value="0" name="upid" id="upid">
					</div>
				</td>
			</tr>
			</tbody>
			</table>
			</form>
		</div>
	</div>
	<form id="districtlistform">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
		getDistricts({'obj':null,'num':0,'total':4,'callback':'searchDistricts'});
	    searchDistricts(true);
	});
</script>
