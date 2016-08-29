<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/uploadify/uploadify.css" />
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="productPackageForm" action="${app.basePath}/admin/productPackage/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="id" value="<#if tdProduct?? && tdProduct.id??>${tdProduct.id?c}</#if>">
<div class="easyui-tabs" style="width:100%； height:750px;">
		<div title="商品信息" style="padding:10px">
			<table class="table_form" width="100%;hright:700px;">
		    <tr>
		        <th width="150">名称：</th>
		        <td><input type="text" name="name" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.name!''}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="length[2,64]"></td>
		    </tr>
		    <tr>
		        <th width="150">副标题：</th>
		        <td><input type="text" name="title" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.title!''}</#if>"  style="width:200px;height:30px"  ></td>
		    </tr>
		    <tr>
		        <th width="150">上架状态：</th>
		        <td>
			        <input type="radio" name="onshelf" value="true" <#if !tdProduct?? || (tdProduct.onshelf?? && tdProduct.onshelf)>checked</#if>>上架
			        <input type="radio" name="onshelf" value="false" <#if tdProduct?? && tdProduct.onshelf?? && !tdProduct.onshelf>checked</#if>>下架
		        </td>
		    </tr>
		    <tr>
		        <th width="150">包含商品：</th>
		        <td><button type="button" class="btn" onclick="openDialog()">添加</button></td>
		    </tr>
		    <tr>
		        <th width="150"></th>
		        <td id="skusTr">
		        	<#if ppiList??>
						<#list ppiList as ppi>
							<div style="float:left;margin-left:10px;" id="ppiDiv" onclick="removeSkuId(this)">
								<div id="productImage">
									<img width="100px" height="100px" src="${app.basePath}${ppi.productImage!''}">
								</div>
								<div>
									商品名称：<label id="productName">${ppi.productName!''}</label><br>
									销售价：<label id="price"><#if ppi.price??>${ppi.price?c}</#if></label><br>
									<input type="hidden" id="specifications" value="${ppi.specKey!'' }">
								</div>
							</div>
						</#list> 
					</#if>
		        </td>
		    </tr>
		    <tr>
		        <th width="150">编号：</th>
		        <td><input type="text" name="code" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.code!''}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
		    </tr>
		    <tr>
		        <th width="150">参考价：</th>
		        
		        <td><input type="text" name="price" class="easyui-textbox" value="<#if tdProduct?? && tdProduct.price??>${tdProduct.price?string('0.00')}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="currency"></td>
		    </tr>
		    <tr>
		        <th width="150">运费：</th>
		        
		        <td><input type="text" name="postage" class="easyui-textbox" value="<#if tdProduct?? && tdProduct.postage??>${tdProduct.postage?string('0.00')}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="currency"></td>
		    </tr>
		    <tr>
		        <th width="150">库存：</th>
		        
		        <td><input type="text" name="quantum" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.quantum!'0'}</#if>"  style="width:200px;height:30px" data-options="required:true" ></td>
		    </tr>
		    <tr>
		        <th width="150">封面图片：</th>
		        <td>
		        	<input type="hidden" id="image" name="imageUrl" value="<#if tdProduct??>${tdProduct.imageUrl!''}</#if>">
		        	<input type="file" id="file_upload"/>
		        	<div id="showImg">
		        		<#if tdProduct?? &&  tdProduct.imageUrl??>
		        			<img width='100' height='100' src='${app.basePath}${tdProduct.imageUrl!''}'/>
						</#if>
		        	</div>
		        </td>
		    </tr>
		    <tr>
		        <th>状态：</th>
		        <td>
		            <input type="radio" name="status" value="1" <#if !tdProduct?? || (tdProduct.status?? && tdProduct.status==1)>checked</#if>>正常
		            <input type="radio" name="status" value="2" <#if tdProduct?? && tdProduct.status?? && tdProduct.status==2>checked</#if>>待审核
		            <input type="radio" name="status" value="3" <#if tdProduct?? && tdProduct.status?? && tdProduct.status==3>checked</#if>>审核不通过
		        </td>
		    </tr>
		    <tr>
		        <th>关键字：</th>
		        <td>
	            	<input type="text" name="keyword" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.keyword!''}</#if>" style="width:200px;height:30px" >
		        </td>
		    </tr>
		    <tr>
		        <th>综合排序值：</th>
		        <td>
		            <input type="text" name="sort" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.sort!'0'}<#else>0</#if>" style="width:200px;height:30px" data-options="required:true" >
		            值越大越靠前
		        </td>
		    </tr>
		    <tr>
		        <th>提示：</th>
		        <td>
		            <input type="text" name="tags" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.tags!''}</#if>" style="width:200px;height:30px" >
		        </td>
		    </tr>
		</table>
		</div>
		<div title="图文信息" style="padding:10px">
			<table class="table_form" width="100%;hright:700px;">
				<tr>
			        <th width="150">展示图片：</th>
			        <td>
			        	<div id="attachmentDiv">
			        		<#if attachmentList??>
			        			<#list attachmentList as attachment>
			        				<img width='100' height='100' src="${app.basePath}${attachment.attachment }" onclick="$(this).remove()" alt="图片丢失"/>
			        			</#list>
			        		</#if>
			        	</div>
			        	<input type="file" id="file_uploads"/>
			        	<input type="hidden" id="attachmentUrls" name="attachmentUrls" value="">
			        </td>
			    </tr>
			    <tr>
			    	<th width="150">图文详情：</th>
			        <td colspan="2">
			        	<textarea style="display:none;" rows="5" cols="60" id="detailArea" name="detail"><#if detail??>${detail.description!''}</#if></textarea>
					    <!-- 加载编辑器的容器 -->
					    <script id="detail" type="text/plain"><#if detail??>${detail.description!''}</#if></script>
			        </td> 
		    	</tr>
			</table>
		</div>
		<div title="其他信息" style="padding:10px">
		<table class="table_form" width="100%;hright:700px;">
			<tr>
		    	<th width="150">包装配送：</th>
		        <td colspan="2" >
		        	<textarea style="display:none;" rows="5" cols="60" id="dispatchArea" name="dispatch"><#if dispatch??>${dispatch.description!''}</#if></textarea>
				    <!-- 加载编辑器的容器 -->
				    <script id="dispatch" type="text/plain"><#if dispatch??>${dispatch.description!''}</#if></script>
		        </td> 
	    	</tr>
	    	<tr>
		    	<th width="150">售后服务：</th>
		        <td colspan="2" >
		        	<textarea style="display:none;" rows="5" cols="60" id="serviceArea" name="service"><#if service??>${service.description!''}</#if></textarea>
				    <!-- 加载编辑器的容器 -->
				    <script id="service" type="text/plain"><#if service??>${service.description!''}</#if></script>
		        </td> 
	    	</tr>
		</table>
		</div>
		<div title="销售状况" style="padding:10px">
		<table class="table_form" width="100%;hright:700px;">
			<tr>
				<th width="150">浏览次数：</th>
				<td><#if productStat??>${productStat.viewCount!'0'}<#else>0</#if></td>
			</tr>
			<tr>
				<th width="150">总销售数量：</th>
				<td><#if productStat??>${productStat.buyCount!'0'}<#else>0</#if></td>
			</tr>
			<tr>
				<th width="150">总销售次数：</th>
				<td><#if productStat??>${productStat.buyTimes!'0'}<#else>0</#if></td>
			</tr>
			<tr>
				<th width="150">总评论数：</th>
				<td><#if productStat??>${productStat.reviewCount!'0'}<#else>0</#if></td>
			</tr>
			<tr>
				<th width="150">好评数：</th>
				<td><#if productStat??>${productStat.positiveRate!'0'}<#else>0</#if></td>
			</tr>
			<tr>
				<th width="150">中评数：</th>
				<td><#if productStat??>${productStat.neutralRate!'0'}<#else>0</#if></td>
			</tr>
			<tr>
				<th width="150">差评数：</th>
				<td><#if productStat??>${productStat.negativeRate!'0'}<#else>0</#if></td>
			</tr>
		</table>
		</div>
