package com.tiandu.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.common.utils.DateUtil;
import com.tiandu.common.utils.MessageSender;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.complaint.entity.TdComplaint;
import com.tiandu.complaint.service.TdComplaintService;
import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.entity.TdBrancheCompany;
import com.tiandu.custom.entity.TdExperienceStore;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.entity.TdUserIntegralLog;
import com.tiandu.custom.search.TdAgentSearchCriteria;
import com.tiandu.custom.search.TdBrancheCompanySearchCriteria;
import com.tiandu.custom.search.TdExperienceStoreSearchCriteria;
import com.tiandu.custom.service.TdAgentService;
import com.tiandu.custom.service.TdBrancheCompanyService;
import com.tiandu.custom.service.TdExperienceStoreService;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.custom.service.TdUserAddressService;
import com.tiandu.custom.service.TdUserIntegralService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.custom.vo.ProfitInfo;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.order.entity.TdJointOrder;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderAddress;
import com.tiandu.order.entity.TdOrderLog;
import com.tiandu.order.entity.TdOrderPay;
import com.tiandu.order.entity.TdOrderProduct;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.entity.TdOrderShipmentItem;
import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.entity.TdShoppingcartItem;
import com.tiandu.order.entity.mapper.TdJointOrderMapper;
import com.tiandu.order.entity.mapper.TdOrderAddressMapper;
import com.tiandu.order.entity.mapper.TdOrderLogMapper;
import com.tiandu.order.entity.mapper.TdOrderMapper;
import com.tiandu.order.entity.mapper.TdOrderProductMapper;
import com.tiandu.order.entity.mapper.TdOrderShipmentItemMapper;
import com.tiandu.order.entity.mapper.TdOrderShipmentMapper;
import com.tiandu.order.entity.mapper.TdOrderSkuMapper;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.service.TdOrderPayService;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.OrderCancel;
import com.tiandu.order.vo.OrderForm;
import com.tiandu.order.vo.OrderPay;
import com.tiandu.order.vo.OrderRefund;
import com.tiandu.order.vo.ShoppingcartVO;
import com.tiandu.product.entity.TdAgentProduct;
import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.service.TdAgentProductService;
import com.tiandu.product.service.TdProductService;
import com.tiandu.product.service.TdProductSkuService;
import com.tiandu.product.service.TdProductStatService;
import com.tiandu.system.entity.TdBenefit;
import com.tiandu.system.search.TdBenefitSearchCriteria;
import com.tiandu.system.service.TdBenefitService;
import com.tiandu.system.utils.ConfigUtil;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdOrderService")
public class TdOrderServiceImpl implements TdOrderService{

	@Autowired
	private TdOrderMapper tdOrderMapper;
	@Autowired
	private TdOrderSkuMapper tdOrderSkuMapper;
	@Autowired
	private TdOrderAddressMapper tdOrderAddressMapper;
	@Autowired
	private TdOrderShipmentMapper tdOrderShipmentMapper;
	@Autowired
	private TdOrderShipmentItemMapper tdOrderShipmentItemMapper;
	@Autowired
	private TdOrderLogMapper tdOrderLogMapper;
	@Autowired
	private TdOrderProductMapper tdOrderProductMapper;
	@Autowired
	private TdJointOrderMapper tdJointOrderMapper;
	@Autowired
	private TdUserIntegralService tdUserIntegralService;
	@Autowired
	private TdUserAddressService tdUserAddressService;
	@Autowired
	private TdComplaintService tdComplaintService;
	@Autowired
	private TdBrancheCompanyService tdBrancheCompanyService;
	@Autowired
	private TdAgentService tdAgentService;
	@Autowired
	private TdAgentProductService tdAgentProductService;
	@Autowired
	private TdBenefitService tdBenefitService;
	@Autowired
	private TdUserService tdUserService;
	@Autowired
	private TdUserAccountService tdUserAccountService;
	@Autowired
	private TdDistrictService tdDistrictService;
	@Autowired
	private TdExperienceStoreService tdExperienceStoreService;
	@Autowired
	private TdProductService tdProductService;
	@Autowired
	private TdProductSkuService tdProductSkuService;
	
	@Autowired
	private TdOrderPayService tdOrderPayService;
	
	@Autowired
	private TdProductStatService tdProductStatService;
	
	@Autowired
	private ConfigUtil configUtil;

	private final Logger logger = Logger.getLogger(getClass());
	
