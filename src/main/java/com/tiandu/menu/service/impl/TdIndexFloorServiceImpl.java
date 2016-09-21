package com.tiandu.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.menu.entity.TdIndexFloor;
import com.tiandu.menu.entity.TdIndexFloorType;
import com.tiandu.menu.entity.mapper.TdIndexFloorMapper;
import com.tiandu.menu.entity.mapper.TdIndexFloorTypeMapper;
import com.tiandu.menu.search.TdIndexFloorSearchCriteria;
import com.tiandu.menu.service.TdIndexFloorService;
import com.tiandu.product.entity.TdProductType;

@Service("tdIndexFloorService")
public class TdIndexFloorServiceImpl implements TdIndexFloorService {

	@Autowired
	TdIndexFloorMapper tdIndexFloorMapper;
	
	@Autowired
	TdIndexFloorTypeMapper tdIndexFloorTypeMapper;

	public int insert(TdIndexFloor u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return tdIndexFloorMapper.insert(u);
	}
	public int save(TdIndexFloor u) {
		if(null!=u.getFid()){
			return tdIndexFloorMapper.updateByPrimaryKey(u);
		}else{
			return tdIndexFloorMapper.insert(u);
		}
	}

	public TdIndexFloor findOne(Integer id) {
		return tdIndexFloorMapper.selectByPrimaryKey(id);
	}

	public List<TdIndexFloor> findByTdIndexFloorSearchCriteria(TdIndexFloorSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdIndexFloorMapper.countByCriteria(sc);
		}
		return tdIndexFloorMapper.findByTdIndexFloorSearchCriteria(sc);
	}
	@Override
	public int deleteFull(Integer id) {
		tdIndexFloorMapper.deleteByPrimaryKey(id);
		tdIndexFloorTypeMapper.deleteByFloorId(id);
		return 1;
	}
	
	@Override
	public void deleteFloorTypes(Integer mid) {
		tdIndexFloorTypeMapper.deleteByFloorId(mid);
	}
	@Override
	public void saveFloorTypes(Integer mid, Integer[] typeid) {
		if(null!=mid && null!=typeid&&typeid.length>0){
			int i = 0;
			for(Integer id: typeid){
				if(id!=null){
					TdIndexFloorType type = new TdIndexFloorType();
					type.setFid(mid);
					type.setTid(id);
					type.setSort(i);
					tdIndexFloorTypeMapper.insert(type);
				}
			}
		}
	}
	@Override
	public List<TdProductType> removeSeletedType(List<TdProductType> typeList, List<TdIndexFloorType> seletedTypeList) {
		List<TdProductType> tylist = new ArrayList<TdProductType>();
		if(null!=typeList && null!=seletedTypeList){
			for(TdProductType type1: typeList){
				boolean exist = false;
				for(TdIndexFloorType type2: seletedTypeList){
					if(type1.getId().equals(type2.getTid())){
						exist =true;
						break;
					}
				}
				if(!exist){
					tylist.add(type1);
				}
			}
		}
		return tylist;
	}
	
	
	
}
