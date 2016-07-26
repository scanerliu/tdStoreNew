function searchExper(t){
	var url = basePath + "/mobile/experience/search";
	
	var loadData = null;
	if(t){
		loadData = null;
	}else{
		loadData = $("#form").serializeArray();
	}
	$("#results").load(url,loadData);
}


function three_bing2(){
	// 三级联动
	var tarea=document.getElementById('tarea');
	var oSec=tarea.getElementsByTagName('section');
	var oSpan=tarea.getElementsByTagName('span');
	var oSelect=tarea.getElementsByTagName('select');
	for(i=0;i<oSec.length;i++){
		oSec[i].index=i;
		oSec[i].onclick=function(){
			// alert(this.index);
			choose(this.index);
		}
	}
	function choose(num){
		oSelect[num].style.display="block";
		oSelect[num].addEventListener("click",function(){},false);
		oSelect[num].onchange=function(){
			oSpan[num].innerHTML=oSelect[num].value;
			oSelect[num].style.display="none";
			searchExper(false);
		}
	}
}