package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.search.TdUserAddressCriteria;

public interface TdUserAddressService {
	
	public TdUserAddress findOne(Integer id);

	public List<TdUserAddress> findBySearchCriteria(TdUserAddressCriteria sc);
	
	public Integer save(TdUserAddress userAddress);
	
	public Integer delete(Integer id);
	
	public void setIsDefaultFalse(Integer UserId);
}
