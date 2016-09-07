package com.tiandu.order.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单取消操作实体类
 * @author liuxinbing
 *
 */
public class OrderCancel {

	private Integer orderId;	//订单id
	
	private String cancelReason;  //退款原因
	
	private Date createTime;	//操作时间
	
	private Integer operBy;	//操作人
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getOperBy() {
		return operBy;
	}
	public void setOperBy(Integer operBy) {
		this.operBy = operBy;
	}
	
}
