<#import "/common/app.ftl" as app>
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
    <title>附近门店详情页</title>
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/common.css" type="text/css" />
    <link rel="stylesheet" href="${app.basePath}/static/touch/css/main.css" type="text/css" />
    <!-- js -->
    <script src="http://api.map.baidu.com/api?v=2.0&ak=I3Xk7OyH19ufStmYQ3a6Gr4hoaTNKP6j" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>
</head>
<style type="text/css">
	body, html,#map {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	
<body class="bg_2">

<!-- header_top -->
<div class="top_heater">
    <a href="javascript:history.go(-1);" title="返回" class="hleft hback"></a>
    <span>附近门店</span>
</div>
<!-- header_top end -->
	<div id="map" style="height:100%;width:100%"></div>
    <script type="text/javascript">
      // 百度地图API功能
      <#if experexce.lng?? && experexce.lat??>
      var map = new BMap.Map("map");
      var point = new BMap.Point(${experexce.lng!''},${experexce.lat!''});
      map.centerAndZoom(point,14);

      var geolocation = new BMap.Geolocation();
      geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
          var mk = new BMap.Marker(r.point);
          map.addOverlay(mk);
          map.panTo(r.point);
          var mk2 = new BMap.Marker(point);
          map.addOverlay(mk2);
          map.panTo(point);
         // console.debug('您的位置：'+r.point.lng+','+r.point.lat);
        }
        else {
          //alert('failed'+this.getStatus());
        }        
      },{enableHighAccuracy: true})
      <#else>
	      alert("当前店铺未添加具体坐标，无法查看地图");
	      window.history.go(-1);
      </#if>
      
</script>
<!-- Center End -->
</body>
</html>