function searchCollects(f){
	var url = basePath+"/user/collect/search";
	var loadData = "";
	if(f){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageCollects(num){
	searchCollects(false);
}

function lgoods(){
  var oUl=document.getElementById('l_goodsall');
  var aLi=oUl.getElementsByTagName('li');
  var that=null;
  for(i=0;i<aLi.length;i++){
    aLi[i].onmouseover=function(){
      for(j=0;j<aLi.length;j++){
        aLi[j].getElementsByTagName('div')[0].style.display="none";
      }
      var obj_div=this.getElementsByTagName('div');
      obj_div[0].style.display="block";
    }
    aLi[i].onmouseout=function(){
      for(j=0;j<aLi.length;j++){
        aLi[j].getElementsByTagName('div')[0].style.display="none";
      }
    }
  }
}

/**
 * 点击收藏
 */
function addCollect(productId)
{
    if (undefined == productId)
    {
        return;
    }
    openwaiting();
    $.ajax({
        type:"post",
        url:basePath+"/product/collect",
        data:{"productId": productId},
        dataType: "json",
        success:function(data){
            // 需登录
        	closewaiting();
            if (data.code==0)
            {
                alert(data.msg);
            }
            searchCollects(false);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 关闭等待图标
			closewaiting();
			alert("收藏失败");
		},
    });
}