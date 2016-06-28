package com.tiandu.product.entity.mapper;

import com.tiandu.product.entity.TdProductAttributeOption;

public interface TdProductAttributeOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductAttributeOption record);

    int insertSelective(TdProductAttributeOption record);

    TdProductAttributeOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductAttributeOption record);

    int updateByPrimaryKey(TdProductAttributeOption record);
    
    Integer deleteByAttriId(Integer attrid);
}