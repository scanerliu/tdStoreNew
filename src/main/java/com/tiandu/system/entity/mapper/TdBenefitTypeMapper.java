package com.tiandu.system.entity.mapper;

import java.util.List;

import com.tiandu.system.entity.TdBenefitType;
import com.tiandu.system.search.TdBenefitTypeSearchCriteria;

public interface TdBenefitTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdBenefitType record);

    int insertSelective(TdBenefitType record);

    TdBenefitType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdBenefitType record);

    int updateByPrimaryKey(TdBenefitType record);
    
    public List<TdBenefitType> findBySearchCriteria(TdBenefitTypeSearchCriteria sc);
    public Integer countBySearchCriteria(TdBenefitTypeSearchCriteria sc);
    
    
}