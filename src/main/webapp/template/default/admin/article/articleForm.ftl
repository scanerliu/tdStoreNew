<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/uploadify/uploadify.css">
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="articleForm" action="${app.basePath}/admin/manager/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
	<tr>
        <th>资讯目录：</th>
        <td>
            <select id="cid" name="cid">
	            <#if articleCategoryList??>
	            	<#list articleCategoryList as ac>
			            <option value="${ac.cid?c}" <#if tdArticleTitle.cid?? && tdArticleTitle.cid==ac.cid>selected="selected"</#if>>${ac.name!''}</option>	            	
					</#list>
				</#if>
	        </select>
        </td>
    </tr>
	<tr>
        <th width="150">标题：</th>
        <td><input type="text" name="title" class="easyui-textbox" value="${tdArticleTitle.title!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,100]"></td>
    </tr>
    <tr>
        <th width="150">关键字：</th>
        <td><input type="text" name="keyword" class="easyui-textbox" value="${tdArticleTitle.keyword!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,100]"></td>
    </tr>
    <tr>
        <th width="150">摘要：</th>
        <td><textarea rows="5" cols="60" name="summary">${tdArticleTitle.summary!''}</textarea></td>
    </tr>
    <tr>
        <th width="150">图片：</th>
        <td>
        	<input type="text" name="imageUrl" class="easyui-textbox" value="${tdArticleTitle.imageUrl!''}" style="width:200px;height:30px;" data-options="required:true" validType="length[2,100]">
        	<input type="file" id="file_upload"/>
        	<div id="showImg"></div>
        </td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
            <input type="radio" name="status" value="1" <#if !tdArticleTitle.status?? || (tdArticleTitle.status?? && tdArticleTitle.status==1)>checked</#if>>启用
            <input type="radio" name="status" value="2" <#if tdArticleTitle.status?? && tdArticleTitle.status==2>checked</#if>>禁用
        </td>
    </tr>
    <tr>
        <th>排序：</th>
        <td>
            <input type="text" name="sort" class="easyui-textbox" value="${tdArticleTitle.sort!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]">
        </td>
    </tr>
    <tr>
        <th>热门：</th>
        <td>
            <input type="radio" name="hotRecommend" value="1" <#if !tdArticleTitle.hotRecommend?? || (tdArticleTitle.hotRecommend?? && tdArticleTitle.hotRecommend==1)>checked</#if>>热门
            <input type="radio" name="hotRecommend" value="2" <#if tdArticleTitle.status?? && tdArticleTitle.status==2>checked</#if>>非热门
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="aid" value="${tdArticleTitle.aid!''}"></td>
        <td>
            <button type="button" class="smt mr10" onclick="saveArticle()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
	$(function(){
		var type = "${type!''}";
		$('#file_upload').uploadify({
				'formData' : {'type' : type},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '浏览2',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");	
		            alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + result.msg);
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        }
		});
	});
</script>
