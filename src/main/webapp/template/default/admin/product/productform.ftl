<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
<#--<script src="${app.basePath}/static/js/jquery-1.7.2.js" type="text/javascript"></script>-->
<link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/uploadify/uploadify.css" />
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="productForm" action="${app.basePath}/admin/product/save" method="post">
<input type="hidden" name="productId" value="<#if tdProduct??>${tdProduct.id!''}</#if>">
<div class="easyui-tabs" style="width:100%； height:750px;">
		<div title="商品信息" style="padding:10px">
			<table class="table_form" width="100%;hright:700px;">
		    <tr>
		        <th width="150">商品分类：</th>
		        <td>
		        	<#if tdProduct?? && tdProduct.id??>
		        		<#if productTypeList ??>
		        		<#list productTypeList as pro>
		        			<#if tdProduct?? && tdProduct.typeId == pro.id>${pro.name!''}</#if>
		        			<#if pro.subList??>
		        			<#list pro.subList as spro>
		        				<#if tdProduct?? && tdProduct.typeId == spro.id>${spro.name!''}</#if>
		        				<#if spro.subList??>
			        			<#list spro.subList as tpro>
			        				<#if tdProduct?? && tdProduct.typeId == tpro.id>${tpro.name!''}</#if>
			        			</#list>
			        			</#if>
		        			</#list>
		        			</#if>	
		        		</#list>
		        		</#if>
		        		<input type="hidden" name="typeId" value="<#if tdProduct??>${tdProduct.typeId!''}</#if>">
		        	<#else>
		        	<select id="productTypeSelections" name="typeId" style="width:200px;" onchange="changeType(this)" datatype="n" nullmsg="请选择商品分类！">
		        		<option value="" >--请选择--</option>
		        		<#if productTypeList ??>
		        		<#list productTypeList as pro>
		        			<option value="${pro.id?c}" disabled="">${pro.name!''}</option>
		        			<#if pro.subList??>
		        			<#list pro.subList as spro>
		        				<option value="${spro.id?c}" disabled="">├ ${spro.name!''}</option>
		        				<#if spro.subList??>
			        			<#list spro.subList as tpro>
			        				<option value="${tpro.id?c}" <#if !tpro.subList?? || tpro.subList?size == 0>isLastLevel="yes"</#if> <#if tdProduct?? && tdProduct.typeId == tpro.id>selected="selected"</#if>>&emsp;├ ${tpro.name!''}</option>
			        			</#list>
			        			</#if>
		        			</#list>
		        			</#if>	
		        		</#list>
		        		</#if>
		        	</select>
		        	</#if>
		        </td>
		    </tr>
		    <tr>
		    	<th>货品规格：</th>
		    	<td id="attrList">
		    		<#assign selindex = 1>
		    		<#if taList??>
			    		<#list taList as attr>
			    			<#if attr.attribute??>
			    			<#if (attr_index != 0) && (attr_index lt taList?size)></#if>
			    			<div class="fig fig5 slect">
				            <label for="" class="lab1 fl"><span>*</span>${attr.attribute.name!''}</label>
				            <section class="sig fl">
				                <ul>
				                	<#if attr.attribute.tdProductAttributeOptionList??>
					    				<#list attr.attribute.tdProductAttributeOptionList as option>
					    					<li>
					    						<input class="fl chk" type="checkbox" onchange="flushTable()" id="spe_${attr.attribute.name!''}_${option.name!''}" name="${attr.attribute.name!''}" value="${option.name!''}" <#if option.status?? && option.status==1>checked</#if>>
					    						<input type="text" value="${option.name!''}" class="fl" readonly="readonly">
					    					</li>
					    					<#assign selindex = selindex+1>
										</#list>
									</#if>
				                    <li>
				                </ul>
				                <ul>
									<li>
										<input class="fl chk selfconf" type="checkbox" name="${attr.attribute.name!''}" value="${selindex}" onclick="checkAttributeSelect(this)">
										<input type="text" name="avs" value="" class="fl selfconf" placeholder="自定义值" maxlength="20" keyup="checkAttribute(this)" onblur="checkAttribute(this)">
										<#assign selindex = selindex+1>
									</li>
					            </ul>
				            </section>
				        	</div>
				        	</#if>
			    		</#list>	
					</#if>
		    	</td>
		    </tr>
		    <tr>
		    	<th>货品组合：</th>
		    	<td>
		    		<table class="table1" id="skuTable">
		                <tr style="background:#ddd;">
		                    <td>规格值</td>
		                    <td>货品编号</td>
		                    <td>供应商价</td>
		                    <td>零售价</td>
		                    <td>市场价</td>
		                    <td>最高价</td>
		                    <td>最低价</td>
		                    <td>商品库存</td>
		                </tr>
		                <#if productSkuList?? && tdProduct.specification?? && tdProduct.specification==true>
		                	<#list productSkuList as sku>
		                		<tr tid="${sku.specKey!''}" class="skuspec">
				                    <td>${sku.specOptionsKey!''}</td>
				                    <td><input type="text" name="skuList[${sku_index}].skuCode" value="${sku.skuCode!''}" datatype="n4-10" nullmsg="请填写货品编号！" errormsg="货品编号格式错误！只能填写4-10个数字"></td>
				                    <td><input type="text" name="skuList[${sku_index}].supplierPrice" value="${sku.supplierPrice!'0'}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写供应商价！" errormsg="供应商价格式错误！"></td>
				                    <td><input type="text" name="skuList[${sku_index}].salesPrice" value="${sku.salesPrice!'0'}" <#if tdProduct.kind?? && tdProduct.kind==3>readonly="readonly" </#if> datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写零售价！" errormsg="零售价格式错误！"></td>
				                    <td><input type="text" name="skuList[${sku_index}].marketPrice" value="${sku.marketPrice!'0'}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写市场价！" errormsg="市场价格式错误！"></td>
				                    <td><input type="text" name="skuList[${sku_index}].highPrice" value="${sku.highPrice!'0'}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写最高价！" errormsg="最高价格式错误！"></td>
				                    <td><input type="text" name="skuList[${sku_index}].lowPrice" value="${sku.lowPrice!'0'}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写最低价！" errormsg="最低价格式错误！"></td>
				                    <td><input type="text" name="skuList[${sku_index}].stock" value="${sku.stock!'0'}" datatype="n1-7" nullmsg="请填写库存！" errormsg="库存格式错误，只能填写1-7位数字！"></td>
				                    <input type="hidden" name="skuList[${sku_index}].specifications" value="${sku.specKey!''}">
				                </tr>
		                	</#list>
		                </#if>
		            </table>
		    	</td>
		    </tr>
		    <tr>
		    	<th><label for="" class="lab1"><span>*</span>一口价和总库存</label>：</th>
		    	<td id="comprodTab">
			            <table class="table1">
			                <tr style="background:#ddd;">
			                	<td>货品编号</td>
			                    <td>供应商价</td>
			                    <td>零售价</td>
			                    <td>市场价</td>
			                    <td>最高价</td>
			                    <td>最低价</td>
			                    <td>商品总库存</td>
			                </tr>
			        		<tr class="skuspec">
			        			<td><input type="text" id="cskuCode" name="skuCode" value="${tdProduct.skuCode!''}" datatype="n4-10" nullmsg="请填写货品编号！" errormsg="货品编号格式错误！只能填写4-10个数字"></td>
			                    <td><input type="text" id="csupplierPrice" name="supplierPrice" value="${tdProduct.supplierPrice!''}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写供应商价！" errormsg="供应商价格式错误！"></td>
			                    <td><input type="text" id="cprice" name="price" value="${tdProduct.price!''}" <#if tdProduct.kind?? && tdProduct.kind==3>readonly="readonly" </#if> datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写零售价！" errormsg="零售价格式错误！"></td>
			                    <td><input type="text" id="cmarketPrice" name="marketPrice" value="${tdProduct.marketPrice!''}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写市场价！" errormsg="市场价格式错误！"></td>
			                    <td><input type="text" id="chighPrice" name="highPrice" value="${tdProduct.highPrice!''}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写最高价！" errormsg="最高价格式错误！"></td>
			                    <td><input type="text" id="clowPrice" name="lowPrice" value="${tdProduct.lowPrice!''}" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写最低价！" errormsg="最低价格式错误！"></td>
			                    <td><input type="text" id="cquantum" name="quantum" value="${tdProduct.quantum!''}" datatype="n1-7" nullmsg="请填写库存！" errormsg="库存格式错误，只能填写1-7位数字！"></td>
			                </tr>
			            </table>
		    	</td>
		    </tr>
		    <tr>
		        <th>商品类型：</th>
		        <td>
		            <input type="radio" name="kind" onclick="changekind(1)" value="1" <#if !tdProduct.kind?? || (tdProduct.kind?? && tdProduct.kind==1)>checked</#if>>普通商品&emsp;
		            <input type="radio" name="kind" onclick="changekind(this.value)" value="3" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==3>checked</#if>>0元购&emsp;
		            <!--<input type="radio" name="kind" onclick="changekind(this.value)" value="4" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==4>checked</#if>>10元购&emsp;-->
		            <input type="radio" name="kind" onclick="changekind(this.value)" value="5" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==5>checked</#if>>预售&emsp;
		            <input type="radio" name="kind" onclick="changekind(this.value)" value="6" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==6>checked</#if>>秒杀&emsp;
		            <input type="radio" name="kind" onclick="changekind(this.value)" value="7" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==7>checked</#if>>全积分兑换&emsp;
		            <input type="radio" name="kind" onclick="changekind(this.value)" value="8" <#if tdProduct?? && tdProduct.kind?? && tdProduct.kind==8>checked</#if>>部分积分兑换&emsp;
		        </td>
		    </tr>
		     <tr>
		        <th>品牌：</th>
		        <td>
		            <select name="brandId">
		            	<option value="0">--请选择--</option>
		            	<#if brandList??>
		    				<#list brandList as brand>
		    					<option value="${brand.id!''}">${brand.name!''}</option>
							</#list>
						</#if>
		            </select>
		        </td>
		    </tr>
		    <tr>
		        <th width="150">名称：</th>
		        <td><input type="text" name="name" value="<#if tdProduct??>${tdProduct.name!''}</#if>"  style="width:200px;height:30px" datatype="s1-60" nullmsg="请填写商品名称！"></td>
		    </tr>
		    <tr>
		        <th width="150">副标题：</th>
		        <td><input type="text" name="title" value="<#if tdProduct??>${tdProduct.title!''}</#if>"  style="width:200px;height:30px"  datatype="s1-50" nullmsg="请填写副标题！"></td>
		    </tr>
		    <tr>
		        <th width="150">编号：</th>
		        <td><input type="text" name="code" value="<#if tdProduct??>${tdProduct.code!''}</#if>"  style="width:200px;height:30px" datatype="s5-20" nullmsg="请填写商品编号！"></td>
		    </tr>
		    <tr>
		        <th width="150">运费：</th>
		        <td><input type="text" name="postage" style="width:200px;height:30px" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写商品运费！" errormsg="运费格式错误！" value="${tdProduct.postage!'0'}"></td>
		    </tr>
		    <tr class="seckill" <#if (tdProduct?? && tdProduct.kind?? && tdProduct.kind == 5) || (tdProduct?? && tdProduct.kind?? && tdProduct.kind == 6)>style="display: table-row;"<#else>style="display:none"</#if>>
		        <th  width="150">开始时间：</th>
		        <td>
	            	<input type="text" name="startTime" class="easyui-datetimebox" value="<#if tdProduct?? && tdProduct.startTime??>${tdProduct.startTime?string('yyyy-MM-dd HH:mm:ss')}</#if>" style="width:200px;height:30px"  data-options="showSeconds:true">
		        </td>
		    </tr>
		     <tr class="seckill" <#if (tdProduct?? && tdProduct.kind?? && tdProduct.kind == 5) || (tdProduct?? && tdProduct.kind?? && tdProduct.kind == 6)>style="display: table-row;"<#else>style="display:none"</#if>>
		        <th  width="150">结算时间：</th>
		        <td>
		            <input type="text" name="endTime" class="easyui-datetimebox" value="<#if tdProduct?? && tdProduct.endTime??>${tdProduct.endTime?string('yyyy-MM-dd HH:mm:ss')}</#if>" style="width:200px;height:30px" data-options="showSeconds:true" >
		        </td>
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
	            	<input type="text" name="keyword" value="<#if tdProduct??>${tdProduct.keyword!''}</#if>" style="width:200px;height:30px" datatype="s1-50">
		        </td>
		    </tr>
		    <tr>
		        <th>综合排序值：</th>
		        <td>
		            <input type="text" name="sort" value="<#if tdProduct??>${tdProduct.sort!'0'}<#else>0</#if>" style="width:200px;height:30px" datatype="n1-6" >
		            值越大越靠前
		        </td>
		    </tr>
		    <!--
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
		    -->
		    <tr>
		        <th>提示：</th>
		        <td>
		            <input type="text" name="tags" value="<#if tdProduct??>${tdProduct.tags!''}</#if>" style="width:200px;height:30px" datatype="s0-20">
		        </td>
		    </tr>
		</table>
		<#if !tdProduct??>
			<input type="hidden" name="newRecommend" value="0" >
			<input type="hidden" name="fineRecommend" value="0" >
			<input type="hidden" name="typeRecommend" value="0" >
			<input type="hidden" name="hotRecommend" value="0" >
			<input type="hidden" name="onshelf" value="false" >
		</#if>
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
 <input type="hidden" name="tableData" value="" id="formTableData">
 <button type="submit" class="smt mr10">保存</button>
