package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdUserSign;
import com.tiandu.custom.search.TdUserSignSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdUserSignService {

	public int insert(TdUserSign u);
	public TdUserSign findOne(Integer id);
	public List<TdUserSign> findBySearchCriteria(TdUserSignSearchCriteria sc);
	public Integer save(TdUserSign tdUserSign);
	public Integer delete(Integer id);
}
