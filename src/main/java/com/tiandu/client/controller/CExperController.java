package com.tiandu.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdExperienceStore;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdExperienceStoreSearchCriteria;
import com.tiandu.custom.service.TdExperienceStoreService;
import com.tiandu.district.search.TdDistrictSearchCriteria;
import com.tiandu.district.service.TdDistrictService;

/**
 * 附近门店
 */
@Controller
@RequestMapping("/experience")
public class CExperController extends BaseController{

	@Autowired
	TdExperienceStoreService tdExperienceStoreService;
	
	@Autowired
	private TdDistrictService tdDistrictService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		return "/client/exper/list";
	}
	
	@RequestMapping("/search")
	public String search(TdExperienceStoreSearchCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setStatus(Byte.valueOf("1"));	
		map.addAttribute("experList", tdExperienceStoreService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);		
		return "/client/exper/listbody";
	}
	
	@RequestMapping("/detail")
	public String detail(Integer id,HttpServletRequest req,ModelMap map)
	{
		if(null == id){
			return "redirect:404";
		}
		
		// 系统配置
		map.addAttribute("system", getSystem());
		TdExperienceStore store = tdExperienceStoreService.findOne(id);
		if(null==store){
			return "redirect:404";
		}
		map.addAttribute("experexce", store);
		
		return "/client/exper/detail";
	}
	
}
