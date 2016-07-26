package com.tiandu.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.menu.entity.TdMenu;
import com.tiandu.menu.entity.mapper.TdMenuMapper;
import com.tiandu.menu.search.TdMenuSearchCriteria;
import com.tiandu.menu.service.TdMenuService;
import com.tiandu.menu.vo.TdMenuTreeJson;

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
	
	/**
	 * 获取菜单树(只获取三级目录树)
	 * @return
	 */
	public List<TdMenu> getMenuTreeAll() {
		//获取主菜单列表
		TdMenuSearchCriteria sc = new TdMenuSearchCriteria();
		sc.setParentId(0);
		sc.setFlag(false);
		List<TdMenu> mainList = this.findByTdMenuSearchCriteria(sc);
		//获取一级子菜单列表
		if(null!=mainList && mainList.size()>0){
			for(TdMenu menu : mainList){
				sc.setParentId(menu.getId());
				sc.setFlag(false);
				List<TdMenu> sublist = this.findByTdMenuSearchCriteria(sc);
				//获取二级子菜单列表
				if(null!=sublist && sublist.size()>0){
					for(TdMenu mu : sublist){
						sc.setParentId(mu.getId());
						sc.setFlag(false);
						List<TdMenu> sub2list = this.findByTdMenuSearchCriteria(sc);
						mu.setSubList(sub2list);
					}
				}
				menu.setSubList(sublist);
			}
		}
		return mainList;
	}

	/**
	 * 返回权限列表json
	 * @param role
	 * @param menuList
	 * @return
	 */
	public String getGsonTree(TdRole role, List<TdMenu> menuList) {
		List<TdMenuTreeJson> subList = new ArrayList<TdMenuTreeJson>();
		for(TdMenu menu : menuList){//一级目录
			TdMenuTreeJson tr = new  TdMenuTreeJson();
			tr.setId(menu.getId());
			tr.setText(menu.getName());
			if(role.hasPermissionId(menu.getId())){
				tr.setChecked(true);
			}
			if(null!=menu.getSubList()&&menu.getSubList().size()>0){//子目录
				tr.setState("open");
				List<TdMenuTreeJson> sub2List = new ArrayList<TdMenuTreeJson>();
				for(TdMenu menu2 : menu.getSubList()){//二级目录
					TdMenuTreeJson tr2 = new  TdMenuTreeJson();
					tr2.setId(menu2.getId());
					tr2.setText(menu2.getName());
					if(role.hasPermissionId(menu2.getId())){
						tr2.setChecked(true);
					}
					if(null!=menu2.getSubList()&&menu2.getSubList().size()>0){//子目录
						tr2.setState("open");
						List<TdMenuTreeJson> sub3List = new ArrayList<TdMenuTreeJson>();
						for(TdMenu menu3 : menu2.getSubList()){//二级目录
							TdMenuTreeJson tr3 = new  TdMenuTreeJson();
							tr3.setId(menu3.getId());
							tr3.setText(menu3.getName());
							if(role.hasPermissionId(menu3.getId())){
								tr3.setChecked(true);
							}
							sub3List.add(tr3);
						}
						tr2.setChildren(sub3List);
					}					
					sub2List.add(tr2);
				}
				tr.setChildren(sub2List);
			}
			
			subList.add(tr);
		}
		Gson gson = new Gson();
		return gson.toJson(subList);
	}

	
	
}
