package com.tiandu.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.search.TdUserCampaignCriteria;
import com.tiandu.custom.service.TdUserCampaignService;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.search.TdDistrictSearchCriteria;
import com.tiandu.district.service.TdDistrictService;

/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月14日 下午5:55:07
 */

@Controller
@RequestMapping("/mobile/campaign")
public class MobileCampaignController extends BaseController{
	
	@Autowired
	private TdUserCampaignService tdUserCampaignService;
	
	@Autowired
	private TdDistrictService tdDistrictService;
	
	@RequestMapping("/list")
	public String list(TdDistrictSearchCriteria sc ,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		TdDistrictSearchCriteria dsc = new TdDistrictSearchCriteria();
		dsc.setFlag(false);
		dsc.setUpid(0);
		map.addAttribute("districtList", tdDistrictService.findBySearchCriteria(dsc));
		map.addAttribute("dissc", dsc);
		
		TdUserCampaignCriteria usc = new TdUserCampaignCriteria();
		
		// 如果选择省级，查找市级
		if(null != sc.getProvinceId())
		{
			sc.setUpid(sc.getProvinceId());
			map.addAttribute("cityList", tdDistrictService.findBySearchCriteria(sc));
			
			map.addAttribute("province", tdDistrictService.findOne(sc.getProvinceId()));
			usc.setRegionId(sc.getProvinceId());
		}
		// 如果选择市级，查找区县级
		if(null != sc.getCityId())
		{
			sc.setUpid(sc.getCityId());
			map.addAttribute("regionList", tdDistrictService.findBySearchCriteria(sc));
			map.addAttribute("city",tdDistrictService.findOne(sc.getCityId()));
			
			usc.setRegionId(sc.getCityId());
		}
		
		// 选择区县级，复制代理查询地区ID参数
		if(null != sc.getRegionId())
		{
			map.addAttribute("regin",tdDistrictService.findOne(sc.getRegionId()));
			
			usc.setRegionId(sc.getRegionId());
		}
		
		usc.setOrderBy("2");
		map.addAttribute("campaignList", tdUserCampaignService.findBySearchCriteria(usc));
		
		return "/mobile/campaign/list";
	}

}
