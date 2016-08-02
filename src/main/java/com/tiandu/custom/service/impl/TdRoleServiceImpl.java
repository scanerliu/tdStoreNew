package com.tiandu.custom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdRolePermission;
import com.tiandu.custom.entity.mapper.TdRoleMapper;
import com.tiandu.custom.entity.mapper.TdRolePermissionMapper;
import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.custom.service.TdRoleService;
import com.tiandu.menu.entity.TdMenu;

/**
 * 
 * @author liuxinbing
 *
 */
@Service("tdRoleService")
public class TdRoleServiceImpl implements TdRoleService {

	@Autowired
	TdRoleMapper roleMapper;
	@Autowired
	TdRolePermissionMapper rolePermissionMapper;

	public int insert(TdRole u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return roleMapper.insert(u);
	}

	public TdRole findOne(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public TdRole findOneWithPermiisions(Integer id) {
		return roleMapper.selectByPrimaryKeyPermiision(id);
	}

	public List<TdRole> findBySearchCriteria(TdRoleSearchCriteria sc) {
		Integer count = roleMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return roleMapper.findBySearchCriteria(sc);
	}

	public Integer save(TdRole role) {
		if(null!=role){
			if(null!=role.getId()){//更新
				return roleMapper.updateByPrimaryKey(role);
			}else{
				return roleMapper.insert(role);
			}
		}
		return null;
	}

	public Integer delete(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	public Integer saveRolePermission(TdRole role) {
		if(null!=role&&null!=role.getId()){
			rolePermissionMapper.deleteByRole(role);
			if(StringUtils.isNotEmpty(role.getMenuIds())){
				String[] menuIds =  role.getMenuIds().split(",");
				List<TdMenu> menuList = new ArrayList<TdMenu>();
				for(String id : menuIds){
					if(StringUtils.isNotEmpty(id)){
						try {
							Integer mid = Integer.valueOf(id);
							TdMenu menu = new TdMenu();
							menu.setId(mid);
							menuList.add(menu);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(menuList.size()>0){
					role.setMenuList(menuList);
					rolePermissionMapper.insertRolePermissions(role);
				}
			}
		}
		return null;
	}

	public List<TdRolePermission> findRolePermissionsByRoleId(Integer roleId) {
		return rolePermissionMapper.findRolePermissionsByRoleId(roleId);
	}

	
	
	
}
