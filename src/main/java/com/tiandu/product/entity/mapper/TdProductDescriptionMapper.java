package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdProductDescription;
import com.tiandu.product.search.TdProductDescriptionCriteria;

public interface TdProductDescriptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductDescription record);

    int insertSelective(TdProductDescription record);

    TdProductDescription selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductDescription record);

    int updateByPrimaryKeyWithBLOBs(TdProductDescription record);

    int updateByPrimaryKey(TdProductDescription record);
    
    TdProductDescription findByProductId(TdProductDescriptionCriteria sc);
    Integer deleteByProductId(Integer proId);
    
    public List<TdProductDescription> findBySearchCriteria(TdProductDescriptionCriteria sc);
}