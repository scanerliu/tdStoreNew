package com.tiandu.mobile.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.custom.service.TdUserIntegralService;
import com.tiandu.order.entity.TdJointOrder;
import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdShoppingcartItemService;
import com.tiandu.order.vo.BuyNow;
import com.tiandu.order.vo.OrderForm;
import com.tiandu.order.vo.ShoppingcartVO;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.service.TdProductSkuService;
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
	private TdUserIntegralService tdUserIntegralService;
	
	@Autowired
	private TdUserAccountService tdUserAccountService;
	
	@Autowired
	private TdOrderService tdOrderService;
	@Autowired
	private TdProductSkuService tdProductSkuService;
	
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
	 * 下订单
	 */
	@RequestMapping("/order")
	public String order(OrderForm orderForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		if(null==orderForm.getUsePoints()){
			orderForm.setUsePoints(false);
		}
		TdUser currUser = this.getCurrentUser();
		//获取购物车
		ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
		
		TdJointOrder order = new TdJointOrder();
		try {
			order = tdOrderService.saveOrderFull(currUser, orderForm, shoppingcart);
			tdShoppingcartItemService.deleteByUid(currUser.getUid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			order.setErrMsg(e.getMessage());
		}
		if(order!=null && StringUtils.isNotEmpty(order.getJno())){
			//下单成功调整支付页面
			modelMap.addAttribute("order", order) ;
		    return "/mobile/shoppingcart/orderpay";
			
		}else{
			//下单失败
			modelMap.addAttribute("order", order) ;
		    return "/mobile/shoppingcart/ordererror";
		}
	}
	
	/*
	 * 立即下单确认订单
	 */
	@RequestMapping("/buynow")
	public String buynow(OrderForm orderForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		//生成购物车
		ShoppingcartVO shoppingcart;
		try {
			shoppingcart = getShoppingcart(currUser.getUid(), orderForm);
			modelMap.addAttribute("shoppingcart", shoppingcart) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//下单失败
			TdJointOrder order = new TdJointOrder();
			order.setErrMsg(e.getMessage());
			modelMap.addAttribute("order", order) ;
		    return "/mobile/shoppingcart/ordererror";
		}
		modelMap.addAttribute("orderForm", orderForm) ;
	    return "/mobile/shoppingcart/buynow";
	}
	
	/*
	 * 下订单
	 */
	@RequestMapping("/singleorder")
	public String singleorder(OrderForm orderForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		if(null==orderForm.getUsePoints()){
			orderForm.setUsePoints(false);
		}
		TdJointOrder order = new TdJointOrder();
		TdUser currUser = this.getCurrentUser();
		try {
			//获取购物车
			ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid(), orderForm);
		
			order = tdOrderService.saveOrderFull(currUser, orderForm, shoppingcart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			order.setErrMsg(e.getMessage());
		}
		if(order!=null && StringUtils.isNotEmpty(order.getJno())){
			//下单成功调整支付页面
			modelMap.addAttribute("order", order) ;
		    return "/mobile/shoppingcart/orderpay";
			
		}else{
			//下单失败
			modelMap.addAttribute("order", order) ;
		    return "/mobile/shoppingcart/ordererror";
		}
	}
	/**
	 * 根据立即购买生成购物车信息
	 * @param uid
	 * @param buynow
	 * @return
	 * @throws Exception 
	 */
	private ShoppingcartVO getShoppingcart(Integer uid, OrderForm orderForm) throws Exception {
		Integer integralexchangerate = configUtil.getIntegralExchangerate(); //积分抵扣金额比例
		Integer commonproductpointpercent = configUtil.getCommonProductPointPercent(); //普通商品可积分抵扣的比例
		Integer partproductpointpercent = configUtil.getPartProductPointPercent(); //部分积分兑换商品可积分抵扣的比例
		ShoppingcartVO cart = new ShoppingcartVO();
		TdShoppingcartItem item = new TdShoppingcartItem();
		TdProductSku sku = tdProductSkuService.findOneWithProduct(orderForm.getProductSkuId());
		if(null!=sku && sku.getProduct().getStatus().equals(Byte.valueOf("1")) && sku.getProduct().getOnshelf()){
			item.setItemType(sku.getProduct().getKind().intValue());
			item.setPostage(sku.getProduct().getPostage());
			item.setPrice(sku.getSalesPrice());
			item.setProductId(sku.getProductId());
			item.setProductSkuId(sku.getId());
			item.setProduct(sku.getProduct());
			item.setProductSku(sku);
			item.setQuantity(orderForm.getQuantity());
		}else{
			throw new Exception("下单失败，商品不存在或已经下架！");
		}
		
		//重新计算
		List<TdShoppingcartItem> itemList = new ArrayList<TdShoppingcartItem>();
		itemList.add(item);
		//累加每个商品的运费
		BigDecimal postage = (null!=item.getPostage())?item.getPostage():BigDecimal.ZERO;
		cart.setTotalPostage(postage);
		//累加单个商品总金额（价格*数量）
		BigDecimal quantity = new BigDecimal(item.getQuantity());
		BigDecimal amount = item.getPrice().multiply(quantity);
		cart.setTotalProductAmount(amount);
		cart.setTotalAmount(amount);
		//计算可以积分抵扣金额
		BigDecimal pointAmount =BigDecimal.ZERO;
		if(ConstantsUtils.PRODUCT_KIND_PART_POINT_EXCHANGE.equals(item.getProduct().getKind()) && partproductpointpercent>0 && integralexchangerate>0){
			pointAmount =  amount.multiply(new BigDecimal(partproductpointpercent)).divide(new BigDecimal(100));
			cart.setTotalPartPointAmount(pointAmount);
		}else if(commonproductpointpercent>0 && integralexchangerate>0){
			pointAmount =  amount.multiply(new BigDecimal(commonproductpointpercent)).divide(new BigDecimal(100));
			cart.setTotalCommonPointAmount(pointAmount);
		}
		//统计供应商id
		cart.setSupplierId(item.getProduct().getUid());
		//计算总积分抵扣金额和
		cart.setTotalPointAmount(cart.getTotalCommonPointAmount().add(cart.getTotalPartPointAmount()));
		Integer commonproductpoint = cart.getTotalCommonPointAmount().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
		Integer partproductpoint = cart.getTotalPartPointAmount().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
		cart.setTotalPointsUsed(commonproductpoint+partproductpoint);
		//获取用户积分
		TdUserIntegral userIntegral = tdUserIntegralService.findOne(uid);
		if(null!=userIntegral && userIntegral.getIntegral()>=cart.getTotalPointsUsed()){
			
		}else{
			cart.setTotalPointsUsed(0);
		}
		//获取用户钱包余额
		TdUserAccount userAccount = tdUserAccountService.findOne(uid);
		if(null!=userAccount && TdUserAccount.ACCOUNT_STATUS_ACTIVE.equals(userAccount.getStatus()) && userAccount.getAmount().compareTo(cart.getTotalAmount())>=0){
			cart.setCanUserAccount(true);
		}else{
			cart.setCanUserAccount(false);
		}
		cart.setCombiningOrder(false);
		cart.setItemList(itemList);
		cart.setTotalcount(itemList.size());
		return cart;
	}
	/**
	 * 查询用户购物车信息
	 * @param uid
	 * @return
	 */
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
			Set<Integer> suppliers = new HashSet<Integer>(); 
			for(TdShoppingcartItem item : itemList){
				//累加每个商品的运费
				BigDecimal postage = (null!=item.getProduct() && null!=item.getProduct().getPostage())?item.getProduct().getPostage():BigDecimal.ZERO;
				cart.setTotalPostage(postage.add(cart.getTotalPostage()));
				//累加单个商品总金额（价格*数量）
				BigDecimal quantity = new BigDecimal(item.getQuantity());
				BigDecimal amount = item.getProductSku().getSalesPrice().multiply(quantity);
				cart.setTotalProductAmount(amount.add(cart.getTotalProductAmount()));
				cart.setTotalAmount(amount.add(cart.getTotalAmount()).add(postage));
				//计算可以积分抵扣金额
				BigDecimal pointAmount =BigDecimal.ZERO;
				if(ConstantsUtils.PRODUCT_KIND_PART_POINT_EXCHANGE.equals(item.getProduct().getKind()) && partproductpointpercent>0 && integralexchangerate>0){
					pointAmount =  amount.multiply(new BigDecimal(partproductpointpercent)).divide(new BigDecimal(100));
					cart.setTotalPartPointAmount(pointAmount.add(cart.getTotalPartPointAmount()));
				}else if(commonproductpointpercent>0 && integralexchangerate>0){
					pointAmount =  amount.multiply(new BigDecimal(commonproductpointpercent)).divide(new BigDecimal(100));
					cart.setTotalCommonPointAmount(pointAmount.add(cart.getTotalCommonPointAmount()));
				}
				//统计供应商id
				suppliers.add(item.getProduct().getUid());
				cart.setSupplierId(item.getProduct().getUid());
			}
			//设定供应商集合
			cart.setSupplierIds(suppliers);
			//计算总积分抵扣金额和
			cart.setTotalPointAmount(cart.getTotalCommonPointAmount().add(cart.getTotalPartPointAmount()));
			Integer commonproductpoint = cart.getTotalCommonPointAmount().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
			Integer partproductpoint = cart.getTotalPartPointAmount().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
			cart.setTotalPointsUsed(commonproductpoint+partproductpoint);
			//获取用户积分
			TdUserIntegral userIntegral = tdUserIntegralService.findOne(uid);
			if(null!=userIntegral && userIntegral.getIntegral()>=cart.getTotalPointsUsed()){
				
			}else{
				cart.setTotalPointsUsed(0);
			}
			//获取用户钱包余额
			TdUserAccount userAccount = tdUserAccountService.findOne(uid);
			if(null!=userAccount && TdUserAccount.ACCOUNT_STATUS_ACTIVE.equals(userAccount.getStatus()) && userAccount.getAmount().compareTo(cart.getTotalAmount())>=0){
				cart.setCanUserAccount(true);
			}else{
				cart.setCanUserAccount(false);
			}
			//判断是否需要拆分订单
			if(suppliers.size()>1){
				cart.setCombiningOrder(true);
			}
		}
		cart.setTotalcount(itemList.size());
		return cart;
	}
}
