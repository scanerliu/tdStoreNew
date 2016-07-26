package com.tiandu.product.entity.mapper;

import com.tiandu.product.entity.TdProductPackageItem;

public interface TdProductPackageItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductPackageItem record);

    int insertSelective(TdProductPackageItem record);

    TdProductPackageItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductPackageItem record);

    int updateByPrimaryKey(TdProductPackageItem record);
}