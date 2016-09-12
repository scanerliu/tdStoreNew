package com.tiandu.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.article.entity.TdAdvertisement;
import com.tiandu.article.entity.TdArticleTitle;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;
import com.tiandu.article.search.TdArticleTitleSearchCriteria;
import com.tiandu.article.service.TdAdvertisementService;
import com.tiandu.article.service.TdArticleTitleService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.search.TdUserCampaignCriteria;
import com.tiandu.custom.service.TdUserCampaignService;
import com.tiandu.district.search.TdDistrictSearchCriteria;
import com.tiandu.district.service.TdDistrictService;

/**
 * 股东竞选
 */

@Controller
@RequestMapping("/campaign")
public class CCampaignController extends BaseController{
	
	@Autowired
	private TdUserCampaignService tdUserCampaignService;
	
	@Autowired
	private TdDistrictService tdDistrictService;
	
	@Autowired
	private TdAdvertisementService tdAdvertisementService;
	
	@Autowired
	private TdArticleTitleService tdArticleTitleService;
	
	@RequestMapping("/list")
	public String list(TdDistrictSearchCriteria sc ,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		return "/client/campaign/list";
	}
	
	@RequestMapping("/search")
	public String search(TdUserCampaignCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setOrderBy("2");
		map.addAttribute("campaignList", tdUserCampaignService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/client/campaign/listbody";
	}
	//地区竞选广告
	@RequestMapping("/searchads")
	public String searchads(TdAdvertisementSearchCriteria sc,HttpServletRequest req,ModelMap map)
	{
		//广告
		sc.setPageSize(1);
		sc.setAdsId(1);
		List<TdAdvertisement> advertList = tdAdvertisementService.findBySearchCriteria(sc);
		map.addAttribute("advertList", advertList);
		//文章
		TdArticleTitleSearchCriteria asc = new TdArticleTitleSearchCriteria();
		asc.setCid(1);
		asc.setRegionId(sc.getRegionId());
		asc.setPageSize(4);
		List<TdArticleTitle> artList = tdArticleTitleService.findBySearchCriteria(asc);
		map.addAttribute("artList", artList);
		return "/client/campaign/ads";
	}

}
