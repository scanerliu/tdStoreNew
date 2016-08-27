function showQcode(){
	$("#listdiv").hide();
	$("#formdiv").show();
}

function returnQcodeList(){
	$("#formdiv").hide();
	$("#listdiv").show();
}

function genenateQcode()
{
	var uavatar = $("#cuavatar").val();
	var csnick = $("#csnick").val();
	var cscontent = $("#cscontent").val();
	if(csnick==""||csnick.length>25){
		alert("请正确填写您的昵称");
		$("#csnick").focus();
		return false;
	}
	if(cscontent==""||cscontent.length<6 ||cscontent.length>25){
		alert("请正确填写您的文字");
		$("#cscontent").focus();
		return false;
	}
	var url = basePath+"/mobile/user/saveInfo";
	var loadData = $('#spreadForm').serialize();
	$.post(url, loadData, saveUserInfoCallback, "text");
}

function saveUserInfoCallback(data){
	var result = eval("("+data+")");
	alert('消息提醒' + result.msg);
	if(result.code == 1){
//		$("#myspreadavatar").attr("src",basePath+$("#cuavatar").val());
//		$("#myspreadnick").html($("#csnick").val());
//		$("#myspreadcontent").html($("#cscontent").val());
		$("#imgcode").attr("src",basePath+"/mobile/user/mySpread/qrcode?title="+$("#cscontent").val()+"&t="+Math.random());
		returnQcodeList();
	}
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
