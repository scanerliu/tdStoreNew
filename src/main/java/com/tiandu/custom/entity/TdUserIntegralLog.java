package com.tiandu.custom.entity;

import java.util.Date;

public class TdUserIntegralLog {
	/**
	 * 后台调整
	 */
	public static final Byte USERINTEGRALLOG_TYPE_SYSTEM = 1;
	/**
	 * 签到送积分
	 */
	public static final Byte USERINTEGRALLOG_TYPE_SIGN = 2;
	/**
	 * 礼品兑换
	 */
	public static final Byte USERINTEGRALLOG_TYPE_GIFT_EXCHANGE = 3;
	/**
	 * 抵扣订单金额
	 */
	public static final Byte USERINTEGRALLOG_TYPE_DEDUCTIBLE_AMOUNT = 4;
	/**
	 * 注册赠送积分
	 */
	public static final Byte USERINTEGRALLOG_TYPE_REGISTER = 5;
	
    private Integer id;

    private Integer uid;

    private Integer preintegral;

    private Byte type;

    private Integer integral;

    private Date createTime;

    private String note;

    private String relation;

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

    public Integer getPreintegral() {
        return preintegral;
    }

    public void setPreintegral(Integer preintegral) {
        this.preintegral = preintegral;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }
    /**
     * 获取积分变更类型文字说明
     * @return
     */
    public String getTypeStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getType()){
    		if(this.getType().equals(USERINTEGRALLOG_TYPE_SYSTEM)){
    			sb.append("后台调整");
    		}else if(this.getType().equals(USERINTEGRALLOG_TYPE_SIGN)){
    			sb.append("签到送积分");
    		}else if(this.getType().equals(USERINTEGRALLOG_TYPE_GIFT_EXCHANGE)){
    			sb.append("积分兑换礼品");
    		}else if(this.getType().equals(USERINTEGRALLOG_TYPE_DEDUCTIBLE_AMOUNT)){
    			sb.append("抵扣订单金额");
    		}else if(this.getType().equals(USERINTEGRALLOG_TYPE_REGISTER)){
    			sb.append("注册赠送积分");
    		}
    		
    	}
    	return sb.toString();
    }
}