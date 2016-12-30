<#import "/common/app.ftl" as app>
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<div class="subnav">
	<div class="content_menu ib_a blue line_x">
	<a data-height="190" data-width="450" data-id="add" data-title="添加类型" href="javascript:;" class="add fb J_showdialog" onclick="editProductType(0)"><em>添加类型</em></a>&#12288;
	<a data-height="190" data-width="450" data-id="add" id="scollExp" data-title="添加类型" href="javascript:;" class="add fb J_showdialog" onclick="collapseAll()"><em>展开/关闭全部</em></a>&#12288;
	</div>
</div>
<div class="pad_lr_10">
<div class="J_tablelist table_list">
	<div id="district">
		<!--
		<input type="button" value="批量删除" onclick="batchDelete()">&nbsp;&nbsp;&nbsp;
		<input type="button" value="添加" onclick="addDistrict()">
		-->
    </div>

	<#if productTypeList?? >
    <ul id="tt" class="easyui-tree">
    	<#list productTypeList as pro>
	    <li >
			<span ><a href="javascript:editProductType(${pro.id?c});">${pro.name!''}</a>&emsp;&emsp;&emsp;&emsp;<a href="javascript:;" onclick="searchTypeAttrbute(${pro.id?c})">属性</a>&emsp;/&emsp;<a href="javascript:" onclick="deleteProduct(${pro.id?c});">删除</a></span>
			<#if pro.subList??>
			<ul>
				<#list pro.subList as spro>
				<li>
					<span><a href="javascript:editProductType(${spro.id?c});">${spro.name!''}</a>&emsp;&emsp;&emsp;&emsp;<a href="javascript:;" onclick="searchTypeAttrbute(${spro.id?c})">属性</a>&emsp;/&emsp;<a href="javascript:;" onclick="deleteProduct(${spro.id?c});">删除</a></span>
					<#if spro.subList??>
					<ul>
						<#list spro.subList as tpro>
						<li><span><a href="javascript:editProductType(${tpro.id?c});">${tpro.name!''}</a>&emsp;&emsp;&emsp;&emsp;<a href="javascript:;" onclick="searchTypeAttrbute(${tpro.id?c})">属性</a>&emsp;/&emsp;<a href="javascript:;" onclick="deleteProduct(${tpro.id?c});">删除</a>&emsp;/&emsp;<a href="javascript:;" onclick="addProduct(${tpro.id?c});">添加商品</a></span></li>
						</#list>
					</ul>
					</#if>
				</li>
				</#list>
			</ul>
			</#if>
		</li>
		</#list>
	</ul>
    </#if>
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
    collapseAll();
});

// 全部收缩
function collapseAll() { 
		$('#tt').tree('collapseAll'); 
		$("#scollExp").attr("onclick", "expandAll()");  
} 
//全部展开
function expandAll() { 
		$('#tt').tree('expandAll'); 
		$("#scollExp").attr("onclick", "collapseAll()");  
} 

</script>
