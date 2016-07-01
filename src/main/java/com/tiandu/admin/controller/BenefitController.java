package com.tiandu.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.system.entity.TdBenefit;
import com.tiandu.system.search.TdBenefitSearchCriteria;
import com.tiandu.system.service.TdBenefitService;

@Controller
@RequestMapping("/admin/benefit")
public class BenefitController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdBenefitService tdBenefitService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    return "/admin/benefit/list";
	}
	@RequestMapping("/search")
	public String search(TdBenefitSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		sc.setGetUpdateUser(true);
		List<TdBenefit> benefitList = tdBenefitService.findBySearchCriteria(sc);
	    modelMap.addAttribute("benefitList", benefitList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/benefit/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdBenefit tdbenefit = null;
		if(null!=id){
			tdbenefit = tdBenefitService.findOne(id);		    
		}
		if(null==tdbenefit){
			tdbenefit = new TdBenefit();
		}
		modelMap.addAttribute("tdbenefit", tdbenefit);
		return "/admin/benefit/benefitform";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdBenefit benefit, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=benefit){
			Date now = new Date();
			try {
				TdUser currManager = this.getCurrentUser();
				benefit.setUpdateBy(currManager.getUid());
				benefit.setUpdateTime(now);
				tdBenefitService.save(benefit);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("分润设置保存失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(Integer id, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=id){
			try {
				tdBenefitService.deleteByPrimaryKey(id);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("分润设置删除失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
}
