<!-- 头部 -->
<header class="innerh">
	<div class="w100" style="border-bottom:1px solid #ddd;">
		<div class="top_div">
			<section class="signor fl">
			<@shiro.user>
				您好!欢迎来到创客联盟，<label><@shiro.principal/> <a href="${app.basePath}/logout" title="">退出</a></label> <label>消息<a href="${app.basePath}/user/messageList" class="orange" title="" id="smessagecount">0</a></label>
				<script>
					$(function(){
						getshoppingcartcount();
						getsystemmessagecount();
					});
				</script>
			</@shiro.user>
			<@shiro.guest>
			您好欢迎来到创客联盟!请<a href="${app.basePath}/login" title="" class="a1">【登录】</a>
			</@shiro.guest>
			</section>
			<section class="row_linka fr">
				<a href="${app.basePath}/shoppingcart/list" title="" class="_shop_car"><span id="shoppingcartcount">0</span></a>
				<a href="${app.basePath}/help/list" title="" class="">帮助中心</a>
				<!--<a href="#" title="" class="">关注创客</a>-->
				<a href="${app.basePath}/mobile/index" title="" class="">手机创客</a>
				<a href="${app.basePath}/user/center" title="" class="">会员中心</a>
				<a href="${app.basePath}/order/list" title="" class="">我的订单</a>
				<!--<a href="${app.basePath}/user/index" title="" class="">我的店铺</a>-->
			</section>
		</div>
	</div>
	<div class="w100">
		<div class="logo_div">
			<a href="${app.basePath}/" title="" class="alogo fl"><img src="${app.basePath}/static/default/client/images/logo.png" alt="" /></a>
			<section class="sec1 fr">
				<aside class="search_div">
					<form id="keywordform" method="post" action="${app.basePath}/product/list">
					<input type="text" placeholder="请输入关键字搜索..." name="name" id="keywords" value="<#if sc?? && sc.name??>${sc.name}</#if>"/>
					<a href="javascript:;" title="" class="asearch" onclick="searchItems()"></a>
					</form>
				</aside>
				<aside class="as2">
					<#if system?? && system.hotsearchword??>
					<#list system.hotsearchword?split(",") as word>
					<a href="javascript:;" class="a2" title="${word}" onclick="searchhotword('${word}')">${word}</a>
					</#list>
					</#if>
				</aside>
			</section>
		</div>
	</div>
	<div class="w100 nav-box">
		<div class="nav_div_box">
			<dl class="all1 fl" id="menu">
				<dt><label>全部商品分类</label></dt>
				<dd class="menuitem" id="productTypeList">
				<!-- load menu -->
				</dd>
			</dl>
			<nav class="nav_div fl">
				<a href="${app.basePath}/" title="" class="fl">首页</a>
				<a href="${app.basePath}/agent/list" title="" class="fl">创业中心</a>
				<a href="${app.basePath}/campaign/list" title="" class="fl">股东竞选</a>
				<a href="${app.basePath}/product/killlist" title="" class="fl">秒杀专区</a>
				<a href="${app.basePath}/product/zerolist" title="" class="fl">0元专区</a>
				<a href="${app.basePath}/product/newlist" title="" class="fl">排队免单</a>
			</nav>
			<section class="sec1 fr">服务热线：<span><#if system?? && system.webhotline??>${system.webhotline}</#if></span></section>
		</div>
	</div>
</header>
<!-- 头部-结束 -->