<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="benefitForm" action="${app.basePath}/admin/benefit/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
    <tr>
        <th width="150">分润类型：</th>
        <td>
        	<select name="benefitType">
        		<option value="1">代金券、供应商和分销商产品分润</option>
            	<option value="2"<#if benefit.benefitType?? && benefit.benefitType==2>selected=selected</#if>>零元购分润</option>
            	<option value="3"<#if benefit.benefitType?? && benefit.benefitType==3>selected=selected</#if>>代理加盟费分润</option>
            	<option value="4"<#if benefit.benefitType?? && benefit.benefitType==4>selected=selected</#if>>代理加盟费分润</option>
            	<option value="5"<#if benefit.benefitType?? && benefit.benefitType==5>selected=selected</#if>>代理加盟费分润</option>
            	<option value="6"<#if benefit.benefitType?? && benefit.benefitType==6>selected=selected</#if>>代理加盟费分润</option>
            	<option value="7"<#if benefit.benefitType?? && benefit.benefitType==7>selected=selected</#if>>代理加盟费分润</option>
            	<option value="11" <#if benefit.benefitType?? && benefit.benefitType==11>selected=selected</#if>>普通产品购买分润</option>
            	<option value="12"<#if benefit.benefitType?? && benefit.benefitType==12>selected=selected</#if>>零元购分润</option>
            </select>
        </td>
    </tr>
    <tr>
        <th>成本价：</th>
        <td>
            <input type="text" name="supplierPrice" class="easyui-textbox" value="${product.supplierPrice!''}" style="width:200px;height:30px" data-options="required:true" validType="price">
        </td>
    </tr>
    <tr>
        <th>销售价：</th>
        <td>
           <input type="text" name="salesPrice" class="easyui-textbox" value="${product.salesPrice!''}" style="width:200px;height:30px" data-options="required:true" validType="price">
        </td>
    </tr>
    <!--
    <tr>
        <th>代理类型：</th>
        <td>
            <select name="groupId">
            	<option value="1">单类代理</option>
            	<option value="2"<#if product.groupId?? && product.groupId==2>selected=selected</#if>>分公司</option>
            </select>
        </td>
    </tr>
    <tr>
        <th>级别：</th>
        <td>
            <select name="level">
            	<option value="1">一级</option>
            	<option value="2" <#if product.level?? && product.level==2>selected=selected</#if>>二级</option>
            	<option value="3" <#if product.level?? && product.level==3>selected=selected</#if>>三级</option>
            	<option value="4" <#if product.level?? && product.level==4>selected=selected</#if>>四级</option>
            </select>
        </td>
    </tr>
    -->
    <tr>
        <th>产品描述：</th>
        <td>
            <input type="text" name="note" class="easyui-textbox" value="${product.note!''}" style="width:800px;height:300px" data-options="multiline:true,required:true" validType="length[2,200]">
        </td>
    </tr>
    <tr>
        <td><input type="hidden" name="id" value="${product.id!''}"></td>
        <td>
            <button type="button" class="smt mr10" onclick="saveAgentProduct()">保存</button>
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
