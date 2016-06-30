package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdProductTypeAttribute;
import com.tiandu.product.search.TdProductTypeAttributeCriteria;

public interface TdProductTypeAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductTypeAttribute record);

    int insertSelective(TdProductTypeAttribute record);

    TdProductTypeAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductTypeAttribute record);

    int updateByPrimaryKey(TdProductTypeAttribute record);
    
    int deleteByTypeId(Integer typeId);
    
    List<TdProductTypeAttribute> findByTypeId(Integer typeId);
    
    Integer countByCriteria(TdProductTypeAttributeCriteria sc);
    List<TdProductTypeAttribute> findBySearchCriteria(TdProductTypeAttributeCriteria sc);
    
}