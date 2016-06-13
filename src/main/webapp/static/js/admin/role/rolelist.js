function searchRoles(f){
	var url = basePath+"/admin/role/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#rolelistform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function gotoPageRoles(num){
	searchRoles(false);
}

