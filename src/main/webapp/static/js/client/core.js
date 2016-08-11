$.fn.extend({
	loading:function(){
		return this.each(function(){
			$(this).html('数据加载中...');			
		});
	}
});


function formatInputInteger(obj,min,max){
	var num = obj.value.replace(/\D/g,'');
	if(num<min){
		obj.value=min;
	}else if(num>max){
		obj.value=max;
	}else{
		obj.value = num;
	}
}

function formatInputPrice(obj,min,max){
	var reg = $(obj).val().match(/\d+\.?\d{0,2}/);
    var txt = '';
    if (reg != null) {
        txt = reg[0];
    }
    if(txt<min){
    	txt = min;
    }else if(txt>max){
    	txt = max;
    }
    $(obj).val(txt);
}

function formatInputPriceDefault(obj){
	var min = 0;
	var max = 999999;
	formatInputPrice(obj,min,max);
}

//写cookies
function setCookie(c_name, value, expiredays){
	var exdate=new Date();
	exdate.setDate(exdate.getDate() + expiredays);
	document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "; path=/" : ";expires="+exdate.toGMTString()+"; path=/");
}
 
//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return (arr[2]);
    else
        return "";
}

//删除cookies
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}