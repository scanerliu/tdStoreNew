function searchCollect()
{
	var url = basePath+"/mobile/user/collect/search";
	var loadData = null;

	$("#results").load(url,loadData);
}



function delCollect(id){
	var url = basePath+"/mobile/user/collect/delete";
	var loadData={"id":id};
	$.post(url,loadData,delCollectCallback,"text");
}

function delCollectCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		searchCollect();
	}
}
