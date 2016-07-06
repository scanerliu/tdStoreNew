<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/uploadify/uploadify.css" />
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="productForm" action="${app.basePath}/admin/product/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="productId" value="<#if tdProduct??>${tdProduct.id?c}</#if>">
<div class="easyui-tabs" style="width:100%； height:750px;">
		<div title="商品信息" style="padding:10px">
			<table class="table_form" width="100%;hright:700px;">
		    <tr>
		        <th width="150">商品分类：</th>
		        <td>
		        	<select name="typeId" class="easyui-combobox" style="width:200px;">
		        		<option value="0" <#if !tdProduct?? || tdProduct.typeId == 0>selected="selected"</#if>>无上级分类</option>
		        		<#if productTypeList ??>
		        		<#list productTypeList as pro>
		        			<option value="${pro.id?c}" <#if tdProduct?? && tdProduct.typeId == pro.id>selected="selected"</#if>>${pro.name!''}</option>
		        			<#if pro.subList??>
		        			<#list pro.subList as spro>
		        				<option value="${spro.id?c}" <#if tdProduct?? && tdProduct.typeId == spro.id>selected="selected"</#if>>├ ${spro.name!''}</option>
		        				<#if spro.subList??>
			        			<#list spro.subList as tpro>
			        				<option value="${tpro.id?c}" <#if tdProduct?? && tdProduct.typeId == tpro.id>selected="selected"</#if>>&emsp;├ ${tpro.name!''}</option>
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
		        <th>商品类型：</th>
		        <td>
		            <input type="radio" name="kind" value="1" <#if !tdProduct?? || (tdProduct.kind?? && tdProduct.kind==1)>checked</#if>>普通商品&emsp;
		            <input type="radio" name="kind" value="2" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==2>checked</#if>>商品包&emsp;
		            <input type="radio" name="kind" value="3" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==3>checked</#if>>0元购&emsp;
		            <input type="radio" name="kind" value="4" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==4>checked</#if>>10元购&emsp;
		            <input type="radio" name="kind" value="5" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==5>checked</#if>>预售&emsp;
		            <input type="radio" name="kind" value="6" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==6>checked</#if>>秒杀&emsp;
		        </td>
		    </tr>
		    <tr>
		        <th width="150">名称：</th>
		        <td><input type="text" name="name" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.name!''}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="length[2,64]"></td>
		    </tr>
		    <tr>
		        <th width="150">副标题：</th>
		        <td><input type="text" name="title" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.title!''}</#if>"  style="width:200px;height:30px"  ></td>
		    </tr>
		    <tr>
		        <th width="150">编号：</th>
		        
		        <td><input type="text" name="code" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.code!''}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
		    </tr>
		    <tr>
		        <th width="150">参考价：</th>
		        
		        <td><input type="text" name="price" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.price?string('0.00')}</#if>"  style="width:200px;height:30px" data-options="required:true" validType="currency"></td>
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
		        		<#if tdProduct?? &&  tdProduct.imageUrl != "">
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
		        <th>新品推荐值：</th>
		        <td>
		            <input type="text" name="newRecommend" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.newRecommend!'0'}<#else>0</#if>" style="width:200px;height:30px" data-options="required:true" >
		            值越大越靠前
		        </td>
		    </tr>
		    <tr>
		        <th>热销推荐值：</th>
		        <td>
		            <input type="text" name="hotRecommend" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.hotRecommend!'0'}<#else>0</#if>" style="width:200px;height:30px" data-options="required:true" >
		            值越大越靠前
		        </td>
		    </tr>
		    <tr>
		        <th>精品推荐值：</th>
		        <td>
		            <input type="text" name="fineRecommend" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.fineRecommend!'0'}<#else>0</#if>" style="width:200px;height:30px" data-options="required:true" >
		            值越大越靠前
		        </td>
		    </tr>
		    <tr>
		        <th>分类推荐值：</th>
		        <td>
		            <input type="text" name="typeRecommend" class="easyui-textbox" value="<#if tdProduct??>${tdProduct.typeRecommend!'0'}<#else>0</#if>" style="width:200px;height:30px" data-options="required:true" >
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
			        	<div id="imgs">
			        		<#if imgList??>
			        		<#list imgList as img>
			        			<input type='hidden' name='attId' id="attId${img.id?c}"  value='${img.id?c}'/>
			        		</#list>
			        		</#if>
			        	</div>
			        	<input type="file" id="file_uploads"/>
			        	<div >
			        		<table>
								<tr id="showImgs">
					        		<#if imgList??>
					        		<#list imgList as img>
					        			<td style="text-align: center;" id="img${img.id?c}">
					        			<img width='100' height='100'  src='${app.basePath}${img.attachment!''}'/><br/>
					        			<a href="javascript:removeImg(${img.id?c});">删除</a>
					        			</td>
					        		</#list>
					        		</#if>
				        		</tr>
							</table>
			        	</div>
			        </td>
			    </tr>
			    <tr>
			    	<th width="150">图文详情：</th>
			        <td colspan="2" >
			        	<textarea style="display:none;" rows="5" cols="60" id="ac" name="description"><#if detail??>${detail.description!''}</#if></textarea>
					    <!-- 加载编辑器的容器 -->
					    <script id="detail" name="detail" type="text/plain"><#if detail??>${detail.description!''}</#if></script>
			        </td> 
		    	</tr>
			</table>
		</div>
		<div title="其他信息" style="padding:10px">
		<table class="table_form" width="100%;hright:700px;">
			<tr>
		    	<th width="150">包装配送：</th>
		        <td colspan="2" >
		        	<textarea style="display:none;" rows="5" cols="60" id="ac" name="description"><#if packDetail??>${packDetail.description!''}</#if></textarea>
				    <!-- 加载编辑器的容器 -->
				    <script id="packDetail" name="packDetail" type="text/plain"><#if packDetail??>${packDetail.description!''}</#if></script>
		        </td> 
	    	</tr>
	    	<tr>
		    	<th width="150">售后服务：</th>
		        <td colspan="2" >
		        	<textarea style="display:none;" rows="5" cols="60" id="ac" name="description"><#if afterSale??>${afterSale.description!''}</#if></textarea>
				    <!-- 加载编辑器的容器 -->
				    <script id="afterSale" name="afterSale" type="text/plain"><#if afterSale??>${afterSale.description!''}</#if></script>
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
 <button type="button" class="smt mr10" onclick="saveProduct()">保存</button>
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
	    UE.getEditor('packDetail');
	    UE.getEditor('afterSale');	
	});
</script>
<script>
	$(function(){
		$('#file_upload').uploadify({
				'multi'    : false, // 限制单图上传
				'formData' : {'type' : "product"},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '点击选择图片',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#showImg").empty();
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
					$("#showImg").empty();
					$("#showImgs").append("<td style='text-align: center;' id='img"+result.atts+"'><img width='100' height='100'  src='"+basePath+result.savedFile+"'/><br/><a href='javascript:removeImg("+result.atts+");'>删除</a></td>");
					$("#imgs").append("<input type='hidden' name='attId' id='attId"+result.atts+"' value='"+result.atts+"'/>");
				//	$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	$.messager.alert('消息提醒','上传失败。');
		        }
		});
	});

function removeImg(id)
{
	
	$.ajax({
		type : "post",
		data : {"attId":id},
		url : basePath+"/admin/product/deleteImg",
		success:function(data){
		} 
	})
	
	$("#attId"+id).remove();
	$("#img"+id).remove();
}
</script>
