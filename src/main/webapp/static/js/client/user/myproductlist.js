var __SELFCONFIGINDEX = 100000;
//查询商品列表
var __skuinex = 1;
function searchProducts(flag){
	var url = basePath + "/user/searchmyproduct";
	var loadData = "";
	if(flag){
		loadData = $("#searchform").serializeArray();
	}else{
		loadData = $("#listform").serializeArray();
	}
	$("#results").loading().load(url,loadData);
}

//分页函数
function fnGotoPageProducts(num){
	searchProducts(false);
}
/**
 * 初始化商品一口价设置
 */
function initProductCommon(){
	if($("#skuTable input[name$='.skuCode']").length<=0){
		return false;
	}
	var skucode = $("#skuTable input[name$='.skuCode']:first").val();
	var supplierPrice = $("#skuTable input[name$='.supplierPrice']:first").val();
	var salesPrice = 0;
	var marketPrice = $("#skuTable input[name$='.marketPrice']:first").val();
	var highPrice = $("#skuTable input[name$='.highPrice']:first").val();
	var lowPrice = $("#skuTable input[name$='.lowPrice']:first").val();
	var stock = 0;
	//计算供商品价格最低销售价
	var i=0;
	$("#skuTable input[name$='.salesPrice']").each(function(){
		if(i==0){
			salesPrice = Number($(this).val());
		}else{
			var sprice = Number($(this).val());
			if(salesPrice > sprice){
				salesPrice = sprice;
			}
		}
		i++;
	});
	//计算总库存
	$("#skuTable input[name$='.stock']").each(function(){
			stock = stock + Number($(this).val());
	});
	//赋值
	$("#comprodTab #cskuCode").val(skucode);
	$("#comprodTab #csupplierPrice").val(supplierPrice);
	$("#comprodTab #cprice").val(salesPrice);
	$("#comprodTab #cmarketPrice").val(marketPrice);
	$("#comprodTab #chighPrice").val(highPrice);
	$("#comprodTab #clowPrice").val(lowPrice);
	$("#comprodTab #cquantum").val(stock);
}
/**
 * tab选择项
 */
function selectTab(id){
	$("#"+id).on("click","a",function(){
		var oid = $(this).attr("aid");
		$(this).addClass("active").siblings().removeClass("active");
		if(oid==0){
			$("#sc_onshelf").val("");
		}else if(oid==1){
			$("#sc_onshelf").val("true");
		}else if(oid==2){
			$("#sc_onshelf").val("false");
		}
		searchProducts(true);
	});
}
/**
 * 商品编辑
 */
function editProduct(id){
	var url = basePath + "/user/editproduct";
	var loadData = {"id":id};
	$("#formdiv").loading().load(url,loadData);
	showForm();
}
/**
 * 添加零元商品
 */
function addZeroProduct(id){
	var url = basePath + "/user/editproduct";
	var loadData = {"id":id,"ktype":2};
	$("#formdiv").loading().load(url,loadData);
	showForm();
}
function getAllTypes(_settings){
	var settings = {obj:"",num:0};
	settings=$.extend(settings,_settings);
	var url = basePath+"/agent/producttypeallselect";
	var loadData = "";
	if(settings.num==0){
		loadData = {'parentId':0};
		$("#onetypespn").loading().load(url,loadData);
		$("#twotypespn").html("");
		$("#typespn").html("");
		$("#productTypeId").val("");
	}else if(settings.num==1){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#twotypespn").loading().load(url,loadData);
		$("#typespn").html("");
		$("#productTypeId").val("");
		changeType();
	}else if(settings.num==2){
		var upid = $(settings.obj).val();
		loadData = {'parentId':upid};
		$("#typespn").loading().load(url,loadData);
		$("#productTypeId").val("");
		changeType();
	}else if(settings.num==3){
		var upid = $(settings.obj).val();
		$("#productTypeId").val(upid);
		changeType();
	}
}
/**
 * 改变商品分类
 */
function changeType(){
	var typeId = $("#productTypeId").val();
	if(typeId!=""){
		var url = basePath + "/user/producttypeattributes";
		var loadData = {"id":typeId};
		$("#attrList").loading().load(url,loadData);
	}else{
		$("#attrList").html("");
	}
	clearSkuTable();
}

/**
 * 检查自定义属性值的合法性及后续操作
 */
