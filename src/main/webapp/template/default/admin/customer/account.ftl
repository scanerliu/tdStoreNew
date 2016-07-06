<table class="tabcontent" width="100%" cellspacing="0">
<tr>
    <td width="150" align="right">账号金额：</td>
    <td id="customer_amount">${account.amount!'0'}</td>
</tr>
<tr>
    <td align="right">金额变更：</td>
    <td>
    	<input type="hidden" id="customeruid" value="${account.uid!''}"/>
    	<input type="hidden" id="preamount" value="${account.amount!'0'}"/>
    	<select id="changetype">
    		<option value="1">增加</option>
    		<option value="2">减少</option>
    	</select>
    	<input type="text" id="changeamount" value="" holderplace="变更金额" style="width:200px;height:30px" onkeyup="formatInputPrice(this,-${account.amount!'0'},99999)">
    	（填写金额）
    </td>
</tr>
<tr>
    <td align="right">变更说明：</td>
    <td>
    	<input type="text" id="changenote" value="" holderplace="变更金额说明" style="width:500px;height:30px" size="60">
    </td>
</tr>
<tr>
    <td align="right"></td>
    <td>
    	 <input type="button" name="change" class="btn" value="变更" onclick="changeUserAccount()"/>
    </td>
</tr>
</table>
账户变更记录
<div id="accountlog_div">
</div>
<script>
$(function(){
    searchCustomerAccountLogs(true);
});
</script>
