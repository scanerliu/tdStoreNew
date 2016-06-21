package com.tiandu.express.entity.mapper;

import java.util.List;

import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.express.entity.TdExpress;
import com.tiandu.express.search.TdExpressSearchCriteria;

public interface TdExpressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdExpress record);

    int insertSelective(TdExpress record);

    TdExpress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdExpress record);

    int updateByPrimaryKey(TdExpress record);
    
    public List<TdExpress> findBySearchCriteria(TdExpressSearchCriteria sc);
    public Integer countByCriteria(TdExpressSearchCriteria sc);
}