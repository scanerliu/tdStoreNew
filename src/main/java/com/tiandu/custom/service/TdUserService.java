package com.tiandu.custom.service;

import com.tiandu.custom.entity.TdUser;

public interface TdUserService {

	public int insert(TdUser u);
	public TdUser findOne(Integer id);
	public TdUser selectByUname(String uname);
}
