<#import "/common/app.ftl" as app>
<#if menuList??>
<#list menuList as menu>
	<@shiro.hasPermission name="${menu.modelName!''}_${menu.actionName!''}">
    <h3 class="f14">
        <span class="J_switchs cu on" title="展开或关闭"></span>${menu.name!''}
    </h3>
    <#if menu.subList??>
    <ul>
        <#list menu.subList as menu>
        <@shiro.hasPermission name="${menu.modelName!''}_${menu.actionName!''}">
        <li class="sub_menu"><a data-id="${menu.id!''}" data-uri="${app.basePath}${menu.url!''}" href="javascript:;" title="${menu.name!''}">${menu.name!''}</a></li>
        </@shiro.hasPermission>
        </#list>
    </ul>
    </#if>
    </@shiro.hasPermission>
</#list>
<script>
$(function(){
    $(".J_switchs").on('click', function(i){
        var ul = $(this).parent().next();
        if(ul.is(':visible')){
            ul.hide();
            $(this).removeClass('on');
        }else{
            ul.show();
            $(this).addClass('on');
        }
    });
    //左侧菜单点击
    $('.J_lmenu a').on('click', function(){
        var data_name=$(this).html(),
            data_uri = $(this).attr('data-uri'),
            data_id = $(this).attr('data-id'),
            title = $(this).attr('title');
            //不存在新建frame和tab
        $("#J_rframe").load(data_uri,null,function(response,status,xhr){
            if(status!="success"){
                $("#J_rframe").html(response);
            }         
        });
        $("#J_banner").html(title);
        $(this).parent().addClass("on fb blue").siblings().removeClass("on fb blue");
        $(this).parent().parent().siblings().find('.sub_menu').removeClass("on fb blue");       
    });
    
});
</script>
</#if>