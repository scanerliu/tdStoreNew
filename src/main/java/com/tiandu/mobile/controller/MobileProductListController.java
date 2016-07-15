package com.tiandu.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.service.TdProductTypeService;

/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月14日 下午2:13:55
 */

@Controller
@RequestMapping("/mobile/product")
public class MobileProductListController extends BaseController{

	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	@RequestMapping("/list/{lister}")
	public String productList(@PathVariable String lister,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		if(null == lister || "".equals(lister))
		{
			return "";
		}
		
		String[] strs = lister.split("-");
		
		if(strs.length <= 0 )
		{
			return "";
		}
		
		// 分类ID
		Integer typeId = null;
		
		typeId = Integer.parseInt(strs[0]);
		
		map.addAttribute("productType", tdProductTypeService.findOne(typeId));
		map.addAttribute("typeId", typeId);
		
		TdProductCriteria sc = new TdProductCriteria();
		sc.setTypeId(typeId);
		sc.setOnshelf(true);
		
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		
		return "/mobile/product/list";
	}
	
	
	
	
}
