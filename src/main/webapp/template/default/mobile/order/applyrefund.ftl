<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="copyright" content="" />
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <title>退款申请</title>
    <#include "/common/common.ftl" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/common.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/main.css"/>
    <link rel="stylesheet" href="${app.basePath}/static/default/mobile/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/huploadify/Huploadify.css">
    <script src="${app.basePath}/static/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/huploadify/jquery.Huploadify.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/mobile/core.js"></script>
    <script src="${app.basePath}/static/js/mobile/order/orderlist.js" type="text/javascript"></script>
</head>
<body class="body_bg">
  <!-- header_top -->
  <div class="top_heater">
    <a class="hleft hback" href="javascript:;" onclick="window.history.go(-1)" title=""></a>
    <span>退款申请</span>
  </div>
  <!-- header_top end -->
 <!-- Center Start -->
<form id="refundForm" method="post" action="${app.basePath}/mobile/order/refund">
<input type="hidden" name="orderId" value="${order.orderId!'0'}"/>
<section class="container zizhirenzheng">
    <article>
        <span>申请服务</span>
        <span>退货/退款</span>
    </article>
    <article>
        <span>退款方式</span>
        <select name="cargoStatus">
            <option value="1">退款退货</option>
            <option value="2">退款不退货</option>
        </select>
    </article>
    <article>
        <span>退款原因</span>
        <select name="returnCause">
            <option value="1">七天无理由退换货</option>
            <option value="2">退运费</option>
            <option value="3">收到商品破损</option>
            <option value="4">商品错发/漏发</option>
            <option value="5">商品需要维修</option>
            <option value="6">收到商品与描述不符</option>
            <option value="7">商品质量问题</option>
            <option value="8">假冒品牌</option>
            <option value="9">未收到货</option>
        </select>
    </article>
    <article>
        <span>退款金额</span>
        <input type="text" placeholder="0" value="0" id="returnAmount" name="returnAmount" onkeyup="formatInputPrice(this,0,${order.totalAmount-order.refundAmount})">
    </article>
    <article>
        <span>退货说明</span>
        <textarea placeholder="请在这里输入文字，少于100文字" name="returnReason"></textarea>
    </article>
    <article>
        <span>上传图片凭证</span>
        <div class="imgupload">
            <div class="input-file" id="file_upload">
                
            </div>
            <div id="imageslist"></div>
            <input type="hidden" id="voucherImages" name="voucherImages" value=""/>
        </div>
    </article>
</section>
<!-- Center End -->

<!-- Footer Start -->
<div style="width:100%;height:0.2rem"></div>
<footer>
   <div class="addnew" style="background:#f23030">
       <a href="javascript:;" title="">提交申请</a>
   </div>
    <span class="footclear" onclick="applyRefund()"></span>
</footer>
<!-- Footer End -->
</form>
<script>
	$(function(){
		$('#file_upload').Huploadify({
				'multi': false, // 限制单图上传
				'auto': true,
				'formData' : {'type' : 'refund'},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '上传图片',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					var html = '<img src="'+basePath+result.savedFile+'"/>'
					$("#imageslist").append(html);
					var images = $("#voucherImages").val();
					if(images.length>0){
						images = images + "," + result.savedFile;
						$("#voucherImages").val(images);
					}else{
						$("#voucherImages").val(result.savedFile);
					}
					//$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	//$.messager.alert('消息提醒','上传失败。');
		        	alert("图片上传失败。");
		        }
		});
	});
</script>
</body>
</html>