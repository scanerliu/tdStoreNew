<#import "/common/app.ftl" as app> 
<div id="1" class="description">
    <div class="info">
        ${productdesc.description!''}
    </div>
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