package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdUserAddress;
import com.tiandu.custom.search.TdUserAddressCriteria;

public interface TdUserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserAddress record);

    int insertSelective(TdUserAddress record);

    TdUserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserAddress record);

    int updateByPrimaryKey(TdUserAddress record);
    
    List<TdUserAddress> findBySearchCriteria(TdUserAddressCriteria sc);
}