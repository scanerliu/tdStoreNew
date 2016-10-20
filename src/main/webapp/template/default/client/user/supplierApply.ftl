<#import "/common/app.ftl" as app> <!DOCTYPE html><html lang="en"><head>	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />    <meta http-equiv="Content-Language" content="zh-CN">    <meta name="keywords" content="${system.webkeywords!''}">    <meta name="description" content="${system.webkeywords!''}">    <meta name="copyright" content="${system.webkeywords!''}" />    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">    <meta content="yes" name="apple-mobile-web-app-capable">    <meta content="black" name="apple-mobile-web-app-status-bar-style">    <meta content="telephone=no" name="format-detection">    <link rel="shortcut icon" href="${app.basePath}/static/default/images/icon.ico" />    <title>供应商资质认证-  ${system.webkeywords!''}</title>    <!-- css -->    <link rel="stylesheet" href="${app.basePath}/static/default/client/style/site.css" type="text/css"/>    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/personal-center-common.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/f_personal_center.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/lhead.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/index.css">    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/default/client/style/has.css">    <!-- js -->    <#include "/common/common.ftl" />    <script type="text/javascript" src="${app.basePath}/static/js/jquery-1.12.3.min.js"></script>	<script type="text/javascript" src="${app.basePath}/static/js/client/html5.js"></script>	<script type="text/javascript" src="${app.basePath}/static/js/client/library.js"></script>	<!--通用js-->    <script type="text/javascript" src="${app.basePath}/static/js/client/core.js"></script>    <script type="text/javascript" src="${app.basePath}/static/js/client/common.js"></script>    <script src="${app.basePath}/static/js/uploadify/jquery.uploadify.js" type="text/javascript"></script>    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script></head><body><h1 style="display:none;"></h1><!-- header_top --><#include "../common/centerheader.ftl"><!-- header_top end --><div class="clear"></div><!-- Center Start --><!-- Center Start --><div class="index-main">    <div class="personal-center">        <!-- 左侧导航 -->        <#include "../common/centerleftmenu.ftl">         <!-- 右侧内容 -->        <div class="right-content">            <!-- 全国代理、商品编辑 -->            <#if currentUser?? && currentUser.tempsupplier gt 0>            <form id="userSupplierForm" action="${app.basePath}/user/saveUserSupplierApply" class="all_nation white_box" style="height:930px;">            	<input type="hidden" name="id" value="<#if userSupplier??>${userSupplier.id?c }</#if>">                <div class="otitle">                    <label for="" class="lab1 fl">供应商资质认证</label>                </div>                <div class="tabb obody">                    <div class="fig fig4">                        <label for="" class="lab1 fl">供应商资质:</label>                      	<span class="lab1">${currentUser.supplierTypeStr!''}</span>                        <input type="hidden" name="supplierType" value="${currentUser.supplierType!'0'}"/>                    </div>                    <#if userSupplier??>					    <div class="fig fig3">	                        <label for="" class="lab1 fl">申请状态</label>	                        <span>${userSupplier.statusStr!''}</span>	                    </div>				    </#if>				    <#if userSupplier?? && userSupplier.status?c == "3">					    <div class="fig fig3">	                        <label for="" class="lab1 fl">理由</label>	                        <span>${userSupplier.reply!''}</span>	                    </div>				    </#if>                    <div class="fig fig1">                        <label for="" class="lab1 fl Validform_label"><span>*</span>认证说明</label>                        <textarea name="note" placeholder="请在这里输入文字，少于200文字" class="fl" style="height:110px;" datatype="*6-200" nullmsg="请填写认证说明！"><#if userSupplier??>${userSupplier.note!''}</#if></textarea>                    </div>                    <div class="fig fig2">                        <label for="" class="lab1 fl"><span>*</span>上传照片</label>                        <section class="sig fl">                            <ul id="imglist">                                <li class="li1">                                    <#if  !userSupplier?? || (userSupplier?? && userSupplier.status?c != "2")><input type="file" id="file_uploads" style="width:1000px;"/></#if>                                    <span></span>                                </li>                                <#if imgList??>				        		<#list imgList as img>				        		<li>			                        <img src="${app.basePath}${img!''}" alt="图片">			                        <a href="javascript:;" title="删除图片" class="aclose" onclick="deleteimg(this)"></a>			                        <input type='hidden' name='attachments' value='${img!''}'/>			                    </li>				        		</#list>				        		</#if>                            </ul>                            <p class="pig">温馨提示：1.个人供应商需上传本人身份证正反两面的照片，2.公司供应商或企业供应商需上传“公司营业执照”、“法人身份证”、“企业产品9001认证”。3.照片大小在3M内。</p>                        </section>                    </div>                </div>                <div class="sub_div" style="padding:0 80px;">                	<#if  !userSupplier?? || (userSupplier?? && userSupplier.status?c != "2")>                		<input type="submit" value="确认提交" class="btn" style="width:146px; height:40px;border-radius:0;">                	</#if>                </div>            </form>            <!-- 全国代理、商品编辑-结束 -->            <#else>            	<div class="otitle">                    <label for="" class="lab1 fl">供应商资质认证</label>                </div>                <div class="tabb obody">                    <div class="fig fig4">                        	您未获得供应商资格，请先到创业中心购买供应商资格。<a href="${app.basePath}/agent/list" title="供应商购买">立即购买</a>                    </div>                </div>            </#if>        </div>        <!-- 右侧内容 END -->    </div>    <div class="clear"></div></div><!-- Center End --><script>	$(function(){		//初始化图片上传		$('#file_uploads').uploadify({				'multi': true, // 限制单图上传				'auto': true,				'formData' : {'type' : 'supplier'},				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径				'fileObjName' : 'file',				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet				'buttonText' : '',				//'buttonImage': '${app.basePath}/static/touch/images/add_72px_1199462_easyicon.net.png',				'onUploadSuccess' : function(file, data, response) {					var result = eval("("+data+")");					$("#imglist").append("<li><img src='"+ basePath+result.savedFile +"' alt='图片不存在'/><a 'javascript:;' title='删除图片' class='aclose' onclick='deleteimg(this)'></a> <input type='hidden' name='attachments' value='"+result.savedFile+"'/></li>");		        },		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);		        	alert("图片上传失败。");		        }		});	});	$("#userSupplierForm").Validform({		tiptype:function(msg,o,cssctl){		    //msg：提示信息;		    //o:{obj:*,type:*,curform:*},		    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），		    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 		    //curform为当前form对象;		    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;		    if(o.type == 3){		    	alert(msg);		    }		},		beforeCheck:function(curform){		},		beforeSubmit:function(curform){		 	var images = $("#imglist img");		 	if(images.length==0){		 		alert("请上传资质照片！");		 		return false;		 	}		 	openwaiting();		},		postonce:true, // 开启二次提交防御		ajaxPost:true, 		callback:function(data){			//返回数据data是json对象，{"msg":"demo info","code":"1"}			//info: 输出提示信息;			//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;			closewaiting();			alert(data.msg);			if(data.code == 1){				window.location.href=basePath+"/user/center";			}		}	});	function deleteimg(obj){		$(obj).parent().remove();	}</script><!-- Footer Start --><#include "../common/commonfooter.ftl"><!-- Footer End --></body></html>