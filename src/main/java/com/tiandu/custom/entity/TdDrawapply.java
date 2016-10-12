package com.tiandu.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TdDrawapply {
    private Integer id;

    private Integer uid;

    private BigDecimal amount;
    
    private BigDecimal fee;

    private String carduser;

    private String idno;

    private Integer bankid;

    private String cardno;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer updateBy;

    private Date cashTime;

    private String remark;
    
    private TdUser user;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getCarduser() {
        return carduser;
    }

    public void setCarduser(String carduser) {
        this.carduser = carduser == null ? null : carduser.trim();
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno == null ? null : idno.trim();
    }

    public Integer getBankid() {
        return bankid;
    }

    public void setBankid(Integer bankid) {
        this.bankid = bankid;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Date getCashTime() {
        return cashTime;
    }

    public void setCashTime(Date cashTime) {
        this.cashTime = cashTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public String getStatusStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.status){
    		switch(this.status){
	    		case 1:
	    			sb.append("新申请");
	    			break;
	    		case 2:
	    			sb.append("已同意");
	    			break;
	    		case 3:
	    			sb.append("已拒绝");
	    			break;
	    		case 4:
	    			sb.append("已打款");
	    			break;
	    		case 5:
	    			sb.append("打款失败");
	    			break;
    		}
    	}
    	return sb.toString();
    }

	public TdUser getUser() {
		return user;
	}

	public void setUser(TdUser user) {
		this.user = user;
	}
	
	public String getBankname(){
		StringBuffer sb = new StringBuffer();
    	if(null!=this.bankid){
    		switch(this.bankid){
	    		case 1:
	    			sb.append("中国工商银行");
	    			break;
	    		case 2:
	    			sb.append("中国建设银行");
	    			break;
	    		case 3:
	    			sb.append("中国银行");
	    			break;
	    		case 4:
	    			sb.append("中国农业银行");
	    			break;
	    		case 5:
	    			sb.append("交通银行");
	    			break;
	    		case 6:
	    			sb.append("招商银行");
	    			break;
	    		case 7:
	    			sb.append("中国邮政储蓄银行");
	    			break;
	    		case 8:
	    			sb.append("中信银行");
	    			break;
	    		case 9:
	    			sb.append("光大银行");
	    			break;
	    		case 10:
	    			sb.append("民生银行");
	    			break;
	    		case 11:
	    			sb.append("兴业银行");
	    			break;
	    		case 12:
	    			sb.append("华夏银行");
	    			break;
	    		case 13:
	    			sb.append("平安银行");
	    			break;
    		}
    	}
    	return sb.toString();
	}
    
    
}