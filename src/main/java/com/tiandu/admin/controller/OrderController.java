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
import com.tiandu.custom.entity.TdRole;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.entity.TdUserIntegral;
import com.tiandu.custom.entity.TdUserIntegralLog;
import com.tiandu.custom.search.TdRoleSearchCriteria;
import com.tiandu.custom.search.TdUserAccountLogSearchCriteria;
import com.tiandu.custom.search.TdUserIntegralLogSearchCriteria;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdRoleService;
import com.tiandu.custom.service.TdUserAccountLogService;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.custom.service.TdUserIntegralLogService;
import com.tiandu.custom.service.TdUserIntegralService;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.service.TdOrderService;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/admin/order")
public class OrderController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdOrderService tdOrderService;
	
	@RequestMapping("/list")
	public String list(TdOrderSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("sc", sc) ;
	    return "/admin/order/list";
	}
	
	@RequestMapping("/search")
	public String search(TdOrderSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		//获取主菜单
		sc.setGetUpdateUser(true);
		List<TdOrder> orderList = tdOrderService.findBySearchCriteria(sc);
	    modelMap.addAttribute("orderList", orderList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/admin/order/listbody";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdOrder order = null;
		if(null!=id){
			order = tdOrderService.findOne(id);		    
		}
		if(null==order){
			order = new TdOrder();
		}
		modelMap.addAttribute("order", order);
		return "/admin/order/orderform";
	}
	

	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> save(TdOrder order, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=order){
			Date now = new Date();
			try {
				TdUser currManager = this.getCurrentUser();
				order.setUpdateBy(currManager.getUid());
				order.setUpdateTime(now);
				order.setCreateTime(now);
				tdOrderService.save(order);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("订单保存失败错误信息:"+e);
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
				tdOrderService.deleteByPrimaryKey(id);
				res.put("code", "1");
				return res;
			}catch (Exception e) {
				logger.error("订单删除失败错误信息:"+e);
				res.put("code", "0");
				return res;
			}
		}else{
			res.put("code", "0");
			return res;
		}
	}
	
	@RequestMapping(value="/updatestatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updatestatus(TdUser manager, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=manager){
		    try {
		    	Date now = new Date();
		    	TdUser currManager = this.getCurrentUser();
				manager.setUpdateBy(currManager.getUid());
				manager.setUpdateTime(now);
				manager.setCreateTime(now);
		    	tdUserService.saveUserStatus(manager);
				res.put("code", "1");
				return res;
		    }catch (Exception e) {
		    	logger.error("角色保存失败错误信息:"+e);
		    	res.put("code", "0");
		    	return res;
		    }
		}else{
			res.put("code", "0");
			return res;
		}
	}
	/**
	 * 获取订单详细信息
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/details")
	public String details(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdOrder order = null;
		if(null!=id){
			order = tdOrderService.findDetail(id);		    
		}
		if(null==order){
			modelMap.addAttribute("errmsg", "未找到相关订单，请重新操作！");
			return "/admin/error";
		}
		modelMap.addAttribute("order", order);
		return "/admin/order/detail";
	}

}
