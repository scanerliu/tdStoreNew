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
import com.tiandu.menu.entity.TdIndexFloor;
import com.tiandu.menu.search.TdIndexFloorSearchCriteria;
import com.tiandu.menu.service.TdIndexFloorService;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.service.TdProductTypeService;

/**
 * 
 * pc首页楼层控制器
 *
 */
@Controller
@RequestMapping("/admin/pfloor")
public class IndexFloorController extends BaseController{
	
	@Autowired
	private TdIndexFloorService tdIndexFloorService;
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		  
		return "/admin/pfloor/list";
	}

	@RequestMapping("/search")
	public String search(TdIndexFloorSearchCriteria sc,HttpServletResponse request, HttpServletResponse response, ModelMap map)
	{
		map.addAttribute("floorlist", tdIndexFloorService.findByTdIndexFloorSearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/admin/pfloor/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletResponse request, HttpServletResponse response, ModelMap map)
	{
		TdIndexFloor floor = null;
		if(null != id && id>0)
		{
			floor = tdIndexFloorService.findOne(id);
		}
		if(null==floor){
			floor = new TdIndexFloor();
		}
		map.addAttribute("pfloor", floor);
		//查询一级商品类型
		/*List<TdProductType> typeList = tdProductTypeService.findByParentId(0);
		if(null!=floor && null!=floor.getTypeList()){//移除已经选择的分类
			List<TdProductType> typerList =  tdIndexFloorService.removeSeletedType(typeList, floor.getTypeList());
			map.addAttribute("typeList", typerList);
		}else{
			map.addAttribute("typeList", typeList);
		}*/
		return "/admin/pfloor/pfloorform";
	}
	
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdIndexFloor pmenu, Integer[] typeid, HttpServletResponse request, HttpServletResponse response)
	{
		Map<String,String> res = new HashMap<>();
		res.put("code", "0");
		if(null != pmenu)
		{
			pmenu.setUpdateTime(new Date());
			TdUser user = getCurrentUser();
			if(user!= null )
			{
				if(null!=pmenu.getFid()){//编辑先删掉就的分类信息
					tdIndexFloorService.deleteFloorTypes(pmenu.getFid());
				}
				pmenu.setUpdateBy(user.getUid());
				tdIndexFloorService.save(pmenu);
				tdIndexFloorService.saveFloorTypes(pmenu.getFid(),typeid);
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
			tdIndexFloorService.deleteFull(id);
			res.put("code", "1");
		}
		return res;
	}
	
	
	
	
}
