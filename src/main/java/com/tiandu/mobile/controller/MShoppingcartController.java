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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdShoppingcartItemService;
import com.tiandu.order.vo.OrderForm;
import com.tiandu.order.vo.ShoppingcartVO;
import com.tiandu.system.utils.ConfigUtil;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/mobile/shoppingcart")
public class MShoppingcartController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdShoppingcartItemService tdShoppingcartItemService;
	
	@Autowired
	private TdOrderService tdOrderService;
	
	@Autowired
	private ConfigUtil configUtil;
	
	/*
	 * 我的购物车
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		//获取购物车
		ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
		modelMap.addAttribute("shoppingcart", shoppingcart) ;
	    return "/mobile/shoppingcart/list";
	}
	/**
	 * 加减购物车商品数量
	 * @param item
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(TdShoppingcartItem item, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=item && null!=item.getId() && null!=item.getOptype()){
			try {
				TdUser currUser = this.getCurrentUser();
				item.setUid(currUser.getUid());
				tdShoppingcartItemService.addShoppingcartItemNum(item);
				//获取购物车
				ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
				
				res.put("code", "1");
				res.put("totalAmount", shoppingcart.getTotalAmount().toString());
				res.put("totalPostage", shoppingcart.getTotalPostage().toString());
				return res;
			}catch (Exception e) {
				logger.error("购物车增减失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	/**
	 * 更改购物车商品数量
	 * @param item
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/change", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> change(TdShoppingcartItem item, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=item){
			try {
				TdUser currUser = this.getCurrentUser();
				item.setUid(currUser.getUid());
				tdShoppingcartItemService.updateShoppingcartItem(item);
				//重新计算
				//获取购物车
				ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
				
				res.put("code", "1");
				res.put("totalAmount", shoppingcart.getTotalAmount().toString());
				res.put("totalPostage", shoppingcart.getTotalPostage().toString());
				return res;
			}catch (Exception e) {
				logger.error("购物车更新失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	/**
	 * 购物车移除商品
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> remove(String ids, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(StringUtils.isNotEmpty(ids)){
			try {
				TdUser currUser = this.getCurrentUser();
				tdShoppingcartItemService.removeItemsFromShoppingcart(currUser.getUid(), ids);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("购物车删除失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	
	/**
	 * 购物车添加商品
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addsku", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> addsku(TdShoppingcartItem item, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=item){
			try {
				TdUser currUser = this.getCurrentUser();
				item.setUid(currUser.getUid());
				tdShoppingcartItemService.addToShoppingcart(item);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("购物车添加商品失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	
	/*
	 * 确认订单
	 */
	@RequestMapping("/confirmorder")
	public String confirmorder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		//获取购物车
		ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
		//收货地址
//		List<>
		modelMap.addAttribute("shoppingcart", shoppingcart) ;
	    return "/mobile/shoppingcart/confirmorder";
	}
	
	/*
	 * 确认订单
	 */
	@RequestMapping("/order")
	public String order(OrderForm orderForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		//获取购物车
		ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
		
		TdOrder order = tdOrderService.genernateOrder(currUser, orderForm, shoppingcart);
		
		modelMap.addAttribute("shoppingcart", shoppingcart) ;
	    return "/mobile/shoppingcart/confirmorder";
	}
	
	private ShoppingcartVO getShoppingcart(Integer uid){
		Integer integralexchangerate = configUtil.getIntegralExchangerate(); //积分抵扣金额比例
		Integer commonproductpointpercent = configUtil.getCommonProductPointPercent(); //普通商品可积分抵扣的比例
		Integer partproductpointpercent = configUtil.getPartProductPointPercent(); //部分积分兑换商品可积分抵扣的比例
		ShoppingcartVO cart = new ShoppingcartVO();
		//重新计算
		TdShoppingcartSearchCriteria sc = new TdShoppingcartSearchCriteria();
		sc.setFlag(false);
		sc.setUid(uid);
		List<TdShoppingcartItem> itemList = tdShoppingcartItemService.findBySearchCriteria(sc);
		cart.setItemList(itemList);
		if(null!=itemList && itemList.size()>0){
			for(TdShoppingcartItem item : itemList){
				//累加每个商品的运费
				BigDecimal postage = (null!=item.getProduct() && null!=item.getProduct().getPostage())?item.getProduct().getPostage():BigDecimal.ZERO;
				cart.setTotalPostage(postage.add(cart.getTotalPostage()));
				//累加单个商品总金额（价格*数量）
				BigDecimal quantity = new BigDecimal(item.getQuantity());
				BigDecimal amount = item.getProductSku().getSalesPrice().multiply(quantity);
				cart.setTotalAmount(amount.add(cart.getTotalAmount()).add(postage));
				//计算可以积分抵扣金额
				BigDecimal pointAmount =BigDecimal.ZERO;
				if(ConstantsUtils.PRODUCT_KIND_PART_POINT_EXCHANGE.equals(item.getProduct().getKind()) && partproductpointpercent>0 && integralexchangerate>0){
					pointAmount =  amount.multiply(new BigDecimal(partproductpointpercent));
					cart.setTotalPartPointAmount(pointAmount.add(cart.getTotalPartPointAmount()));
				}else if(commonproductpointpercent>0 && integralexchangerate>0){
					pointAmount =  amount.multiply(new BigDecimal(commonproductpointpercent));
					cart.setTotalCommonPointAmount(pointAmount.add(cart.getTotalCommonPointAmount()));
				}				
			}
			//计算总积分抵扣金额和
			cart.setTotalPointAmount(cart.getTotalCommonPointAmount().add(cart.getTotalPartPointAmount()));
			Integer commonproductpoint = cart.getTotalCommonPointAmount().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
			Integer partproductpoint = cart.getTotalPartPointAmount().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
			cart.setTotalPointsUsed(commonproductpoint+partproductpoint);
			
		}
		cart.setTotalcount(itemList.size());
		return cart;
	}
}
