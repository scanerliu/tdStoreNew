<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/article/ad.js" type="text/javascript"></script>
<div id="rightlist">
	<div class="subnav"><div class="content_menu ib_a blue line_x"><a data-height="190" data-width="450" data-id="add" data-title="添加广告" href="javascript:;" class="add fb J_showdialog" onclick="editAd(0)"><em>添加广告</em></a>&#12288;</div></div>
	<form id="searchform">
	<table width="100%" cellspacing="0" class="search_form">
		<tbody>
			<tr>
				<td>
					<div class="explain_col">
						<select style="width: 100px;" name="adsId">
						    <option value="">请选择类别...</option>
						    <#if adsenseList??>
						    <#list adsenseList as ads>
						    	<#if branch?? && branch==true>
				        			<#if ads.id==3||ads.id==4>
				        			<option value="${ads.id?c}">${ads.name!''}</option>
				        			</#if>
			        			<#else>
					    			<option value="${ads.id?c}">${ads.name!''}</option>
					    		</#if>
						    </#list>
						    </#if>
						</select>
						&nbsp;&nbsp;关键词 :
						<input type="text" value="" size="25" class="input-text" name="keyword">
						<input type="button" value="搜索" class="btn" name="search" onclick="searchAd(true)">
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	<form id="adform">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
	    searchAd(true);
	});
</script>
