<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/jquery-treetable/jquery.treetable.js" type="text/javascript"></script>
<div class="subnav">
<!--<div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="editAgentProduct(0)"><em>添加代理产品</em></a>&#12288;</div>-->
</div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0" id="order_tree">
<thead>
<tr>
<th align="left">订单编号</th>
<th align="left">总金额</th>
<th align="left">积分抵扣金额</th>
<th align="left">订单状态</th>
<th align="left">支付状态</th>
<th align="left">发货状态</th>
<th align="left">下单时间</th>
<th align="left">更新时间</th>
<th align="left">更新人</th>
</tr>
</thead>
<tbody>
<#assign _treeId=0/>
<#if orderList??>
<#list orderList as order>
	<#assign _treeId++/>
    <tr data-tt-id="${_treeId}" orderId="${order.orderId}">
        <td>${order.orderNo}</td>
        <td>${order.totalAmount}</td>
        <td>${order.pointAmount}</td>
        <td>${order.getOrderStatusStr()!''}</td>
        <td>${order.getPayStatusStr()!''}</td>
        <td>${order.getShipmentStatusStr()!''}</td>
        <td><#if order.createTime??>${order.createTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td><#if order.updateTime??>${order.updateTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
        <td><#if order.updateUser??>${order.updateUser.unick!''}</#if></td>
    </tr>
    <#assign _ptreeId=_treeId/>
    <#assign _treeId++/>
    <tr data-tt-id="${_treeId}" data-tt-parent-id="${_ptreeId}">
    </tr>
</#list>
</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
<div class="btn_wrap_fixed">
<#assign pageId="Orders" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>
$(function(){
    $("#order_tree").treetable({expandable: true, indent:20, initialState: "collapsed",expanderTemplate:"<a href='javascript:;'></a>",stringExpand:"查看详情",stringCollapse:"收起",onNodeExpand:function(){
    		var node = this;
			//判断当前节点是否已经拥有子节点
			var childTr = $("#order_tree [data-tt-parent-id='" + node.id + "']");
			var childTrcsize = childTr.html().length;
			if (childTrcsize > 10) { 
				 return; 
			}
			var orderId=$("#order_tree [data-tt-id='" + node.id + "']").attr("orderId");
			var url = basePath+"/admin/order/details";
			var loadData={"id":orderId};
			$("#order_tree [data-tt-parent-id='" + node.id + "']").loading().load(url,loadData);
    	}
    });
});
</script>
