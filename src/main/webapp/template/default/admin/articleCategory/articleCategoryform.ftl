<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="articleCategoryForm" action="${app.basePath}/admin/articlecategory/save" class="easyui-form" method="post" data-options="novalidate:true">
<table>
    <tr>
        <td>目录名称：</td>
        <td><input type="text" name="name" class="easyui-textbox" value="${articleCategory.name!''}" data-options="required:true" validType="length[2,100]"></td>
    </tr>
    <tr>
        <td>上级目录：</td>
        <td>
	        <select id="parentId" name="parentId">
	            <option value="0">顶级目录</option>
	            <#if parentArticleCategoryList??>
	            	<#list parentArticleCategoryList as pac>
			            <option value="${pac.cid?c}" <#if articleCategory.parentId?? && articleCategory.parentId==pac.cid>selected="selected"</#if>>${pac.name!''}</option>	            	
					</#list>
				</#if>
	        </select>
        </td>
    </tr>
    <tr>
        <td>排序(只接受整数)</td>
        <td>
            <input type="text" name="sort" class="easyui-textbox" value="${articleCategory.sort!'0'}">
        </td>
    </tr>
    <tr>
        <td>状态</td>
        <td>
            <input type="radio" name="status" value="1" <#if articleCategory.status?? && articleCategory.status==1>checked="checked"</#if>/>正常
			<input type="radio" name="status" value="2" <#if articleCategory.status?? && articleCategory.status==2>checked="checked"</#if> />屏蔽
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="cid" value="${articleCategory.cid!''}"></td>
        <td>&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td></td>
        <td>
            <button type="button" class="d-button" onclick="saveArticleCategory()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
