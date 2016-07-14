package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdBrand;
import com.tiandu.product.search.TdBrandSearchCriteria;

public interface TdBrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdBrand record);

    int insertSelective(TdBrand record);

    TdBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdBrand record);

    int updateByPrimaryKey(TdBrand record);
    public List<TdBrand> findBySearchCriteria(TdBrandSearchCriteria sc);
    public Integer countByCriteria(TdBrandSearchCriteria sc);
}