package com.tiandu.order.service;

import java.util.List;

import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.OrderRefund;

public interface TdOrderService {

	public Integer deleteByPrimaryKey(Integer orderId);

	public Integer save(TdOrder e);
	public TdOrder findOne(Integer id);
	public TdOrder findDetail(Integer id);
	public TdOrder findOrderWithOrderSku(Integer id);
	
	public List<TdOrder> findBySearchCriteria(TdOrderSearchCriteria sc);
	/**
	 * 订单发货操作
	 * @param order
	 * @param shipment
	 * @return
	 */
	public OperResult shiporder(TdOrderShipment shipment);
	/**
	 * 订单退款操作
	 * @param order
	 * @param refund
	 * @return
	 */
	public OperResult refundorder(OrderRefund refund);
	
	/**
	 * 订单完成操作,根据订单类型进行分润处理
	 * @param order
	 * @param refund
	 * @return
	 */
	public OperResult completeorder(Integer id);
}