function checkAttribute(obj){
	var patt1 = new RegExp("^([\u4E00-\uFA29]*[a-z]*[A-Z]*[0-9]*)+$");
	var val = $(obj).val();
	if(val==""){//输入为空时
		if($(obj).hasClass("selfconf")){//新增属性
			return false;
		}else{//已有输入框
			$(obj).parent().remove();
			flushTable();
		}
	}else{//输入不为空时
		if(patt1.test(val)){
		}else{
			alert("请正确填写自定义规格值，只能输入文字、字母、数字");
			$(obj).focus();
			return false;
		}
		//检查是否有重复的值
		var attrs = $(obj).parent().parent().parent().find('input[name="avs"]');
		var i=0;
		attrs.each(function(){
			var slib = $(this).val();
			if(slib==val){
				i++;
			}
		});
		
		if(i>1){
			alert("属性值重复，请修改！");
			$(obj).focus();
			return false;
		}
		
		if($(obj).hasClass("selfconf")){
			var addhtml = '<li><input class="fl chk" type="checkbox" name="'+$(obj).siblings(":first").attr("name")+'" value="'+__SELFCONFIGINDEX+'"><input type="text" name="avs" value="" class="fl selfconf" placeholder="自定义值" maxlength="20" keyup="checkAttribute(this)" onblur="checkAttribute(this)"></li>';
			__SELFCONFIGINDEX++;
			$(obj).parent().parent().append(addhtml);
			$(obj).removeClass("selfconf");
			$(obj).siblings(":first").removeClass("selfconf");
			$(obj).siblings(":first").prop("checked",true);
			$(obj).siblings(":first").on("click",function(){
				checkAttributeSelect(this);
			});
		}
		
		flushTable();
	}
}
/**
 * 勾选自定义属性值事件
 */
function checkAttributeSelect(obj){
	var jobj = $(obj);
	if(jobj.prop("checked")){
		
	}else if(jobj.hasClass("selfconf")){
	}else{		
		jobj.parent().remove();
		flushTable();
	}
}
function flushTable(){
	var selectUL = $("#attrList .slect");
	var idkey = new Array();
	var selected = true;
	selectUL.each(function(){
		var sected = $(this).find("input[type='checkbox']:checked");
		if(sected.length==0){
			selected = false;
			return false;
		}else{
			var attributes = new Array();
			sected.each(function(){
				var slib = $(this).siblings(":first").val();
				attributes.push($(this).attr("name")+"_"+$(this).siblings(":first").val());
			});
			idkey.push(attributes);
		}
	});
	idkey.sort();
	var alen = idkey.length;
	var result = new Array();
	if(selected && alen>0){
		if(alen == 1){
			result = idkey[0];
		}else{
			// result是所有属性按照规格顺序的全排列
			result = twoArrayAssembleToTrIds(idkey[0],idkey[1]);
			var i=2;
			while(i<alen){
				result = twoArrayAssembleToTrIds(result,idkey[i]);
				i++;
			}
		}
		//组装table数据
		var preskukeys = new Array();
		var preskuattris = $("#skuTable .skuspec");
		preskuattris.each(function(){
			var ake = $(this).attr("tid");
			if($.inArray(ake, result)<0){
				$(this).remove();
			}else{
				preskukeys.push(ake);
			}
		});
		console.log(result);
		if(result.length>0){
			var html = "";
			var kind = $("#pkind").val();
			$.each(result,function(i,o){
				if($.inArray(o, preskukeys)<0){
					var keystr = getkeystr(o);
					var specifications = getspecifications(o);
					html+='<tr tid="'+o+'" class="skuspec"><td>'+keystr+'</td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].skuCode" value="" datatype="n4-10" nullmsg="请填写货品编号！" errormsg="货品编号格式错误！只能填写4-10个数字"></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].supplierPrice" value="" datatype="/(^[1-9]\\d{0,7}(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)/i" nullmsg="请填写供应商价！" errormsg="供应商价格式错误！"></td>';
					if(kind==3){
						html+='<td><input type="text" name="skuList['+__skuinex+'].salesPrice" value="0" readonly="readonly" datatype="/(^[1-9]\\d{0,7}(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)/i" nullmsg="请填写零售价！" errormsg="零售价格式错误！"></td>';
					}else{
						html+='<td><input type="text" name="skuList['+__skuinex+'].salesPrice" value="" datatype="/(^[1-9]\\d{0,7}(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)/i" nullmsg="请填写零售价！" errormsg="零售价格式错误！"></td>';
					}
					html+='<td><input type="text" name="skuList['+__skuinex+'].marketPrice" value="" datatype="/(^[1-9]\\d{0,7}(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)/i" nullmsg="请填写市场价！" errormsg="市场价格式错误！"></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].highPrice" value="" datatype="/(^[1-9]\\d{0,7}(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)/i" nullmsg="请填写最高价！" errormsg="最高价格式错误！"></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].lowPrice" value="" datatype="/(^[1-9]\\d{0,7}(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)/i" nullmsg="请填写最低价！" errormsg="最低价格式错误！"></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].stock" value="" datatype="n1-7" nullmsg="请填写库存！" errormsg="库存格式错误，只能填写1-7位数字！"></td>';
					html+='<input type="hidden" name="skuList['+__skuinex+'].specifications" value="'+o+'">';
					html+='</tr>';
					__skuinex++;
				}
			});
			$("#skuTable").append(html);
			validateproductform();//添加验证
		}
	}else{
		clearSkuTable();
	}
}
//清空货品表
function clearSkuTable(){
	$("#skuTable .skuspec").remove();
}

