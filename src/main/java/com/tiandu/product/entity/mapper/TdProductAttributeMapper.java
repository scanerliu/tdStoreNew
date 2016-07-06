package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdProductAttribute;
import com.tiandu.product.search.TdProductAttributeCriteria;

public interface TdProductAttributeMapper {
    int deleteByPrimaryKey(Integer attriId);

    int insert(TdProductAttribute record);

    int insertSelective(TdProductAttribute record);

    TdProductAttribute selectByPrimaryKey(Integer attriId);

    int updateByPrimaryKeySelective(TdProductAttribute record);

    int updateByPrimaryKey(TdProductAttribute record);
    
    public Integer countByCriteria(TdProductAttributeCriteria sc);
	public List<TdProductAttribute> findBySearchCriteria(TdProductAttributeCriteria sc);
}