package com.tiandu.mobile.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiandu.comment.entity.TdProductComment;
import com.tiandu.comment.search.TdProductCommentCrateria;
import com.tiandu.comment.service.TdProductCommentService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.service.TdProductSkuService;

/**
 * 
 * @author Max
 * 会员中心—我的评价
 * 
 * 创建时间：2016年7月21日 下午3:46:02
 * 
 */
@Controller
@RequestMapping("/mobile/user")
public class MobileUserCommentController extends BaseController{

	@Autowired
	private TdProductCommentService tdProductCommentService; 
	
	@Autowired
	private TdOrderService tdOrderService;
	
	@Autowired
	private TdProductSkuService tdProductSkuService;
	
	@RequestMapping("/productComment")
	public String comment(HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/mobile/login";
		}
		
		TdProductCommentCrateria sc = new TdProductCommentCrateria();
		sc.setUid(user.getUid());
		
		
		map.addAttribute("commentList", tdProductCommentService.findBySearchCriteria(sc));
		return "/mobile/user/comment/comment";	
	}
	
	@RequestMapping("/comment/search")
	public String commentSearch(TdProductCommentCrateria sc,Integer page,HttpServletRequest req,ModelMap map)
	{
		sc.setPageNo(page);
		
		int count = tdProductCommentService.getTotalPageCount(sc);
		if(count >= page)
		{
			map.addAttribute("commentList", tdProductCommentService.findBySearchCriteria(sc));
			map.addAttribute("sc", sc);
		}
		
		return "/mobile/user/comment/commentmore";
	}
	
	@RequestMapping("/comment/{orderId}")
	public String ordercoment(@PathVariable Integer orderId,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/mobile/login";
		}
		if(null == orderId)
		{
			return "redirect:404";
		}
		
		map.addAttribute("order", tdOrderService.findDetail(orderId));
		
		return "/mobile/user/comment/order_com";
	}
	
	
	@RequestMapping(value="/comment/add",method=RequestMethod.POST)
	public String addComment(Integer orderId,Integer[] productId,
							String[] imgUrl,Byte score,
							String[] content,Integer[] skuId,
							Boolean anonymous,HttpServletRequest req,ModelMap map){
		// 系统配置
		map.addAttribute("system", getSystem());
		
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/mobile/login";
		}
		
		
		if(null == orderId || null == productId)
		{
			return "/mobile/user/comment/failed";
		}
		TdOrder order = tdOrderService.findOne(orderId);
		if(null == order)
		{
			return "redirect:404";
		}
		
		for (int i = 0; i < productId.length; i++) {
			TdProductSku sku = tdProductSkuService.findOne(skuId[i]);
			TdProductComment comment = new TdProductComment();

			comment.setOrderId(orderId); // 订单
			comment.setImages(imgUrl[i]); //图
			comment.setContent(content[i]); //内容
			comment.setProductId(productId[i]);
			comment.setUid(user.getUid()); 
			comment.setScore(score); // 评分
			comment.setStatus((byte)1);
			if(null != anonymous){
				comment.setAnonymous(true);
			}else{
				comment.setAnonymous(false);
			}
			comment.setCreateTime(new Date());
			if(null != sku){
				// 规格属性
				comment.setSpecifications(sku.getSpecifications());
			}
			tdProductCommentService.save(comment);
		}
		
		// 修改订单评论状态
		order.setCommented(true);
		tdOrderService.save(order);
		
		return "/mobile/user/comment/success";
	}
	
}
