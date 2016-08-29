function three2(){
	var three2=document.getElementById('three2');
	var three2_a=three2.getElementsByTagName('a');
	var three2_match=document.getElementById('three2_match');
	var three2_li=three2_match.getElementsByTagName('li');
	var nowA,nowLi;
	for(var i=0;i<three2_a.length;i++){
		if(three2_a[i].className=='active'){
			nowA=nowLi=i;
			break;
		}
	}
	for(var i=0;i<three2_a.length;i++){
		three2_a[i].index=i;
		three2_a[i].onclick=function(){
			three2_a[nowA].className="";
			three2_li[nowLi].className="";
			this.className="active";
			three2_li[this.index].className="active";
			nowA=nowLi=this.index;
		}
	}
}
function four(){
	var four=document.getElementById('four');
	var four_sec=four.getElementsByTagName('section');
	var four_match=document.getElementById('four_match');
	var four_li=four_match.getElementsByTagName('li');
	var nowA=0,nowLi=0;
	for(var i=0;i<four_sec.length;i++){
		four_sec[i].index=i;
		four_sec[i].onclick=function(){
			four_sec[nowA].className="";
			four_li[nowLi].className="";
			this.className="active";
			four_li[this.index].className="active";
			nowA=nowLi=this.index;
		}
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
			$("#form").submit();
		}
	}
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
		}
	}
}
function payway(){
	var pay_warn=document.getElementById('pay_warn');
	var ok_box=document.getElementById('ok_box');
	var dHeight=document.documentElement.clientHeight;
	pay_warn.style.height=dHeight+'px';
	ok_box.style.top=(dHeight-ok_box.offsetHeight)/2+'px';
}
function warn_box(){
	var pay_warn=document.getElementById('pay_warn');
	var warn_box=document.getElementById('warn_box');
	var dHeight=document.documentElement.clientHeight;
	pay_warn.style.height=dHeight+'px';
	warn_box.style.top=(dHeight-warn_box.offsetHeight)/2+'px';
}
function ul_li(obj){
	var oLi=obj.getElementsByTagName('li');
	for(var i=0;i<oLi.length;i++){
		oLi[i].onclick=function(){
			for(i=0;i<oLi.length;i++){
				oLi[i].className='';
			}
			this.className='active';
		}
	}
}
function left_right(){
	var cla_left=document.getElementById('cla_left');
	var oA=cla_left.getElementsByTagName('a');
	var cla_right=document.getElementById('cla_right');
	var arr_a=cla_right.getElementsByTagName('a');
	var arr_i=cla_right.getElementsByTagName('i');
	// alert(arr_i.length);
	var oUl=cla_right.getElementsByTagName('ul');
	var arr=[],oindex=[];
	for(var i=0;i<oA.length;i++){
		oA[i].index=i;
		oA[i].onclick=function(){
			for(var i=0;i<oA.length;i++){
				oA[i].className='';
				oUl[i].className='';
			}
			this.className='active';
			oUl[this.index].className='active';
		}
	}
	for(i=0;i<arr_a.length;i++){
		if(arr_a[i].className=='active'){
			arr.push(arr_a[i]);
			oindex.push(i);
		}
	}
	for(var i=0;i<arr.length;i++){
		arr[i].index=i;
		arr[i].onclick=function(){
			for(var i=0;i<arr.length;i++){
				arr_i[i].style.display='none';
			}
			arr_i[oindex[this.index]].style.display='block';
		}
	}
	// alert(arr.length);
	for(i=0;i<oUl.length;i++){
		var oLi=oUl[i].getElementsByTagName('li');
		oLichange(oLi);
	}
	function oLichange(obj){
		for(var j=0;j<obj.length;j++){
			obj[j].onclick=function(){
				for(j=0;j<obj.length;j++){
					obj[j].className='';
				}
				this.className='active';
			}
		}
	}
}

/*function left_right2(){
	var cla_left=document.getElementById('cla_left');
	var oA=cla_left.getElementsByTagName('a');
	var cla_right=document.getElementById('cla_right');
	var oUl=cla_right.getElementsByTagName('ul');
	// alert(oUl.length);
	for(var i=0;i<oA.length;i++){
		oA[i].index=i;
		oA[i].onclick=function(){
			for(var i=0;i<oA.length;i++){
				oA[i].className =' ';
				
			}
			this.className='active';
		}
	}
	for(i=0;i<oUl.length;i++){
		var oLi=oUl[i].getElementsByTagName('li');
		oLichange(oLi);
	}
	function oLichange(obj){
		for(var j=0;j<obj.length;j++){
			obj[j].onclick=function(){
				if(this.className==""){
					this.className='active';
				}else{
					this.className=" ";
				}
			}
		}
	}
}*/
function left_right2(){
	var cla_left=document.getElementById('cla_left');
	var oA=cla_left.getElementsByTagName('a');
	
	// alert(oUl.length);
	for(var i=0;i<oA.length;i++){
		oA[i].index=i;
		oA[i].onclick=function(){
			for(var i=0;i<oA.length;i++){
				oA[i].className =' ';
				
			}
			this.className='active';
		}
	}
	
}