</form>
<#--用作规格顺序-->
<input type="hidden" value="${speOrder!''}" id="speOrder">
<input type="hidden" value="${specifiactionNum!''}" id="speSize">
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
		//初始化商品一口价设置
		<#if tdProduct?? && tdProduct.id?? && tdProduct.specification==true>
			initProductCommon();
		</#if>
		
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
		
		$("#productForm").Validform({
			tiptype:function(msg,o,cssctl){
			    //msg：提示信息;
			    //o:{obj:*,type:*,curform:*},
			    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），
			    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 
			    //curform为当前form对象;
			    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			    if(o.type == 3){
			    	$.messager.alert('消息提醒',msg);
			    }
			},
			beforeCheck:function(curform){
				initProductCommon();
			},
			beforeSubmit:function(curform){
			 	openwaiting();
			},
			postonce:true, // 开启二次提交防御
			ajaxPost:true, 
			callback:function(data){
				//返回数据data是json对象，{"msg":"demo info","code":"1"}
				//info: 输出提示信息;
				//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
				closewaiting();
				$.messager.alert('消息提醒',data.msg);
				if(data.code == 1){
					returnList();
					refreshList();
				}
			}
		});
	});
</script>
<style type="text/css">
	#skuAssemble table tbody tr td input{
		width: 60px;
	}
</style>
