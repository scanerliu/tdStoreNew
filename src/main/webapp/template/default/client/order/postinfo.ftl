<#import "/common/app.ftl" as app>
<ul>
	<#if presp?? && presp.data??>
	<#list presp.data as item>
    <li <#if item_index ==0>class="top"</#if>><label>${item.ftime!''}</label>${item.context!''}</li>
    </#list>
    <#else>
    <li class="top">暂无物流信息。</li>
    </#if>
</ul>
<a href="javascript:void(0);" id="wuliumore" class="amore" title="">展开</a>
<script>
    $(function () {
        $('#wuliumore').click(function () {
        	var i = $(this).hasClass("on");
            if(i==false){
                $("#postinfo").removeClass('hei30')
                $(this).addClass('on').html('收起');
            }else{
                $("#postinfo").addClass('hei30');
                $(this).removeClass('on').html('展开');
            }
        });
    });
</script>