package com.tiandu.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tiandu.custom.vo.ProfitInfo;

public class TdUserAccountLog {
	/**
	 * 后台调整
	 */
	public static final Byte USERACCOUNTLOG_TYPE_SYSTEM = 1;
	/**
	 * 分润收入
	 */
	public static final Byte USERACCOUNTLOG_TYPE_PROFIT_INCOME = 2;
	/**
	 * 订单退货退款
	 */
	public static final Byte USERACCOUNTLOG_TYPE_ORDER_REFUND = 3;
	/**
	 * 用户提现
	 */
	public static final Byte USERACCOUNTLOG_TYPE_WITHDRAWALS = 4;
	
    private Integer id;

    private Integer uid;

    private BigDecimal preamount;

    private Byte type;

    private BigDecimal upamount;

    private Date createTime;

    private String note;

    private String relation;
    
    private String username;
    
    private BigDecimal totalUpamount;
    /**
     * 收益明细，由relation字段转换而来，展示时候用
     */
    private ProfitInfo profit;

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

    public BigDecimal getPreamount() {
        return preamount;
    }

    public void setPreamount(BigDecimal preamount) {
        this.preamount = preamount;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public BigDecimal getUpamount() {
        return upamount;
    }

    public void setUpamount(BigDecimal upamount) {
        this.upamount = upamount;
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
    
    
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public BigDecimal getTotalUpamount() {
		return totalUpamount;
	}

	public void setTotalUpamount(BigDecimal totalUpamount) {
		this.totalUpamount = totalUpamount;
	}

	public ProfitInfo getProfit() {
		if(null!=this.profit){
			return profit;
		}
		if(null!=this.getRelation() && StringUtils.isNotEmpty(this.getRelation())){
			try {
				Gson json = new Gson();
				ProfitInfo prof = json.fromJson(this.getRelation(), ProfitInfo.class);
				this.profit = prof;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return profit;
	}

	public void setProfit(ProfitInfo profit) {
		this.profit = profit;
	}

	/**
     * 获取金额变更类型文字说明
     * @return
     */
    public String getTypeStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getType()){
    		if(this.getType().equals(USERACCOUNTLOG_TYPE_SYSTEM)){
    			sb.append("后台调整");
    		}else if(this.getType().equals(USERACCOUNTLOG_TYPE_PROFIT_INCOME)){
    			sb.append("分润收入");
    		}else if(this.getType().equals(USERACCOUNTLOG_TYPE_ORDER_REFUND)){
    			sb.append("订单退款");
    		}else if(this.getType().equals(USERACCOUNTLOG_TYPE_WITHDRAWALS)){
    			sb.append("提现");
    		}
    		
    	}
    	return sb.toString();
    }
}