	@Override
	public Integer deleteByPrimaryKey(Integer orderId) {
		return tdOrderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public Integer save(TdOrder e) {
		if(null!=e.getOrderId()){//更新
			return tdOrderMapper.updateByPrimaryKey(e);
		}else{
			return tdOrderMapper.insert(e);
		}
	}

	@Override
	public TdOrder findOne(Integer id) {
		return tdOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public TdOrder findDetail(Integer id) {
		TdOrder order = tdOrderMapper.findDetail(id);
		if(null!=order && null!=order.getOrderId()){
			//查询订单货品
			List<TdOrderSku> skuList = tdOrderSkuMapper.findByOrderId(id);
			order.setSkuList(skuList);
			//查询订单收货地址
			TdOrderAddress address = tdOrderAddressMapper.selectByPrimaryKey(id);
			order.setOrderAddress(address);
			//代理商产品订单、图片订单查询商品详情
			if(ConstantsUtils.ORDER_KIND_AGENTPRODUCT.equals(order.getOrderType())||ConstantsUtils.ORDER_KIND_IMAGEPRODUCT.equals(order.getOrderType())){
				List<TdOrderProduct> productList = tdOrderProductMapper.findByOrderId(order.getOrderId());
				order.setProductList(productList);
			}
		}
		return order;
	}

	@Override
	public TdOrder findOrderWithOrderSku(Integer id) {
		TdOrder order = tdOrderMapper.findDetail(id);
		if(null!=order && null!=order.getOrderId()){
			//查询订单货品
			List<TdOrderSku> skuList = tdOrderSkuMapper.findByOrderId(id);
			order.setSkuList(skuList);
		}
		return order;
	}

	@Override
	public List<TdOrder> findBySearchCriteria(TdOrderSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdOrderMapper.countByCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdOrderMapper.findBySearchCriteria(sc);
	}

	@Override
	public Integer countByCriteria(TdOrderSearchCriteria sc) {
		return tdOrderMapper.countByCriteria(sc);
	}

	@Override
	public OperResult shiporder(TdOrderShipment shipment) {
		OperResult result = new OperResult();
		TdOrder order = this.findOrderWithOrderSku(shipment.getOrderId());
		//已发货的不能再次发货
		if(null==order||null==order.getOrderId() || ConstantsUtils.ORDER_SHIPMENT_STATUS_SHIPPED.equals(order.getShipmentStatus())){
			result.setFailMsg("已发货的不能再次发货！");
			return result;
		}
		List<TdOrderSku> skuList = order.getSkuList();
		if(null==skuList || skuList.size()<1){
			result.setFailMsg("数据错误，请重新操作！");
			return result;
		}
		shipment.setType(ConstantsUtils.ORDERSHIPMENT_TYPE_SHIP);
		shipment.setSupplyId(order.getSupplierId());
		tdOrderShipmentMapper.insert(shipment);
		List<TdOrderShipmentItem> itemList = new ArrayList<TdOrderShipmentItem>();
		for(TdOrderSku sku : skuList){
			TdOrderShipmentItem item = new TdOrderShipmentItem();
			item.setOrderSkuId(sku.getOrderSkuId());
			item.setQuantity(sku.getQuantity());
			item.setShipmentId(shipment.getId());
			itemList.add(item);
		}
		shipment.setItemList(itemList);
		tdOrderShipmentItemMapper.insertOrderShipmentItems(shipment);
		//未发货订单更新发货状态
		if(ConstantsUtils.ORDER_SHIPMENT_STATUS_UNSHIPPED.equals(order.getShipmentStatus())){
			TdOrder uporder = new TdOrder();
			uporder.setOrderId(order.getOrderId());
			uporder.setUpdateBy(shipment.getUpdateBy());
			uporder.setUpdateTime(shipment.getUpdateTime());
			uporder.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_SHIPPED);
			uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_SHIPPMENTED);
			tdOrderMapper.updateByPrimaryKeySelective(uporder);
		}
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(shipment.getUpdateBy());
		log.setCreateTime(shipment.getUpdateTime());
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_SHIP);
		log.setNote("订单进行发货操作");
		tdOrderLogMapper.insert(log);
		result.setFlag(true);
		return result;
	}

	@Override
	public OperResult refundorder(OrderRefund refund) {
		OperResult result = new OperResult();
		TdOrder order = this.findOne(refund.getOrderId());
		//未支付、全部退款的不能再次退款
		if(null==order||null==order.getOrderId() || ConstantsUtils.ORDER_PAY_STATUS_UNPAY.equals(order.getPayStatus()) || ConstantsUtils.ORDER_PAY_STATUS_ALL_REFUND.equals(order.getPayStatus())){
			result.setFailMsg("未支付、全部退款的不能再次退款！");
			return result;
		}
		
		TdOrder uporder = new TdOrder();
		uporder.setOrderId(order.getOrderId());
		uporder.setUpdateBy(refund.getCreateBy());
		uporder.setUpdateTime(refund.getCreateTime());
		BigDecimal refundAmount = order.getRefundAmount().add(refund.getRefundAmount());
		if(BigDecimal.ZERO.compareTo(order.getPayAmount())>0){
			result.setFailMsg("退款金额不能小于0！");
			return result;
		}
		if(refundAmount.compareTo(order.getPayAmount())>0){
			result.setFailMsg("退款金额不能大于支付金额！");
			return result;
		}else if(refundAmount.compareTo(order.getPayAmount().subtract(order.getRefundAmount()))>0){
			result.setFailMsg("退款金额不能大于支付金额！");
			return result;
		}else if(refundAmount.compareTo(order.getPayAmount().subtract(order.getRefundAmount()))==0){
			uporder.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_ALL_REFUND);
			uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		}else{
			uporder.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_PART_REFUND);
		}
		uporder.setRefundAmount(refundAmount);
		uporder.setGainPoints(0);//退款赠送积分清零
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(refund.getCreateBy());
		log.setCreateTime(refund.getCreateTime());
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_REFUND);
		log.setNote("订单进行退款操作：退款金额 "+refund.getRefundAmount());
		tdOrderLogMapper.insert(log);
		//返回钱包余额
		TdUserAccount account = tdUserAccountService.findByUid(order.getUserId());
		account.setUpdateBy(1);
		account.setUpdateTime(refund.getCreateTime());
		TdUserAccountLog alog = new TdUserAccountLog();
		alog.setPreamount(account.getAmount());
		alog.setUid(account.getUid());
		alog.setUpamount(refund.getRefundAmount());
		alog.setType(TdUserAccountLog.USERACCOUNTLOG_TYPE_ORDER_REFUND);
		alog.setNote("订单退款，订单编号："+order.getOrderNo());
    	alog.setCreateTime(refund.getCreateTime());
    	alog.setRelation(null);
		tdUserAccountService.addAmount(account, alog);
		//已分润订单进行回扣操作
		if(order.getBenefited().equals(2)&&BigDecimal.ZERO.compareTo(order.getBenefitAmount())<0){
			backBenefitOrder(order);
		}
		result.setFlag(true);
		return result;
	}

	private void backBenefitOrder(TdOrder order) {
		Date now  = new Date();
		//分润花费金额
		BigDecimal totalBenefitAmount = BigDecimal.ZERO;
		//订单用于分润金额
		BigDecimal orderBenefitAmount = BigDecimal.ZERO;
		
		if(order.getBenefited().equals(2)&&BigDecimal.ZERO.compareTo(order.getBenefitAmount())<0){//已分润和分润金额大于零的进行扣润
			//订单用户信息
			TdUser orderUser = tdUserService.findOneWithAccount(order.getUserId());
			if(ConstantsUtils.ORDER_KIND_AGENTPRODUCT.equals(order.getOrderType())){//代理产品分润
				//代理产品添加代理
				TdOrderProduct orderProduct = tdOrderProductMapper.findByOrderIdAndTypeId(order.getOrderId(), Byte.valueOf("1"));
				if(null!=orderProduct){
						BigDecimal amount = orderProduct.getItemPrice().subtract(orderProduct.getSupplierPrice());
						TdBenefitSearchCriteria sc = new TdBenefitSearchCriteria();
						sc.setFlag(false);
						sc.setTypeId(orderProduct.getItemId());
						List<TdBenefit> benefitList = tdBenefitService.findBySearchCriteria(sc);
						
						//单类代理分润
						BigDecimal agentBenefitAmount = displayAgentBenefit(BigDecimal.ZERO.subtract(amount), order, orderUser, orderProduct, benefitList, 3, now);
						totalBenefitAmount = totalBenefitAmount.add(agentBenefitAmount);
						
						//分公司分润
						BigDecimal branchBenefitAmount = displayBranchBenefit(BigDecimal.ZERO.subtract(amount), order, orderUser, benefitList, 3, now);
						totalBenefitAmount = totalBenefitAmount.add(branchBenefitAmount);
						
						//三级分销分润
						BigDecimal distriBenefitAmount = displayDistributionBenefit(BigDecimal.ZERO.subtract(amount), order, orderUser, benefitList, 3, now);
						totalBenefitAmount = totalBenefitAmount.add(distriBenefitAmount);
				}
			}else if(ConstantsUtils.ORDER_KIND_ZEROPRODUCT.equals(order.getOrderType())){//零元购商品分润
				//普通商品分润设置
				TdBenefitSearchCriteria sc = new TdBenefitSearchCriteria();
				/*sc.setFlag(false);
				sc.setTypeId(8);
				List<TdBenefit> benefitList = tdBenefitService.findBySearchCriteria(sc);*/
				//零元商品分润设置
				sc.setFlag(false);
				sc.setTypeId(9);
				List<TdBenefit> zerobenefitList = tdBenefitService.findBySearchCriteria(sc);
				
				//获取零元三级分润金额
				BigDecimal zeroAmount = configUtil.getZeroProductBenefitAmount();
				
				List<TdOrderSku> orderSkuList = tdOrderSkuMapper.findByOrderId(order.getOrderId());
				if(null!=orderSkuList && orderSkuList.size()>0){
					//单类代理分润
					for(TdOrderSku ordersku : orderSkuList){
						//零元商品分润，不再参与普通商品分润
						if(BigDecimal.ZERO.compareTo(ordersku.getBenefitAmount())<0){//有利润空间才分润
							BigDecimal amount = ordersku.getBenefitAmount();
							if(zeroAmount.compareTo(amount)<=0){
								//三级分销分润
								BigDecimal distBenefitAmount = displayDistributionBenefit(BigDecimal.ZERO.subtract(zeroAmount), order, orderUser, zerobenefitList, 4, now);
								totalBenefitAmount = totalBenefitAmount.add(distBenefitAmount);	
								amount = amount.subtract(zeroAmount);
								
								if(BigDecimal.ZERO.compareTo(amount)<0){
									//分公司分润
									BigDecimal branchBenefitAmount = displayBranchBenefit(BigDecimal.ZERO.subtract(amount), order, orderUser, zerobenefitList, 3, now);
									totalBenefitAmount = totalBenefitAmount.add(branchBenefitAmount);
								}
							}
						}							
					}
				}
			}
			//更新订单分润信息
			TdOrder uporder2 = new TdOrder();
			uporder2.setOrderId(order.getOrderId());
			
			uporder2.setBenefitAmount(totalBenefitAmount.add(order.getBenefitAmount()));
			uporder2.setUpdateBy(1);
			uporder2.setUpdateTime(now);
			uporder2.setBenefited(5);
			tdOrderMapper.updateByPrimaryKeySelective(uporder2);
		}
		
	}

	@Override
	public OperResult completeorder(Integer id) {
		OperResult result = new OperResult();
		TdOrder order = this.findOne(id);
		//订单不存在
		if(null==order||null==order.getOrderId()){
			result.setFailMsg("订单不存在！");
			return result;
		}
		//已完成的订单的不能进行完成操作
		if(ConstantsUtils.ORDER_STATUS_COMPLETE.equals(order.getOrderStatus())){
			result.setFailMsg("已完成的订单的不能进行完成操作！");
			return result;
		}
		
		//未支付的订单的不能进行完成操作
		if(ConstantsUtils.ORDER_PAY_STATUS_UNPAY.equals(order.getPayStatus())){
			result.setFailMsg("未支付的订单的不能进行完成操作！");
			return result;
		}
		
		//开始操作
		Date now  = new Date();
		TdOrder uporder = new TdOrder();
		uporder.setOrderId(order.getOrderId());
		uporder.setUpdateBy(1);
		uporder.setUpdateTime(now);
		uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_COMPLETE);
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(1);
		log.setCreateTime(now);
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_COMPLETE);
		log.setNote("订单进行完成操作");
		tdOrderLogMapper.insert(log);
		//订单完成赠送积分
		if(order.getGainPoints().compareTo(0)>0){
			TdUserIntegral userIntegral = tdUserIntegralService.findOne(order.getUserId());
			userIntegral.setUpdateBy(0);
			userIntegral.setUpdateTime(now);
			TdUserIntegralLog integralLog = new TdUserIntegralLog();
			integralLog.setType(TdUserIntegralLog.USERINTEGRALLOG_TYPE_ORDER_SEND);
			integralLog.setCreateTime(now);
			integralLog.setPreintegral(userIntegral.getIntegral());
			integralLog.setIntegral(order.getGainPoints());
			integralLog.setUid(order.getUserId());
			integralLog.setNote("订单赠送积分,订单编号："+order.getOrderNo());
			integralLog.setRelation("");
			tdUserIntegralService.addIntegral(userIntegral, integralLog);
		}
		
		//分润开始
		this.benefitOrder(order);
		
		//货款给到供应商钱包里
		this.castCash(order);
		
		result.setFlag(true);
		return result;
	}

	@Override
	public OperResult completeOrder(TdOrder order, TdUser user) {
		logger.error("completeOrder  start: orderno= "+order.getOrderNo());
		OperResult result = new OperResult();
		//已完成的订单的不能进行完成操作
		if(ConstantsUtils.ORDER_STATUS_COMPLETE.equals(order.getOrderStatus())){
			result.setFailMsg("已完成的订单的不能进行完成操作！");
			return result;
		}
		
		//未支付的订单的不能进行完成操作
		if(ConstantsUtils.ORDER_PAY_STATUS_UNPAY.equals(order.getPayStatus())){
			result.setFailMsg("未支付的订单的不能进行完成操作！");
			return result;
		}
		
		//开始操作
		Date now  = new Date();
		TdOrder uporder = new TdOrder();
		uporder.setOrderId(order.getOrderId());
		uporder.setUpdateBy(user.getUid());
		uporder.setUpdateTime(now);
		uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_COMPLETE);
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(user.getUid());
		log.setCreateTime(now);
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_COMPLETE);
		log.setNote("订单进行完成操作");
		tdOrderLogMapper.insert(log);
		
		//订单完成赠送积分
		if(order.getGainPoints().compareTo(0)>0){
			TdUserIntegral userIntegral = tdUserIntegralService.findOne(order.getUserId());
			userIntegral.setUpdateBy(0);
			userIntegral.setUpdateTime(now);
			TdUserIntegralLog integralLog = new TdUserIntegralLog();
			integralLog.setType(TdUserIntegralLog.USERINTEGRALLOG_TYPE_ORDER_SEND);
			integralLog.setCreateTime(now);
			integralLog.setPreintegral(userIntegral.getIntegral());
			integralLog.setIntegral(order.getGainPoints());
			integralLog.setUid(order.getUserId());
			integralLog.setNote("订单赠送积分,订单编号："+order.getOrderNo());
			integralLog.setRelation("");
			tdUserIntegralService.addIntegral(userIntegral, integralLog);
		}
		
		//分润
		this.benefitOrder(order);
		logger.error("completeOrder  end: orderno= "+order.getOrderNo());
		
		//货款给到供应商钱包里
		this.castCash(order);
		
		result.setFlag(true);
		return result;
	}

	private void castCash(TdOrder order) {
		Date now = new Date();
		TdUserAccount account = tdUserAccountService.findByUid(order.getSupplierId());
		if(null!=account){
			BigDecimal benefitAmount = order.getPayAmount().subtract(order.getRefundAmount()).subtract(order.getBenefitAmount());
			account.setUpdateBy(1);
			account.setUpdateTime(now);
			TdUserAccountLog alog = new TdUserAccountLog();
			alog.setPreamount(account.getAmount());
			alog.setUid(account.getUid());
			alog.setUpamount(benefitAmount);
			alog.setType(TdUserAccountLog.USERACCOUNTLOG_TYPE_PRODUCT_INCOME);
			alog.setNote("订单完成，货款收入，订单编号："+order.getOrderNo());
	    	alog.setCreateTime(now);
	    	alog.setRelation(null);
			tdUserAccountService.addAmount(account, alog);
		}
		
	}

	/**
	 * 保存完整订单生态
	 */
	@Override
	public TdJointOrder saveOrderFull(TdUser currUser, OrderForm orderForm, ShoppingcartVO shoppingcart) throws RuntimeException{
		Date now = new Date();
		Integer orderdeliveryintegral = configUtil.getOrderDeliveryIntegral(); //订单金额获取积分比例
		TdJointOrder torder = new TdJointOrder();
		torder.setAmount(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount()));
		//赠送积分
		if(orderdeliveryintegral>0){
			torder.setGainPoints(torder.getAmount().divide(new BigDecimal(orderdeliveryintegral)).setScale(0, BigDecimal.ROUND_FLOOR).intValue());
		}else{
			torder.setGainPoints(0);
		}
		shoppingcart.setGainPoints(torder.getGainPoints());
		//收货地址
		if(null!=orderForm.getAddressId() && orderForm.getAddressId()>0){
			TdUserAddress address = tdUserAddressService.findOne(orderForm.getAddressId());
			orderForm.setUserAddress(address);
		}
		
		torder.setPaymentId(orderForm.getPaymentId());
		torder.setStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
		torder.setCreateTime(now);
		torder.setUpdateTime(now);
		torder.setJno(WebUtils.generateJointOrderNo());
		if(orderForm.getUsePoints()){
			torder.setAmount(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount()));
		}else{
			torder.setAmount(shoppingcart.getTotalAmount());
		}
		// 先保存才有ID
		tdJointOrderMapper.insert(torder);
		
		if(shoppingcart.getCombiningOrder()){//拆单
			//拆分购物车
			List<ShoppingcartVO> cartList = this.splitShoppingcart(shoppingcart);
			//分别生成订单
			for(ShoppingcartVO cart : cartList){
				this.insertOrder(currUser, orderForm, cart, torder, now);
			}
		}else{
			//普通订单
			if(shoppingcart.getPtype()==1){
				TdOrder order = this.insertOrder(currUser, orderForm, shoppingcart, torder, now);
//				torder.setJno(order.getOrderNo());
			
			}else if(shoppingcart.getPtype()==2){//单代订单
				TdOrder order = this.insertAgentOrder(currUser, orderForm, shoppingcart, torder, now);
//				torder.setJno(order.getOrderNo());
			}else if(shoppingcart.getPtype()==3){//分公司订单
				TdOrder order = this.insertBrachOrder(currUser, orderForm, shoppingcart, torder, now);
//				torder.setJno(order.getOrderNo());
			}else if(shoppingcart.getPtype()==4){//图片美化订单
				TdOrder order = this.insertImageOrder(currUser, orderForm, shoppingcart, torder, now);
//				torder.setJno(order.getOrderNo());
			}else if(shoppingcart.getPtype()==5){//供应商订单
				TdOrder order = this.insertSuppilerOrder(currUser, orderForm, shoppingcart, torder, now);
//				torder.setJno(order.getOrderNo());
			}else if(shoppingcart.getPtype()==6){
				TdOrder order = this.insertZeroOrder(currUser, orderForm, shoppingcart, torder, now);
			}
			
//			tdJointOrderMapper.updateByPrimaryKey(torder);
		}		
		return torder;
	}
	/**
	 * 生成零元购订单
	 * @param currUser
	 * @param orderForm
	 * @param shoppingcart
	 * @param torder
	 * @param now
	 * @return
	 */
	private TdOrder insertZeroOrder(TdUser currUser, OrderForm orderForm, ShoppingcartVO shoppingcart, TdJointOrder torder, Date now) {
		TdOrder order = new TdOrder();
		order.setCreateTime(now);
		order.setGainPoints(shoppingcart.getGainPoints());
		order.setCommented(false);
		order.setBenefited(1);
		order.setItemNum(shoppingcart.getTotalcount());
		order.setJointId(torder.getId());//联合订单
		order.setOrderNo(WebUtils.generateOrderNo());
		order.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		order.setOrderType(ConstantsUtils.ORDER_KIND_ZEROPRODUCT);
		order.setPaymentId(orderForm.getPaymentId());
		if(orderForm.getUsePoints()){
			order.setUsedPoint(shoppingcart.getTotalPointsUsed());
			order.setPointAmount(shoppingcart.getTotalPointAmount());
			order.setPayAmount(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount()));
		}else{
			order.setUsedPoint(0);
			order.setPointAmount(BigDecimal.ZERO);
			order.setPayAmount(shoppingcart.getTotalAmount());
		}
		order.setPostage(shoppingcart.getTotalPostage());
		order.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
		order.setPaymentId(orderForm.getPaymentId());
		order.setProductAmount(shoppingcart.getTotalProductAmount());
		order.setRefundAmount(BigDecimal.ZERO);
		order.setBenefitAmount(BigDecimal.ZERO);
		order.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_UNSHIPPED);
		order.setTotalAmount(shoppingcart.getTotalAmount());
		order.setUpdateBy(currUser.getUid());
		order.setUpdateTime(now);
		order.setUserId(currUser.getUid());
		order.setUserMessage(orderForm.getUserMsg());
		order.setSupplierId(shoppingcart.getSupplierId());
		this.save(order);
		//保存订单货品
		for(TdShoppingcartItem item : shoppingcart.getItemList()){
			if(item.getQuantity()>item.getProductSku().getStock()){
				throw new RuntimeException("商品"+item.getProduct().getName()+"库存不足!");
			}
			if(!item.getProduct().getOnshelf()||!item.getProduct().getStatus().equals(Byte.valueOf("1"))){
				throw new RuntimeException("商品"+item.getProduct().getName()+"已经下架!");
			}
			TdOrderSku sku = new TdOrderSku();
			sku.setDisplaySpecifications(item.getProductSku().getSpecifications());
			sku.setItemType(item.getProduct().getKind());
			sku.setOrderId(order.getOrderId());
			sku.setProductSkuId(item.getProductSkuId());
			sku.setProductId(item.getProductId());
			sku.setProductImage(tdProductService.findOne(item.getProductId()).getImageUrl());
			if(ConstantsUtils.PRODUCT_KIND_ZEROBUY.equals(sku.getItemType())){
				sku.setPrice(item.getProduct().getPostage());
			}else{
				sku.setPrice(item.getProductSku().getSalesPrice());
			}
			sku.setSupplierPrice(item.getProductSku().getSupplierPrice());
			sku.setProductName(item.getProduct().getName());
			sku.setProductSkuCode(item.getProductSku().getSkuCode());
			sku.setQuantity(item.getQuantity());
			sku.setBackQuantity(0);
			tdOrderSkuMapper.insert(sku);
			//更新货品库存
			tdProductSkuService.updateStock(item.getProductSkuId(),-item.getQuantity());
			//更新总库存
			tdProductService.updateStock(item.getProductId(),-item.getQuantity());
			
			// 更新销量
			tdProductStatService.updateByCount(item.getProductId(), item.getQuantity());
		}
		//扣除抵扣积分
		if(order.getUsedPoint()>0){
			TdUserIntegral userIntegral = tdUserIntegralService.findOne(currUser.getUid());
			if(null==userIntegral||userIntegral.getIntegral()<order.getUsedPoint()){
				throw new RuntimeException("积分不足，不能抵扣金额！");
			}
			userIntegral.setUpdateBy(0);
			userIntegral.setUpdateTime(now);
			TdUserIntegralLog integralLog = new TdUserIntegralLog();
			integralLog.setType(Byte.valueOf("4"));
			integralLog.setCreateTime(now);
			integralLog.setPreintegral(userIntegral.getIntegral());
			integralLog.setIntegral(-order.getUsedPoint());
			integralLog.setUid(currUser.getUid());
			integralLog.setNote("积分抵扣订单金额,订单编号："+order.getOrderNo()+" 消耗积分数量："+order.getUsedPoint()+" 抵扣金额：￥"+order.getPointAmount());
			integralLog.setRelation("");
			tdUserIntegralService.addIntegral(userIntegral, integralLog);
		}
		//保存收货地址
		if(null!=orderForm.getUserAddress()){
			TdUserAddress address = orderForm.getUserAddress();
			TdOrderAddress orderAddress = new TdOrderAddress();
			orderAddress.setOrderId(order.getOrderId());
			orderAddress.setAddress(address.getAddress());
			orderAddress.setCustomerName(address.getName());
			orderAddress.setRegionFullName(address.getFullAddress());
			orderAddress.setTelphone(address.getTelphone());
			orderAddress.setRegionId(address.getRegionId());
			tdOrderAddressMapper.insert(orderAddress);			
		}
		//保存日志
		
		return order;
	}

	/**
	 * 生成订单
	 * @param currUser
	 * @param orderForm
	 * @param shoppingcart
	 * @param torder
	 * @param now
	 * @return
	 */
	private TdOrder insertOrder(TdUser currUser, OrderForm orderForm, ShoppingcartVO shoppingcart,TdJointOrder torder, Date now) throws RuntimeException {
		TdOrder order = new TdOrder();
		order.setCreateTime(now);
		order.setGainPoints(shoppingcart.getGainPoints());
		order.setCommented(false);
		order.setBenefited(1);
		order.setItemNum(shoppingcart.getTotalcount());
		order.setJointId(torder.getId());//联合订单
		order.setOrderNo(WebUtils.generateOrderNo());
		order.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		order.setOrderType(ConstantsUtils.ORDER_KIND_COMMON);
		order.setPaymentId(orderForm.getPaymentId());
		if(orderForm.getUsePoints()){
			order.setUsedPoint(shoppingcart.getTotalPointsUsed());
			order.setPointAmount(shoppingcart.getTotalPointAmount());
			order.setPayAmount(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount()));
		}else{
			order.setUsedPoint(0);
			order.setPointAmount(BigDecimal.ZERO);
			order.setPayAmount(shoppingcart.getTotalAmount());
		}
		order.setPostage(shoppingcart.getTotalPostage());
		order.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
		order.setPaymentId(orderForm.getPaymentId());
		order.setProductAmount(shoppingcart.getTotalProductAmount());
		order.setRefundAmount(BigDecimal.ZERO);
		order.setBenefitAmount(BigDecimal.ZERO);
		order.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_UNSHIPPED);
		order.setTotalAmount(shoppingcart.getTotalAmount());
		order.setUpdateBy(currUser.getUid());
		order.setUpdateTime(now);
		order.setUserId(currUser.getUid());
		order.setUserMessage(orderForm.getUserMsg());
		order.setSupplierId(shoppingcart.getSupplierId());
		this.save(order);
		//保存订单货品
		for(TdShoppingcartItem item : shoppingcart.getItemList()){
			if(item.getQuantity()>item.getProductSku().getStock()){
				throw new RuntimeException("商品"+item.getProduct().getName()+"库存不足!");
			}
			if(!item.getProduct().getOnshelf()||!item.getProduct().getStatus().equals(Byte.valueOf("1"))){
				throw new RuntimeException("商品"+item.getProduct().getName()+"已经下架!");
			}
			TdOrderSku sku = new TdOrderSku();
			sku.setDisplaySpecifications(item.getProductSku().getSpecifications());
			sku.setItemType(item.getProduct().getKind());
			sku.setOrderId(order.getOrderId());
			sku.setProductSkuId(item.getProductSkuId());
			sku.setProductId(item.getProductId());
			sku.setProductImage(tdProductService.findOne(item.getProductId()).getImageUrl());
			if(ConstantsUtils.PRODUCT_KIND_ZEROBUY.equals(sku.getItemType())){
				sku.setPrice(item.getProduct().getPostage());
			}else{
				sku.setPrice(item.getProductSku().getSalesPrice());
			}
			sku.setSupplierPrice(item.getProductSku().getSupplierPrice());
			sku.setProductName(item.getProduct().getName());
			sku.setProductSkuCode(item.getProductSku().getSkuCode());
			sku.setQuantity(item.getQuantity());
			sku.setBackQuantity(0);
			tdOrderSkuMapper.insert(sku);
			//更新货品库存
			tdProductSkuService.updateStock(item.getProductSkuId(),-item.getQuantity());
			//更新总库存
			tdProductService.updateStock(item.getProductId(),-item.getQuantity());
			
			// 更新销量
			tdProductStatService.updateByCount(item.getProductId(), item.getQuantity());
		}
		//扣除抵扣积分
		if(order.getUsedPoint()>0){
			TdUserIntegral userIntegral = tdUserIntegralService.findOne(currUser.getUid());
			if(null==userIntegral||userIntegral.getIntegral()<order.getUsedPoint()){
				throw new RuntimeException("积分不足，不能抵扣金额！");
			}
			userIntegral.setUpdateBy(1);
			userIntegral.setUpdateTime(now);
			TdUserIntegralLog integralLog = new TdUserIntegralLog();
			integralLog.setType(Byte.valueOf("4"));
			integralLog.setCreateTime(now);
			integralLog.setPreintegral(userIntegral.getIntegral());
			integralLog.setIntegral(-order.getUsedPoint());
			integralLog.setUid(currUser.getUid());
			integralLog.setNote("积分抵扣订单金额,订单编号："+order.getOrderNo()+" 消耗积分数量："+order.getUsedPoint()+" 抵扣金额：￥"+order.getPointAmount());
			integralLog.setRelation("");
			tdUserIntegralService.addIntegral(userIntegral, integralLog);
		}
		//保存收货地址
		if(null!=orderForm.getUserAddress()){
			TdUserAddress address = orderForm.getUserAddress();
			TdOrderAddress orderAddress = new TdOrderAddress();
			orderAddress.setOrderId(order.getOrderId());
			orderAddress.setAddress(address.getAddress());
			orderAddress.setCustomerName(address.getName());
			orderAddress.setRegionFullName(address.getFullAddress());
			orderAddress.setTelphone(address.getTelphone());
			orderAddress.setRegionId(address.getRegionId());
			tdOrderAddressMapper.insert(orderAddress);			
		}
		//保存日志
		
		return order;
	}
	/**
	 * 按供应商拆分购物车
	 * @param shoppingcart
	 * @return
	 */
	private List<ShoppingcartVO> splitShoppingcart(ShoppingcartVO shoppingcart) throws RuntimeException{
		Integer integralexchangerate = configUtil.getIntegralExchangerate(); //积分抵扣金额比例
		Integer commonproductpointpercent = configUtil.getCommonProductPointPercent(); //普通商品可积分抵扣的比例
		Integer partproductpointpercent = configUtil.getPartProductPointPercent(); //部分积分兑换商品可积分抵扣的比例
		
		List<ShoppingcartVO> cartList = new ArrayList<ShoppingcartVO>();
		for(Integer supplierId : shoppingcart.getSupplierIds()){
			ShoppingcartVO scart = new ShoppingcartVO();
			List<TdShoppingcartItem> itemList = new ArrayList<TdShoppingcartItem>();
			//获取货品
			for(TdShoppingcartItem item : shoppingcart.getItemList()){
				if(item.getQuantity()>item.getProductSku().getStock()){
					throw new RuntimeException("商品"+item.getProduct().getName()+"库存不足!");
				}
				if(item.getProduct().getUid().equals(supplierId)){
					itemList.add(item);
				}
			}
			scart.setItemList(itemList);
			scart.setSupplierId(supplierId);
			//重新计算新购物车
			computershoppingcart(scart, integralexchangerate, commonproductpointpercent, partproductpointpercent);
			//计算获得积分
			if(shoppingcart.getGainPoints()>0){
				BigDecimal samount = scart.getTotalAmount().subtract(scart.getTotalPointAmount());
				BigDecimal totalmount = shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount());
				BigDecimal pamount = samount.divide(totalmount,2, BigDecimal.ROUND_FLOOR);
				BigDecimal bamount = pamount.multiply(new BigDecimal(shoppingcart.getGainPoints()));
				Integer point = bamount.setScale(0, BigDecimal.ROUND_FLOOR).intValue();
				scart.setGainPoints(point);
			}else{
				scart.setGainPoints(0);
			}
			cartList.add(scart);
		}
		
		return cartList;
	}

	/**
	 * 重新计算新购物车
	 * @param cart
	 * @param integralexchangerate
	 * @param commonproductpointpercent
	 * @param partproductpointpercent
	 */
	private void computershoppingcart(ShoppingcartVO cart, Integer integralexchangerate, Integer commonproductpointpercent, Integer partproductpointpercent){
		List<TdShoppingcartItem> itemList = cart.getItemList();
		if(null!=itemList && itemList.size()>0){
			for(TdShoppingcartItem item : itemList){
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
				}else if(commonproductpointpercent>0 && integralexchangerate>0){
					pointAmount =  amount.multiply(new BigDecimal(commonproductpointpercent)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_FLOOR);
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
	}
	
	/**
	 * 生成单代订单
	 * @param currUser
	 * @param orderForm
	 * @param shoppingcart
	 * @param torder
	 * @param now
	 * @return
	 */
	private TdOrder insertAgentOrder(TdUser currUser, OrderForm orderForm, ShoppingcartVO shoppingcart,TdJointOrder torder, Date now) throws RuntimeException {
		TdOrder order = new TdOrder();
		order.setCreateTime(now);
		order.setGainPoints(shoppingcart.getGainPoints());
		order.setCommented(true);
		order.setBenefited(1);
		order.setItemNum(shoppingcart.getTotalcount());
		order.setJointId(torder.getId());//联合订单
		order.setOrderNo(WebUtils.generateOrderNo());
		order.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		order.setOrderType(ConstantsUtils.ORDER_KIND_AGENTPRODUCT);
		order.setPaymentId(orderForm.getPaymentId());
		if(orderForm.getUsePoints()){
			order.setUsedPoint(shoppingcart.getTotalPointsUsed());
			order.setPointAmount(shoppingcart.getTotalPointAmount());
			order.setPayAmount(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount()));
		}else{
			order.setUsedPoint(0);
			order.setPointAmount(BigDecimal.ZERO);
			order.setPayAmount(shoppingcart.getTotalAmount());
		}
		order.setPostage(shoppingcart.getTotalPostage());
		order.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
		order.setPaymentId(orderForm.getPaymentId());
		order.setProductAmount(shoppingcart.getTotalProductAmount());
		order.setRefundAmount(BigDecimal.ZERO);
		order.setBenefitAmount(BigDecimal.ZERO);
		order.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_UNSHIPPED);
		order.setTotalAmount(shoppingcart.getTotalAmount());
		order.setUpdateBy(currUser.getUid());
		order.setUpdateTime(now);
		order.setUserId(currUser.getUid());
		order.setUserMessage(orderForm.getUserMsg());
		order.setSupplierId(1);
		//保存订单详情
		TdAgent agent = shoppingcart.getAgent();
		TdAgentSearchCriteria sc = new TdAgentSearchCriteria();
		sc.setLevel(agent.getLevel());
		sc.setRegionId(agent.getRegionId());
		sc.setProductTypeId(agent.getProductTypeId());
		int count = tdAgentService.countByCriteria(sc);
		if(count>0){
			throw new RuntimeException("单类代理已存在，不能加入!");
		}
		this.save(order);
		TdOrderProduct orderproduct = new TdOrderProduct();
		orderproduct.setItemType(Byte.valueOf("1"));
		orderproduct.setItemId(orderForm.getAgentProductId());
		orderproduct.setItemPrice(shoppingcart.getAgentProduct().getSalesPrice());
		orderproduct.setSupplierPrice(shoppingcart.getAgentProduct().getSupplierPrice());
		orderproduct.setRegionId(agent.getRegionId());
		orderproduct.setLevel(agent.getLevel());
		orderproduct.setQuantity(1);
		orderproduct.setTitle(shoppingcart.getAgentProduct().getTitle());
		orderproduct.setProductTypeId(agent.getProductTypeId());
		orderproduct.setOrderId(order.getOrderId());
		orderproduct.setAttachment(shoppingcart.getAgentProduct().getImageUrl());
		tdOrderProductMapper.insert(orderproduct);
		//保存订单货品
		if(null!=shoppingcart.getItemList() && shoppingcart.getAgentProduct().getGift() && null!=orderForm.getProductId()){
			//保存商品包信息到订单详情
			TdOrderProduct orderproduct2 = new TdOrderProduct();
			orderproduct2.setItemType(Byte.valueOf("2"));
			orderproduct2.setItemId(orderForm.getProductId());
			orderproduct2.setItemPrice(shoppingcart.getProductPackage().getPrice());
			orderproduct2.setSupplierPrice(BigDecimal.ZERO);
			orderproduct2.setRegionId(0);
			orderproduct2.setLevel(0);
			orderproduct2.setQuantity(1);
			orderproduct2.setTitle(shoppingcart.getProductPackage().getName());
			orderproduct2.setProductTypeId(0);
			orderproduct2.setOrderId(order.getOrderId());
			orderproduct2.setAttachment(shoppingcart.getProductPackage().getImageUrl());
			tdOrderProductMapper.insert(orderproduct2);
			//保存商品包详情
			for(TdShoppingcartItem item : shoppingcart.getItemList()){
				TdOrderSku sku = new TdOrderSku();
				if(null!=item.getProductPackageItem().getProductSku().getSpecifications()){
					sku.setDisplaySpecifications(item.getProductPackageItem().getSpecifications());
				}
				sku.setItemType(item.getItemType().byteValue());
				sku.setOrderId(order.getOrderId());
				sku.setProductSkuId(item.getProductSkuId());
				sku.setProductId(item.getProductId());
				sku.setPrice(item.getProductPackageItem().getPrice());
				sku.setSupplierPrice(BigDecimal.ZERO);
				sku.setProductName(item.getProductPackageItem().getProductName());
				sku.setProductSkuCode("");
				sku.setQuantity(item.getQuantity());
				sku.setProductImage(item.getProductPackageItem().getProductImage());
				tdOrderSkuMapper.insert(sku);
			}
			//更新总库存
			tdProductService.updateStock(shoppingcart.getProductPackage().getId(),-1);
			//保存收货地址
			if(null!=orderForm.getUserAddress()){
				TdUserAddress address = orderForm.getUserAddress();
				TdOrderAddress orderAddress = new TdOrderAddress();
				orderAddress.setOrderId(order.getOrderId());
				orderAddress.setAddress(address.getAddress());
				orderAddress.setCustomerName(address.getName());
				orderAddress.setRegionFullName(address.getFullAddress());
				orderAddress.setTelphone(address.getTelphone());
				orderAddress.setRegionId(address.getRegionId());
				tdOrderAddressMapper.insert(orderAddress);			
			}
		}
		//保存日志
		
		return order;
	}
	/**
	 * 生成供应商订单
	 * @param currUser
	 * @param orderForm
	 * @param shoppingcart
	 * @param torder
	 * @param now
	 * @return
	 */
	private TdOrder insertSuppilerOrder(TdUser currUser, OrderForm orderForm, ShoppingcartVO shoppingcart,TdJointOrder torder, Date now) throws RuntimeException {
		TdOrder order = new TdOrder();
		order.setCreateTime(now);
		order.setGainPoints(shoppingcart.getGainPoints());
		order.setCommented(true);
		order.setBenefited(1);
		order.setItemNum(shoppingcart.getTotalcount());
		order.setJointId(torder.getId());//联合订单
		order.setOrderNo(WebUtils.generateOrderNo());
		order.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		order.setOrderType(ConstantsUtils.ORDER_KIND_AGENTPRODUCT);
		order.setPaymentId(orderForm.getPaymentId());
		if(orderForm.getUsePoints()){
			order.setUsedPoint(shoppingcart.getTotalPointsUsed());
			order.setPointAmount(shoppingcart.getTotalPointAmount());
			order.setPayAmount(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount()));
		}else{
			order.setUsedPoint(0);
			order.setPointAmount(BigDecimal.ZERO);
			order.setPayAmount(shoppingcart.getTotalAmount());
		}
		order.setPostage(shoppingcart.getTotalPostage());
		order.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
		order.setPaymentId(orderForm.getPaymentId());
		order.setProductAmount(shoppingcart.getTotalProductAmount());
		order.setRefundAmount(BigDecimal.ZERO);
		order.setBenefitAmount(BigDecimal.ZERO);
		order.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_UNSHIPPED);
		order.setTotalAmount(shoppingcart.getTotalAmount());
		order.setUpdateBy(currUser.getUid());
		order.setUpdateTime(now);
		order.setUserId(currUser.getUid());
		order.setUserMessage(orderForm.getUserMsg());
		order.setSupplierId(1);
		//保存订单详情
		if(ConstantsUtils.CUSTOMER_SUPPLIER_BUYED.equals(currUser.getTempsupplier())){
			throw new RuntimeException("用户已经拥有供应商，不能重复购买!");
		}
		this.save(order);
		TdOrderProduct orderproduct = new TdOrderProduct();
		orderproduct.setItemType(Byte.valueOf("1"));
		orderproduct.setItemId(orderForm.getAgentProductId());
		orderproduct.setItemPrice(shoppingcart.getAgentProduct().getSalesPrice());
		orderproduct.setSupplierPrice(shoppingcart.getAgentProduct().getSupplierPrice());
		orderproduct.setRegionId(null);
		orderproduct.setLevel(null);
		orderproduct.setQuantity(1);
		orderproduct.setTitle(shoppingcart.getAgentProduct().getTitle());
		orderproduct.setProductTypeId(null);
		orderproduct.setOrderId(order.getOrderId());
		orderproduct.setAttachment(shoppingcart.getAgentProduct().getImageUrl());
		tdOrderProductMapper.insert(orderproduct);
		//保存订单货品
		if(null!=shoppingcart.getItemList() && shoppingcart.getAgentProduct().getGift() && null!=orderForm.getProductId()){
			//保存商品包信息到订单详情
			TdOrderProduct orderproduct2 = new TdOrderProduct();
			orderproduct2.setItemType(Byte.valueOf("2"));
			orderproduct2.setItemId(orderForm.getProductId());
			orderproduct2.setItemPrice(shoppingcart.getProductPackage().getPrice());
			orderproduct2.setSupplierPrice(BigDecimal.ZERO);
			orderproduct2.setRegionId(0);
			orderproduct2.setLevel(0);
			orderproduct2.setQuantity(1);
			orderproduct2.setTitle(shoppingcart.getProductPackage().getName());
			orderproduct2.setProductTypeId(0);
			orderproduct2.setOrderId(order.getOrderId());
			orderproduct2.setAttachment(shoppingcart.getProductPackage().getImageUrl());
			tdOrderProductMapper.insert(orderproduct2);
			//保存商品包详情
			for(TdShoppingcartItem item : shoppingcart.getItemList()){
				TdOrderSku sku = new TdOrderSku();
				sku.setDisplaySpecifications(item.getProductPackageItem().getSpecifications());
				sku.setItemType(item.getItemType().byteValue());
				sku.setOrderId(order.getOrderId());
				sku.setProductSkuId(item.getProductSkuId());
				sku.setProductId(item.getProductId());
				sku.setPrice(item.getProductPackageItem().getPrice());
				sku.setSupplierPrice(BigDecimal.ZERO);
				sku.setProductName(item.getProductPackageItem().getProductName());
				sku.setProductSkuCode("");
				sku.setQuantity(item.getQuantity());
				sku.setProductImage(item.getProductPackageItem().getProductImage());
				tdOrderSkuMapper.insert(sku);
			}
			//更新总库存
			tdProductService.updateStock(shoppingcart.getProductPackage().getId(),-1);
			//保存收货地址
			if(null!=orderForm.getUserAddress()){
				TdUserAddress address = orderForm.getUserAddress();
				TdOrderAddress orderAddress = new TdOrderAddress();
				orderAddress.setOrderId(order.getOrderId());
				orderAddress.setAddress(address.getAddress());
				orderAddress.setCustomerName(address.getName());
				orderAddress.setRegionFullName(address.getFullAddress());
				orderAddress.setTelphone(address.getTelphone());
				orderAddress.setRegionId(address.getRegionId());
				tdOrderAddressMapper.insert(orderAddress);			
			}
		}
		//保存日志
		
		return order;
	}
	/**
	 * 生成分公司订单
	 * @param currUser
	 * @param orderForm
	 * @param shoppingcart
	 * @param torder
	 * @param now
	 * @return
	 */
	private TdOrder insertBrachOrder(TdUser currUser, OrderForm orderForm, ShoppingcartVO shoppingcart,TdJointOrder torder, Date now) throws RuntimeException {
		TdOrder order = new TdOrder();
		order.setCreateTime(now);
		order.setGainPoints(shoppingcart.getGainPoints());
		order.setCommented(true);
		order.setBenefited(1);
		order.setItemNum(shoppingcart.getTotalcount());
		order.setJointId(torder.getId());//联合订单
		order.setOrderNo(WebUtils.generateOrderNo());
		order.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		order.setOrderType(ConstantsUtils.ORDER_KIND_AGENTPRODUCT);
		order.setPaymentId(orderForm.getPaymentId());
		if(orderForm.getUsePoints()){
			order.setUsedPoint(shoppingcart.getTotalPointsUsed());
			order.setPointAmount(shoppingcart.getTotalPointAmount());
			order.setPayAmount(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount()));
		}else{
			order.setUsedPoint(0);
			order.setPointAmount(BigDecimal.ZERO);
			order.setPayAmount(shoppingcart.getTotalAmount());
		}
		order.setPostage(shoppingcart.getTotalPostage());
		order.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
		order.setPaymentId(orderForm.getPaymentId());
		order.setProductAmount(shoppingcart.getTotalProductAmount());
		order.setRefundAmount(BigDecimal.ZERO);
		order.setBenefitAmount(BigDecimal.ZERO);
		order.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_UNSHIPPED);
		order.setTotalAmount(shoppingcart.getTotalAmount());
		order.setUpdateBy(currUser.getUid());
		order.setUpdateTime(now);
		order.setUserId(currUser.getUid());
		order.setUserMessage(orderForm.getUserMsg());
		order.setSupplierId(1);
		this.save(order);
		//保存订单货品
		TdBrancheCompany branch = shoppingcart.getBranch();
		TdBrancheCompanySearchCriteria sc = new TdBrancheCompanySearchCriteria();
		sc.setLevel(branch.getLevel());
		sc.setRegionId(branch.getRegionId());
		int count = tdBrancheCompanyService.countByCriteria(sc);
		if(count>0){
			throw new RuntimeException("分公司已存在，不能加入!");
		}
		TdOrderProduct orderproduct = new TdOrderProduct();
		orderproduct.setItemType(Byte.valueOf("1"));
		orderproduct.setRegionId(branch.getRegionId());
		orderproduct.setLevel(branch.getLevel());
		orderproduct.setOrderId(order.getOrderId());
		orderproduct.setItemType(Byte.valueOf("1"));
		orderproduct.setItemId(orderForm.getAgentProductId());
		orderproduct.setItemPrice(shoppingcart.getAgentProduct().getSalesPrice());
		orderproduct.setSupplierPrice(shoppingcart.getAgentProduct().getSupplierPrice());
		orderproduct.setQuantity(1);
		orderproduct.setTitle(shoppingcart.getAgentProduct().getTitle());
		orderproduct.setAttachment(shoppingcart.getAgentProduct().getImageUrl());
		tdOrderProductMapper.insert(orderproduct);
		//保存订单货品
		if(null!=shoppingcart.getItemList() && shoppingcart.getAgentProduct().getGift() && null!=orderForm.getProductId()){
			//保存商品包信息到订单详情
			TdOrderProduct orderproduct2 = new TdOrderProduct();
			orderproduct2.setItemType(Byte.valueOf("2"));
			orderproduct2.setItemId(orderForm.getProductId());
			orderproduct2.setItemPrice(shoppingcart.getProductPackage().getPrice());
			orderproduct2.setSupplierPrice(BigDecimal.ZERO);
			orderproduct2.setRegionId(0);
			orderproduct2.setLevel(0);
			orderproduct2.setQuantity(1);
			orderproduct2.setTitle(shoppingcart.getProductPackage().getName());
			orderproduct2.setProductTypeId(0);
			orderproduct2.setOrderId(order.getOrderId());
			orderproduct2.setAttachment(shoppingcart.getProductPackage().getImageUrl());
			tdOrderProductMapper.insert(orderproduct2);
			//保存商品包详情
			for(TdShoppingcartItem item : shoppingcart.getItemList()){
				TdOrderSku sku = new TdOrderSku();
				sku.setDisplaySpecifications(item.getProductPackageItem().getSpecifications());
				sku.setItemType(item.getItemType().byteValue());
				sku.setOrderId(order.getOrderId());
				sku.setProductSkuId(item.getProductSkuId());
				sku.setProductId(item.getProductId());
				sku.setPrice(item.getProductPackageItem().getPrice());
				sku.setSupplierPrice(BigDecimal.ZERO);
				sku.setProductName(item.getProductPackageItem().getProductName());
				sku.setProductSkuCode("");
				sku.setQuantity(item.getQuantity());
				sku.setProductImage(item.getProductPackageItem().getProductImage());
				tdOrderSkuMapper.insert(sku);
			}
			//更新总库存
			tdProductService.updateStock(shoppingcart.getProductPackage().getId(),-1);
			//保存收货地址
			if(null!=orderForm.getUserAddress()){
				TdUserAddress address = orderForm.getUserAddress();
				TdOrderAddress orderAddress = new TdOrderAddress();
				orderAddress.setOrderId(order.getOrderId());
				orderAddress.setAddress(address.getAddress());
				orderAddress.setCustomerName(address.getName());
				orderAddress.setRegionFullName(address.getFullAddress());
				orderAddress.setTelphone(address.getTelphone());
				orderAddress.setRegionId(address.getRegionId());
				tdOrderAddressMapper.insert(orderAddress);			
			}
		}
		return order;
	}

	/**
	 * 生成图片美化订单
	 * @param currUser
	 * @param orderForm
	 * @param shoppingcart
	 * @param torder
	 * @param now
	 * @return
	 */
	private TdOrder insertImageOrder(TdUser currUser, OrderForm orderForm, ShoppingcartVO shoppingcart,TdJointOrder torder, Date now) throws RuntimeException {
		TdOrder order = new TdOrder();
		order.setCreateTime(now);
		order.setGainPoints(shoppingcart.getGainPoints());
		order.setCommented(true);
		order.setBenefited(4);//不参与分润
		order.setItemNum(shoppingcart.getTotalcount());
		order.setJointId(torder.getId());//联合订单
		order.setOrderNo(WebUtils.generateOrderNo());
		order.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		order.setOrderType(ConstantsUtils.ORDER_KIND_IMAGEPRODUCT);
		if(orderForm.getUsePoints()){
			order.setUsedPoint(shoppingcart.getTotalPointsUsed());
			order.setPointAmount(shoppingcart.getTotalPointAmount());
		}else{
			order.setUsedPoint(0);
			order.setPointAmount(BigDecimal.ZERO);
		}
		order.setPostage(BigDecimal.ZERO);
		order.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
		order.setPaymentId(orderForm.getPaymentId());
		order.setProductAmount(shoppingcart.getTotalProductAmount());
		order.setRefundAmount(BigDecimal.ZERO);
		order.setPayAmount(shoppingcart.getTotalAmount());
		order.setBenefitAmount(BigDecimal.ZERO);
		order.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_UNSHIPPED);
		order.setTotalAmount(shoppingcart.getTotalAmount());
		order.setUpdateBy(currUser.getUid());
		order.setUpdateTime(now);
		order.setUserId(currUser.getUid());
		order.setUserMessage(orderForm.getUserMsg());
		order.setSupplierId(1);
		this.save(order);
		//保存订单详情
		TdOrderProduct orderproduct = new TdOrderProduct();
		orderproduct.setItemType(Byte.valueOf("3"));//图片订单信息
		orderproduct.setItemId(orderForm.getProductId());
		orderproduct.setItemPrice(new BigDecimal(shoppingcart.getImageOrder().getPrice()));
		orderproduct.setSupplierPrice(BigDecimal.ZERO);
		orderproduct.setRegionId(0);
		orderproduct.setLevel(0);
		orderproduct.setQuantity(shoppingcart.getImageOrder().getImageNum());
		orderproduct.setTitle(shoppingcart.getImageOrder().getProduct().getName());
		orderproduct.setProductTypeId(0);
		orderproduct.setOrderId(order.getOrderId());
		orderproduct.setAttachment(shoppingcart.getImageOrder().getAttachmentString());
		tdOrderProductMapper.insert(orderproduct);
		//保存日志
		return order;
	}
	@Override
	public OperResult applyRefundOrder(TdOrder order, TdOrderShipment shipment,Integer skuId) {
		OperResult result = new OperResult();
		if(order.getOrderStatus() == ConstantsUtils.ORDER_STATUS_APPLYREFUND){
			result.setFailMsg("退款申请失败：此商品已经申请退款");
			return result;
		}
		if(order.getPayAmount().subtract(order.getRefundAmount()).compareTo(shipment.getReturnAmount())>=0){
			Date now = new Date();
			shipment.setStatus(Byte.valueOf("1"));
			shipment.setType(Byte.valueOf("2"));
			shipment.setCreateBy(order.getUserId());
			shipment.setUpdateBy(order.getUserId());
			shipment.setCreateTime(now);
			shipment.setUpdateTime(now);
			shipment.setSupplyId(order.getSupplierId());
			shipment.initVoucherImages();
			tdOrderShipmentMapper.insert(shipment);
			
			/**
			 * 添加退款详情  Max
			 */
			List<TdOrderShipmentItem> itemList = new ArrayList<TdOrderShipmentItem>();
			TdOrderSku sku = tdOrderSkuMapper.selectByPrimaryKey(skuId);
			if(null != sku){
				TdOrderShipmentItem item = new TdOrderShipmentItem();
				item.setOrderSkuId(sku.getOrderSkuId());
				item.setQuantity(sku.getQuantity());
				item.setShipmentId(shipment.getId());
				itemList.add(item);
				shipment.setItemList(itemList);
				tdOrderShipmentItemMapper.insertOrderShipmentItems(shipment);
			}
			order.setOrderStatus(ConstantsUtils.ORDER_STATUS_APPLYREFUND); // 订单状态改为申请退货
			this.save(order);
			
			result.setFlag(true);
			result.setFailMsg("退款申请成功，请等待商户确认。");
		}else{
			result.setFailMsg("退款申请失败：退款金额大于订单金额！");
		}
		return result;
	}

	@Override
	public OperResult complaintOrder(TdOrder order, TdComplaint complaint) {
		OperResult result = new OperResult();
		if(order.getOrderId()>0){
			Date now = new Date();
			complaint.setCreateTime(now);
			complaint.setUpdateBy(order.getUserId());
			complaint.setUpdateDate(now);
			complaint.setCuid(order.getSupplierId());
			complaint.setUid(order.getUserId());
			complaint.setStatus(Byte.valueOf("1"));
			tdComplaintService.insert(complaint);
			result.setFlag(true);
			result.setFailMsg("投诉已经提交，请等待平台处理。");
			//发送短信通知管理员
			String phoneNums = configUtil.getComplaintTelphone();
			if(StringUtils.isNotEmpty(phoneNums)){
				String message = DateUtil.convertDateTimeToString(now);
				String[] phones = phoneNums.split(",");
				List<String> phoneList = new ArrayList<>();
				for(String phone : phones){
					phoneList.add(phone);
				}
				List<String> datas = new ArrayList<>();
				datas.add(message);
				MessageSender ms = new MessageSender();
				ms.init();
				boolean isSendSuccess = ms.send(phoneList, ConstantsUtils.SMS_TEMPLATE_COMPLAINT, datas);
				if(!isSendSuccess){
					logger.error("投诉短信发送失败,投诉订单编号："+order.getOrderNo());		
				}
			}else{
				logger.error("投诉短信发送失败，系统配置未设置投诉管理员手机号码");
			}
			
		}else{
			result.setFailMsg("投诉提交失败!");
		}
		return result;
	}

	@Override
	public OperResult receiptOrder(TdOrder order, TdUser user) {
		OperResult result = new OperResult();
		//订单不存在
		if(null==order||null==order.getOrderId()){
			result.setFailMsg("订单不存在！");
			return result;
		}
		//已完成的订单的不能进行收货操作
		if(ConstantsUtils.ORDER_STATUS_COMPLETE.equals(order.getOrderStatus())){
			result.setFailMsg("已完成的订单的不能进行收货操作！");
			return result;
		}
		
		//未支付的订单的不能进行收货操作
		if(ConstantsUtils.ORDER_PAY_STATUS_UNPAY.equals(order.getPayStatus())){
			result.setFailMsg("未支付的订单的不能进行收货操作！");
			return result;
		}
		//已收货的订单的不能进行收货操作
		if(ConstantsUtils.ORDER_SHIPMENT_STATUS_RECEIPT.equals(order.getShipmentStatus())){
			result.setFailMsg("已收货的订单的不能进行收货操作！");
			return result;
		}
		
		//开始操作
		Date now  = new Date();
		TdOrder uporder = new TdOrder();
		uporder.setOrderId(order.getOrderId());
		uporder.setUpdateBy(user.getUid());
		uporder.setUpdateTime(now);
		uporder.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_RECEIPT);
		uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_RECEIPTED);
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(user.getUid());
		log.setCreateTime(now);
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_RECEIPT);
		log.setNote("订单进行收货操作");
		tdOrderLogMapper.insert(log);
		
		result.setFlag(true);
		return result;
	}

	@Override
	public OperResult payOrder(TdOrder order, OrderPay orderPay) throws RuntimeException {
		OperResult result = new OperResult();
		//订单不存在
		if(null==order||null==order.getOrderId()){
			result.setFailMsg("订单不存在！");
			return result;
		}
		//已完成的订单的不能进行支付操作
		if(ConstantsUtils.ORDER_STATUS_COMPLETE.equals(order.getOrderStatus())){
			result.setFailMsg("已完成的订单的不能进行收货操作！");
			return result;
		}
		
		//已支付的订单的不能进行支付操作
		if(ConstantsUtils.ORDER_PAY_STATUS_PAYED.equals(order.getPayStatus())){
			result.setFailMsg("已支付的订单的不能进行支付操作！");
			return result;
		}
		//金额不等不能进行支付操作
		if(order.getUnPayAmount().compareTo(orderPay.getPayAmount())!=0){
			result.setFailMsg("支付金额与订单金额不匹配不能进行支付操作！");
			return result;
		}
		//开始操作
		Date now  = new Date();
		TdOrder uporder = new TdOrder();
		uporder.setOrderId(order.getOrderId());
		uporder.setUpdateBy(orderPay.getCreateBy());
		uporder.setUpdateTime(now);
		uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_PAYED);
		uporder.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_PAYED);
		uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_PAYED);
		uporder.setPayAmount(order.getUnPayAmount());
		uporder.setPaymentId(orderPay.getPaymentId());
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(orderPay.getCreateBy());
		log.setCreateTime(now);
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_PAY);
		log.setNote("订单进行收款操作");
		tdOrderLogMapper.insert(log);
		
		//代理产品订单添加代理和计算分润
		if(ConstantsUtils.ORDER_KIND_AGENTPRODUCT.equals(order.getOrderType())){
			//代理产品添加代理
			TdOrderProduct orderProduct = tdOrderProductMapper.findByOrderIdAndTypeId(order.getOrderId(), Byte.valueOf("1"));
			TdOrderLog log2 = new TdOrderLog();
			log2.setOrderId(order.getOrderId());
			log2.setCreateBy(orderPay.getCreateBy());
			log2.setCreateTime(now);
			log2.setOperType(ConstantsUtils.ORDER_LOG_TYPE_AGENT);
			Boolean addagent = false;
			if(null!=orderProduct){
				TdAgentProduct agentProduct = tdAgentProductService.findOne(orderProduct.getItemId());
				if(null!=agentProduct){
					if(ConstantsUtils.AGENT_GROUPID_AGENT.equals(agentProduct.getGroupId())){//单代代理
						TdAgentSearchCriteria sc = new TdAgentSearchCriteria();
						sc.setLevel(agentProduct.getLevel());
						sc.setRegionId(orderProduct.getRegionId());
						sc.setProductTypeId(orderProduct.getProductTypeId());
						int count = tdAgentService.countByCriteria(sc);
						if(count>0){
							log2.setNote("订单生成代理操作失败：地区已经有单类代理了!");
						}else{
							TdAgent agent = tdAgentService.findByUid(order.getUserId());
							if(null!=agent){//用户已经购买代理
								if(agent.getLevel()>agentProduct.getLevel()){//已有代理级别大于先购买的代理级别，覆盖失败
									log2.setNote("订单生成代理操作失败：用户已拥有代理级别比现购买的代理级别更高，不能降级处理!");
								}else{
									agent.setLevel(agentProduct.getLevel());
									agent.setProductTypeId(orderProduct.getProductTypeId());
									agent.setRegionId(orderProduct.getRegionId());
									agent.setUpdateDate(now);
									tdAgentService.save(agent);
									addagent = true;
									log2.setNote("订单生成代理操作成功。");
								}
							}else{
								agent = new TdAgent();
								agent.setUid(order.getUserId());
								agent.setUpdateBy(1);
								agent.setLevel(agentProduct.getLevel());
								agent.setProductTypeId(orderProduct.getProductTypeId());
								agent.setRegionId(orderProduct.getRegionId());
								agent.setUpdateDate(now);
								tdAgentService.save(agent);
								addagent = true;
								log2.setNote("订单生成代理操作成功。");
							}							
						}
						
					}else if(ConstantsUtils.AGENT_GROUPID_BRANCH.equals(agentProduct.getGroupId())){//分公司
						TdBrancheCompanySearchCriteria sc = new TdBrancheCompanySearchCriteria();
						sc.setLevel(agentProduct.getLevel());
						sc.setRegionId(orderProduct.getRegionId());
						int count = tdBrancheCompanyService.countByCriteria(sc);
						if(count>0){
							log2.setNote("订单生成代理操作失败：地区已经有分公司了!");
						}else{
							TdBrancheCompany branch = tdBrancheCompanyService.findByUid(order.getUserId());
							if(null!=branch){//用户已经购买代理
								if(branch.getLevel()>agentProduct.getLevel()){//已有分公司级别大于现购买的分公司级别，覆盖失败
									log2.setNote("订单生成分公司操作失败：用户已拥有分公司级别比现购买的分公司级别更高，不能降级处理!");
								}else{
									branch.setLevel(agentProduct.getLevel());
									branch.setRegionId(orderProduct.getRegionId());
									branch.setUpdateTime(now);
									branch.setUpdateBy(1);
									tdBrancheCompanyService.save(branch);
									addagent = true;
									log2.setNote("订单生成分公司操作成功。");
								}
							}else{
								branch = new TdBrancheCompany();
								branch.setUid(order.getUserId());
								branch.setLevel(agentProduct.getLevel());
								branch.setRegionId(orderProduct.getRegionId());
								branch.setUpdateTime(now);
								branch.setUpdateBy(1);
								branch.setStatus(Byte.valueOf("1"));
								tdBrancheCompanyService.save(branch);
								addagent = true;
								log2.setNote("订单生成分公司操作成功。");
							}							
						}
					}else if(ConstantsUtils.AGENT_GROUPID_SUPPLIER.equals(agentProduct.getGroupId())){//供应商
						//检查用户是否已经是供应商资格
						TdUser user = tdUserService.findOne(order.getUserId());
						if(null!=user && !ConstantsUtils.CUSTOMER_SUPPLIER_BUYED.equals(user.getTempsupplier())){
							TdUser upuser = new TdUser();
							upuser.setTempsupplier(ConstantsUtils.CUSTOMER_SUPPLIER_BUYED);
							upuser.setUid(user.getUid());
							tdUserService.updateByPrimaryKeySelective(upuser);
							addagent = true;
							log2.setNote("订单生成供应商操作成功。");
						}else{
							log2.setNote("订单生成供应商失败，购买用户已经具有供应商资格，不能重复购买！");
						}
					}
				}else{
					log2.setNote("订单生成代理操作失败：代理产品未找到!");
				}
				//分润开始
				if(addagent){//生成代理成功才才分润
					benefitOrder(order);
				}
			}else{
				log2.setNote("订单生成代理操作失败：订单详情未找到!");
			}			
			tdOrderLogMapper.insert(log2);
		}else if(ConstantsUtils.ORDER_KIND_ZEROPRODUCT.equals(order.getOrderType())){//零元购订单立即分润
			benefitOrder(order);
		}
		
		result.setFlag(true);
		return result;
	}
	
	@Override
	public void benefitOrder(TdOrder order) throws RuntimeException {
		Date now  = new Date();
		//分润花费金额
		BigDecimal totalBenefitAmount = BigDecimal.ZERO;
		//订单用于分润金额
		BigDecimal orderBenefitAmount = BigDecimal.ZERO;
		
		if(order.getBenefited()==1||order.getBenefited()==3){
			//订单用户信息
			TdUser orderUser = tdUserService.findOneWithAccount(order.getUserId());
			if(ConstantsUtils.ORDER_KIND_AGENTPRODUCT.equals(order.getOrderType())){//代理产品分润
				//代理产品添加代理
				TdOrderProduct orderProduct = tdOrderProductMapper.findByOrderIdAndTypeId(order.getOrderId(), Byte.valueOf("1"));
				if(null!=orderProduct){
						BigDecimal amount = orderProduct.getItemPrice().subtract(orderProduct.getSupplierPrice());
						TdBenefitSearchCriteria sc = new TdBenefitSearchCriteria();
						sc.setFlag(false);
						sc.setTypeId(orderProduct.getItemId());
						List<TdBenefit> benefitList = tdBenefitService.findBySearchCriteria(sc);
						
						//单类代理分润
						BigDecimal agentBenefitAmount = displayAgentBenefit(amount, order, orderUser, orderProduct, benefitList, 3, now);
						totalBenefitAmount = totalBenefitAmount.add(agentBenefitAmount);
						
						//分公司分润
						BigDecimal branchBenefitAmount = displayBranchBenefit(amount, order, orderUser, benefitList, 3, now);
						totalBenefitAmount = totalBenefitAmount.add(branchBenefitAmount);
						
						//三级分销分润
						BigDecimal distriBenefitAmount = displayDistributionBenefit(amount, order, orderUser, benefitList, 3, now);
						totalBenefitAmount = totalBenefitAmount.add(distriBenefitAmount);
				}
			}else if(ConstantsUtils.ORDER_KIND_COMMON.equals(order.getOrderType())){//普通商品分润
				//普通商品分润设置
				TdBenefitSearchCriteria sc = new TdBenefitSearchCriteria();
				sc.setFlag(false);
				sc.setTypeId(8);
				List<TdBenefit> benefitList = tdBenefitService.findBySearchCriteria(sc);
				/*//零元商品分润设置
				sc.setFlag(false);
				sc.setTypeId(9);
				List<TdBenefit> zerobenefitList = tdBenefitService.findBySearchCriteria(sc);
				
				//获取零元三级分润金额
				BigDecimal zeroAmount = configUtil.getZeroProductBenefitAmount();*/
				
				List<TdOrderSku> orderSkuList = tdOrderSkuMapper.findByOrderId(order.getOrderId());
				if(null!=orderSkuList && orderSkuList.size()>0){
					//单类代理分润
					for(TdOrderSku ordersku : orderSkuList){
						if(!ordersku.getItemType().equals(Byte.valueOf("3"))){//普通商品分润
							if(BigDecimal.ZERO.compareTo(ordersku.getBenefitAmount())<0){//有利润空间才分润
								BigDecimal amount = ordersku.getBenefitAmount();
								if(BigDecimal.ZERO.compareTo(amount)<0){
									orderBenefitAmount = orderBenefitAmount.add(amount);
									//单类代理分润
									BigDecimal agentBenefitAmount = displayCommonProductAgentBenefit(amount, order, ordersku, orderUser, benefitList, 4, now);
									totalBenefitAmount = totalBenefitAmount.add(agentBenefitAmount);								
								}
							}
						}/*else{//零元商品分润，不再参与普通商品分润
							if(BigDecimal.ZERO.compareTo(ordersku.getBenefitAmount())<0){//有利润空间才分润
								BigDecimal amount = ordersku.getBenefitAmount();
								if(zeroAmount.compareTo(amount)<=0){
									//三级分销分润
									BigDecimal distBenefitAmount = displayDistributionBenefit(zeroAmount, order, orderUser, zerobenefitList, 4, now);
									totalBenefitAmount = totalBenefitAmount.add(distBenefitAmount);	
									amount = amount.subtract(zeroAmount);
									
									if(BigDecimal.ZERO.compareTo(amount)<0){
										//分公司分润
										BigDecimal branchBenefitAmount = displayBranchBenefit(amount, order, orderUser, zerobenefitList, 3, now);
										totalBenefitAmount = totalBenefitAmount.add(branchBenefitAmount);
									}
								}
							}							
						}*/
					}
					if(BigDecimal.ZERO.compareTo(orderBenefitAmount)<0){
						//分公司分润
						BigDecimal branchBenefitAmount = displayBranchBenefit(orderBenefitAmount, order, orderUser, benefitList, 3, now);
						totalBenefitAmount = totalBenefitAmount.add(branchBenefitAmount);
						
						//三级分销分润
						BigDecimal distBenefitAmount = displayDistributionBenefit(orderBenefitAmount, order, orderUser, benefitList, 4, now);
						totalBenefitAmount = totalBenefitAmount.add(distBenefitAmount);
					}
				}
			}else if(ConstantsUtils.ORDER_KIND_ZEROPRODUCT.equals(order.getOrderType())){//零元购商品分润
				//普通商品分润设置
				TdBenefitSearchCriteria sc = new TdBenefitSearchCriteria();
				/*sc.setFlag(false);
				sc.setTypeId(8);
				List<TdBenefit> benefitList = tdBenefitService.findBySearchCriteria(sc);*/
				//零元商品分润设置
				sc.setFlag(false);
				sc.setTypeId(9);
				List<TdBenefit> zerobenefitList = tdBenefitService.findBySearchCriteria(sc);
				
				//获取零元三级分润金额
				BigDecimal zeroAmount = configUtil.getZeroProductBenefitAmount();
				
				List<TdOrderSku> orderSkuList = tdOrderSkuMapper.findByOrderId(order.getOrderId());
				if(null!=orderSkuList && orderSkuList.size()>0){
					//单类代理分润
					for(TdOrderSku ordersku : orderSkuList){
						//零元商品分润，不再参与普通商品分润
						if(BigDecimal.ZERO.compareTo(ordersku.getBenefitAmount())<0){//有利润空间才分润
							BigDecimal amount = ordersku.getBenefitAmount();
							if(zeroAmount.compareTo(amount)<=0){
								//三级分销分润
								BigDecimal distBenefitAmount = displayDistributionBenefit(zeroAmount, order, orderUser, zerobenefitList, 4, now);
								totalBenefitAmount = totalBenefitAmount.add(distBenefitAmount);	
								amount = amount.subtract(zeroAmount);
								
								if(BigDecimal.ZERO.compareTo(amount)<0){
									//分公司分润
									BigDecimal branchBenefitAmount = displayBranchBenefit(amount, order, orderUser, zerobenefitList, 3, now);
									totalBenefitAmount = totalBenefitAmount.add(branchBenefitAmount);
								}
							}
						}							
					}
				}
			}
			//更新订单分润信息
			TdOrder uporder2 = new TdOrder();
			uporder2.setOrderId(order.getOrderId());
			uporder2.setBenefitAmount(totalBenefitAmount);
			uporder2.setUpdateBy(1);
			uporder2.setUpdateTime(now);
			uporder2.setBenefited(2);
			tdOrderMapper.updateByPrimaryKeySelective(uporder2);
		}
	}
	/**
	 *  商品分公司分润
	 * @param amount
	 * @param order
	 * @param benefitList
	 * @param size
	 * @param now
	 */
	private BigDecimal displayBranchBenefit(BigDecimal amount, TdOrder order, TdUser orderUser, List<TdBenefit> benefitList, int size, Date now){
		BigDecimal totalBenefitAmount = BigDecimal.ZERO;//已分润金额
		TdDistrict district = null;
		if(null!=orderUser&& null!=orderUser.getUregionId()){
			district = tdDistrictService.findOneFull(orderUser.getUregionId());//用户注册地址
		}
		logger.info("订单"+order.getOrderNo()+" 分公司分润:start");
		for(int i=0; i<size; i++){
			int level = i+1;
			logger.info("订单"+order.getOrderNo()+" 分公司分润:"+level+"级分公司分润");
			//分销设置
			TdBenefit  benefit = this.getBenefitConfig(benefitList, ConstantsUtils.AGENT_GROUPID_BRANCH, level);
			if(null==benefit){
				logger.info("订单"+order.getOrderNo()+" 分公司分润:"+level+"级分公司分润配置未找到");
				continue;
			}
			if(level==1){//分公司第一级为平台
				String note = "";
				TdUserAccount account = tdUserAccountService.findOne(1);
				BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
				totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
			}else if(level==2 && null!=district && null!=district.getRegionProvinceId()){//省直辖市市分公司
				TdBrancheCompanySearchCriteria bsc = new TdBrancheCompanySearchCriteria();
				bsc.setLevel(level);
				bsc.setRegionId(district.getRegionProvinceId());
				bsc.setFlag(false);
				List<TdBrancheCompany> branchList = tdBrancheCompanyService.findBySearchCriteria(bsc);
				if(null!= branchList && branchList.size()>0){
					String note = "";
					TdBrancheCompany branch = branchList.get(0);
					TdUserAccount account = tdUserAccountService.findOne(branch.getUid());
					BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}else{//未找到分公司，分润转给平台
					String note = "未找到"+district.getRegionProvinceName()+" 分公司,分润划归平台。";
					TdUserAccount account = tdUserAccountService.findOne(1);
					BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}
			}else if(level==3 && null!=district){//地区分公司
				TdBrancheCompanySearchCriteria bsc = new TdBrancheCompanySearchCriteria();
				bsc.setLevel(level);
				bsc.setRegionId(orderUser.getUregionId());
				bsc.setFlag(false);
				List<TdBrancheCompany> branchList = tdBrancheCompanyService.findBySearchCriteria(bsc);
				if(null!= branchList && branchList.size()>0){
					String note = "";
					TdBrancheCompany branch = branchList.get(0);
					TdUserAccount account = tdUserAccountService.findOne(branch.getUid());
					BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}else{//未找到分公司，分润转给平台
					String note = "未找到"+district.getName()+" 分公司,分润划归平台。";
					TdUserAccount account = tdUserAccountService.findOne(1);
					BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);	
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}					
			}else{
				String note = "未找到地区id"+orderUser.getUregionId()+" 的相关分公司,分润划归平台。";
				TdUserAccount account = tdUserAccountService.findOne(1);
				BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
				totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
			}
		}
		logger.info("订单"+order.getOrderNo()+" 分公司分润:end，分润金额:"+totalBenefitAmount);
		return totalBenefitAmount;
	}
	
	/**
	 *  普通商品单类代理分润
	 * @param amount
	 * @param order
	 * @param benefitList
	 * @param size
	 * @param now
	 */
	private BigDecimal displayCommonProductAgentBenefit(BigDecimal amount, TdOrder order, TdOrderSku ordersku, TdUser orderUser, List<TdBenefit> benefitList, int size, Date now){
		BigDecimal totalBenefitAmount = BigDecimal.ZERO;//已分润金额
		TdDistrict district = null;
		if(null!=orderUser&& null!=orderUser.getUregionId()){
			district = tdDistrictService.findOneFull(orderUser.getUregionId());//用户注册地址
		}
		//商品信息
		TdProduct product = tdProductService.findOne(ordersku.getProductId());
		logger.info("订单"+order.getOrderNo()+" 全国单代分润:start");
		for(int i=0; i<size; i++){
			int level = i+1;
			//分销设置
			TdBenefit  benefit = this.getBenefitConfig(benefitList, ConstantsUtils.AGENT_GROUPID_AGENT, level);
			if(null==benefit){
				logger.info("订单"+order.getOrderNo()+"  全国单代分润:"+level+"级代理分润配置未找到");
				continue;
			}else if(null==product||null==product.getTypeId()){
				String note = "全国单代分润：未找到商品信息,商品id="+ordersku.getProductId()+",分润划归平台。";
				TdUserAccount account = tdUserAccountService.findOne(1);
				BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
				totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
			}else if(null==district){
				String note = "全国单代分润：未找到会员注册地址,商品id="+ordersku.getProductId()+",分润划归平台。";
				TdUserAccount account = tdUserAccountService.findOne(1);
				BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
				totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
			}else{
				BigDecimal benefitAmount = saveCommonProductAgentBenefit(amount, order, orderUser, product, benefit, district, now);
				totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
			}
		}
		logger.info("订单"+order.getOrderNo()+" 全国单代分润:end 分润金额 "+totalBenefitAmount);
		return totalBenefitAmount;
	}

	/**
	 * 普通商品单类代理分润逻辑
	 * @param amount
	 * @param order
	 * @param orderUser
	 * @param product
	 * @param benefit
	 * @param district
	 * @param now
	 * @return
	 */
	private BigDecimal saveCommonProductAgentBenefit(BigDecimal amount, TdOrder order, TdUser orderUser, TdProduct product, TdBenefit benefit, TdDistrict district, Date now) {
		BigDecimal totalBenefitAmount = BigDecimal.ZERO;//已分润金额
		if(null==product.getTypeId()){//不存在代理分类，购买分公司时，代理归平台所有
			TdUserAccount account = tdUserAccountService.findOne(1);
			//分润
			String note = "商品没有分类，故没有"+benefit.getLevel()+"级分销代理 ,分润划归归平台";
			BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
			totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
		}else{
			if(benefit.getLevel()==1){//全国单代分润
				TdAgent agent = tdAgentService.findByTypeIdAndRegionId(product.getTypeId(), 0);
				if(agent !=null){//上级分销用户id 存在
					TdUser buser = tdUserService.findOneWithAccount(agent.getUid());
					if(null!=buser && null!=buser.getUserAccount()){
						TdUserAccount account = buser.getUserAccount();
						//分润
						String note = "";
						BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
						totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
					}else{//未找到代理，分润转给平台
						TdUserAccount account = tdUserAccountService.findOne(1);
						//分润
						String note = "未找到全国代理  ，当前分类id="+product.getTypeId()+" ,分润划归归平台";
						BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
						totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
					}
				}else{//全国单代分润不存在
					TdUserAccount account = tdUserAccountService.findOne(1);
					//分润
					String note = "未找到全国代理  ，当前分类id="+product.getTypeId()+" ,分润划归归平台";
					BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}
			}else if(benefit.getLevel()==2){//省市单代分润
				TdDistrict region = district.getRegionProvince();
				if(null!=region){
					TdAgent agent = tdAgentService.findByTypeIdAndRegionId(product.getTypeId(), region.getId());
					if(agent !=null){//省市代理存在
						TdUser buser = tdUserService.findOneWithAccount(agent.getUid());
						if(null!=buser && null!=buser.getUserAccount()){
							TdUserAccount account = buser.getUserAccount();
							//分润
							String note = "";
							BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
							totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
						}else{//未找到代理，分润转给平台
							TdUserAccount account = tdUserAccountService.findOne(1);
							//分润
							String note = "未找到省市代理  ，当前分类id="+product.getTypeId()+" ,分润划归归平台";
							BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
							totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
						}
					}else{//省市代理分润不存在
						TdUserAccount account = tdUserAccountService.findOne(1);
						//分润
						String note = "未找到省市代理  ，当前分类id="+product.getTypeId()+" ,分润划归归平台";
						BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
						totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
					}
				}else{//地区不存在
					TdUserAccount account = tdUserAccountService.findOne(1);
					//分润
					String note = "未找到省市代理   ，分润划归归平台";
					BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}
			}else if(benefit.getLevel()==3){//地区单代分润
				TdAgent agent = tdAgentService.findByTypeIdAndRegionId(product.getTypeId(), district.getId());
				if(agent !=null){//省市代理存在
					TdUser buser = tdUserService.findOneWithAccount(agent.getUid());
					if(null!=buser && null!=buser.getUserAccount()){
						TdUserAccount account = buser.getUserAccount();
						//分润
						String note = "";
						BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
						totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
					}else{//未找到代理，分润转给平台
						TdUserAccount account = tdUserAccountService.findOne(1);
						//分润
						String note = "未找到省市代理  ，当前分类id="+product.getTypeId()+" ,分润划归归平台";
						BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
						totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
					}
				}else{//省市代理分润不存在
					TdUserAccount account = tdUserAccountService.findOne(1);
					//分润
					String note = "未找到省市代理  ，当前分类id="+product.getTypeId()+" ,分润划归归平台";
					BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}
			}else if(benefit.getLevel()==4){//体验点分润：体验店发展的会员购买体验店拥有类目的商品是才参与分润
				if(orderUser.getUparentId()!=0){
					//查找体验店
					TdExperienceStore store = null;
					TdExperienceStoreSearchCriteria esc = new TdExperienceStoreSearchCriteria();
					esc.setUid(orderUser.getUparentId());
					esc.setStatus(Byte.valueOf("1"));
					List<TdExperienceStore> storeList = tdExperienceStoreService.findBySearchCriteria(esc);
					if(null!=storeList && storeList.size()>0){
						for(TdExperienceStore tstore : storeList){
							if(tstore.isProductTypeStore(product.getTypeId())){//体验店存在，且代理分类符合。状态正常的参与分润
								store = tstore;
								break;
							}
						}
					}
					if(null!=store && Byte.valueOf("1").equals(store.getStatus()) && store.isProductTypeStore(product.getTypeId())){//体验店存在，且代理分类符合。状态正常的参与分润
						TdUser buser = tdUserService.findOneWithAccount(orderUser.getUparentId());
						if(null!=buser && null!=buser.getUserAccount()){
							TdUserAccount account = buser.getUserAccount();
							//分润
							String note = "";
							BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
							totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
						}else{//未找到代理，分润转给平台
							TdUserAccount account = tdUserAccountService.findOne(1);
							//分润
							String note = "未找到体验店账号,分润划归归平台";
							BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
							totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
						}
					}else{
						TdUserAccount account = tdUserAccountService.findOne(1);
						//分润
						String note = "没有体验店,分润划归归平台";
						BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
						totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
					}
				}else{
					TdUserAccount account = tdUserAccountService.findOne(1);
					//分润
					String note = "没有体验店,分润划归归平台";
					BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}
			}
		}
		return totalBenefitAmount;
	}

	
	/**
	 * 代理产品代理分润
	 * @param amount
	 * @param order
	 * @param orderProduct
	 * @param benefitList
	 * @param size
	 * @param now
	 */
	private BigDecimal displayAgentBenefit(BigDecimal amount, TdOrder order, TdUser orderUser, TdOrderProduct orderProduct, List<TdBenefit> benefitList, int size, Date now) {
		BigDecimal totalBenefitAmount = BigDecimal.ZERO;//已分润金额
		TdDistrict district = null;
		if(null!=orderProduct.getRegionId() && orderProduct.getRegionId()>0){
			district = tdDistrictService.findOneFull(orderProduct.getRegionId());
		}
		logger.info("订单"+order.getOrderNo()+" 全国单代分润:start");
		for(int i=0; i<size;i++){
			int level = i+1;
			//分销设置
			TdBenefit  benefit = this.getBenefitConfig(benefitList, ConstantsUtils.AGENT_GROUPID_AGENT, level);
			if(null==benefit){
				logger.info("订单"+order.getOrderNo()+"  全国单代分润:"+level+"级代理分润配置未找到");
				continue;
			}
			if(null==orderProduct.getProductTypeId()){
				TdUserAccount account = tdUserAccountService.findOne(1);
				//分润
				String note = "代理产品没有分类，故没有"+level+"级分销 ,分润划归归平台";
				BigDecimal benefitAmount = saveBenefit(account, orderUser, amount, order, benefit, now, note);
				totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
			}else{
				//代理分润
				BigDecimal benefitAmount = saveAgentBenefit(amount, order, orderUser, orderProduct, benefit, district, now);
				totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
			}
		}
		logger.info("订单"+order.getOrderNo()+" 全国单代分润:end 分润金额 "+totalBenefitAmount);
		return totalBenefitAmount;		
	}
	/**
	 * 代理产品全国分润分润
	 * @param amount
	 * @param order
	 * @param orderProduct
	 * @param benefit
	 * @param district
	 * @param now
	 */
	private BigDecimal saveAgentBenefit(BigDecimal amount, TdOrder order, TdUser orderUser, TdOrderProduct orderProduct,TdBenefit benefit, TdDistrict district, Date now){
		BigDecimal totalBenefitAmount = BigDecimal.ZERO;//已分润金额
		if(orderProduct.getLevel()>=benefit.getLevel()){//购买产品级别比分润级别大和相等时，返回
			return totalBenefitAmount;
		}else{
			if(benefit.getLevel()==1){//全国单代分润
				TdAgent agent = tdAgentService.findByTypeIdAndRegionId(orderProduct.getProductTypeId(), 0);
				if(agent !=null){//上级分销用户id 存在
					TdUser buser = tdUserService.findOneWithAccount(agent.getUid());
					if(null!=buser && null!=buser.getUserAccount()){
						TdUserAccount account = buser.getUserAccount();
						//分润
						String note = "";
						saveBenefit(account, orderUser, amount, order, benefit, now, note);
					}else{//未找到代理，分润转给平台
						TdUserAccount account = tdUserAccountService.findOne(1);
						//分润
						String note = "未找到全国代理  ，当前分类id="+orderProduct.getProductTypeId()+" ,分润划归归平台";
						saveBenefit(account, orderUser, amount, order, benefit, now, note);
					}
				}else{//全国单代分润不存在
					TdUserAccount account = tdUserAccountService.findOne(1);
					//分润
					String note = "未找到全国代理  ，当前分类id="+orderProduct.getProductTypeId()+" ,分润划归归平台";
					saveBenefit(account, orderUser, amount, order, benefit, now, note);
				}
			}else if(benefit.getLevel()==2){//省市单代分润
				TdDistrict region = district.getRegionProvince();
				if(null!=region){
					TdAgent agent = tdAgentService.findByTypeIdAndRegionId(orderProduct.getProductTypeId(), 0);
					if(agent !=null){//省市代理存在
						TdUser buser = tdUserService.findOneWithAccount(agent.getUid());
						if(null!=buser && null!=buser.getUserAccount()){
							TdUserAccount account = buser.getUserAccount();
							//分润
							String note = "";
							saveBenefit(account, orderUser, amount, order, benefit, now, note);
						}else{//未找到代理，分润转给平台
							TdUserAccount account = tdUserAccountService.findOne(1);
							//分润
							String note = "未找到省市代理  ，当前分类id="+orderProduct.getProductTypeId()+" ,分润划归归平台";
							saveBenefit(account, orderUser, amount, order, benefit, now, note);
						}
					}else{//省市代理分润不存在
						TdUserAccount account = tdUserAccountService.findOne(1);
						//分润
						String note = "未找到省市代理  ，当前分类id="+orderProduct.getProductTypeId()+" ,分润划归归平台";
						saveBenefit(account, orderUser, amount, order, benefit, now, note);
					}
				}else{//地区不存在
					TdUserAccount account = tdUserAccountService.findOne(1);
					//分润
					String note = "未找到省市代理   ，分润划归归平台";
					saveBenefit(account, orderUser, amount, order, benefit, now, note);
				}
			}
		}
		return totalBenefitAmount;
	}

	/**
	 * 三级分销分润
	 * @param order
	 * @param user
	 * @param benefitList
	 * @param size 分几级
	 */
	private BigDecimal displayDistributionBenefit(BigDecimal amount, TdOrder order, TdUser user, List<TdBenefit> benefitList, int size, Date now){
		BigDecimal totalBenefitAmount = BigDecimal.ZERO;//已分润金额
		int userId = user.getUparentId();
		logger.info("订单"+order.getOrderNo()+" 三级分销分润:start");
		for(int i=0; i<size;i++){
			int level = i+1;
			//分销设置
			TdBenefit  benefit = this.getBenefitConfig(benefitList, ConstantsUtils.AGENT_GROUPID_DISTRIBUTION, level);
			if(null==benefit){
				logger.info("订单"+order.getOrderNo()+"  三级分销分润:"+level+"级代理分润配置未找到");
				continue;
			}
			if(i==3 && size==4){//自己购买分润
				String note = "";
				TdUserAccount account = user.getUserAccount();
				BigDecimal benefitAmount = saveBenefit(account, user, amount, order, benefit, now, note);
				totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
			}else{
				//三级分销用户
				if(userId>0){//上级分销用户id 存在
					TdUser buser = tdUserService.findOneWithAccount(userId);
					if(null!=buser && null!=buser.getUserAccount()){
						userId = buser.getUparentId();
						TdUserAccount account = buser.getUserAccount();
						//分润
						String note = "";
						BigDecimal benefitAmount = saveBenefit(account, user, amount, order, benefit, now, note);
						totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
					}else{//未找到分公司，分润转给平台
						userId = 0;
						TdUserAccount account = tdUserAccountService.findOne(1);
						//分润
						String note = "未找到"+level+"级分销  ，当前用户userid="+user.getUid()+" ,分润划归归平台";
						BigDecimal benefitAmount = saveBenefit(account, user, amount, order, benefit, now, note);
						totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
					}
				}else{//上级分销用户id 不存在
					TdUserAccount account = tdUserAccountService.findOne(1);
					//分润
					String note = "未找到"+level+"级分销  ,当前用户userid="+user.getUid()+" ,分润划归归平台";
					BigDecimal benefitAmount = saveBenefit(account, user, amount, order, benefit, now, note);
					totalBenefitAmount = totalBenefitAmount.add(benefitAmount);
				}
			}
		}
		logger.info("订单"+order.getOrderNo()+" 三级分销分润:end 分润金额 "+totalBenefitAmount);
		return totalBenefitAmount;
	}
	
	/**
	 * 匹配分润设置
	 * @param benefitList
	 * @param groupId
	 * @param level
	 * @return
	 */
	private TdBenefit getBenefitConfig(List<TdBenefit> benefitList, Integer groupId, Integer level){
		if(null!=benefitList&&benefitList.size()>0){
			for(TdBenefit benefit : benefitList){
				if(groupId.equals(benefit.getGroupId()) && level.equals(benefit.getLevel())){
					return benefit;
				}
			}
		}
		return null;
	}
	/**
	 * 分润操作
	 * @param account
	 * @param amount
	 * @param order
	 * @param benefit
	 * @param now
	 */
	private BigDecimal saveBenefit(TdUserAccount account, TdUser orderUser, BigDecimal amount, TdOrder order, TdBenefit benefit, Date now, String note){
		BigDecimal benefitAmount = amount.multiply(new BigDecimal(benefit.getPercent())).divide(ConstantsUtils.SYSTEM_BENEFIT_PERCENT_NUM).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(BigDecimal.ZERO.compareTo(benefitAmount.abs())==0){
			return benefitAmount;
		}
		account.setUpdateBy(1);
		account.setUpdateTime(now);
		TdUserAccountLog alog = new TdUserAccountLog();
		alog.setPreamount(account.getAmount());
		alog.setUid(account.getUid());
		alog.setUpamount(benefitAmount);
		if(BigDecimal.ZERO.compareTo(benefitAmount)>0){
			alog.setType(TdUserAccountLog.USERACCOUNTLOG_TYPE_PROFIT_BACK);
			alog.setNote("订单退款分润收入扣回，订单编号："+order.getOrderNo()+" "+note);
		}else{
			alog.setType(TdUserAccountLog.USERACCOUNTLOG_TYPE_PROFIT_INCOME);
			alog.setNote("订单分润收入，订单编号："+order.getOrderNo()+" "+note);
		}
    	alog.setCreateTime(now);
    	//关联对象
    	ProfitInfo profit = new ProfitInfo();
    	profit.setBuyUserName(orderUser.getUnick());
    	profit.setOrderNo(order.getOrderNo());
    	profit.setProductName("");
    	profit.setProfit(benefitAmount.toString());
    	profit.setTotalAmount(order.getTotalAmount().toString());
    	Gson json = new Gson();
    	String relation = json.toJson(profit);
    	alog.setRelation(relation);
		tdUserAccountService.addAmount(account, alog);	
		return benefitAmount;
	}

	@Override
	public TdOrder findByOrderNo(String orderNo) {
		return tdOrderMapper.findByOrderNo(orderNo);
	}

	@Override
	public void AfterPaySuccess(TdOrder order,String response) {
		if(null == order){
			return;
		}
		
		OrderPay orderPay = new OrderPay();
		orderPay.setPayAmount(order.getPayAmount());
		orderPay.setPaymentId(order.getPaymentId());
		orderPay.setCreateBy(order.getUserId());
		orderPay.setCreateTime(new Date());
		orderPay.setOrderId(order.getOrderId());
		
		TdOrderPay pay = new TdOrderPay();
		pay.setCreateTime(new Date());
		pay.setOrderNo(order.getOrderNo());
		pay.setPayamount(order.getPayAmount());
		pay.setPaymentType(order.getPaymentId());
		pay.setResponse(response);
		pay.setStatus((byte)1);
		tdOrderPayService.save(pay);
		
		this.payOrder(order, orderPay);
		
	}
	
	@Override
	public void AfterJointPaySuccess(TdJointOrder tdOrder, String response) {
		TdOrderSearchCriteria sc = new TdOrderSearchCriteria();
    	sc.setFlag(false);
    	sc.setJointId(tdOrder.getId());
    	List<TdOrder> orderList = this.findBySearchCriteria(sc);
    	
		if(null != orderList && orderList.size() > 0){
    		for (TdOrder order : orderList) {
				this.AfterPaySuccess(order, response);
			}
    	}
	}

	@Override
	public void receiptOrderBySystemJob() {
		Date now = new Date();
		Integer orderreceiptperiod = configUtil.getOrderReceiptPeriod();
		Date receiptTime = DateUtil.getNewDate(now, -orderreceiptperiod);//获取n天前的时间
		
		TdOrderSearchCriteria sc = new TdOrderSearchCriteria();
		sc.setFlag(false);
//		sc.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_SHIPPED);
		sc.setOrderStatus(ConstantsUtils.ORDER_STATUS_SHIPPMENTED);
		sc.setShippmentTime(receiptTime);
		List<TdOrder> orderList = tdOrderMapper.findBySearchCriteria(sc);
		if(null!=orderList){
			TdUser user = tdUserService.findOne(1);//系统账号
			for(TdOrder order : orderList){
				this.receiptOrder(order, user);
			}
		}
		
	}

	@Override
	public void completeOrderBySystemJob() {
		logger.error("completeOrderBySystemJob  start:");
		Date now = new Date();
		Integer ordercompleteperiod = configUtil.getOrderCompletePeriod();
		Date receiptTime = DateUtil.getNewDate(now, -ordercompleteperiod);//获取n天前的时间
		
		TdOrderSearchCriteria sc = new TdOrderSearchCriteria();
		sc.setFlag(false);
//		sc.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_RECEIPT);
		sc.setOrderStatus(ConstantsUtils.ORDER_STATUS_RECEIPTED);
		sc.setReceiptTime(receiptTime);
		sc.setGetProductSku(true);
		List<TdOrder> orderList = tdOrderMapper.findBySearchCriteria(sc);
		if(null!=orderList){
			TdUser user = tdUserService.findOne(1);//系统账号
			for(TdOrder order : orderList){
				logger.error("completeOrderBySystemJob  start orderno:"+order.getOrderNo());
				this.completeOrder(order, user);
				logger.error("completeOrderBySystemJob  end orderno:"+order.getOrderNo());
			}
		}
		logger.error("completeOrderBySystemJob  end");
	}
	
	

	@Override
	public OperResult cancelOrder(TdOrder order, TdUser user) {
		OperResult result = new OperResult();
		//未支付订单才能取消
		if(!ConstantsUtils.ORDER_STATUS_NEW.equals(order.getOrderStatus())){
			result.setFailMsg("订单的不能进行取消操作！");
			return result;
		}
		//代理商、供应商、分公司的订单取消资格
		if(ConstantsUtils.ORDER_KIND_AGENTPRODUCT.equals(order.getOrderType())){
			TdOrderProduct orderProduct = tdOrderProductMapper.findByOrderIdAndTypeId(order.getOrderId(), Byte.valueOf("1"));
			if(null!=orderProduct){
				TdAgentProduct agentProduct = tdAgentProductService.findOne(orderProduct.getItemId());
				if(null!=agentProduct){
					if(ConstantsUtils.AGENT_GROUPID_AGENT.equals(agentProduct.getGroupId())){//单代代理
						TdAgent agent = tdAgentService.findByUid(order.getUserId());
						if(null!=agent){//用户已经购买代理
							tdAgentService.delete(agent.getId());
						}	
						
					}else if(ConstantsUtils.AGENT_GROUPID_BRANCH.equals(agentProduct.getGroupId())){//分公司
						TdBrancheCompany branch = tdBrancheCompanyService.findByUid(order.getUserId());
						if(null!=branch){//用户已经购买代理
							tdBrancheCompanyService.delete(branch.getId());
						}				
					}else if(ConstantsUtils.AGENT_GROUPID_SUPPLIER.equals(agentProduct.getGroupId())){//供应商
						//检查用户是否已经是供应商资格
						TdUser orderuser = tdUserService.findOne(order.getUserId());
						if(null!=orderuser && ConstantsUtils.CUSTOMER_SUPPLIER_BUYED.equals(orderuser.getTempsupplier())){
							TdUser upuser = new TdUser();
							upuser.setTempsupplier(ConstantsUtils.CUSTOMER_SUPPLIER_NONE);
							upuser.setUid(orderuser.getUid());
							tdUserService.updateByPrimaryKeySelective(upuser);
						}
					}
				}
			}
		}
		//开始操作
		Date now  = new Date();
		TdOrder uporder = new TdOrder();
		uporder.setOrderId(order.getOrderId());
		uporder.setUpdateBy(user.getUid());
		uporder.setUpdateTime(now);
		uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_CANCEL);
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(user.getUid());
		log.setCreateTime(now);
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_CANCEL);
		log.setNote("订单进行取消操作");
		tdOrderLogMapper.insert(log);
		
		//返回使用积分
		if(order.getUsedPoint().compareTo(0)>0){
			TdUserIntegral userIntegral = tdUserIntegralService.findOne(order.getUserId());
			userIntegral.setUpdateBy(0);
			userIntegral.setUpdateTime(now);
			TdUserIntegralLog integralLog = new TdUserIntegralLog();
			integralLog.setType(TdUserIntegralLog.USERINTEGRALLOG_TYPE_REFUND);
			integralLog.setCreateTime(now);
			integralLog.setPreintegral(userIntegral.getIntegral());
			integralLog.setIntegral(order.getUsedPoint());
			integralLog.setUid(order.getUserId());
			integralLog.setNote("订单取消返回已使用积分,订单编号："+order.getOrderNo());
			integralLog.setRelation("");
			tdUserIntegralService.addIntegral(userIntegral, integralLog);
		}
		
		//返回货品库存
		if(ConstantsUtils.ORDER_KIND_COMMON.equals(order.getOrderType())){
			List<TdOrderSku> orderSkuList = tdOrderSkuMapper.findByOrderId(order.getOrderId());
			if(null!=orderSkuList && orderSkuList.size()>0){
				for(TdOrderSku ordersku : orderSkuList){
					tdProductSkuService.updateStock(ordersku.getProductSkuId(), ordersku.getQuantity());//更新库存
				}
			}
		}
		
		
		result.setFlag(true);
		return result;
	}

	
	@Override
	public OperResult cancelOrderByUser(TdOrder order, OrderCancel ordercancel) {
		OperResult result = new OperResult();
		//发货后的订单不能取消
		if(ConstantsUtils.ORDER_STATUS_SHIPPMENTED.compareTo(order.getOrderStatus())<=0){
			result.setFailMsg("订单的不能进行取消操作！");
			return result;
		}
		//代理商、供应商、分公司的订单取消资格
		if(ConstantsUtils.ORDER_KIND_AGENTPRODUCT.equals(order.getOrderType())){
			TdOrderProduct orderProduct = tdOrderProductMapper.findByOrderIdAndTypeId(order.getOrderId(), Byte.valueOf("1"));
			if(null!=orderProduct){
				TdAgentProduct agentProduct = tdAgentProductService.findOne(orderProduct.getItemId());
				if(null!=agentProduct){
					if(ConstantsUtils.AGENT_GROUPID_AGENT.equals(agentProduct.getGroupId())){//单代代理
						TdAgent agent = tdAgentService.findByUid(order.getUserId());
						if(null!=agent){//用户已经购买代理
							tdAgentService.delete(agent.getId());
						}	
						
					}else if(ConstantsUtils.AGENT_GROUPID_BRANCH.equals(agentProduct.getGroupId())){//分公司
						TdBrancheCompany branch = tdBrancheCompanyService.findByUid(order.getUserId());
						if(null!=branch){//用户已经购买代理
							tdBrancheCompanyService.delete(branch.getId());
						}				
					}else if(ConstantsUtils.AGENT_GROUPID_SUPPLIER.equals(agentProduct.getGroupId())){//供应商
						//检查用户是否已经是供应商资格
						TdUser orderuser = tdUserService.findOne(order.getUserId());
						if(null!=orderuser && ConstantsUtils.CUSTOMER_SUPPLIER_BUYED.equals(orderuser.getTempsupplier())){
							TdUser upuser = new TdUser();
							upuser.setTempsupplier(ConstantsUtils.CUSTOMER_SUPPLIER_NONE);
							upuser.setUid(orderuser.getUid());
							tdUserService.updateByPrimaryKeySelective(upuser);
						}
					}
				}
			}
		}
		if(ConstantsUtils.ORDER_PAY_STATUS_PAYED.equals(order.getPayStatus())){//已支付的订单进行退款操作
			OrderRefund refund = new OrderRefund();
			refund.setOrderId(order.getOrderId());
			refund.setCreateBy(ordercancel.getOperBy());
			refund.setCreateTime(ordercancel.getCreateTime());
			refund.setRefundAmount(order.getPayAmount());
			this.refundorder(refund);
		}
		//开始操作
		TdOrder uporder = new TdOrder();
		uporder.setOrderId(order.getOrderId());
		uporder.setUpdateBy(ordercancel.getOperBy());
		uporder.setUpdateTime(ordercancel.getCreateTime());
		uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_CANCEL);
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(ordercancel.getOperBy());
		log.setCreateTime(ordercancel.getCreateTime());
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_CANCEL);
		log.setNote("客户取消订单操作，取消原因："+ordercancel.getCancelReason());
		tdOrderLogMapper.insert(log);
		//返回使用积分
		if(order.getUsedPoint().compareTo(0)>0){
			TdUserIntegral userIntegral = tdUserIntegralService.findOne(order.getUserId());
			userIntegral.setUpdateBy(0);
			userIntegral.setUpdateTime(ordercancel.getCreateTime());
			TdUserIntegralLog integralLog = new TdUserIntegralLog();
			integralLog.setType(TdUserIntegralLog.USERINTEGRALLOG_TYPE_REFUND);
			integralLog.setCreateTime(ordercancel.getCreateTime());
			integralLog.setPreintegral(userIntegral.getIntegral());
			integralLog.setIntegral(order.getUsedPoint());
			integralLog.setUid(order.getUserId());
			integralLog.setNote("订单取消返回已使用积分,订单编号："+order.getOrderNo());
			integralLog.setRelation("");
			tdUserIntegralService.addIntegral(userIntegral, integralLog);
		}
		//返回货品库存
		if(ConstantsUtils.ORDER_KIND_COMMON.equals(order.getOrderType())){
			List<TdOrderSku> orderSkuList = tdOrderSkuMapper.findByOrderId(order.getOrderId());
			if(null!=orderSkuList && orderSkuList.size()>0){
				for(TdOrderSku ordersku : orderSkuList){
					tdProductSkuService.updateStock(ordersku.getProductSkuId(), ordersku.getQuantity());//更新库存
				}
			}
		}
		
		
		result.setFlag(true);
		return result;
	}

	@Override
	public void cancelOrderBySystemJob() {
		Date now = new Date();
		Integer ordercancelperiod = configUtil.getOrderCancelPeriod()*60;
		Date receiptTime = DateUtil.getNewTime(now, -ordercancelperiod);//获取n天前的时间
		
		TdOrderSearchCriteria sc = new TdOrderSearchCriteria();
		sc.setFlag(false);
//		sc.setShipmentStatus(ConstantsUtils.ORDER_SHIPMENT_STATUS_RECEIPT);
		sc.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		sc.setReceiptTime(receiptTime);
		sc.setGetProductSku(true);
		List<TdOrder> orderList = tdOrderMapper.findBySearchCriteria(sc);
		if(null!=orderList){
			TdUser user = tdUserService.findOne(1);//系统账号
			for(TdOrder order : orderList){
				this.cancelOrder(order, user);
			}
		}
		
	}

	@Override
	public OperResult refundorderBySupply(TdOrderShipment ship) {
		OperResult result = new OperResult();
		OrderRefund refund = new OrderRefund();
		refund.setOrderId(ship.getOrderId());
		refund.setCreateTime(ship.getUpdateTime());
		refund.setCreateBy(ship.getSupplyId());
		refund.setRefundAmount(ship.getReturnAmount());
		OperResult ret = this.refundorder(refund);
		if(ret.isFlag()){
			//更改退货单状态为已完成
			ship.setStatus(Byte.valueOf("5"));
			tdOrderShipmentMapper.updateByPrimaryKeySelective(ship);
			//更改订单状态为已发货状态继续订单流程
			TdOrder uporder = new TdOrder();
			uporder.setOrderId(ship.getOrderId());
			uporder.setUpdateBy(ship.getSupplyId());
			uporder.setUpdateTime(ship.getUpdateTime());
			uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_RECEIPTED);
			tdOrderMapper.updateByPrimaryKeySelective(uporder);
			result.setFlag(true);
		}else{
			result.setFailMsg(ret.getFailMsg());
		}
		return result;
	}

	
	
}
