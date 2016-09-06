function showQcode(){
	$("#listdiv").hide();
	$("#formdiv").show();
}

function returnQcodeList(){
	$("#formdiv").hide();
	$("#listdiv").show();
}

function selectTab(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).addClass("active").siblings().removeClass("active");
		var index = $(this).index();
		if(index==1){
			showQcode();
		}else{
			returnQcodeList();
		}
	});
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
	var url = basePath+"/user/saveInfo";
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
		$("#imgcode").attr("src",basePath+"/user/mySpread/qrcode?title="+$("#cscontent").val()+"&t="+Math.random());
		returnQcodeList();
	}
}



function delCollect(id){
	var url = basePath+"/user/collect/delete";
	var loadData={"id":id};
	$.post(url,loadData,delCollectCallback,"text");
}

function delCollectCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		searchCollect();
	}
}
