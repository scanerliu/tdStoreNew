package com.tiandu.district.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.entity.mapper.TdDistrictMapper;
import com.tiandu.district.search.TdDistrictSearchCriteria;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.menu.entity.TdMenu;

/**
 * 
 * @author L. Gao
 *
 */
@Service("tdDistrictService")
public class TdDistrictServiceImpl implements TdDistrictService {

	@Autowired
	TdDistrictMapper districtMapper;

	public int insert(TdDistrict u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return districtMapper.insert(u);
	}

	public TdDistrict findOne(Integer id) {
		return districtMapper.selectByPrimaryKey(id);
	}

	public List<TdDistrict> findBySearchCriteria(TdDistrictSearchCriteria sc) {
		return districtMapper.findBySearchCriteria(sc);
	}
	
	/**
	 * 获取上级地区属于特定地区的所有地区
	 * @param upid 上级地区id
	 * @return
	 */
	public List<TdDistrict> getDistrictByUpid(Integer upid) {
		TdDistrictSearchCriteria sc = new TdDistrictSearchCriteria();
		sc.setUpid(upid);
		sc.setFlag(false);
		List<TdDistrict> districtList = districtMapper.findBySearchCriteria(sc);
		return districtList;
	}

	@Override
	public Integer save(TdDistrict district) {
		if(null!=district){
			if(null!=district.getId()){//更新
				return districtMapper.updateByPrimaryKey(district);
			}else{
				return districtMapper.insert(district);
			}
		}
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		return districtMapper.deleteByPrimaryKey(id);
	}

}
