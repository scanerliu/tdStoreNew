<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<link rel="stylesheet" href="${app.basePath}/static/default/admin/style/style.css"/>
<link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
<script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/easyui/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/admin/article/ad.js" type="text/javascript"></script>
<div id="rightlist">
	<form id="adsenseform">
		<div id="results"></div>
	</form>
</div>
<div id="rightform"></div>

<script type="text/javascript">
	$(function(){
	    searchAdsense(true);
	});
</script>
