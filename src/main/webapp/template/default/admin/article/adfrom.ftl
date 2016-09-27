<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/uploadify/uploadify.css" />
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="adForm" action="${app.basePath}/admin/advert/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="adId" value="<#if ad??>${ad.id?c}</#if>">
<table class="table_form" width="100%">
    <tr>
        <th width="150">所属广告位：</th>
        <td>
        	<#if ad?? && ad.id??>
        		<#if adsenseList ??>
        		<#list adsenseList as ads>
        			<#if ad?? && ad.adsId == ads.id>${ads.name!''}</#if>
        		</#list>
        		</#if>
        		<input type="hidden" name="adsId" id="adsId" value="${ad.adsId?c}">
        	<#else>
        	<select name="adsId" id="adsId" style="width:200px;" onchange="changeAds(this)">
        		<option value="">--请选择--</option>
        		<#if adsenseList ??>
        		<#list adsenseList as ads>
        			<#if branch?? && branch==true>
	        			<#if ads.id==3||ads.id==4>
	        			<option value="${ads.id?c}" <#if ad?? && ad.adsId == ads.id>selected</#if>>${ads.name!''}</option>
	        			</#if>
        			<#else>
        				<option value="${ads.id?c}" <#if ad?? && ad.adsId == ads.id>selected</#if>>${ads.name!''}</option>
        			</#if>
        		</#list>
        		</#if>
        	</select>
        	</#if>
        </td>
    </tr>
    <tr>
        <th width="150">广告标题：</th>
        <td><input type="text" name="title" class="easyui-textbox" value="<#if ad??>${ad.title!''}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="length[2,32]"></td>
    </tr>
    <tr>
        <th width="150">图片：</th>
        <td>
        	<input type="hidden" id="image" name="imageUrl" value="<#if ad??>${ad.imageUrl!''}</#if>">
        	<input type="file" id="file_upload"/>
        	<div id="showImg">
        		<#if ad?? &&  ad.imageUrl != "">
        			<img width='200' height='200' src='${ad.imageUrl!''}'/>
				</#if>
        	</div>
        </td>
    </tr>
    <tr>
        <th>链接地址：</th>
        <td>
            <input type="text" name="linkUrl" class="easyui-textbox" value="<#if ad??>${ad.linkUrl!''}</#if>" style="width:200px;height:30px" data-options="required:true" >
        </td>
    </tr>
    <tr>
        <th>开始时间：</th>
        <td>
    		<input class="easyui-datetimebox" name="createTime" data-options="required:true" value="<#if ad?? && ad.createTime??>${ad.createTime?string('yyyy-MM-dd MM:DD:ss')}</#if>" style="width:150px"/>
    	</td>
   	</tr>
   	<tr>
        <th>结束时间：</th>
        <td>
    		<input class="easyui-datetimebox" name="endTime" value="<#if ad?? && ad.endTime??>${ad.endTime?string('yyyy-MM-dd MM:DD:ss')}</#if>" style="width:150px"/>
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
    <tr style="display:none;" id="typeIdTr">
        <th>商品分类：</th>
        <td>
            <select name="typeId" style="width:200px;" id="typeIdSelect">
            	<option value="">--请选择--</option>
        		<#if typeList ??>
        		<#list typeList as type>
        			<option value="${type.id?c}" <#if ad?? && ad.typeId?? && ad.typeId== type.id>selected</#if>>${type.name!''}</option>
        		</#list>
        		</#if>
        	</select>
        </td>
    </tr>
    <tr style="display:none;" id="floorIdTr">
        <th>pc首页楼层：</th>
        <td>
            <select name="floorId" style="width:200px;" id="floorIdSelect">
            	<option value="">--请选择--</option>
        		<#if floorList ??>
        		<#list floorList as floor>
        			<option value="${floor.fid?c}" <#if ad?? && ad.floorId?? && ad.floorId== floor.fid>selected</#if>>${floor.title!''}</option>
        		</#list>
        		</#if>
        	</select>
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
<#assign flooraids = [14, 15, 16, 17]>
<script>
	__flooraids = [14, 15, 16, 17];
	$(function(){
		$('#file_upload').uploadify({
				'multi'    : false, // 限制单图上传
				'formData' : {'type' : "ad"},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '点击选择图片',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#showImg").empty();
					$("#showImg").append("<img width='200' height='200' src='"+basePath+result.savedFile+"'/>");
					$("#image").val(basePath+result.savedFile);
					$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	$.messager.alert('消息提醒','上传失败。');
		        }
		});
		<#if ad?? && ad.adsId?? && ad.adsId==6>
			$("#typeIdTr").show();
		</#if>
		<#if ad?? && ad.adsId?? && flooraids?seq_contains(ad.adsId)>
			$("#floorIdTr").show();
		</#if>
	});
</script>
