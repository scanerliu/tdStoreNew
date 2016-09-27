package com.tiandu.client.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.comment.entity.TdProductComment;
import com.tiandu.comment.search.TdProductCommentCrateria;
import com.tiandu.comment.service.TdProductCommentService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.vo.CommentForm;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.entity.TdProductStat;
import com.tiandu.product.service.TdProductSkuService;
import com.tiandu.product.service.TdProductStatService;

/**
 * 会员中心—我的评价
 * 
 */
@Controller
@RequestMapping("/user")
public class CUserCommentController extends BaseController{

	@Autowired
	private TdProductCommentService tdProductCommentService; 
	
	@Autowired
	private TdOrderService tdOrderService;
	
	@Autowired
	private TdProductSkuService tdProductSkuService;
	
	@Autowired
	private TdProductStatService tdProductStatService;
	
	@RequestMapping("/commentlist")
	public String commentlist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		modelMap.addAttribute("menucode", "comment") ;//菜单code
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/login";
		}
		return "/client/user/comment/comment";	
	}
	
	@RequestMapping("/comment/search")
	public String commentSearch(TdProductCommentCrateria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		TdUser user = getCurrentUser();
		sc.setUid(user.getUid());
		modelMap.addAttribute("commentList", tdProductCommentService.findBySearchCriteria(sc));
		modelMap.addAttribute("sc", sc);
		
		return "/client/user/comment/commentmore";
	}
	
	@RequestMapping("/comment/{orderId}")
	public String ordercoment(@PathVariable Integer orderId,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/login";
		}
		if(null == orderId)
		{
			return "redirect:404";
		}
		
		map.addAttribute("order", tdOrderService.findDetail(orderId));
		// 系统配置
		map.addAttribute("system", getSystem());
		return "/client/user/comment/order_com";
	}
	
	
	@RequestMapping(value="/comment/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addComment(CommentForm commentForm,HttpServletRequest req,ModelMap map){
		Map<String,String> res = new HashMap<String,String>();
		Date now = new Date();
		TdUser user = getCurrentUser();
		if(null==user)
		{
			res.put("msg", "请先登陆！");
			res.put("code", "0");
			return res;
		}
		
		if(null == commentForm.getOrderId() || null == commentForm.getProductId())
		{
			res.put("msg", "评论失败：数据错误！");
			res.put("code", "0");
			return res;
		}
		TdOrder order = tdOrderService.findOne(commentForm.getOrderId());
		if(null == order)
		{
			res.put("msg", "评论失败：订单未找到！");
			res.put("code", "0");
			return res;
		}
		
		for (int i = 0; i < commentForm.getProductId().length; i++) {
			TdProductSku sku = tdProductSkuService.findOne(commentForm.getSkuId()[i]);
			TdProductComment comment = new TdProductComment();
			comment.setOrderId(commentForm.getOrderId()); // 订单
			if(null!=commentForm.getImages()){
				String[] subimag = commentForm.getImages().get(i);
				StringBuffer sb = new StringBuffer();
				if(null!=subimag){
					int k=1;
					for(String image : subimag){
						if(StringUtils.isNotBlank(image)){
							if(k==1){
								sb.append(image);
							}else{
								sb.append(":"+image);
							}
							k++;
						}
					}
				}
				comment.setImages(sb.toString()); //图
			}
			comment.setContent(commentForm.getContent()[i]); //内容
			comment.setProductId(commentForm.getProductId()[i]);
			comment.setUid(user.getUid()); 
			comment.setScore(commentForm.getScore()[i]); // 评分
			comment.setStatus((byte)1);
			if(null != commentForm.getAnonymous()){
				comment.setAnonymous(true);
			}else{
				comment.setAnonymous(false);
			}
			comment.setCreateTime(now);
			if(null != sku){
				// 规格属性
				comment.setSpecifications(sku.getSpecifications());
			}
			tdProductCommentService.save(comment);
			//更新商品统计
			TdProductStat stat = tdProductStatService.findOne(comment.getProductId());
			if(null!=stat){
				Integer reviewCount = stat.getReviewCount();
				BigDecimal score = stat.getReviewScore();
				BigDecimal newscore = score.multiply(new BigDecimal(reviewCount)).add(new BigDecimal(comment.getScore())).divide(new BigDecimal(reviewCount+1), 2, BigDecimal.ROUND_HALF_UP);
				stat.setReviewScore(newscore);
				if(comment.getScore().compareTo(Byte.valueOf("3"))<0){
					stat.setNegativeRate(stat.getNegativeRate()+1);
				}else if(comment.getScore().compareTo(Byte.valueOf("5"))==0){
					stat.setPositiveRate(stat.getPositiveRate()+1);
				}else{
					stat.setNeutralRate(stat.getNeutralRate()+1);
				}
				stat.setReviewCount(stat.getReviewCount()+1);
				stat.setShowreviewCount(stat.getShowreviewCount()+1);
				tdProductStatService.updateByPrimaryKey(stat);
			}
		}
		
		// 修改订单评论状态
		order.setCommented(true);
		tdOrderService.save(order);
		
		res.put("msg", "评论成功。");
		res.put("code", "1");
		return res;
	}
	
}
