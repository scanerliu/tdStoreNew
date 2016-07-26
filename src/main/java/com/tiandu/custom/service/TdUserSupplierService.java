package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdUserSupplier;
import com.tiandu.custom.search.TdUserSupplierSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdUserSupplierService {

	public int insert(TdUserSupplier u);
	public TdUserSupplier findOne(Integer id);
	public List<TdUserSupplier> findBySearchCriteria(TdUserSupplierSearchCriteria sc);
	public Integer save(TdUserSupplier tdUserSupplier);
	public Integer delete(Integer id);
}
