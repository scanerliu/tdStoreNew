$.fn.extend({
	loading:function(){
		return this.each(function(){
			$(this).html('数据加载中...');			
		});
	}
});