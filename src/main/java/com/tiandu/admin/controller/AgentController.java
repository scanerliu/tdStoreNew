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

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.search.TdAgentSearchCriteria;
import com.tiandu.custom.service.TdAgentService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.product.service.TdProductTypeService;

/**
 * 
 * @author L. Gao
 *
 */
@Controller
@RequestMapping("/admin/agent")
public class AgentController extends BaseController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdAgentService tdAgentService;

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdDistrictService tdDistrictService;

	@Autowired
	private TdProductTypeService tdProductTypeService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "/admin/agent/agentList";
	}

	@RequestMapping("/search")
	public String search(TdAgentSearchCriteria sc, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		//获取代理列表
		List<TdAgent> agentList = tdAgentService.findBySearchCriteria(sc);
		modelMap.addAttribute("agentList", agentList);
		modelMap.addAttribute("sc", sc);
		return "/admin/agent/agentListbody";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String idStr, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> res = new HashMap<String, String>();
		if (null != idStr && !idStr.equals("")) {
			String[] idStrArray = idStr.split(",");
			try {
				for(String id : idStrArray){
					tdAgentService.delete(Integer.parseInt(id));	
				}
				res.put("code", "1");
				res.put("msg", "代理删除成功。");
				return res;
			} catch (Exception e) {
				logger.error("代理删除失败，错误信息:" + e);
				res.put("code", "0");
				res.put("msg", "代理删除失败。");
				e.printStackTrace();
				return res;
			}
		} else {
			logger.error("代理删除失败，错误信息:idStr为空。");
			res.put("code", "0");
			res.put("msg", "代理删除失败。");
			return res;
		}
	}
}
