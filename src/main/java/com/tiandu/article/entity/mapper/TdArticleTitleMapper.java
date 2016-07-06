package com.tiandu.article.entity.mapper;

import java.util.List;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.entity.TdArticleTitle;
import com.tiandu.article.search.TdArticleCategorySearchCriteria;
import com.tiandu.article.search.TdArticleTitleSearchCriteria;

public interface TdArticleTitleMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(TdArticleTitle record);

    int insertSelective(TdArticleTitle record);

    TdArticleTitle selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(TdArticleTitle record);

    int updateByPrimaryKey(TdArticleTitle record);
    
    Integer countByCriteria(TdArticleTitleSearchCriteria sc);
    public List<TdArticleTitle> findBySearchCriteria(TdArticleTitleSearchCriteria sc);
}