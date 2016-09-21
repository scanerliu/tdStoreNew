<#import "/common/app.ftl" as app>
<#if treeList??>
	<#list treeList as menu>
		<div class="item">
			<a href="javascript:;" title="">${menu.name!''}</a>
			<div class="childitem">
				<#if menu.typeList??>
				<#list menu.typeList as ptype>
					<#if ptype.productType?? && ptype.productType.subList??>
					<#list ptype.productType.subList as subptype>
						<dl>
							<dt><a href="javascript:;" title="${subptype.name!''}">${subptype.name!''}</a></dt>
							<dd>
								<#if subptype.subList??>
								<#list subptype.subList as stype>
									<a href="${app.basePath}/product/list?typeId=${stype.id}" target="_blank">${stype.name!''}</a>
								</#list>
								</#if>
							</dd>
						</dl>
					</#list>
					</#if>
				</#list>
				</#if>
			</div>
		</div>
    </#list>
</#if>
<script>
	$(function(){
		<#if sc.sctype?? && sc.sctype==2>
		indexmenutoggle('menu', "menuitem", "childitem");
		/*头部菜单js*/
		<#else>
		menutoggle('menu',"menuitem","childitem");
		</#if>
	});
</script>
