package com.tiandu.custom.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TdUserAccountLog {
    private Integer id;

    private Integer uid;

    private BigDecimal preamount;

    private Byte type;

    private BigDecimal upamount;

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
}