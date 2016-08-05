package com.tiandu.order.entity.mapper;

import com.tiandu.order.entity.TdJointOrder;

public interface TdJointOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdJointOrder record);

    int insertSelective(TdJointOrder record);

    TdJointOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdJointOrder record);

    int updateByPrimaryKey(TdJointOrder record);
    
    TdJointOrder findByJno(String jno);
}