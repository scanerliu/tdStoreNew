<#import "/common/app.ftl" as app>
<#if presellList??>
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
</#if>