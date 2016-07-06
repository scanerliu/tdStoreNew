<#import "/common/app.ftl" as app>
<td colspan="10">
<div class="J_tablelist table_list">
<div class="content_menu ib_a blue line_x"><a href="javascript:;" onclick="" ><em>发货</em></a><span>|</span><a href="javascript:;" onclick="" ><em>退货</em></a><span>|</span><a href="javascript:;" onclick="" ><em>退款</em></a></div>
<div id="dg" style="width:800px;height:350px;">
    <div title="基本信息" style="padding:20px;">
    	<table width="100%" cellspacing="0">
        <tr>
            <td>商品信息：</td>
            <td>${order.orderNo!''}</td>
        </tr>
        </table>
    </div>
    <div title="发货信息" style="overflow:auto;padding:20px;" href="${app.basePath}/admin/order/shippments?id=${order.orderId!''}">
    </div>
</div>
</div>
<script>
$(function(){
     $('#dg').tabs({
	    border:false,
	    fit:true,
	    tabPosition:"left"
	});
});
</script>
</td>