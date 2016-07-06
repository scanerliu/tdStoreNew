package com.tiandu.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderAddress;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.entity.TdOrderShipmentItem;
import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.entity.mapper.TdOrderAddressMapper;
import com.tiandu.order.entity.mapper.TdOrderMapper;
import com.tiandu.order.entity.mapper.TdOrderShipmentItemMapper;
import com.tiandu.order.entity.mapper.TdOrderShipmentMapper;
import com.tiandu.order.entity.mapper.TdOrderSkuMapper;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.product.entity.TdAgentProduct;
import com.tiandu.product.entity.mapper.TdAgentProductMapper;
import com.tiandu.product.search.TdAgentProductSearchCriteria;

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
	public boolean shiporder(TdOrderShipment shipment) {
		TdOrder order = this.findOrderWithOrderSku(shipment.getOrderId());
		//已发货的不能再次发货
		if(null==order||null==order.getOrderId() || ConstantsUtils.ORDER_SHIPMENT_STATUS_SHIPPED.equals(order.getShipmentStatus())){
			return false;
		}
		List<TdOrderSku> skuList = order.getSkuList();
		if(null==skuList || skuList.size()<1){
			return false;
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
		return true;
	}

	
}
