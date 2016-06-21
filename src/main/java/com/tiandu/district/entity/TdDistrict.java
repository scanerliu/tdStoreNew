package com.tiandu.district.entity;

public class TdDistrict {
    private Integer id;

    private String name;

    private Byte level;

    private Integer upid;

    private Short displayorder;

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
}