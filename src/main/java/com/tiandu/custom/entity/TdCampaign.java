package com.tiandu.custom.entity;

import java.util.Date;

import com.tiandu.district.entity.TdDistrict;

public class TdCampaign {
	private Integer id;

	private Integer regionId;

	private TdDistrict district;

	private Integer quota;

	private Byte status = 1; // 默认初始化正常

	private String statusStr;

	private Date createTime;

	private Integer createBy;

	private TdUser createPerson;

	private Date updateTime;

	private Integer updateBy;

	private TdUser updatePerson;

	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	public TdDistrict getDistrict() {
		return district;
	}

	public void setDistrict(TdDistrict district) {
		this.district = district;
	}

	public TdUser getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(TdUser updatePerson) {
		this.updatePerson = updatePerson;
	}

	public TdUser getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(TdUser createPerson) {
		this.createPerson = createPerson;
	}
	

	public String getStatusStr() {
		if (this.getStatus().equals(Byte.valueOf("1"))) {
			return "正常";
		} else {
			return "禁用";
		}
	}
	
}