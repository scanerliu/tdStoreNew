<#import "/common/app.ftl" as app>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="keywords" content="${system.webkeywords!''}">
    <meta name="description" content="${system.webdescription!''}">
    <meta name="copyright" content="${system.webcopyright!''}" />
    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />
    <title>体验店申请 - ${system.webkeywords!''}</title>
    <!-- css -->
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/index.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/chuangkeftt.css" />
	<link rel="stylesheet" href="${app.basePath}/static/default/client/style/lhead.css" />
	<!-- js -->
	<#include "/common/common.ftl" />
	<script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/library.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/index.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>
	<script type="text/javascript" src="${app.basePath}/static/js/client/agent/agent.js"></script>
	<script src="${app.basePath}/static/js/uploadify/jquery.uploadify.js" type="text/javascript"></script>
    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script>
</head>
<body>
<h1 style="display:none;">创客</h1>
	<!-- 头部 -->
	<#include "../common/commonheader.ftl">
	<!-- 头部 -->
	<!-- 中间 -->
	<div class="wrapper">
	    <div class="become-agent">
	        <div class="descinfo">
	            <span class="title">${agent.title!''}</span>
	            <p>
	            	${agent.note!''}
	            </p>
	            <p>
	            	￥<span><#if agent.salesPrice??>${agent.salesPrice?string('0.00')}</#if></span>
	            </p>
	        </div>
        	<form id="agentform" method="post" action="${app.basePath}/agent/addagent" class="all_nation white_box" style="height:630px;">
        	<div class="tabb obody" style="float:left;">
	    		<div class="fig fig4">
	                <label class="lab1 fl">地址地区:</label>
	                <span id="provincespn"></span><span id="cityspn"></span><span id="regionspn"></span><span id="townspn"></span><span id="villagespn"></span>
	                <script>
						$(function(){
							getLongDistricts({'obj':null,'num':0,'total':4});
					    });
					</script>
	            </div>
	            <div class="fig fig1">
	                <label class="lab1 fl Validform_label">详细地址:</label>
	                <input type="text" name="address" placeholder="请填写详细地址" datatype="*2-50" nullmsg="请填写详细地址！">
	            </div>
	            <div class="fig fig1">
	                <label class="lab1 fl Validform_label">手机号码:</label>
	                <input type="text" name="telphone" placeholder="请填写手机号码" datatype="m" nullmsg="请填写手机号码！">
	            </div>
	            <div class="fig fig1">
	                <label class="lab1 fl">想要代理的产品分类:</label>
	                <span id="onetypespn"></span><span id="twotypespn"></span><span id="typespn"></span>
	                <script>
						$(function(){
							getAllTypes({'obj':null,'num':0})
					    });
					</script>
	            </div>
	            <div class="fig fig2">
	                <label for="" class="lab1 fl"><span>*</span>上传照片</label>
	                <section class="sig fl">
	                    <ul id="imglist">
	                        <li class="li1">
	                            <input type="file" id="file_uploads" style="width:1000px;"/>
	                        </li>
	                    </ul>
	                    <p class="pig">小提示：亲，体验店申请需要填写门店的详细信息，门店的图片，社区的图片等</p>
	                </section>
	            </div>
	            <div class="fig fig2">
	                <label for="" class="lab1 fl Validform_label"><span>*</span>百度坐标</label>
	                <input type="text" name="storesite" placeholder="请定位百度坐标" datatype="/^[-]?[0-9]\d{1,3}(\.\d{1,6})?,[-]?[0-9]\d{1,3}(\.\d{1,6})?$/i" nullmsg="请定位百度坐标！">
	                <a href="http://api.map.baidu.com/lbsapi/getpoint/index.html" target="_blank">获取百度地图坐标工具</a>
	            </div>
	            <input type="submit" id="sub_btn" class="btnjoin" value="立即申请" />
	        	<input type="hidden" name="agentProductId" id="agentProductId" value="${agent.id!''}"/>
	        	<input type="hidden" name="productType" id="productType" value="2"/>
	        	<input type="hidden" name="regionId" id="upid" value="" nullmsg="请选择所在地区！"/>
	        	<input type="hidden" name="typeId" id="productTypeId" value="" nullmsg="请选择代理分类！"/>
	        	<input type="hidden" id="isAgentProductUsePackage" value="${agent.gift?c}"/>
	        	<input type="hidden" id="agentprice" value="${agent.salesPrice?c}"/>
	        </div>
	        </form>
	    </div>
	</div>
	<!-- 中间-结束 -->
	<script>
	$(function(){
		//初始化图片上传
		$('#file_uploads').uploadify({
				'multi': true, // 限制单图上传
				'auto': true,
				'formData' : {'type' : 'agent'},
				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径
				'fileObjName' : 'file',
				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet
				'buttonText' : '',
				//'buttonImage': '${app.basePath}/static/touch/images/add_72px_1199462_easyicon.net.png',
				'onUploadSuccess' : function(file, data, response) {
					var result = eval("("+data+")");
					$("#imglist").append("<li><img src='"+ basePath+result.savedFile +"' alt='图片不存在'/><a 'javascript:;' title='删除图片' class='aclose' onclick='deleteimg(this)'></a> <input type='hidden' name='attachments' value='"+result.savedFile+"'/></li>");
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
		        	alert("图片上传失败。");
		        }
		});
	});
	$("#agentform").Validform({
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
		 	var images = $("#imglist img");
		 	if(images.length==0){
		 		alert("请上传资质照片！");
		 		return false;
		 	}
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
				window.location.href=basePath+"/user/center";
			}
		}
	});
	function deleteimg(obj){
		$(obj).parent().remove();
	}
</script>
	<!-- 底部 -->
	<#include "../common/commonfooter.ftl">
	<!-- 底部 -->
</body>
</html>