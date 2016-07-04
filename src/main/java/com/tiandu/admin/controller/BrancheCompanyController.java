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
import com.tiandu.custom.entity.TdBrancheCompany;
import com.tiandu.custom.search.TdBrancheCompanySearchCriteria;
import com.tiandu.custom.service.TdBrancheCompanyService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.district.service.TdDistrictService;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/branch")
public class BrancheCompanyController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdBrancheCompanyService tdBrancheCompanyService;
	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdDistrictService tdDistrictService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/brancheCompany/brancheCompanyList";
	}
	
	@RequestMapping("/search")
	public String search(TdBrancheCompanySearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<TdBrancheCompany> brancheCompanyList = tdBrancheCompanyService.findBySearchCriteria(sc);
	    modelMap.addAttribute("brancheCompanyList", brancheCompanyList);
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/brancheCompany/brancheCompanyListbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdBrancheCompany tdBrancheCompany = null;
		if(null!=id && id != 0){
			tdBrancheCompany = tdBrancheCompanyService.findOne(id);
			if(tdBrancheCompany.getUid() != null){
				tdBrancheCompany.setUser(tdUserService.findOne(tdBrancheCompany.getUid()));
			}
			if(tdBrancheCompany.getUpdateBy() != null){
				tdBrancheCompany.setUpdatePerson(tdUserService.findOne(tdBrancheCompany.getUpdateBy()));
			}
			if(tdBrancheCompany.getRegionId() != null){
				tdBrancheCompany.setDistrict(tdDistrictService.findOne(tdBrancheCompany.getRegionId()));
			}
		}else{
			tdBrancheCompany = new TdBrancheCompany();	
		}
		modelMap.addAttribute("tdBrancheCompany", tdBrancheCompany);
		return "/admin/brancheCompany/brancheCompanyForm";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdBrancheCompany brancheCompany, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=brancheCompany){
			try {
				TdBrancheCompany tbc = tdBrancheCompanyService.findOne(brancheCompany.getId());
				tbc.setUpdateBy(this.getCurrentUser().getUid());
				tbc.setUpdateTime(new Date());
				tbc.setStatus(brancheCompany.getStatus());
				tdBrancheCompanyService.save(tbc);
				res.put("msg", "分公司状态更新成功。");
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("分公司状态更新失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "分公司状态更新失败。");
				e.printStackTrace();
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "分公司状态更新失败。");
			return res;
		}
	}
	
	@RequestMapping(value="/goCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> goCheck(Integer id, Boolean isPass, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(id == null){
			res.put("code", "0");
			res.put("msg", "分公司状态更新失败。");
			logger.error("由于分公司Id为空，分公司状态更新失败。");
			return res;
		}
		TdBrancheCompany tbc = tdBrancheCompanyService.findOne(id);
		if(isPass){
			tbc.setStatus(Byte.valueOf("1"));
		}else{
			tbc.setStatus(Byte.valueOf("2"));
		}
		tbc.setUpdateTime(new Date());
		tbc.setUpdateBy(this.getCurrentUser().getUid());
		tdBrancheCompanyService.save(tbc);
		res.put("code", "1");
		res.put("msg", "分公司状态更新成功。");
		return res;
	}
	
}
