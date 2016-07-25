package com.tiandu.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.search.TdProductDescriptionCriteria;
import com.tiandu.product.search.TdProductSkuCriteria;
import com.tiandu.product.service.TdBrandService;
import com.tiandu.product.service.TdProductAttachmentService;
import com.tiandu.product.service.TdProductAttributeOptionService;
import com.tiandu.product.service.TdProductAttributeService;
import com.tiandu.product.service.TdProductDescriptionService;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.service.TdProductSkuService;
import com.tiandu.product.service.TdProductStatService;
import com.tiandu.product.service.TdProductTypeAttributeService;
import com.tiandu.product.service.TdProductTypeService;

@Controller
@RequestMapping("/admin/productPackage")
public class ProductPackageController extends BaseController{
	
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
	
	@Autowired
	private TdBrandService tdBrandService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,ModelMap map){
		return "/admin/productPackage/list";
	}
	
	@RequestMapping("/search")
	public String search(TdProductCriteria sc,HttpServletRequest request, ModelMap modelMap){
		sc.setKind(Byte.valueOf("2"));
		modelMap.addAttribute("productPakcageList", tdProductService.findBySearchCriteria(sc));
		modelMap.addAttribute("sc", sc);
		
		return "/admin/productPackage/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest req, ModelMap map){
		TdProduct productPackage = new TdProduct();
		if(id != 0){
			productPackage = tdProductService.findOne(id);
			List<TdProductAttachment> attachmentList = tdProductAttachmentService.findByProductId(id);
			map.addAttribute("attachmentList", attachmentList);
			TdProductDescriptionCriteria sc = new TdProductDescriptionCriteria();
			sc.setProductId(id);
			List<TdProductDescription> productDescriptionList = tdProductDescriptionService.findBySearchCriteria(sc);
			TdProductDescription detail = null; //商品详情 1
			TdProductDescription dispatch = null; //包装配送 2
			TdProductDescription service = null; //售后服务 3
			for(TdProductDescription desc : productDescriptionList){
				if(desc.getType().equals(Byte.valueOf("1"))){
					detail = desc;
				}
				if(desc.getType().equals(Byte.valueOf("2"))){
					dispatch = desc;
				}
				if(desc.getType().equals(Byte.valueOf("3"))){
					service = desc;
				}
			}
			map.addAttribute("detail", detail);
			map.addAttribute("dispatch", dispatch);
			map.addAttribute("service", service);
			TdProductStat productStat = tdProductStatService.findOne(id);
			map.addAttribute("productStat", productStat);
			
		}
		map.addAttribute("tdProduct", productPackage);
		
		return "/admin/productPackage/productPackageEdit";
	}
	
	
	@RequestMapping("/skuList")
	public String skuList(TdProductSkuCriteria sc, HttpServletRequest request, ModelMap modelMap, Integer productId){
		List<TdProductSku> skuList = tdProductSkuService.findBySearchCriteria(sc);
		modelMap.addAttribute("skuList", skuList);
		return "/admin/productPackage/skuList";
	}
}
