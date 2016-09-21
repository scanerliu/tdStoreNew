package com.tiandu.menu.service;

import java.util.List;

import com.tiandu.menu.entity.TdProductMenu;
import com.tiandu.menu.entity.TdProductMenuType;
import com.tiandu.menu.search.TdProductMenuSearchCriteria;
import com.tiandu.product.entity.TdProductType;

public interface TdProductMenuService {

	public int insert(TdProductMenu u);
	public int save(TdProductMenu u);
	public TdProductMenu findOne(Integer id);
	public List<TdProductMenu> findByTdProductMenuSearchCriteria(TdProductMenuSearchCriteria sc);
	public int deleteFull(Integer id);
	public List<TdProductType> removeSeletedType(List<TdProductType> typeList, List<TdProductMenuType> list);
	public void deleteMenuTypes(Integer mid);
	public void saveMenuTypes(Integer mid, Integer[] typeid);
	public List<TdProductMenu> findMenuTree(TdProductMenuSearchCriteria sc);
}
