<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="adForm" action="${app.basePath}/admin/advert/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="adId" value="<#if ad??>${ad.id?c}</#if>">
<table class="table_form" width="100%">
    <tr>
        <th width="150">所属广告位：</th>
        <td>
        	<select name="adsId" calss="easyui-combobox" style="listWidth:200px;listHeight:30px;">
        		<#if adsenseList ??>
        		<#list adsenseList as ads>
        			<option value="${ads.id?c}" <#if ad?? && ad.adsId == ads.id>select</#if>>${ads.name!''}</option>
        		</#list>
        		</#if>
        	</select>
        </td>
    </tr>
    <tr>
        <th width="150">广告标题：</th>
        <td><input type="text" name="title" class="easyui-textbox" value="<#if ad??>${ad.title!''}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th>图片：</th>
        <td><input type="text" name="imageUrl" class="easyui-textbox" value="<#if ad??>${ad.imageUrl!''}</#if>" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th>链接地址：</th>
        <td>
            <input type="text" name="linkUrl" class="easyui-textbox" value="<#if ad??>${ad.linkUrl!''}</#if>" style="width:200px;height:30px" data-options="required:true" validType="url">
        </td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
            <input type="radio" name="status" value="1" <#if !ad?? || (ad.status?? && ad.status==1)>checked</#if>>启用
            <input type="radio" name="status" value="2" <#if ad?? && ad.status?? && ad.status==2>checked</#if>>禁用
        </td>
    </tr>
    <tr>
        <th>排序值：</th>
        <td>
            <input type="text" name="sort" class="easyui-textbox" value="<#if ad??>${ad.sort!'99'}</#if>" style="width:200px;height:30px" data-options="required:true" validType="">
        </td>
    </tr>
    <tr>
        <th>
        </th>
        <td>
            <button type="button" class="smt mr10" onclick="saveAd()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
