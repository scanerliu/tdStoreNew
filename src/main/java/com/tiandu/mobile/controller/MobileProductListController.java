package com.tiandu.mobile.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;
import com.tiandu.article.service.TdAdsenseService;
import com.tiandu.article.service.TdAdvertisementService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.service.TdProductTypeService;

/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月14日 下午2:13:55
 * 
 * 商品列表页，预购秒杀列表页，0元、10元购列表页
 * 
 */

@Controller
@RequestMapping("/mobile/product")
public class MobileProductListController extends BaseController{

	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	@Autowired
	private TdAdsenseService tdAdsenseService;
	
	@Autowired
	private TdAdvertisementService tdAdvertisementService;
	
	@RequestMapping("/list/{typeId}")
	public String productList(@PathVariable Integer typeId,
			HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		map.addAttribute("typeId", typeId);
		
		return "/mobile/product/list";
	}
	
	/**
	 * 
	 * @author Max
	 * 
	 * 查找商品
	 */
	@RequestMapping("/list/search")
	public String listSearch(TdProductCriteria sc ,HttpServletRequest req,ModelMap map)
	{
		map.addAttribute("productType", tdProductTypeService.findOne(sc.getTypeId()));
		
		sc.setOnshelf(true);
		sc.setStatus(Byte.valueOf("1"));
		if(null == sc.getOrderby())
		{
			sc.setOrderby(1);
		}
		sc.setKind((byte)1);
		sc.setOrderBy(sc.getOrderBySql());
		if(null!=sc.getTypeId() && sc.getTypeId()>0){
			sc.setTypeIdTree("["+sc.getTypeId()+"]");
		}
		
		List<TdProduct> productList = tdProductService.findBySearchCriteria(sc);
		map.addAttribute("productList", productList);
		map.addAttribute("sc", sc);
		//分类广告
		if(null!=sc.getTypeId() && sc.getTypeId()>0){
			// 轮播广告
			TdAdvertisementSearchCriteria asc = new TdAdvertisementSearchCriteria();
			asc.setCreateTime(new Date());
			asc.setEndTime(new Date());
			TdAdsense adsense = tdAdsenseService.findByName("触屏商品分类列表页轮播大图广告");
			if(null != adsense)
			{
				asc.setAdsId(adsense.getId());
				asc.setTypeId(sc.getTypeId());
				asc.setOrderBy("2");
				map.addAttribute("adList", tdAdvertisementService.findBySearchCriteria(asc));
			}
		}
		return "/mobile/product/list_body";
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
		sc.setStatus(Byte.valueOf("1"));
		sc.setTypeIdTree("["+typeId+"]");
		if(null == sc.getOrderby())
		{
			sc.setOrderby(1);
		}
		sc.setOrderBy(sc.getOrderBySql());
		sc.setPageNo(page);
		sc.setKind((byte)1);
		int count = tdProductService.getTotalPageCount(sc);
		if(count >= page){
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
	 * 加载跟多。。。
	 *  6 = 秒杀、5 = 预售、3 = 0元购、4 = 10元购
	 */
	@RequestMapping("/search/more")
	public String seckillMore(TdProductCriteria sc,
			Integer page,
			HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		sc.setOnshelf(true);
		
		// 0元购
		if(sc.getKind() == 3)
		{
			int count = tdProductService.getTotalPageCount(sc);
			if(count >= page){
				map.addAttribute("zeroList", tdProductService.findBySearchCriteria(sc));
			}
			return "/mobile/product/zero_list_more";
		}
		// 10元购
		else if(sc.getKind() == 4)
		{
			int count = tdProductService.getTotalPageCount(sc);
			if(count >= page){
				map.addAttribute("tenList", tdProductService.findBySearchCriteria(sc));
			}
			return "/mobile/product/ten_list_more";
		}
		// 预售
		else if(sc.getKind() == 5)
		{
			sc.setStartTime(new Date());
			sc.setEndTime(new Date());
			int count = tdProductService.getTotalPageCount(sc);
			if(count >= page){
				map.addAttribute("presellList", tdProductService.findBySearchCriteria(sc));
			}
			return "/mobile/product/persell_list_more";
		}
		// 秒杀
		sc.setStartTime(new Date());
		sc.setEndTime(new Date());
		if(sc.getPageNo()-1==page){
			map.addAttribute("killList", tdProductService.findBySearchCriteria(sc));
		}
		return "/mobile/product/kill_list_more";
	}
	
	// 秒杀列表
	@RequestMapping("/zero")
	public String zero(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		sc.setOnshelf(true);
		
		// 秒杀
		sc.setKind((byte)3);
		map.addAttribute("zeroList", tdProductService.findBySearchCriteria(sc));
		
		// 预售
		sc.setKind((byte)4);
		map.addAttribute("tenList", tdProductService.findBySearchCriteria(sc));
		
		return "/mobile/product/zero_list";
	}
	
	// 新品推荐
	@RequestMapping("/new")
	public String newProductList(HttpServletRequest req,ModelMap map){
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/mobile/product/new_list";
	}
	
	// 新品推荐查找
	@RequestMapping("/new/search")
	public String newSearch(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setOnshelf(true);
		if(null == sc.getOrderby())
		{
			sc.setOrderby(1);
		}
		
		sc.setOrderBy(sc.getOrderBySql());
		sc.setNewRecommend(1);
		map.addAttribute("new_list", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		
		return "/mobile/product/new_listbody";
	}
	
	// 新品推荐加载更多。。。
	@RequestMapping("/new/more")
	public String newListMore(
			TdProductCriteria sc,Integer page,
			HttpServletRequest req,ModelMap map)
	{
		
		sc.setOnshelf(true);
		if(null == sc.getOrderby())
		{
			sc.setOrderby(1);
		}
		sc.setOrderBy(sc.getOrderBySql());
		sc.setPageNo(page);
		sc.setNewRecommend(1);
		
		int count = tdProductService.getTotalPageCount(sc);
		if(count >= page)
		{
			map.addAttribute("new_list", tdProductService.findBySearchCriteria(sc));
			map.addAttribute("sc", sc);
		}
		
		return "/mobile/product/new_list_more";
	}
	
	//积分兑换列表
	@RequestMapping("/point/list")
	public String point(HttpServletRequest request, HttpServletResponse response,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/mobile/product/point_list";
	}
	
	//积分兑换列表内容
	@RequestMapping("/point/search")
	public String pointsearch(TdProductCriteria sc,HttpServletRequest request, HttpServletResponse response, ModelMap map)
	{
		sc.setOnshelf(true);
		sc.setStatus(Byte.valueOf("1"));
		sc.setStatus(Byte.valueOf("1"));		
		map.addAttribute("pointList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/mobile/product/point_listbody";
	}
	
}
