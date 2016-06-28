<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a><#if tdUserSupplier.status == 1>&nbsp;&nbsp;&nbsp;<input type="button" class="btn" value="通过" onclick="goCheck(${tdUserSupplier.id!''}, true)">&nbsp;&nbsp;&nbsp;<input type="button" class="btn" value="拒绝" onclick="goCheck(${tdUserSupplier.id!''}, false)"></#if>&#12288;</div></div>

<div class="pad_lr_10">
<form id="userSupplierForm" action="${app.basePath}/admin/userSupplier/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
	<tr>
        <th>用户：</th>
        <td>${tdUserSupplier.user.uname!''}</td>
    </tr>
	<tr>
        <th width="150">资质类型：</th>
        <td>${tdUserSupplier.supplierTypeStr!''}</td>
    </tr>
    <tr>
        <th width="150">申请说明：</th>
        <td>${tdUserSupplier.note!''}</td>
    </tr>
    <tr>
        <th width="150">图片：</th>
        <td>
    		<#if imgList??>
    			<#list imgList as img>
    				<#if img_index != 0 && img_index%3 == 0><br/></#if>	    			
	    			<img width='200' height='200' src='img'/>
				</#list>
			</#if>
        </td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>${tdUserSupplier.statusStr!''}</td>
    </tr>
    <#if tdUserSupplier.status != 1>
	    <tr>
	        <th>回复：</th>
	        <td>${tdUserSupplier.reply!''}</td>
	    </tr> 
	<#else>
		<tr>
	        <th>回复：</th>
	        <td><textarea rows='4' cols='80' id='reply'></textarea></td>
	    </tr>   	
    </#if>
	<tr>
        <th>申请时间：</th>
		<td>${tdUserSupplier.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
    </tr>
    <#if tdUserSupplier.updateTime??>
		 <tr>
	        <th>审核时间：</th>
	        <td>${tdUserSupplier.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
	    </tr>   
	</#if>
	<#if tdUserSupplier.updatePerson??>
		 <tr>
	        <th>审核人：</th>
	        <td>${tdUserSupplier.updatePerson.uname!''}</td>
	    </tr>   
	</#if>
</table>
</form>
</div>