function left_right3(){
	var cla_right=document.getElementById('cla_right');
	var oUl=cla_right.getElementsByTagName('ul');
	for(i=0;i<oUl.length;i++){
		var oLi=oUl[i].getElementsByTagName('li');
		oLichange(oLi);
	}
	function oLichange(obj){
		for(var j=0;j<obj.length;j++){
			obj[j].onclick=function(){
				if(this.className==""){
					this.className='active';
				}else{
					this.className=" ";
				}
			}
		}
	}
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
//function cleft_height2(){
//	var dHeight=document.documentElement.clientHeight;
//	var cla_left=document.getElementById('cla_left');
//	var classify=document.getElementById('classify');
//	var cla_right=document.getElementById('cla_right');
//	var top_heater=document.getElementById('top_heater');
//	cla_left.style.height=dHeight-top_heater.offsetHeight+'px';
//	classify.style.height=dHeight-top_heater.offsetHeight+'px';
//	cla_right.style.height=dHeight-top_heater.offsetHeight-14+'px';
//}


function cleft_height2(){
	var dHeight=window.screen.height ;
	var top_heater=document.getElementById('top_heater');
	var cla_left=document.getElementById('cla_left');
	cla_left.style.height=dHeight-top_heater.offsetHeight+'px';
	var classify=document.getElementById('classify');
	classify.style.height=dHeight-top_heater.offsetHeight+'px';
	
}
function cleft_height3(){
	var dHeight=window.screen.height ;
	
	var cla_right=document.getElementById('cla_right');
	var top_heater=document.getElementById('top_heater');

	cla_right.style.height=dHeight-top_heater.offsetHeight-14+'px';

}

function news_alllist(){
	var oBox=document.getElementById('news_alllist');
	var i=1;
	setInterval(function(){
		oBox.style.transition='1.5s';
		oBox.style.webkitTransform='translateY(-'+1.8*i+'rem)';
		oBox.style.transform='translateY(-'+1.8*i+'rem)';
		i++;
		console.log(i);
		if(i>2){
			i=0;
		}
	},4000);
}
function value_over(){
	var avalue_btn=document.getElementById('avalue_btn');
	var value_over=document.getElementById('value_over');
	var oMenu=value_over.getElementsByTagName('menu')[0];
	avalue_btn.onclick=function(){
		value_over.style.display='block';
	}
	oMenu.onclick=function(){
		value_over.style.display='none';
	}
}
function value_over1(){
	var avalue_btn=document.getElementById('avalue_btn');
	var value_over=document.getElementById('value_over1');
	var oMenu=value_over.getElementsByTagName('menu')[0];
	avalue_btn.onclick=function(){
		value_over.style.display='block';
	}
	oMenu.onclick=function(){
		value_over.style.display='none';
	}
}


function acare(){
    var onOFF=true;
    var acare=document.getElementById('acare');
    acare.onclick=function(){
        if(onOFF){
            this.className='acare2';
        	onOFF=false;
        }else{
            this.className='acare';
        	onOFF=true;
        }
    }
}
function file_btn(){
	var file_btn=document.getElementById('file_btn');
	var file_btna=document.getElementById('file_btna');
	file_btna.onclick=function(){
		file_btn.click();
	}	
}
function two2(){
	var two2=document.getElementById('two2');
	var two2_match=document.getElementById('two2_match');
	var oA=two2.getElementsByTagName('a');
	var oDiv=two2_match.getElementsByTagName('div');
	var nowA,nowDiv;
	for(var i=0;i<oA.length;i++){
		if(oA[i].className=='active'){
			nowA=nowDiv=i;
			break;
		}
	}
	oDiv[nowDiv].style.display='block';
	for(var i=0;i<oA.length;i++){
		oA[i].index=i;
		oA[i].onclick=function(){
			// for(var i=0;i<oA.length;i++){
			// 	oA[i].className='';
			// }
			oA[nowA].className='';
			oDiv[nowDiv].style.display='none';
			this.className='active';
			nowA=nowDiv=this.index;
			oDiv[nowDiv].style.display='block';
		}
	}
}
function three2_hao(){
	var three2=document.getElementById('three2');
	var three2_a=three2.getElementsByTagName('a');
	var three2_hao=document.getElementById('three2_hao');
	var three2_ul=three2_hao.getElementsByTagName('ul');
	var nowA,nowUl;
	for(var i=0;i<three2_a.length;i++){
		if(three2_a[i].className=='active'){
			nowA=nowUl=i;
			break;
		}
	}
	three2_ul[nowUl].style.display='block';
	for(var i=0;i<three2_a.length;i++){
		three2_a[i].index=i;
		three2_a[i].onclick=function(){
			three2_a[nowA].className="";
			three2_ul[nowUl].style.display='none';
			this.className="active";
			three2_ul[this.index].style.display='block';
			nowA=nowUl=this.index;
		}
	}
}
function all_sel(){
	var two2_match=document.getElementById('two2_match');
	var all_sel=document.getElementById('all_sel');
	var arr=[];
	var ipt=two2_match.getElementsByTagName('input');
	for(i=0;i<ipt.length;i++){
		ipt[i].onclick=function(){
			if(this.checked){
				this.className='och';
			}else{
				this.className='';
			}
			// alert(this.checked);
		}
	}
	all_sel.onclick=function(){
			if(this.checked){
				this.className='och';
				for(i=0;i<ipt.length;i++){
					ipt[i].className='och';
				}
			}else{
				this.className='';
				for(i=0;i<ipt.length;i++){
					ipt[i].className='';
				}
			}
		}
}