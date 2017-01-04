//common js
$(function(){
	// Rich.html_height();//页面高度计算
});

var Rich = {};
//math for page width
Rich.pageSize = function ()
{

	setRootSize();
	//$(document).ready(function(){
		//setRootSize();
	//});
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

Rich.pageSize();//页面计算 rem

function tree(){
	$('.inactive').click(function(){
		if($(this).siblings('ul').css('display')=='none'){
			$(this).siblings('ul').slideDown().children('li');
			if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
				$(this).parents('li').siblings('li').children('ul').slideUp();
			}
		}else{
			//控制自身菜单下子菜单隐藏
			$(this).siblings('ul').slideUp();
			//控制自身菜单下子菜单隐藏
			$(this).siblings('ul').children('li').children('ul').slideUp();
		}
	})
}





