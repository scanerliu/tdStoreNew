<div class="w100">
    <div class="under_div">
        <p class="p1" id="sitebanner">
            <a href="#" title="" class="a1">关于我们</a>
            <a href="#" title="" class="">联系我们</a>
            <a href="#" title="" class="">人才招聘</a>
            <a href="#" title="" class="">手机创客</a>
            <a href="#" title="" class="">购物指南</a>
            <a href="#" title="" class="">配送方式</a>
            <a href="#" title="" class="">支付方式</a>
            <a href="#" title="" class="">售后服务</a>
        </p>
        <p class="p2">公司地址：<#if system?? && system.webcompanyaddress??>${system.webcompanyaddress}</#if>  公司邮箱：<#if system?? && system.webcompanyemail??>${system.webcompanyemail}</#if></p>
        <p class="p3"><#if system?? && system.webcopyright??>${system.webcopyright}</#if></p>
    </div>
</div>
<script>
$(function(){
loadsitebanner();
});
</script>
