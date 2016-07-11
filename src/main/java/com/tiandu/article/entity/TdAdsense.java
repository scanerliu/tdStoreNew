package com.tiandu.article.entity;

import java.util.Date;

import com.tiandu.common.entity.TdBaseEntity;
import com.tiandu.custom.entity.TdUser;

public class TdAdsense extends TdBaseEntity{
    private Integer id;

    private String name;

    private String adkey;

    private Integer width;

    private Integer height;

    private Date updateTime;

    private Integer updateBy;
    
    private TdUser updateUser;
    

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

    public String getAdkey() {
        return adkey;
    }

    public void setAdkey(String adkey) {
        this.adkey = adkey == null ? null : adkey.trim();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

	public TdUser getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(TdUser updateUser) {
		this.updateUser = updateUser;
	}
    
    
    
    
}