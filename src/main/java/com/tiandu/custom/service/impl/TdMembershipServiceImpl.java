package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdMembership;
import com.tiandu.custom.entity.mapper.TdMembershipMapper;
import com.tiandu.custom.search.TdMembershipSearchCriteria;
import com.tiandu.custom.service.TdMembershipService;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdMembershipService")
public class TdMembershipServiceImpl implements TdMembershipService {
	
	@Autowired
	TdMembershipMapper membershipMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Override
	public int insert(TdMembership u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return membershipMapper.insert(u);
	}

	@Override
	public TdMembership findOne(Integer id) {
		return membershipMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdMembership> findBySearchCriteria(TdMembershipSearchCriteria sc) {
		Integer count = membershipMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		List<TdMembership> mebershipList = membershipMapper.findBySearchCriteria(sc);
		return mebershipList;
	}
	
	@Override
	public Integer save(TdMembership tdMembership) {
		if(null!=tdMembership){
			if(null!=tdMembership.getId()){//更新
				return membershipMapper.updateByPrimaryKey(tdMembership);
			}else{
				return membershipMapper.insert(tdMembership);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return membershipMapper.deleteByPrimaryKey(id);
	}

}
