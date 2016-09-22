package com.tiandu.article.search;

import java.util.Date;

import com.tiandu.common.search.SearchCriteria;

public class TdAdvertisementSearchCriteria extends SearchCriteria{

    private Byte status;
    
    private Date createTime;
    
    private Date endTime;
    
    private Integer adsId;
    
    private Integer regionId;
    
    private Integer typeId;
    
    private Integer floorId;
    
    private Integer createBy;

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getAdsId() {
		return adsId;
	}

	public void setAdsId(Integer adsId) {
		this.adsId = adsId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}
	
}
