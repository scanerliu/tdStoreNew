package com.tiandu.custom.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.mapper.TdUserMapper;
import com.tiandu.custom.entity.mapper.TdUserRoleMapper;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.menu.entity.TdMenu;

@Service("tdUserService")
public class TdUserServiceImpl implements TdUserService {

	@Autowired
	TdUserMapper userMapper;
	
	@Autowired
	TdUserRoleMapper userRoleMapper;

	public int insert(TdUser u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return userMapper.insert(u);
	}
	/**
	 * 删除用户所有信息
	 * @param id
	 * @return
	 */
	@Override
	public int deleteUserAll(Integer id) {
		
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer saveManager(TdUser user) {
		if(null!=user){
			if(null!=user.getUid()){//更新
				return userMapper.updateManagerByPrimaryKey(user);
			}else{
				return userMapper.insert(user);
			}
		}
		return null;
	}
	
	@Override
	public Integer saveUserRole(TdUser user) {
		if(null!=user&&null!=user.getUid()){
			userRoleMapper.deleteByUser(user);
			if(StringUtils.isNotEmpty(user.getRoleIds())){
				String[] menuIds =  user.getRoleIds().split(",");
				Set<TdRole> roleSet = new HashSet<TdRole>();
				for(String id : menuIds){
					if(StringUtils.isNotEmpty(id)){
						try {
							Integer mid = Integer.valueOf(id);
							TdRole role = new TdRole();
							role.setId(mid);
							roleSet.add(role);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(roleSet.size()>0){
					user.setRoleSet(roleSet);
					userRoleMapper.insertUserRoles(user);
				}
			}
		}
		return null;
	}
	public TdUser findOne(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public TdUser selectByUname(String uname) {
		return userMapper.selectByUname(uname);
	}

	@Override
	public List<TdUser> findBySearchCriteria(TdUserSearchCriteria sc) {
		Integer count = userMapper.countByCriteria(sc);
		sc.setTotalCount(count);
		return userMapper.findBySearchCriteria(sc);
	}

	@Override
	public TdUser findOneWithRoles(Integer id) {
		return userMapper.selectByPrimaryKeyWithRoles(id);
	}
	
	
}
