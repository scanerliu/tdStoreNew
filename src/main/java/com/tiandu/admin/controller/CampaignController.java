package com.tiandu.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdBrancheCompany;
import com.tiandu.custom.entity.TdCampaign;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdBrancheCompanySearchCriteria;
import com.tiandu.custom.search.TdCampaignSearchCriteria;
import com.tiandu.custom.service.TdBrancheCompanyService;
import com.tiandu.custom.service.TdCampaignService;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.service.TdDistrictService;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/campaign")
public class CampaignController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdCampaignService tdCampaignService;

	@Autowired
	private TdDistrictService tdDistrictService;
	
	@Autowired
	private TdBrancheCompanyService tdBrancheCompanyService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Subject user = SecurityUtils.getSubject();
		if(user.hasRole("branch")){
			modelMap.addAttribute("branch", true);
		}
		return "/admin/campaign/campaignList";
	}

	@RequestMapping("/search")
	public String search(TdCampaignSearchCriteria sc, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		TdUser currentUser = this.getCurrentUser();
		TdBrancheCompanySearchCriteria bsc = new TdBrancheCompanySearchCriteria();
		bsc.setFlag(false);
		bsc.setUid(currentUser.getUid());
		List<TdBrancheCompany> bclist = tdBrancheCompanyService.findBySearchCriteria(bsc);
		if(bclist != null && bclist.size() == 1){
			sc.setCreateBy(currentUser.getUid());
		}
		
		List<TdCampaign> campaignList = tdCampaignService.findBySearchCriteria(sc);
		modelMap.addAttribute("campaignList", campaignList);
		modelMap.addAttribute("sc", sc);
		return "/admin/campaign/campaignListbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdCampaign tdCampaign = null;
		String isNew = "false"; // 根据此字段判断前台如何初始化地区
		if(null!=id && id != 0){
			tdCampaign = tdCampaignService.findOne(id);
			// 初始化level及id
			Integer upOneDistrictLevel = null;
			Integer upOneDistrictId = null;
			Integer upTwoDistrictLevel = null;
			Integer upTwoDistrictId = null;
			Integer currentDistrictLevel = null;
			Integer currentDistrictId = null;
			// 设置id
			currentDistrictId = tdCampaign.getRegionId();
			TdDistrict td = tdDistrictService.findOne(currentDistrictId);
			if(td.getUpid() != null && td.getUpid() != 0){
				upOneDistrictId = td.getUpid();
				if(upOneDistrictId != null && upOneDistrictId != 0){
					td = tdDistrictService.findOne(upOneDistrictId);				
					if(td != null && td.getUpid() != null && td.getUpid() != 0){
						upTwoDistrictId = td.getUpid();
					}
				}
			}
			// 设置level
			if(upTwoDistrictId != null){
				currentDistrictLevel = 3;
				upOneDistrictLevel = 2;
				upTwoDistrictLevel = 1;
				modelMap.addAttribute("levelCount", 3);
				// 获取各个level的地区列表
				//List<TdDistrict> upTwoDistrictLevelList = tdDistrictService.getDistrictByUpid(0);
				//List<TdDistrict> upOneDistrictLevelList = tdDistrictService.getDistrictByUpid(upTwoDistrictId);
				//List<TdDistrict> currentDistrictLevelList = tdDistrictService.getDistrictByUpid(upOneDistrictId);
			}else if(upOneDistrictId != null){
				currentDistrictLevel = 2;
				upOneDistrictLevel = 1;
				modelMap.addAttribute("levelCount", 2);
				// 获取各个level的地区列表
				//List<TdDistrict> upOneDistrictLevelList = tdDistrictService.getDistrictByUpid(0);
				//List<TdDistrict> currentDistrictLevelList = tdDistrictService.getDistrictByUpid(upOneDistrictId);
			}else{
				currentDistrictLevel = 1;
				modelMap.addAttribute("levelCount", 1);
				// 获取各个level的地区列表
				//List<TdDistrict> upOneDistrictLevelList = tdDistrictService.getDistrictByUpid(0);
			}
			
			
			modelMap.addAttribute("upOneDistrictLevel", upOneDistrictLevel);
			modelMap.addAttribute("upOneDistrictId", upOneDistrictId);
			modelMap.addAttribute("upTwoDistrictLevel", upTwoDistrictLevel);
			modelMap.addAttribute("upTwoDistrictId", upTwoDistrictId);
			modelMap.addAttribute("currentDistrictLevel", currentDistrictLevel);
			modelMap.addAttribute("currentDistrictId", currentDistrictId);
			// 
		}else{
			isNew = "true";
			tdCampaign = new TdCampaign();
			tdCampaign.setRegionId(-1);
			modelMap.addAttribute("levelCount", 1);
		}
		Subject user = SecurityUtils.getSubject();
		if(user.hasRole("branch")){
			modelMap.addAttribute("branch", true);
		}
		modelMap.addAttribute("campaign", tdCampaign);
		modelMap.addAttribute("isNew", isNew);
		return "/admin/campaign/campaignForm";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdCampaign campaign, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=campaign){
			if(null==campaign.getId()){
				Subject user = SecurityUtils.getSubject();
				TdUser currUser = this.getCurrentUser();
				Integer regionid = null;
				if(user.hasRole("branch")){
					TdBrancheCompany branch = tdBrancheCompanyService.findByUid(currUser.getUid());
					if(null!=branch){
						regionid = branch.getRegionId();
					}
					campaign.setRegionId(regionid);
				}
			}
			// 除直辖市外的市没有活动
			TdDistrict district = tdDistrictService.findOne(campaign.getRegionId());
			if(district.getLevel().equals(Byte.valueOf("2"))){
				TdDistrict parentDistrict =  tdDistrictService.findOne(district.getUpid());
				if(!isCentralCity(parentDistrict.getName())){
					if(!isCentralCity(district.getName())){
						res.put("code", "0");
						res.put("msg", "除直辖市外的市没有活动。");
						return res;
					}					
				}
			}
			// 不能选择街道
			if(district.getLevel().equals(Byte.valueOf("3"))){
				TdDistrict parentDistrict =  tdDistrictService.findOne(district.getUpid());
				TdDistrict grandParentDistrict =  tdDistrictService.findOne(parentDistrict.getUpid());
				if(isCentralCity(grandParentDistrict.getName())){
					res.put("code", "0");
					res.put("msg", "不能选择街道。");
					return res;
				}
			}
			
			try {
				if(campaign.getId() == null){
					campaign.setCreateBy(this.getCurrentUser().getUid());
					campaign.setCreateTime(new Date());
				}else{
					campaign.setUpdateBy(this.getCurrentUser().getUid());
					campaign.setUpdateTime(new Date());
				}
				tdCampaignService.save(campaign);
				res.put("code", "1");
				res.put("msg", "活动保存成功");
				return res;
			}catch (Exception e) {
				logger.error("活动保存失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "活动保存失败");
				e.printStackTrace();
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "活动保存失败");
			return res;
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (null != idStr && !idStr.equals("")) {
			String[] idStrArray = idStr.split(",");
			try {
				for(String id : idStrArray){
					tdCampaignService.delete(Integer.parseInt(id));	
				}
				res.put("code", "1");
				res.put("msg", "活动删除成功。");
				return res;
			} catch (Exception e) {
				logger.error("活动删除失败，错误信息:" + e);
				res.put("code", "0");
				res.put("msg", "活动删除失败。");
				e.printStackTrace();
				return res;
			}
		} else {
			logger.error("活动删除失败，错误信息:idStr为空。");
			res.put("code", "0");
			res.put("msg", "活动删除失败。");
			return res;
		}
	}
	
	// 是否是直辖市
	private boolean isCentralCity(String cityName) {
		if(cityName.indexOf("北京")>-1 || cityName.indexOf("天津")>-1 || cityName.indexOf("上海")>-1 || cityName.indexOf("重庆")>-1){
			return true;
		}
		return false;
	}
	
}
