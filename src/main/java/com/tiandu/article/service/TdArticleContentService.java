package com.tiandu.article.service;

import java.util.List;

import com.tiandu.article.entity.TdArticleContent;
import com.tiandu.article.search.TdArticleContentSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdArticleContentService {

	public int insert(TdArticleContent u);
	public TdArticleContent findOne(Integer id);
	public List<TdArticleContent> findBySearchCriteria(TdArticleContentSearchCriteria sc);
	public Integer save(TdArticleContent tdArticleContent, boolean isUpdate);
	public Integer delete(Integer id);
}
