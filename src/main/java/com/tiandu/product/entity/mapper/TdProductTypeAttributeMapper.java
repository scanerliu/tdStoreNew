package com.tiandu.product.entity.mapper;

import com.tiandu.product.entity.TdProductTypeAttribute;

public interface TdProductTypeAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductTypeAttribute record);

    int insertSelective(TdProductTypeAttribute record);

    TdProductTypeAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductTypeAttribute record);

    int updateByPrimaryKey(TdProductTypeAttribute record);
}