<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>

<div class="pad_lr_10">
<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th width="40"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
	<th>ID</th>
	<th align="center">会员</th>
	<th align="center">消息类别</th>
	<th align="center">题目</th>
	<th align="center">发布时间</th>
	<th align="center">状态</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if userMessageList??>
<#list userMessageList as userMessage>
    <tr>
    	<td align="center">
        	<input type="checkbox" value="${userMessage.id?c}" name="subbox" class="J_checkitem">
        </td>
        <td align="center">${userMessage.id?c}</td>
        <td align="center">${userMessage.user.uname!''}</td>
        <td align="center">${userMessage.msgTypeStr}</td>
        <td align="center">${userMessage.title!''}</td>
        <td align="center">${userMessage.createTime?string('yyyy-MM-dd')}</td>
        <td align="center">${userMessage.statusStr!''}</td>
        <td align="center">
        	<a class="J_showdialog" href="javascript:;" onclick="editUserMessage(${userMessage.id?c})">详情</a>
            |&nbsp;<a class="J_showdialog" href="javascript:;" onclick="delUserMessage(${userMessage.id?c})">删除</a>
        </td>
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
<#-- 搜索条件 begin-->
<input type="hidden" name="status" value="<#if sc.status??>${sc.status?c}<#else>-1</#if>">
<input type="hidden" name="msgType" value="<#if sc.regionId??>${sc.regionId?c}<#else>-1</#if>">
<input type="hidden" name="title" value="${sc.title!''}">
<input type="hidden" name="username" value="${sc.username!''}">
<input type="hidden" name="beginDate" value="">
<input type="hidden" name="endDate" value="">
<#-- 搜索条件 end-->
<#assign pageId="UserMessage" />
<#include "/admin/common/common_postpage.ftl" />
</div>
<script>
$(function(){
    $("#J_checkall").click(function() {
        $('input[name="subbox"]').prop("checked",this.checked);
    });
    var $subBox = $("input[name='subbox']");
    $subBox.click(function(){
        $("#J_checkall").prop("checked",$subBox.length == $("input[name='subbox']:checked").length ? true : false);
    });   
});
</script>
