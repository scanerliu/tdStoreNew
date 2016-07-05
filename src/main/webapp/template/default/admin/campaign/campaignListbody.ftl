<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
	<th>ID</th>
	<th align="center">地区</th>
	<th align="center">竞选名额</th>
	<th align="center">状态</th>
	<th align="center">创建时间</th>
	<th align="center">创建人</th>
	<th align="center">修改时间</th>
	<th align="center">修改人</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if campaignList??>
<#list campaignList as campaign>
    <tr>
    	<td align="center">
        	<input type="checkbox" value="${campaign.id?c}" name="subbox" class="J_checkitem">
        </td>
        <td align="center">${campaign.id?c}</td>
        <td align="center">${campaign.district.name!''}</td>
        <td align="center">${campaign.quota?c}</td>
        <td align="center">${campaign.statusStr!''}</td>
        <td align="center">${campaign.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
        <td align="center">${campaign.createPerson.uname!''}</td>
        <td align="center"><#if campaign.updateTime??>${campaign.updateTime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
        <td align="center"><#if campaign.updatePerson??>${campaign.updatePerson.uname!''}</#if></td>
        <td align="center">
        	<a class="J_showdialog" href="javascript:;" onclick="editcampaign(${campaign.id?c})">编辑</a>
            |&nbsp;<a class="J_showdialog" href="javascript:;" onclick="delcampaign(${campaign.id?c})">删除</a>
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
