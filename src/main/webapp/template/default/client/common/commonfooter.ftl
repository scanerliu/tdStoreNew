<!-- 底部 -->
<footer class="innerf">
	<div class="w100" style="background:#333333;">
		<div class="icon_div">
			<ul>
				<li class="li1">
					<p class="p1">正品保障</p>
					<p class="p2">公司正规，提供发票</p>
				</li>
				<li class="li2">
					<p class="p1">无忧售后</p>
					<p class="p2">7天无理由退货</p>
				</li>
				<li class="li3">
					<p class="p1">急速物流</p>
					<p class="p2">急速物流，急速物流</p>
				</li>
				<li class="li4">
					<p class="p1">安心便捷</p>
					<p class="p2">在线支付，货到付款</p>
				</li>
				<li class="li5">
					<p class="p1">物美价廉</p>
					<p class="p2">工厂直销，只为您省</p>
				</li>
			</ul>
		</div>
	</div>
	<div class="w100" style="border-bottom:1px solid #ddd;">
		<div class="link_div">
			<ul>
				<#if articleList??>
				<#list articleList as at>
				<li>
					<p class="p1">${at.name!''}</p>
					<p class="p2">
						<#if at.articleList??>
						<#list at.articleList as item>
							<a href="#" title="" class="">${item.title!''}</a>
						</#list>
						</#if>
					</p>
				</li>
				</#list>
				</#if>
				<#--
				<li>
					<p class="p1">支付方式</p>
					<p class="p2">
						<a href="#" title="" class="">在线支付</a>
						<a href="#" title="" class="">货到付款</a>
						<a href="#" title="" class="">银行转账</a>
						<a href="#" title="" class="">门店支付</a>
						<a href="#" title="" class="">发票制度</a>
						<a href="#" title="" class=""></a>
					</p>
				</li>
				<li>
					<p class="p1">售后服务</p>
					<p class="p2">
						<a href="#" title="" class="">售后服务</a>
						<a href="#" title="" class="">退款说明</a>
						<a href="#" title="" class="">配送说明</a>
						<a href="#" title="" class="">七天包退</a>
						<a href="#" title="" class="">联系方式</a>
					</p>
				</li>
				<li>
					<p class="p1">会员专区</p>
					<p class="p2">
						<a href="#" title="" class="">会员介绍</a>
						<a href="#" title="" class="">代理商申请</a>
						<a href="#" title="" class="">积分说明</a>
						<a href="#" title="" class="">加盟公司</a>
						<a href="#" title="" class="">账户安全</a>
					</p>
				</li>
				<li>
					<p class="p1">购物帮助</p>
					<p class="p2">
						<a href="#" title="" class="">购物流程</a>
						<a href="#" title="" class="">常见问题</a>
						<a href="#" title="" class="">联系我们</a>
					</p>
				</li>
				-->
			</ul>
		</div>
	</div>
	<#include "foot.ftl">
</footer>
<!-- 底部-结束 -->
<div class="load-container" id="loaddiv" style="display:none;">
	<div class="loader" style="z-index: 1000;position:fixed;left:0;top:0;width:100%;height:100%;background:url('${app.basePath}/static/default/client/images/opa1.png');text-align:center;"><img style="position:relative;top:50%;margin-top:-18px;" src="${app.basePath}/static/default/client/images/loading.gif"></div>
</div>