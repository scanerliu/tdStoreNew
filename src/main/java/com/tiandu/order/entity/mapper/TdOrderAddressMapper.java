package com.tiandu.order.entity.mapper;

import com.tiandu.order.entity.TdOrderAddress;

public interface TdOrderAddressMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(TdOrderAddress record);

    int insertSelective(TdOrderAddress record);

    TdOrderAddress selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(TdOrderAddress record);

    int updateByPrimaryKey(TdOrderAddress record);
}