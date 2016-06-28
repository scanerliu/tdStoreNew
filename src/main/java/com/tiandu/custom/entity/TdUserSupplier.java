package com.tiandu.custom.entity;

import java.util.Date;

public class TdUserSupplier {

	private Integer id;

	private Integer uid;

	private TdUser user;

	private Byte supplierType;

	private String note;

	private String images;

	private Byte status = 1; // 默认初始化为未审核

	private String reply;

	private Date createTime;

	private Date updateTime;

	private Integer updateBy;

	private TdUser updatePerson;

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

	public Byte getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Byte supplierType) {
		this.supplierType = supplierType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images == null ? null : images.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply == null ? null : reply.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public TdUser getUser() {
		return user;
	}

	public void setUser(TdUser user) {
		this.user = user;
	}

	public TdUser getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(TdUser updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getSupplierTypeStr() {
		if (this.getSupplierType().equals(Byte.valueOf("1"))) {
			return "个人";
		} else {
			return "公司";
		}
	}

	public String getStatusStr() {
		if (this.getStatus().equals(Byte.valueOf("1"))) {
			return "未审核";
		} else if (this.getStatus().equals(Byte.valueOf("2"))) {
			return "通过";
		} else {
			return "未通过";
		}

	}

}