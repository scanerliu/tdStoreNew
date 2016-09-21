package com.tiandu.menu.service;

import java.util.List;

import com.tiandu.menu.entity.TdIndexFloor;
import com.tiandu.menu.entity.TdIndexFloorType;
import com.tiandu.menu.search.TdIndexFloorSearchCriteria;
import com.tiandu.product.entity.TdProductType;

public interface TdIndexFloorService {

	public int insert(TdIndexFloor u);
	public int save(TdIndexFloor u);
	public TdIndexFloor findOne(Integer id);
	public List<TdIndexFloor> findByTdIndexFloorSearchCriteria(TdIndexFloorSearchCriteria sc);
	public int deleteFull(Integer id);
	public List<TdProductType> removeSeletedType(List<TdProductType> typeList, List<TdIndexFloorType> list);
	public void deleteFloorTypes(Integer mid);
	public void saveFloorTypes(Integer mid, Integer[] typeid);
}
