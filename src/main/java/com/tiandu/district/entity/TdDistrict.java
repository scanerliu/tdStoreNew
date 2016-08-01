package com.tiandu.district.entity;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

public class TdDistrict {
    private Integer id;

    private String name;

    private Byte level;

    private Integer upid;

    private Short displayorder;
    
    
    /**
     * 地区的上级地区
     */
    private TdDistrict parent;

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

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Integer getUpid() {
        return upid;
    }

    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public Short getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(Short displayorder) {
        this.displayorder = displayorder;
    }
    
    public TdDistrict getParent() {
		return parent;
	}

	public void setParent(TdDistrict parent) {
		this.parent = parent;
	}
	

	/**
     * 直辖市判断 根据id判断
     * @return
     */
    public Boolean gentralCity(){
    	int[] gentralcitys = new int[]{1,2,9,22};
    	if(null!=this.getId() && ArrayUtils.contains(gentralcitys,this.getId())){
    		return true;
    	}
    	return false;
    }
    /**
     * 获取地区的省份直辖市id
     * @return
     */
    public Integer getRegionProvinceId(){
    	if(null!=this.getParent()){
    		if(this.getParent().getUpid()==0){
    			return this.getParent().getId();
    		}else if(null!=this.getParent().getParent()){
    			return this.getParent().getParent().getId();
    		}
    	}
    	return null;
    }
    /**
     * 获取地区的省份直辖市名称
     * @return
     */
    public String getRegionProvinceName(){
    	if(null!=this.getParent()){
    		if(this.getParent().getUpid()==0){
    			return this.getParent().getName();
    		}else if(null!=this.getParent().getParent()){
    			return this.getParent().getParent().getName();
    		}
    	}
    	return null;
    }
    /**
     * 获取地区的省份直辖市
     * @return
     */
    public TdDistrict getRegionProvince(){
    	if(null!=this.getParent()){
    		if(this.getParent().getUpid()==0){
    			return this.getParent();
    		}else if(null!=this.getParent().getParent()){
    			return this.getParent().getParent();
    		}
    	}
    	return null;
    }
    
    /**
     * 获取地区的path,如：[2][12]
     * @return
     */
    public String getRegionPath(){
    	if(null!=this.getParent()){
    		if(this.getParent().getUpid()==0){
    			return "["+this.getParent().getId()+"]"+"["+this.getId()+"]";
    		}else{
    			return "["+this.getParent().getUpid()+"]"+"["+this.getParent().getId()+"]"+"["+this.getId()+"]";
    		}
    	}
    	return null;
    }
    /**
     * 获取地区的fullname,如：重庆市 渝北区
     * @return
     */
    public String getRegionFullName(){
    	if(null!=this.getParent()){
    		if(this.getParent().getUpid()==0){
    			return this.getParent().getName()+" "+this.getName();
    		}else{
    			return this.getParent().getParent().getName()+" "+this.getParent().getName()+" "+this.getName();
    		}
    	}
    	return null;
    }
    
    /**
     *是否代理 区
     * @return
     */
    public Boolean isAgentRegion(){
    	if(null!=this.getParent()){
    		if(this.getParent().getUpid()==0){
    			if(this.getParent().gentralCity()){
    				return true;
    			}
    		}else{
    			return true;
    		}
    	}else if(null!=this.getUpid() && this.getUpid()==0){
    		return true;
    	}
    	return false;
    }
}