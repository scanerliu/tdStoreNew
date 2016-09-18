package com.tiandu.order.vo;

/**
 * 物流查询响应实体类
 *
 */
public class PostageResponseDataVO {

	/**
	 * 内容
	 */
	private String context;
	/**
	 * 时间，原始格式
	 */
	private String time;
	/**
	 * 格式化后时间
	 */
	private String ftime;
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	
}
