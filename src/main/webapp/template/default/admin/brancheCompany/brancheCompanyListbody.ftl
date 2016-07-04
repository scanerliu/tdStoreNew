<#import "/common/app.ftl" as app>
<div class="subnav"><div class="content_menu ib_a blue line_x">
</div></div>
<div class="pad_lr_10">
<select style="width: 100px;" name="status">
    <option value="-1">请选择状态...</option>
    <option value="1" <#if sc.status?? && sc.status==1>selected="selected"</#if>>正常</option>
    <option value="2" <#if sc.status?? && sc.status==2>selected="selected"</#if>>禁用</option>
</select>
<select style="width: 100px;" name="level">
    <option value="-1">请选择级别...</option>
    <option value="1" <#if sc.level?? && sc.level==1>selected="selected"</#if>>平台</option>
    <option value="2" <#if sc.level?? && sc.level==2>selected="selected"</#if>>省市</option>
    <option value="3" <#if sc.level?? && sc.level==3>selected="selected"</#if>>区县</option>
</select>
<input type="text" placeholder="用户名..." name="searchName" value="${sc.searchName!''}">
<input type="text" placeholder="地区名..." name="districtName" value="${sc.districtName!''}">
<input type="button" value="搜索" onclick="searchBrancheCompany(false)">

<div class="J_tablelist table_list">
<table width="100%" cellspacing="0">
<thead>
<tr>
	<th>ID</th>
	<th align="center">用户</th>
	<th align="center">级别</th>
	<th align="center">地区</th>
	<th align="center">状态</th>
	<th align="center">修改时间</th>
	<th align="center">修改人</th>
	<th align="center">操作</th>
</tr>
</thead>
<tbody>
<#if brancheCompanyList??>
<#list brancheCompanyList as brancheCompany>
    <tr>
        <td align="center">${brancheCompany.id?c}</td>
        <td align="center">${brancheCompany.user.uname!''}</td>
        <td align="center">${brancheCompany.levelStr!''}</td>
        <td align="center">${brancheCompany.district.name!''}</td>
        <td align="center">${brancheCompany.statusStr!''}</td>
        <td align="center"><#if brancheCompany.updateTime??>${brancheCompany.updateTime?string('yyyy-MM-dd HH:mm:ss')!''}</#if></td>
        <td align="center"><#if brancheCompany.updatePerson??>${brancheCompany.updatePerson.uname!''}</#if></td>
        <td align="center">
            <a class="J_showdialog" href="javascript:;" onclick="editBrancheCompany(${brancheCompany.id?c})">详情</a>
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
<#assign pageId="BrancheCompany" />
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
