package com.tiandu.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.article.entity.TdAdsense;
import com.tiandu.article.entity.TdAdvertisement;
import com.tiandu.article.search.TdAdsenseSearchCriteria;
import com.tiandu.article.search.TdAdvertisementSearchCriteria;
import com.tiandu.article.service.TdAdsenseService;
import com.tiandu.article.service.TdAdvertisementService;
import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdBrancheCompany;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdBrancheCompanySearchCriteria;
import com.tiandu.custom.service.TdBrancheCompanyService;
import com.tiandu.product.entity.TdProductType;
import com.tiandu.product.search.TdProductTypeCriteria;
import com.tiandu.product.service.TdProductTypeService;

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
	
	@Autowired
	private TdBrancheCompanyService tdBrancheCompanyService;
	
	@Autowired
	private TdProductTypeService tdProductTypeService;
	
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
			// 新增
			if(null == tdAdsense.getId())
			{
				// 判断是否为同名广告位
				if(null != tdAdsenseService.findByName(tdAdsense.getName())){
					return res;
				}
			}
			
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
		TdUser currentUser = this.getCurrentUser();
		TdBrancheCompanySearchCriteria bsc = new TdBrancheCompanySearchCriteria();
		bsc.setFlag(false);
		bsc.setUid(currentUser.getUid());
		List<TdBrancheCompany> bclist = tdBrancheCompanyService.findBySearchCriteria(bsc);
		if(bclist != null && bclist.size() == 1){
			sc.setCreateBy(currentUser.getUid());
		}
		
		map.addAttribute("adlist", tdAdvertService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		
		TdAdsenseSearchCriteria criteria = new TdAdsenseSearchCriteria();
		criteria.setFlag(false);
		map.addAttribute("adsenseList", tdAdsenseService.findBySearchCriteria(criteria));
		
		return "/admin/article/adlistbody";
	}
	
	@RequestMapping(value="/advert/edit")
	public String adEdit(Integer id,HttpServletRequest req,ModelMap map)
	{
		TdAdsenseSearchCriteria sc = new TdAdsenseSearchCriteria();
		sc.setFlag(false);
		map.addAttribute("adsenseList", tdAdsenseService.findBySearchCriteria(sc));
		
		if(null != id)
		{
			map.addAttribute("ad", tdAdvertService.findOne(id));
		}
		//查询商品一级分类
		TdProductTypeCriteria tsc = new TdProductTypeCriteria();
		tsc.setParentId(0);
		tsc.setStatus(Byte.valueOf("1"));
		tsc.setOrderBy("1");
		tsc.setFlag(false);
		List<TdProductType> typeList = tdProductTypeService.findBySearchCriteria(tsc);
		map.addAttribute("typeList", typeList);
		return "/admin/article/adfrom";
	}
	
	@RequestMapping(value = "/advert/save",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> adSave(TdAdvertisement tdAdvertisement,
//			String createTime,String endTime,
			HttpServletRequest req,HttpServletResponse resp)
	{
		Map<String,String> res = new HashMap<>();
		res.put("code", "0");
		if(null != tdAdvertisement)
		{
			// 更新时间
			tdAdvertisement.setUpdateTime(new Date());
			TdUser user = getCurrentUser();
			if(user!= null )
			{
				if(null == tdAdvertisement.getCreateBy())
				{
					// 创建人
					tdAdvertisement.setCreateBy(user.getUid());
				}
				// 地区
				if(null == tdAdvertisement.getRegionId()){
					tdAdvertisement.setRegionId(user.getUregionId());
				}
				// 最后更新人
				tdAdvertisement.setUpdateBy(user.getUid());
				
				tdAdvertService.save(tdAdvertisement);
				res.put("code", "1");
			}
		}
		return res;
	}
	
	@RequestMapping(value="/advert/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> adDelete(Integer id,HttpServletRequest req,HttpServletResponse resp)
	{
		Map<String,String> res = new HashMap<>();
		res.put("code", "0");
		if(null != id)
		{
			tdAdvertService.deleteByPrimaryKey(id);
			res.put("code", "1");
		}
		return res;
	}
	
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "adId", required = false) Integer adId,
                        Model model) {
        if (null != adId) {
            model.addAttribute("tdAdvertisement", tdAdvertService.findOne(adId));
        }
    }
	
}
