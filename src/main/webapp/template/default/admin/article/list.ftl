<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/article/article.js" type="text/javascript"></script>
<div id="rightlist">
<div class="subnav"><div class="content_menu ib_a blue line_x">
	<a data-height="190" data-width="450" data-id="add" data-title="添加资讯" href="javascript:;" class="add fb J_showdialog" onclick="editArticle(0)"><em>添加资讯</em></a>&#12288;
	<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
</div></div>
<form id="searchform">
<table width="100%" cellspacing="0" class="search_form">
	<tbody>
		<tr>
			<td>
				<div class="explain_col">
					&nbsp;&nbsp;关键词 :
					<input type="text" value="" size="25" class="input-text" name="keyword">
					<input type="button" value="搜索" class="btn" name="search" onclick="searchArticle(true)">
				</div>
			</td>
		</tr>
	</tbody>
</table>
</form>
<form id="articlelistform">
<div id="results"></div>
</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
$(function(){
    searchArticle(true);
});
</script>
