<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="adsenseForm" action="${app.basePath}/admin/advertposition/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="id" value="<#if adsense??>${adsense.id?c}</#if>">
<table class="table_form" width="100%">
    <tr>
        <th width="150">广告位名称：</th>
        <td><input type="text" name="name" class="easyui-textbox" value="<#if adsense??>${adsense.name!''}</#if>" <#if adsense??>reaonly</#if> style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">*广告位名称，添加后不可修改</td>
    </tr>
    <tr>
        <th>宽度：</th>
        <td><input type="text" name="width" class="easyui-textbox" value="<#if adsense??>${adsense.width!'0'}</#if>" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">px</td>
    </tr>
    <tr>
        <th>高度：</th>
        <td>
            <input type="text" name="height" class="easyui-textbox" value="<#if adsense??>${adsense.height!'0'}</#if>" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">px
        </td>
    </tr>
    <#--
    <tr>
        <th>状态：</th>
        <td>
            <input type="radio" name="ustatus" value="1" <#if !manager.ustatus?? || (manager.ustatus?? && manager.ustatus==1)>checked</#if>>启用
            <input type="radio" name="ustatus" value="2" <#if manager.ustatus?? && manager.ustatus==2>checked</#if>>禁用
        </td>
    </tr>
    -->
    <tr>
        <td>
        	<input type="hidden" name="adkey" value="<#if adsense??>${adsense.adkey!''}</#if>">
        	<input type="hidden" name="updateBy" value="<#if adsense??>${adsense.updateBy?c}</#if>">
        </td>
        <td>
            <button type="button" class="smt mr10" onclick="saveAdsense()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
