// ----------------销售----------------
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

//----------------未销售----------------
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

//----------------用户收入 ----------------
function searchUserIncome(f){
	var url = basePath+"/admin/report/user/incomeSearch";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#userIncomeListForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageUserIncome(num){
	searchUserIncome(false);
}
/**钱包余额**/
function searchUserAccounts(f){
	var url = basePath+"/admin/report/user/accountsearch";
	var loadData = "";
	if(f){
		loadData = $("#searchForm").serializeArray();
	}else{
		loadData = $("#listForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageAccounts(num){
	searchUserAccounts(false);
}

