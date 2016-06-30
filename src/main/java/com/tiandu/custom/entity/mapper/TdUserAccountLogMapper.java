package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;

public interface TdUserAccountLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserAccountLog record);

    int insertSelective(TdUserAccountLog record);

    TdUserAccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserAccountLog record);

    int updateByPrimaryKey(TdUserAccountLog record);
    
    public List<TdUserAccountLog>  findBySearchCriteria(TdUserAccountLogSearchCriteria sc);
    public int countByCriteria(TdUserAccountLogSearchCriteria sc);
}