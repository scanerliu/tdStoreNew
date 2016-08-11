//暂时抄袭

// 元素拖拽(pc)
function drag(obj) {
	obj.onmousedown = function(ev) {
		var ev = ev || event;	
		var disX = ev.clientX - this.offsetLeft;
		var disY = ev.clientY - this.offsetTop;
		// 设置全局捕获:兼容ie
		if ( obj.setCapture ) {
			obj.setCapture();
		}
		document.onmousemove = function(ev) {
			var ev = ev || event;
			obj.style.left = ev.clientX - disX + 'px';
			obj.style.top = ev.clientY - disY + 'px';
		}
		document.onmouseup = function() {
			document.onmousemove = document.onmouseup = null;
			//释放全局捕获 releaseCapture();
			if ( obj.releaseCapture ) {
				obj.releaseCapture();
			}
		}
		// 阻止默认事件:标准下
		return false;
	}
}

// 获取、设置、移除cookie
function setCookie(key, value, t) {
	var oDate = new Date();
	oDate.setDate( oDate.getDate() + t );
	document.cookie = key + '=' + value + ';expires=' + oDate.toGMTString();
}
function getCookie(key) {
	var arr1 = document.cookie.split('; ');
	for (var i=0; i<arr1.length; i++) {
		var arr2 = arr1[i].split('=');
		if ( arr2[0] == key ) {
			return decodeURI(arr2[1]);
		}
	}
}
function removeCookie(key) {
	setCookie(key, '', -1);
}

//暂时抄袭-结束

// 获取元素的各种属性
function getStyle(obj,attr){
	return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj)[attr];
}

// 获取元素到页面边缘的left/top值
function getPosition(obj){
	var pos={'left':0,'top':0}
	while(obj){
		pos.left+=obj.offsetLeft;
		pos.top+=obj.offsetTop;
		obj=obj.offsetParent;
	}
	return pos;
}

// 通过class获取元素
function getClassName(obj,sName){	
	var aTmp = obj.getElementsByTagName('*');
	var aRes=[];
	var arr =[];
	for(var i=0;i<aTmp.length;i++){	
		arr = aTmp[i].className.split(' ');
		for (var j=0;j<arr.length;j++){
			if(arr[j] == sName){
				aRes.push(aTmp[i]);
			}
		}
	}
	return aRes;
}

// 为元素添加class
function addClass(obj,sName){
	if(obj.className==''){
		obj.className=sName;
	}else{
		var arrClass=obj.className.split(' ');
		var dure=arrHasValue(arrClass,sName);//审查一个数组中是否含有某个值
		if(dure==-1){
			obj.className+=' '+sName;
		}
	}
}

// 为元素移除class
function removeClass(obj,sName){
	if(obj.className!=''){
		var arrClass=obj.className.split(' ');
		var dure=arrHasValue(arrClass,sName);// 审查一个数组中是否含有某个值
		if(dure!=-1){
			arrClass.splice(dure,1);
			obj.className=arrClass.join(' ');
		}
	}
}

// 审查一个数组中是否含有某个值
function arrHasValue(arr,val){
	for(var i=0;i<arr.length;i++){
		if(arr[i]==val){
			return i;
		}
	}
	return -1;
}
// 设置触屏版的单位rem
function setRem(){
	var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';//判断屏幕旋转还是resize
	function setRem2(){
		var dWidth=document.documentElement.clientWidth;
		if(dWidth>640){dWidth=640}
		document.documentElement.style.fontSize=dWidth/6.4+'px';
	}
	setRem2();
	window.addEventListener(resizeEvt,function(){
		setRem2();
	},false)
}
//tab切换(一个页面支持多个)
//<div class="tabh">
//		<a href="" class="active"></a>
//		<a href=""></a>
//</div>
//<ul>
//		<li class="active"></li>
//		<li></li>
//</ul>
function itab(){
	var tabh=getClassName(document,'tabh');
	var tabb=getClassName(document,'tabb');
	for(var i=0;i<tabh.length;i++){
		var htag=tabh[i].children[0].tagName;
		var hLi=tabh[i].getElementsByTagName(htag);//类似head下面的li选项集合
		var btag=tabb[i].children[0].tagName;
		var bLi=tabb[i].getElementsByTagName(btag);//类似body下面的li选项集合
		tab2(hLi,bLi);
	}
	function tab2(hLi,bLi){
		for(var j=0;j<hLi.length;j++){
			hLi[j].index=j;
			hLi[j].onclick=function(){
				for(var j=0;j<hLi.length;j++){
					removeClass(hLi[j],'active');
					removeClass(bLi[j],'active');
				}
				addClass(this,'active');
				addClass(bLi[this.index],'active');
			}
		}
	}
}

// 触屏版tab选项swiper效果(一个页面支持多个)
// <div class="iswiper_box">
// 		<div class="iswiper">
// 			<a href=""></a>
// 			<a href=""></a>
// 		</div>
// </div>
function iswiper(){
	var resizeEvt='orientationchange' in window?'orientationchange':'resize';
	function doSwiper(){
		var iswiper=getClassName(document,'iswiper');
		var iswiper_box=getClassName(document,'iswiper_box');
		for(var i=0;i<iswiper.length;i++){
			goIswiper(iswiper[i],iswiper_box[i]);
		}
		function goIswiper(obj,objBox){
			var oA=obj.getElementsByTagName('a');
			var oWidth=oA[0].offsetWidth*oA.length
			obj.style.width=oWidth+'px';
			var touch,left0,top0,goLeftStart=0,lastLeft=0;
			objBox.addEventListener('touchstart',function(event){
				touch=event.changedTouches[0];
				left0=touch.pageX;
				// top0=touch.pageY;
			},false)
			objBox.addEventListener('touchmove',function(event){
				var touch=event.changedTouches[0];
				goLeftStart=lastLeft+touch.pageX-left0;
				if(goLeftStart>0){
					goLeftStart=0
				}
				if(goLeftStart<-oWidth+oA[0].offsetWidth){
					goLeftStart=-oWidth+oA[0].offsetWidth
				}
				// goTop=touch.pageY-top0;
				obj.style.transform='translateX('+goLeftStart+'px)';
			},false)
			objBox.addEventListener('touchend',function(event){
				lastLeft=goLeftStart;
			},false)
		}
	}
	doSwiper();
	window.addEventListener(resizeEvt,function(){
		doSwiper();
	})
}


// 左拉删除
// <ul class="delh">
// 	<li>
// 		<a href="#" title="" class="">
// 		</a>
// 		<menu>删除</menu>
// 	</li>
// </ul>

function left_del(val){
	var delh=getClassName(document,'delh');
	for(var i=0;i<delh.length;i++){
		delgo(delh[i]);
	}
	function delgo(obj){
		var childTag=obj.children[0].tagName;
		var oLi=obj.getElementsByTagName(childTag);
		var left0;
		for(var j=0;j<oLi.length;j++){
			var delClick=oLi[j].getElementsByTagName('menu')[0];
			var bW=delClick.offsetWidth;
			if(delClick){
				oLi[j].addEventListener('touchstart',function(event){
					this.style.transition='1s';
					var touch=event.changedTouches[0];
					left0=touch.pageX;
				},false)
				oLi[j].addEventListener('touchmove',function(event){
					var touch=event.changedTouches[0];
					nowleft=touch.pageX;
					if(nowleft-left0<-30){
						this.style.transform='translateX('+-bW+'px)';
					}
					if(nowleft-left0>30){
						this.style.transform='translateX('+0+'px)';
					}
				},false)
				delClick.onclick=function(){
					obj.removeChild(this.parentNode);
				}
			}
		}
	}
}