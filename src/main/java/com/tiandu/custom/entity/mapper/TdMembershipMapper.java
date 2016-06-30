package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdMembership;
import com.tiandu.custom.search.TdMembershipSearchCriteria;

public interface TdMembershipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdMembership record);

    int insertSelective(TdMembership record);

    TdMembership selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdMembership record);

    int updateByPrimaryKey(TdMembership record);
    
    Integer countByCriteria(TdMembershipSearchCriteria sc);
    public List<TdMembership> findBySearchCriteria(TdMembershipSearchCriteria sc);
}