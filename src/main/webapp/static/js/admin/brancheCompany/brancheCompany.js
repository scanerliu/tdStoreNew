function searchBrancheCompany(f){
	var url = basePath+"/admin/branch/search";
	var loadData = "";
	if(f){
		loadData = null;
	}else{
		loadData = $("#brancheCompanyForm").serializeArray();
	}
	$("#results").load(url,loadData);
}

function fnGotoPageBrancheCompany(num){
	searchBrancheCompany(false);
}

function editBrancheCompany(num){
	var url = basePath+"/admin/branch/edit";
	var loadData={"id":num};
	$("#rightform").load(url,loadData);
	showForm();
}

function showForm(){
	$("#rightlist").hide();
	$("#rightform").show();
}
function returnList(){
	$("#rightlist").show();
	$("#rightform").html("").hide();
	refreshList();
}

function refreshList(){
	searchBrancheCompany(false);
}

function goCheck(id, isPass){
	var url = basePath+"/admin/branch/goCheck";
	var loadData={"id":id, "isPass":isPass};
	$.post(url,loadData,commonCallback,"text");
}

function commonCallback(data){
	var result = eval("("+data+")");
	$.messager.alert('消息提醒',result.msg);
	if(result.code==1){
		refreshList();
		returnList();
	}
}