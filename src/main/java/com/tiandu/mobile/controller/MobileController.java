package com.tiandu.mobile.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.vo.LoginForm;

/** 
 * @author liuxinbing
 */
@Controller
@RequestMapping("/mobile")
public class MobileController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    return "/mobile/index";
	}
	@RequestMapping("/index")
	public String index1(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/mobile/index";
	}
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/unauthorized";
	}
	@RequestMapping("/login")
	public String login(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("loginForm", loginForm);
		return "/mobile/login";
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String logining(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
		if(null!=loginForm && StringUtils.isNotEmpty(loginForm.getUsername())&&StringUtils.isNotEmpty(loginForm.getPassword())){
			TdUser cuser = new TdUser();
			cuser.setUname(loginForm.getUsername());
			cuser.setUpassword(WebUtils.generatePassword(loginForm.getUsername(), loginForm.getPassword()));
			Subject user = SecurityUtils.getSubject();
		    UsernamePasswordToken token = new UsernamePasswordToken(cuser.getUname(),cuser.getUpassword());
		    token.setRememberMe(true);
		    try {
		      user.login(token);
		      Date now = new Date();
		      String hostip = request.getRemoteHost();
		      //记录登陆时间 host ip
		      TdUser muser = tdUserService.selectByUname(cuser.getUname());
		      if(null!=muser && null!=muser.getUid()){
			      TdUser upuser = new TdUser();
			      upuser.setUid(muser.getUid());
			      upuser.setLastLoginIp(hostip);
			      upuser.setLastLoginTime(now);
			      tdUserService.updateByPrimaryKeySelective(upuser);
		      }
		      return "redirect:/mobile/index";
		    }catch (AuthenticationException e) {
		    	logger.error("登录失败错误信息:"+e);
		    	token.clear();
		    	return "redirect:/mobile/login";
		    }
		}else{
			
			return "redirect:/mobile/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return "redirect:/mobile/login";
	}
	
}
