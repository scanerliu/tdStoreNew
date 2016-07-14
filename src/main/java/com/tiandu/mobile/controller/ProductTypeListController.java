package com.tiandu.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class ProductTypeListController extends BaseController{
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		TdProductTypeCriteria sc = new TdProductTypeCriteria();
		sc.setStatus((byte)1);
		sc.setOrderBy("2");
		map.addAttribute("typeList", tdProductTypeService.findAll(sc));
		
		return "/mobile/product/typelist";
	}

}
