package com.tiandu.custom.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.entity.mapper.TdUserAddressMapper;
import com.tiandu.custom.search.TdUserAddressCriteria;
import com.tiandu.custom.service.TdUserAddressService;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.service.TdDistrictService;

@Service
public class TdUserAddressServiceImpl implements TdUserAddressService {
	
	@Autowired
	private TdUserAddressMapper tdUserAddressMapper;
	
	@Autowired
	private TdDistrictService tdDistrictService;
	
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
	
	public Map<String,Object> getUserDistrictIdByUserAddress(Map<String,Object> resMap,Integer districtId)
	{
		TdDistrict district = tdDistrictService.findOne(districtId);
		if(district != null && district.getUpid() != 0)
		{
			resMap.put(district.getUpid().toString() , districtId);
			this.getUserDistrictIdByUserAddress(resMap, district.getUpid());
		}
		else
		{
			Integer frist = (Integer)resMap.get(districtId.toString());
			Integer second = (Integer)resMap.get(frist.toString());
			resMap.clear();
			if(second != null)
			{
				resMap.put("district",second);
				resMap.put("city", frist);
				resMap.put("province", districtId);
			}
			else
			{
				resMap.put("city", frist);
				resMap.put("province", districtId);
			}
		}
		
		return resMap;
	}
	
}
