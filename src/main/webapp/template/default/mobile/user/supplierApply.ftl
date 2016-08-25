<#import "/common/app.ftl" as app> <#include "/common/common.ftl" /><!DOCTYPE html><html lang="en"><head>    <meta charset="UTF-8">    <meta http-equiv="Content-Language" content="zh-CN">    <meta name="keywords" content="">    <meta name="description" content="">    <meta name="copyright" content="" />    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">    <meta content="yes" name="apple-mobile-web-app-capable">    <meta content="black" name="apple-mobile-web-app-status-bar-style">    <meta content="telephone=no" name="format-detection">    <title>创客联盟</title>    <!-- css -->    <link href="${app.basePath}/static/touch/css/common.css" rel="stylesheet" type="text/css" />    <link href="${app.basePath}/static/touch/css/main.css" rel="stylesheet" type="text/css" />    <link href="${app.basePath}/static/touch/css/f-personalcenter.css" rel="stylesheet" type="text/css" />    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/huploadify/Huploadify.css">    <!-- js -->    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>    <script src="${app.basePath}/static/js/huploadify/jquery.Huploadify.js" type="text/javascript"></script>    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script></head><body class="body_gray"><!-- header_top --><div class="top_heater">    <a href="${app.basePath}/mobile/user/center" title="返回个人中心" class="hleft hback"></a>    <span>资质认证</span></div><!-- header_top end --><!-- Center Start --><form id="userSupplierForm" action="${app.basePath}/mobile/user/saveUserSupplierApply"><input type="hidden" name="id" value="<#if userSupplier??>${userSupplier.id?c }</#if>"><section class="container zizhirenzheng">    <article>        <span>申请服务</span>        <select name="supplierType" style="-webkit-appearance: none;">            <option value="1" <#if userSupplier?? && userSupplier.supplierType?c=="1">selected="selected"</#if>>个人</option>            <option value="2" <#if userSupplier?? && userSupplier.supplierType?c=="2">selected="selected"</#if>>公司</option>        </select>    </article>	<#if userSupplier??>		<article>	        <span>申请状态：&nbsp;&nbsp;${userSupplier.statusStr!''}</span>	    </article>    </#if>    <#if userSupplier?? && userSupplier.status?c == "3">		<article>	        <span>理由：&nbsp;&nbsp;${userSupplier.reply!''}</span>	    </article>    </#if>    <article>        <span>资质认证说明</span>        <textarea placeholder="请在这里输入文字，少于200文字" name="note"><#if userSupplier??>${userSupplier.note!''}</#if></textarea>    </article>    <article>        <span>上传图片</span>        <div class="imgupload">        	<div class="imgupload" id="imgShowDiv">        	            <#if  !userSupplier?? || (userSupplier?? && userSupplier.status?c != "2")><div style="float:left;" id="file_upload"></div></#if>        		<#if userSupplier??>        			<#list imgList as img>        				<a><img src="${app.basePath}${img!''}" onclick="$(this).remove()" alt="图片丢失"/></a>        			</#list>        		</#if>        	</div>        	<input type="hidden" id="images" name="images" value="<#if userSupplier??>${userSupplier.images}</#if>">        </div>        <span class="tips">小提示：公司需上传“企业营业执照”、“法人身份证”、“企业产品9001认证” </span>    </article>    <#if  !userSupplier?? || (userSupplier?? && userSupplier.status?c != "2")><a href="javascript:;" class="create-code" title="" onclick="submitUserSupplier()">完成</a></#if></section></form><!-- Center End --><!--footer Start--><footer>    <span class="footclear"></span></footer><!--footer End--></body><script>	$(document).ready(function(){		//初始化图片上传		$('#file_upload').Huploadify({				'multi': true, // 限制单图上传				'auto': true,				'formData' : {'type' : 'supplier'},				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径				'fileObjName' : 'file',				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet				'buttonText' : '添加图片',				//'buttonImage': '${app.basePath}/static/touch/images/add_72px_1199462_easyicon.net.png',				'onUploadSuccess' : function(file, data, response) {					var result = eval("("+data+")");					$("#imgShowDiv").append("<a onclick='$(this).remove()'><img style='width:100%;height:100%;' src='"+ basePath+result.savedFile +"' alt='图片不存在'/></a>");		        },		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);		        	alert("图片上传失败。");		        }		});	});		function setImgInput(){		var imgs = $("#imgShowDiv").find("img");		var imgsStr = "";		for(var i = 0; i < imgs.length; i ++){			imgsStr = imgsStr + $(imgs[i]).attr("src");			if(i < imgs.length - 1){				imgsStr = imgsStr + ":";			}		}		var f = $("#images").val(imgsStr);		console.log(f);	}		function submitUserSupplier(){		setImgInput();		var url = basePath + "/mobile/user/saveUserSupplierApply";		var loadData = $("#userSupplierForm").serialize();		$.post(url, loadData, function(data){			var jsonData = eval('('+data+')');			alert(jsonData.msg);			if(jsonData.code == "1"){				window.location.href="/tdStore/mobile/user/center";			}		}, "text");	}</script></html>