function searcAgent(agentId){
	var url = basePath + "/mobile/agent/search/distric";
	var loadData = {"agentId":agentId};
	
	$("#results").load(url,loadData);
}

function searchAgentType(parentId,regionId){
	var url = basePath + "/mobile/agent/search/subtype";
	var loadData = {"parentId":parentId,"regionId":regionId};
	
	$("#sub_type_list").load(url,loadData);
}


function searchDis()
{
	var url = basePath + "/mobile/agent/search/distric";
	var loadData = $("#form").serializeArray();
	
	$("#results").load(url,loadData);
}

function subAgent()
{
	var typeId = $("#typeId").val();
	var regionId = $("#regionId").val();
	var disLevel = $("#disLevel").val();
	var level = $("#level").val();
	var agentId = $("#agentid").val();
	var groupId = $("#groupId").val();
	
	if(groupId != 2){
		// 分类ID为空，
		if(undefined == typeId || typeId == "")
		{
			alert("请选择代理类别");
			return ;
		}
	}
	if(null == agentId || null == level)
	{
		alert("参数错误");
		return ;
	}
	
	// 非全国代理 未选择地区提示
	if(level != 1 )
	{
		if(null == regionId || regionId == 0)
		{
			alert("请选择代理地区");
			return ;
		}
		// 区县级代理，只选择一级地区提示
		if(level != 2)
		{
			if(disLevel == 1)
			{
				alert("请选择下级地区");
				return ;
			}
		}
	}
	
	
	$("#sub_form").submit();
	
}



function cleft_height(){
	var dHeight=document.documentElement.clientHeight;
	
	var cla_left=document.getElementById('cla_left');
	var cla_right=document.getElementById('cla_right');
	var top_heater=document.getElementById('top_heater');
	var three3=document.getElementById('three3');
	cla_left.style.height=dHeight-top_heater.offsetHeight-three3.offsetHeight+'px';
	cla_right.style.height=dHeight-top_heater.offsetHeight-three3.offsetHeight-14+'px';
}

function left_right(){
	var cla_left=document.getElementById('cla_left');
	var oA=cla_left.getElementsByTagName('a');
	//var cla_right=document.getElementById('cla_right');
	//var arr_a=cla_right.getElementsByTagName('a');
	//var arr_i=cla_right.getElementsByTagName('i');
	// alert(arr_i.length);
	//var oUl=cla_right.getElementsByTagName('ul');
	//var arr=[],oindex=[];
	for(var i=0;i<oA.length;i++){
		oA[i].index=i;
		oA[i].onclick=function(){
			for(var i=0;i<oA.length;i++){
				oA[i].className='';
				//oUl[i].className='';
			}
			this.className='active';
			//oUl[this.index].className='active';
		}
	}
//	for(i=0;i<arr_a.length;i++){
//		if(arr_a[i].className=='active'){
//			arr.push(arr_a[i]);
//			oindex.push(i);
//		}
//	}
//	for(var i=0;i<arr.length;i++){
//		arr[i].index=i;
//		arr[i].onclick=function(){
//			for(var i=0;i<arr.length;i++){
//				arr_i[i].style.display='none';
//			}
//			arr_i[oindex[this.index]].style.display='block';
//		}
//	}
//	// alert(arr.length);
//	for(i=0;i<oUl.length;i++){
//		var oLi=oUl[i].getElementsByTagName('li');
//		oLichange(oLi);
//	}
//	function oLichange(obj){
//		for(var j=0;j<obj.length;j++){
//			obj[j].onclick=function(){
//				for(j=0;j<obj.length;j++){
//					obj[j].className='';
//				}
//				this.className='active';
//			}
//		}
//	}
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
			searchDis();
		}
	}
}

function citylist(){
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
			//searchExper(false);
			searcAgentAddr();
		}
	}
}

function searcAgentAddr()
{
	var url = basePath + "/mobile/agent/search/addr";
	var loadData = $("#form").serializeArray();
	
	$("#tarea").load(url,loadData);
}

function addAgent(id){
	var url = basePath+"/mobile/agent/addagent";
	var loadData = $("#form").serializeArray();
	$.post(url,loadData,saveCallback,"text");
}

function saveCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$("#pay_warn").css("display","block")
	}else{
		alert(result.msg);
	}
}

function getLongDistricts(_settings){
	var settings = {obj:"",num:0,level:1,total:1,callback:""};
	settings=$.extend(settings,_settings);
	var url = basePath+"/region/regionlongselect";
	var loadData = "";
	var callback = settings.callback;
	if(settings.num==0){
		loadData = {'upid':0,'level':1,'totalLevel':settings.total,'callback':''+settings.callback+''};
		$("#provincespn").loading().load(url,loadData);
		$("#cityspn").html("");
		$("#regionspn").html("");
		$("#townspn").html("");
		$("#villagespn").html("");
		$("#upid").val("0");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		if(upid!==""){
			loadData = {'upid':upid,'provinceId':upid,'level':2,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#cityspn").loading().load(url,loadData);
			$("#regionspn").html("");
			$("#townspn").html("");
			$("#villagespn").html("");
			$("#upid").val(upid);
			if(callback!=""){
			}
		}else{
			$("#cityspn").html("");
			$("#regionspn").html("");
			$("#townspn").html("");
			$("#villagespn").html("");
		}
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		if(upid!==""){
			var provinceid = $("#provinceId").val();
			loadData = {'upid':upid,'provinceId':provinceid,'level':3,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#regionspn").loading().load(url,loadData);
			$("#townspn").html("");
			$("#villagespn").html("");
			$("#upid").val(upid);
			if(callback!=""){
			}
		}else{
			$("#regionspn").html("");
			$("#townspn").html("");
			$("#villagespn").html("");
		}
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		if(upid!==""){
			var provinceid = $("#provinceId").val();
			loadData = {'upid':upid,'provinceId':provinceid,'level':4,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#townspn").loading().load(url,loadData);
			$("#villagespn").html("");
			$("#upid").val(upid);
			if(callback!=""){
			}
		}else{
			$("#townspn").html("");
			$("#villagespn").html("");
		}
	}else if(settings.num==4){
		var upid = $(settings.obj).val();
		if(upid!==""){
			var provinceid = $("#provinceId").val();
			loadData = {'upid':upid,'provinceId':provinceid,'level':5,'totalLevel':settings.total,'callback':''+settings.callback+''};
			$("#villagespn").loading().load(url,loadData);
			$("#upid").val(upid);
			if(callback!=""){
			}
		}else{
			$("#villagespn").html("");
		}
	}
}

function getAllTypes(_settings){
	var settings = {obj:"",num:0};
	settings=$.extend(settings,_settings);
	var url = basePath+"/agent/producttypeallselect";
	var loadData = "";
	if(settings.num==0){
		loadData = {'parentId':0};
		$("#onetypespn").loading().load(url,loadData);
		$("#twotypespn").html("");
		$("#typespn").html("");
		$("#productTypeId").val("");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#twotypespn").loading().load(url,loadData);
		$("#typespn").html("");
		$("#productTypeId").val("");
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#typespn").loading().load(url,loadData);
		$("#productTypeId").val("");
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		$("#productTypeId").val(upid);
	}
}