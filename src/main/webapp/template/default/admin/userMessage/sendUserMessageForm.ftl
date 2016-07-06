<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="userMessage" action="${app.basePath}/admin/message/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
    <tr>
        <th width="150">标题：</th>
        <td>
        	<input type="text" name="title" class="easyui-textbox" style="width:700px;height:30px" data-options="required:true" validType="length[1,100]">
        	<input type="hidden" name="idStr" value="${idStr}">
        	<input type="hidden" name="msgType" value="1">
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
        	<label style="padding-left: 10px;">内容:</label>
        	<textarea style="display:none;" rows="5" cols="60" id="cc" name="content"></textarea>
		    <!-- 加载编辑器的容器 -->
		    <script id="userMessageContent" type="text/plain"></script>
        </td> 
    </tr>
    <tr>
        <td>
            <button type="button" class="smt mr10" onclick="saveUserMessage()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<!-- 配置文件 -->
<script type="text/javascript" src="${app.basePath}/static/js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${app.basePath}/static/js/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
	$(document).ready(function(){
	    var ue = UE.getEditor('userMessageContent');	
	});
</script>
