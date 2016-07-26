<#import "/common/app.ftl" as app> 
<#include "/common/common.ftl" />
<script type="text/javascript">
    function warning(text){
        $('.warning').html(text)
        var le = ($(window).width()-$('.warning').outerWidth(true))/2;
        var he = ($(window).height()-$('.warning').height())/2;
        
        $('.warning').css({left:le,top:he});
        $('.warning').fadeIn();
        warning_out();
    };
    
    function warning_out(){
        var timer = setTimeout(function(){
            $('.warning').fadeOut(1000);
        },1000);
    };
</script>
<style>
.warning{                                     
    padding: 6px 14px;
    background: black;
    color: white;
    position: fixed;
    left: 0px;
    top: 0px;
    z-index: 99999;
    border-radius: 6px;
    background: url(${app.basePath}/static/default/images/color_warning.png);
    font-size: 1.2em;
    display:none;
}
</style>
<div class="warning"></div>