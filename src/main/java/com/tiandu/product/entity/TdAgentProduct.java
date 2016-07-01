package com.tiandu.product.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.tiandu.common.entity.TdBaseEntity;

public class TdAgentProduct extends TdBaseEntity {
	/**
	 * 代理类型-1 单类代理
	 */
	public static final Integer AGENTPRODUCT_GROUPID_AGENT = 1;
	/**
	 * 代理类型-2 分公司
	 */
	public static final Integer AGENTPRODUCT_GROUPID_BRANCH = 2;
	
    private Integer id;

    private String title;

    private String note;

    private String imageUrl;

    private BigDecimal supplierPrice;

    private BigDecimal salesPrice;

    private Integer groupId;

    private Integer level;

    private Date updateTime;

    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
    /**
     * 获取代理类型文字说明
     * @return
     */
    public String getGroupIdStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getGroupId()){
    		if(TdAgentProduct.AGENTPRODUCT_GROUPID_AGENT.equals(this.getGroupId())){
    			sb.append("单类代理");
    		}else if(TdAgentProduct.AGENTPRODUCT_GROUPID_BRANCH.equals(this.getGroupId())){
    			sb.append("分公司");
    		}    		
    	}
    	return sb.toString();
    }
    
    /**
     * 获取级别文字说明
     * @return
     */
    public String getLevelStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getGroupId()&&null!=this.getLevel()){
    		if(TdAgentProduct.AGENTPRODUCT_GROUPID_AGENT.equals(this.getGroupId())){
    			switch(this.getLevel()){
    				case 1:
    					sb.append("全国");
    					break;
    				case 2:
    					sb.append("省市");
    					break;
    				case 3:
    					sb.append("区县");
    					break;
    				case 4:
    					sb.append("体验店");
    					break;
    				default:
    			}
    		}else if(TdAgentProduct.AGENTPRODUCT_GROUPID_BRANCH.equals(this.getGroupId())){
    			switch(this.getLevel()){
					case 1:
						sb.append("平台");
						break;
					case 2:
						sb.append("省市");
						break;
					case 3:
						sb.append("区县");
						break;
					default:
				}
    		}    		
    	}
    	return sb.toString();
    }
    
}