//关键字搜索function searchItems(){	$("#keywordform").submit();}//热门词搜索function searchhotword(word){	$("#keywords").val(word);	$("#keywordform").submit();}//获取商品类型  m 获取类型：1-普通商品，2-商品包，3-零元购，4-10元购，5-预售，6-秒杀商品7-全积分兑换，8-部分积分兑换function searchProductType(m){	var url = basePath + "/product/searchproducttype";	var loadData = {"sctype":m};	$("#productTypeList").loading().load(url,loadData);	if(m==2){		$("#productTypeList").show();	}}//获取购物车商品个数function getshoppingcartcount(){	var url = basePath + "/shoppingcart/cartcount";	$.post(url,null,function(data){		var result = eval("("+data+")");		if(result.code==1){			$("#shoppingcartcount").html(result.cartcount);		}	},'text');	}//获取系统消息个数function getsystemmessagecount(){	var url = basePath + "/user/messagecount";	$.post(url,null,function(data){		var result = eval("("+data+")");		if(result.code==1){			$("#smessagecount").html(result.count);		}	},'text');	}//猜你喜欢商品查询function getenjoyproducts(){	$("#enjoybtn").off("click");	var url = basePath + "/product/enjoysearch";	var loadData = $("#enjoyForm").serializeArray();	$("#enjoyList").loading().load(url,loadData);}// nav切换function togglenav(obj,curclass,wrapid){    $(obj).bind('click',function(){        $(obj).removeClass(curclass);        $(this).addClass(curclass);        $('#'+wrapid).children('div').hide();        $('#'+wrapid).children('div:eq('+$(this).index()+')').stop().fadeIn();    });}/*图片自适应*/function relazieimg(oImg){    for (var i = 0; i < oImg.length; i++) {        var width = parseInt(getstyle(oImg[i], 'width'));        var height = parseInt(getstyle(oImg[i], 'height'));        if (width > height) {            oImg[i].style.height = '100%';        } else {            oImg[i].style.width = '100%';        }    }    function getstyle(obj, sty) {        return obj.currentStyle ? obj.currentStyle[sty] : getComputedStyle(obj)[sty];    }}// +1function add(obj){    $(obj).bind('click',function(){        var additem=$(this).siblings('input');        var num = additem.val();        num++;        additem.val(num);    });}// -1function redu(obj){    $(obj).bind('click',function(){        var additem=$(this).siblings('input');        var num = additem.val();        num > 1 ? num--: num;        additem.val(num);    });}//适应高度$.fn.divfixed = function(options) {    var opts = $.extend({},options);    return this.each(function() {        var $this = $(this);        var thisTop=$this.offset().top;        var winTop=$(window).scrollTop();        var aLi=$('li',$this);        $(window).scroll(function()        {            winTop=$(window).scrollTop();            if(winTop > thisTop)            {                $this.css({"position":"fixed","top":opts.top,"margin-left": "-347px"});            }            else            {                $this.css({"position":"static","top":opts.top,"margin-left": "0"});            }        });    });};function hreftotop(obj, id) {    var height = $("#" + id).offset().top - 50;    obj.addClass('on').siblings().removeClass('on');    $('html,body').animate({scrollTop: height + 'px'}, 1000);}function menutoggle(id1, classname1, classname2) {//一级菜单id,二级菜单容器class名,三级菜单class名    var parent1 = $('#' + id1);    parent1.hover(function () {        $(this).find('.' + classname1).stop().slideToggle();    });    parent1.find('.' + classname1 + ' > div').hover(function () {        $(this).find('.' + classname2).stop().fadeToggle();    });}function indexmenutoggle(id1, classname1, classname2) {    var parent1 = $('#' + id1);    parent1.find('.' + classname1 + ' > div').hover(function () {        $(this).find('.' + classname2).stop().fadeToggle();    });}//nav切换1function togglenav1(obj, curclass, wrapobj) {    $(obj).bind('click', function () {        $(obj).removeClass(curclass);        $(this).addClass(curclass);        $(wrapobj).children('div').hide();        $(wrapobj).children('div:eq(' + $(this).index() + ')').stop().fadeIn();    });}