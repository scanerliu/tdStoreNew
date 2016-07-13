package com.tiandu.order.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退款操作实体类
 * @author liuxinbing
 *
 */
public class OrderRefund {

	private Integer orderId;	//订单id
	
	private BigDecimal refundAmount;  //退款金额
	
	private Date createTime;	//操作时间
	
	private Integer createBy;	//操作人
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	
	
}
