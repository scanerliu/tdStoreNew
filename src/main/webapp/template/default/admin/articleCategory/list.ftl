<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/articleCategory/articleCategory.js" type="text/javascript"></script>
<div id="rightlist">
<form id="articleCategorylistform">
<div id="results"></div>
</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
$(function(){
    searchArticleCategory(true);
});
</script>
