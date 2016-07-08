package com.tiandu.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderAddress;
import com.tiandu.order.entity.TdOrderLog;
import com.tiandu.order.entity.TdOrderProduct;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.entity.TdOrderShipmentItem;
import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.entity.mapper.TdOrderAddressMapper;
import com.tiandu.order.entity.mapper.TdOrderLogMapper;
import com.tiandu.order.entity.mapper.TdOrderMapper;
import com.tiandu.order.entity.mapper.TdOrderProductMapper;
import com.tiandu.order.entity.mapper.TdOrderShipmentItemMapper;
import com.tiandu.order.entity.mapper.TdOrderShipmentMapper;
import com.tiandu.order.entity.mapper.TdOrderSkuMapper;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.OrderRefund;

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
			Integer count = tdOrderMapper.countBySearchCriteria(sc);
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
	
	

	
}
