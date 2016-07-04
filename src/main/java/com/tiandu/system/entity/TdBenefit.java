package com.tiandu.system.entity;

import java.util.Date;

import com.tiandu.common.entity.TdBaseEntity;
import com.tiandu.common.utils.ConstantsUtils;

public class TdBenefit extends TdBaseEntity {
	
    private Integer id;

    private Integer benefitType;

    private Integer groupId;

    private Integer level;

    private Byte percent;

    private Date updateTime;

    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(Integer benefitType) {
		this.benefitType = benefitType;
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

    public Byte getPercent() {
        return percent;
    }

    public void setPercent(Byte percent) {
        this.percent = percent;
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
    		if(ConstantsUtils.AGENT_GROUPID_AGENT.equals(this.getGroupId())){
    			sb.append("单类代理");
    		}else if(ConstantsUtils.AGENT_GROUPID_BRANCH.equals(this.getGroupId())){
    			sb.append("分公司");
    		}else if(ConstantsUtils.AGENT_GROUPID_DISTRIBUTION.equals(this.getGroupId())){
    			sb.append("三级分销");
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
    		if(ConstantsUtils.AGENT_GROUPID_AGENT.equals(this.getGroupId())){
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
    		}else if(ConstantsUtils.AGENT_GROUPID_BRANCH.equals(this.getGroupId())){
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
    		}else if(ConstantsUtils.AGENT_GROUPID_DISTRIBUTION.equals(this.getGroupId())){
    			switch(this.getLevel()){
					case 1:
						sb.append("一级");
						break;
					case 2:
						sb.append("二级");
						break;
					case 3:
						sb.append("三级");
						break;
					case 4:
						sb.append("自己购买");
						break;
					default:
				}
    		}    		
    	}
    	return sb.toString();
    }
}