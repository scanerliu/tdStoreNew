package com.tiandu.custom.entity;

import java.util.ArrayList;
import java.util.Date;
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
	
	public static final Byte ROLE_TYPE_ADMIN = 1; //平台角色类型
	public static final Byte ROLE_TYPE_CLIENT = 2; //前台角色类型
	private Integer id;

	private String name;
	
	private String title;
	
	private Integer updateBy;

	private Date updateTime;
	
	private Byte type; //类型：1-平台角色，2-会员角色

	private Set<TdMenu> menuSet = new HashSet<TdMenu>();
	
	/**
	 * 更新人
	 */
	private TdUser updateUser;
	
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

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public TdUser getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(TdUser updateUser) {
		this.updateUser = updateUser;
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

	/**
	 * 判断是否权限id
	 * @param permId 权限id
	 * @return
	 */
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
	
	/**
     * 获取角色类型文字说明
     * @return
     */
	public String getTypeStr(){
		StringBuffer sb = new StringBuffer(); 
		if(null!=this.getType()){
			if(this.getType().equals(TdRole.ROLE_TYPE_ADMIN)){
				sb.append("平台角色");
			}else{
				sb.append("会员角色");
			}
		}
		return sb.toString();
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