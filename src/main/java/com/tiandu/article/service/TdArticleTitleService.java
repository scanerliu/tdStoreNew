package com.tiandu.article.service;

import java.util.List;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.entity.TdArticleTitle;
import com.tiandu.article.search.TdArticleTitleSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdArticleTitleService {

	public int insert(TdArticleTitle u);
	public TdArticleTitle findOne(Integer id);
	public List<TdArticleTitle> findBySearchCriteria(TdArticleTitleSearchCriteria sc);
	public Integer save(TdArticleTitle tdArticleTitle);
	public Integer delete(Integer id);
}
