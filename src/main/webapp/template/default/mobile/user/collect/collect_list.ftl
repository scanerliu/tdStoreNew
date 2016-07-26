<#import "/common/app.ftl" as app> 
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="" class="hleft hback"></a>
    <span>我的收藏</span>
</div>
<!-- header_top end -->
<script type="text/javascript">
$(document).ready(function(){
	$(document).off();
	var url = '${app.basePath}/mobile/user/collect/search/more'
	$('#collect_list').refresh(url,"#collect_list",1);
})

</script>
<!-- Center Start -->
<section class="container my-collection">
    <ol id="collect_list">
    	<#if collectList??>
    	<#list collectList as col>
        <li>
            <article>
                <img src="<#if col.product??>${app.basePath}${col.product.imageUrl!''}</#if>" alt="<#if col.product??>${col.product.name!''}</#if>"/>

                <div class="right-content">
                    <h3><#if col.product??>${col.product.name!''}</#if></h3>
                    <span>店铺名称：今年的秋天的旗舰店</span>
                    <p>￥<#if col.product?? && col.product.price??>${col.product.price?string('0.00')}</#if></p>
                </div>
                <div class="dele" onclick="delCollect(${col.id?c});">删除</div>
            </article>

        </li>
        </#list>
        </#if>

    </ol>
</section>
<!-- Center End -->

<script>
    $(function () {
        drge('.my-collection ol li');
    });
</script>