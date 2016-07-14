function subAgent()
{
	var typeId = $("#typeId").val();
	var regionId = $("#regionId").val();
	var disLevel = $("#disLevel").val();
	var level = $("#level").val();
	var agentId = $("#agentid").val();
	
	// 分类ID为空，
	if(undefined == typeId || typeId == "")
	{
		alert("请选择代理类别");
		return ;
	}
	if(null == agentId || null == level)
	{
		alert("参数错误");
		return ;
	}
	
	// 非全国代理 未选择地区提示
	if(level != 1 )
	{
		if(null == regionId || regionId == 0)
		{
			alert("请选择代理地区");
			return ;
		}
		// 区县级代理，只选择一级地区提示
		if(level != 2)
		{
			if(disLevel == 1)
			{
				alert("请选择下级地区");
				return ;
			}
		}
	}
	
	$("#sub_form").submit();
	
}