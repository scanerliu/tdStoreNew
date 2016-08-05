function searchKeyword(key,o){
	var url = basePath + "/mobile/search/search";
	var loadData = {"keyword":key,"orderby":o};
	
	$("#results").load(url,loadData);
}

function searchkey(){
	var key = $("#keywords").val();
	
	var keyword = getcookie("keywords");
	var keywords =null;
	if("" == keyword){
		keywords = key;
	}else{
		keywords = key+","+keyword;
	}
	setCookie("keywords",keywords,30);
	
	window.location.href =basePath+"/mobile/search/list?keyword="+key
}

/**
 * 获取Cookie 
 * 解决中文乱码
 */
function getcookie(name){
	var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]); return null;
}

function getCookieValue(){
	var keywords = getcookie("keywords");
	
	if("" != keywords){
		var keys =  keywords.split(",");
		if(null != keys && keys.length > 0){
			for (var i = 0; i < keys.length; i++) {
				if(i < 9){
					$("#key1").append("<a href='"+basePath+"/mobile/search/list?keyword="+keys[i]+"' title='"+keys[i]+"'>"+keys[i]+"</a>");
				}
			}
		}
	}
}