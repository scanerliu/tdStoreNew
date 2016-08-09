package com.tiandu.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TdExperienceStore {
	private Integer id;

	private Integer uid;

	private TdUser user;

	private Integer regionId;

	private String regionFullName;

	private String address;

	private String telphone;

	private String storeImages;

	private String storeTypeIds;

	private BigDecimal lng;

	private BigDecimal lat;

	private String storeTypeNames;

	private Byte status = 1; // 默认状态为：未审核

	private Date createTime;

	private Date updateTime;

	private Integer updateBy;

	private TdUser updatePerson;

	private Integer sort = 99; // 默认排序为99

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

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionFullName() {
		return regionFullName;
	}

	public void setRegionFullName(String regionFullName) {
		this.regionFullName = regionFullName == null ? null : regionFullName.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone == null ? null : telphone.trim();
	}

	public String getStoreImages() {
		return storeImages;
	}

	public void setStoreImages(String storeImages) {
		this.storeImages = storeImages == null ? null : storeImages.trim();
	}

	public String getStoreTypeIds() {
		return storeTypeIds;
	}

	public void setStoreTypeIds(String storeTypeIds) {
		this.storeTypeIds = storeTypeIds == null ? null : storeTypeIds.trim();
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public String getStoreTypeNames() {
		return storeTypeNames;
	}

	public void setStoreTypeNames(String storeTypeNames) {
		this.storeTypeNames = storeTypeNames == null ? null : storeTypeNames.trim();
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getStatusStr() {
		if (this.getStatus().equals(Byte.valueOf("2"))) {
			return "未审核";
		} else if (this.getStatus().equals(Byte.valueOf("1"))) {
			return "已通过";
		} else {
			return "未通过";
		}
	}
	
	public boolean isProductTypeStore(Integer typeId){
		if(null!=this.getStoreTypeIds()){
			String str = "["+typeId+"]";
			if(this.getStoreTypeIds().indexOf(str)>=0){
				return true;
			}
		}
		return false;
	}

}