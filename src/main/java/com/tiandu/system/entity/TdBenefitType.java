package com.tiandu.system.entity;

import java.util.Date;
import java.util.List;

import com.tiandu.common.entity.TdBaseEntity;

public class TdBenefitType extends TdBaseEntity {
    private Integer id;

    private String name;

    private Date updateTime;

    private Integer updateBy;
    
    private List<TdBenefit> benefitList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

	public List<TdBenefit> getBenefitList() {
		return benefitList;
	}

	public void setBenefitList(List<TdBenefit> benefitList) {
		this.benefitList = benefitList;
	}
    
    
}