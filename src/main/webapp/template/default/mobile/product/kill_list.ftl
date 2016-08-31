<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>秒杀列表</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/swipe.css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/swipe.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/product/search.js"></script>
</head>
<style>
#loading {
    display: block;
}
</style>
<script>
    window.onload=function(){
       $('#two2 a ').click(function(){
    	   $('#two2 a').removeClass("active");
    	   $(this).addClass("active");
    	   $('#two2_match').children('div').stop().hide();
    	   $('#two2_match').children('div:eq('+$(this).index()+')').stop().fadeIn();
    	   
       });
    }
    
 $(document).ready(function(){   
	var url = '${app.basePath}/mobile/product/search/more';
	var cc=document.getElementById('two2');
	var aA=cc.getElementsByTagName('a');
	if(aA[0].className=='active'){
		$('#kill_list').refresh(url+"?kind=6","#kill_list",0);
		$('#one_div').css({"display":"block"});
	}
	if(aA[1].className=='active'){
		$('#persell_list').refresh(url+"?kind=5","#persell_list",0);
	}
})
  
</script>

<body class="body_bg">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="" class="hleft hback"></a>
    <span>秒杀预售</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <div class="two2" id="two2">
        <a href="javascript:;" title="秒杀" class="active">秒杀</a>
        <a href="javascript:;" title="预售" class="">预售</a>
    </div>
    <!-- 热销推荐 -->
    <div class="two2_match" id="two2_match">
        <div class="hot" id="one_div">
            
            	<#if killList?? && killList?size gt 0>
            	<section class="sec2" id="kill_list">
            	<#list killList as item>
                <a href="${app.basePath}/mobile/product/item${item.id?c}" title="${item.name!''}" class="">
                    <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
                    <p class="p1">${item.name!''}</p>
                    <aside class="overtime" id="timeLeft${item_index}"><span>00</span>天<span>00</span>时<span>00</span>分<span>00</span>秒</aside>
                    <p class="p2">
                        <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
                        <label class="lab2">￥188.00</label>
                    </p>
                </a>
<script>
$(document).ready(function(){
	timer${item_index}();
    setInterval("timer${item_index}()",1000);
});

function checkTime(i)  
{  
    if (i < 10) {  
        i = "0" + i;  
    }  
    return i;  
}

function timer${item_index}()
{
	    var ts = (new Date(${item.endTime?string("yyyy")}, 
                parseInt(${item.endTime?string("MM")}, 10)-1, 
                ${item.endTime?string("dd")}, 
                ${item.endTime?string("HH")}, 
                ${item.endTime?string("mm")}, 
                ${item.endTime?string("ss")})) - (new Date());//计算剩余的毫秒数
  
    var allts = (new Date(${item.endTime?string("yyyy")}, 
                parseInt(${item.endTime?string("MM")}, 10)-1, 
                ${item.endTime?string("dd")}, 
                ${item.endTime?string("HH")}, 
                ${item.endTime?string("mm")}, 
                ${item.endTime?string("ss")}))
               - (new Date(${item.startTime?string("yyyy")}, 
                parseInt(${item.startTime?string("MM")}, 10)-1, 
                ${item.startTime?string("dd")}, 
                ${item.startTime?string("HH")}, 
                ${item.startTime?string("mm")}, 
                ${item.startTime?string("ss")}));//总共的毫秒数 
    
    if (0 == ts)
    {
        window.location.reload();
    }
  
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
     if(ss < 0){
    	ss = 0;
    }
    if(mm < 0){
    	mm = 0;
    }
    if(hh < 0){
    	hh = 0;
    }
    if(dd < 0){
    	dd = 0;
    }
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#timeLeft${item_index}").html("<span>"+dd+"</span>天<span>"+hh+"</span>时<span>"+mm+"</span>分<span>"+ss+"</span>秒");
    
}

</script>
                </#list>
                
                
            </section>
            <#else>
                	<div style=" background:#f2f2f2;padding-bottom:0;display:inline-block;width:100%; text-align:center;padding-top:3rem;">
                		<span style="background:#f2f2f2;">暂无商品</span>
                	</div>
                </#if>
        </div>
        <div class="hot">
            <#if presellList?? && presellList?size gt 0>
            	<section class="sec2" id="persell_list">
            	<#list presellList as item>
                <a href="${app.basePath}/mobile/product/item${item.id?c}" title="${item.name!''}" class="">
                    <img src="${app.basePath}${item.imageUrl!''}" alt="${item.name!''}">
                    <p class="p1">${item.name!''}</p>
                    <aside class="overtime" id="timeLeft${item.id?c}"><span>00</span>天<span>00</span>时<span>00</span>分<span>00</span>秒</aside>
                    <p class="p2">
                        <label class="lab1">¥<#if item.price??>${item.price?string('0.00')}</#if></label>
                        <label class="lab2">￥188.00</label>
                    </p>
                </a>
<script>
$(document).ready(function(){
	timer${item.id?c}()
    setInterval("timer${item.id?c}()",1000);
});

function checkTime(i)  
{  
    if (i < 10) {  
        i = "0" + i;  
    }  
    return i;  
}

function timer${item.id?c}()
{
    	var ts = (new Date(${item.endTime?string("yyyy")}, 
                parseInt(${item.endTime?string("MM")}, 10)-1, 
                ${item.endTime?string("dd")}, 
                ${item.endTime?string("HH")}, 
                ${item.endTime?string("mm")}, 
                ${item.endTime?string("ss")})) - (new Date());//计算剩余的毫秒数
  
    var allts = (new Date(${item.endTime?string("yyyy")}, 
                parseInt(${item.endTime?string("MM")}, 10)-1, 
                ${item.endTime?string("dd")}, 
                ${item.endTime?string("HH")}, 
                ${item.endTime?string("mm")}, 
                ${item.endTime?string("ss")}))
               - (new Date(${item.startTime?string("yyyy")}, 
                parseInt(${item.startTime?string("MM")}, 10)-1, 
                ${item.startTime?string("dd")}, 
                ${item.startTime?string("HH")}, 
                ${item.startTime?string("mm")}, 
                ${item.startTime?string("ss")}));//总共的毫秒数 
    
    if (0 == ts)
    {
        window.location.reload();
    }
  
    var date = new Date();
    var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数
    var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数
    var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数
    var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数
     if(ss < 0){
    	ss = 0;
    }
    if(mm < 0){
    	mm = 0;
    }
    if(hh < 0){
    	hh = 0;
    }
    if(dd < 0){
    	dd = 0;
    }
    dd = checkTime(dd);
    hh = checkTime(hh);
    mm = checkTime(mm);
    ss = checkTime(ss);
    $("#timeLeft${item.id?c}").html("<span>"+dd+"</span>天<span>"+hh+"</span>时<span>"+mm+"</span>分<span>"+ss+"</span>秒");
    
}

</script>
                </#list>
                
            </section>
            <#else>
            <div style=" background:#f2f2f2;padding-bottom:0;display:inline-block;width:100%; text-align:center;padding-top:3rem;">
                <span style="background:#f2f2f2;">暂无商品</span>
            </div>
                
            </#if>
                
        </div>
    </div>
    <!-- 热销推荐-结束 -->
</section>
<!-- Center end -->

</body>
</html>