package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.entity.mapper.TdUserAddressMapper;
import com.tiandu.custom.search.TdUserAddressCriteria;
import com.tiandu.custom.service.TdUserAddressService;

@Service
public class TdUserAddressServiceImpl implements TdUserAddressService {
	
	@Autowired
	private TdUserAddressMapper tdUserAddressMapper;
	
	public TdUserAddress findOne(Integer id)
	{
		return tdUserAddressMapper.selectByPrimaryKey(id);
	}
	
	public List<TdUserAddress> findBySearchCriteria(TdUserAddressCriteria Sc)
	{
		return tdUserAddressMapper.findBySearchCriteria(Sc);
	}
	
	public Integer save(TdUserAddress userAddress)
	{
		if(userAddress.getId() == null)
		{
			return tdUserAddressMapper.insert(userAddress);
		}
		else
		{
			return tdUserAddressMapper.updateByPrimaryKey(userAddress);
		}
	}
	
	public Integer delete(Integer id)
	{
		return tdUserAddressMapper.deleteByPrimaryKey(id);
	}
	
	public void setIsDefaultFalse(Integer userId)
	{
		TdUserAddressCriteria sc = new TdUserAddressCriteria(userId,true);
		List<TdUserAddress> userAddresses = tdUserAddressMapper.findBySearchCriteria(sc);
		if(userAddresses != null && userAddresses.size() > 0)
		{
			for(int index = 0 ;index <userAddresses.size(); index ++)
			{
				TdUserAddress userAddress = userAddresses.get(index);
				userAddress.setIsDefault(false);
				this.save(userAddress);
			}
		}
	}
	
}
