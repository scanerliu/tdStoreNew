package com.tiandu.order.service;

import java.util.List;

import com.tiandu.complaint.entity.TdComplaint;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdJointOrder;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.OrderCancel;
import com.tiandu.order.vo.OrderForm;
import com.tiandu.order.vo.OrderPay;
import com.tiandu.order.vo.OrderRefund;
import com.tiandu.order.vo.ShoppingcartVO;

public interface TdOrderService {

	public Integer deleteByPrimaryKey(Integer orderId);

	public Integer save(TdOrder e);
	public TdOrder findOne(Integer id);
	public TdOrder findDetail(Integer id);
	public TdOrder findOrderWithOrderSku(Integer id);
	
	public List<TdOrder> findBySearchCriteria(TdOrderSearchCriteria sc);
	public Integer countByCriteria(TdOrderSearchCriteria sc);
	
	TdOrder findByOrderNo(String orderNo);
	
	/**
	 * 支付成功后操作
	 * @author Max
	 *
	 * @param tdOrder
	 */
	void AfterPaySuccess(TdOrder tdOrder,String response);
	
	/**
	 * 
	 * @author Max
	 *
	 * 联合订单支付后续
	 * 
	 */
	void AfterJointPaySuccess(TdJointOrder tdOrder,String response);
	
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
	 * @param Integer id
	 * @return
	 */
	public OperResult completeorder(Integer id);
	/**
	 * 订单手动完成操作
	 * @param order
	 * @param user
	 * @return
	 */
	public OperResult completeOrder(TdOrder order, TdUser user);
	
	/**
	 * 系统自动完成订单任务
	 * @return
	 */
	public void completeOrderBySystemJob();

	/**
	 * 生成订单
	 * @param currUser
	 * @param orderForm
	 * @param shoppingcart
	 * @return
	 * @throws RuntimeException 
	 */
	public TdJointOrder saveOrderFull(TdUser currUser, OrderForm orderForm, ShoppingcartVO shoppingcart) throws RuntimeException;

	/**
	 * 申请退款
	 * @param order
	 * @param shipment
	 * @return
	 */
	public OperResult applyRefundOrder(TdOrder order, TdOrderShipment shipment,Integer skuId);

	/**
	 * 投诉
	 * @param order
	 * @param complaint
	 * @return
	 */
	public OperResult complaintOrder(TdOrder order, TdComplaint complaint);
	/**
	 * 收货
	 * @param order
	 * @param user
	 * @return
	 */
	public OperResult receiptOrder(TdOrder order, TdUser user);
	/**
	 * 系统自动收货任务
	 * @return
	 */
	public void receiptOrderBySystemJob();
	/**
	 * 订单支付
	 * @param order
	 * @param orderPay
	 * @return
	 */
	public OperResult payOrder(TdOrder order, OrderPay orderPay);
	
	/**
	 * 订单分润
	 * @param order
	 * @return
	 */
	public void benefitOrder(TdOrder order) throws RuntimeException;

	/**
	 * 系统自动取消未支付订单任务
	 */
	public void cancelOrderBySystemJob();
	
	/**
	 * 订单手动取消操作
	 * @param order
	 * @param user
	 * @return
	 */
	public OperResult cancelOrder(TdOrder order, TdUser user);

	/**
	 * 客户取消订单
	 * @param order
	 * @param ordercancel
	 * @return
	 */
	public OperResult cancelOrderByUser(TdOrder order, OrderCancel ordercancel);

	/**
	 * 供应商完成退货操作
	 * @param ship
	 * @return
	 */
	public OperResult refundorderBySupply(TdOrderShipment ship);
}
