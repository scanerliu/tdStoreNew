package com.tiandu.custom.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信发红包请求封装类
 * @author liuxinbing
 *
 */
@XStreamAlias("xml")
public class WeChatRedPackRequest {

	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 商户订单号
	 */
	private String mch_billno;
	/**
	 * 商户号
	 */
	private String mch_id;
	/**
	 * 公众账号appid
	 */
	private String wxappid;
	/**
	 * 商户名称
	 */
	private String send_name;
	/**
	 * 用户openid
	 */
	private String re_openid;
	/**
	 * 付款金额，单位分
	 */
	private Integer total_amount;
	/**
	 * 红包发放总人数
	 */
	private Integer total_num;
	/**
	 * 红包祝福语
	 */
	private String wishing;
	/**
	 * Ip地址
	 */
	private String client_ip;
	/**
	 * 活动名称
	 */
	private String act_name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 场景id
	 */
	private String scene_id;
	/**
	 * 随机字符串
	 */
	private String nonce_str;
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getMch_billno() {
		return mch_billno;
	}
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	public Integer getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}
	public Integer getTotal_num() {
		return total_num;
	}
	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getScene_id() {
		return scene_id;
	}
	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getWxappid() {
		return wxappid;
	}
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	@Override
	public String toString() {
		return "WeChatRedPackRequest [sign=" + sign + ", mch_billno=" + mch_billno + ", mch_id=" + mch_id + ", wxappid="
				+ wxappid + ", send_name=" + send_name + ", re_openid=" + re_openid + ", total_amount=" + total_amount
				+ ", total_num=" + total_num + ", wishing=" + wishing + ", client_ip=" + client_ip + ", act_name="
				+ act_name + ", remark=" + remark + ", scene_id=" + scene_id + ", nonce_str=" + nonce_str + "]";
	}
	
}
