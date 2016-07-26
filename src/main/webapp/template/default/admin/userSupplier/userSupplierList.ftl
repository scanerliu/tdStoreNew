<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/userSupplier/userSupplier.js" type="text/javascript"></script>
<div id="rightlist">
<form id="userSupplierForm">
<div id="results"></div>
</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
$(function(){
    searchUserSupplier(true);
});
</script>
