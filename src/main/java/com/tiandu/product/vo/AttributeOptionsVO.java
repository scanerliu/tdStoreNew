package com.tiandu.product.vo;

import java.util.Set;

public class AttributeOptionsVO {

	private String sname;	//属性名称
	
	private Set<String>  soptions;	 //属性值集合

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Set<String> getSoptions() {
		return soptions;
	}

	public void setSoptions(Set<String> soptions) {
		this.soptions = soptions;
	}
}
