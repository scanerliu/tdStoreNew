<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="userMessage" action="${app.basePath}/admin/message/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
	<tr>
        <th width="150">消息类型：</th>
        <td><label>${tdUserMessage.msgTypeStr!''}<label></td>
    </tr>
    <tr>
        <th width="150">状态：</th>
        <td><label>${tdUserMessage.statusStr!''}<label></td>
    </tr>
    <tr>
        <th width="150">创建时间：</th>
        <td>
        	<label>${tdUserMessage.createTime?string('yyyy-MM-dd')}</label>
        </td>
    </tr>
    <tr>
        <th width="150">标题：</th>
        <td>
        	<label>${tdUserMessage.title!''}</label>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center">
        	<label style="padding-left: 10px;">内容:</label>
		    <!-- 加载编辑器的容器 -->
		    <script id="userMessageContent" name="content" type="text/plain">${tdUserMessage.content!''}</script>
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
	    var ue = UE.getEditor('userMessageContent',{readonly:true});	
	});
</script>
