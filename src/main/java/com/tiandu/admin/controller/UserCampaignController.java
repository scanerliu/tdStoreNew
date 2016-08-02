package com.tiandu.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.custom.entity.TdUserCampaign;
import com.tiandu.custom.search.TdUserCampaignCriteria;
import com.tiandu.custom.service.TdCampaignService;
import com.tiandu.custom.service.TdUserCampaignService;

/**
 * 
 * @author Max
 * 
 * 创建时间：2016年7月15日 上午9:26:46
 */
@Controller
@RequestMapping("/admin/usercampaign")
public class UserCampaignController {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdUserCampaignService tdUserCampaignService;
	
	@Autowired
	private TdCampaignService tdCampaignService;
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req,ModelMap map)
	{
		return "/admin/campaign/list";
	}
	
	@RequestMapping("/search")
	public String search(TdUserCampaignCriteria sc,HttpServletRequest req,ModelMap map)
	{
		sc.setOrderBy("2");
		map.addAttribute("userCampaignList", tdUserCampaignService.findBySearchCriteria(sc));
		map.addAttribute("sc", sc);
		
		return "/admin/campaign/listbody";
		
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletRequest req,ModelMap map)
	{
		if(null != id)
		{
			map.addAttribute("campaign", tdUserCampaignService.findOne(id));
		}
		
		return "/admin/campaign/editform";
	}
	
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(TdUserCampaign tdUserCampaign,HttpServletRequest req,ModelMap map)
	{
		Map<String,Object> res = new HashMap<>();
		res.put("code",0);
		
		if(null != tdCampaignService)
		{
			tdUserCampaignService.save(tdUserCampaign);
			res.put("code", 1);
		}
		
		return res;
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (null != idStr && !idStr.equals("")) {
			String[] idStrArray = idStr.split(",");
			try {
				for(String id : idStrArray){
					tdUserCampaignService.deleteByPrimaryKey(Integer.parseInt(id));
				}
				res.put("code", "1");
				res.put("msg", "删除成功。");
				return res;
			} catch (Exception e) {
				logger.error("删除失败，错误信息:" + e);
				res.put("code", "0");
				res.put("msg", "删除失败。");
				e.printStackTrace();
				return res;
			}
		} else {
			logger.error("删除失败，错误信息:idStr为空。");
			res.put("code", "0");
			res.put("msg", "删除失败。");
			return res;
		}
	}
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "campaignId", required = false) Integer campaignId,
                        Model model) {
        if (null != campaignId) {
            model.addAttribute("tdUserCampaign", tdUserCampaignService.findOne(campaignId));
        }
    }
}
