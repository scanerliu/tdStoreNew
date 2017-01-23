//common js
$(function(){
	Rich.pageSize();//页面计算 rem
	// Rich.html_height();//页面高度计算
});

var Rich = {};
//math for page width
Rich.pageSize = function ()
{
	$(document).ready(function(){
		setRootSize();
	});
	function setRootSize() {
		var deviceWidth = document.documentElement.clientWidth; 
		if(deviceWidth>640){deviceWidth = 640;}  
		document.documentElement.style.fontSize = deviceWidth / 6.4 + 'px';
	}
	setRootSize();
	window.addEventListener('resize', function () {
	    setRootSize();
	    // Rich.html_height();//页面高度计算
	}, false);
};
//math for html height
// Rich.html_height = function (){
// 	var oHtml = document.getElementsByTagName('html')[0];
// 	var win_hi =document.documentElement.clientHeight;
// 	var doc_hi =document.documentElement.offsetHeight;
// //	console.log(doc_hi);
// //	console.log(win_hi);
// 	if(doc_hi>=win_hi){
// 		oHtml.style.height = doc_hi + 'px';
// 	}else{
// 		oHtml.style.height = win_hi + 'px';
// 	};
// }
//创客联盟 密码校验
function passWord(){
	var btn = $(".gopay .a-pay");
	var wrap = $(".password_wrap");
	var input = $(".password_wrap .password input");
	var quit = $(".password_wrap .password a.quit");
	btn.click(function(){
		$("body").css({overflow:"hidden"});
		wrap.css({"display":"block"})
		input.focus();
	});
	quit.click(function(){
		$("body").css({overflow:"initial"});
		wrap.css({"display":"none"})
	})
}
//商品详情页 轮播放大
function car(){
	var wrap = $(".swiper-container");
	var img = $(".container .swiper-container .swiper-wrapper .swiper-slide img");
	var mask = $(".mask");
	img.click(function(){
		if(mask.css("display")=="none"){
			//console.log(0)
			$("body").css({"overflow":"hidden","background":"rgba(0,0,0,.8)"});
			wrap.css({"height":"6rem","position":"fixed","top":"2rem","z-index":"105"});
			mask.show();
		}
		else{
			//console.log(1)
			$("body").css({"overflow":"visible","background":"#f2f2f2"});
			wrap.css({"height":"100%","position":"relative","top":"0","z-index":"105"});
			mask.hide();
		}
	})
	mask.click(function(){
		$("body").css({"overflow":"visible","background":"#f2f2f2"});
		wrap.css({"height":"100%","position":"relative","top":"0","z-index":"105"});
		mask.hide();
	})
}

//创客联盟 密码校验
function passWord(){
	var btn = $(".gopay .a-pay");
	var wrap = $(".password_wrap");
	var input = $(".password_wrap .password input");
	var quit = $(".password_wrap .password a.quit");
/*	btn.click(function(){
		$("body").css({overflow:"hidden"});
		wrap.css({"display":"block"})
		input.focus();
	});*/
	quit.click(function(){
		$("body").css({overflow:"initial"});
		wrap.css({"display":"none"})
	})
}






