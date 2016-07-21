function searchList(typeId,o){
	var url = basePath + "/mobile/product/list/search";
	var loadData = {"typeId":typeId,"orderby":o};
	
	$("#results").load(url,loadData);
}

function searchNew(o){
	var url = basePath + "/mobile/product/new/search";
	var loadData = {"orderby":o};
	
	$("#results").load(url,loadData);
}