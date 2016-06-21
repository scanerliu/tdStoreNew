<#import "/common/app.ftl" as app>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<div class="subnav"><div class="content_menu ib_a blue line_x"><a href="javascript:;" class="add fb J_showdialog" onclick="returnList(${provinceId!''}, ${cityId!''})"><em>返回列表页</em></a>&#12288;</div></div>
<div class="pad_lr_10">
<table>
    <tr>
        <td>地区名称：</td>
        <td><input type="text" name="name" value="${district.name!''}"><button type="button" class="d-button" onclick="updateDistrict()">保存</button></td>
    </tr>
    <tr>
        <td><input type="hidden" name="id" value="${district.id!''}"></td>
        <td>&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td><input type="hidden" name="provinceId" value="${provinceId!''}"></td>
        <td>&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td><input type="hidden" name="cityId" value="${cityId!''}"></td>
        <td>&nbsp;&nbsp;</td>
    </tr>
</table>
</div>