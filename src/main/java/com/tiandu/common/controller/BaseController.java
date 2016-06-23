package com.tiandu.common.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;

public class BaseController {
	@Autowired
	protected TdUserService tdUserService;
	
	public TdUser getCurrentUser(){
		Subject user = SecurityUtils.getSubject();
		if(null!=user.getPrincipal()){
			String uname = user.getPrincipal().toString().trim();
			TdUser currentUser = tdUserService.selectByUname(uname);
			return currentUser;
		}
		return null;
	}
}
