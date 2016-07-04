package com.tiandu.system.entity.mapper;

import java.util.List;

import com.tiandu.system.entity.TdBenefit;
import com.tiandu.system.search.TdBenefitSearchCriteria;

public interface TdBenefitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdBenefit record);

    int insertSelective(TdBenefit record);

    TdBenefit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdBenefit record);

    int updateByPrimaryKey(TdBenefit record);
    
    public List<TdBenefit> findBySearchCriteria(TdBenefitSearchCriteria sc);
    public Integer countBySearchCriteria(TdBenefitSearchCriteria sc);
}