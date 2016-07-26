<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/productPackage/productPackage.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/productPackage/productSku.js" type="text/javascript"></script>
<div id="rightlist">
<form id="productPacakgeListForm">
<div id="results"></div>
</form>
</div>
<div id="rightform"></div>
<#--弹框-->
<div id="pskuswindow" class="easyui-window" closed="true" style="width:600px;height:300px;padding:10px"></div>
<script type="text/javascript">
$(function(){
    searchProductPackage(true);
});
</script>
