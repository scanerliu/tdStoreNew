package com.tiandu.custom.entity;

import java.util.Date;

import com.tiandu.district.entity.TdDistrict;
import com.tiandu.product.entity.TdProductType;

public class TdAgent {
	private Integer id;

	private Integer uid;

	private TdUser user;

	private Integer level;

	private Integer regionId;

	private TdDistrict district;

	private Integer productTypeId;

	private TdProductType productType;

	private Date updateDate;

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getLevelStr() {
		if (this.getLevel().equals(1)) {
			return "全国代理";
		} else if (this.getLevel().equals(2)) {
			return "省市代理";
		} else if (this.getLevel().equals(3)) {
			return "区县代理";
		} else {
			return "体验店";
		}
	}

	public TdUser getUser() {
		return user;
	}

	public void setUser(TdUser user) {
		this.user = user;
	}

	public TdDistrict getDistrict() {
		return district;
	}

	public void setDistrict(TdDistrict district) {
		this.district = district;
	}

	public TdProductType getProductType() {
		return productType;
	}

	public void setProductType(TdProductType productType) {
		this.productType = productType;
	}

	public TdUser getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(TdUser updatePerson) {
		this.updatePerson = updatePerson;
	}

}