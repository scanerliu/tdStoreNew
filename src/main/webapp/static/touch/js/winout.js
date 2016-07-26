(function($, window, document,undefined) {
	//构造
	var Winout = function (element,option){
	    this.$e = element,
	    this.defaults = {
	    	sure:"sure",               //类型 sure:(闪现) yes(确定) no(确定，取消)
	    	outerbox:".outerbox",		//外框 选择器
	    	innerbox:".innerbox",		//内框 选择器
	    	outerwidth:"100%",			//外框宽度
	    	outerheight:"100%",			//外框高度
	    	innerwidth:"600px",    		//内框宽度
	    	innerheight:"100px",    	//内框高度
	    	lineheight:"100px",			//内框文字行高
	    	innertext:"这是rich's弹窗",  //内框文字内容
	    	yesclick:"yesClick();" ,     //确定按钮触发函数
	    	font:"12px"
	    },
	    this.options = $.extend({},this.defaults,option)
	};
	//方法
	Winout.prototype ={
	    init:function(){
    			var outer = this.options.outerbox.substring(1,this.options.outerbox.length);
				var inner = this.options.innerbox.substring(1,this.options.innerbox.length);
			    $('<div></div>',{
			    	'class':outer
			    }).appendTo(this.$e);
			    $(this.options.outerbox).css({
			    	width: this.options.outerwidth,
					height: this.options.outerheight,
					background:'rgba(0,0,0,0.6)',
					zIndex:'9999',
					position:'absolute',
					left:'0px',
					top:'0px',
					display:'none'
			    });
			    $('<div></div>',{
			    	'class':inner,
			    	text:this.options.innertext
			    }).appendTo(this.options.outerbox);
	    		$(this.options.innerbox).css({
			    	width: this.options.innerwidth,
					height: this.options.innerheight,
					background:'white',
					borderRadius:'8px',
					margin:'auto',
					zIndex:'9999',
					fontSize:this.options.font,
					color:'#2d1709',
					lineHeight:this.options.lineheight,
					textAlign: 'center',
					position: 'relative'
			    });
			    console.log($(this.options.outerbox))
			    switch(this.options.sure){
			    	case "yes":
			    		$('<span></span>',{
					    	'class':'yes',
					    	text:'确定',
					    	onclick:this.options.yesclick
					    }).appendTo(this.options.innerbox);
					    $('<span></span>',{
					    	'class':'no',
					    	text:'取消',
					    	onclick:'$(this).parent().parent().hide()'
					    }).appendTo(this.options.innerbox);
				    	$(this.options.outerbox).find('span').css({
				    		position:'absolute',
				    		bottom:'20px',
							width:'40%',
				    	});
				    	$(this.options.outerbox).find('span:first').css({
				    		left:'10%',
							width:'40%'
				    	});
				    	$(this.options.outerbox).find('span:last').css({
				    		right:'10%',
							width:'40%'
				    	});
			    	break;
			    	case "no":
			    	$('<span></span>',{
				    	'class':'yes',
				    	text:'确定',
				    	onclick:onclick
				    }).appendTo(innerbox);
			    	$(outerbox).find('span').css({
			    		position:'absolute',
			    		bottom:'20px',
						width:'80%',
						left:'10%'
			    	});
			    	break;
			    };
			    var wi = ($(this.options.outerbox).width() - $(this.options.innerbox).width())/2;
			    var he = ($(this.options.outerbox).height() - $(this.options.innerbox).height())/2;
			    $(this.options.innerbox).css({
			    	marginTop:he
			    });
	    }
	};
	$.fn.winout = function(options) {
        var winout = new Winout(this, options);
        return winout.init();
    }
})(jQuery, window, document);