package com.tiandu.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.article.entity.TdArticleCategory;
import com.tiandu.article.entity.TdArticleContent;
import com.tiandu.article.entity.TdArticleTitle;
import com.tiandu.article.entity.mapper.TdArticleContentMapper;
import com.tiandu.article.search.TdArticleContentSearchCriteria;
import com.tiandu.article.service.TdArticleContentService;
import com.tiandu.article.service.TdArticleTitleService;
import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdArticleContentService")
public class TdArticleContentServiceImpl implements TdArticleContentService {
	
	@Autowired
	TdArticleContentMapper articleContentMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Autowired
	TdArticleTitleService tdArticleTitleService;
	
	@Override
	public int insert(TdArticleContent u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return articleContentMapper.insert(u);
	}

	@Override
	public TdArticleContent findOne(Integer id) {
		return articleContentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdArticleContent> findBySearchCriteria(TdArticleContentSearchCriteria sc) {
		Integer count = articleContentMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdArticleContent> articleContentList = articleContentMapper.findBySearchCriteria(sc);
		return articleContentList;
	}
	
	@Override
	public Integer save(TdArticleContent tdArticleContent) {
		if(null!=tdArticleContent){
			if(null!=tdArticleContent.getAid()){//更新
				return articleContentMapper.updateByPrimaryKeyWithBLOBs(tdArticleContent);
			}else{
				return articleContentMapper.insert(tdArticleContent);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return articleContentMapper.deleteByPrimaryKey(id);
	}

}
