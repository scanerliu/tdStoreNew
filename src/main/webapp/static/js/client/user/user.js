/**
 * 签到
 */
function tipshide(obj){
	var url = basePath+"/user/sign";
	var integral = $("#uintegral");
	var pintegral = integral.html();
	var integralInt = parseInt(pintegral);
	$.post(url,null,function(data){
		var result = eval("("+data+")");
		var tipobj = "";
		var wrap = obj.parent();
		if(result.code==0){
			tipobj = $('<label= class="tips">'+result.msg+'</label>');
		}else if(result.code==1){
			// 修改弹框积分
			tipobj = $('<label= class="tips">签到成功，本次获得'+result.gettedIntegral+'个积分。</label>');
			var totalintegral = parseInt(result.gettedIntegral)+integralInt;
			integral.html(totalintegral);			
		} 
		wrap.append(tipobj);
		tipobj.show();
		tipobj.css({
		    '-webkit-transform':'translateY(-10px)',
		    '-ms-transform':'translateY(-10px)',
		    '-o-transform':'translateY(-10px)',
		    'transform':'translateY(-10px)',
		    'opacity':'.5'
		    }).fadeOut();
		setTimeout(function(){tipobj.remove();},1000);
	},"text");
}

function saveUserInfo(){
	var url = basePath+"/mobile/user/saveInfo";
	var loadData = $('#userInfoForm').serialize();
	$.post(url, loadData, saveUserInfoCallback, "text");
}

function saveUserInfoCallback(data){
	var result = eval("("+data+")");
	alert('消息提醒' + result.msg);
	if(result.code == 1){
		window.location.href=basePath+"/mobile/user/center";
	}
}


function verifyApply(id, status){
	var url = basePath+"/mobile/user/verifyExperienceStoreApply";
	var loadData = {'id':id, 'status': status};
	$.post(url, loadData, verifyApplyCallback, "text");
}

function verifyApplyCallback(data){
	var result = eval("("+data+")");
	alert('消息提醒'+result.msg);
	if(result.code == 1){
		window.location.reload();
	}
}

function commonCallback(data){
	var result = eval("("+data+")");
	alert('消息提醒'+result.msg);
}

//验证码
var wait=30;
var isWait = false; //验证码请求等待
function waitSeconds(o) {
    if (wait == 0) {
    	// 为获取验证按钮绑定获取验证码事件
		$("#getChangeUserPasswordValidCode").bind("click", function(){
			var vurl = basePath+"/mobile/user/verifyCode";
			var vcode =	$("#verifyCode").val();
			if(vcode == ""){
				alert("请输入图形验证码");
				return;
			}
			var vdata = {'vcode': vcode};
			$.post(vurl, vdata, function(data){
				var jdata = eval('('+data+')');
				if(jdata.code == 0){
					alert(jdata.msg);
					return;
				}else{
					isWait = true;
					waitSeconds($("#getChangeUserPasswordValidCode"));
					var url = basePath+"/mobile/user/getChangeUserPasswordValidCode";
					$.post(url, function(pdata){
						var result = eval("("+pdata+")");
						alert('消息提醒'+result.msg);
						isWait = false;
					}, "text");
				}
			}, "text");
		});
		// 为获取验证按钮绑定等待60秒事件
		$("#getChangeUserPasswordValidCode").bind("click", function(){
			if(isWait){
				waitSeconds($("#getChangeUserPasswordValidCode"));			
			}
		});
    	o.text("获取验证码");
        wait = 30;
    } else {
    	$('#getChangeUserPasswordValidCode').unbind("click");
        o.text("重新发送(" + wait + ")");
        wait--;
        setTimeout(function() {
        	waitSeconds(o)
        }, 1000);
    }
}


//验证码
var pwait=30;
var pisWait = false;
function waitSecondsForPhoneNum(o) {
    if (pwait == 0) {
    	// 为获取验证按钮绑定获取验证码事件
		$("#getChangePhoneNumValidCode").bind("click", function(){
			var vurl = basePath+"/mobile/user/verifyCode";
			var vcode =	$("#verifyCode").val();
			if(vcode == ""){
				alert("请输入图形验证码");
				return;
			}
			var vdata = {'vcode': vcode};
			$.post(vurl, vdata, function(data){
				var jdata = eval('('+data+')');
				if(jdata.code == 0){
					alert(jdata.msg);
					return;
				}else{
					var phone = $("#utel").val();
					if(phone == ""){
						alert("请输入手机号码。");
						return;
					}else if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){ 
						alert("手机号码有误，请重填");
						return;
					}else{
						pisWait = true;
						waitSecondsForPhoneNum($("#getChangePhoneNumValidCode"));
						var url = basePath+"/mobile/user/getChangePhoneNumValidCode";
						var loadData = {'phone': phone};
						$.post(url, loadData, function(pdata){
							var result = eval("("+pdata+")");
							alert('消息提醒'+result.msg);
							pisWait = false;
						}, "text");			
					}
				}
			}, "text");
			
		});
		// 为获取验证按钮绑定等待60秒事件
		$("#getChangePhoneNumValidCode").bind("click", function(){
			if(pisWait){
				waitSecondsForPhoneNum($("#getChangePhoneNumValidCode"));
			}
		});
    	o.text("获取验证码");
        pwait = 30;
    } else {
    	$('#getChangePhoneNumValidCode').unbind("click");
        o.text("重新发送(" + pwait + ")");
        pwait--;
        setTimeout(function() {
        	waitSecondsForPhoneNum(o)
        }, 1000);
    }
}

/**
 * 查询收货地址
 * @param flag
 */
function searchAddress(flag){
	var url = basePath+"/user/sarchshoppingaddress";
	var loadData = null;
	$("#results").loading().load(url,null);
}