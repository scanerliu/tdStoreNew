<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
	<a data-height="190" data-width="450" data-id="add" data-title="添加资讯目录" href="javascript:;" class="add fb J_showdialog" onclick="editArticleCategory(0)"><em>添加资讯目录</em></a>&#12288;
	<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
	<th>ID</th>
	<th align="left">类别名称</th>
	<th align="left">上级目录</th>
	<th align="center">资讯数量</th>
	<th align="center">排序</th>
	<th align="left">状态</th>
	<th align="left">修改时间</th>
	<th align="left">修改人</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if articleCategoryList??>
<#list articleCategoryList as articleCategory>
    <tr>
        <td align="center">
        <input type="checkbox" value="${articleCategory.cid?c}" name="subbox" class="J_checkitem"></td>
        <td align="center">${articleCategory.cid?c}</td>
        <td>${articleCategory.name!''}</td>
        <td><#if articleCategory.parent??>${articleCategory.parent.name!''}<#else>无</#if></td>
        <td align="center">${articleCategory.articles!''}</td>
        <td align="center">${articleCategory.sort!''}</td>
        <td>${articleCategory.statusDescription!''}</td>
        <td>${articleCategory.updateTime?string('yyyy-MM-dd')}</td>
        <td><#if articleCategory.updatePerson??>${articleCategory.updatePerson.uname!''}</#if></td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editArticleCategory(${articleCategory.cid?c})">编辑</a> | 
            <a class="J_confirmurl" href="javascript:;" onclick="delArticleCategory(${articleCategory.cid?c})">删除</a>
        </td>
    </tr>
</#list>
</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
<div class="btn_wrap_fixed">
<#assign pageId="ArticleCategory" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>
$(function(){
    $("#J_checkall").click(function() {
        $('input[name="subbox"]').prop("checked",this.checked); 
    });
    var $subBox = $("input[name='subbox']");
    $subBox.click(function(){
        $("#J_checkall").prop("checked",$subBox.length == $("input[name='subbox']:checked").length ? true : false);
    });   
});
</script>
