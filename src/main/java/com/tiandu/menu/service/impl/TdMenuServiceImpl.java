package com.tiandu.menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.menu.entity.TdMenu;
import com.tiandu.menu.entity.mapper.TdMenuMapper;
import com.tiandu.menu.search.TdMenuSearchCriteria;
import com.tiandu.menu.service.TdMenuService;

@Service("tdMenuService")
public class TdMenuServiceImpl implements TdMenuService {

	@Autowired
	TdMenuMapper menuMapper;

	public int insert(TdMenu u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return menuMapper.insert(u);
	}

	public TdMenu findOne(Integer id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	public List<TdMenu> findByTdMenuSearchCriteria(TdMenuSearchCriteria sc) {
		return menuMapper.findByTdMenuSearchCriteria(sc);
	}

	/**
	 * 获取菜单树(只获取两级目录树)
	 * @param parentId 父菜单id
	 * @return
	 */
	public List<TdMenu> getMenuTreeByParentId(Integer parentId) {
		//获取一级子菜单列表
		TdMenuSearchCriteria sc = new TdMenuSearchCriteria();
		sc.setParentId(parentId);
		sc.setFlag(false);
		List<TdMenu> mainList = this.findByTdMenuSearchCriteria(sc);
		//获取二级子菜单列表
		if(null!=mainList && mainList.size()>0){
			for(TdMenu menu : mainList){
				sc.setParentId(menu.getId());
				sc.setFlag(false);
				List<TdMenu> sublist = this.findByTdMenuSearchCriteria(sc);
				menu.setSubList(sublist);
			}
		}
		return mainList;
	}

	
	
}
