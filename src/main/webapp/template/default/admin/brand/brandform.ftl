<#import "/common/app.ftl" as app>
<link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/uploadify/uploadify.css">
<script src="${app.basePath}/static/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="brandForm" action="${app.basePath}/admin/brand/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
    <tr>
        <th width="150">品牌名称：</th>
        <td><input type="text" name="name" class="easyui-textbox" value="${brand.name!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th width="150">品牌图片：</th>
        <td>
        	<div id="showImg">
        		<#if brand.imageUrl??>
        			<img width='200' height='200' src='${app.basePath}${brand.imageUrl!''}'/>
				</#if>
        	</div>
        	<input type="hidden" id="image" name="imageUrl" value="${brand.imageUrl!''}">
        	<input type="input" id="file_upload"/>
        </td>
    </tr>
    <tr>
        <th>排序值：</th>
        <td>
            <input type="text" name="sortBy" class="easyui-textbox" value="${brand.sortBy!''}" style="width:200px;height:30px" data-options="required:true" validType="number">
        </td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
           <label><input type="radio" name="status" value="1" <#if !brand.status?? || brand.status==1>checked="checked"</#if>>启用</label>
           <label><input type="radio" name="status" value="2" <#if brand.status?? && brand.status==2>checked="checked"</#if>>禁用</label>
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="id" value="${brand.id!''}"></td>
        <td>
            <button type="button" class="smt mr10" onclick="saveBrand()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
		$('#file_upload').uploadify({
				'multi'    : false, // 限制单图上传
				'formData' : {'type' : "product"},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '选择图片',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#showImg").empty();
					$("#showImg").append("<img width='200' height='200' src='"+basePath+result.savedFile+"'/>");
					$("#image").val(result.savedFile);
					$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	$.messager.alert('消息提醒','上传失败。');
		        }
		});
});
</script>
