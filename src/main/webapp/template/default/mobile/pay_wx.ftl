<#import "/common/app.ftl" as app>
<#include "/common/common.ftl" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="copyright" content="" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<!--css-->
<script language="javascript" src="http://res.mail.qq.com/mmr/static/lib/js/jquery.js" type="text/javascript"></script>
<script language="javascript" src="http://res.mail.qq.com/mmr/static/lib/js/lazyloadv3.js" type="text/javascript"></script>
<script type="text/javascript">
function onBridgeReady(){
    var data = {
            "appId": "${appId}", //公众号名称，由商户传入
            "timeStamp": "${timeStamp}", //时间戳
            "nonceStr": "${nonceStr}", //随机串
            "package": "${package}",//扩展包
            "signType": "MD5", //微信签名算法：MD5
            "paySign": "${paySign}" //微信签名
        };
   WeixinJSBridge.invoke(
       'getBrandWCPayRequest', data,
       function(res){
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
            alert("支付成功");
            window.location.href= basePath+"/mobile/order/list";
             // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
           }
           else if (res.err_msg == "get_brand_wcpay_request:cancel")
           {
               alert("取消支付！");
               window.location.href= basePath+"/mobile/order/list";
           }
           else{
               alert("支付失败！"+res.err_msg);
               window.location.href= basePath+"/mobile/order/list";
           }
       }
   );
}

if (typeof WeixinJSBridge == "undefined"){
   if( document.addEventListener )
   {
       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
   }
   else if (document.attachEvent)
   {
       document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
   }
}
else
{
   onBridgeReady();
}

</script>
<title>订单支付</title>
</head>
<body>

</body>
</html>