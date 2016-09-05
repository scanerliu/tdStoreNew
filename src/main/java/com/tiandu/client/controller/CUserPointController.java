package com.tiandu.client.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.entity.TdUserIntegralLog;
import com.tiandu.custom.search.TdUserIntegralLogSearchCriteria;
import com.tiandu.custom.service.TdUserIntegralLogService;
import com.tiandu.custom.service.TdUserIntegralService;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/user/point")
public class CUserPointController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdUserIntegralService tdUserIntegralService;
	
	@Autowired
	private TdUserIntegralLogService tdUserIntegralLogService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("menucode", "point");
		TdUser currUser = this.getCurrentUser();
		TdUserIntegral integral = tdUserIntegralService.findOne(currUser.getUid());
		if(null==integral){
			integral = new TdUserIntegral();
			integral.setIntegral(0);
			integral.setTotalIntegral(0);
			integral.setUid(currUser.getUid());
			integral.setUpdateBy(1);
			integral.setUpdateTime(new Date());
		}
		modelMap.addAttribute("integral", integral);
		// 系统配置
		modelMap.addAttribute("system", getSystem());
	    return "/client/user/point/list";
	}
	
	@RequestMapping("/search")
	public String search(TdUserIntegralLogSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		sc.setUid(currUser.getUid());
		List<TdUserIntegralLog> logList = tdUserIntegralLogService.findBySearchCriteria(sc);
	    modelMap.addAttribute("logList", logList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/client/user/point/listbody";
	}
	
}
