package com.tiandu.custom.entity;

public class TdMembership {
    private Integer id;

    private String name;

    private Integer level;

    private Integer upgradeAgents;

    private String tip;

    private Byte status = 1; // 默认启用

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUpgradeAgents() {
        return upgradeAgents;
    }

    public void setUpgradeAgents(Integer upgradeAgents) {
        this.upgradeAgents = upgradeAgents;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip == null ? null : tip.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
    
    public String getStatusStr(){
    	if(this.getStatus().equals(Byte.valueOf("1"))){
    		return "正常";
    	}else{
    		return "屏蔽";
    	}
    }
}