package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdAgentProduct;
import com.tiandu.product.search.TdAgentProductSearchCriteria;

public interface TdAgentProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdAgentProduct record);

    int insertSelective(TdAgentProduct record);

    TdAgentProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdAgentProduct record);

    int updateByPrimaryKey(TdAgentProduct record);
    
    public List<TdAgentProduct> findBySearchCriteria(TdAgentProductSearchCriteria sc);
    public Integer countByCriteria(TdAgentProductSearchCriteria sc);
}