<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>

<div class="pad_lr_10">
<form id="drawapplyForm" action="${app.basePath}/admin/supplier/savedrawapply" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="id" value="${drawapply.id!''}">
<table class="table_form" width="100%">
	<tr>
        <th>用户：</th>
        <td>${drawapply.user.uname!''}</td>
    </tr>
	<tr>
        <th width="150">提现金额：</th>
        <td>${drawapply.amount!'0'}</td>
    </tr>
	<tr>
        <th width="150">手续费：</th>
        <td>${drawapply.fee}</td>
    </tr>
	<tr>
        <th width="150">实际打款金额：</th>
        <td>${drawapply.amount-drawapply.fee}</td>
    </tr>
    <tr>
        <th width="150">提现银行：</th>
        <td>${drawapply.bankname!''}</td>
    </tr>
    <tr>
        <th>银行卡号：</th>
        <td>${drawapply.cardno!''}</td>
    </tr>
    <tr>
        <th>银行卡持有人身份证号码：</th>
        <td>${drawapply.idno!''}</td>
    </tr>
    <tr>
        <th>银行卡持有人姓名：</th>
        <td>${drawapply.carduser!''}</td>
    </tr>
	<tr>
        <th>说明：</th>
        <td><textarea rows='4' cols='80' name="remark" id='reply'>${drawapply.remark!''}</textarea></td>
    </tr>
    <#if drawapply.status==1||drawapply.status==2>
	<tr>
        <th>操作状态：</th>
        <td>
        	<#if drawapply.status==1><label><input type="radio" name="status" value="2">同意打款</label><label><input type="radio" name="status" value="3">拒绝打款</label></#if>
        	<label><input type="radio" name="status" value="4" checked="true">打款完成</label>
        	<label><input type="radio" name="status" value="5">打款失败</label>
        </td>
    </tr>
    <#else>
    <tr>
        <th>操作状态：</th>
        <td>
        	${drawapply.statusStr!''}
        </td>
    </tr>
    </#if>
	<tr>
        <th>申请时间：</th>
		<td>${drawapply.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
    </tr>
	<tr>
        <th>更新时间：</th>
        <td>${drawapply.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
    </tr>   
</table>
<#if drawapply.status==1||drawapply.status==2>
<button type="button" class="smt mr10" onclick="saveDrawApply()">保存</button>
</#if>
</form>
</div>