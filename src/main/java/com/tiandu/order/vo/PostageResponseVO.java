package com.tiandu.order.vo;

import java.util.List;

/**
 * 物流查询响应实体类
 *
 */
public class PostageResponseVO {

	private String message;
	private String state;
	private String status;
	private String com;
	private String nu;
	private List<PostageResponseDataVO> data;
	private Boolean result;
	private String returnCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public String getNu() {
		return nu;
	}
	public void setNu(String nu) {
		this.nu = nu;
	}
	
	public List<PostageResponseDataVO> getData() {
		return data;
	}
	public void setData(List<PostageResponseDataVO> data) {
		this.data = data;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
}
