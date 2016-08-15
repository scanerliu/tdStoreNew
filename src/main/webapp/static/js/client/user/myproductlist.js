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
function addZeroProduct(){
	var url = basePath + "/user/editproduct";
	var loadData = {"id":id,"ktype":2};
	$("#formdiv").loading().load(url,loadData);
	showForm();
}
/**
 * 改变商品分类
 */
function changeType(obj){
	var typeId = $(obj).val();
	if(typeId!=""){
		var url = basePath + "/user/producttypeattributes";
		var loadData = {"id":typeId};
		$("#attrList").loading().load(url,loadData);
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
				attributes.push($(this).attr("name")+"_"+$(this).attr("value"));
			});
			idkey.push(attributes);
		}
	});
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
			$.each(result,function(i,o){
				if($.inArray(o, preskukeys)<0){
					var keystr = getkeystr(o);
					var specifications = getspecifications(o);
					html+='<tr tid="'+o+'" class="skuspec"><td>'+keystr+'</td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].skuCode" value=""></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].supplierPrice" value=""></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].salesPrice" value=""></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].marketPrice" value=""></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].highPrice" value=""></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].lowPrice" value=""></td>';
					html+='<td><input type="text" name="skuList['+__skuinex+'].stock" value=""></td>';
					html+='<input type="hidden" name="skuList['+__skuinex+'].specifications" value="'+o+'">';
					html+='</tr>';
					__skuinex++;
				}
			});
			$("#skuTable").append(html);
		}
	}
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

function saveProduct(){
	
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
