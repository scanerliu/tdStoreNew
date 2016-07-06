package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdExperienceStore;
import com.tiandu.custom.entity.TdUserSupplier;
import com.tiandu.custom.entity.mapper.TdExperienceStoreMapper;
import com.tiandu.custom.search.TdExperienceStoreSearchCriteria;
import com.tiandu.custom.service.TdExperienceStoreService;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdExperienceStoreService")
public class TdExperienceStoreServiceImpl implements TdExperienceStoreService {
	
	@Autowired
	TdExperienceStoreMapper experienceStoreMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Override
	public int insert(TdExperienceStore u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return experienceStoreMapper.insert(u);
	}

	@Override
	public TdExperienceStore findOne(Integer id) {
		return experienceStoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdExperienceStore> findBySearchCriteria(TdExperienceStoreSearchCriteria sc) {
		if(sc.getStatus() != null && sc.getStatus() == -1){
			sc.setStatus(null);
		}
		sc.setAssociationUser(true);
		sc.setAssociationUpdatePerson(true);
		if(sc.getSearchName() != null && sc.getSearchName().trim().equals("")){
			sc.setSearchName(null);
		}
		Integer count = experienceStoreMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdExperienceStore> experienceStoreList = experienceStoreMapper.findBySearchCriteria(sc);
		return experienceStoreList;
	}
	
	
	@Override
	public Integer save(TdExperienceStore experienceStore) {
		if(null!=experienceStore){
			if(null!=experienceStore.getId()){//更新
				return experienceStoreMapper.updateByPrimaryKey(experienceStore);
			}else{
				return experienceStoreMapper.insert(experienceStore);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return experienceStoreMapper.deleteByPrimaryKey(id);
	}

}
