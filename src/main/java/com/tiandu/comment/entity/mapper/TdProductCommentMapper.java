package com.tiandu.comment.entity.mapper;

import java.util.List;

import com.tiandu.comment.entity.TdProductComment;
import com.tiandu.comment.search.TdProductCommentCrateria;

public interface TdProductCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TdProductComment record);

    int insertSelective(TdProductComment record);

    TdProductComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TdProductComment record);

    int updateByPrimaryKeyWithBLOBs(TdProductComment record);

    int updateByPrimaryKey(TdProductComment record);
    
    
    Integer countByCriteria(TdProductCommentCrateria sc);
    List<TdProductComment> findBySearchCriteria(TdProductCommentCrateria sc);
    
    Integer deleteByProductId(Integer proId);
}