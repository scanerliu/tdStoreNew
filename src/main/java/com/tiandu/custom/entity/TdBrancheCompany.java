package com.tiandu.custom.entity;

import java.util.Date;

import com.tiandu.district.entity.TdDistrict;

public class TdBrancheCompany {
    private Integer id;

    private Integer uid;
    
    private TdUser user;

    private Integer level;

    private Integer regionId;
    
    private TdDistrict district;

    private Byte status = 1; // 默认初始化为正常

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
    
    public String getStatusStr(){
    	if(this.getStatus().equals(Byte.valueOf("1"))){
    		return "正常";
    	}else{
    		return "禁用";
    	}
    }
    
    public String getLevelStr(){
    	if(this.getLevel().equals(1)){
    		return "平台";
    	}else if(this.getLevel().equals(2)){
    		return "省市";
    	}else{
    		return "区县";
    	}
    }
}