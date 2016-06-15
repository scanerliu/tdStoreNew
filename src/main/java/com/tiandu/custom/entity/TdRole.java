package com.tiandu.custom.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tiandu.menu.entity.TdMenu;

/**
 * 
 * @author Administrator
 *
 */
public class TdRole {
	private Integer id;

	private String name;
	
	private String title;

	private Set<TdMenu> menuSet = new HashSet<TdMenu>();
	
	/**
	 * 后台保存权限使用
	 */
	private List<TdMenu> menuList = new ArrayList<TdMenu>();
	private String menuIds; //多个menuId 用逗号隔开

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<TdMenu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<TdMenu> menuSet) {
		this.menuSet = menuSet;
	}

	public List<TdMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<TdMenu> menuList) {
		this.menuList = menuList;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public Boolean hasPermissionId(Integer permId){
		if(null!=this.getMenuSet()){
			for(TdMenu per : this.getMenuSet()){
				if(per.getId().equals(permId)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TdRole other = (TdRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}