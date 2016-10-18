package com.tiandu.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdExperienceStoreSearchCriteria;
import com.tiandu.custom.service.TdExperienceStoreService;
import com.tiandu.district.search.TdDistrictSearchCriteria;
import com.tiandu.district.service.TdDistrictService;

/**
 * 
 * @author Max
 * 附近门店
 * 创建时间：2016年7月23日 上午11:03:04
 */
@Controller
@RequestMapping("/mobile/experience")
public class MobileExperController extends BaseController{

	@Autowired
	TdExperienceStoreService tdExperienceStoreService;
	
	@Autowired
	private TdDistrictService tdDistrictService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		return "/mobile/exper/list";
	}
	
	@RequestMapping("/search")
	public String search(TdExperienceStoreSearchCriteria sc,HttpServletRequest req,ModelMap map)
	{
		TdUser user = getCurrentUser();
		
		if(null == sc.getRegionId()){
			sc.setRegionId(user.getUregionId());
		}		
		map.addAttribute("experList", tdExperienceStoreService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		
		
		return "/mobile/exper/listbody";
	}
	
	@RequestMapping("/detail")
	public String detail(Integer id,HttpServletRequest req,ModelMap map)
	{
		if(null == id){
			return "redirect:404";
		}
		
		// 系统配置
		map.addAttribute("system", getSystem());
		
		map.addAttribute("experexce", tdExperienceStoreService.findOne(id));
		
		return "/mobile/exper/detail";
	}
	
	@RequestMapping("/map")
	public String map(Integer id,HttpServletRequest req,ModelMap map)
	{
		if(null == id){
			return "redirect:404";
		}
		
		// 系统配置
		map.addAttribute("system", getSystem());
		
		map.addAttribute("experexce", tdExperienceStoreService.findOne(id));
		
		return "/mobile/exper/map";
	}
}
