<#import "/common/app.ftl" as app>
<#if recommendList?? && recommendList?size gt 0>
	<#list recommendList as pro>
		<li>
            <a href="${app.basePath}/product/item${pro.id}" class="img" title="${pro.name!''}"><img src="${app.basePath}${pro.imageUrl!''}" alt="${pro.name!''}"/></a>
            <a href="${app.basePath}/product/item${pro.id}" class="ptitle" title="${pro.name!''}">${pro.name!''}</a>
       		<span class="price">
	            <label class="p1">￥<#if pro.price??>${pro.price?string('0.00')}</#if></label>
	            <s class="p2">￥<#if pro.price??>${pro.price?string('0.00')}</#if></s>
	        </span>
        </li>
    </#list>
</#if>
<script language="javascript" type="text/javascript">
    $(function () {
		$(".pro-list").slide({
		            mainCell: ".bd ul",
            autoPage: true,
            effect: "topLoop",
            autoPlay: true,
            vis: 2,
            trigger: "click"
        });
    });
</script>
