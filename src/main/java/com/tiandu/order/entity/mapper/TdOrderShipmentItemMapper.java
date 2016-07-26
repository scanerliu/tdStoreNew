package com.tiandu.order.entity.mapper;

import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.entity.TdOrderShipmentItem;

public interface TdOrderShipmentItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdOrderShipmentItem record);

    int insertSelective(TdOrderShipmentItem record);

    TdOrderShipmentItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdOrderShipmentItem record);

    int updateByPrimaryKey(TdOrderShipmentItem record);
    
    int insertOrderShipmentItems(TdOrderShipment shipment);
}