package com.tiandu.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdJointOrder;
import com.tiandu.order.vo.OrderForm;
import com.tiandu.order.vo.ShoppingcartVO;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.service.TdProductAttachmentService;
import com.tiandu.product.service.TdProductService;
import com.tiandu.system.utils.ConfigUtil;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/mobile/imageorder")
public class MImageOrderController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	private TdProductAttachmentService tdProductAttachmentService;
	
	@Autowired
	private ConfigUtil configUtil;
	/*
	 * 图片下单页面
	 */
	@RequestMapping("/list")
	public String list(Integer productId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdProduct product = null;
		//获取商品信息
		if(null!=productId && productId>0){
			product = tdProductService.findOne(productId);
		}
		
		if(null==product || !product.getUid().equals(currUser.getUid())){
			return "redirect:404";
		}
		Integer imageprice = configUtil.getImageProcessingPrice();
		List<TdProductAttachment> attachmentList = tdProductAttachmentService.findByProductId(productId);
		int imagNum = null!=attachmentList?attachmentList.size():0;
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("attachmentList", attachmentList);
		modelMap.addAttribute("imageprice", imageprice);
		modelMap.addAttribute("imagNum", imagNum);
	    return "/mobile/imageorder/list";
	}
	
}
