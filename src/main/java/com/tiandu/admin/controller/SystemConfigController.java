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
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.system.entity.TdSystemConfig;
import com.tiandu.system.search.TdSystemConfigSearchCriteria;
import com.tiandu.system.service.TdSystemConfigService;

@Controller
@RequestMapping("/admin/systemconfig")
public class SystemConfigController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdSystemConfigService tdSystemConfigService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    return "/admin/systemconfig/list";
	}
	@RequestMapping("/search")
	public String search(TdSystemConfigSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		sc.setFlag(false);
		List<TdSystemConfig> configList = tdSystemConfigService.findBySearchCriteria(sc);
		Map<String,String> configMap = new HashMap<String,String>();
		for(TdSystemConfig cf : configList){
			configMap.put(cf.getConfigKey(), cf.getConfigValue());
		}
	    modelMap.addAttribute("configMap", configMap) ;
		return "/admin/systemconfig/listbody";
	}
		
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>();
		String[] configKeys = request.getParameterValues("configKey");
		if(configKeys != null && configKeys.length > 0){
			TdUser currManager = this.getCurrentUser();
			Date now = new Date();
			res.put("code", "1");
			for (int i = 0; i < configKeys.length; i++) {
				String key = configKeys[i];
				String value = request.getParameter(key);

				TdSystemConfig systemConfig = tdSystemConfigService.findByKey(key);
				if(systemConfig!=null){
					if (value == null&& ConstantsUtils.CONF_DATA_TYPE_BOOLEAN.equals(systemConfig.getDataType())) {
						value = "false"; // 针对checkbox的处理
					}
					if(null!=systemConfig){
						
						systemConfig.setConfigValue(value);
						systemConfig.setUpdateBy(currManager.getUid());
						systemConfig.setUpdateTime(now);
						
						try {
							tdSystemConfigService.save(systemConfig);
						} catch (Exception e) {
//							e.printStackTrace();
							logger.error("更新系统配置出错", e);
							res.put("errMsg", "更新系统配置出错,稍后再试！");
							res.put("code", "0");
							break;
						}
					}
				}else{
					logger.error("新增系统配置出错  :key="+key);
					res.put("errMsg", "新增系统配置出错,稍后再试！");
					res.put("code", "0");
					break;
				}
			}
			return res;
		}else{
			res.put("code", "0");
			return res;
		}
	}
}
