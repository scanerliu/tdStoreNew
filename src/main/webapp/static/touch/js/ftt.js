/** * Created by Administrator on 2016/6/7. */function drge(obj){    var oBox = $(obj);    $.each(oBox,function(i){        drgeGo(oBox.eq(i));    });    function drgeGo(obj)    {        var dele = obj.find('.dele');        var onOff = true;        var disX = 0;        myX = 0;        firstX = 0;        lastX = 0;        obj.on('touchstart',function(ev){            obj.css({WebkitTransition:'0.6s'});            firstX = ev.originalEvent.changedTouches[0].clientX;            disX = ev.originalEvent.changedTouches[0].clientX - obj.position().left;            $(document).on('touchmove',function(ev){                myX = ev.originalEvent.changedTouches[0].clientX - disX;                if(myX>0){                    myX=0;                }else if(myX<-dele.width()){                    myX=-dele.width();                };                obj.css({left:myX});            });            $(document).on('touchend',function(ev){                lastX = ev.originalEvent.changedTouches[0].clientX;                $(document).unbind('touchend');                $(document).unbind('touchmove');                if((firstX - lastX)/dele.width()>0.5){                    myX=-dele.width();                }else if((firstX - lastX)/dele.width()<0.5){                    myX=0;                };                obj.css({WebkitTransition:'0.6s',left:myX});            });        });    };};