package com.tiandu.custom.service;

import java.util.List;
import java.util.Map;

import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.search.TdUserAddressCriteria;

public interface TdUserAddressService {
	
	public TdUserAddress findOne(Integer id);

	public List<TdUserAddress> findBySearchCriteria(TdUserAddressCriteria sc);
	
	public Integer save(TdUserAddress userAddress);
	
	public Integer delete(Integer id);
	
	
	/**
	 * 根据用户ID设置所有地址的默认地址为：false
	 * @param UserId
	 */
	public void setIsDefaultFalse(Integer UserId);
	
	/**
	 * 根据用户地址的regionID返回包含省市区id的MAP
	 * @param resMap 用于存储返回的省市区ID 的 map
	 * @param regionId 地区id
	 * @return	resMap 省市区相应Id :key:province,city,district
	 */
	public Map<String,Object> getUserDistrictIdByRegionId(Map<String,Object> resMap,Integer regionId);
	
	/**
	 * 根据用户ID查找默认地址
	 * @param uId 用户ID
	 * @return 返回默认地址，如果无默认地址则返回最新添加的地址，若用户没有添加则返回null
	 */
	public TdUserAddress defaultAddressByUid(Integer uId);
}
