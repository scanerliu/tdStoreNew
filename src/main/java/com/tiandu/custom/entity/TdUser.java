package com.tiandu.custom.entity;

import java.util.HashSet;
import java.util.Set;

public class TdUser {
	
	/**
	 * 用户状态2 锁定
	 */
	public final static Byte ustatus_forbbiden = 2;
	/**
	 * 用户状态1 正常
	 */
	public final static Byte ustatus_active = 1;
    private Integer uid;

    private String uname;

    private String upassword;

    private Byte ustatus;

    private Byte utype;
    
    private Set<TdRole> roleSet = new HashSet<TdRole>();

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public Byte getUstatus() {
        return ustatus;
    }

    public void setUstatus(Byte ustatus) {
        this.ustatus = ustatus;
    }

    public Byte getUtype() {
        return utype;
    }

    public void setUtype(Byte utype) {
        this.utype = utype;
    }

	public Set<TdRole> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<TdRole> roleSet) {
		this.roleSet = roleSet;
	}
	
	public Boolean isLocked(){
		if(TdUser.ustatus_forbbiden==this.getUstatus()){
			return true;
		}
		return false;
	}
    
}