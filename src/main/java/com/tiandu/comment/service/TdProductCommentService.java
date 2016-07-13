package com.tiandu.comment.service;

import java.util.List;

import com.tiandu.comment.entity.TdProductComment;
import com.tiandu.comment.search.TdProductCommentCrateria;

public interface TdProductCommentService {

	
	Integer deleteByPrimaryKey(Integer id);
	
	TdProductComment findOne(Integer id);
	
	Integer save(TdProductComment comment);
	
	List<TdProductComment> findBySearchCriteria(TdProductCommentCrateria sc);
	
	Integer deleteByProductId(Integer proId);
}
