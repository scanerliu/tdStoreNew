package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdMembership;
import com.tiandu.custom.search.TdMembershipSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdMembershipService {

	public int insert(TdMembership u);
	public TdMembership findOne(Integer id);
	public List<TdMembership> findBySearchCriteria(TdMembershipSearchCriteria sc);
	public Integer save(TdMembership tdArticleTitle);
	public Integer delete(Integer id);
}
