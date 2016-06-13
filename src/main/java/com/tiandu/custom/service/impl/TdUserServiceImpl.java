package com.tiandu.custom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.common.db.DBContextHolder;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.mapper.TdUserMapper;
import com.tiandu.custom.service.TdUserService;

@Service("tdUserService")
public class TdUserServiceImpl implements TdUserService {

	@Autowired
	TdUserMapper userMapper;

	public int insert(TdUser u) {
		DBContextHolder.setDbType(DBContextHolder.DB_RW);
		return userMapper.insert(u);
	}

	public TdUser findOne(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public TdUser selectByUname(String uname) {
		return userMapper.selectByUname(uname);
	}
	
	
}
