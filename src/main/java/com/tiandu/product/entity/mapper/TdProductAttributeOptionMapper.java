package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdProductAttributeOption;
import com.tiandu.product.search.TdProductAttributeOptionCriteria;

public interface TdProductAttributeOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductAttributeOption record);

    int insertSelective(TdProductAttributeOption record);

    TdProductAttributeOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductAttributeOption record);

    int updateByPrimaryKey(TdProductAttributeOption record);
    
    Integer deleteByAttriId(Integer attrid);
    
    Integer countByCriteria(TdProductAttributeOptionCriteria sc);
    List<TdProductAttributeOption> findBySearchCriteria(TdProductAttributeOptionCriteria sc);
}