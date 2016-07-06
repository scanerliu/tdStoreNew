//登陆
function login(){
	var f = $('#loginForm').form('enableValidation').form('validate');
	if(f){
		$('#loginForm').form('submit',{
			  success : function(data){
				  var result = eval("("+data+")");
				  if(result.code==1){
					   location.replace(basePath+'/admin/index');//成功之后用js进行跳转
				  }else{
					  $.messager.alert('消息','用户或密码错误!');
				  }
			  }
			 });
	};
}
