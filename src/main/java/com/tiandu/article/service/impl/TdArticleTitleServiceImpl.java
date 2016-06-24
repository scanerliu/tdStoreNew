package com.tiandu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.entity.TdArticleTitle;
import com.tiandu.article.entity.mapper.TdArticleTitleMapper;
import com.tiandu.article.search.TdArticleCategorySearchCriteria;
import com.tiandu.article.search.TdArticleTitleSearchCriteria;
import com.tiandu.article.service.TdArticleCategoryService;
import com.tiandu.article.service.TdArticleTitleService;
import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("TdArticleTitleService")
public class TdArticleTitleServiceImpl implements TdArticleTitleService {
	
	@Autowired
	TdArticleTitleMapper articleTitleMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdArticleCategoryService tdArticleCategoryService;
	
	@Override
	public int insert(TdArticleTitle u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return articleTitleMapper.insert(u);
	}

	@Override
	public TdArticleTitle findOne(Integer id) {
		return articleTitleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdArticleTitle> findBySearchCriteria(TdArticleTitleSearchCriteria sc) {
		Integer count = articleTitleMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdArticleTitle> articleTitleList = articleTitleMapper.findBySearchCriteria(sc);
		for(TdArticleTitle tat: articleTitleList){
			if(tat.getCid() != null){
				TdArticleCategory articleCategory = tdArticleCategoryService.findOne(tat.getCid());
				tat.setTdArticleCategory(articleCategory);
			}
			if(tat.getUpdateBy() != null){
				tat.setUpdatePerson(tdUserService.findOne(tat.getUpdateBy()));
			}
		}
		return articleTitleList;
	}
	
	@Override
	public Integer save(TdArticleTitle tdArticleTitle) {
		if(null!=tdArticleTitle){
			if(null!=tdArticleTitle.getAid()){//更新
				return articleTitleMapper.updateByPrimaryKey(tdArticleTitle);
			}else{
				return articleTitleMapper.insert(tdArticleTitle);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return articleTitleMapper.deleteByPrimaryKey(id);
	}

}
