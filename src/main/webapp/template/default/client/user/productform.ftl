<#import "/common/app.ftl" as app> <!-- 全国代理、商品编辑 --><form id="productForm" class="all_nation white_box" style="padding-bottom:40px;" method="post" action="${app.basePath}/user/saveproduct">    <div class="otitle">        <label for="" class="lab1 fl">商品编辑</label>    </div>    <div class="tabb obody">    	<input type="hidden" name="id" value="${tdProduct.id!''}">    	<input type="hidden" name="kind" id="pkind" value="${tdProduct.kind!'1'}">    	<#if tdProduct.id??>        	<input type="hidden" name="typeId" value="${tdProduct.typeId!''}">        </#if>    	<div class="fig fig3">            <label for="" class="lab1 fl Validform_label"><span>*</span>商品分类</label>            <select id="productTypeSelections" name="typeId" style="width:200px;" <#if tdProduct.id??>disabled<#else>onchange="changeType(this)" datatype="n"</#if>>        		<option value="" >--请选择--</option>        		<#if productTypeList ??>        		<#list productTypeList as pro>        			<option value="${pro.id?c}" disabled="">${pro.name!''}</option>        			<#if pro.subList??>        			<#list pro.subList as spro>        				<option value="${spro.id?c}" disabled="">├ ${spro.name!''}</option>        				<#if spro.subList??>	        			<#list spro.subList as tpro>	        				<option value="${tpro.id?c}" <#if !tpro.subList?? || tpro.subList?size == 0>isLastLevel="yes"</#if> <#if tdProduct?? && tdProduct.typeId == tpro.id>selected="selected"</#if>>&emsp;├ ${tpro.name!''}</option>	        			</#list>	        			</#if>        			</#list>        			</#if>	        		</#list>        		</#if>        	</select>        </div>        <div id="attrList">        	<#if taList??>	    		<#list taList as attr>	    			<#if (attr_index != 0) && (attr_index lt taList?size)><br/></#if>	    			<div class="fig fig5 slect">		            <label for="" class="lab1 fl"><span>*</span>${attr.attribute.name!''}</label>		            <section class="sig fl">		                <ul>		                	<#if attr.attribute.tdProductAttributeOptionList??>			    				<#list attr.attribute.tdProductAttributeOptionList as option>			    					<li>			    						<input class="fl chk" type="checkbox" onchange="flushTable()" id="spe_${attr.attribute.name!''}_${option.name!''}" name="${attr.attribute.name!''}" value="${option.name!''}" <#if option.status?? && option.status==1>checked</#if>>			    						<input type="text" value="${option.name!''}" class="fl" readonly="readonly">			    					</li>								</#list>							</#if>		                    <li>		                </ul>		                <ul id="selfconf">	    					<li>	    						<input class="fl chk" type="checkbox" name="${attr.attribute.name!''}" value="${option.name!''}">	    						<input type="text" value="" class="fl" placeholder="自定义规格值">	    					</li>		                </ul>		            </section>		        	</div>	    		</#list>				</#if>        </div>        <div class="fig fig3">            <table class="table1" id="skuTable">                <tr style="background:#ddd;">                    <td>规格值</td>                    <td>货品编号</td>                    <td>供应商价</td>                    <td>零售价</td>                    <td>市场价</td>                    <td>最高价</td>                    <td>最低价</td>                    <td>商品库存</td>                </tr>                <#if productSkuList??>                	<#list productSkuList as sku>                		<tr tid="${sku.specKey!''}" class="skuspec">		                    <td>${sku.specOptionsKey!''}</td>		                    <td><input type="text" name="skuList[${sku_index}].skuCode" value="${sku.skuCode!''}" datatype="n4-10" nullmsg="请填写货品编号！" errormsg="货品编号格式错误！只能填写4-10个数字"></td>		                    <td><input type="text" name="skuList[${sku_index}].supplierPrice" value="${sku.supplierPrice!'0'}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写供应商价！" errormsg="供应商价格式错误！"></td>		                    <td><input type="text" name="skuList[${sku_index}].salesPrice" value="${sku.salesPrice!'0'}" <#if tdProduct.kind?? && tdProduct.kind==3>readonly="readonly" </#if> datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写零售价！" errormsg="零售价格式错误！"></td>		                    <td><input type="text" name="skuList[${sku_index}].marketPrice" value="${sku.marketPrice!'0'}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写市场价！" errormsg="市场价格式错误！"></td>		                    <td><input type="text" name="skuList[${sku_index}].highPrice" value="${sku.highPrice!'0'}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写最高价！" errormsg="最高价格式错误！"></td>		                    <td><input type="text" name="skuList[${sku_index}].lowPrice" value="${sku.lowPrice!'0'}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写最低价！" errormsg="最低价格式错误！"></td>		                    <td><input type="text" name="skuList[${sku_index}].stock" value="${sku.stock!'0'}" datatype="n1-7" nullmsg="请填写库存！" errormsg="库存格式错误，只能填写1-7位数字！"></td>		                    <input type="hidden" name="skuList[${sku_index}].specifications" value="${sku.specKey!''}">		                </tr>                	</#list>                </#if>            </table>        </div>                <div class="fig fig1">            <label for="" class="lab1 fl Validform_label"><span>*</span>商品编号</label>            <input type="text" class="fl" name="code" value="${tdProduct.code!''}" datatype="*" nullmsg="请填写商品编号！">        </div>        <div class="fig fig1">            <label for="" class="lab1 fl Validform_label">商品品牌</label>            <select name="brandId">            	<option value="0">--请选择--</option>            	<#if brandList??>    				<#list brandList as brand>    					<option value="${brand.id!''}" <#if tdProduct.brandId?? && tdProduct.brandId==brand.id>selected="selected"</#if>>${brand.name!''}</option>					</#list>				</#if>            </select>        </div>        <div class="fig fig3">            <label for="" class="lab1 fl Validform_label"><span>*</span>商品名称</label>            <input type="text" class="fl" name="name" datatype="*6-50" nullmsg="请填写商品名称！" value="${tdProduct.name!''}">        </div>        <div class="fig fig3">            <label for="" class="lab1 fl Validform_label"><span>*</span>副标题</label>            <input type="text" class="fl" name="title" datatype="*6-50" nullmsg="请填写商品名称！" value="${tdProduct.title!''}">        </div>        <div class="fig fig1">            <label for="" class="lab1 fl"><span>*</span>运费</label>            <input type="text" class="fl" name="postage" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写商品运费！" errormsg="运费格式错误！" value="${tdProduct.postage!'0'}">        </div>        <div class="fig fig3">            <label for="" class="lab1 fl Validform_label">关键字</label>            <input type="text" class="fl" name="keyword" datatype="*0-30" value="${tdProduct.keyword!''}">        </div>        <div class="fig fig3">            <label for="" class="lab1 fl Validform_label">提示</label>            <input type="text" class="fl" name="tags" datatype="*6-50" value="${tdProduct.tags!''}">        </div>        <div class="fig fig2">            <label for="" class="lab1 fl"><span>*</span>上传照片</label>            <section class="sig fl">            	<div id="imgs">	        		<#if imgList??>	        		<#list imgList as img>	        			<input type='hidden' name='attId' id="attId${img.id?c}"  value='${img.id?c}'/>	        		</#list>	        		</#if>	        	</div>                <ul id="imglist">                    <li class="li1">                        <input type="file" id="file_uploads" style="width:1000px;"/>                    </li>                    <#if imgList??>	        		<#list imgList as img>	        		<li id="atid${img.id?c}">                        <img src="${app.basePath}${img.attachment!''}" alt="图片" presrc="${img.attachment!''}">                        <a href="javascript:;" title="删除图片" class="aclose" onclick="deleteImg(${img.id?c})"></a>                    </li>	        		</#list>	        		</#if>                </ul>                <input type='hidden' name='imageUrl' id="main_image_url"  value='${tdProduct.imageUrl!''}' datatype="*6-200" nullmsg="请上传产品图片！"/>                <p class="pig">小提示：亲，您可以 <a href="javascript:;" title="" class="">选择图片美化</a>，我们会帮您美化出简洁美观的产品图！当然，图片美化需要收取少量的加工费哦！</p>            </section>        </div>        <div class="fig fig6">            <label for="" class="lab1 fl"><span>*</span>商品介绍</label>            <!-- 下面这个section里面放《文字编辑器》 -->            <section class="sif fl">            	<textarea style="display:none;" rows="8" cols="50" id="ac" name="description"><#if detail??>${detail.description!''}</#if></textarea>			    <!-- 加载编辑器的容器 -->			    <script id="detail" name="detail" type="text/plain"><#if detail??>${detail.description!''}</#if></script>            </section>        </div>        <div class="fig fig6">            <label for="" class="lab1 fl"><span>*</span>包装配送</label>            <!-- 下面这个section里面放《文字编辑器》 -->            <section class="sif fl">            	<textarea style="display:none;" rows="8" cols="50" id="ac" name="description"><#if packDetail??>${packDetail.description!''}</#if></textarea>			    <!-- 加载编辑器的容器 -->			    <script id="packDetail" name="packDetail" type="text/plain"><#if packDetail??>${packDetail.description!''}</#if></script>            </section>        </div>        <div class="fig fig6">            <label for="" class="lab1 fl"><span>*</span>售后服务</label>            <!-- 下面这个section里面放《文字编辑器》 -->            <section class="sif fl">            	<textarea style="display:none;" rows="8" cols="50" id="ac" name="description"><#if afterSale??>${afterSale.description!''}</#if></textarea>			    <!-- 加载编辑器的容器 -->			    <script id="afterSale" name="afterSale" type="text/plain"><#if afterSale??>${afterSale.description!''}</#if></script>            </section>        </div>    </div>    <div class="sub_div" style="padding:0 80px;">    	<input type="submit" value="确认提交" class="btn" style="width:146px; height:40px;border-radius:0;">    	<input type="button" value="取消" class="btn" onclick="returnList()" style="width:146px; height:40px;border-radius:0;">    </div></form><!-- 配置文件 --><script type="text/javascript" src="${app.basePath}/static/js/ueditor/ueditor.config.js"></script><!-- 编辑器源码文件 --><script type="text/javascript" src="${app.basePath}/static/js/ueditor/ueditor.all.js"></script><!-- 实例化编辑器 --><script type="text/javascript">	$(document).ready(function(){	    UE.getEditor('detail');	    UE.getEditor('packDetail');	    UE.getEditor('afterSale');		});</script><script>$(function(){	$('#file_uploads').uploadify({			'multi'    : true, // 多图上传			'formData' : {'type' : "product"},			'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径			'fileObjName' : 'file',			'uploader' : basePath+'/product/upload/singleFile',    // 处理上传的Servlet			'buttonText' : '',			'onUploadSuccess' : function(file, data, response) {				var result = eval("("+data+")");				$("#imglist").append("<li id='atid"+result.atts+"'><img src='"+basePath+result.savedFile+"' presrc='"+result.savedFile+"' alt='图片'><a href='javascript:;' title='删除图片' class='aclose' onclick='deleteImg("+result.atts+")'></a></li>");				$("#imgs").append("<input type='hidden' name='attId' id='attId"+result.atts+"' value='"+result.atts+"'/>");			//	$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');	        },	        'onUploadError' : function(file, errorCode, errorMsg, errorString) {	        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);	        	$.messager.alert('消息提醒','上传失败。');	        }	});	$("#productForm").Validform({		tiptype:function(msg,o,cssctl){		    //msg：提示信息;		    //o:{obj:*,type:*,curform:*},		    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），		    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 		    //curform为当前form对象;		    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;		    if(o.type == 3){		    	alert(msg);		    }		},		beforeCheck:function(curform){			var url = $("#imglist img:first").attr("presrc");			$("#main_image_url").val(url);		},		beforeSubmit:function(curform){		 	//验证货品		 	if($("#skuTable .skuspec").length==0){		 		alert("请先勾选属性添加货品！");		 		return false;		 	}		 	openwaiting();		},		postonce:true, // 开启二次提交防御		ajaxPost:true, 		callback:function(data){			//返回数据data是json对象，{"msg":"demo info","code":"1"}			//info: 输出提示信息;			//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;			closewaiting();			alert(data.msg);			if(data.code == 1){				returnList();				refreshList();			}		}	});});</script>