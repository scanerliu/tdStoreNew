package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdUserSupplier;
import com.tiandu.custom.search.TdUserSupplierSearchCriteria;

public interface TdUserSupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserSupplier record);

    int insertSelective(TdUserSupplier record);

    TdUserSupplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserSupplier record);

    int updateByPrimaryKey(TdUserSupplier record);
    
    Integer countByCriteria(TdUserSupplierSearchCriteria sc);
    public List<TdUserSupplier> findBySearchCriteria(TdUserSupplierSearchCriteria sc);
}