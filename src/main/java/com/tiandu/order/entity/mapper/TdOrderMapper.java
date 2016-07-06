package com.tiandu.order.entity.mapper;

import java.util.List;

import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.search.TdOrderSearchCriteria;

public interface TdOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(TdOrder record);

    int insertSelective(TdOrder record);

    TdOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(TdOrder record);

    int updateByPrimaryKey(TdOrder record);
    
    public List<TdOrder> findBySearchCriteria(TdOrderSearchCriteria sc);
    public Integer countBySearchCriteria(TdOrderSearchCriteria sc);
    public TdOrder findDetail(Integer orderId);
}