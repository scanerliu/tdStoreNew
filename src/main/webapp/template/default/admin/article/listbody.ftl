<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
	<a data-height="190" data-width="450" data-id="add" data-title="添加资讯" href="javascript:;" class="add fb J_showdialog" onclick="editArticle(0)"><em>添加资讯</em></a>&#12288;
	<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
	<th>ID</th>
	<th align="left">目录名称</th>
	<th align="left">创建人</th>
	<th align="center">地区</th>
	<th align="center">标题</th>
	<th align="left">状态</th>
	<th align="left">排序</th>
	<th align="left">热门推荐</th>
	<th align="left">更新时间</th>
	<th align="left">修改人</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if articleTitleList??>
<#list articleTitleList as article>
    <tr>
        <td align="center">
        <input type="checkbox" value="${article.aid?c}" name="subbox" class="J_checkitem"></td>
        <td align="center">${article.aid?c}</td>
        <td>${article.tdArticleCategory.name!''}</td>
        <td>${article.user.uname!''}</td>
        <td align="center">${article.regionId?c}</td>
        <td align="center">${article.title!''}</td>
        <td>${article.statusStr!''}</td>
        <td>${article.sort!'0'}</td>
        <td>${article.hotRecommendStr!''}</td>
        <td>${article.updateTime?string('yyyy-MM-dd')}</td>
        <td>${article.updatePerson.uname!''}</td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editArticle(${article.aid?c})">编辑</a> | 
            <a class="J_confirmurl" href="javascript:;" onclick="delArticle(${article.aid?c})">删除</a>
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
<#assign pageId="Article" />
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
