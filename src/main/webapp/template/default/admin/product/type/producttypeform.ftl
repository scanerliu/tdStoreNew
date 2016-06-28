<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="productForm" action="${app.basePath}/admin/producttype/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="productId" value="<#if productType??>${productType.id?c}</#if>">
<table class="table_form" width="100%">
    <tr>
        <th width="150">上一级分类：</th>
        <td>
        	<select name="parentId" class="easyui-combobox" style="width:200px;">
        		<option value="0" <#if !productType?? || productType.parentId == 0>selected="selected"</#if>>无上级分类</option>
        		<#if productTypeList ??>
        		<#list productTypeList as pro>
        			<option value="${pro.id?c}" <#if productType?? && productType.parentId == pro.id>selected="selected"</#if>>${pro.name!''}</option>
        			<#if pro.subList??>
        			<#list pro.subList as spro>
        				<option value="${spro.id?c}" <#if productType?? && productType.parentId == spro.id>selected="selected"</#if>>├ ${spro.name!''}</option>
        				<#if spro.subList??>
	        			<#list spro.subList as tpro>
	        				<option value="${tpro.id?c}" <#if productType?? && productType.parentId == tpro.id>selected="selected"</#if>>&emsp;├ ${tpro.name!''}</option>
	        			</#list>
	        			</#if>
        			</#list>
        			</#if>	
        		</#list>
        		</#if>
        	</select>
        </td>
    </tr>
    <tr>
        <th width="150">分类名称：</th>
        <td><input type="text" name="name" class="easyui-textbox" value="<#if productType??>${productType.name!''}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th>图片：</th>
        <td><input type="text" name="imageUrl" class="easyui-textbox" value="<#if productType??>${productType.imageUrl!''}</#if>" style="width:200px;height:30px" data-options="required:true" ></td>
    </tr>
    <#--
    <tr>
        <th>链接地址：</th>
        <td>
            <input type="text" name="linkUrl" class="easyui-textbox" value="<#if productType??>${ad.linkUrl!''}</#if>" style="width:200px;height:30px" data-options="required:true" validType="url">
        </td>
    </tr>
    -->
    <tr>
        <th>状态：</th>
        <td>
            <input type="radio" name="status" value="1" <#if !productType?? || (productType.status?? && productType.status==1)>checked</#if>>启用
            <input type="radio" name="status" value="2" <#if productType?? && productType.status?? && productType.status==2>checked</#if>>禁用
        </td>
    </tr>
    <tr>
        <th>排序值：</th>
        <td>
            <input type="text" name="sort" class="easyui-textbox" value="<#if productType??>${productType.sort!'99'}<#else>99</#if>" style="width:200px;height:30px" data-options="required:true" validType="">
        </td>
    </tr>
    <tr>
        <th>
        </th>
        <td>
            <button type="button" class="smt mr10" onclick="saveProductType()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
});
</script>
