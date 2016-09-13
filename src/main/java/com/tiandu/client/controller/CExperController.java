package com.tiandu.client.controller;

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
		TdUser user = getCurrentUser();
		
		TdDistrictSearchCriteria dsc = new TdDistrictSearchCriteria();
		dsc.setFlag(false);
		dsc.setUpid(0);
		map.addAttribute("districtList", tdDistrictService.findBySearchCriteria(dsc));
		map.addAttribute("dissc", dsc);
		
		// 如果选择省级，查找市级
		if(null != sc.getProvinceId())
		{
			dsc.setUpid(sc.getProvinceId());
			map.addAttribute("cityList", tdDistrictService.findBySearchCriteria(dsc));
			
			map.addAttribute("province", tdDistrictService.findOne(sc.getProvinceId()));
		}
		// 如果选择市级，查找区县级
		if(null != sc.getCityId())
		{
			dsc.setUpid(sc.getCityId());
			map.addAttribute("regionList", tdDistrictService.findBySearchCriteria(dsc));
			map.addAttribute("city",tdDistrictService.findOne(sc.getCityId()));
		}
		
		if(null == sc.getRegionId()){
			sc.setRegionId(user.getUregionId());
		}else{
			map.addAttribute("regin",tdDistrictService.findOne(sc.getRegionId()));
		}
		
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
		
		map.addAttribute("experexce", tdExperienceStoreService.findOne(id));
		
		return "/client/exper/detail";
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
		
		return "/client/exper/map";
	}
}
