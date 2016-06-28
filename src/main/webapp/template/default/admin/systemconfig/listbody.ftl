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
