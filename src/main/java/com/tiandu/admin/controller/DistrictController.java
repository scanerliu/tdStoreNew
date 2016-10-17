package com.tiandu.admin.controller;

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

import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.search.TdDistrictSearchCriteria;
import com.tiandu.district.service.TdDistrictService;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/district")
public class DistrictController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdDistrictService tdDistrictService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 获取地区列表
		return "/admin/district/list";
	}

	@RequestMapping("/search")
	public String search(TdDistrictSearchCriteria sc, HttpServletRequest request, HttpServletResponse response,	ModelMap modelMap) {
		//设置上级id
		if(null==sc.getUpid()){
			sc.setUpid(0);
		}
		List<TdDistrict> districtList = tdDistrictService.getDistrictByUpid(sc.getUpid());

		modelMap.addAttribute("districtList", districtList);
		modelMap.addAttribute("sc", sc);

		return "/admin/district/listbody";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(Integer upid, String newDistrict, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		TdDistrict district = new TdDistrict();
		short displayorder = 0;
		// 分三种情况
		if(null==upid){
			res.put("code", "0");
			return res;
		}else if(upid==0){
			district.setName(newDistrict);
			district.setDisplayorder(displayorder);
			district.setUpid(upid);
			district.setLevel(Byte.valueOf("1"));
		}else{
			TdDistrict updistrict = tdDistrictService.findOne(upid);
			if(null==updistrict){
				res.put("code", "0");
				return res;
			}
			byte upLevel = updistrict.getLevel();
			upLevel++;
			district.setName(newDistrict);
			district.setDisplayorder(displayorder);
			district.setUpid(upid);
			district.setLevel(upLevel);
		}
		tdDistrictService.save(district);
		res.put("code", "1");
		return res;
			
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Integer id, Integer provinceId, Integer cityId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdDistrict district = tdDistrictService.findOne(id);
		modelMap.addAttribute("district", district);
		modelMap.addAttribute("provinceId", provinceId==null?"-1":provinceId.toString());
		modelMap.addAttribute("cityId", cityId==null?"-1":cityId.toString());
		return "/admin/district/districtform";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> update(Integer id, String name, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> res = new HashMap<String, String>();
		TdDistrict district = tdDistrictService.findOne(id);
		if(district != null){
			district.setName(name);
			tdDistrictService.save(district);
			res.put("code", "1");
		}else{
			res.put("code", "0");
			logger.error("地区更新失败，原因：更新地区不存在。");
		}
		return res;
	}
	
	

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (null != idStr && !idStr.equals("")) {
			try {
				tdDistrictService.delete(Integer.parseInt(idStr));
				res.put("code", "1");
				return res;
			} catch (Exception e) {
				logger.error("地区删除失败错误信息:" + e);
				res.put("code", "0");
				return res;
			}
		} else {
			res.put("code", "0");
			return res;
		}
	}

	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> batchDelete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		String[] ids = idStr.split(",");
		try {
			for (String id : ids) {
				tdDistrictService.delete(Integer.parseInt(id));
			}
			res.put("code", "1");
			return res;
		} catch (Exception e) {
			logger.error("地区删除失败错误信息:" + e);
			res.put("code", "0");
			return res;
		}
	}
	
	@RequestMapping(value = "/hasChrildren", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> hasChrildren(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		String[] idArray = idStr.split(",");
		for(String s : idArray){
			if(s != null && !s.equals("")){
				List<TdDistrict> districtList = tdDistrictService.getDistrictByUpid(Integer.parseInt(s));
				if(districtList != null && districtList.size() > 0){
					res.put("code", "0");
					return res;
				}
			}
		}
		res.put("idStr", idStr);
		res.put("code", "1");
		return res;
	}
	
	@RequestMapping("/regionselect")
	public String regionselect(TdDistrictSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		sc.setFlag(false);
		TdDistrict parent = new TdDistrict();	//上级
		TdDistrict province = new TdDistrict();	//省份
		if(null!=sc.getUpid()&&sc.getUpid()>0){
			parent = tdDistrictService.findOne(sc.getUpid());
			if(parent!=null && parent.getLevel()==1){
				province = parent;
			}else if(null!=sc.getProvinceId()&&sc.getProvinceId()>0){
				province = tdDistrictService.findOne(sc.getProvinceId());
			}
		}
		List<TdDistrict> regionList = tdDistrictService.findBySearchCriteria(sc);
		modelMap.addAttribute("regionList", regionList);
		modelMap.addAttribute("parent", parent);
		modelMap.addAttribute("province", province);
		return "/admin/district/regionselect";
	}
	
	@RequestMapping("/regionlongselect")
	public String regionlongselect(TdDistrictSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		sc.setFlag(false);
		TdDistrict parent = new TdDistrict();	//上级
		TdDistrict province = new TdDistrict();	//省份
		if(null!=sc.getUpid()&&sc.getUpid()>0){
			parent = tdDistrictService.findOne(sc.getUpid());
			if(parent!=null && parent.getLevel()==1){
				province = parent;
			}else if(null!=sc.getProvinceId()&&sc.getProvinceId()>0){
				province = tdDistrictService.findOne(sc.getProvinceId());
			}
		}
		List<TdDistrict> regionList = tdDistrictService.findBySearchCriteria(sc);
		modelMap.addAttribute("regionList", regionList);
		modelMap.addAttribute("parent", parent);
		modelMap.addAttribute("province", province);
		modelMap.addAttribute("sc", sc);
		return "/admin/district/regionlongselect";
	}
	
	@RequestMapping("/getDistrictSelections")
	public String getDistrictSelections(Integer level, Integer selectedDistrictId, String prefix, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdDistrictSearchCriteria sc = new TdDistrictSearchCriteria();
		sc.setFlag(false);
		sc.setUpid(selectedDistrictId);
		List<TdDistrict> districtList = tdDistrictService.findBySearchCriteria(sc);
		modelMap.addAttribute("districtList", districtList);
		
		String selectedInputName = ""; // 根据level，即根据要加载的区域来确定加载区域中select框的inputName
		if(level == 0){
			selectedInputName = "firstDistrictId";
		}else if(level == 1){
			selectedInputName = "secondDistrictId";
		}else if(level == 2){
			selectedInputName = "thirdDistrictId";
		}
		modelMap.addAttribute("selectedInputName", selectedInputName);
		modelMap.addAttribute("level", level+1);  // 用于设置加载区域中select框的的级别
		modelMap.addAttribute("prefix", prefix);
		return "/admin/district/districtSelections";
	}

	// 是否是直辖市
	private boolean isCentralCity(String cityName) {
		switch (cityName) {
		case "北京市":
		case "天津市":
		case "上海市":
		case "重庆市":
			return true;
		}
		return false;
	}

}
