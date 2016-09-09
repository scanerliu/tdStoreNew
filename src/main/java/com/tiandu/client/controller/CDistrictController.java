package com.tiandu.client.controller;

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
 */
@Controller
@RequestMapping("/region")
public class CDistrictController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdDistrictService tdDistrictService;

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
		return "/client/region/regionselect";
	}
}
