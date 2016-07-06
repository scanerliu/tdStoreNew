package com.tiandu.article.entity.mapper;

import java.util.List;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.search.TdArticleCategorySearchCriteria;

public interface TdArticleCategoryMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(TdArticleCategory record);

    int insertSelective(TdArticleCategory record);

    TdArticleCategory selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(TdArticleCategory record);

    int updateByPrimaryKey(TdArticleCategory record);
    
    Integer countByCriteria(TdArticleCategorySearchCriteria sc);
    public List<TdArticleCategory> findBySearchCriteria(TdArticleCategorySearchCriteria sc);
    
}