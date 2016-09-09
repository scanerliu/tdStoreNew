package com.tiandu.client.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tiandu.custom.entity.TdUserCollection;
import com.tiandu.custom.search.TdUserCollectionCriteria;
import com.tiandu.custom.service.TdUserCollectionService;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.entity.TdProductPackageItem;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.search.TdProductDescriptionCriteria;
import com.tiandu.product.search.TdProductPackageItemSearchCriteria;
import com.tiandu.product.service.TdProductAttachmentService;
import com.tiandu.product.service.TdProductAttributeOptionService;
import com.tiandu.product.service.TdProductAttributeService;
import com.tiandu.product.service.TdProductDescriptionService;
import com.tiandu.product.service.TdProductPackageItemService;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.service.TdProductSkuService;
import com.tiandu.product.service.TdProductStatService;
import com.tiandu.product.service.TdProductTypeAttributeService;
import com.tiandu.product.service.TdProductTypeService;

/**
 * 礼品包
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/package")
public class CProductPackageController extends BaseController {
	
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
	private TdProductTypeAttributeService tdProductTypeAttributeService; 
	
	@Autowired
	private TdProductSkuService tdProductSkuService;
	
	@Autowired
	private TdDistrictService tdDistrictService; 
	
	@Autowired
	private TdUserCollectionService tdUserCollectionService;
	@Autowired
	private TdProductPackageItemService tdProductPackageItemService;
	
	/*
	 * 商品包列表页
	 */
	@RequestMapping("/list")
	public String list(TdProductCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("sc", sc);
		// 系统配置
		modelMap.addAttribute("system", getSystem());
	    return "/client/package/list";
	}
	/*
	 * 商品列表数据页
	 */
	@RequestMapping("/search")
	public String search(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setKind(ConstantsUtils.PRODUCT_KIND_PACKAGE);
		sc.setPageSize(12);
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/package/listbody";
	}
	
	/*
	 * 商品详情数据页
	 */
	@RequestMapping("/item{id}")
	public String item(@PathVariable("id") Integer id,HttpServletRequest req,ModelMap map)
	{
		TdProduct product  = tdProductService.findOne(id);
		if(product.getOnshelf()==false||!product.getStatus().equals(Byte.valueOf("1")) || !ConstantsUtils.PRODUCT_KIND_PACKAGE.equals(product.getKind())){
			return "redirect:404";
		}
		//商品包
		TdProductPackageItemSearchCriteria psc = new TdProductPackageItemSearchCriteria();
		psc.setFlag(false);
		psc.setProductId(id);
		List<TdProductPackageItem> itemList = tdProductPackageItemService.findBySearchCriteria(psc);
		//商品图片
		List<TdProductAttachment> attachmentList = tdProductAttachmentService.findByProductId(id);
		
		map.addAttribute("product", product);
		map.addAttribute("itemList", itemList);
		map.addAttribute("attachmentList", attachmentList);
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/client/package/detail";
	}
	/*
	 * 商品图文详情页
	 */
	@RequestMapping("/describe/{type}/{id}")
	public String describe(@PathVariable("id") Integer id,@PathVariable("type") Integer type,HttpServletRequest req,ModelMap map)
	{
		TdProductDescriptionCriteria sc = new TdProductDescriptionCriteria();
		sc.setProductId(id);
		sc.setType(type);
		List<TdProductDescription>  descList  = tdProductDescriptionService.findBySearchCriteria(sc);
		//图文说明
		TdProductDescription productdesc = new TdProductDescription();
		//配送说明
		TdProductDescription delivedesc = new TdProductDescription();
		//售后说明
		TdProductDescription servicedesc = new TdProductDescription();
		for(TdProductDescription desc : descList){
			if(Byte.valueOf("1").equals(desc.getType())){
				productdesc = desc;
			}else if(Byte.valueOf("2").equals(desc.getType())){
				delivedesc = desc;
			}else if(Byte.valueOf("3").equals(desc.getType())){
				servicedesc = desc;
			}
		}
		map.addAttribute("productdesc", productdesc);
		map.addAttribute("delivedesc", delivedesc);
		map.addAttribute("servicedesc", servicedesc);
		map.addAttribute("sc", sc);
		return "/client/package/describe";
	}
	
	/*
	 * 商品图文详情页
	 */
	@RequestMapping("/describe/{id}")
	public String describe(@PathVariable("id") Integer id,HttpServletRequest req,ModelMap map)
	{
		TdProductDescriptionCriteria sc = new TdProductDescriptionCriteria();
		sc.setProductId(id);
		List<TdProductDescription>  descList  = tdProductDescriptionService.findBySearchCriteria(sc);
		//图文说明
		TdProductDescription productdesc = new TdProductDescription();
		//配送说明
		TdProductDescription delivedesc = new TdProductDescription();
		//售后说明
		TdProductDescription servicedesc = new TdProductDescription();
		for(TdProductDescription desc : descList){
			if(Byte.valueOf("1").equals(desc.getType())){
				productdesc = desc;
			}else if(Byte.valueOf("2").equals(desc.getType())){
				delivedesc = desc;
			}else if(Byte.valueOf("3").equals(desc.getType())){
				servicedesc = desc;
			}
		}
		
		map.addAttribute("productdesc", productdesc);
		map.addAttribute("delivedesc", delivedesc);
		map.addAttribute("servicedesc", servicedesc);
		return "/client/package/describe";
	}
	

}
