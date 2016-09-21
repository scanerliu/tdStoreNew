<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="pmenuForm" action="${app.basePath}/admin/pmenu/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="mid" value="${pmenu.mid!''}">
<table class="table_form" width="100%">
    <tr>
        <th width="150">名称：</th>
        <td><input type="text" name="name" class="easyui-textbox" value="${pmenu.name!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th>排序值：</th>
        <td><input type="text" name="sort" class="easyui-textbox" value="${pmenu.sort!'0'}" style="width:200px;height:30px" data-options="required:true" min="0" max="999999"></td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
            <input type="radio" name="status" value="1" <#if !pmenu.status?? || (pmenu.status?? && pmenu.status==1)>checked</#if>>启用
            <input type="radio" name="status" value="2" <#if pmenu.status?? && pmenu.status==2>checked</#if>>禁用
        </td>
    </tr>
    <tr>
    	<th>分类选择</th>
    	<td>
	    	<div>
		        <div>
		          <select multiple="multiple" id="select1" style="width:150px;height:200px; float:left; border:4px #A0A0A4 outset; padding:4px; ">
		            <#if typeList??>
		            <#list typeList as type>
		            	<option value="${type.id?c}">${type.name!''}</option>
		            </#list>
		            </#if>
		          </select>
		        </div>
		        <div style="float:left"> <span id="add">
		          <input type="button" class="btn" value=">"/>
		          </span><br />
		          <span id="add_all">
		          <input type="button" class="btn" value=">>"/>
		          </span> <br />
		          <span id="remove">
		          <input type="button" class="btn" value="<"/>
		          </span><br />
		          <span id="remove_all">
		          <input type="button" class="btn" value="<<"/>
		          </span> 
		        </div>
		        <div>
		          <select multiple="multiple" name="typeid" id="select2" style="width: 150px;height:200px; float:lfet;border:4px #A0A0A4 outset; padding:4px;">
		          	<#if pmenu.typeList??>
		            <#list pmenu.typeList as type>
		            	<#if type.productType??>
				        <option value="${type.tid?c}">${type.productType.name!''}</option>
				        </#if>
		            </#list>
		            </#if>
		          </select>
		        </div>
		      </div>
    	</td>
    </tr>
    <tr>
        <td>
        </td>
        <td>
            <button type="button" class="smt mr10" onclick="savePmenu()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
$(function(){
	//移到右边
    $('#add').click(function() {
    //获取选中的选项，删除并追加给对方
        $('#select1 option:selected').appendTo('#select2');
    });
    //移到左边
    $('#remove').click(function() {
        $('#select2 option:selected').appendTo('#select1');
    });
    //全部移到右边
    $('#add_all').click(function() {
        //获取全部的选项,删除并追加给对方
        $('#select1 option').appendTo('#select2');
    });
    //全部移到左边
    $('#remove_all').click(function() {
        $('#select2 option').appendTo('#select1');
    });
    //双击选项
    $('#select1').dblclick(function(){ //绑定双击事件
        //获取全部的选项,删除并追加给对方
        $("option:selected",this).appendTo('#select2'); //追加给对方
    });
    //双击选项
    $('#select2').dblclick(function(){
       $("option:selected",this).appendTo('#select1');
    });
});
</script>
