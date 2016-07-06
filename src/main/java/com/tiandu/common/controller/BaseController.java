package com.tiandu.common.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.tiandu.common.configuration.DateEditor;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;

public class BaseController {
	@Autowired
	protected TdUserService tdUserService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理
	    binder.registerCustomEditor(Date.class, new DateEditor());
	}
	/**
	 * 获取当前用户信息
	 * @return
	 */
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
