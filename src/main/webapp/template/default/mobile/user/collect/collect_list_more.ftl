<#import "/common/app.ftl" as app>
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