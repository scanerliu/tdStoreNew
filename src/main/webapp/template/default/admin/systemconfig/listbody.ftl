<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="saveConfigs()"><em>保存</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
<form id="systemconfigList" action="" method="post" autocomplete="off" class="easyui-form">
<div id="dg" style="width:800px;height:650px;">
    <div title="系统参数" style="padding:20px;">
    	<table class="tabcontent" width="100%" cellspacing="0">
        <tr>
            <td width="150" align="right">投诉通知手机号码:</td><td><input type="hidden" name="configKey" value="complaint_telphone"/><input type="text" name="complaint_telphone" id="complaint_telphone" value="${configMap.complaint_telphone!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="length[10,50]"/>（多个用英文逗号,隔开）</td>
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
            <td align="right">订单赠送积分比例:</td><td><input type="hidden" name="configKey" value="orderdeliveryintegral"/><input type="text" name="orderdeliveryintegral" id="orderdeliveryintegral" value="${configMap.orderdeliveryintegral!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[0,100]"/>（是订单金额赠送积分比例，如：每满100元赠送1积分时，此项填1）</td>
        </tr>
        <tr>
            <td align="right">积分抵扣金额比例:</td><td><input type="hidden" name="configKey" value="integralexchangerate"/><input type="text" name="integralexchangerate" id="integralexchangerate" value="${configMap.integralexchangerate!''}" class="easyui-textbox" style="width:300px;height:30px;" data-options="required:true" validType="numrange[1,100000000]"/>（积分抵扣一元金额的比例，如：10个积分抵扣一元钱时，此项填10）</td>
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
