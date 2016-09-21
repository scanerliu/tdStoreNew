package com.tiandu.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.menu.entity.TdProductMenu;
import com.tiandu.menu.search.TdProductMenuSearchCriteria;
import com.tiandu.menu.service.TdProductMenuService;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.service.TdProductTypeService;

/**
 * 
 * pc菜单控制器
 *
 */
@Controller
@RequestMapping("/admin/pmenu")
public class ProductMenuController extends BaseController{
	
	@Autowired
	private TdProductMenuService tdProductMenuService;
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		  
		return "/admin/pmenu/list";
	}

	@RequestMapping("/search")
	public String search(TdProductMenuSearchCriteria sc,HttpServletResponse request, HttpServletResponse response, ModelMap map)
	{
		map.addAttribute("menulist", tdProductMenuService.findByTdProductMenuSearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/admin/pmenu/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletResponse request, HttpServletResponse response, ModelMap map)
	{
		TdProductMenu pmenu = null;
		if(null != id && id>0)
		{
			pmenu = tdProductMenuService.findOne(id);
		}
		if(null==pmenu){
			pmenu = new TdProductMenu();
		}
		map.addAttribute("pmenu", pmenu);
		//查询一级商品类型
		List<TdProductType> typeList = tdProductTypeService.findByParentId(0);
		if(null!=pmenu && null!=pmenu.getTypeList()){//移除已经选择的分类
			List<TdProductType> typerList =  tdProductMenuService.removeSeletedType(typeList, pmenu.getTypeList());
			map.addAttribute("typeList", typerList);
		}else{
			map.addAttribute("typeList", typeList);
		}
		return "/admin/pmenu/pmenuform";
	}
	
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdProductMenu pmenu, Integer[] typeid, HttpServletResponse request, HttpServletResponse response)
	{
		Map<String,String> res = new HashMap<>();
		res.put("code", "0");
		if(null != pmenu)
		{
			pmenu.setUpdateTime(new Date());
			TdUser user = getCurrentUser();
			if(user!= null )
			{
				if(null!=pmenu.getMid()){//编辑先删掉就的分类信息
					tdProductMenuService.deleteMenuTypes(pmenu.getMid());
				}
				pmenu.setUpdateBy(user.getUid());
				tdProductMenuService.save(pmenu);
				tdProductMenuService.saveMenuTypes(pmenu.getMid(),typeid);
				res.put("code", "1");
			}
		}
		return res;
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(Integer id,HttpServletResponse request, HttpServletResponse response)
	{
		Map<String,String> res = new HashMap<>();
		res.put("code", "0");
		if(null != id)
		{
			tdProductMenuService.deleteFull(id);
			res.put("code", "1");
		}
		return res;
	}
	
	
	
	
}
