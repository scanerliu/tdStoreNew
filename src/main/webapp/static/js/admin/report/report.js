function searchSale(f){
	var url = basePath+"/admin/report/sale/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#saleListForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageSale(num){
	searchSale(false);
}

function refreshList(){
	searchSale(false);
}


function searchUnsale(f){
	var url = basePath+"/admin/report/unsale/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#unsaleListForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageUnsale(num){
	searchUnsale(false);
}

function refreshList(){
	searchUnsale(false);
}

