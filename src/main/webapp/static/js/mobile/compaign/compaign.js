function searchCompaign(t){
	var url = basePath + "/mobile/campaign/search";
	
	var loadData = null;
	if(t){
		loadData = null;
	}else{
		loadData = $("#form").serializeArray();
	}
	$("#results").load(url,loadData);
}

function ranktop(){
	// 弹出个人信息
	var rank_match=document.getElementById('rank_match');
	var rank_top=document.getElementById('rank_top');
	var tLi=rank_top.getElementsByTagName('li');
	var rLi=rank_match.getElementsByTagName('li');
	var rA=rank_top.getElementsByTagName('a');
	var dHeight=document.documentElement.clientHeight;
	for(i=0;i<rA.length;i++){
		rA[i].index=i;
		rA[i].onclick=function(){
			rLi[this.index].className='active';
		}
	}
	for(i=0;i<rLi.length;i++){
		rLi[i].style.height=dHeight+'px';
		var opa_bg=rLi[i].getElementsByTagName('div')[0];
		var oMenu=rLi[i].getElementsByTagName('menu')[0];
		opa_bg.style.top=(dHeight-opa_bg.offsetHeight)/2+'px';
		// console.log(dHeight+':'+opa_bg.offsetHeight+':'+(dHeight-opa_bg.offsetHeight)/2);
		oMenu.onclick=function(){
			for(i=0;i<rLi.length;i++){
				rLi[i].className='';
			}
		}
	}
}

function three_bing(){
	// 三级联动
	var three3=document.getElementById('three3');
	var oSec=three3.getElementsByTagName('section');
	var oSpan=three3.getElementsByTagName('span');
	var oSelect=three3.getElementsByTagName('select');
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
			searchCompaign(false);
		}
	}
}