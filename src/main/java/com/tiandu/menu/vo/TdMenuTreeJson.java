package com.tiandu.menu.vo;

import java.util.List;

public class TdMenuTreeJson {
	private Integer id;
	private String text;
	private String state;
	private Boolean checked;
	private List<TdMenuTreeJson> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public List<TdMenuTreeJson> getChildren() {
		return children;
	}
	public void setChildren(List<TdMenuTreeJson> children) {
		this.children = children;
	}
	
	
}
