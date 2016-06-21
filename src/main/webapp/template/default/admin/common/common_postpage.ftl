<script>
function gotopage${pageId!''}(num){
    $("#sc_page${pageId!''}").val(num);
    try 
       {  
         if(fnGotoPage${pageId!''}&&typeof(fnGotoPage${pageId!''})=="function")  
         {
            fnGotoPage${pageId!''}(num);
         }
       }catch(e)
      {
        alert("not fnGotoPage${pageId!''} function"); 
      }  
}
</script>
<div id="pages">
        显示 <input name="pageSize" type="text" value="${sc.pageSize}" onblur="gotopage${pageId!''}(${sc.pageNo})" class="pagenum">条/页
    ${sc.totalCount!"0"} 条记录 <#if sc.totalPageCount==0>0<#else>${sc.pageNo}</#if>/${sc.totalPageCount} 页
    <#if sc??>
            <#assign continueEnter=false>
            <#if sc.pageNo == 1>
            <#else>
                 <a href="javascript:;" onclick="gotopage${pageId!''}(${sc.pageNo-1})">上一页</a>
            </#if>
            
            <#if sc??>
            <#assign continueEnter=false>
            <#if sc.totalPageCount gt 0>
                <#list 1..sc.totalPageCount as page>
                    <#if page <= 3 || (sc.totalPageCount-page) < 3 || (sc.pageNo-page)?abs<3 >
                        <#if page == sc.pageNo>
                            <span class="current">${page}</span>
                        <#else>
                            <a href="javascript:;" onclick="gotopage${pageId!''}(${page})">${page}</a>
                        </#if>
                        <#assign continueEnter=false>
                    <#else>
                        <#if !continueEnter>
                            <b class="pn-break">&hellip;</b>
                            <#assign continueEnter=true>
                        </#if>
                    </#if>
                </#list>
            </#if>
        </#if>
        <#if sc.pageNo == sc.totalPageCount || sc.totalPageCount==0>
        <#else>
            <a href="javascript:;" onclick="gotopage${pageId!''}(${sc.pageNo+1})">下一页 &gt;</a>
        </#if>
    </#if>
    <input type="hidden" name="pageNo" id="sc_page${pageId!''}" value="${sc.pageNo}"/>
</div>