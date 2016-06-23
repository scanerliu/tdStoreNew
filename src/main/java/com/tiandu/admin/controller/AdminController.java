package com.tiandu.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.custom.vo.LoginForm;
import com.tiandu.menu.entity.TdMenu;
import com.tiandu.menu.search.TdMenuSearchCriteria;
import com.tiandu.menu.service.TdMenuService;

/** 
 * @author liuxinbing
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdMenuService tdMenuService;
	
	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    //获取主菜单
		TdMenuSearchCriteria sc = new TdMenuSearchCriteria();
		sc.setParentId(0);
		sc.setFlag(false);
		List<TdMenu> mainList = tdMenuService.findByTdMenuSearchCriteria(sc);
	    modelMap.addAttribute("mainList", mainList) ;
	    return "/admin/index";
	}
	@RequestMapping("/index")
	public String index1(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		//获取主菜单
		TdMenuSearchCriteria sc = new TdMenuSearchCriteria();
		sc.setParentId(0);
		sc.setFlag(false);
		List<TdMenu> mainList = tdMenuService.findByTdMenuSearchCriteria(sc);
	    modelMap.addAttribute("mainList", mainList) ;
	    
		return "/admin/index";
	}
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/unauthorized";
	}
	@RequestMapping("/login")
	public String login(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("loginForm", loginForm);
		return "/admin/login";
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> logining(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=loginForm && StringUtils.isNotEmpty(loginForm.getUsername())&&StringUtils.isNotEmpty(loginForm.getPassword())){
			TdUser cuser = new TdUser();
			cuser.setUname(loginForm.getUsername());
			cuser.setUpassword(WebUtils.generatePassword(loginForm.getUsername(), loginForm.getPassword()));
			Subject user = SecurityUtils.getSubject();
		    UsernamePasswordToken token = new UsernamePasswordToken(cuser.getUname(),cuser.getUpassword());
	//	    token.setRememberMe(true);
		    try {
		      user.login(token);
		      res.put("code", "1");
		      return res;
		    }catch (AuthenticationException e) {
		    	logger.error("登录失败错误信息:"+e);
		    	token.clear();
		    	res.put("code", "0");
		    	return res;
		    }
		}else{
			res.put("code", "0");
	    	return res;
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return "redirect:/admin/login";
	}
	
	@RequestMapping("/loadmenu")
	public String loadmenu(Integer parentId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		if(null!=parentId){
			List<TdMenu> menuList = tdMenuService.getMenuTreeByParentId(parentId);
		    modelMap.addAttribute("menuList", menuList) ;
		}
		return "/admin/menu";
	}
}
