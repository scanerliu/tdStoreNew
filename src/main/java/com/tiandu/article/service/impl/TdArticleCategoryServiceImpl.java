package com.tiandu.article.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.entity.mapper.TdArticleCategoryMapper;
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
@Service("tdArticleCategoryService")
public class TdArticleCategoryServiceImpl implements TdArticleCategoryService {
	
	@Autowired
	TdArticleCategoryMapper articleCategoryMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdArticleTitleService tdArticleService;
	
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

	@Override
	public List<TdArticleCategory> getFooterArticleCategory() {
		List<TdArticleCategory> articleList = new ArrayList<>();
		
		TdArticleCategorySearchCriteria sc = new TdArticleCategorySearchCriteria();
		sc.setName("帮助中心");
		sc.setFlag(false);
		
		// 查找帮助中心
		List<TdArticleCategory> articleCatelist = this.findBySearchCriteria(sc);
		if(null != articleCatelist && articleCatelist.size() > 0){
			
			// 查找帮助中心下级分类
			TdArticleCategorySearchCriteria subsc = new TdArticleCategorySearchCriteria();
			subsc.setParentId(articleCatelist.get(0).getCid());
			subsc.setFlag(false);
			articleList = this.findBySearchCriteria(subsc);
			
			if(null != articleList && articleList.size() > 0){
				for (TdArticleCategory tdArticleCategory : articleList) {
					TdArticleTitleSearchCriteria asc =new TdArticleTitleSearchCriteria();
					asc.setCid(tdArticleCategory.getCid());
					asc.setFlag(false);
					asc.setStatus((byte)1);
					tdArticleCategory.setArticleList(tdArticleService.findBySearchCriteria(asc));
				}
			}
			
		}
		return articleList;
	}

}
