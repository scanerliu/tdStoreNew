<#import "/common/app.ftl" as app> 
<div id="1" class="description">
    <div class="info">
        ${productdesc.description!''}
    </div>
</div>
<div id="2" class="evalute">
	<form id="searchcommentform">
		<input type="hidden" id="sc_fliter" name="fliter" value=""/>
	</form>
    <div class="enav" id="commentTab">
        <a href="javascript:;" class="on" tid=""><label>全部（${productStat.reviewCount!'0'}）</label></a>
        <a href="javascript:;" tid="1"><label>好评（${productStat.positiveRate!'0'}）</label></a>
        <a href="javascript:;" tid="2"><label>中评（${productStat.neutralRate!'0'}）</label></a>
        <a href="javascript:;" tid="3"><label class="noborder">差评（${productStat.negativeRate!'0'}）</label></a>
    </div>
    <form id="commentlistform">
    <div class="evalute-list" id="commentList">
        <#if productStat.reviewCount==0>
        <span class="noinfo">
            暂无评论
        </span>
        </#if>
    </div>
    </form>
</div>
<div id="3" class="iteminfo">
    <div class="title">
        <label>包装与配送</label>
    </div>
    <div class="itemcontent">
       ${delivedesc.description!''}
    </div>
</div>
<div id="4" class="iteminfo">
    <div class="title">
        <label>售后服务</label>
    </div>
    <div class="itemcontent">
    	${servicedesc.description!''}
    </div>
</div>

<script>
$(function(){
	<#if productStat.reviewCount gt 0>
        searchComments(true);
        $("#commentTab").on("click","a", function(){
	    	$(this).addClass("on").siblings().removeClass("on");
	    	$("#sc_fliter").val($(this).attr("tid"));
	    	searchComments(true);
	    });
    </#if>
});
</script>
