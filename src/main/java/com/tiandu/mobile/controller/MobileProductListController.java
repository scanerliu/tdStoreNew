package com.tiandu.mobile.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.product.entity.TdProduct;
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
	
	@RequestMapping("/list/{typeId}")
	public String productList(@PathVariable Integer typeId,
			TdProductCriteria sc,
			HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		map.addAttribute("productType", tdProductTypeService.findOne(typeId));
		map.addAttribute("typeId", typeId);
		
		sc.setTypeId(typeId);
		sc.setOnshelf(true);
		if(null == sc.getOrderby())
		{
			sc.setOrderby(1);
		}
		sc.setKind((byte)1);
		sc.setOrderBy(sc.getOrderBySql());
		
		List<TdProduct> productList = tdProductService.findBySearchCriteria(sc);
		map.addAttribute("productList", productList);
		map.addAttribute("sc", sc);
		return "/mobile/product/list";
	}
	
	/**
	 * 
	 * @author Max
	 * 加载更多。。。
	 * 
	 */
	@RequestMapping("/list/more/{typeId}")
	public String productListMore(@PathVariable Integer typeId,
			TdProductCriteria sc,Integer page,
			HttpServletRequest req,ModelMap map)
	{
		
		sc.setTypeId(typeId);
		sc.setOnshelf(true);
		if(null == sc.getOrderby())
		{
			sc.setOrderby(1);
		}
		sc.setOrderBy(sc.getOrderBySql());
		sc.setPageNo(page);
		sc.setKind((byte)1);
		if(sc.getPageNo()-1==page)
		{
			map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
			map.addAttribute("sc", sc);
		}
		
		return "/mobile/product/list_more";
	}
	
	/**
	 * 
	 * @author Max
	 * 限时秒杀、预售
	 */
	@RequestMapping("/seckill")
	public String seckill(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		sc.setStartTime(new Date());
		sc.setEndTime(new Date());
		sc.setOnshelf(true);
		
		// 秒杀
		sc.setKind((byte)6);
		map.addAttribute("killList", tdProductService.findBySearchCriteria(sc));
		
		// 预售
		sc.setKind((byte)5);
		map.addAttribute("presellList", tdProductService.findBySearchCriteria(sc));
		
		return "/mobile/product/kill_list";
	}
	
	/**
	 * 
	 * @author Max
	 * 更多秒杀、预售
	 */
	@RequestMapping("/seckill/more")
	public String seckillMore(TdProductCriteria sc,
			Integer page,
			HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		sc.setStartTime(new Date());
		sc.setEndTime(new Date());
		sc.setOnshelf(true);
		
		// 秒杀
		if(sc.getPageNo()-1==page){
			map.addAttribute("killList", tdProductService.findBySearchCriteria(sc));
		}
		// 预售
		if(sc.getPageNo()-1==page){
			map.addAttribute("presellList", tdProductService.findBySearchCriteria(sc));
		}
		
		if(sc.getKind() == 5)
		{
			return "/mobile/product/persell_list_more";
		}
		return "/mobile/product/kill_list_more";
	}
	
}
