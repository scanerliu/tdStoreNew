package com.tiandu.custom.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Administrator
 *
 */
public class TdRole {
	private Integer id;

	private String name;
	
	private String title;

	private Set<TdPermission> permissionSet = new HashSet<TdPermission>();
	
	/**
	 * 后台保存权限使用
	 */
	private List<TdPermission> permissionList = new ArrayList<TdPermission>();

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

	public Set<TdPermission> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<TdPermission> permissionSet) {
		this.permissionSet = permissionSet;
	}
	
	public List<TdPermission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<TdPermission> permissionList) {
		this.permissionList = permissionList;
	}

	public Boolean hasPermissionId(Integer permId){
		if(null!=this.permissionSet){
			for(TdPermission per : this.permissionSet){
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