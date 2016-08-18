function isLegal(str){
	if(str >= '0' && str <= '9')return true;
	if(str >= 'a' && str <= 'z')return true;
	if(str >= 'A' && str <= 'Z')return true;
	var regEx=new RegExp("[-()#, ，（）]");
	if (regEx.test(str))return true;
	var reg = /^[\u4e00-\u9fa5]+$/i;
	if (reg.test(str))return true;
	return false;
}
function isAllLegal(str1){
	if(str1=="" || str1==undefined)return false;
	for (i=0; i<str1.length; i++) {
		if (!isLegal(str1.charAt(i))){
			return false;
		}
	}
	return true;
}

function TdIsNull(object)
{
	if(null==object||"" == object)
	{
		return true;
	}
	return false;
}

function save(){
	var addressId = $("#addressId").val();
	var receiverName = $("#receiverName").val();
	var receiverMobile = $("#receiverMobile").val();
	var detailAddress = $("#detailAddress").val();
	var provinceId = $("#prov").val();
	var cityId = $("#city").val();
	var districtId= $("#dist").val();
	
	var district= $("#dist").find("option:selected").text();
	district = district==undefined ? "": district;
	var province = $("#prov").find("option:selected").text();
	var city = $("#city").find("option:selected").text();
	var districtChildNo = $("#dist").children().length;
	var regionId = districtChildNo > 1 ? districtId:cityId;
	var fullAddress = province + city + district + detailAddress;
	if (TdIsNull(receiverName)) {
		alert("请填写收货人的姓名");
		return;
	}
	
	if(!isAllLegal(receiverName)){
		alert("收货人信息不能输入除-()#,以外的特殊字符");
		return;
	}

	if (TdIsNull(receiverMobile)) {
		alert("请填写收货人的联系电话");
		return;
	}

	if(!/^1\d{10}$/.test(receiverMobile)){
		alert("请输入正确的手机号码");
		return;
	}
	
	if(TdIsNull(provinceId) || TdIsNull(cityId) ||(districtChildNo > 1 && TdIsNull(districtId)))
	{
		alert("请填写所在区域");
		return;
	}
	
	if (TdIsNull(detailAddress)) {
		alert("请填写详细地址");
		return;
	}
	
	if (detailAddress.length > 128) {
		alert("请控制详细地址字数在128");
		return;
	}

	if(!isAllLegal(detailAddress)){
		alert("详细地址不能输入除-()#,以外的特殊字符");
		return;
	}

	// 发送异步请求
	$.ajax({
		url : basePath + "/user/shoppingAddressSave",
		type : "post",
		timeout : 10000,
		data : {
			name : receiverName,
			telphone : receiverMobile,
			address : detailAddress,
			isDefault : onOff,
			regionId : regionId,
			fullAddress : fullAddress,
			id:addressId
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 关闭等待图标
			alert("添加失败")
		},
		success : function(res) {
			$('.outerbox').fadeIn(400,function(){
				$('.outerbox').fadeOut();
				window.location.href=basePath + "/user/shoppingAddress";
			});
		}
	});
}

/**
 * 查询收货地址
 * @param flag
 */
function searchAddress(flag){
	var url = basePath+"/user/sarchshoppingaddress";
	var loadData = null;
	$("#results").loading().load(url,null);
}
//修改收货地址
function editAddress(id){
	var url = basePath + "/user/editshoppingaddress";
	var loadData = {"addressId":id};
	$("#formdiv").loading().load(url,loadData);
	showForm();
}
//删除收货地址
function delAddress(id){
	var url = basePath + "/user/shoppingAddressDelete";
	var postData = {"addressId":id};
	$.post(url,postData,delAddressCallback,"text");
}
//删除回调函数
function delAddressCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		searchAddress(false);
	}else{
		alert(result.msg);
	}
}
//设置默认地址
function defaultAddress(id){
	var url = basePath + "/user/defaultshoppingaddress";
	var postData = {"addressId":id};
	$.post(url,postData,delAddressCallback,"text");
}
//显示form页
function showForm(){
	$("#results").hide();
	$("#formdiv").show();
}
//显示列表页
function backtoList(){
	$("#results").show();
	$("#formdiv").hide();
}