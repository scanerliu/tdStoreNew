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
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.entity.TdBrancheCompany;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.search.TdUserAddressCriteria;
import com.tiandu.custom.service.TdAgentService;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.custom.service.TdUserAddressService;
import com.tiandu.custom.service.TdUserIntegralService;
import com.tiandu.order.entity.TdJointOrder;
import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.search.TdShoppingcartSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdShoppingcartItemService;
import com.tiandu.order.vo.ImageOrderVO;
import com.tiandu.order.vo.OrderForm;
import com.tiandu.order.vo.ShoppingcartVO;
import com.tiandu.product.entity.TdAgentProduct;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;
import com.tiandu.product.entity.TdProductPackageItem;
import com.tiandu.product.entity.TdProductSku;
import com.tiandu.product.search.TdProductPackageItemSearchCriteria;
import com.tiandu.product.service.TdAgentProductService;
import com.tiandu.product.service.TdProductAttachmentService;
import com.tiandu.product.service.TdProductPackageItemService;
import com.tiandu.product.service.TdProductService;
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
	private TdAgentService tdAgentService;
	@Autowired
	private TdAgentProductService tdAgentProductService;
	
	@Autowired
	private TdProductService tdProductService;
	
	@Autowired
	private TdProductAttachmentService tdProductAttachmentService;
	
	@Autowired
	private ConfigUtil configUtil;
	
	@Autowired
	private TdUserAddressService tdUserAddressService;
	
	@Autowired
	private TdProductPackageItemService tdProductPackageItemService;
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
	@RequestMapping(value = "/confirmorder")
	public String confirmorder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,Integer addressId) {
		TdUser currUser = this.getCurrentUser();
		if(!TdUser.USTATUS_ACTIVE.equals(currUser.getUverification())){//未验证的用户不能购买商品
			String errmsg = "用户未验证，请先到个人信息中绑定手机号码和设置地区完成验证才能购买商品！";
			modelMap.addAttribute("errmsg", errmsg) ;
			return  "/mobile/error";
		}
		//获取购物车
		ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
		
		//收货地址
		TdUserAddress defaultAddress = null;
		if(addressId != null)
		{
			defaultAddress = tdUserAddressService.findOne(addressId);
			if(defaultAddress != null && defaultAddress.getUid() != currUser.getUid())
			{
				defaultAddress = null;
			}
			request.getSession().removeAttribute("shopping");
		}
		else
		{
			defaultAddress = tdUserAddressService.defaultAddressByUid(currUser.getUid());
		}
		if(defaultAddress != null)
		{
			modelMap.addAttribute("address", defaultAddress);
		}
		
		modelMap.addAttribute("shoppingcart", shoppingcart) ;
		modelMap.addAttribute("randomNo",-(int)(Math.random()*(100-1+1)));
		
		String ua = request.getHeader("user-agent") .toLowerCase();
		
		// 微信端
		if(WebUtils.checkAgentIsWX(ua)){
			modelMap.addAttribute("isWx", true);
		}
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
		if(!TdUser.USTATUS_ACTIVE.equals(currUser.getUverification())){//未验证的用户不能购买商品
			String errmsg = "用户未验证，请先到个人信息中绑定手机号码和设置地区完成验证才能购买商品！";
			modelMap.addAttribute("errmsg", errmsg) ;
			return  "/mobile/error";
		}
		//获取购物车
		ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid());
		
		if(null==shoppingcart.getItemList() || shoppingcart.getItemList().size()==0)
		{//订单没有商品
			String errmsg = "订单没有商品！";
			modelMap.addAttribute("errmsg", errmsg) ;
			return  "/mobile/error";
		}
		
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
			return "redirect:/mobile/order/jointdopay"+order.getId();
			
		}else{
			//下单失败
			modelMap.addAttribute("order", order) ;
		    return "/mobile/shoppingcart/ordererror";
		}
	}
	
	/**
	 * 立即下单确认订单
	 * @param orderForm
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param addressId  选择订单地址，从收货地址处返回收货地址ID
	 * @return
	 */
	@RequestMapping("/buynow")
	public String buynow(OrderForm orderForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap ,Integer addressId) {
		TdUser currUser = this.getCurrentUser();
		if(!TdUser.USTATUS_ACTIVE.equals(currUser.getUverification())){//未验证的用户不能购买商品
			String errmsg = "用户未验证，请先到个人信息中绑定手机号码和设置地区完成验证才能购买商品！";
			modelMap.addAttribute("errmsg", errmsg) ;
			return  "/mobile/error";
		}
		//生成购物车
		ShoppingcartVO shoppingcart;	
		try {
			shoppingcart = getShoppingcart(currUser.getUid(), orderForm);
			modelMap.addAttribute("shoppingcart", shoppingcart) ;
			
			//收货地址
			TdUserAddress defaultAddress = null;
			if(addressId != null)
			{
				defaultAddress = tdUserAddressService.findOne(addressId);
				if(defaultAddress != null && defaultAddress.getUid() != currUser.getUid())
				{
					defaultAddress = null;
				}
				orderForm = (OrderForm)request.getSession().getAttribute("orderForm");
				request.getSession().removeAttribute("shopping");
				request.getSession().removeAttribute("orderForm");
			}
			else
			{
				defaultAddress = tdUserAddressService.defaultAddressByUid(currUser.getUid());
				request.getSession().setAttribute("orderForm", orderForm);
			}
			
			if(defaultAddress != null)
			{
				modelMap.addAttribute("address", defaultAddress);
			}
			
			
			modelMap.addAttribute("randomNo",-(int)(Math.random()*(1000-101+1)));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//下单失败
			TdJointOrder order = new TdJointOrder();
			order.setErrMsg(e.getMessage());
			modelMap.addAttribute("order", order) ;
		    return "/mobile/shoppingcart/ordererror";
		}
		String ua = request.getHeader("user-agent") .toLowerCase();
		
		// 微信端
		if(WebUtils.checkAgentIsWX(ua)){
			modelMap.addAttribute("isWx", true);
		}
		
		modelMap.addAttribute("orderForm", orderForm) ;
	    return "/mobile/shoppingcart/buynow";
	}
	
	/*
	 * 下订单
	 */
	@RequestMapping("/singleorder")
	public String singleorder(OrderForm orderForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		request.getSession().removeAttribute("orderForm");
		if(null==orderForm.getUsePoints()){
			orderForm.setUsePoints(false);
		}
		TdJointOrder order = new TdJointOrder();
		TdUser currUser = this.getCurrentUser();
		if(!TdUser.USTATUS_ACTIVE.equals(currUser.getUverification())){//未验证的用户不能购买商品
			String errmsg = "用户未验证，请先到个人信息中绑定手机号码和设置地区完成验证才能购买商品！";
			modelMap.addAttribute("errmsg", errmsg) ;
			return  "/mobile/error";
		}
		try {
			//获取购物车
			ShoppingcartVO shoppingcart  = getShoppingcart(currUser.getUid(), orderForm);
			if(null==orderForm.getAddressId() && shoppingcart.getNeedShipment()){//未填写发货地址
				String errmsg = "未填写收货地址！";
				modelMap.addAttribute("errmsg", errmsg) ;
				return  "/mobile/error";
			}
			if((orderForm.getProductType().equals(2)&& null==orderForm.getAgentProductId() )||(!orderForm.getProductType().equals(2)&&null==orderForm.getProductId()))
			{//订单没有商品
				String errmsg = "订单没有商品！";
				modelMap.addAttribute("errmsg", errmsg) ;
				return  "/mobile/error";
			}
		
			order = tdOrderService.saveOrderFull(currUser, orderForm, shoppingcart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			order.setErrMsg(e.getMessage());
		}
		if(order!=null && StringUtils.isNotEmpty(order.getJno())){
			//下单成功调整支付页面
			modelMap.addAttribute("order", order) ;
		    return "redirect:/mobile/order/jointdopay"+order.getId();
			
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
		Boolean canuserpackage = configUtil.isAgentProductUsePackage();//是否启用700元的单类代理领取商品包
		ShoppingcartVO cart = new ShoppingcartVO();
		//判断商品类型，1普通商品，2代理产品，3图片美化
		if(orderForm.getProductType()==2){
			cart.setPtype(2);
			TdAgentProduct agentproduct = tdAgentProductService.findOne(orderForm.getAgentProductId());
			cart.setNeedShipment(agentproduct.getGift());
			if(null!=agentproduct && null!=agentproduct.getId()){
				cart.setAgentProduct(agentproduct);
				//代理产品类型 1-单代
				if(ConstantsUtils.AGENT_GROUPID_AGENT.equals(agentproduct.getGroupId())){
					TdAgent agent = new TdAgent();
					agent.setLevel(agentproduct.getLevel());
					agent.setProductTypeId(orderForm.getProductTypeId());
					agent.setRegionId(orderForm.getRegionId());
					agent.setUid(uid);
					cart.setPtype(2);
					cart.setAgent(agent);
					cart.setTotalcount(1);
					//2-分公司
				}else if(ConstantsUtils.AGENT_GROUPID_BRANCH.equals(agentproduct.getGroupId())){
					TdBrancheCompany branche = new TdBrancheCompany();
					branche.setLevel(agentproduct.getLevel());
					branche.setRegionId(orderForm.getRegionId());
					branche.setUid(uid);
					cart.setPtype(3);
					cart.setBranch(branche);
					cart.setTotalcount(1);
					//4-供应商
				}else if(ConstantsUtils.AGENT_GROUPID_SUPPLIER.equals(agentproduct.getGroupId())){
					//检查用户是否已经是供应商资格
					TdUser user = tdUserService.findOne(uid);
					if(null!=user && user.getSupplierType().equals(Byte.valueOf("0"))){
						
					}else{
						throw new Exception("下单失败，您已经具有供应商资格，不能重复购买！");
					}
					cart.setPtype(5);
					cart.setTotalcount(1);
				}else{
					throw new Exception("下单失败，代理产品不存在或已经下架！");
				}
				if(agentproduct.getGift()&& null!=orderForm.getProductId()){//代金券产品
					//添加礼品包
					TdProduct productpack = tdProductService.findOne(orderForm.getProductId());
					if(null==productpack|| !productpack.getOnshelf() || !productpack.getStatus().equals(Byte.valueOf("1"))){
						throw new Exception("下单失败，礼品包不存在或已经下架！");
					}
					cart.setProductPackage(productpack);
					//礼品包详细列表
					List<TdShoppingcartItem> itemList = new ArrayList<TdShoppingcartItem>();
					TdProductPackageItemSearchCriteria psc = new TdProductPackageItemSearchCriteria();
					psc.setFlag(false);
					psc.setProductId(orderForm.getProductId());
					List<TdProductPackageItem> packList = tdProductPackageItemService.findBySearchCriteria(psc);
					if(null!=packList){
						for(TdProductPackageItem pack : packList){
							TdShoppingcartItem item = new TdShoppingcartItem();
							item.setItemType(ConstantsUtils.PRODUCT_KIND_PACKAGE.intValue());
							item.setPostage(BigDecimal.ZERO);
							item.setPrice(pack.getPrice());
							item.setProductId(pack.getProductId());
							item.setProductSkuId(pack.getSkuId());
							item.setProductPackageItem(pack);
							item.setQuantity(pack.getQuantity());								
							itemList.add(item);
						}
					}
					cart.setItemList(itemList);
				}
				cart.setTotalAmount(agentproduct.getSalesPrice());
				cart.setTotalProductAmount(agentproduct.getSalesPrice());
				
			}else{
				throw new Exception("下单失败，代理产品不存在或已经下架！");
			}
		}else if(orderForm.getProductType()==3){//图片美化订单
			cart.setPtype(4); //图片订单类型
			TdProduct product = null;
			//获取商品信息
			if(null!=orderForm.getProductId() && orderForm.getProductId()>0){
				product = tdProductService.findOne(orderForm.getProductId());
			}
			
			if(null==product || !product.getUid().equals(uid)){
				throw new Exception("下单失败，商品不存在！");
			}
			
			List<TdProductAttachment> attachmentList = tdProductAttachmentService.findByProductId(product.getId());
			if(null==attachmentList || attachmentList.size()==0){
				throw new Exception("下单失败，商品图片不存在！");
			}
			Integer imageNum = attachmentList.size();
			Integer imageprice = configUtil.getImageProcessingPrice();
			ImageOrderVO imageorder = new ImageOrderVO();
			imageorder.setAttachmentList(attachmentList);
			imageorder.setImageNum(imageNum);
			imageorder.setPrice(imageprice);
			imageorder.setProduct(product);
			BigDecimal totalAmount = new BigDecimal(imageNum * imageprice);
			
			cart.setImageOrder(imageorder);
			cart.setTotalAmount(totalAmount);
			cart.setTotalProductAmount(totalAmount);
			cart.setTotalCommonPointAmount(BigDecimal.ZERO);
			cart.setTotalPointAmount(BigDecimal.ZERO);
			cart.setTotalcount(1);
			cart.setGainPoints(0);
			cart.setSupplierId(1);
			
		}else{
			cart.setPtype(1);
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
				if(sku.getProduct().getKind().equals(Byte.valueOf("3"))){//零元购数量固定为1
					item.setQuantity(1);
				}else{
					item.setQuantity(orderForm.getQuantity());
				}
			}else{
				throw new Exception("下单失败，商品不存在或已经下架！");
			}
			cart.setNeedShipment(true);//需要发货
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
			cart.setTotalAmount(amount.add(postage));
			//计算可以积分抵扣金额
			BigDecimal pointAmount =BigDecimal.ZERO;
			if(ConstantsUtils.PRODUCT_KIND_PART_POINT_EXCHANGE.equals(item.getProduct().getKind()) && partproductpointpercent>0 && integralexchangerate>0){
				pointAmount =  amount.multiply(new BigDecimal(partproductpointpercent)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_FLOOR);
				cart.setTotalPartPointAmount(pointAmount);
			}else if(ConstantsUtils.PRODUCT_KIND_POINT_EXCHANGE.equals(item.getProduct().getKind()) && partproductpointpercent>0 && integralexchangerate>0){
				pointAmount =  amount;
				cart.setTotalPartPointAmount(pointAmount.add(cart.getTotalPartPointAmount()));
			}else if(commonproductpointpercent>0 && integralexchangerate>0){
				pointAmount =  amount.multiply(new BigDecimal(commonproductpointpercent)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_FLOOR);
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
		}
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
		cart.setPtype(1);
		cart.setNeedShipment(true);//需要发货
		//重新计算
		TdShoppingcartSearchCriteria sc = new TdShoppingcartSearchCriteria();
		sc.setFlag(false);
		sc.setUid(uid);
		List<TdShoppingcartItem> itemList = tdShoppingcartItemService.findBySearchCriteria(sc);
		cart.setItemList(itemList);
		if(null!=itemList && itemList.size()>0){
			Set<Integer> suppliers = new HashSet<Integer>(); 
			for(TdShoppingcartItem item : itemList){
				if(null==item.getProductSku()||null==item.getProduct()){
					tdShoppingcartItemService.removeFromShoppingcart(item.getUid(), item.getId());
				}
				//累加每个商品的运费
				BigDecimal postage = BigDecimal.ZERO;
				if(null!=item.getProduct() && null!=item.getProduct().getPostage()){
					if(ConstantsUtils.PRODUCT_KIND_ZEROBUY.equals(item.getProduct().getKind())){
						postage = item.getProduct().getPostage().multiply(new BigDecimal(item.getQuantity()));
					}else{
						postage = item.getProduct().getPostage();
					}
				}
				cart.setTotalPostage(postage.add(cart.getTotalPostage()));
				//累加单个商品总金额（价格*数量）
				BigDecimal quantity = new BigDecimal(item.getQuantity());
				BigDecimal amount = item.getProductSku().getSalesPrice().multiply(quantity);
				cart.setTotalProductAmount(amount.add(cart.getTotalProductAmount()));
				cart.setTotalAmount(amount.add(cart.getTotalAmount()).add(postage));
				//计算可以积分抵扣金额
				BigDecimal pointAmount =BigDecimal.ZERO;
				if(ConstantsUtils.PRODUCT_KIND_PART_POINT_EXCHANGE.equals(item.getProduct().getKind()) && partproductpointpercent>0 && integralexchangerate>0){
					pointAmount =  amount.multiply(new BigDecimal(partproductpointpercent)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_FLOOR);
					cart.setTotalPartPointAmount(pointAmount.add(cart.getTotalPartPointAmount()));
				}else if(ConstantsUtils.PRODUCT_KIND_POINT_EXCHANGE.equals(item.getProduct().getKind()) && partproductpointpercent>0 && integralexchangerate>0){
					pointAmount =  amount;
					cart.setTotalPartPointAmount(pointAmount.add(cart.getTotalPartPointAmount()));
				}else if(commonproductpointpercent>0 && integralexchangerate>0){
					pointAmount =  amount.multiply(new BigDecimal(commonproductpointpercent)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_FLOOR);
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
	
	@RequestMapping(value = "/selectAddress")
	public String SelectShoppingAddress(ModelMap map,Integer addressId)
	{
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			return "redirect:/mobile/login";
		}
		if(addressId != null && addressId > 0)
		{
			tdUserAddressService.setIsDefaultFalse(tdUser.getUid());
			TdUserAddress userAddress = tdUserAddressService.findOne(addressId);
			userAddress.setIsDefault(true);
			tdUserAddressService.save(userAddress);
		}
		// 系统配置
		map.addAttribute("system", getSystem());
		TdUserAddressCriteria sc = new TdUserAddressCriteria();
		sc.setUid(tdUser.getUid());
		List<TdUserAddress> userAddresses = tdUserAddressService.findBySearchCriteria(sc);
		map.addAttribute("address_list", userAddresses);
		return "/mobile/shoppingCart/selectAddress";
	}
	@RequestMapping(value = "/addAddress")
	public String addAddress(Integer addressId,ModelMap map)
	{
		TdUser tdUser = this.getCurrentUser();
		if(tdUser == null)
		{
			return "redirect:/modile/login";
		}
		// 系统配置
		map.addAttribute("system", getSystem());
		if(addressId != null)
		{
			TdUserAddress userAddress = tdUserAddressService.findOne(addressId);
			if(userAddress != null && userAddress.getUid() == tdUser.getUid())
			{
				map.addAttribute("address",userAddress);
				Integer regionId = userAddress.getRegionId();
				Map<String, Object> regionMap = tdUserAddressService.getUserDistrictIdByRegionId(new HashMap<String,Object>(),regionId);
				map.addAllAttributes(regionMap);
			}
		}
		return "/mobile/shoppingCart/selectAddressAdd";
	}
}
