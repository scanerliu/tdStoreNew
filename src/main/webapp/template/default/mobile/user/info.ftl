<#import "/common/app.ftl" as app> 
<#include "/common/common.ftl" />
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Language" content="zh-CN">
<title>个人信息</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="copyright" content="" />
<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<!-- css -->
<link href="${app.basePath}/static/touch/css/common.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/main.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/date.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/x_pc.css" rel="stylesheet" type="text/css" />
<link href="${app.basePath}/static/touch/css/f-personalcenter.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/huploadify/Huploadify.css">
<!-- js -->
<script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
<script type="text/javascript" src="${app.basePath}/static/touch/js/date.js"></script>
<script type="text/javascript" src="${app.basePath}/static/touch/js/iscroll.js"></script>
<script type="text/javascript" src="${app.basePath}/static/touch/js/winout.js"></script>
<script src="${app.basePath}/static/js/huploadify/jquery.Huploadify.js" type="text/javascript"></script>
<script src="${app.basePath}/static/js/mobile/user/user.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
	$(function(){
	   $('#beginTime').date();
	   $('body').winout({
	   		innerwidth:"4rem",
	   		font:"0.24rem",
	   		innerheight:"0.8rem",
	    	lineheight:"0.8rem",
	    	innertext:"保存成功"
	   });
	});
	function save(){
	    $('.outerbox').fadeIn(400,function(){
	    	$('.outerbox').fadeOut();
	    });
	}
</script>
<body class="body_gray">


<!-- pop-ups -->
<article class="avatarchange">
	<section class="pop-one">
		<span class="title">上传新头像</span>
		<div class="avatar-cut">
			<div>

			</div>
		</div>
		<span class="input-upload">点击上传<input type="file"/></span>
		<div class="avatar-edit">
			<a href="javascript:;" title="">放大</a>
			<a href="javascript:;" title="">缩小</a>
			<a href="javascript:;" title="">裁剪</a>
		</div>
		<div class="btn-wrap">
			<input type="button" value="保存"/>
			<a href="javascript:$('.avatarchange').fadeOut();" title="">取消</a>
		</div>
	</section>
</article>
<script type="text/javascript">
	$(function(){
	/*
		$('#avatar-change').click(function(e){
			$('.avatarchange').fadeIn();
			e.stopPropagation();  //阻止冒泡事件
		});
		$(document).click(function(){
			$('.avatarchange').fadeOut();
		});
		$(".avatarchange .pop-one").click(function(e){
			e.stopPropagation();
		});
		*/
	});
</script>
<!-- pop-ups end -->
<!-- header_top -->
<div class="top_heater">
	<a href="${app.basePath}/mobile/user/center" title="返回个人中心" class="hleft hback"></a>
	<span>个人信息</span>
	<a href="#" title="" class="hright hsave" onclick="saveUserInfo();">保存</a>
</div>
<!-- header_top end -->
<!-- order_detail_title -->
<form id="userInfoForm" method="post">
<section class="container">
	<div id="datePlugin"></div>
	<ul class="my_center p">
		<li class="li01">
			<font>个人头像</font>
			<a id="avatar-change" href="javascript:;" title="修改头像">
				<img alt="暂无头像" id="uavatarShow" src="${currentUser.uavatar!''}"/>
				<div id="file_upload"><div>
				<input type="hidden" id="uavatar" name="uavatar" value="${currentUser.uavatar!''}">
			</a>
		</li>
		<li class="li02">
			<font>用户昵称</font>
			<a href="#" title="修改昵称">
				<input type="text" name="unick" id="unick" value="${currentUser.unick!''}" />
				<span></span>
			</a>
		</li>
		<li class="li02">
			<font>性别</font>
			<a href="#" title="">
				<div class="sky"></div>
				<select name="ugenter">
					<option value="1" <#if currentUser.ugenter==1>selected</#if>>男</option>
					<option value="2" <#if currentUser.ugenter==2>selected</#if>>女</option>
					<option value="3" <#if currentUser.ugenter==3>selected</#if>>保密</option>
				</select>
				<span></span>
			</a>
		</li>
		<li class="li02">
			<font>生日</font>
			<a href="#" title="修改生日">
				<input name="ubirthday" id="beginTime" class="select-date" placeholder="${currentUser.ubirthdayStr!''}">
				<span></span>
			</a>
		</li>
		<li class="li02">
			<font>手机号码</font>
			<a class="inp-phone-num" href="#" title="修改手机号">
				<input name="utel" type="text" value="${currentUser.utel!''}">
				<span></span>
			</a>
		</li>
		<li class="li02">
			<font>我的收货地址</font>
			<a href="${app.basePath}/mobile/user/shoppingAddress" title="">
				<span></span>
			</a>
		</li>
	</ul>
</section>
</form>
</body>
<script>
	$(function(){
		$('#file_upload').Huploadify({
				'multi': false, // 限制单图上传
				'auto': true,
				'formData' : {'type' : 'userAvatar'},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '修改',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#uavatarShow").attr("src",basePath+result.savedFile);
					$("#uavatar").val(basePath+result.savedFile);
					$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	$.messager.alert('消息提醒','上传失败。');
		        }
		});
	});
</script>
</html>
