<#import "/common/app.ftl" as app>
<#if articleTitle?? && articleContent??>
<span class="title">${articleTitle.title!''}</span>
<article>${articleContent.content!''}</article>
<#else>
<span class="title">未找到相关信息。</span>
</#if>