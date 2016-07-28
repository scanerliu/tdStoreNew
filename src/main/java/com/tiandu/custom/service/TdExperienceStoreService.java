package com.tiandu.custom.service;

import java.util.List;

import com.tiandu.custom.entity.TdExperienceStore;
import com.tiandu.custom.entity.TdUserSupplier;
import com.tiandu.custom.search.TdExperienceStoreSearchCriteria;

/**
 * 
 * @author L. Gao
 *
 */
public interface TdExperienceStoreService {

	public int insert(TdExperienceStore u);
	public TdExperienceStore findOne(Integer id);
	public List<TdExperienceStore> findBySearchCriteria(TdExperienceStoreSearchCriteria sc);
	public Integer save(TdExperienceStore tdExperienceStore);
	public Integer delete(Integer id);
	/**
	 * 查找用户的体验店
	 * @param uparentId
	 * @return
	 */
	public TdExperienceStore findByUid(Integer uparentId);
}
