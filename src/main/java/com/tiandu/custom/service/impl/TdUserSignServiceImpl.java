package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdUserSign;
import com.tiandu.custom.entity.mapper.TdUserSignMapper;
import com.tiandu.custom.search.TdUserSignSearchCriteria;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.custom.service.TdUserSignService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdUserSignService")
public class TdUserSignServiceImpl implements TdUserSignService {
	
	@Autowired
	TdUserSignMapper userSignMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Override
	public int insert(TdUserSign u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return userSignMapper.insert(u);
	}

	@Override
	public TdUserSign findOne(Integer id) {
		return userSignMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdUserSign> findBySearchCriteria(TdUserSignSearchCriteria sc) {
		Integer count = userSignMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdUserSign> userSignList = userSignMapper.findBySearchCriteria(sc);
		return userSignList;
	}
	
	@Override
	public Integer save(TdUserSign tdUserSign) {
		if(null!=tdUserSign){
			if(null!=tdUserSign.getId()){//更新
				return userSignMapper.updateByPrimaryKey(tdUserSign);
			}else{
				return userSignMapper.insert(tdUserSign);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return userSignMapper.deleteByPrimaryKey(id);
	}

}
