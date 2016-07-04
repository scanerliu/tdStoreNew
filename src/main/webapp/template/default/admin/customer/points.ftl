<table class="tabcontent" width="100%" cellspacing="0">
<tr>
    <td width="150" align="right">总数：</td>
    <td id="customer_integral">${integral.integral!'0'}</td>
</tr>
<tr>
    <td align="right">积分变更：</td>
    <td>
    	<input type="hidden" id="customeruid" value="${integral.uid!''}"/>
        <input type="hidden" id="preintegral" value="${integral.integral!'0'}"/>
    	<select id="changetype">
    		<option value="1">增加</option>
    		<option value="2">减少</option>
    	</select>
    	<input type="text" id="changeintegral" value="" holderplace="变更积分数量" style="width:200px;height:30px" onkeyup="formatInputInteger(this,-${integral.integral!'0'},99999)">
    	（填写正整数）
    </td>
</tr>
<tr>
    <td align="right">变更说明：</td>
    <td>
    	<input type="text" id="changenote" value="" holderplace="变更积分说明" style="width:500px;height:30px" size="60">
    </td>
</tr>
<tr>
    <td align="right"></td>
    <td>
    	 <input type="button" name="change" class="btn" value="变更" onclick="changeUserIntegral()"/>
    </td>
</tr>
</table>
积分变更记录
<div id="integrallog_div">
</div>
<script>
$(function(){
    searchCustomerPointLogs(true);
});
</script>
