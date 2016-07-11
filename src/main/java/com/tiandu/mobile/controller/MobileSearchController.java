package com.tiandu.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.product.search.TdProductCriteria;

/**
 * 搜索
 * 
 * @author Max
 *
 */
@Controller
@RequestMapping("/mobile")
public class MobileSearchController extends BaseController{

	
	@RequestMapping("/search")
	public String toSearch(HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		
		return "/mobile/search/search";
	}
	
	@RequestMapping("/search/list")
	public String searchList(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		
		
		return "";
	}
	
}
