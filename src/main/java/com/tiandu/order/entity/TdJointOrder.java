package com.tiandu.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TdJointOrder {
    private Integer id;

    private String jno;

    private BigDecimal amount;

    private Byte paymentId;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    /**
     * 生成订单错误信息
     */
    private String errMsg;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJno() {
        return jno;
    }

    public void setJno(String jno) {
        this.jno = jno == null ? null : jno.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Byte paymentId) {
        this.paymentId = paymentId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
    
}