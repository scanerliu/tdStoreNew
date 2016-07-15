package com.tiandu.mobile.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;
import com.tiandu.order.service.TdShoppingcartItemService;
import com.tiandu.order.vo.ShoppingcartVO;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.service.TdProductAttachmentService;
import com.tiandu.product.service.TdProductAttributeOptionService;
import com.tiandu.product.service.TdProductAttributeService;
import com.tiandu.product.service.TdProductDescriptionService;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.service.TdProductSkuService;
import com.tiandu.product.service.TdProductStatService;
import com.tiandu.product.service.TdProductTypeAttributeService;
import com.tiandu.product.service.TdProductTypeService;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/mobile/product")
public class MProductController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	TdProductAttributeOptionService tdProductAttributeOptionService; 
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	@Autowired
	private TdProductAttachmentService tdProductAttachmentService;
	
	@Autowired
	private TdProductDescriptionService tdProductDescriptionService; 
	
	@Autowired
	TdProductAttributeService tdProductAttributeService; 
	
	@Autowired
	TdProductTypeAttributeService tdProductTypeAttributeService; 
	
	@Autowired
	private TdProductStatService tdProductStatService;
	
	@Autowired
	TdProductSkuService tdProductSkuService; 
	
	/*
	 * 商品列表页
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    return "/mobile/product/productlist";
	}
	/*
	 * 商品列表数据页
	 */
	@RequestMapping("/search")
	public String search(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setKind(ConstantsUtils.PRODUCT_KIND_COMMON);
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/mobile/product/productlistbody";
	}
	
	/*
	 * 商品列表数据页
	 */
	@RequestMapping("/item{id}")
	public String item(@PathVariable("id") Integer id,HttpServletRequest req,ModelMap map)
	{
		TdProduct product  = tdProductService.findOne(id);
		//货品
		List<TdProductSku> skuList = tdProductSkuService.findByProductId(id);
		if(skuList.size()>0){
			//商品类型规格
			List<TdProductTypeAttribute> taList = tdProductTypeAttributeService.findByTypeIdWithOptions(product.getTypeId());
			if(taList.size()>0){
				//匹配货品库存状态
				tdProductService.matchSkuStockWithAttributeOption(skuList,taList);
			}
			map.addAttribute("taList", taList);
		}
		map.addAttribute("product", product);
		return "/mobile/product/productdetail";
	}
	
	

}
