<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/core.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/role/rolelist.js" type="text/javascript"></script>
<div id="rightlist">
<form id="rolelistform">
<div id="results"></div>
</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
$(function(){
    searchRoles(true);
});
</script>
