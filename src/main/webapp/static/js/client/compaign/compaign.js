function searchCompaign(t){
	var url = basePath + "/campaign/search";
	var loadData = null;
	if(t){
		loadData =$("#searchform").serializeArray();
		var newsurl = basePath + "/campaign/searchads";
		$("#newsList").load(url,loadData);
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function getDistricts(_settings){
	var settings = {obj:"",num:0,total:1,callback:""};
	settings=$.extend(settings,_settings);
	var url = basePath+"/region/regionselect";
	var loadData = "";
	var callback = settings.callback;
	if(settings.num==0){
		loadData = {'upid':0,'totalLevel':settings.total,'callback':''+settings.callback+''};
		$("#provincespn").loading().load(url,loadData);
		$("#cityspn").html("");
		$("#regionspn").html("");
		$("#regionId").val("");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		$("#regionId").val(upid);
		searchCompaign(true);
		loadData = {'upid':upid,'provinceId':upid,'totalLevel':settings.total,'callback':''+settings.callback+''};
		$("#cityspn").loading().load(url,loadData);
		$("#regionspn").html("");
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		$("#regionId").val(upid);
		var provinceid = $("#provinceId").val();
		loadData = {'upid':upid,'provinceId':provinceid,'totalLevel':settings.total,'callback':''+settings.callback+''};
		$("#regionspn").loading().load(url,loadData);
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		$("#regionId").val(upid);
		searchCompaign(true);
	}
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