<#import "/common/app.ftl" as app> <#include "/common/common.ftl" /><!DOCTYPE html><html lang="en"><head>    <meta charset="UTF-8">    <meta http-equiv="Content-Language" content="zh-CN">    <meta name="keywords" content="">    <meta name="description" content="">    <meta name="copyright" content="" />    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">    <meta content="yes" name="apple-mobile-web-app-capable">    <meta content="black" name="apple-mobile-web-app-status-bar-style">    <meta content="telephone=no" name="format-detection">    <title>创客联盟</title>    <!-- css -->    <link href="${app.basePath}/static/touch/css/common.css" rel="stylesheet" type="text/css" />    <link href="${app.basePath}/static/touch/css/main.css" rel="stylesheet" type="text/css" />    <link href="${app.basePath}/static/touch/css/x_pc.css" rel="stylesheet" type="text/css" />    <link href="${app.basePath}/static/touch/css/f-personalcenter.css" rel="stylesheet" type="text/css" />    <link rel="stylesheet" type="text/css" href="${app.basePath}/static/js/huploadify/Huploadify.css">    <!-- js -->    <script type="text/javascript" src="${app.basePath}/static/touch/js/jquery-1.9.1.min.js"></script>    <script type="text/javascript" src="${app.basePath}/static/touch/js/common.js"></script>    <script src="${app.basePath}/static/js/huploadify/jquery.Huploadify.js" type="text/javascript"></script>    <script type="text/javascript" src="${app.basePath}/static/js/Validform_v5.3.2_min.js"></script></head><body class="body_gray"><!-- pop-ups --><#--<article class="pop-ups">    <section class="pop-one">        <img src="../x-img/icon_pop_success.png" alt="">        <div>恭喜您，参加竞选成功！</div>        <div>请继续关注我们的竞选结果！</div>    </section></article>--><script type="text/javascript">    $(function(){        $(function(){            $('#a-success').click(function(e){                $('.pop-ups').fadeIn();                e.stopPropagation();  //阻止冒泡事件            });            $(document).click(function(){                $('.pop-ups').fadeOut();            });            $(".pop-ups .pop-one").click(function(e){                e.stopPropagation();            });        });    });</script><!-- pop-ups end --><!-- header_top --><div class="top_heater">    <a href="${app.basePath}/mobile/user/center" title="返回个人中心" class="hleft hback"></a>    <span>股东竞选资料填写</span></div><!-- header_top end --><!-- Center Start --><form id="electionForm" action="${app.basePath}/mobile/user/saveElection"><section class="container data-fill">    <ol class="areachoose">        <li id="firstLevelDistrict">        </li>        <li id="secondLevelDistrict">        </li>        <li id="thirdLevelDistrict">        </li>    </ol>    <ol class="data-avatar">        <li>            <span>活动介绍:</span>        </li>	</ol>    <article id="campaignDescription">    </article>    <input type="hidden" name="regionId" id="regionId" value="">    <ol class="data-avatar">        <li style="position:relative">            <span>个人头像</span>            <a href="javascript:;"><img alt="暂无头像" id="uavatarShow" style="border-radius:50%;" src="${currentUser.uavatar!''}"/></a>			<div style="position:absolute;right:30%;top:0;" id="file_upload"><div>			<input type="hidden" id="uavatar" name="uavatar" value="${currentUser.uavatar!''}">        	<style>        		#file_upload_1-button{text-align:center;color:#fff;padding:0 .2rem;margin-top:.42rem;height:.36rem;line-height:.36rem;border-radius:.06rem;}        	</style>        </li>        <li>            <span>姓名</span>            <input style="float:right;border:none;width:50%;height:.8rem;line-height:.8rem;text-align:right;" type="text" name="uname" id="uname" value="${currentUser.uname!''}">        </li>    </ol>    <article>        <textarea placeholder="请填写您的竞选宣言！" id="declaration" name="declaration"></textarea>    </article>    <article>        <textarea placeholder="请填写您的个人简介！" id="resume" name="resume"></textarea>    </article></section><!-- Center End --><!--footer Start--><footer>    <a href="javascript:goElection();" class="create-code" id="a-success" title="">完成</a>    <span class="footclear"></span></footer></form><!--footer End--></body><script>	$(document).ready(function(){		// 初始化加载第一级地区		loadDistrictSelections('0', 'firstLevelDistrict');		// 初始化ValidForm插件		$("#productForm").Validform({			tiptype:function(msg,o,cssctl){			    //msg：提示信息;			    //o:{obj:*,type:*,curform:*},			    //obj指向的是当前验证的表单元素（或表单对象，验证全部验证通过，提交表单时o.obj为该表单对象），			    //type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, 			    //curform为当前form对象;			    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;			    if(o.type == 3){			    	alert(msg);			    }			},		});		//初始化图片上传		$('#file_upload').Huploadify({				'multi': false, // 限制单图上传				'auto': true,				'formData' : {'type' : 'userCampaign'},				'swf'      : basePath+'/static/js/uploadify/uploadify.swf', // swf存放的路径				'fileObjName' : 'file',				'uploader' : basePath+'/uploadify/upload/singleFile',    // 处理上传的Servlet				'buttonText' : '修改',				//'buttonImage': '${app.basePath}/static/touch/images/add_72px_1199462_easyicon.net.png',				'onUploadSuccess' : function(file, data, response) {					var result = eval("("+data+")");					var result = eval("("+data+")");					$("#uavatarShow").attr("src",basePath+result.savedFile);					$("#uavatar").val(basePath+result.savedFile);		        },		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {		        	//alert('The file ' + file.name + ' could not be uploaded: ' + errorString);		        	alert("图片上传失败。");		        }		});	});		// 地区加载	function loadDistrictSelections(parentId, liId){		var url = basePath+"/mobile/user/getDistrictSelections";		var nextLiId = nextLi(liId);		if(nextLiId != "nothing"){			$("#" + nextLiId).html("");		}		var nnextLiId = nextLi(nextLiId);		if(nnextLiId != "nothing"){			$("#" + nnextLiId).html("");		}		var loadData = {"parentId": parentId, "nextLiId": nextLiId};		$.post(url, loadData, function(template){    		$("#"+liId).html(template);    		flushDescription();    	}, "text");	}		// 获取所选地区的下一级地区下拉框	function getNextLevelDistrcitSelections(parentId, liId, canGetNextDistrictSelections){		if(canGetNextDistrictSelections == "false"){			flushDescription();			return;		}else{			loadDistrictSelections(parentId, liId);				}	}		// 下一级地区liId	function nextLi(currentLi){		if(currentLi=="firstLevelDistrict"){			return "secondLevelDistrict";		}		if(currentLi=="secondLevelDistrict"){			return "thirdLevelDistrict";		}		if(currentLi=="thirdLevelDistrict"){			return "nothing";		}	}		// 获取选择的地区id，若没选中则返回-1	function getSelectedDistrict(){		var firstSelect = $("#firstLevelDistrict").find("select");		var secondSelect = $("#secondLevelDistrict").find("select");		var thirdSelect = $("#thirdLevelDistrict").find("select");		var firstOption = "-1";		var secondOption = "-1";		var thirdOption = "-1";		if(firstSelect.length != 0){			firstOption = $("#firstLevelDistrict").find("select").val();		}		if(secondSelect.length != 0){			secondOption = $("#secondLevelDistrict").find("select").val();		}		if(thirdSelect.length != 0){			thirdOption = $("#thirdLevelDistrict").find("select").val();		}		if(thirdOption != "-1"){			return thirdOption;		}else if(secondOption != "-1"){			var firstSelectName = $("#firstLevelDistrict").find("select").find("option:selected").text();			if(isCentralCity(firstSelectName)){				return secondOption;			}		}else if(firstOption != "-1"){			var firstSelectName = $("#firstLevelDistrict").find("select").find("option:selected").text();			return firstOption;		}		return "-1";	}		//是否是直辖市	function isCentralCity(districtName){		if(districtName.indexOf("北京")>=0 || districtName.indexOf("天津")>=0 || districtName.indexOf("上海")>=0 || districtName.indexOf("重庆")>=0){			return true;		}else{			return false;		}	}		function goElection(){		var selectedDistrictId = getSelectedDistrict();		if(selectedDistrictId == -1){			alert("未选中地区。");			return;		}else{			// 判断已选择地区是否有活动			var url = basePath+"/mobile/user/hasElection";			var loadData = {"selectedDistrictId": selectedDistrictId};			$.post(url, loadData, function(data){	    		var jsonData = eval('('+data+')');	    		if(jsonData.code == "0"){	    			alert(jsonData.msg);	    			return;	    		}else if(jsonData.code == "1"){	    			$("#regionId").val(selectedDistrictId);	    			// 加载活动类容	    			if(!hasNullInput()){		    			var formUrl = basePath+"/mobile/user/saveElection";		    			var formData = $("#electionForm").serialize();		    			$.post(formUrl, formData, function(backData){		    				var jsonBackData = eval('('+backData+')');		    				alert(jsonBackData.msg);		    				window.location.href=basePath+"/mobile/user/center";		    			});	    					    			}else{	    				alert("信息不完整！");	    			}	    		}	    	}, "text");		}	}		function hasNullInput(){		if($("#uname").val() == "" || $("#declaration").val() == "" || $("#resume").val() == ""){			return true;		}else{			return false;		}	}		function flushDescription(){		$("#campaignDescription").html("");		var selectedDistrictId = getSelectedDistrict();		if(selectedDistrictId != "-1"){			var url = basePath+"/mobile/user/flushDescription";			var loadData = {"selectedDistrictId": selectedDistrictId};			$.post(url, loadData, function(data){				var jsonData = eval('('+data+')');				if(jsonData.code == "1"){	    			$("#campaignDescription").html(jsonData.note);									}	    	}, "text");		}	}</script></html>