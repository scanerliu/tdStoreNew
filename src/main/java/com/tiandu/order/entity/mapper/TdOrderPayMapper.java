package com.tiandu.order.entity.mapper;

import com.tiandu.order.entity.TdOrderPay;

public interface TdOrderPayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdOrderPay record);

    int insertSelective(TdOrderPay record);

    TdOrderPay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdOrderPay record);

    int updateByPrimaryKeyWithBLOBs(TdOrderPay record);

    int updateByPrimaryKey(TdOrderPay record);
}