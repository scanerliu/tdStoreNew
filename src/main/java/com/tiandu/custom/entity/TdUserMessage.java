package com.tiandu.custom.entity;

import java.util.Date;

public class TdUserMessage {
	private Integer id;

	private Integer uid;

	private TdUser user;

	private Byte msgType;

	private String title;

	private Date createTime;

	private Integer relateId = 0; // 默认初始化为0，即系统消息

	private Byte status = 1; // 默认初始化为未读

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Byte getMsgType() {
		return msgType;
	}

	public void setMsgType(Byte msgType) {
		this.msgType = msgType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRelateId() {
		return relateId;
	}

	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getStatusStr() {
		if (this.getStatus().equals(Byte.valueOf("1"))) {
			return "未读";
		} else {
			return "已读";
		}
	}

	public String getMsgTypeStr() {
		if (this.getMsgType().equals(Byte.valueOf("1"))) {
			return "系统消息";
		} else if (this.getMsgType().equals(Byte.valueOf("2"))) {
			return "订单消息";
		} else {
			return "门店申请消息";
		}
	}

	public TdUser getUser() {
		return user;
	}

	public void setUser(TdUser user) {
		this.user = user;
	}

}