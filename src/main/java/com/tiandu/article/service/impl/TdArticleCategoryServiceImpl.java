package com.tiandu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.entity.mapper.TdArticleCategoryMapper;
import com.tiandu.article.search.TdArticleCategorySearchCriteria;
import com.tiandu.article.service.TdArticleCategoryService;
import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdArticleCategoryService")
public class TdArticleCategoryServiceImpl implements TdArticleCategoryService {
	
	@Autowired
	TdArticleCategoryMapper articleCategoryMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Override
	public int insert(TdArticleCategory u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return articleCategoryMapper.insert(u);
	}

	@Override
	public TdArticleCategory findOne(Integer id) {
		return articleCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdArticleCategory> findBySearchCriteria(TdArticleCategorySearchCriteria sc) {
		Integer count = articleCategoryMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdArticleCategory> articleCategoryList = articleCategoryMapper.findBySearchCriteria(sc);
		for(TdArticleCategory tac: articleCategoryList){
			if(tac.getParentId() != null){
				tac.setParent(this.findOne(tac.getParentId()));
			}
			if(tac.getUpdateBy() != null){
				tac.setUpdatePerson(tdUserService.findOne(tac.getUpdateBy()));
			}
		}
		return articleCategoryList;
	}

	@Override
	public Integer save(TdArticleCategory tdArticleCategory) {
		if(null!=tdArticleCategory){
			if(null!=tdArticleCategory.getCid()){//更新
				return articleCategoryMapper.updateByPrimaryKey(tdArticleCategory);
			}else{
				return articleCategoryMapper.insert(tdArticleCategory);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return articleCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TdArticleCategory> getArticleCategoryAll() {
		TdArticleCategorySearchCriteria sc = new TdArticleCategorySearchCriteria();
		sc.setFlag(false);
		sc.setStatus(Byte.valueOf("1"));
		return findBySearchCriteria(sc);
	}

	@Override
	public List<TdArticleCategory> getOtherArticleCategoryAll(Integer cid) {
		TdArticleCategorySearchCriteria sc = new TdArticleCategorySearchCriteria();
		sc.setFlag(false);
		sc.setCid(cid);
		sc.setStatus(Byte.valueOf("1"));
		return findBySearchCriteria(sc);
	}

	@Override
	public Integer getChildrenCount(Integer cid) {
		TdArticleCategorySearchCriteria sc = new TdArticleCategorySearchCriteria();
		sc.setFlag(false);
		sc.setParentId(cid);
		return articleCategoryMapper.countByCriteria(sc);
	}

}
