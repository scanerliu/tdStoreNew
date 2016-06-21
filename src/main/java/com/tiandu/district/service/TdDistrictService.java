package com.tiandu.district.service;

import java.util.List;

import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.search.TdDistrictSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdDistrictService {

	public int insert(TdDistrict u);
	public TdDistrict findOne(Integer id);
	public List<TdDistrict> findBySearchCriteria(TdDistrictSearchCriteria sc);
	public Integer save(TdDistrict district);
	public Integer delete(Integer id);
	public List<TdDistrict> getDistrictByUpid(Integer upid);
	
}
