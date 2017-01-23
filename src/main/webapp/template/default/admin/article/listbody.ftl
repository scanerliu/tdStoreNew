<#import "/common/app.ftl" as app>
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
        <td><#if article.tdArticleCategory??>${article.tdArticleCategory.name!''}</#if></td>
        <td>${article.user.uname!''}</td>
        <td align="center">
        	<#if article.regionId??>${article.regionId!''}</#if>
        </td>
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
<input type="hidden" name="keyword" value="${sc.keyword!''}"/>
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
