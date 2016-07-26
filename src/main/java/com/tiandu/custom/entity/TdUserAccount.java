package com.tiandu.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TdUserAccount {
	
	/**
	 * 账户状态，1-正常
	 */
	public static final Byte ACCOUNT_STATUS_ACTIVE = 1;
	/**
	 * 账户状态，2-锁定
	 */
	public static final Byte ACCOUNT_STATUS_FORBBIDEN = 2;
	
    private Integer uid;

    private BigDecimal amount;

    private Byte status;

    private Date updateTime;

    private Integer updateBy;

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
}