package com.tiandu.order.entity.mapper;

import java.util.List;

import com.tiandu.order.entity.TdOrderProduct;

public interface TdOrderProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdOrderProduct record);

    int insertSelective(TdOrderProduct record);

    TdOrderProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdOrderProduct record);

    int updateByPrimaryKeyWithBLOBs(TdOrderProduct record);

    int updateByPrimaryKey(TdOrderProduct record);
    
    public List<TdOrderProduct> findByOrderId(Integer orderId);
}