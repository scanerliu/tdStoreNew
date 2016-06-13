package com.tiandu.client.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;

@Controller
@RequestMapping("/index")
public class IndexController {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private TdUserService tdUserService;
	
	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    TdUser user = tdUserService.findOne(1);
	    modelMap.addAttribute("user", user) ;
	    return "/index";
	}
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/unauthorized";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/login";
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String logining(String username, String password, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser cuser = new TdUser();
		cuser.setUname(username);
		cuser.setUpassword(password);
		Subject user = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken(cuser.getUname(),cuser.getUpassword());
	    token.setRememberMe(true);
	    try {
	      user.login(token);
	      return "redirect:/main";
	    }catch (AuthenticationException e) {
	      logger.error("登录失败错误信息:"+e);
	      token.clear();
	      return "redirect:/index/login";
	    }
	}
}
