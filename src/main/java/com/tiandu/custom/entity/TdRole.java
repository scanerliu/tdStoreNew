package com.tiandu.custom.entity;

import java.util.HashSet;
import java.util.Set;

public class TdRole {
	private Integer id;

	private String name;

	private Set<TdPermission> permissionSet = new HashSet<TdPermission>();

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

	public Set<TdPermission> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<TdPermission> permissionSet) {
		this.permissionSet = permissionSet;
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