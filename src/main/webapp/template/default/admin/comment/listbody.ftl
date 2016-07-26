<#import "/common/app.ftl" as app>
<div class="subnav">
	<div class="content_menu ib_a blue line_x">
		<#--
		<a data-height="190" data-width="450" data-id="add" data-title="添加类型" href="javascript:;" class="add fb J_showdialog" onclick="editProductAttribute(0)">
		<em>添加类型</em></a>&#12288;
		-->
	</div>
</div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
	<div id="district">
		<select style="width: 100px;" name="status" onchange="searchcomment(false)">
		    <option value="">-显示状态-</option>
		    <option value="1" <#if sc.status?? && sc.status==1>selected="selected"</#if>>正常</option>
		    <option value="2" <#if sc.status?? && sc.status==2>selected="selected"</#if>>屏蔽</option>
		</select>
    </div>

<table width="100%" cellspacing="0">
	<thead>
		<tr>
			<th width="4%"><input type="checkbox" class="J_checkall" id="J_checkall" name="checkall"></th>
			<th width="4%">ID</th>
			<th align="center">评论会员</th>
			<th align="center">商品</th>
			<th align="center">评分</th>
			<th align="center">内容</th>
			<th align="center">状态</th>
			<th align="center">投诉时间</th>
			<th >操作</th>
		</tr>
	</thead>
<tbody>
<#if commentList?? && commentList?size gt 0>
		<#list commentList as comm>
			<tr>
		        <td align="center">
		        	<input type="checkbox" value="${comm.id?c}" name="subbox" class="J_checkitem">
		        </td>
		        <td align="center">${comm.id?c}</td>
		        <td align="center"><#if comm.commentUser??>${comm.commentUser.uname!''}</#if></td>
		        <td align="center"><#if comm.product??>${comm.product.name!''}</#if></td>
		        <td align="center">${comm.score!'0'}</td>
		        <td align="center" width="35%">${comm.content!''}</td>
		        <td align="center"><#if comm.status??><#if comm.status==1>正常<#else>屏蔽</#if></#if></td>
		        <td align="center"><#if comm.createTime??>${comm.createTime?string('yyyy-MM-dd')}</#if></td>
		        <td align="center">
		            <a class="J_confirmurl" href="javascript:;" onclick="delComment(${comm.id?c})">删除</a>
		           |<a class="J_confirmurl" href="javascript:;" onclick="editComment(${comm.id?c})">详情</a>
		        </td>
	    	</tr>
	    </#list>
	</#if>
</tbody>
</table>
</div>
<div class="btn_wrap_fixed"></div>
<div class="btn_wrap_fixed">
<#assign pageId="comments" />
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
