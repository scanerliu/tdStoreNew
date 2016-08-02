package com.tiandu.article.service;

import java.util.List;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.search.TdArticleCategorySearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdArticleCategoryService {

	public int insert(TdArticleCategory u);
	public TdArticleCategory findOne(Integer id);
	public List<TdArticleCategory> findBySearchCriteria(TdArticleCategorySearchCriteria sc);
	public Integer save(TdArticleCategory tdArticleCategory);
	public Integer delete(Integer id);
	public List<TdArticleCategory> getArticleCategoryAll();
	// 出自己以外的所有资讯目录
	public List<TdArticleCategory> getOtherArticleCategoryAll(Integer cid);
	public Integer getChildrenCount(Integer cid);
}
