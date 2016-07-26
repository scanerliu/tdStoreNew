package com.tiandu.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.custom.service.TdRoleService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.menu.entity.TdMenu;
import com.tiandu.menu.search.TdMenuSearchCriteria;
import com.tiandu.menu.service.TdMenuService;

@Controller
@RequestMapping("/admin/role")
public class RoleManageController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdMenuService tdMenuService;
	
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
		sc.setGetUpdateUser(true);
		List<TdRole> roleList = tdRoleService.findBySearchCriteria(sc);
	    modelMap.addAttribute("roleList", roleList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/role/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer roleId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdRole role = null;
		if(null!=roleId){
			role = tdRoleService.findOne(roleId);		    
		}
		if(null==role){
			role = new TdRole();
		}
		modelMap.addAttribute("role", role);
		return "/admin/role/roleform";
	}
	
	@RequestMapping("/permissions")
	public String permissions(Integer roleId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdRole role = null;
		if(null!=roleId){
			role = tdRoleService.findOne(roleId);		    
		}
		if(null==role){
			modelMap.addAttribute("errmsg", "未找到相关角色，请重新操作！");
			return "/admin/error";
		}
		TdRole rolep = tdRoleService.findOneWithPermiisions(roleId);
		if(null!=rolep){
			role.setMenuSet(rolep.getMenuSet());
		}
		//获取菜单目录树
		List<TdMenu> menuList = tdMenuService.getMenuTreeAll();
		//jsonTree转换
		String jsonTree = tdMenuService.getGsonTree(role,menuList);
		
		modelMap.addAttribute("role", role);
		modelMap.addAttribute("menuList", menuList);
		modelMap.addAttribute("jsonTree", jsonTree);
		return "/admin/role/permissionform";
	}
	
	@RequestMapping(value="/savepermission", method = RequestMethod.POST)
	@ResponseBody
	public void savepermission(TdRole role, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=role){
		    try {
		      tdRoleService.saveRolePermission(role);
		      res.put("code", "1");
		      WebUtils.responseJson(response, res);
		    }catch (Exception e) {
		    	logger.error("角色保存失败错误信息:"+e);
		    	res.put("code", "0");
		    	WebUtils.responseJson(response, res);
		    }
		}else{
			res.put("code", "0");
			WebUtils.responseJson(response, res);
		}
	}
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public void save(TdRole role, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=role){
			try {
				Date now = new Date();
				TdUser currManager = this.getCurrentUser();
				role.setUpdateBy(currManager.getUid());
				role.setUpdateTime(now);
				tdRoleService.save(role);
				res.put("code", "1");
				WebUtils.responseJson(response, res);
			}catch (Exception e) {
				logger.error("角色保存失败错误信息:"+e);
				res.put("code", "0");
				WebUtils.responseJson(response, res);
			}
		}else{
			res.put("code", "0");
			WebUtils.responseJson(response, res);
		}
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=id){
			try {
				tdRoleService.delete(id);
				res.put("code", "1");
				WebUtils.responseJson(response, res);
			}catch (Exception e) {
				logger.error("角色删除失败错误信息:"+e);
				res.put("code", "0");
				WebUtils.responseJson(response, res);
			}
		}else{
			res.put("code", "0");
			WebUtils.responseJson(response, res);
		}
	}
	
}
