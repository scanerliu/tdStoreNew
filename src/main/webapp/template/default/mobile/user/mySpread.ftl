<#import "/common/app.ftl" as app> <#include "/common/common.ftl" /><!DOCTYPE html><html lang="en"><head>    <meta charset="UTF-8">    <meta http-equiv="Content-Language" content="zh-CN">    <meta name="keywords" content="">    <meta name="description" content="">    <meta name="copyright" content="" />    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">    <meta content="yes" name="apple-mobile-web-app-capable">    <meta content="black" name="apple-mobile-web-app-status-bar-style">    <meta content="telephone=no" name="format-detection">    <title>创客联盟</title>    <!-- css -->    <link href="${app.basePath}/static/touch/css/common.css" rel="stylesheet" type="text/css" />    <link href="${app.basePath}/static/touch/css/main.css" rel="stylesheet" type="text/css" />    <link href="${app.basePath}/static/touch/css/f-personalcenter.css" rel="stylesheet" type="text/css" />    <!-- js -->    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/mobile/user/myspread.js"></script>    <script src="${app.basePath}/static/js/huploadify/jquery.Huploadify.js" type="text/javascript"></script></head><body class="body_gray"><div id="listdiv"><!-- header_top --><div class="top_heater">    <a href="${app.basePath}/mobile/user/center" title="返回个人中心" class="hleft hback"></a>    <span>我的推广</span></div><!-- header_top end --><!-- Center Start --><section class="container my-spread">    <span class="txtspread">您的二维码已已经生成成功，请长按二维码进行保存和分享。</span>    <article>    <#--<article class="code-wrap">        <div class="avatar" style="display:block;overflow:hidden;">            <img id="myspreadavatar" src="<#if currentUser.uavatar?index_of("http")==0><#else>${app.basePath}</#if>${currentUser.uavatar!''}" alt="无头像"/>            <span id="myspreadnick">${currentUser.unick!''}</span>        </div>        <span class="txt1" id="myspreadcontent">邀您来创客，创业就是这么简单，大众创业，万众创新</span>-->        <img class="imgcode" id="imgcode" src="${app.basePath}/mobile/user/mySpread/qrcode" alt="推广二维码"/>    </article></section><article class="spread-platform">	<#--    <span>分享到</span>    	<div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a><a title="分享到QQ好友" href="#" class="bds_sqq" data-cmd="sqq"></a><a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a></div>		<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},"share":{},"image":{"viewList":["weixin","sqq","qzone"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["weixin","sqq","qzone"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>	--></article><!-- Center End --><!--footer Start--><footer>    <!--<a href="javascript:window.location.reload();" class="create-code" title="生成个性二维码">生成个性二维码</a>-->    <a href="javascript:;" class="create-code" title="生成个性二维码" onclick="showQcode()">生成个性二维码</a>    <span class="footclear"></span></footer><!--footer End--></div><div id="formdiv" style="display:none;">	<!-- header_top -->	<div class="top_heater">	    <a href="javascript:;"  onclick="returnQcodeList()" title="返回" class="hleft hback"></a>	    <span>我的推广</span>	</div>	<!-- header_top end -->	<section class="container personalcode">	<ol>        <!--个性化设置-->        <li class="setting">			<form id="spreadForm">			<div class="avatar-change">						        <img src="<#if currentUser.uavatar??><#if currentUser.uavatar?index_of("http")==0><#else>${app.basePath}</#if>${currentUser.uavatar!''}<#else>${app.basePath}/static/default/mobile/images/defaultavtar.png</#if>" alt="个人头像" id="csavatar"/>		        <div id="file_upload" style="width:60%;margin-top:.32rem;text-align:center;"></div>				<input type="hidden" id="cuavatar" name="uavatar">		    </div>		    <div class="txtset">		        <label>昵称</label>		        <input type="text" id="csnick" name="unick" value="${currentUser.unick!''}"/>		        <label>您的文字</label>		        <textarea id="cscontent"></textarea>		        <label>文字让沟通更有成效，长度6-25个字！</label>		    </div>		    </form>		 </li>    </ol>    </section>    <a href="javascript:;" onclick="genenateQcode()" class="create-code" title="">提交，生成二维码</a></div><script>	$(function(){		$('#file_upload').Huploadify({				'multi': false, // 限制单图上传				'auto': true,				'formData' : {'type' : 'userAvatar'},				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径				'fileObjName' : 'file',				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet				'buttonText' : '修改',				'onUploadSuccess' : function(file, data, response) {					var result = eval("("+data+")");					$("#csavatar").attr("src",basePath+result.savedFile);					$("#cuavatar").val(result.savedFile);					alert("头像上传成功。");					//$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');		        },		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);		        	//$.messager.alert('消息提醒','上传失败。');		        	alert("头像上传失败。");		        }		});	});</script></body></html>