//二维数组全排列
function twoArrayAssembleToTrIds(arr1,arr2){
	var arra = Array();
	for(var m in arr1){
		for(var n in arr2){
			arra.push(arr1[m]+"|"+arr2[n]);
		}
	}
	return arra;
}

//获取规格显示名称
function getkeystr(str){
	var arr = str.split("|");
	var res = "";
	$.each(arr,function(i,o){
		var names = o.split("_");
		if(i==0){
			res = names[0]+"("+names[1]+")";
		}else{
			res += ","+names[0]+"("+names[1]+")";
		}
	});
	return res;
}
//获取规格键值对json
function getspecifications(str){
	var arr = str.split("|");
	var res = "";
	$.each(arr,function(i,o){
		var names = o.split("_");
		if(i==0){
			res = '"'+names[0]+'":"'+names[1]+'"';
		}else{
			res += ','+'"'+names[0]+'":"'+names[1]+'"';
		}
	});
	return '{'+res+'}';
}

function validateproductform(){
	$("#productForm").Validform({
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
			var url = $("#imglist img:first").attr("presrc");
			$("#main_image_url").val(url);
			initProductCommon();
		},
		beforeSubmit:function(curform){
		 	//验证货品
			if($("#skuTable .skuspec").length==0){
		 		alert("请先勾选属性添加货品！");
		 		return false;
		 	}
		},
		postonce:true, // 开启二次提交防御
		ajaxPost:true, 
		callback:function(data){
			//返回数据data是json对象，{"msg":"demo info","code":"1"}
			//info: 输出提示信息;
			//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
			alert(data.msg);
			if(data.code == 1){
				returnList();
				refreshList();
			}
		}
	});
}
function showForm(){
	$("#formdiv").show();
	$("#listdiv").hide();
}
function returnList(){
	$("#formdiv").hide();
	$("#listdiv").show();
}
function refreshList(){
	searchProducts(false);
}
/**
 * 删除图片
 */
function deleteImg(aid){
	$("#attId"+aid).remove();
	$("#atid"+aid).remove();
}
/**
 * 获取选择的商品id字符串，如：1,2,3,5
 */
function getCheckedProductIds(){
	var nodes = $("#listform input[name='subbox']:checked");
	var s = '';
	$.each(nodes, function(){
		var rid = $(this).val();
		if(rid>0){
			if (s != '') {s += ',';}
			s += rid;
		}
	});
	return s;
}
/**
 * 批量操作商品
 * @param optype 1-上架，2-下架，11-删除
 */
function bacthDelProducts(optype){
	var oparr = [1,2,11];
	var ids = getCheckedProductIds();
	if(ids==""){
		alert('请先勾选要操作的商品!');
		return ;
	}
	if($.inArray(optype, oparr)<0){
		alert('操作不合规范!');
		return ;
	}
	var op = "";
	if(optype==1){
		op = "上架";
	}else if(optype==2){
		op = "下架";
	}else if(optype==11){
		op = "删除";
	}
	if(confirm("确定要"+op+"选择的商品？")){
		var url = basePath+"/user/batchoperproducts";
		var postData = {"type":optype,"ids":ids};
		openwaiting();
		$.post(url,postData,batchOperCallback,"text");
	}
}

/**
 * 批量操作返回
 * @param data
 */
function batchOperCallback(data){
	closewaiting();
	var result = eval("("+data+")");
	if(result.code==1){
		alert('操作成功。');
		refreshList();
	}else{
	  alert('操作失败!');
	}
}