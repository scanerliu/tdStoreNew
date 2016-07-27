package com.tiandu.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.entity.TdProductPackageItem;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.search.TdProductDescriptionCriteria;
import com.tiandu.product.search.TdProductPackageItemSearchCriteria;
import com.tiandu.product.search.TdProductSkuCriteria;
import com.tiandu.product.service.TdBrandService;
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
	
	@Autowired
	private TdProductPackageItemService tdProductPackageItemService;
	
	private final Logger logger = Logger.getLogger(getClass());
	
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
			
			TdProductPackageItemSearchCriteria psc = new TdProductPackageItemSearchCriteria();
			psc.setFlag(false);
			psc.setProductId(productPackage.getId());
			List<TdProductPackageItem> ppiList = tdProductPackageItemService.findBySearchCriteria(psc);
			String skuIdStr = "";
			for(TdProductPackageItem ppi : ppiList){
				Integer quantity =  ppi.getQuantity();
				if(quantity != null){
					for(int i = 0; i < quantity; i ++){
						skuIdStr += ppi.getSkuId() + ",";
					}
				}
			}
			map.addAttribute("skuIdStr", skuIdStr);
			
			
		}
		
		map.addAttribute("tdProduct", productPackage);
		
		return "/admin/productPackage/productPackageEdit";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest request, ModelMap modelMap, TdProduct product, String detail, String dispatch, String service, String attachmentUrls, String skuIdStr){
		Map<String, String> res = new HashMap<>();
		try{
			Date now = new Date();
			TdUser currentUser = this.getCurrentUser();
			product.setKind(Byte.valueOf("2"));
			product.setUid(currentUser.getUid());
			if(product.getId() == null){
				product.setCreateTime(now);				
			}
			product.setUpdateBy(currentUser.getUid());
			product.setUpdateTime(now);
			tdProductService.save(product);
			TdProductDescription detailDesc = new TdProductDescription();
			TdProductDescription dispatchDesc = new TdProductDescription();
			TdProductDescription serviceDesc = new TdProductDescription();
			
			
			tdProductDescriptionService.deleteByProductId(product.getId());
			
			detailDesc.setUpdateBy(currentUser.getUid());
			detailDesc.setUpdateTime(now);
			detailDesc.setType(Byte.valueOf("1"));
			detailDesc.setProductId(product.getId());
			detailDesc.setDescription(detail);
			tdProductDescriptionService.save(detailDesc);
			
			dispatchDesc.setUpdateBy(currentUser.getUid());
			dispatchDesc.setUpdateTime(now);
			dispatchDesc.setType(Byte.valueOf("2"));
			dispatchDesc.setProductId(product.getId());
			dispatchDesc.setDescription(dispatch);
			tdProductDescriptionService.save(dispatchDesc);
			
			serviceDesc.setUpdateBy(currentUser.getUid());
			serviceDesc.setUpdateTime(now);
			serviceDesc.setType(Byte.valueOf("3"));
			serviceDesc.setProductId(product.getId());
			serviceDesc.setDescription(service);
			tdProductDescriptionService.save(serviceDesc);
			
			tdProductAttachmentService.deleteByProductId(product.getId());
			
			String[] urlArray = attachmentUrls.split(":");
			for(String url : urlArray){
				String fileName = url.substring(url.lastIndexOf("/") + 1);
				TdProductAttachment tpa = new TdProductAttachment();
				tpa.setFilename(fileName);
				tpa.setAttachment(url);
				tpa.setProductId(product.getId());
				tdProductAttachmentService.save(tpa);
			}
			
			tdProductPackageItemService.deleteByProductId(product.getId());
			
			List<TdProductSku> skuList = new ArrayList<>();
			String[] skuIdArray = {};
			if(!skuIdStr.equals("")){
				skuIdArray = skuIdStr.split(",");				
			}
			for(String skuId : skuIdArray){
				TdProductSku pk = tdProductSkuService.findOneWithProduct(Integer.parseInt(skuId));
				skuList.add(pk);
			}
			List<TdProductSku> uniqueSkuList = uniqueSkuList(skuList);
			for(TdProductSku sku : uniqueSkuList){
				TdProductPackageItem ppi = new TdProductPackageItem();
				ppi.setProductId(product.getId());
				ppi.setSkuId(sku.getId());
				ppi.setQuantity(countSkuQuantity(sku.getId(), skuList));
				ppi.setPreprouductId(sku.getProductId());
				ppi.setProductName(sku.getProduct().getName());
				ppi.setProductImage(sku.getProduct().getImageUrl());
				ppi.setPrice(sku.getProduct().getPrice());
				tdProductPackageItemService.save(ppi);
			}
			
		}catch(Exception e){
			res.put("code", "0");
			res.put("msg", "商品包保存失败。");
			logger.error("商品包保存失败！");
			e.printStackTrace();
			return res;
		}
		res.put("code", "1");
		res.put("msg", "商品包保存成功。");
		return res;
	}
	
	
	@RequestMapping("/skuList")
	public String skuList(HttpServletRequest request, ModelMap modelMap, Integer productId){
		return "/admin/productPackage/skuList";
	}
	
	@RequestMapping("/skuSearch")
	public String skuSearch(TdProductSkuCriteria sc, HttpServletRequest request, ModelMap modelMap){
		List<TdProductSku> skuList = tdProductSkuService.findBySearchCriteria(sc);
		modelMap.addAttribute("skuList", skuList);
		modelMap.addAttribute("sc", sc);
		return "/admin/productPackage/skuListBody";
	}
	
	@RequestMapping("/skuShow")
	public String skuShow(String skuIdStr, HttpServletRequest request, ModelMap modelMap){
		String[] skuIdArray = skuIdStr.split(",");
		List<TdProductSku> skuList = new ArrayList<>();
		for(String skuId : skuIdArray){
			if(!skuId.equals("")){
				TdProductSku sku = tdProductSkuService.findOneWithProduct(Integer.parseInt(skuId));				
				skuList.add(sku);
			}
		}
		modelMap.addAttribute("selectedSku", skuList);
		return "/admin/productPackage/skuListTemplate";
	}
	
	private Integer countSkuQuantity(Integer id, List<TdProductSku> skuList){
		Integer quantity = 0;
		for(TdProductSku sku : skuList){
			if(sku.getId().equals(id)){
				quantity ++;
			}
		}
		return quantity;
	}
	
	private List<TdProductSku> uniqueSkuList(List<TdProductSku> skuList){
		List<TdProductSku> posList = new ArrayList<>();
		Set<Integer> idSet = new HashSet<>();
		for(TdProductSku sku : skuList){
			idSet.add(sku.getId());
		}
		for(Integer id : idSet){
			for(TdProductSku u : skuList){
				if(u.getId().equals(id)){
					posList.add(u);
					break;
				}
			}
		}
		return posList;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (null != idStr && !idStr.equals("")) {
			try {
				Integer productId = Integer.parseInt(idStr);
				tdProductDescriptionService.deleteByProductId(productId);
				tdProductAttachmentService.deleteByProductId(productId);
				tdProductPackageItemService.deleteByProductId(productId);
				tdProductService.deleteByPrimaryKey(productId);
			} catch (Exception e) {
				logger.error("商品包删除失败，错误信息:" + e);
				e.printStackTrace();
				res.put("code", "0");
				res.put("msg", "商品包删除失败。");
				return res;
			}
		} 
		res.put("code", "1");
		res.put("msg", "商品包删除成功。");
		return res;
	}

	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> batchDelete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		String[] ids = idStr.split(",");
		try {
			for (String id : ids) {
				this.delete(id, request, response);
			}
		} catch (Exception e) {
			logger.error("商品包删除批量失败, 错误信息:" + e);
			e.printStackTrace();
			res.put("code", "0");
			res.put("msg", "商品包批量删除失败");
			return res;
		}
		res.put("code", "1");
		res.put("msg", "商品包批量删除成功。");
		return res;
	}
	
}
