package com.tiandu.product.service;

import java.util.List;

import com.tiandu.product.entity.TdProductAttachment;

public interface TdProductAttachmentService {

	List<TdProductAttachment> findByProductId(Integer proId);
	
	Integer save(TdProductAttachment e);
	
	TdProductAttachment findOne(Integer id);
	
	Integer deleteByProductId(Integer proId);
	
	Integer deleteByPrimaryKey(Integer id);
}
