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
		warning("请填写收货人的姓名");
		return;
	}
	
	if(!isAllLegal(receiverName)){
		warning("收货人信息不能输入除-()#,以外的特殊字符");
		return;
	}

	if (TdIsNull(receiverMobile)) {
		warning("请填写收货人的联系电话");
		return;
	}

	if(!/^1\d{10}$/.test(receiverMobile)){
		warning("请输入正确的手机号码");
		return;
	}
	
	if(TdIsNull(provinceId) || TdIsNull(cityId) ||(districtChildNo > 1 && TdIsNull(districtId)))
	{
		warning("请填写所在区域");
		return;
	}
	
	if (TdIsNull(detailAddress)) {
		warning("请填写详细地址");
		return;
	}
	
	if (detailAddress.length > 128) {
		warning("请控制详细地址字数在128");
		return;
	}

	if(!isAllLegal(detailAddress)){
		warning("详细地址不能输入除-()#,以外的特殊字符");
		return;
	}

	// 发送异步请求
	$.ajax({
		url : basePath + "/mobile/user/shoppingAddressSave",
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
			warning("添加失败")
		},
		success : function(res) {
			$('.outerbox').fadeIn(400,function(){
				$('.outerbox').fadeOut();
				window.location.href=basePath + "/mobile/user/shoppingAddress";
			});
		}
	});
}

