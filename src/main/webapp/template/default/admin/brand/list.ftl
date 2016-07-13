<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/core.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/brand/brandlist.js" type="text/javascript"></script>
<div id="rightlist">
<form id="listform">
<div id="results"></div>
</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
$(function(){
    searchBrands(true);
});
</script>
