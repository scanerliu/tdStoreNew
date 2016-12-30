package com.tiandu.custom.search;

import com.tiandu.common.search.SearchCriteria;

public class TdUserSearchCriteria extends SearchCriteria {

	private String uname; // 用户名
	private String keyword; // 关键字

	private Byte ustatus; // 用户状态，1-正常，2-屏蔽

	private Byte utype; // 用户类型，1-普通会员，2-平台用户
	private Byte supplierType; // 供应商资质认证，0-未认证，1-个人资质供应商，2-公司资质供应商
	private Integer tempsupplier; //供应商类型：0-未购买，1-零时供应商，2-购买供应商
	private Byte uverification; // 验证状态:1-已验证，2-未验证
	private Boolean branch = false; // 分公司
	private Boolean agent = false; // 单类代理

	private Boolean getUpdateUser = false; // 获取更新人信息

	private String parentIdsStr; // 下三级所有id字符串，格式为"[1],[2],[3]"
	private Integer parentId; // 父类id
	
	private Integer uregionId; // 地区id
	
	private String jointId; //第三方Id
	
	private Byte ugenter;//性别 1-男，2-女，3-保密
	
	private Integer uprovinceId; // 所属省id
	
	private Integer clevelType;	//下属类别查询，1-一级下属，2-二级下属，3-三级下属

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public Byte getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Byte supplierType) {
		this.supplierType = supplierType;
	}

	public Byte getUverification() {
		return uverification;
	}

	public void setUverification(Byte uverification) {
		this.uverification = uverification;
	}

	public Boolean getBranch() {
		return branch;
	}

	public void setBranch(Boolean branch) {
		this.branch = branch;
	}

	public Boolean getAgent() {
		return agent;
	}

	public void setAgent(Boolean agent) {
		this.agent = agent;
	}

	public Boolean getGetUpdateUser() {
		return getUpdateUser;
	}

	public void setGetUpdateUser(Boolean getUpdateUser) {
		this.getUpdateUser = getUpdateUser;
	}

	public String getParentIdsStr() {
		return parentIdsStr;
	}

	public void setParentIdsStr(String parentIdsStr) {
		this.parentIdsStr = parentIdsStr;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getUregionId() {
		return uregionId;
	}

	public void setUregionId(Integer uregionId) {
		this.uregionId = uregionId;
	}

	public String getJointId() {
		return jointId;
	}

	public void setJointId(String jointId) {
		this.jointId = jointId;
	}

	public Integer getUprovinceId() {
		return uprovinceId;
	}

	public void setUprovinceId(Integer uprovinceId) {
		this.uprovinceId = uprovinceId;
	}

	public Integer getClevelType() {
		return clevelType;
	}

	public void setClevelType(Integer clevelType) {
		this.clevelType = clevelType;
	}

	public Integer getTempsupplier() {
		return tempsupplier;
	}

	public void setTempsupplier(Integer tempsupplier) {
		this.tempsupplier = tempsupplier;
	}

	public Byte getUgenter() {
		return ugenter;
	}

	public void setUgenter(Byte ugenter) {
		this.ugenter = ugenter;
	}
	
}
