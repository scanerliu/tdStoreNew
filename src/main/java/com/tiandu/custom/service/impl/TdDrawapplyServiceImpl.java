package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.entity.TdDrawapply;
import com.tiandu.custom.entity.mapper.TdDrawapplyMapper;
import com.tiandu.custom.search.TdDrawapplySearchCriteria;
import com.tiandu.custom.service.TdDrawapplyService;
import com.tiandu.custom.service.TdUserService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdDrawapplyService")
public class TdDrawapplyServiceImpl implements TdDrawapplyService {
	
	@Autowired
	TdDrawapplyMapper drawapplyMapper;
	
	@Autowired
	TdUserService tdUserService;
	
	@Override
	public int insert(TdDrawapply u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return drawapplyMapper.insert(u);
	}

	@Override
	public TdDrawapply findOne(Integer id) {
		return drawapplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdDrawapply> findBySearchCriteria(TdDrawapplySearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = drawapplyMapper.countByCriteria(sc);
			sc.setTotalCount(count);
		}
		List<TdDrawapply> agentList = drawapplyMapper.findBySearchCriteria(sc);
		return agentList;
	}
	
	
	@Override
	public int countByCriteria(TdDrawapplySearchCriteria sc) {
		return drawapplyMapper.countByCriteria(sc);
	}

	@Override
	public Integer save(TdDrawapply TdAgent) {
		if(null!=TdAgent){
			if(null!=TdAgent.getId()){//更新
				return drawapplyMapper.updateByPrimaryKey(TdAgent);
			}else{
				return drawapplyMapper.insert(TdAgent);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return drawapplyMapper.deleteByPrimaryKey(id);
	}
	
}
