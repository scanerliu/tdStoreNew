<#import "/common/app.ftl" as app>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="description" content="中国创客联盟"/>
    <meta name="keywords" content="中国创客联盟"/>
    <meta name="author" content="中国创客联盟"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title>退款申请- ${system.webkeywords!''}</title>
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
    <script src="${app.basePath}/static/js/client/order/orderlist.js" type="text/javascript"></script>
    <script src="${app.basePath}/static/js/uploadify/jquery.uploadify.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
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
	                <div class="div1 fl">填写申请</div>
	            </div>
	            <div class="form_box3 all_nation">
					<form id="refundForm" method="post" action="${app.basePath}/order/refund" class="obody">
					<input type="hidden" name="orderId" value="${order.orderId!'0'}"/>
					<input type="hidden" name="skuId" value="<#if sku??>${sku.orderSkuId!'0'}</#if>"/>
	                <div class="div1 div_obox">
	                    <label class="lab1 fl"><span>*</span>申请服务</label>
	                    <select name="cargoStatus">
				            <option value="1">退款退货</option>
				            <option value="2">退款不退货</option>
				        </select>
	                </div>
	                <div class="div1 div_obox">
	                    <label class="lab1 fl"><span>*</span>退款原因</label>
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
	                </div>
	                <div class="div2 div_obox">
	                    <label class="lab1 fl"><span>*</span>退款金额</label>
						<input type="text" style="float: left;width: 178px;height: 30px;border: 1px solid #ddd;" placeholder="0" value="0" id="returnAmount" name="returnAmount" onblur="formatInputPrice(this,0,${totalAmount!'0'})" onkeyup="formatInputPrice(this,0,${totalAmount!'0'})" datatype="/(^[1-9]\d{0,7}(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/i" nullmsg="请填写退款金额！" errormsg="退款金额格式错误！"/>
						<label class="lab2 fl">最多退款${totalAmount!'0'}元</label>
	                </div>
	                <div class="div4 div_obox">
	                    <label class="lab1 fl Validform_label">退款说明</label>
	                    <textarea placeholder="请在这里输入文字，少于100文字" name="returnReason" cols="30" rows="10" datatype="*10-100"></textarea>
	                </div>
	                <div class="fig fig2" style="float:none;margin-top:20px;">
			            <label for="" class="lab1 fl" style="margin-right: 20px;width: 100px;text-align: right;">上传照片</label>
			            <section class="sig fl" style="width:664px;">
			                <ul id="imageslist">
			                    <li class="li1">
			                        <input type="file" id="file_upload" style="width:1000px;"/>
			                    </li>
			                </ul>
			            </section>
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
		$('#file_upload').uploadify({
				'multi': false, // 限制单图上传
				'auto': true,
				'formData' : {'type' : 'refund'},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#imageslist").append("<li><img src='"+basePath+result.savedFile+"' presrc='"+result.savedFile+"' alt='图片'><a href='javascript:;' title='删除图片' class='aclose' onclick='deleteImg(this)'></a><input type='hidden' name='voucherImageList' value='"+result.savedFile+"'/></li>");
					//$.messager.alert('消息提醒','图片' + file.name + ' 上传成功。 ');
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	//$.messager.alert('消息提醒','上传失败。');
		        	alert("图片上传失败。");
		        }
		});
		$("#refundForm").Validform({
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
				}
			}
		});
	});
</script>
</body>
</html>