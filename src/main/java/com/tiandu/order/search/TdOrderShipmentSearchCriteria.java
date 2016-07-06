package com.tiandu.order.search;

import com.tiandu.common.search.SearchCriteria;

public class TdOrderShipmentSearchCriteria extends SearchCriteria {

	private Integer orderId; //订单id
    private Byte type; //类型，1-发货单，2-退货单
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}

  
}
