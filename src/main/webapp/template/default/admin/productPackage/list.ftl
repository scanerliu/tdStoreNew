<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/productPackage/productPackage.js" type="text/javascript"></script>
<div id="rightlist">
<form id="productPacakgeListForm">
<div id="results"></div>
</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
$(function(){
    searchProductPackage(true);
});
</script>
