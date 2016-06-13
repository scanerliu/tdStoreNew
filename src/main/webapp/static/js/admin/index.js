function showleftmenu(pid){
	if(pid>0){
		var url = basePath+"/admin/loadmenu";
		var loadData = {"parentId":pid};
		$("#J_lmenu").load(url,loadData);		
	}
}