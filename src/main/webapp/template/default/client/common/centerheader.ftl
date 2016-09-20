<!-- Header Start -->
<div class="personal-top">
    <div class="top">
    	<@shiro.user>
        <h1 class="left">您好!欢迎来到创客联盟，<label><@shiro.principal/> <a href="${app.basePath}/logout" title="">退出</a></label> <label>消息<a href="${app.basePath}/user/messageList" class="orange" title="" id="smessagecount">0</a></label></h1>
        <script>
			$(function(){
				getshoppingcartcount();
				getsystemmessagecount();
			});
		</script>
        </@shiro.user>
        <div class="right">
            <a href="${app.basePath}/" title="">创客联盟首页</a>
            <!--<span class="fl">|</span>
            <a href="${app.basePath}/" title="">我的店铺</a>-->
            <span class="fl">|</span>
            <a class="shopp-car" href="${app.basePath}/shoppingcart/list" title="">购物车(<span id="shoppingcartcount">0</span>)</a>
            <span class="fl">|</span>
            <a href="${app.basePath}/user/center" title="">会员中心</a>
            <span class="fl">|</span>
            <a href="${app.basePath}/mobile/index" title="">手机创客</a>
            <span class="fl">|</span>
            <a class="help" href="${app.basePath}/help/list" title="帮助中心">帮助中心</a>
        </div>
    </div>
</div>
<!-- Header End -->

<!-- 顶部导航 -->
<div class="personal-top-nav">
    <div class="top-nav">
        <!-- LOGO尺寸 295*104 -->
        <a class="personal-logo" href="${app.basePath}/">
            <img src="${app.basePath}/static/default/client/images/index_logo_2.png" alt="中国创客联盟"/>
        </a>
        <!-- 导航 -->
        <ul>
            <li><a class="nav1" href="${app.basePath}/" title="">首页</a></li>
            <li class="li2">
                <a class="nav1" href="javascript:;" title="">账户设置 <span>V</span></a>
                <div class="drop-down">
                    <p>
                        <label>安全设置</label>
                        <a href="${app.basePath}/user/changePassword">修改登录密码</a>
                    </p>

                    <p>
                        <label>个人资料</label>
                        <a href="${app.basePath}/user/info">个人信息设置</a>
                        <a href="${app.basePath}/user/shoppingaddress">收货地址管理</a>
                    </p>
                </div>
            </li>
            <li><a class="nav1" href="${app.basePath}/user/messageList" title="">消息</a></li>
        </ul>
        <!-- 搜索框 -->
        <div class="search-box">
        	<form id="keywordform" method="post" action="${app.basePath}/product/list">
            <input type="text" placeholder="请输入关键字搜索"/>
            <a href="javascript:;" title="" class="asearch" onclick="searchItems()"></a>
			</form>
        </div>
    </div>
</div>
<!-- 顶部导航 END -->