package com.tiandu.product.entity;

import java.util.Date;

import com.tiandu.common.entity.TdBaseEntity;

public class TdBrand extends TdBaseEntity{
    private Integer id;

    private String name;
    
    private String imageUrl;

    private Integer status;

    private Integer sortBy;

    private Integer updateBy;

    private Date updateTime;
    
    private String letter; //首字母大写，展示使用

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

    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortBy() {
        return sortBy;
    }

    public void setSortBy(Integer sortBy) {
        this.sortBy = sortBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	/**
     * 获取品牌状态文字说明
     * @return
     */
    public String getStatusStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getStatus()){
    		switch (this.getStatus()) {
			case 1:
				sb.append("启用");
				break;
			case 2:
				sb.append("禁用");
				break;
			default:
				break;
			}
    	}
    	
    	return sb.toString();
    }
}