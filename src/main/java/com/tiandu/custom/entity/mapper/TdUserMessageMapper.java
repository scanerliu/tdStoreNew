package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.entity.TdUserMessage;
import com.tiandu.custom.search.TdAgentSearchCriteria;
import com.tiandu.custom.search.TdUserMessageSearchCriteria;

public interface TdUserMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdUserMessage record);

    int insertSelective(TdUserMessage record);

    TdUserMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdUserMessage record);

    int updateByPrimaryKeyWithBLOBs(TdUserMessage record);

    int updateByPrimaryKey(TdUserMessage record);
    
    Integer countByCriteria(TdUserMessageSearchCriteria sc);
    public List<TdUserMessage> findBySearchCriteria(TdUserMessageSearchCriteria sc);
}