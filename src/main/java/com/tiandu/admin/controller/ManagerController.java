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
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdRoleService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.menu.entity.TdMenu;
import com.tiandu.menu.search.TdMenuSearchCriteria;
import com.tiandu.menu.service.TdMenuService;

@Controller
@RequestMapping("/admin/manager")
public class ManagerController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdRoleService tdRoleService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    return "/admin/manager/list";
	}
	@RequestMapping("/search")
	public String search(TdUserSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		//获取主菜单
		List<TdUser> userList = tdUserService.findBySearchCriteria(sc);
	    modelMap.addAttribute("userList", userList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/manager/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser manager = null;
		if(null!=id){
			manager = tdUserService.findOne(id);		    
		}
		if(null==manager){
			manager = new TdUser();
		}
		modelMap.addAttribute("manager", manager);
		return "/admin/manager/managerform";
	}
	
	@RequestMapping("/roles")
	public String roles(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser manager = null;
		if(null!=id){
			manager = tdUserService.findOne(id);		    
		}
		if(null==manager){
			modelMap.addAttribute("errmsg", "未找到相关人员，请重新操作！");
			return "/admin/error";
		}
		TdUser managerp = tdUserService.findOneWithRoles(id);
		if(null!=managerp){
			manager.setRoleSet(managerp.getRoleSet());
		}
		//获取角色列表
		TdRoleSearchCriteria sc = new TdRoleSearchCriteria();
		sc.setFlag(false);
		List<TdRole> roleList = tdRoleService.findBySearchCriteria(sc);
		
		modelMap.addAttribute("manager", manager);
		modelMap.addAttribute("roleList", roleList);
		return "/admin/manager/roleform";
	}
	
	@RequestMapping(value="/saverole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saverole(TdUser mananger, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=mananger){
		    try {
		    	tdUserService.saveUserRole(mananger);
		      res.put("code", "1");
		      return res;
		    }catch (Exception e) {
		    	logger.error("角色保存失败错误信息:"+e);
		    	res.put("code", "0");
		    	return res;
		    }
		}else{
			res.put("code", "0");
	    	return res;
		}
	}
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdUser manager, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=manager){
			Date now = new Date();
			try {
				TdUser currManager = this.getCurrentUser();
				manager.setUpdateBy(currManager.getUid());
				manager.setUpdateTime(now);
				manager.setCreateTime(now);
				tdUserService.saveManager(manager);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("管理员保存失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=id){
			try {
				tdUserService.deleteUserAll(id);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("管理员删除失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	
}
