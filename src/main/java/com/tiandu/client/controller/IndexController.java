package com.tiandu.client.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.custom.vo.LoginForm;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private TdUserService tdUserService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 系统配置
		modelMap.addAttribute("system", getSystem());
	    return "/index";
	}
	
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/unauthorized";
	}
	@RequestMapping("/login")
	public String login(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("system", this.getSystem());
		modelMap.addAttribute("loginForm", loginForm);
		return "/client/login";
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String logining(LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
		if(null!=loginForm && StringUtils.isNotEmpty(loginForm.getUsername())&&StringUtils.isNotEmpty(loginForm.getPassword())){
			TdUser cuser = new TdUser();
			cuser.setUname(loginForm.getUsername());
			cuser.setUpassword(WebUtils.generatePassword(loginForm.getUsername(), loginForm.getPassword()));
			Subject user = SecurityUtils.getSubject();
		    UsernamePasswordToken token = new UsernamePasswordToken(cuser.getUname(),cuser.getUpassword());
		    if(null!=loginForm.getRememberMe()){
		    	token.setRememberMe(true);
		    }else{
		    	token.setRememberMe(false);
		    }
		    
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
		      return "redirect:/user/center";
		    }catch (AuthenticationException e) {
		    	logger.error("登录失败错误信息:"+e);
		    	token.clear();
		    	return "redirect:/login";
		    }
		}else{
			
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
	
	@RequestMapping("/forgetpassword")
	public String forgetpassword(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/client/forgetpassword";
	}
	@RequestMapping("/forgetpassword2")
	@ResponseBody
	public  Map<String, String> forgetpassword2(String utel, String valideCode, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<String, String>();
		res.put("code", "0");
		try{
			String changePasswordValidCode = (String) request.getSession().getAttribute("changePasswordValidCode");
			
			if(changePasswordValidCode == null || !valideCode.equals(changePasswordValidCode)){
				request.getSession().removeAttribute("changePasswordValidCode");
				res.put("msg", "验证码错误！");
				return  res;
			}
			
			if(StringUtils.isBlank(utel)){
				res.put("msg", "手机号码不能为空！");
				return  res;
			}
			
			TdUser user = tdUserService.findByUtel(utel);
			if(null == user){
				res.put("msg", "没有找到该用户！");
				return  res;
			}
			String sign = WebUtils.generatePassword(utel, utel);
			res.put("token", user.getUid().toString());
			res.put("sign", sign);
			res.put("code", "1");
			
			return res;
		} catch(Exception e){
			request.getSession().removeAttribute("changePasswordValidCode");
			e.printStackTrace();
			res.put("msg", "数据错误！");
			return  res;
		}
		
	}
	
	@RequestMapping("/repassword")
	@ResponseBody
	public Map<String, String> repassword(TdUser user, String sign, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<>();
		try{
			if(null==user || null == user.getUid()){
				res.put("msg", "重置密码失败：用户信息错误！");
				res.put("code", "0");
				return res;
			}
			
			TdUser puser = tdUserService.findOne(user.getUid());
			if(null == puser || null == puser.getUid()){
				res.put("msg", "重置密码失败：用户查找失败！");
				res.put("code", "0");
				return res;
			}
			String sign2 = WebUtils.generatePassword(puser.getUtel(), puser.getUtel());
			if(StringUtils.isBlank(sign) || !sign2.equals(sign)){
				res.put("msg", "重置密码失败：数据错误！");
				res.put("code", "0");
				return res;
			}
			
			TdUser u = new TdUser();
			u.setUname(puser.getUname());
			u.setUid(puser.getUid());
			u.setUpassword(user.getUpassword());
			u.setUpdateBy(1);
			u.setUpdateTime(new Date());
			tdUserService.saveUserPassword(u);
			
			res.put("msg", "重置密码成功。");
			res.put("code", "1");
			return res;
		} catch(Exception e){
			res.put("msg", "重置密码失败："+e.getMessage());
			res.put("code", "0");
			return res;
		}
		
	}
}
