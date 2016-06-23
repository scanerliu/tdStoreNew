package com.tiandu.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.search.TdAdsenseSearchCriteria;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;
import com.tiandu.article.service.TdAdsenseService;
import com.tiandu.article.service.TdAdvertisementService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;

/**
 * 
 * @author Max
 * 广告位及广告控制器
 *
 */
@Controller
@RequestMapping("/admin")
public class AdSenseController extends BaseController{
	
	@Autowired
	private TdAdsenseService tdAdsenseService;
	
	@Autowired
	private TdAdvertisementService tdAdvertService;
	
	/**
	 * @author Max
	 * 广告位
	 * 
	 */
	@RequestMapping("/advertposition/list")
	public String adList(HttpServletRequest req,HttpServletResponse resp,ModelMap map){
		  
		return "/admin/article/adsenselist";
	}

	@RequestMapping("/advertposition/search")
	public String search(TdAdsenseSearchCriteria sc,HttpServletResponse req,ModelMap map)
	{
		map.addAttribute("adsenselist", tdAdsenseService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/admin/article/adsenselistbody";
	}
	
	@RequestMapping("/advertposition/edit")
	public String adsenseEdit(Integer id,HttpServletResponse req,ModelMap map)
	{
		
		if(null != id)
		{
			map.addAttribute("adsense", tdAdsenseService.findOne(id));
		}
		
		return "/admin/article/adsensefrom";
	}
	
	
	@RequestMapping(value = "/advertposition/save",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> adsenseSave(TdAdsense tdAdsense,HttpServletRequest req,HttpServletResponse resp)
	{
		Map<String,String> res = new HashMap<>();
		res.put("code", "0");
		if(null != tdAdsense)
		{
			tdAdsense.setUpdateTime(new Date());
			TdUser user = getCurrentUser();
			if(user!= null )
			{
				tdAdsense.setUpdateBy(user.getUid());
				tdAdsenseService.save(tdAdsense);
				res.put("code", "1");
			}
		}
		return res;
	}
	
	@RequestMapping(value="/advertposition/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> adsenseDelete(Integer id,HttpServletRequest req,HttpServletResponse resp)
	{
		Map<String,String> res = new HashMap<>();
		res.put("code", "0");
		if(null != id)
		{
			tdAdsenseService.deleteByPrimaryKey(id);
			res.put("code", "1");
		}
		return res;
	}
	
	/**
	 * @author Max
	 * 广告
	 * 
	 */
	@RequestMapping(value="/advert/list")
	public String advertList(HttpServletRequest req,ModelMap map)
	{
		return "/admin/article/adlist";
	}
	
	@RequestMapping(value="/advert/search")
	public String adSearch(TdAdvertisementSearchCriteria sc,HttpServletRequest req,ModelMap map)
	{
		map.addAttribute("adlist", tdAdvertService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		return "/admin/article/adlistbody";
	}
	
}
