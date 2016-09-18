package com.tiandu.order.vo;

/**
 * 物流查询操作实体类
 *
 */
public class PostageVO {

	private String trackingNo;
	private String trackingCom;
	
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getTrackingCom() {
		return trackingCom;
	}
	public void setTrackingCom(String trackingCom) {
		this.trackingCom = trackingCom;
	}
}
