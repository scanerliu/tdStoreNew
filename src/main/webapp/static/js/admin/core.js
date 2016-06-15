$.fn.tdload = function (url,loadData) {
    $(this).load(url,loadData,function (response,status,xhr) {
    	var result = eval("("+response+")");
    	if(result.code!=undefined){
    		$.messager.alert('消息提醒',result.msg);
    	}
    });
}
