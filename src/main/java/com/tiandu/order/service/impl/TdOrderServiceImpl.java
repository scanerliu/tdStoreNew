package com.tiandu.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.entity.TdUserIntegralLog;
import com.tiandu.custom.service.TdUserAddressService;
import com.tiandu.custom.service.TdUserIntegralService;
import com.tiandu.order.entity.TdJointOrder;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderAddress;
import com.tiandu.order.entity.TdOrderLog;
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
import com.tiandu.order.entity.mapper.TdShoppingcartItemMapper;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.OrderForm;
import com.tiandu.order.vo.OrderRefund;
import com.tiandu.order.vo.ShoppingcartVO;
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
	private ConfigUtil configUtil;

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
		}else if(refundAmount.compareTo(order.getPayAmount())==0){
			uporder.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_ALL_REFUND);
		}else{
			uporder.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_PART_REFUND);
		}
		uporder.setRefundAmount(refundAmount);
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(refund.getCreateBy());
		log.setCreateTime(refund.getCreateTime());
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_REFUND);
		log.setNote("订单进行退款操作：退款金额 "+refund.getRefundAmount());
		tdOrderLogMapper.insert(log);
		result.setFlag(true);
		return result;
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
		uporder.setUpdateBy(0);
		uporder.setUpdateTime(now);
		uporder.setOrderStatus(ConstantsUtils.ORDER_STATUS_COMPLETE);
		tdOrderMapper.updateByPrimaryKeySelective(uporder);
		
		//订单操作日志
		TdOrderLog log = new TdOrderLog();
		log.setOrderId(order.getOrderId());
		log.setCreateBy(0);
		log.setCreateTime(now);
		log.setOperType(ConstantsUtils.ORDER_LOG_TYPE_COMPLETE);
		log.setNote("订单进行完成操作");
		tdOrderLogMapper.insert(log);
		
		//分润开始
		//
		
		result.setFlag(true);
		return result;
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
		if(shoppingcart.getCombiningOrder()){//拆单
			torder.setJno(WebUtils.generateJointOrderNo());
			torder.setAmount(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount()));
			torder.setPaymentId(orderForm.getPaymentId());
			torder.setStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
			torder.setCreateTime(now);
			torder.setUpdateTime(now);
			tdJointOrderMapper.insert(torder);
			//拆分购物车
			List<ShoppingcartVO> cartList = this.splitShoppingcart(shoppingcart);
			//分别生成订单
			for(ShoppingcartVO cart : cartList){
				this.insertOrder(currUser, orderForm, cart, torder, now);
			}
		}else{
			torder.setId(0);
			TdOrder order = this.insertOrder(currUser, orderForm, shoppingcart, torder, now);
			torder.setJno(order.getOrderNo());
			
			torder.setPaymentId(orderForm.getPaymentId());
		}		
		return torder;
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
		order.setItemNum(shoppingcart.getTotalcount());
		order.setJointId(torder.getId());//联合订单
		order.setOrderNo(WebUtils.generateOrderNo());
		order.setOrderStatus(ConstantsUtils.ORDER_STATUS_NEW);
		order.setOrderType(ConstantsUtils.ORDER_KIND_COMMON);
		order.setPaymentId(orderForm.getPaymentId());
		if(orderForm.getUsePoints()){
			order.setUsedPoint(shoppingcart.getTotalPointsUsed());
			order.setPointAmount(shoppingcart.getTotalPointAmount());
		}else{
			order.setUsedPoint(0);
			order.setPointAmount(BigDecimal.ZERO);
		}
		order.setPostage(shoppingcart.getTotalPostage());
		order.setPayStatus(ConstantsUtils.ORDER_PAY_STATUS_UNPAY);
		order.setPaymentId(orderForm.getPaymentId());
		order.setProductAmount(shoppingcart.getTotalProductAmount());
		order.setRefundAmount(BigDecimal.ZERO);
		order.setPayAmount(BigDecimal.ZERO);
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
			sku.setPrice(item.getProductSku().getSalesPrice());
			sku.setProductName(item.getProduct().getName());
			sku.setProductSkuCode(item.getProductSku().getSkuCode());
			sku.setQuantity(item.getQuantity());
			tdOrderSkuMapper.insert(sku);
			//更新货品库存
			//TODO:
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
			//重新计算新购物车
			computershoppingcart(scart, integralexchangerate, commonproductpointpercent, partproductpointpercent);
			//计算获得积分
			if(shoppingcart.getGainPoints()>0){
				Integer point = scart.getTotalAmount().subtract(scart.getTotalPointAmount()).divide(shoppingcart.getTotalAmount().subtract(shoppingcart.getTotalPointAmount())).multiply(new BigDecimal(shoppingcart.getGainPoints())).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
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
			}
			//计算总积分抵扣金额和
			cart.setTotalPointAmount(cart.getTotalCommonPointAmount().add(cart.getTotalPartPointAmount()));
			Integer commonproductpoint = cart.getTotalCommonPointAmount().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
			Integer partproductpoint = cart.getTotalPartPointAmount().multiply(new BigDecimal(integralexchangerate)).setScale(0, BigDecimal.ROUND_FLOOR).intValue();
			cart.setTotalPointsUsed(commonproductpoint+partproductpoint);
		}
		cart.setTotalcount(itemList.size());
	}
	
}
