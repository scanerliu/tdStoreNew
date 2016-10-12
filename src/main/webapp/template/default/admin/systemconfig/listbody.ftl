<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="saveConfigs()"><em>保存</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<form id="systemconfigList" action="" method="post" autocomplete="off" class="easyui-form">
<div id="dg" style="width:800px;height:650px;">
    <div title="系统参数" style="padding:20px;">
    	<table class="tabcontent" width="100%" cellspacing="0">
        <tr>
            <td width="200" align="right">投诉通知手机号码:</td><td><input type="hidden" name="configKey" value="complaint_telphone"/><input type="text" name="complaint_telphone" id="complaint_telphone" value="${configMap.complaint_telphone!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[10,50]"/>（多个用英文逗号,隔开）</td>
        </tr>
        <tr>
            <td align="right">会员统计通知电话号码:</td><td><input type="hidden" name="configKey" value="customer_statistics_telphone"/><input type="text" name="customer_statistics_telphone" id="customer_statistics_telphone" value="${configMap.customer_statistics_telphone!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[10,50]"/>（多个用英文逗号,隔开）</td>
        </tr>
        <tr>
            <td align="right">注册赠送积分数:</td><td><input type="hidden" name="configKey" value="registerdeliveryintegral"/><input type="text" name="registerdeliveryintegral" id="registerdeliveryintegral" value="${configMap.registerdeliveryintegral!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[0,100000000]"/>（如：注册赠送20积分时，此项填20）</td>
        </tr>
        <tr>
            <td align="right">签到赠送积分数:</td><td><input type="hidden" name="configKey" value="signdeliveryintegral"/><input type="text" name="signdeliveryintegral" id="signdeliveryintegral" value="${configMap.signdeliveryintegral!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[0,100000000]"/>（如：签到赠送5积分时，此项填5）</td>
        </tr>
        <tr>
            <td align="right">订单赠送积分比例:</td><td><input type="hidden" name="configKey" value="orderdeliveryintegral"/><input type="text" name="orderdeliveryintegral" id="orderdeliveryintegral" value="${configMap.orderdeliveryintegral!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[0,10000]"/>（是订单金额赠送积分比例，如：每满10元赠送1积分时，此项填10）</td>
        </tr>
        <tr>
            <td align="right">积分抵扣金额比例:</td><td><input type="hidden" name="configKey" value="integralexchangerate"/><input type="text" name="integralexchangerate" id="integralexchangerate" value="${configMap.integralexchangerate!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,100000000]"/>（积分抵扣一分钱金额的比例，如：10个积分抵扣一分钱时，此项填10）</td>
        </tr>
        <tr>
            <td align="right">普通商品积分可抵扣金额比例:</td><td><input type="hidden" name="configKey" value="commonproductpointpercent"/><input type="text" name="commonproductpointpercent" id="commonproductpointpercent" value="${configMap.commonproductpointpercent!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,100]"/>（普通商品可用积分抵扣金额的比例，如：100元的商品可以用积分抵扣10元时，比例为10%，此项填10）</td>
        </tr>
        <tr>
            <td align="right">部分积分兑换商品可抵扣金额比例:</td><td><input type="hidden" name="configKey" value="partproductpointpercent"/><input type="text" name="partproductpointpercent" id="partproductpointpercent" value="${configMap.partproductpointpercent!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,100]"/>（部分积分兑换商品可用积分抵扣金额的比例，如：100元的商品可以用积分抵扣20元时，比例为20%，此项填20）</td>
        </tr>
        <tr>
            <td align="right">竞选活动三级会员中区县级代理数量:</td><td><input type="hidden" name="configKey" value="campaignagentnum"/><input type="text" name="campaignagentnum" id="campaignagentnum" value="${configMap.campaignagentnum!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,10000000]"/>（竞选活动规则，参与者三级会员中区县级以上代理数量，如：需要300个区县级以上代理才满足条件，此项填300）</td>
        </tr>
        <tr>
            <td align="right">竞选活动直接下级中企业级供应商数量:</td><td><input type="hidden" name="configKey" value="campaigncompanysuppliernum"/><input type="text" name="campaigncompanysuppliernum" id="campaigncompanysuppliernum" value="${configMap.campaigncompanysuppliernum!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,10000000]"/>（竞选活动规则，参与者的一级下级中企业级供应商数量，如：需要300个企业级供应商才满足条件，此项填300）</td>
        </tr>
        <tr>
            <td align="right">图片美化单价:</td><td><input type="hidden" name="configKey" value="imageprocessingprice"/><input type="text" name="imageprocessingprice" id="imageprocessingprice" value="${configMap.imageprocessingprice!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,100]"/>（图片美化单价，如：处理一周商品图片的价格为5元时，此项填5）</td>
        </tr>
        <tr>
            <td align="right">订单自动收货时间间隔:</td><td><input type="hidden" name="configKey" value="orderreceiptperiod"/><input type="text" name="orderreceiptperiod" id="orderreceiptperiod" value="${configMap.orderreceiptperiod!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,100]"/>（自动收货时间间隔，单位为 天，如：发货7天后，用户没有手动确认收货操作，系统自动更改订单状态为收货，此项填7）</td>
        </tr>
        <tr>
            <td align="right">订单自动完成时间间隔:</td><td><input type="hidden" name="configKey" value="ordercompleteperiod"/><input type="text" name="ordercompleteperiod" id="ordercompleteperiod" value="${configMap.ordercompleteperiod!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,100]"/>（自动完成时间间隔，单位为 天，如：订单确认收货7天后，系统自动更改订单状态为已完成，此项填7）</td>
        </tr>
        <tr>
            <td align="right">订单自动取消时间间隔:</td><td><input type="hidden" name="configKey" value="ordercancelperiod"/><input type="text" name="ordercancelperiod" id="ordercancelperiod" value="${configMap.ordercancelperiod!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,1000]"/>（未支付订单自动取消时间间隔，单位为 小时，如：此项填3，下单3小时后仍未支付，系统自动取消该订单，）</td>
        </tr>
        <tr>
            <td align="right">物流查询地址:</td><td><input type="hidden" name="configKey" value="logisticweburl"/><input type="text" name="logisticweburl" id="logisticweburl" value="${configMap.logisticweburl!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,100]"/>（首页物流查询链接地址）</td>
        </tr>
        <tr>
            <td align="right">热门搜索关键词:</td><td><input type="hidden" name="configKey" value="hotsearchword"/><input type="text" name="hotsearchword" id="hotsearchword" value="${configMap.hotsearchword!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[1,100]"/>（热门搜索关键词，多个用英文逗号隔开，如：花茶,红酒,蜂蜜 ）</td>
        </tr>
        <tr>
            <td align="right">零元商品三级分润金额:</td><td><input type="hidden" name="configKey" value="zeroproductbenefitamount"/><input type="text" name="zeroproductbenefitamount" id="zeroproductbenefitamount" value="${configMap.zeroproductbenefitamount!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[1,100]"/>（如三级分润总共分得1元，此项填1）</td>
        </tr>
        <tr>
            <td align="right">提现手续费:</td><td><input type="hidden" name="configKey" value="withdrawfee"/><input type="text" name="withdrawfee" id="withdrawfee" value="${configMap.withdrawfee!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[1,100]"/>（提现手续费千分比,如收取5‰的手续费，此项填5）</td>
        </tr>
        <tr>
            <td align="right">提现手续费最低金额:</td><td><input type="hidden" name="configKey" value="withdrawfeemin"/><input type="text" name="withdrawfeemin" id="withdrawfeemin" value="${configMap.withdrawfeemin!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[1,100]"/>（提现手续费最低金额，单位为元,如最低收取手续费2元，此项填2）</td>
        </tr>
        <tr>
            <td align="right">大额提现申请手续费:</td><td><input type="hidden" name="configKey" value="drawapplyfee"/><input type="text" name="drawapplyfee" id="drawapplyfee" value="${configMap.drawapplyfee!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[1,100]"/>（提现手续费千分比,如收取5‰的手续费，此项填5）</td>
        </tr>
        <tr>
            <td align="right">大额提现申请手续费最低金额:</td><td><input type="hidden" name="configKey" value="drawapplyfeemin"/><input type="text" name="drawapplyfeemin" id="drawapplyfeemin" value="${configMap.drawapplyfeemin!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[1,100]"/>（提现手续费最低金额，单位为元,如最低收取手续费2元，此项填2）</td>
        </tr>
        </table>
    </div>
    <div title="网站属性设置" style="overflow:auto;padding:20px;">
    	<table class="tabcontent"  width="100%" cellspacing="0">
        <tr>
            <td width="120" align="right">网站备案号:</td><td><input type="hidden" name="configKey" value="webrecordnumber"/><input type="text" name="webrecordnumber" id="webrecordnumber" value="${configMap.webrecordnumber!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td width="120" align="right">页面关键词(SEO):</td><td><input type="hidden" name="configKey" value="webkeywords"/><input type="text" name="webkeywords" id="webkeywords" value="${configMap.webkeywords!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td align="right">页面描述(SEO):</td><td><input type="hidden" name="configKey" value="webdescription"/><input type="text" name="webdescription" id="webdescription" value="${configMap.webdescription!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td align="right">网站版权信息:</td><td><input type="hidden" name="configKey" value="webcopyright"/><input type="text" name="webcopyright" id="webcopyright" value="${configMap.webcopyright!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td align="right">公司地址:</td><td><input type="hidden" name="configKey" value="webcompanyaddress"/><input type="text" name="webcompanyaddress" id="webcompanyaddress" value="${configMap.webcompanyaddress!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td align="right">公司邮箱:</td><td><input type="hidden" name="configKey" value="webcompanyemail"/><input type="text" name="webcompanyemail" id="webcompanyemail" value="${configMap.webcompanyemail!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td align="right">服务热线:</td><td><input type="hidden" name="configKey" value="webhotline"/><input type="text" name="webhotline" id="webhotline" value="${configMap.webhotline!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        </table>
    </div>
    <div title="短信接口设置" style="overflow:auto;padding:20px;">
    	<table class="tabcontent"  width="100%" cellspacing="0">
        <tr>
            <td width="120" align="right">接口地址:</td><td><input type="hidden" name="configKey" value="smsresturl"/><input type="text" name="smsresturl" id="smsresturl" value="${configMap.smsresturl!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td align="right">开发者主账号:</td><td><input type="hidden" name="configKey" value="smsaccountsid"/><input type="text" name="smsaccountsid" id="smsaccountsid" value="${configMap.smsaccountsid!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td align="right">开发者主账号令牌:</td><td><input type="hidden" name="configKey" value="smsauthtoken"/><input type="text" name="smsauthtoken" id="smsauthtoken" value="${configMap.smsauthtoken!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        <tr>
            <td align="right">应用Id:</td><td><input type="hidden" name="configKey" value="smsappid"/><input type="text" name="smsappid" id="smsappid" value="${configMap.smsappid!''}" class="easyui-textbox" style="width:300px;height:30px;"/></td>
        </tr>
        </table>
    </div>
</div>
</form>
</div>
<div class="btn_wrap_fixed">
</div>
</div>
<div class="btn_wrap_fixed">
</div>
<script>
$(function(){
   $('#dg').tabs({
	    border:false,
	    fit:true
	});
});
</script>
