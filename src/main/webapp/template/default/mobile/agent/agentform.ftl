<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>体验店申请</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/index.css">
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/index.js"></script>
    <script src="${app.basePath}/static/js/mobile/agent/agent.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/huploadify/Huploadify.css">
<script src="${app.basePath}/static/js/huploadify/jquery.Huploadify.js" type="text/javascript"></script>
</head>

<script>
    window.onload=function(){
       // file_btn();
         // warn_box();
    }
    
    $(function(){
	    searcAgentAddr();
	});
</script>

<body class="bg_2">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="" class="hleft hback"></a>
    <span>体验店申请</span>
</div>
<!-- header_top end -->

<!-- Center Start -->
<section class="container">
    <form class="store_askfor" id="form" >
        <section class="sec1">填写地址</section>
        <section class="sec2">
        <!-- 三级联动2 -->
        <div class="tarea" id="tarea">
            
        </div>
        <!-- 三级联动2-结束 -->
        </section>
        <section class="sec4"><input type="text" name="address" placeholder="请填写详细地址"></section>
        <section class="sec5"><input type="text" name="telphone" placeholder="请填写手机号码"></section>
        <section class="sec6">体验中心的货架，装修设计平台可以提供支持，部分厂家。客服电话1389636888</section>
        <div class="detail6">
            <section class="title">图片展示</section>
            <section class="upload">
                <aside class="as1" id="showing">
                    <div  id="file_btna"></div>
                </aside>
                <aside class="as2">小提示：<span>亲，体验店申请需要填写门店的详细信息，门店的图片，社区的图片等</span></aside>
            </section>
        </div>
        <section class="sec7">
            <label for="" class="fl">商品分类</label>
            <select name="" id="">
                <option value="">鞋子</option>
                <option value="">鞋子</option>
            </select>
        </section>
        <input type="button" onclick="addAgent();" value="立即申请">
    </form>
<script>
$(function(){
		var num = 0;
		$('#file_btna').Huploadify({
				'multi': true, //
				'auto': true,
				'formData' : {'type' : 'agent'},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '上传',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#showing").append("<a href='javascript:;' id='img"+num+"' class='aimg'><img  src='"+basePath+result.savedFile+"' /><i onclick='removeImg("+num+")'></i></a>");
					$("#showing").append("<input id='input"+num+"' type='hidden' name='storeImages' value='"+basePath+result.savedFile+"'>");
					num++;
					console.debug(num);
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	//$.messager.alert('消息提醒','上传失败。');
		        	alert("头像上传失败。");
		        }
		});
	});
	
function removeImg(id){
	$("#img"+id).remove();
	$("#input"+id).remove();
}
</script>

<div class="pay_warn" id="pay_warn" style="display:none">
        <section class="warn_box" id="warn_box">
            <p class="p1">提示信息</p>
            <p class="p2">申请信息以提交到县级单类代理商。请等待审核结果。</p>
           <a href="${app.basePath}/mobile" title="返回首页"></a>
        </section>
</div>

</section>
<!-- Center End -->
</body>
</html>