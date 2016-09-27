<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList()"><em>返回列表</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<form id="campaignEditForm" action="${app.basePath}/admin/campaign/save" class="easyui-form" method="post" data-options="novalidate:true">
<table class="table_form" width="100%">
	<#if branch?? && branch==true>
		<input type="hidden" id="edit_region_id" name="regionId" value="<#if campaign.regionId??>${campaign.regionId?c}<#else>-1</#if>">
	<#else>
	<tr>
        <th>地区：</th>
        <td>
            <#--地区  begin-->
			<span id="edit_firstDistrictLevel"></span>
			<span id="edit_secondDistrictLevel"></span>
			<span id="edit_thirdDistrictLevel"></span>
			<#--地区  end-->
			<input type="hidden" id="edit_region_id" name="regionId" value="<#if campaign.regionId??>${campaign.regionId?c}<#else>-1</#if>">
        </td>
    </tr>
    </#if>
	<tr>
        <th width="150">竞选人数：</th>
        <td><input type="text" name="quota" class="easyui-textbox" value="${campaign.quota!''}" style="width:200px;height:30px" data-options="required:true" validType="length[1,100]"></td>
    </tr>
    <#if campaign.createTime??>
	    <tr>
	        <th width="150">创建时间：</th>
	        <td>
	        	<label>${campaign.createTime?string('yyyy-MM-dd HH:mm:ss')}</label>
	        	<input type="hidden" name="createTime" value="${campaign.createTime?string('yyyy-MM-dd HH:mm:ss')}">
	        </td>
	    </tr>
	</#if>
	<#if campaign.createPerson??>
	    <tr>
	        <th width="150">创建人：</th>
	        <td>
	        	<label>${campaign.createPerson.uname!''}</label>
	        	<input type="hidden" name="createBy" value="${campaign.createBy?c}">
	        </td>
	    </tr>
	</#if>
	<#if campaign.updateTime??>
	    <tr>
	        <th width="150">更新时间：</th>
	        <td>
	        	<label>${campaign.updateTime?string('yyyy-MM-dd HH:mm:ss')}</label>
	        	<input type="hidden" name="updateTime" value="${campaign.updateTime?string('yyyy-MM-dd HH:mm:ss')}">
	        </td>
	    </tr>
	</#if>
	<#if campaign.updatePerson??>
	    <tr>
	        <th width="150">更新人：</th>
	        <td>
	        	<label>${campaign.updatePerson.uname!''}</label>
	        	<input type="hidden" name="updateBy" value="${campaign.updateBy?c}">
	        </td>
	    </tr>
	</#if>
	<tr>
		<th>状态：</th>
		<td>
			<input type="radio" name="status" value="1" <#if campaign.status==1>checked</#if>>正常
            <input type="radio" name="status" value="2" <#if campaign.status==2>checked</#if>>禁用
		</td>
	</tr>
    <tr>
        <td colspan="2" align="center">
        	<label style="padding-left: 10px;">内容:</label>
        	<textarea style="display:none;" rows="5" cols="60" id="cc" name="note">${campaign.note!''}</textarea>
		    <!-- 加载编辑器的容器 -->
		    <script id="campaignContent" name="content" type="text/plain">${campaign.note!''}</script>
        </td> 
    </tr>
    <tr>
        <td>
        	<input type="hidden" name="id" value="<#if campaign.id??>${campaign.id?c}</#if>">
        </td>
        <td>
            <button type="button" class="smt mr10" onclick="saveCampaign()">保存</button>
        </td>
    </tr>
</table>
</form>
</div>
<script>
	$(function(){
		if(${isNew}=="true"){
			getDistrictSelections(0, 0, "edit", null);
		}else{
			if(${levelCount}==3){
				getDistrictSelections(0, 0, "edit", function(){
					$("#edit_district_id_1").find("option[value='${upTwoDistrictId!'-1'}']").prop("selected",true);
				});
				// 模拟选择
				getDistrictSelections(1, ${upTwoDistrictId!'-1'}, "edit", function(){
					$("#edit_district_id_2").find("option[value='${upOneDistrictId!'-1'}']").prop("selected",true);
				});
				getDistrictSelections(2, ${upOneDistrictId!'-1'}, "edit", function(){
					$("#edit_district_id_3").find("option[value='${currentDistrictId!'-1'}']").prop("selected",true);
					$("#edit_region_id").val(${campaign.regionId?c});
				});
			}else if(${levelCount}==2){
					getDistrictSelections(0, 0, "edit", function(){
					$("#edit_district_id_1").find("option[value='${upOneDistrictId!'-1'}']").attr("selected",true);
				});
				// 模拟选择
				getDistrictSelections(1, ${upOneDistrictId!'-1'}, "edit", function(){
					$("#edit_district_id_2").find("option[value='${currentDistrictId!'-1'}']").attr("selected",true);
					$("#edit_region_id").val(${campaign.regionId?c});	
				});
			}else{
				getDistrictSelections(0, 0, "edit", function(){
					$("#edit_district_id_1").find("option[value='${currentDistrictId!'-1'}']").attr("selected",true);
				});
			}
		}
	});
</script>
<!-- 配置文件 -->
<script type="text/javascript" src="${app.basePath}/static/js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${app.basePath}/static/js/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
	$(document).ready(function(){
	    var ue = UE.getEditor('campaignContent');	
	});
</script>
