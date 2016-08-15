package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdUserMessage;
import com.tiandu.custom.search.TdUserMessageSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdUserMessageService {

	public int insert(TdUserMessage u);
	public TdUserMessage findOne(Integer id);
	public List<TdUserMessage> findBySearchCriteria(TdUserMessageSearchCriteria sc);
	public Integer save(TdUserMessage tdUserMessage);
	public Integer delete(Integer id);
	public Integer countByCriteria(TdUserMessageSearchCriteria sc);
}
