<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>录入物流单号- ${system.webkeywords!''}</title>
    <!-- 网站图标 -->
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <!-- css -->
    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">
    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/index.css">
	<#include "/common/common.ftl" />
    <!-- js -->
    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
    <!--通用js-->
    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
    <script src="${app.basePath}/static/js/client/order/orderlist.js" type="text/javascript"></script>
</head>
<body>
<h1 style="display:none;"></h1>
	<!-- Header -->
	<#include "../common/centerheader.ftl">
	<!-- Header -->
	<div class="clear"></div>
	<!-- Center Start -->
	<div class="index-main">
	    <div class="personal-center">
	        <!-- 左侧导航 -->
        	<#include "../common/centerleftmenu.ftl">
        	
	        <!-- 右边 -->
        	<div class="need_value l_right bg_white fr">
	            <div class="box1">
	                <div class="div1 fl" style="width:100px;">录入物流单号</div>
	            </div>
	            <div class="form_box3 all_nation">
					<form id="tractForm" method="post" action="${app.basePath}/order/savetract" class="obody">
					<input type="hidden" name="orderId" value="${shipment.orderId!'0'}"/>
					<input type="hidden" name="id" value="${shipment.id!'0'}"/>
	                <div class="div1 div_obox">
	                    <label class="lab1 fl"><span>*</span>物流公司</label>
				        <select name="trackingId">
							<#if expressList??>
							<#list expressList as express>
								<option value="${express.id}">${express.name}</option>
							</#list>
							</#if>
						</select>
	                </div>
	                <div class="div2 div_obox">
	                    <label class="lab1 fl Validform_label"><span>*</span>物流单号</label>
						<input type="text" style="float: left;width: 178px;height: 30px;border: 1px solid #ddd;" value="" id="trackingNo" name="trackingNo" length="30" datatype="*5-40"/>
	                </div>
	                <div class="div_sure sub_div"><input type="submit" value="确认提交" class="btn" style="width:146px; height:40px;border-radius:0;"></div>
	            </div>
				</form>
	        </div>
	    </div>
	    <div class="clear"></div>
	</div>
	<!-- Center End -->
	<!-- Footer Start -->
	<#include "../common/commonfooter.ftl">
	<!-- Footer End -->
<script>
	$(function(){
		$("#tractForm").Validform({
			tiptype:function(msg,o,cssctl){
			    //msg：提示信息;
			    //o:{obj:*,type:*,curform:*},
			    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），
			    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 
			    //curform为当前form对象;
			    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			    if(o.type == 3){
			    	alert(msg);
			    }
			},
			beforeCheck:function(curform){
			},
			beforeSubmit:function(curform){
			 	openwaiting();
			},
			postonce:true, // 开启二次提交防御
			ajaxPost:true, 
			callback:function(data){
				//返回数据data是json对象，{"msg":"demo info","code":"1"}
				//info: 输出提示信息;
				//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
				closewaiting();
				alert(data.msg);
				if(data.code == 1){
					window.location.href=basePath+"/order/refundlist";
				}
			}
		});
	});
</script>
</body>
</html>