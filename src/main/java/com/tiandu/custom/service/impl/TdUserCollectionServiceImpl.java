package com.tiandu.custom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.custom.entity.TdUserCollection;
import com.tiandu.custom.entity.mapper.TdUserCollectionMapper;
import com.tiandu.custom.search.TdUserCollectionCriteria;
import com.tiandu.custom.service.TdUserCollectionService;

/**
 * 
 * @author Max
 * 商品收藏
 * 
 * 创建时间：2016年7月21日 下午4:19:31
 */

@Service("tdUserCollectionService")
public class TdUserCollectionServiceImpl implements TdUserCollectionService{

	@Autowired
	private TdUserCollectionMapper tdUserCollectionMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tdUserCollectionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TdUserCollection findOne(Integer id) {
		return tdUserCollectionMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(TdUserCollection collect) {
		if(null != collect)
		{
			if(null != collect.getId()){
				return tdUserCollectionMapper.updateByPrimaryKey(collect);
			}else{
				return tdUserCollectionMapper.insert(collect);
			}
		}
		return null;
	}

	@Override
	public List<TdUserCollection> findBySearchCriteria(TdUserCollectionCriteria sc) {
		Integer count = tdUserCollectionMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return tdUserCollectionMapper.findBySearchCriteria(sc);
	}
	
	@Override
	public int getTotalPageCount(TdUserCollectionCriteria sc) {
		Integer count = tdUserCollectionMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return sc.getTotalPageCount();
	}

}
