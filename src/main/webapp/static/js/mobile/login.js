function login(){
	var username=$("#username").val();
	var password=$("#password").val();
	//用户名
	var nameReg = /^[0-9a-zA-Z]{11,26}$/;
	var passReg = /^[0-9a-zA-Z]{6,20}$/;
	var fname = nameReg.test(username);
	if(fname){}else{
		alert("请正确输入用户名:11位至26位数字或字母！");
		$("#username").focus();
		return;
	}
	var fpass = passReg.test(password);
	if(fpass){}else{
		alert("请正确输入密码:6位至20位数字或字母！");
		$("#password").focus();
		return;
	}
	
	$("#loginForm").submit();
}