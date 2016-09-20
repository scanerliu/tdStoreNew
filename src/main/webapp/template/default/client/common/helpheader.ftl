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
			您好欢迎来到创客联盟!请<a href="${app.basePath}/login" title="" class="a1">【登录】</a>或<a href="${app.basePath}/register" title="" class="a2">【免费注册】</a>
			</@shiro.guest>
			</section>
			<section class="row_linka fr">
				<a href="${app.basePath}/shoppingcart/list" title="" class="_shop_car"><span id="shoppingcartcount">0</span></a>
				<a href="${app.basePath}/help/list" title="" class="">帮助中心</a>
				<!--<a href="#" title="" class="">关注创客</a>-->
				<a href="${app.basePath}/mobile/index" title="" class="">手机创客</a>
				<a href="${app.basePath}/user/center" title="" class="">会员中心</a>
				<a href="${app.basePath}/order/list" title="" class="">我的订单</a>
				<a href="${app.basePath}/user/index" title="" class="">我的店铺</a>
			</section>
		</div>
	</div>
</header>
<!-- 头部-结束 -->
<!-- 帮助中心顶部 -->
<div class="helpcentertop">
    <div class="wrapper">
        <div class="intop">
            <span class="title"><i><img src="${app.basePath}/static/default/client/images/f-icon21.png" alt=""/></i>创客帮助中心</span>
            <aside class="search-div">
                <form id="keywordform" method="post" action="${app.basePath}/product/list">
				<input type="text" class="txt" placeholder="请输入关键字搜索..." name="name" id="keywords" value="<#if sc?? && sc.name??>${sc.name}</#if>"/>
				<a href="javascript:;" title="" class="asearch" onclick="searchItems()"></a>
				</form>
            </aside>
        </div>
    </div>

</div>
<!-- 帮助中心顶部 END -->
<div class="clear"></div>