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
	 * 根据地区ID返回相应的省市区MAP
	 * @param resMap 返回时，返回此这个参数
	 * @param districtId 地区id
	 * @return	resMap 新增了省市区相应的key:(province,city,district) value ：
	 */
	public Map<String,Object> getUserDistrictIdByUserAddress(Map<String,Object> resMap,Integer districtId);
}
