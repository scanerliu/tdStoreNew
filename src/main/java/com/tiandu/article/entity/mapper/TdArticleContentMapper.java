package com.tiandu.article.entity.mapper;

import java.util.List;

import com.tiandu.article.entity.TdArticleContent;
import com.tiandu.article.search.TdArticleContentSearchCriteria;

public interface TdArticleContentMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(TdArticleContent record);

    int insertSelective(TdArticleContent record);

    TdArticleContent selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(TdArticleContent record);

    int updateByPrimaryKeyWithBLOBs(TdArticleContent record);
    
    Integer countByCriteria(TdArticleContentSearchCriteria sc);
    public List<TdArticleContent> findBySearchCriteria(TdArticleContentSearchCriteria sc);
}