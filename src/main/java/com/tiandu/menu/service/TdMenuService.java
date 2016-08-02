package com.tiandu.menu.service;

import java.util.List;

import com.tiandu.custom.entity.TdRole;
import com.tiandu.menu.entity.TdMenu;
import com.tiandu.menu.search.TdMenuSearchCriteria;

public interface TdMenuService {

	public int insert(TdMenu u);
	public TdMenu findOne(Integer id);
	public List<TdMenu> findByTdMenuSearchCriteria(TdMenuSearchCriteria sc);
	/**
	 * 获取菜单树(只获取两级目录树)
	 * @param parentId
	 * @return
	 */
	public List<TdMenu> getMenuTreeByParentId(Integer parentId);
	/**
	 * 获取菜单树(只获取三级目录树)
	 * @param parentId
	 * @return
	 */
	public List<TdMenu> getMenuTreeAll();
	/**
	 * 返回权限列表json
	 * @param role
	 * @param menuList
	 * @return
	 */
	public String getGsonTree(TdRole role, List<TdMenu> menuList);
}