</div>
 <div id="packageItemInputDiv"></div>
 <input type="hidden" name="tableData" value="" id="formTableData">
 <input type="hidden" id="skuIdStrInput" name="skuIdStr" value="<#if skuIdStr??>${skuIdStr}</#if>" id="skuIdStr">
 <button type="button" class="smt mr10" onclick="setData()">保存</button>
</form>

</div>
<!-- 配置文件 -->
<script type="text/javascript" src="${app.basePath}/static/js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${app.basePath}/static/js/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
	$(document).ready(function(){
	    UE.getEditor('detail');
	    UE.getEditor('dispatch');
	    UE.getEditor('service');	
	});
</script>
<script>
	$(function(){
		// 图片上传
		$('#file_upload').uploadify({
				'multi'    : false, // 限制单图上传
				'formData' : {'type' : "product"},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '点击选择图片',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#showImg").html("");
					$("#showImg").append("<img width='100' height='100' src='"+basePath+result.savedFile+"'/>");
					$("#image").val(result.savedFile);
					// $.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	$.messager.alert('消息提醒','上传失败。');
		        }
		});
		$('#file_uploads').uploadify({
				'multi'    : true, // 多图上传
				'formData' : {'type' : "product"},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/product/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '点击选择图片',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#attachmentDiv").append("<img width='100' height='100' style='margin-left: 10px;' onclick='$(this).remove()' src='"+basePath+result.savedFile+"'/>");
				//	$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	$.messager.alert('消息提醒','上传失败。');
		        }
		});
		// 货品展示
		removeSkuIdStrBlank();
		//flushSkuShow();
	});
	
	// 提交前设置百度编辑器和展示图片的值
	function setData(){
		removeSkuIdStrBlank();
		var sisi = $("#skuIdStrInput").val();
		if($("#skusTr").find("div").length == 0){
			$.messager.alert('提示','包含的商品不能为空！');
			return;
		}
		getPackageItemData();
		var detail = UE.getEditor('detail').getContent();
		$("#detailArea").html(detail);
		var dispatch = UE.getEditor('dispatch').getContent();
		$("#dispatchArea").html(dispatch);
		var service = UE.getEditor('service').getContent();
		$("#serviceArea").html(service);
		var imgs = $("#attachmentDiv").find("img");
		var imgsStr = "";
		for(var i = 0; i < imgs.length; i ++){
			var imgsrc = $(imgs[i]).attr("src");
			imgsrc = imgsrc.replace(basePath, "");
			imgsStr = imgsStr + imgsrc;
			if(i < imgs.length - 1){
				imgsStr = imgsStr + ":";	
			}
		}
		$("#attachmentUrls").val(imgsStr);
		console.log(imgsStr);
		removeSkuIdStrBlank();
		
		
		var f = $('#productPackageForm').form('enableValidation').form('validate');
		if(f){
			$("#productPackageForm").form("submit",{
				success : function(data){
					var jsonData = eval('('+data+')');
					$.messager.alert('提示',jsonData.msg);
					if(jsonData.code == "1"){
						returnList();
						refreshList();
					}
				}
			});
		}
	}
	
	function openDialog(){
		var url = basePath+"/admin/productPackage/skuList"; 	
		$('#pskuswindow').window({
			title: '货品列表',
			width: 800,
			height: 400,
			closed: false,
			cache: false,
			modal: true,
			href: url,
			onClose: function() {
                $(this).window({closed:true});
            }
		});
	}
	
	function getPackageItemData(){
		var pidInputStr = "";
		var skus = $("#skusTr").find("div[id='ppiDiv']");
		for(var i = 0; i < skus.length; i ++){
			var productImages = $(skus[i]).find("#productImage img").attr("src");
			var productNames = $(skus[i]).find("#productName").text();
			var prices = $(skus[i]).find("#price").text();
			var specs = $(skus[i]).find("#specifications").val();
			var piInput = "<input type='hidden' name='productImages' value='"+productImages+"'>";
			var pnInput = "<input type='hidden' name='productNames' value='"+productNames+"'>";
			var prInput = "<input type='hidden' name='prices' value='"+prices+"'>";
			var spInput = "<input type='hidden' name='specs' value='"+specs+"'>";
			console.log(piInput);
			console.log(pnInput);
			console.log(prInput);
			console.log(spInput);
			console.log("-----------------------------------------------");
			pidInputStr = pidInputStr + piInput + pnInput + prInput + spInput;
		}
		$("#packageItemInputDiv").html(pidInputStr);
	}
</script>
