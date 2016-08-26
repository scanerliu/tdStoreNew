package com.tiandu.custom.vo;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 微信通过access_token和openid拉取用户信息响应封装类
 * @author liuxinbing
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeChatUserInfoResponse {

	private String nickname;
	private String sex;
	private String openid;
	private String headimgurl;
	private String unionid;
	private Integer errcode;
	private String errmsg;
	
	public WeChatUserInfoResponse() {
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	@Override
	public String toString() {
		return "WeChatUserInfoResponse [nickname=" + nickname + ", sex=" + sex + ", openid=" + openid + ", headimgurl="
				+ headimgurl + ", unionid=" + unionid + ", errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}

	/**
	 * 获取性别
	 * @return
	 */
	public Byte getGenter(){
		Byte genter = 3;
		if(StringUtils.isNotBlank(sex)){
			if(!"0".equals(sex)){
				try {
					genter = Byte.valueOf(sex);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return genter;
	}

}
