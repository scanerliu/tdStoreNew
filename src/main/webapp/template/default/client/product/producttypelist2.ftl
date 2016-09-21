<#import "/common/app.ftl" as app>
<#if producttypelist??>
	<#list producttypelist as ptype>
		<div class="item">
			<a href="javascript:;" title="">${ptype.name!''}</a>
			<div class="childitem">
				<#if ptype.subList??>
				<#list ptype.subList as subptype>
				<dl>
					<dt><a href="javascript:;" title="${subptype.name!''}">${subptype.name!''}</a></dt>
					<dd>
						<#if subptype.subList??>
						<#list subptype.subList as stype>
							<#if sc.sctype?? && sc.sctype==1>
								<a href="${app.basePath}/product/list?typeId=${stype.id}" target="_blank">${stype.name!''}</a>
							<#elseif sc.sctype?? && sc.sctype==2>
								<a href="${app.basePath}/product/list?typeId=${stype.id}" target="_blank">${stype.name!''}</a>
							</#if>
						</#list>
						</#if>
					</dd>
				</dl>
				</#list>
				</#if>
			</div>
		</div>
    </#list>
</#if>
<script>
	$(function(){
		/*头部菜单js*/
		menutoggle('menu',"menuitem","childitem");
	});
</script>