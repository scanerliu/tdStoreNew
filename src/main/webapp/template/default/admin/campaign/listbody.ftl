<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
	<th align="center">用户</th>
	<th align="center">竞选名称</th>
	<th align="center">地区</th>
	<th align="center">排名</th>
	<th align="center">宣言</th>
	<th align="center">参加时间</th>
	<th align="center">状态</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if userCampaignList??>
<#list userCampaignList as camp>
    <tr>
    	<td align="center">
        	<input type="checkbox" value="${camp.id?c}" name="subbox" class="J_checkitem">
        </td>
        <td align="center"><#if camp.compaignUser??>${camp.compaignUser.uname!''}</#if></td>
        <td align="center">${camp.uname!''}</td>
        <td align="center"><#if camp.district??>${camp.district.name!''}</#if></td>
        <td align="center">${camp.level!'99999'}</td>
        <td align="center">${camp.declaration!''}</td>
        <td align="center"><#if camp.createTime??>${camp.createTime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
        <td align="center"><#if camp.status?? && camp.status ==1>满足条件<#else>进行中</#if></td>
        <td align="center">
        	<a class="J_showdialog" href="javascript:;" onclick="editUserCampaign(${camp.id?c})">编辑</a>
            |&nbsp;<a class="J_showdialog" href="javascript:;" onclick="delcampaign(${camp.id?c})">删除</a>
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
<#-- 搜索条件 begin-->
<input type="hidden" name="regionId" value="<#if sc.regionId??>${sc.regionId?c}<#else>-1</#if>">
<#-- 搜索条件 end-->
<#assign pageId="Campaign" />
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
