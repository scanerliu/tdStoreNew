<#import "/common/app.ftl" as app>
<#if commentList??>
<#list commentList as comm>
<dl>
    <dt>
        <span class="fl"><#if comm.commentUser??>${comm.commentUser.unick!''}</#if></span>
        <!--<i class="fl"><img src="images/vip.png" alt=""></i>-->
    </dt>
    <dd>
        <p class="p1">${comm.content!''}</p>
        <p class="p2">
        	<#if comm.specialList??>
          	<#list comm.specialList as special>
          	<label class="fl">${special.sname!''}：<span>${special.soption!''}</span></label>
            </#list>
            </#if>
            <label class="fr"><#if comm.createTime??>${comm.createTime?string('HH:mm:ss')}</#if></label>
            <label class="fr"><#if comm.createTime??>${comm.createTime?string('yyyy-MM-dd')}</#if></label>
        </p>
    </dd>
</dl>
</#list>
</#if>
<script type="text/javascript">
  $(function(){
   	 var pageno = ${sc.pageNo+1};
   	 var fliterType = ${sc.fliter};
   	 $("#pageNo").val(pageno);
   	 $("#fliterType").val(fliterType);
   	<#if sc.hasNextPage?? && sc.hasNextPage==true>
   		$(window).scroll(scrollCommentHandler);
   	</#if>
  })
</script>