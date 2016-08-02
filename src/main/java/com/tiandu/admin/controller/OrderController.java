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
import com.tiandu.express.entity.TdExpress;
import com.tiandu.express.search.TdExpressSearchCriteria;
import com.tiandu.express.service.TdExpressService;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderLog;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.search.TdOrderLogSearchCriteria;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.search.TdOrderShipmentSearchCriteria;
import com.tiandu.order.service.TdOrderLogService;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdOrderShipmentService;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.OrderPay;
import com.tiandu.order.vo.OrderRefund;

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
	
	@Autowired
	private TdExpressService tdExpressService;
	
	@Autowired
	private TdOrderShipmentService tdOrderShipmentService;
	
	@Autowired
	private TdOrderLogService tdOrderLogService;
	
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
	
	/**
	 * 订单发货面板
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/shippment")
	public String shippment(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdOrder order = null;
		if(null!=id){
			order = tdOrderService.findOne(id);		    
		}
		if(null==order){
			modelMap.addAttribute("errmsg", "未找到相关订单，请重新操作！");
			return "/admin/error";
		}
		//查询物流公司
		TdExpressSearchCriteria sc = new TdExpressSearchCriteria();
		sc.setFlag(false);
		List<TdExpress> expressList = tdExpressService.findBySearchCriteria(sc);
		modelMap.addAttribute("order", order);
		modelMap.addAttribute("expressList", expressList);
		return "/admin/order/shippment";
	}
	/**
	 * 订单发货操作
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/shiporder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> shiporder(TdOrderShipment ship, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=ship && null!=ship.getOrderId()){
			try {
				Date now = new Date();
				TdUser currUser = this.getCurrentUser();
				ship.setCreateTime(now);
				ship.setCreateBy(currUser.getUid());
				ship.setUpdateBy(currUser.getUid());
				ship.setUpdateTime(now);
				//发货操作
				OperResult result = tdOrderService.shiporder(ship);
				if(result.isFlag()){
					res.put("code", "1");
				}else{
					res.put("code", "0");
					res.put("msg", result.getFailMsg());
				}
				return res;
			}catch (Exception e) {
				logger.error("订单删除失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "系统错误："+e.getMessage());
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "数据有误！");
			return res;
		}
	}
	
	
	/**
	 * 获取订单发货信息
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/shippments")
	public String shippments(TdOrderShipmentSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		sc.setFlag(false);
		List<TdOrderShipment> shipList = tdOrderShipmentService.findBySearchCriteria(sc);
		modelMap.addAttribute("shipList", shipList);
		return "/admin/order/shiplist";
	}
	
	/**
	 * 订单退款面板
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/refund")
	public String refund(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdOrder order = null;
		if(null!=id){
			order = tdOrderService.findOne(id);		    
		}
		if(null==order){
			modelMap.addAttribute("errmsg", "未找到相关订单，请重新操作！");
			return "/admin/error";
		}
		modelMap.addAttribute("order", order);
		return "/admin/order/refund";
	}
	/**
	 * 订单退款操作
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/refundorder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> refundorder(OrderRefund refund, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=refund && null!=refund.getOrderId()){
			try {
				Date now = new Date();
				TdUser currUser = this.getCurrentUser();
				refund.setCreateTime(now);
				refund.setCreateBy(currUser.getUid());
				//发货操作
				OperResult result = tdOrderService.refundorder(refund);
				if(result.isFlag()){
					res.put("code", "1");
				}else{
					res.put("code", "0");
					res.put("msg", result.getFailMsg());
				}
				return res;
			}catch (Exception e) {
				logger.error("订单删除失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "系统错误："+e.getMessage());
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "数据有误！");
			return res;
		}
	}
	/**
	 * 订单收款面板
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/pay")
	public String pay(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdOrder order = null;
		if(null!=id){
			order = tdOrderService.findOne(id);		    
		}
		if(null==order){
			modelMap.addAttribute("errmsg", "未找到相关订单，请重新操作！");
			return "/admin/error";
		}
		modelMap.addAttribute("order", order);
		return "/admin/order/orderpay";
	}
	/**
	 * 订单退款操作
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/payorder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> payorder(OrderPay pay, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=pay && null!=pay.getOrderId()){
			try {
				Date now = new Date();
				TdUser currUser = this.getCurrentUser();
				pay.setCreateTime(now);
				pay.setCreateBy(currUser.getUid());
				//收款操作
				TdOrder order = tdOrderService.findOne(pay.getOrderId());
				if(null!=order){
					pay.setPayAmount(order.getUnPayAmount());
					pay.setPaymentId(Byte.valueOf("5"));
					OperResult result = tdOrderService.payOrder(order,pay);
					if(result.isFlag()){
						res.put("code", "1");
					}else{
						res.put("code", "0");
						res.put("msg", result.getFailMsg());
					}
				}else{
					res.put("code", "0");
					res.put("msg", "订单收款失败：订单未找到！");
				}
				return res;
			}catch (Exception e) {
				logger.error("订单收款失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "系统错误："+e.getMessage());
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "数据有误！");
			return res;
		}
	}
	
	/**
	 * 订单退款操作
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/completeorder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> completeorder(Integer id, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> res = new HashMap<String,String>(); 
		if(null!=id && id>0){
			try {
				TdUser currUser = this.getCurrentUser();
				//收款操作
				TdOrder order = tdOrderService.findOne(id);
				if(null!=order){
					OperResult result = tdOrderService.completeOrder(order, currUser);
					if(result.isFlag()){
						res.put("code", "1");
					}else{
						res.put("code", "0");
						res.put("msg", result.getFailMsg());
					}
				}else{
					res.put("code", "0");
					res.put("msg", "订单完成失败：订单未找到！");
				}
				return res;
			}catch (Exception e) {
				logger.error("订单完成失败错误信息:"+e);
				res.put("code", "0");
				res.put("msg", "系统错误："+e.getMessage());
				return res;
			}
		}else{
			res.put("code", "0");
			res.put("msg", "数据有误！");
			return res;
		}
	}
	
	/**
	 * 获取订单操作日志信息
	 * @param id 订单id
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/logs")
	public String logs(TdOrderLogSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		sc.setFlag(false);
		sc.setGetUpdateUser(true);
		List<TdOrderLog> logList = tdOrderLogService.findBySearchCriteria(sc);
		modelMap.addAttribute("logList", logList);
		return "/admin/order/orderloglist";
	}

}
