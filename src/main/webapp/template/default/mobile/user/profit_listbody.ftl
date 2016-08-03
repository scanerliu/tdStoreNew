<#import "/common/app.ftl" as app>
<#if logList??>
	<#list logList as item>
		<section>
          <div class="list-info">
            <label>商品名称：</label>
            <span>钻石项链1</span>
          </div>
          <div class="list-info">
            <label>订单号：</label>
            <span>045010152454101</span>
          </div>
          <div class="list-info">
            <label>买家名称：</label>
            <span>小丶夜猫</span>
          </div>
          <div class="list-info">
            <label>总金额：</label>
            <span>200万</span>
          </div>
          <div class="list-info">
            <label>我的收益：</label>
            <span>￥${item.upamount!'0'}</span>
          </div>
        </section>
	</#list>
</#if>
<script type="text/javascript">
  $(function(){
   	 var pageno = ${sc.pageNo+1};
   	 var filterType = ${sc.filterType};
   	 $("#pageNo").val(pageno);
   	 $("#filterType").val(filterType);
   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
   		$(window).scroll(scrollProfitHandler);
   	</#if>
  })
</script>