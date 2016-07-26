<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<script src="${app.basePath}/static/js/admin/productPackage/productSku.js" type="text/javascript"></script>
<div id="selectedSkuDiv">已选择：</div>
<div id="selectedSkuDiv" style="float:right;"><input type="text" style="width:120px;" id="searchProductName" value="" placeholder="商品名称..."><input type="button" onclick="searchSkuByProductName()" value="搜索"><input type="button" onclick="selectedSkus()" value="确定"></div>
<div id="skuListForm"></div>
<script type="text/javascript">
$(function(){
	searchProductSku(true);
});
</script>