package com.tiandu.express.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.express.entity.TdExpress;
import com.tiandu.express.entity.mapper.TdExpressMapper;
import com.tiandu.express.search.TdExpressSearchCriteria;
import com.tiandu.express.service.TdExpressService;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdExpressService")
public class TdExpressServiceImpl implements TdExpressService {
	
	@Autowired
	TdExpressMapper expressMapper;
	
	@Override
	public int insert(TdExpress u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return expressMapper.insert(u);
	}

	@Override
	public TdExpress findOne(Integer id) {
		return expressMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdExpress> findBySearchCriteria(TdExpressSearchCriteria sc) {
		Integer count = expressMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return expressMapper.findBySearchCriteria(sc);
	}

	@Override
	public Integer save(TdExpress tdExpress) {
		if(null!=tdExpress){
			if(null!=tdExpress.getId()){//更新
				return expressMapper.updateByPrimaryKey(tdExpress);
			}else{
				return expressMapper.insert(tdExpress);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return expressMapper.deleteByPrimaryKey(id);
	}

}
