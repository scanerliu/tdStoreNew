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