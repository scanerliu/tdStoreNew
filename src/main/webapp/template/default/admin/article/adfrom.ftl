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
        	<select name="adsId" class="easyui-combobox" style="width:200px;">
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
	});
</script>
