<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/userSupplier/drawapply.js" type="text/javascript"></script>
<div id="rightlist">
<div class="subnav">
		<div class="content_menu ib_a blue line_x">
		</div>
	</div>
	<form id="searchform">
	<table width="100%" cellspacing="0" class="search_form">
		<tbody>
			<tr>
				<td>
					<div class="explain_col">
						申请时间：
						<input type="text" value="" size="12" style="width:100px;" class="easyui-datebox" id="time_start" name="startTime">
						-<input type="text" value="" size="12" style="width:100px;" class="easyui-datebox" id="time_end" name="endTime">
						&nbsp;&nbsp;
						<select name="bankid" class="J_cate_select mr10">
							<option value="">-提现银行-</option>
							<option value="1">中国工商银行</option>
                        	<option value="2">中国建设银行</option>
                        	<option value="3">中国银行 </option>
                        	<option value="4">中国农业银行</option>
                        	<option value="5">交通银行</option>
                        	<option value="6">招商银行</option>
                        	<option value="7">中国邮政储蓄银行</option>
                        	<option value="8">中信银行</option>
                        	<option value="9">光大银行</option>
                        	<option value="10">民生银行</option>
                        	<option value="11">兴业银行</option>
                        	<option value="12">华夏银行</option>
                        	<option value="13">平安银行</option>
						</select>
						提现人姓名:<input type="text" value="" size="25" class="input-text" name="username">
						&nbsp;&nbsp;供应商ID:<input type="text" value="" size="25" class="input-text" name="uid">
						&nbsp;&nbsp;
						<select name="status" class="J_cate_select mr10">
							<option value="">-提现状态-</option>
							<option value="1">新申请</option>
							<option value="2">已同意</option>
							<option value="3">已拒绝</option>
							<option value="4">已打款</option>
							<option value="5">打款失败</option>
						</select>
						&nbsp;&nbsp;
						<input type="button" value="搜索" class="btn" name="search" onclick="searchDrawApplys(true)">
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	<form id="listform">
	<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
$(function(){
    searchDrawApplys(true);
});
</script>
