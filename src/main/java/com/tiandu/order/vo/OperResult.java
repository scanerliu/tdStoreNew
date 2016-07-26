package com.tiandu.order.vo;

/**
 * 操作结果实体类型
 * @author liuxinbing
 *
 */
public class OperResult {

	/**
	 * 操作结果
	 */
	private boolean flag = false;
	/**
	 * 操作失败信息
	 */
	private String failMsg;
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getFailMsg() {
		return failMsg;
	}
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}
	
	
}
