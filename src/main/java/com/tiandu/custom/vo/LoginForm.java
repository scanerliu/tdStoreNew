package com.tiandu.custom.vo;

/**
 * 登陆实体类
 * @author Lxb
 *
 */
public class LoginForm {
	/**
	 * 用户名
	 */
	private String username; 
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 记住我
	 */
	private Boolean rememberMe;
	/**
	 * 验证码
	 */
	private String signcode;
	/**
	 * 错误码
	 */
	private Integer errcode;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getSigncode() {
		return signcode;
	}
	public void setSigncode(String signcode) {
		this.signcode = signcode;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	
}
