package com.tiandu.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.custom.service.TdRoleService;
import com.tiandu.custom.service.TdUserService;

@Controller
@RequestMapping("/admin/role")
public class RoleManageController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdRoleService tdRoleService;
	
	@RequestMapping("/list")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    //获取主菜单
	    return "/admin/role/list";
	}
	@RequestMapping("/search")
	public String search(TdRoleSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		//获取主菜单
		List<TdRole> roleList = tdRoleService.findBySearchCriteria(sc);
	    modelMap.addAttribute("roleList", roleList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/role/listbody";
	}
	
	@RequestMapping("/roleform")
	public String roleform(Integer roleId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdRole role = null;
		if(null!=roleId){
			role = tdRoleService.findOneWithPermiisions(roleId);		    
		}
		if(null==role){
			role = new TdRole();
		}
		modelMap.addAttribute("role", role);
		return "/admin/role/roleform";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdRole role, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=role){
		    try {
		      res.put("code", "1");
		      return res;
		    }catch (AuthenticationException e) {
		    	logger.error("登录失败错误信息:"+e);
		    	res.put("code", "0");
		    	return res;
		    }
		}else{
			res.put("code", "0");
	    	return res;
		}
	}
	
}
