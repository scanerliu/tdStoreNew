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
	/**
	 * 根据地区id查找地区和地区的上级(默认支持三级)
	 * @param id
	 * @return
	 */
	public TdDistrict findOneFull(Integer id);
	
	// 是否是直辖市
	public boolean isCentralCity(String cityName); 
	
}
