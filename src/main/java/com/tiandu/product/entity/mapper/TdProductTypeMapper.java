package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.search.TdProductTypeCriteria;

public interface TdProductTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductType record);

    int insertSelective(TdProductType record);

    TdProductType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductType record);

    int updateByPrimaryKey(TdProductType record);
    
    public Integer countByCriteria(TdProductTypeCriteria sc);
    public List<TdProductType> findBySearchCriteria(TdProductTypeCriteria sc);
    public List<TdProductType> findByParentId(Integer id);
    
}