package com.tiandu.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.service.TdProductService;

/**
 * 搜索
 * 
 * @author Max
 *
 */
@Controller
@RequestMapping("/mobile")
public class MobileSearchController extends BaseController{

	@Autowired
	private TdProductService tdProductService;
	
	@RequestMapping("/search")
	public String toSearch(HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/mobile/search/search";
	}
	
	
	
	@RequestMapping("/search/list")
	public String searchList(String keyword,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		map.addAttribute("keyword", keyword);
		return "/mobile/search/list";
	}
	
	@RequestMapping("/search/search")
	public String search(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setOnshelf(true);
		if(null == sc.getOrderby())
		{
			sc.setOrderby(1);
		}
		sc.setOrderBy(sc.getOrderBySql());
		
		List<TdProduct> productList = tdProductService.findBySearchCriteria(sc);
		map.addAttribute("productList", productList);
		map.addAttribute("sc", sc);
		
		return "/mobile/search/list_body";
	}
	
	@RequestMapping("/search/more")
	public String searchMore(TdProductCriteria sc,Integer page,HttpServletRequest req,ModelMap map)
	{
		sc.setOnshelf(true);
		if(null == sc.getOrderby())
		{
			sc.setOrderby(1);
		}
		sc.setOrderBy(sc.getOrderBySql());
		sc.setPageNo(page);
		int count = tdProductService.getTotalPageCount(sc);
		if(count >= page){
			map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
			map.addAttribute("sc", sc);
		}
		
		return "/mobile/search/list_more";
	}
}
