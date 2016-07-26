package com.tiandu.district.entity.mapper;

import java.util.List;

import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.search.TdDistrictSearchCriteria;

public interface TdDistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdDistrict record);

    int insertSelective(TdDistrict record);

    TdDistrict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdDistrict record);

    int updateByPrimaryKey(TdDistrict record);
    
    public List<TdDistrict> findBySearchCriteria(TdDistrictSearchCriteria sc);
    
}