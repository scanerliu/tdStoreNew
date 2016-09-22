<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="pfloorForm" action="${app.basePath}/admin/pfloor/save" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" name="fid" value="${pfloor.fid!''}">
<table class="table_form" width="100%">
    <tr>
        <th width="150">名称：</th>
        <td><input type="text" name="title" class="easyui-textbox" value="${pfloor.title!''}" style="width:200px;height:30px" data-options="required:true" validType="length[2,20]"></td>
    </tr>
    <tr>
        <th>排序值：</th>
        <td><input type="text" name="sort" class="easyui-textbox" value="${pfloor.sort!'0'}" style="width:200px;height:30px" data-options="required:true" min="0" max="999999"></td>
    </tr>
    <tr>
        <th>状态：</th>
        <td>
            <input type="radio" name="status" value="1" <#if !pfloor.status?? || (pfloor.status?? && pfloor.status==1)>checked</#if>>启用
            <input type="radio" name="status" value="2" <#if pfloor.status?? && pfloor.status==2>checked</#if>>禁用
        </td>
    </tr>
    <tr>
    	<th>分类选择</th>
    	<td>
    		<span id="onetypespn"></span><span id="twotypespn"></span><span id="typespn"></span>(选择第三级分类自动添加)
	    	<div>
		        <div style="float:left">
		          <select multiple="multiple" name="typeid" id="select2" style="width: 150px;height:200px; float:lfet;border:4px #A0A0A4 outset; padding:4px;">
		          	<#if pfloor.typeList??>
		            <#list pfloor.typeList as type>
		            	<#if type.productType??>
				        <option value="${type.tid?c}">${type.productType.name!''}</option>
				        </#if>
		            </#list>
		            </#if>
		          </select>
		        </div>
		        <div style="float:left"> <span id="add">
		          <span id="remove">
		          <input type="button" class="btn" value="删除"/>
		          </span><br />
		          <span id="remove_all">
		          <input type="button" class="btn" value="全部删除"/>
		          </span> 
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
    //移到左边
    $('#remove').click(function() {
        $('#select2 option:selected').remove();
    });
    //全部移到左边
    $('#remove_all').click(function() {
        $('#select2 option').remove();
    });
    //双击选项
    $('#select2').dblclick(function(){
       $("option:selected",this).remove();
    });
    getAllTypes({'obj':null,'num':0});
});
</script>
