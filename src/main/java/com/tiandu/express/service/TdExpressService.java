package com.tiandu.express.service;

import java.util.List;

import com.tiandu.express.entity.TdExpress;
import com.tiandu.express.search.TdExpressSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdExpressService {

	public int insert(TdExpress u);
	public TdExpress findOne(Integer id);
	public List<TdExpress> findBySearchCriteria(TdExpressSearchCriteria sc);
	public Integer save(TdExpress tdExpress);
	public Integer delete(Integer id);
}
