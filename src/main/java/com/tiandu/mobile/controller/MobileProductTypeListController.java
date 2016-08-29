package com.tiandu.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiandu.common.controller.BaseController;
import com.tiandu.product.search.TdProductTypeCriteria;
import com.tiandu.product.service.TdProductTypeService;
/**
 * 分类列表
 * 
 * @author Max
 *
 */
@Controller
@RequestMapping("/mobile/productType")
public class MobileProductTypeListController extends BaseController{
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		map.addAttribute("typeList", tdProductTypeService.findByParentId(0));
		
		return "/mobile/product/typelist";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String searchSubType(Integer typeId,HttpServletRequest req,ModelMap map){
		if(null != typeId){
			map.addAttribute("subtypeList", tdProductTypeService.findByParentId(typeId));
		}
		return "/mobile/product/sub_type_list";
	}

}
