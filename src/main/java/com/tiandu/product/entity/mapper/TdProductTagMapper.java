package com.tiandu.product.entity.mapper;

import com.tiandu.product.entity.TdProductTag;

public interface TdProductTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductTag record);

    int insertSelective(TdProductTag record);

    TdProductTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductTag record);

    int updateByPrimaryKey(TdProductTag record);
}