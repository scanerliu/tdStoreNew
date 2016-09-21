package com.tiandu.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.menu.entity.TdProductMenu;
import com.tiandu.menu.entity.TdProductMenuType;
import com.tiandu.menu.entity.mapper.TdProductMenuMapper;
import com.tiandu.menu.entity.mapper.TdProductMenuTypeMapper;
import com.tiandu.menu.search.TdProductMenuSearchCriteria;
import com.tiandu.menu.service.TdProductMenuService;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.search.TdProductTypeCriteria;
import com.tiandu.product.service.TdProductTypeService;

@Service("tdProductMenuService")
public class TdProductMenuServiceImpl implements TdProductMenuService {

	@Autowired
	TdProductMenuMapper tdProductMenuMapper;
	
	@Autowired
	TdProductMenuTypeMapper tdProductMenuTypeMapper;
	
	@Autowired
	private TdProductTypeService tdProductTypeService;

	public int insert(TdProductMenu u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return tdProductMenuMapper.insert(u);
	}
	public int save(TdProductMenu u) {
		if(null!=u.getMid()){
			return tdProductMenuMapper.updateByPrimaryKey(u);
		}else{
			return tdProductMenuMapper.insert(u);
		}
	}

	public TdProductMenu findOne(Integer id) {
		return tdProductMenuMapper.selectByPrimaryKey(id);
	}

	public List<TdProductMenu> findByTdProductMenuSearchCriteria(TdProductMenuSearchCriteria sc) {
		if(sc.isFlag()){
			Integer count = tdProductMenuMapper.countByCriteria(sc);
		}
		return tdProductMenuMapper.findByTdProductMenuSearchCriteria(sc);
	}
	@Override
	public int deleteFull(Integer id) {
		tdProductMenuMapper.deleteByPrimaryKey(id);
		tdProductMenuTypeMapper.deleteByMenuId(id);
		return 1;
	}
	
	@Override
	public void deleteMenuTypes(Integer mid) {
		tdProductMenuTypeMapper.deleteByMenuId(mid);
	}
	@Override
	public void saveMenuTypes(Integer mid, Integer[] typeid) {
		if(null!=mid && null!=typeid&&typeid.length>0){
			int i = 0;
			for(Integer id: typeid){
				if(id!=null){
					TdProductMenuType menutype = new TdProductMenuType();
					menutype.setMid(mid);
					menutype.setTid(id);
					menutype.setSort(i);
					tdProductMenuTypeMapper.insert(menutype);
				}
			}
		}
	}
	@Override
	public List<TdProductType> removeSeletedType(List<TdProductType> typeList, List<TdProductMenuType> seletedTypeList) {
		List<TdProductType> tylist = new ArrayList<TdProductType>();
		if(null!=typeList && null!=seletedTypeList){
			for(TdProductType type1: typeList){
				boolean exist = false;
				for(TdProductMenuType type2: seletedTypeList){
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
	
	@Override
	public List<TdProductMenu> findMenuTree(TdProductMenuSearchCriteria sc) {
		List<TdProductMenu> menuList = tdProductMenuMapper.findByTdProductMenuSearchCriteria(sc);
		if(null!=menuList){
			for(TdProductMenu menu: menuList){
				if(null!=menu && null!=menu.getTypeList()){
					for(TdProductMenuType menutype : menu.getTypeList()){
						if(null!=menutype&&null!=menutype.getProductType()){
							List<TdProductType> typeList = tdProductTypeService.findTypeTreeByParentId(menutype.getTid());
							menutype.getProductType().setSubList(typeList);
						}
					}
				}
			}
		}
		return menuList;
	}
	
	
	
}
