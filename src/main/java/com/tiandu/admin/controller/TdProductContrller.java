package com.tiandu.admin.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.search.TdProductCriteria;
import com.tiandu.product.search.TdProductDescriptionCriteria;
import com.tiandu.product.search.TdProductTypeCriteria;
import com.tiandu.product.service.TdProductAttachmentService;
import com.tiandu.product.service.TdProductDescriptionService;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.service.TdProductStatService;
import com.tiandu.product.service.TdProductTypeService;

@Controller
@RequestMapping("/admin/product")
public class TdProductContrller extends BaseController{
	
	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	@Autowired
	private TdProductAttachmentService tdProductAttachmentService;
	
	@Autowired
	private TdProductDescriptionService tdProductDescriptionService; 
	
	@Autowired
	private TdProductStatService tdProductStatService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map){
		
		return "/admin/product/list";
	}

	@RequestMapping("/search")
	public String search(TdProductCriteria sc,HttpServletRequest req,ModelMap map)
	{
		map.addAttribute("productList", tdProductService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		
		return "/admin/product/listbody";
	}
	
	
	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletRequest req,ModelMap map)
	{
		TdProductTypeCriteria tsc = new TdProductTypeCriteria();
		tsc.setStatus((byte) 1);
		map.addAttribute("productTypeList", tdProductTypeService.findAll(tsc));
		
		if(null != id)
		{
			// 商品主要信息
			map.addAttribute("tdProduct", tdProductService.findOne(id));
			// 商品图片
			map.addAttribute("imgList", tdProductAttachmentService.findByProductId(id));
			
			TdProductDescriptionCriteria sc = new TdProductDescriptionCriteria();
			sc.setProductId(id);
			sc.setFlag(false);
			// 图文详情
			sc.setType(1);
			map.addAttribute("detail", tdProductDescriptionService.findByProductId(sc));
			// 包装配送
			sc.setType(2);
			map.addAttribute("packDetail", tdProductDescriptionService.findByProductId(sc));
			// 售后
			sc.setType(3);
			map.addAttribute("afterSale", tdProductDescriptionService.findByProductId(sc));
			
			map.addAttribute("productStat", tdProductStatService.findOne(id));
			
		}
		
		return "/admin/product/productform";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(TdProduct tdProduct,Integer[] attId,
					String detail, String  packDetail,
					String afterSale,
					HttpServletRequest req,ModelMap map)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 0);
		
		if(null != tdProduct)
		{
			if(null == tdProduct.getCreateTime())
			{
				tdProduct.setCreateTime(new Date());
			}
			TdUser user = getCurrentUser();
			if(null != user)
			{
				tdProduct.setBrandId(0);
				tdProduct.setDefaultSkuId(0);
				tdProduct.setSpecification(true);
				tdProduct.setUpdateBy(user.getUid());
				tdProduct.setUid(user.getUid());
			}
			tdProduct.setUpdateTime(new Date());
			tdProductService.save(tdProduct);
			
			// 修改展示图片
			if(null != attId)
			{
				for (Integer attaId : attId) {
					TdProductAttachment attachment = tdProductAttachmentService.findOne(attaId);
					attachment.setProductId(tdProduct.getId());
					tdProductAttachmentService.save(attachment);
				}
			}
			// 保存其他信息
			TdProductDescriptionCriteria sc = new TdProductDescriptionCriteria();
			sc.setProductId(tdProduct.getId());
			sc.setFlag(false);
			
			// 图文详情
			sc.setType(1);
			TdProductDescription imgDetail = tdProductDescriptionService.findByProductId(sc);
			if(null == imgDetail)
			{
				imgDetail = new TdProductDescription();
				imgDetail.setProductId(tdProduct.getId());
				imgDetail.setType((byte) 1);
			}
			imgDetail.setDescription(detail);
			imgDetail.setUpdateBy(user.getUid());
			imgDetail.setUpdateTime(new Date());
			
			// 包装配送
			sc.setType(2);
			TdProductDescription packdetail = tdProductDescriptionService.findByProductId(sc);
			if(null == packdetail)
			{
				packdetail = new TdProductDescription();
				packdetail.setProductId(tdProduct.getId());
				packdetail.setType((byte) 2);
			}
			packdetail.setDescription(packDetail);
			packdetail.setUpdateBy(user.getUid());
			packdetail.setUpdateTime(new Date());
			
			// 售后说明
			sc.setType(3);
			TdProductDescription afterSaleDetail = tdProductDescriptionService.findByProductId(sc);
			
			if(null == afterSaleDetail)
			{
				afterSaleDetail = new TdProductDescription();
				afterSaleDetail.setProductId(tdProduct.getId());
				afterSaleDetail.setType((byte) 3);
			}
			afterSaleDetail.setDescription(afterSale);
			afterSaleDetail.setUpdateBy(user.getUid());
			afterSaleDetail.setUpdateTime(new Date());
			
			tdProductDescriptionService.save(imgDetail);
			tdProductDescriptionService.save(packdetail);
			tdProductDescriptionService.save(afterSaleDetail);
			
			// 新增商品时添加统计表
			TdProductStat stat = tdProductStatService.findOne(tdProduct.getId());
			if(null == stat)
			{
				stat = new TdProductStat();
				stat.setBuyCount(0);
				stat.setBuyTimes(0);
				stat.setNegativeRate(0);
				stat.setNeutralRate(0);
				stat.setPositiveRate(0);
				stat.setProductId(tdProduct.getId());
				stat.setReviewCount(0);
				stat.setReviewScore(new BigDecimal(0));
				stat.setShowreviewCount(0);
				stat.setViewCount(0);
			}
			tdProductStatService.Insert(stat);
			
			res.put("code", 1);
		}
		
		
		return res;
	}
	
	/**
	 * 商品删除
	 * @author Max
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer id,HttpServletRequest req,ModelMap map)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != id)
		{
			// 删除图片
			tdProductAttachmentService.deleteByProductId(id);
			
			// 删除图文、包装、售后信息
			tdProductDescriptionService.deleteByProductId(id);
			
			// 删除统计表
			tdProductStatService.deleteByPrimaryKey(id);
			
			// 删除商品
			tdProductService.deleteByPrimaryKey(id);
			res.put("code", 1);
		}
		
		return res;
	}
	
	/**
	 * @author Max
	 * 图片移除
	 * 
	 */
	@RequestMapping(value="/deleteImg",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> imgDelete(Integer attId,HttpServletRequest req)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != attId)
		{
			tdProductAttachmentService.deleteByPrimaryKey(attId);
			res.put("code", 1);
		}
		return res;
	}
	
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "productId", required = false) Integer productId,
                        Model model) {
        if (null != productId) {
            model.addAttribute("tdProduct", tdProductService.findOne(productId));
        }
    }
	
}
