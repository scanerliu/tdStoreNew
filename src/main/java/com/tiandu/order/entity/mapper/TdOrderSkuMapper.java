package com.tiandu.order.entity.mapper;

import java.util.List;

import com.tiandu.order.entity.TdOrderSku;
import com.tiandu.order.search.TdOrderSkuSearchCriteria;
import com.tiandu.report.SaleProductReportEntity;

public interface TdOrderSkuMapper {
    int deleteByPrimaryKey(Integer orderSkuId);

    int insert(TdOrderSku record);

    int insertSelective(TdOrderSku record);

    TdOrderSku selectByPrimaryKey(Integer orderSkuId);

    int updateByPrimaryKeySelective(TdOrderSku record);

    int updateByPrimaryKey(TdOrderSku record);
    
    public List<TdOrderSku> findByOrderId(Integer orderId);
    
    public Integer countByCriteria(TdOrderSkuSearchCriteria sc);
    public List<TdOrderSku> findBySearchCriteria(TdOrderSkuSearchCriteria sc);
    
    public Integer countGroupByCriteria(TdOrderSkuSearchCriteria sc);
    public List<SaleProductReportEntity> findGroupBySearchCriteria(TdOrderSkuSearchCriteria sc);
}