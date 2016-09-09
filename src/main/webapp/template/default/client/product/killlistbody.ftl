<#import "/common/app.ftl" as app>
<section class="gou tabb w1200">
	<ul class="active">
		<#if productList?? && productList?size gt 0>
			<#list productList as pro>
				<li>
					<a href="${app.basePath}/product/item${pro.id}" title="${pro.name!''}"><img src="${app.basePath}${pro.imageUrl!''}" alt="暂无图片" /></a>
					<p class="p1">${pro.name!''}</p>
					<p class="p3" id="timeLeft${pro.id?c}">
						<span>00</span>
						天
						<span>00</span>
						时
						<span>00</span>
						分
						<span>00</span>
						秒
					</p>
					<p class="p2">
						<label for="" class="lab1">￥<#if pro.price??>${pro.price?string('0.00')}</#if></label>
						<span></span>
					</p>
				</li>
				<script>
					$(document).ready(function(){
						timer${pro.id?c}()
					    setInterval("timer${pro.id?c}()",1000);
					});
					
					function checkTime(i)  
					{  
					    if (i < 10) {  
					        i = "0" + i;  
					    }  
					    return i;  
					}
					
					function timer${pro.id?c}()
					{
					    	var ts = (new Date(${pro.endTime?string("yyyy")}, 
					                parseInt(${pro.endTime?string("MM")}, 10)-1, 
					                ${pro.endTime?string("dd")}, 
					                ${pro.endTime?string("HH")}, 
					                ${pro.endTime?string("mm")}, 
					                ${pro.endTime?string("ss")})) - (new Date());//计算剩余的毫秒数
					  
					    var allts = (new Date(${pro.endTime?string("yyyy")}, 
					                parseInt(${pro.endTime?string("MM")}, 10)-1, 
					                ${pro.endTime?string("dd")}, 
					                ${pro.endTime?string("HH")}, 
					                ${pro.endTime?string("mm")}, 
					                ${pro.endTime?string("ss")}))
					               - (new Date(${pro.startTime?string("yyyy")}, 
					                parseInt(${pro.startTime?string("MM")}, 10)-1, 
					                ${pro.startTime?string("dd")}, 
					                ${pro.startTime?string("HH")}, 
					                ${pro.startTime?string("mm")}, 
					                ${pro.startTime?string("ss")}));//总共的毫秒数 
					    
					    if (0 == ts)
					    {
					        window.location.reload();
					    }
					  
					    var date = new Date();
					    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
					    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
					    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
					    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
					     if(ss < 0){
					    	ss = 0;
					    }
					    if(mm < 0){
					    	mm = 0;
					    }
					    if(hh < 0){
					    	hh = 0;
					    }
					    if(dd < 0){
					    	dd = 0;
					    }
					    dd = checkTime(dd);
					    hh = checkTime(hh);
					    mm = checkTime(mm);
					    ss = checkTime(ss);
					    $("#timeLeft${pro.id?c}").html("<span>"+dd+"</span>天<span>"+hh+"</span>时<span>"+mm+"</span>分<span>"+ss+"</span>秒");
					    
					}
				</script>
		    </#list>
		<#else>
			<span class="nothing_tips">暂无相关商品。</span>
		</#if>
	</ul>
</section>
<input type="hidden" value="${sc.name!''}" name="name">
<input type="hidden" value="${sc.brandId!''}" name="brandId">
<input type="hidden" value="${sc.typeId!''}" name="typeId">
<input type="hidden" value="${sc.orderby!''}" name="orderby">
<input type="hidden" value="${sc.stock!''}" name="stock">
<input type="hidden" value="${sc.postage!''}" name="postage">
<input type="hidden" value="${sc.startPrice!''}" name="startPrice">
<input type="hidden" value="${sc.endPrice!''}" name="endPrice">
<#if productList?? && productList?size gt 0>
<section class="page" style="margin:0 auto;margin-top:30px;width:1200px;">
<#assign pageId="ZeroProducts" />
<#include "../common/commonpostpage.ftl" />
</section>
<script>
	$(function(){
		
	});
</script>
</#if>
