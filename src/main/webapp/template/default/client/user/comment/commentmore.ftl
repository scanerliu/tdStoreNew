<#import "/common/app.ftl" as app><#if commentList??><#list commentList as comm><ul class="active">  <!-- 单行商品 -->  <li>    <div class="order_num">      <input type="checkbox"/>      <label class="lab1"><#if comm.createTime??>${comm.createTime?string('yyyy-MM-dd')}</#if></label>      <label class="lab2">订单号： <#if comm.order??>${comm.order.orderNo!''}</#if></label>    </div>    <div class="goodsorder_detail">      <div class="fl goodsorder_detail_left w590">        <dl class="fl">          <dt class="fl"><a href="<#if comm.product??>${app.basePath}/product/item${comm.productId!''}</#if>" title=""><img src="<#if comm.product??>${app.basePath}${comm.product.imageUrl!''}</#if>" alt="<#if comm.product??>${comm.product.name!''}</#if>" /></a></dt>          <dd class="dd1 w370 fl">            <p class="p1"><#if comm.product??>${comm.product.name!''}</#if></p>            <p class="p2">              ${comm.specifications!''}            </p>          </dd>        </dl>      </div>      <div class="div5 w118 fl">        <p class="p1"><#if comm.order?? && comm.order.productAmount??>${comm.order.productAmount?string('0.00')}</#if></p>        <p class="p2 color_black">含运费（<span><#if comm.order?? && comm.order.postage??>${comm.order.postage?string('0.00')}</#if></span>）</p>      </div>      <div class="div6 w108 fl">        <p><a href="#" title="">订单详情</a></p>      </div>    </div>  </li></ul>  <!-- 评分 -->  <div class="score">    <div class="left">评价等级</div>    <!--评价星星-->    <div class="reviewsbox">    	<#list 1..5 as num>			<a href="javascript:;" <#if comm.score?? && num lte comm.score>class="sel"</#if>>&nbsp;</a>      	</#list>    </div>  </div><!-- 评分 END -->  <!-- 评价内容 -->  <div class="reviews-content" style="margin-top:0px;">    <div class="left" style="line-height:30px;">评价内容</div>    <p style="float:left;overflow:hidden;width:850px;line-height:30px;color:#999;">    	${comm.content!''}    </p>  </div></#list></#if><div class="goods-page-nums mt20 w100 txtr"><#assign pageId="Comments" /><#include "../../common/commonpostpage.ftl" /></div><script type="text/javascript">  $(function(){   	  })</script>