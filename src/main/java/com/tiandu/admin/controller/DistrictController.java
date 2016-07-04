package com.tiandu.admin.controller;

import java.util.ArrayList;
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

import com.tiandu.common.utils.MessageSender;
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.service.TdRoleService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.search.TdDistrictSearchCriteria;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.menu.service.TdMenuService;

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
	public String search(Integer provinceId, Integer cityId, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		// 省列表
		List<TdDistrict> provinceList = tdDistrictService.getDistrictByUpid(0);

		if (provinceId != null && provinceId != -1) {
			List<TdDistrict> cityList = tdDistrictService.getDistrictByUpid(provinceId);
			// 是否是直辖市
			if (tdDistrictService.findOne(provinceId) != null
					&& isCentralCity(tdDistrictService.findOne(provinceId).getName())) {
				modelMap.addAttribute("isCentralCity", true);
			} else {
				modelMap.addAttribute("isCentralCity", false);
			}
			modelMap.addAttribute("cityList", cityList);
		}
		if (cityId != null && cityId != -1) {
			List<TdDistrict> areaList = tdDistrictService.getDistrictByUpid(cityId);
			// 若用户在选择了与市不匹配的省时，可视为只选择了省
			if (tdDistrictService.findOne(cityId) != null && provinceId != null && provinceId != -1
					&& tdDistrictService.findOne(cityId).getUpid().equals(provinceId)) {
				modelMap.addAttribute("areaList", areaList);
			} else {
				cityId = -1;
			}
		}
		modelMap.addAttribute("provinceList", provinceList);
		modelMap.addAttribute("provinceId", provinceId);
		modelMap.addAttribute("cityId", cityId);

		return "/admin/district/listbody";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(Integer provinceId, Integer cityId, String newDistrict, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		// 分三种情况：上级为市
		if (cityId != null && cityId != -1) {
			TdDistrict district = new TdDistrict();
			district.setName(newDistrict);
			short displayorder = 0; // 顺序默认为0
			district.setDisplayorder(displayorder);
			TdDistrict theCity = tdDistrictService.findOne(cityId);
			if (theCity != null) {
				byte upLevel = theCity.getLevel();
				upLevel++;
				district.setLevel(upLevel);
				district.setUpid(theCity.getId());
				tdDistrictService.save(district);
				res.put("code", "1");
				return res;
			} else {
				logger.error("地区保存失败，原因：上级地区不存在。");
				res.put("code", "0");
				return res;
			}
			// 上级为省或直辖市
		} else if (provinceId != null && provinceId != -1) {
			TdDistrict district = new TdDistrict();
			district.setName(newDistrict);
			short displayorder = 0; // 顺序默认为0
			district.setDisplayorder(displayorder);
			TdDistrict theProvince = tdDistrictService.findOne(provinceId);
			if (theProvince != null) {
				byte upLevel = theProvince.getLevel();
				upLevel++;
				district.setLevel(upLevel);
				district.setUpid(theProvince.getId());
				tdDistrictService.save(district);
				res.put("code", "1");
				return res;
			} else {
				logger.error("地区保存失败，原因：上级地区不存在。");
				res.put("code", "0");
				return res;
			}
		} else {
			// 没有上级
			TdDistrict district = new TdDistrict();
			district.setName(newDistrict);
			short displayorder = 0; // 顺序默认为0
			district.setDisplayorder(displayorder);
			byte level = 1;
			district.setLevel(level);
			district.setUpid(0);
			tdDistrictService.save(district);
			res.put("code", "1");
			return res;
		}
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
	
	@RequestMapping("/getDistrictSelections")
	public String getDistrictSelections(Integer level, Integer selectedDistrictId,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
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
