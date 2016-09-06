<#import "/common/app.ftl" as app> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webkeywords!''}">
    <meta name="copyright" content="${system.webkeywords!''}" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>个人信息-  ${system.webkeywords!''}</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/index.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/has.css">
    <!-- js -->
    <#include "/common/common.ftl" />
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/library.js"></script>
	<!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/user/user.js"></script>
    <script src="${app.basePath}/static/js/uploadify/jquery.uploadify.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
<h1 style="display:none;"></h1>

<!-- header_top -->
<#include "../common/centerheader.ftl">
<!-- header_top end -->
<div class="clear"></div>
<!-- Center Start -->
<!-- Center Start -->
<div class="index-main">
    <div class="personal-center">
        <!-- 左侧导航 -->
        <#include "../common/centerleftmenu.ftl">
         <!-- 右侧内容 -->
        <div class="change_password modify_personal">
            <!-- 账户设置 -->
            <form id="userInfoForm" method="post">
              <div class="h2">个人信息设置</div>
              <div class="head-img">
                <div class="img">
                  <img src="<#if currentUser.uavatar??><#if currentUser.uavatar?index_of("http")==0><#else>${app.basePath}</#if>${currentUser.uavatar!''}<#else>${app.basePath}/static/default/mobile/images/defaultavtar.png</#if>" alt="个人头像" id="csavatar"/>
				  <input type="hidden" id="cuavatar" name="uavatar">
                </div>
                <div class="right">
                  <div id="file_upload"></div>
                  <p>图片格式:GIF、JPG、JPEG、PNG,最适合尺寸100*100 像素</p>
                </div>
              </div>
              <div class="info-list" style="margin-top:30px;">
                <label>用户名</label>
                <span class="rinfo">${currentUser.uname!''}</span>
              </div>
              <div class="info-list" style="margin-top:30px;">
                <label>手机号</label>
                <span class="rinfo">${currentUser.utel!''}</span>
                <span class="tips"><a href="#">修改手机号</a></span>
              </div>
              <div class="info-list" style="margin-top:30px;">
                <label>昵称</label>
                <input type="text" id="csnick" name="unick" value="${currentUser.unick!''}"/>
              </div>
              <div class="info-list">
                <label>性别</label>
                <select name="ugenter" style="background:#fff;">
					<option value="1" <#if currentUser.ugenter==1>selected</#if>>男</option>
					<option value="2" <#if currentUser.ugenter==2>selected</#if>>女</option>
					<option value="3" <#if currentUser.ugenter==3>selected</#if>>保密</option>
				</select>
              </div>
              <div class="info-list">
                <label>生日</label>
                <input type="text" name="ubirthday" id="beginTime" class="select-date" placeholder="${currentUser.ubirthdayStr!''}" onClick="WdatePicker()" readonly="readonly">
                <div class="clear"></div>
                <!-- <span class="tips">信息输入错误！</span> -->
              </div>
              <input class="btn-ok-modify" type="button" value="确定" onclick="saveUserInfo()">
            </form>
            <!-- 账户设置-结束 -->
        </div>
        <!-- 右侧内容 END -->
    </div>
    <div class="clear"></div>
</div>
<!-- Center End -->
<script>
	$(function(){
		$('#file_upload').uploadify({
				'multi': false, // 限制单图上传
				'auto': true,
				'formData' : {'type' : 'userAvatar'},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '上传头像',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#csavatar").attr("src",basePath+result.savedFile);
					$("#cuavatar").val(result.savedFile);
					alert("头像上传成功。");
					//$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	//$.messager.alert('消息提醒','上传失败。');
		        	alert("头像上传失败。");
		        }
		});
	});
</script>
<!-- Footer Start -->
<#include "../common/commonfooter.ftl">
<!-- Footer End -->
</body>
</html>