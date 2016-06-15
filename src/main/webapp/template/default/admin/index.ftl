<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en" class="off">
<head>
    <meta charset="UTF-8">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content=""/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>首页</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/admin/style/style.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/js/easyui/easyui.css"/>
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/admin/core.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/admin/index.js" type="text/javascript"></script>
</head>
<body>
<div id="header">
    <div class="logo"><a href="${app.basePath}/admin/index" title="首页"></a></div>
    <div class="fl">
        <div class="cut_line admin_info tr">
                            您好！<b><@shiro.principal/> </b>
            &nbsp;&nbsp;&nbsp;<a href="${app.basePath}/admin/logout">登出</a>
        </div>
    </div>
    <ul class="nav white" id="J_tmenu">
        <#if mainList??>
        <#list mainList as menu>
        	<@shiro.hasPermission name="${menu.modelName!''}_${menu.actionName!''}">
            <li class="top_menu"><a href="javascript:;" data-id="${menu.id!''}" hidefocus="true" style="outline:none;">${menu.name!''}</a></li>
            </@shiro.hasPermission>
        </#list>
        </#if>
    </ul>
</div>
<div id="content">
    <div class="left_menu fl">
        <div id="J_lmenu" class="J_lmenu">
            
        </div>
        <a id="J_lmoc" class="open" title="展开或关闭" hidefocus="true" style="outline-style: none; outline-width: medium; height: 522px;" href="javascript:;"></a>
    </div>
    <div class="right_main">
        <div class="crumbs">
            <div id="J_mtab" class="mtab">
                <div class="mtab_p">
                    <div class="mtab_b">
                        <ul id="J_mtab_h" class="mtab_h"><li class="current" data-id="0"><span><a id="J_banner">后台首页</a></span></li></ul>
                    </div>
                </div>
            </div>
        </div>
        <div id="J_rframe" class="rframe_b">
        </div>
    </div>
</div>
<script>
$(function(){
    var set_h = function(){
        var heights = document.documentElement.clientHeight-109;
        $("#J_rframe").height(heights);
        var openClose = $("#J_rframe").height()+9;
        $('#center_frame').height(openClose+9);
        $("#J_lmoc").height(openClose+20);
        $('body').css('overflow','hidden');
    }
    $(window).resize(function(){
        set_h();
    });
    set_h();
    //顶部菜单
    $("#J_tmenu a").click(function(){
        var pid=$(this).attr("data-id");
        $(this).parent().addClass("on").siblings().removeClass("on");
        showleftmenu(pid);
    });
    //左侧开关
    $('#J_lmoc').on('click', function(){
        if($(this).data('clicknum')==1) {
            $('html').removeClass('on');
            $('#J_lmenu').parent().removeClass('left_menu_on');
            $(this).removeClass('close');
            $(this).data('clicknum', 0);
        } else {
            $('#J_lmenu').parent().addClass('left_menu_on');
            $(this).addClass('close');
            $('html').addClass('on');
            $(this).data('clicknum', 1);
        }
        return false;
    });
    
});
</script>
</body>  
</html>