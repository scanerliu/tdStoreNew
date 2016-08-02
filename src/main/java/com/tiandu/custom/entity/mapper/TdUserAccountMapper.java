package com.tiandu.custom.entity.mapper;

import java.util.List;

import com.tiandu.custom.entity.TdCampaign;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.search.TdCampaignSearchCriteria;

public interface TdUserAccountMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(TdUserAccount record);

    int insertSelective(TdUserAccount record);

    TdUserAccount selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(TdUserAccount record);

    int updateByPrimaryKey(TdUserAccount record);
    
}