package com.tiandu.client.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.common.controller.BaseController;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.express.entity.TdExpress;
import com.tiandu.express.search.TdExpressSearchCriteria;
import com.tiandu.express.service.TdExpressService;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.search.TdOrderShipmentSearchCriteria;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdOrderShipmentService;
import com.tiandu.order.vo.OperResult;

/**
 * 
 * 供应商
 * 
 */
@Controller
@RequestMapping("/supply")
public class CSupplyController extends BaseController{

	@Autowired
	TdOrderService tdOrderService;
	
	@Autowired
	TdOrderShipmentService tdOrderShipmentService;
	
	@Autowired
	private TdExpressService tdExpressService;
	
	
	@RequestMapping("/order")
	public String supplyOrder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		modelMap.addAttribute("menucode", "shipment") ;//菜单code
		
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/login";
		}
		return "/client/supply/list";
	}
	
	@RequestMapping("/searchorder")
	public String searchorder(TdOrderSearchCriteria sc ,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		TdUser user = getCurrentUser();
		sc.setSupplierId(user.getUid());
		sc.setGetProductSku(true);
		modelMap.addAttribute("orderList", tdOrderService.findBySearchCriteria(sc));
		modelMap.addAttribute("sc", sc);
		return "/client/supply/order_list";
	}
	
	@RequestMapping("/refundlist")
	public String refundlist(TdOrderShipmentSearchCriteria sc ,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		// 系统配置
		modelMap.addAttribute("system", getSystem());
		modelMap.addAttribute("menucode", "refundlist") ;//菜单code
		TdUser user = getCurrentUser();
		if(null==user)
		{
			return "redirect:/login";
		}
		return "/client/supply/refundList";
	}
	@RequestMapping("/searchrefund")
	public String searchrefund(TdOrderShipmentSearchCriteria sc ,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		TdUser user = getCurrentUser();
		sc.setGetOrder(true);
		sc.setSupplyId(user.getUid());
		sc.setType((byte)2);
		modelMap.addAttribute("shipList", tdOrderShipmentService.findBySearchCriteria(sc));
		modelMap.addAttribute("sc", sc);
		return "/client/supply/refundListBody";
	}
	
	@RequestMapping("/order/detail{orderId}")
	public String detail(@PathVariable("orderId") Integer orderId, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		// 系统配置
		map.addAttribute("system", getSystem());
		
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=orderId && orderId>0){
			order = tdOrderService.findDetail(orderId);
		}
		if(null==order || !order.getSupplierId().equals(currUser.getUid())){
		    return "redirect:404";
		}
		map.addAttribute("order", order) ;
		return "/client/supply/order_detail";
	}
	
	@RequestMapping("/return/detail{shipId}")
	public String turnDetail(@PathVariable Integer shipId,HttpServletRequest req,ModelMap map)
	{
		// 系统配置
		map.addAttribute("system", getSystem());
		
		if(null == shipId){
			return "redirect:404";
		}
		TdOrderShipment shipment = tdOrderShipmentService.findOne(shipId);
		
		if(null != shipment){
			map.addAttribute("ship", shipment);
			map.addAttribute("order", tdOrderService.findDetail(shipment.getOrderId()));
		}
		
		return "/client/supply/turn_detail";
	}
	
	@RequestMapping("/shipment")
	public String shipment(Integer orderId,HttpServletRequest req,ModelMap map)
	{
		TdUser user = this.getCurrentUser();
		
		if(null == orderId)
		{
			return "redirect:404";
		}
		TdOrder order = tdOrderService.findDetail(orderId);
		
		if(null==order || !order.getSupplierId().equals(user.getUid())){
		    return "redirect:404";
		}
		
		// 系统配置
		map.addAttribute("system", getSystem());
		
		map.addAttribute("order", order);
		//查询物流公司
		TdExpressSearchCriteria sc = new TdExpressSearchCriteria();
		sc.setFlag(false);
		List<TdExpress> expressList = tdExpressService.findBySearchCriteria(sc);
		map.addAttribute("expressList", expressList);
		
		return "/client/supply/shipment";
	}
	
	/**
	 * 
	 * @author Max
	 * 同意退款
	 * 退款金额退回暂无。。。。。。。。
	 * 
	 */
	@RequestMapping(value = "/agree",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> agree(Integer shipId,HttpServletRequest req,ModelMap map){
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		
		if(null != shipId){
			TdOrderShipment shipment = tdOrderShipmentService.findOne(shipId);
			shipment.setStatus((byte)2);
			tdOrderShipmentService.save(shipment);
			res.put("code", 1);
			res.put("msg", "退款成功");
			return res;
		}
		res.put("msg", "参数错误");
		return res;
	}
	
	@RequestMapping("/refuse")
	public String refuse(Integer shipId,HttpServletRequest req,ModelMap map){
		// 系统配置
		map.addAttribute("system", getSystem());
		if(null == shipId){
			return "redirect:404";
		}
		
		map.addAttribute("ship", tdOrderShipmentService.findOne(shipId));
		
		return "/client/supply/refuse";
	}
	
	@RequestMapping(value = "/refuse/reply",method = RequestMethod.POST)
	public String refuseForm(TdOrderShipment tdOrderShipment,HttpServletRequest req,ModelMap map){
		
		tdOrderShipment.setStatus((byte)3);
		tdOrderShipmentService.save(tdOrderShipment);
		return "redirect:/client/supply/refuse?shipId="+tdOrderShipment.getId();
	}
	
	@RequestMapping(value="/shiporder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> shiporder(TdOrderShipment ship, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> res = new HashMap<String,Object>(); 
		res.put("code", 0);
		if(null == ship){
			res.put("msg", "数据有误");
			return res;
		}
		
		if(null == ship.getTrackingNo() || "".equals(ship.getTrackingNo().trim()) 
					|| ship.getTrackingNo().trim().length() < 2 || ship.getTrackingNo().trim().length() > 20){
			res.put("msg", "请正确输入物流编号");
			return res;
		}
		
		if(null!=ship && null!=ship.getOrderId()){
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
					res.put("msg", "操作成功");
				}else{
					res.put("code", "0");
					res.put("msg", result.getFailMsg());
				}
				return res;
		}else{
			res.put("code", "0");
			res.put("msg", "数据有误！");
			return res;
		}
	}
	
	
	
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "shipId", required = false) Integer shipId,
                        Model model) {
        if (null != shipId) {
            model.addAttribute("tdOrderShipment", tdOrderShipmentService.findOne(shipId));
        }
    }
}
