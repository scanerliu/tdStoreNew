package com.tiandu.custom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.utils.DateUtil;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.entity.mapper.TdUserAccountLogMapper;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;
import com.tiandu.custom.service.TdUserAccountLogService;

@Service("tdUserAccountLogService")
public class TdUserAccountLogServiceImpl implements TdUserAccountLogService {

	@Autowired
	TdUserAccountLogMapper tdUserAccountLogMapper;

	@Override
	public int insert(TdUserAccountLog u) {
		return tdUserAccountLogMapper.insert(u);
	}

	@Override
	public int updateByPrimaryKeySelective(TdUserAccountLog record) {
		return tdUserAccountLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TdUserAccountLog findOne(Integer id) {
		return tdUserAccountLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdUserAccountLog> findBySearchCriteria(TdUserAccountLogSearchCriteria sc) {
		Date now = new Date();
		switch(sc.getFilterType()){
			case 1://三天内的记录
				Date starttime = DateUtil.getNewDate(now, -3);
				sc.setBeginDate(starttime);
				break;
				
			case 2://一周以内的记录
				Date newstarttime = DateUtil.getNewDate(now, -7);
				sc.setBeginDate(newstarttime);
				break;
			default :
				break;			
		}
		if(sc.isFlag()){
			Integer count = tdUserAccountLogMapper.countByCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdUserAccountLogMapper.findBySearchCriteria(sc);
	}

	@Override
	public List<TdUserAccountLog> findGroupBySearchCriteria(TdUserAccountLogSearchCriteria sc) {
		if(sc.isFlag() == true){
			Integer count = tdUserAccountLogMapper.countGroupByCriteria(sc);
			sc.setTotalCount(count);
		}
		return tdUserAccountLogMapper.findGroupBySearchCriteria(sc);
	}
	
}
