<!-- 左侧导航 -->
<ul class="l_left fl">
    <li>
        <p class="p1">订单中心</p>

        <p <#if menucode?? && menucode=="order">class="active"</#if>><a href="${app.basePath}/order/list" title="我的订单">我的订单</a></p>

        <p><a href="${app.basePath}/" title="">我的评价</a></p>

        <p><a href="${app.basePath}/" title="">我的收藏</a></p>

        <p><a href="${app.basePath}/" title="">浏览历史</a></p>
    </li>
    <li>
        <p class="p1">我的资产</p>

        <p><a href="${app.basePath}/" title="">我的钱包</a></p>

        <p><a href="${app.basePath}/" title="">我的积分</a></p>

        <p><a href="${app.basePath}/" title="">我的流水</a></p>
    </li>
    <li>
        <p class="p1">联盟创客</p>

        <p><a href="${app.basePath}/" title="">我的盟友</a></p>

        <p><a href="${app.basePath}/" title="">我的推广</a></p>
    </li>
    <li>
        <p class="p1">商家管理</p>
        <p><a href="${app.basePath}/user/productmanage" <#if menucode?? && menucode=="productmanager">class="active"</#if> title="商品管理">商品管理</a></p>
        <p><a href="${app.basePath}/" title="">供应商资质认证</a></p>
        <p><a href="${app.basePath}/supply/order" <#if menucode?? && menucode=="shipment">class="active"</#if> title="发货管理">发货管理</a></p>
        <p><a href="${app.basePath}/supply/refundlist" <#if menucode?? && menucode=="refundlist">class="active"</#if> title="退货管理">退货管理</a></p>
    </li>
    <li>
        <p class="p1">附近门店</p>
        <p><a href="${app.basePath}/" title="">门店列表</a></p>
    </li>
    <li>
        <p class="p1">客服中心</p>
        <!--<p><a href="${app.basePath}/" title="">意见反馈</a></p>-->
        <p <#if menucode?? && menucode=="refund">class="active"</#if>><a href="${app.basePath}/order/refundlist" title="我的退款">我的退款</a></p>
    </li>
</ul>