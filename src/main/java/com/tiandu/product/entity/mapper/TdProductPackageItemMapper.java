package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdProductPackageItem;
import com.tiandu.product.search.TdProductPackageItemSearchCriteria;

public interface TdProductPackageItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductPackageItem record);

    int insertSelective(TdProductPackageItem record);

    TdProductPackageItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductPackageItem record);

    int updateByPrimaryKey(TdProductPackageItem record);
    
    Integer deleteByProductId(Integer proId);
    
    public List<TdProductPackageItem> findBySearchCriteria(TdProductPackageItemSearchCriteria sc);
    public Integer countByCriteria(TdProductPackageItemSearchCriteria sc);
}