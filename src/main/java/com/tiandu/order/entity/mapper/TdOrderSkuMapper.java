package com.tiandu.order.entity.mapper;

import java.util.List;

import com.tiandu.order.entity.TdOrderSku;

public interface TdOrderSkuMapper {
    int deleteByPrimaryKey(Integer orderSkuId);

    int insert(TdOrderSku record);

    int insertSelective(TdOrderSku record);

    TdOrderSku selectByPrimaryKey(Integer orderSkuId);

    int updateByPrimaryKeySelective(TdOrderSku record);

    int updateByPrimaryKey(TdOrderSku record);
    
    public List<TdOrderSku> findByOrderId(Integer orderId);
}