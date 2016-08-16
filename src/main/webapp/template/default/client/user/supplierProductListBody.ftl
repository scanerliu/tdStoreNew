<#import "/common/app.ftl" as app>
<ul class="active">
	<#if productList??>
		<#list productList as product>
			<!-- 单行商品 -->
			<li>
		    <div class="order_num">
		      <input type="checkbox" name="subbox" value="${product.id?c}"/>
		      <label class="lab1">${product.createTime?string("yyyy-MM-dd")}</label>
		      <label class="lab2">商品编号： ${product.code!''}</label>
		    </div>
		    <div class="goodsorder_detail">
		      <div class="fl goodsorder_detail_left w590">
		        <dl class="fl">
		          <dt class="fl"><a href="${app.basePath}/product/item${product.id?c}" title=""><img src="${app.basePath}${product.imageUrl!''}" alt="${product.name!''}" /></a></dt>
		          <dd class="dd1 w240 fl">
		            <p class="p1">${product.name!''}</p>
		          </dd>
		          <dd class="dd2 w80 txtc fl" style="padding:0;line-height:100px;">${product.postage!'0'}</dd>
		          <dd class="dd3 w80 txtc fl" style="padding:0;line-height:100px;">${product.quantum!'0'}</dd>
		          <dd class="dd4 w90 txtc fl" style="padding:0;line-height:100px;">${product.tdProductType.name!''}</dd>
		        </dl>
		      </div>
		      <div class="div5 w118 fl" style="line-height:100px;">${product.price!'0'}</div>
		      <div class="div6 w108 fl" style="line-height:100px;">
		      	${product.joinStatusStr!''}
		      </div>
		      <div class="div7 w89 fl">
		        <P><a href="javascript:;" onclick="editProduct(${product.id?c})" title="" class="a_sure2" style="margin-top:20px;">商品编辑</a></P>
		        <!--<p><a href="javascript:;" class="a_sure3" title="立即上架" onclick="<#if product.onshelf>goOnshelf('${product.id?c}', 'false')<#else>goOnshelf('${product.id?c}', 'true')</#if>"><#if product.onshelf>立即下架<#else>立即上架</#if></a></p>-->
		        <!--<P><a href="javascript:;" title="" class="a_sure3">立即上架</a></P>-->
		        <!--<p><a href="${app.basePath}/mobile/imageorder/list?productId=${product.id?c}" class="a-up" title="图片美化">图片美化</a></p>-->
		      </div>
		    </div>
			</li>
		</#list>
	</#if>
	<div class="flowbox4 mt20">
	    <label><input type="checkbox" style="margin-left:20px;" id="J_checkall">
	    <a href="javascript:;" title="" class="lab1">全选</a></label>
	    <a href="javascript:;" title="" class="lab2" onclick="bacthOperProducts(1)">上架</a>
	    <a href="javascript:;" title="" class="lab2" onclick="bacthOperProducts(2)">下架</a>
	</div>
</ul>
<input type="hidden" value="${sc.name!''}" name="name">
<input type="hidden" value="<#if sc.onshelf??>${sc.onshelf?c}</#if>" name="onshelf">
<div class="goods-page-nums mt20 w100 txtr">
	<#assign pageId="Products" />
	<#include "../common/commonpostpage.ftl" />
</div>
<script>
$(function(){
    $("#J_checkall").click(function() {
        $('input[name="subbox"]').prop("checked",this.checked); 
    });
    var $subBox = $("input[name='subbox']");
    $subBox.click(function(){
        $("#J_checkall").prop("checked",$subBox.length == $("input[name='subbox']:checked").length ? true : false);
    });
});
</script>