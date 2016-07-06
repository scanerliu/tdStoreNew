package com.tiandu.product.entity.mapper;

import java.util.List;

import com.tiandu.product.entity.TdProductAttachment;

public interface TdProductAttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductAttachment record);

    int insertSelective(TdProductAttachment record);

    TdProductAttachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductAttachment record);

    int updateByPrimaryKey(TdProductAttachment record);
    
    List<TdProductAttachment> findByProductId(Integer productId); 
    Integer deleteByProductId(Integer productId);
    
}