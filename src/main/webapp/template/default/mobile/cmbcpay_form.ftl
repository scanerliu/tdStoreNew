<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创客联盟</title>
<meta name="keywords" content="${system.webkeywords!''}">
<meta name="description" content="${system.webdescription!''}">
<meta name="copyright" content="${system.webcopyright!''}" />
<link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />

    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/swipe.js"></script> 
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>

<link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
<link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
</head>

<body>
<div class="main">
    <form id="login" action="http://111.205.207.118:55003/epay/cmbcpay.do" method="post">
	   <input type="hidden"" name="orderinfo" value="${orderData!''}"/>
	   <input type="submit" value="跳转" />
	 </form>
</div>
</body>
</html>
