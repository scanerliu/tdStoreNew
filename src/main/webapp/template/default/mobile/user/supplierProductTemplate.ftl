<#import "/common/app.ftl" as app>
<#if productList??>
	<#list productList as product>
		<article>
		    <dl>
		        <dt>
		            <span>商品编号 </span>
		            <label>${product.code!''}</label>
		        </dt>
		        <dd>
		            <span>商品名称：${product.name!''} </span>
		        </dd>
		        <dd>
		            <span>商品状态：${product.onshelfStr!''}</span>
		        </dd>
		        <dd>
		            <span>库存数量：<#if product.quantum??>${product.quantum?c}</#if>件</span>
		            <a href="${app.basePath}/mobile/imageorder/list?productId=${product.id?c}" class="a-up" title="图片美化">图片美化</a>
		        </dd>
		    </dl>
		    <#--
		    <div class="btnedit">
		        <a href="上传商品.html" title="">商品编辑</a>
		    </div>
		    -->
		</article>
	</#list>
</#if>
<#if sc??>
	<script>
		$(document).ready(function(){
			$(window).off("scroll");			
		});
	</script>
</#if>