package com.tiandu.custom.vo;

import java.math.BigDecimal;

/**
 * 提现信息
 * @author liuxinbing
 *
 */
public class WithDrawVO {
	/**
	 * 提现方式 1-微信红包，2-银联转账
	 */
	private Integer type;
	/**
	 * 提现金额
	 */
	private BigDecimal amount;
	/**
	 * 微信openId
	 */
	private String openId;
	/**
	 * 调用ip
	 */
	private String clientIp;
	
	/**
	 * 微信ca证书文件地址
	 */
	private String capath;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getCapath() {
		return capath;
	}
	public void setCapath(String capath) {
		this.capath = capath;
	}
	
}
