package com.tiandu.mobile.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.alipay.util.AlipayNotify;
import com.tiandu.common.controller.BaseController;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.complaint.entity.TdComplaint;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.express.entity.TdExpress;
import com.tiandu.express.search.TdExpressSearchCriteria;
import com.tiandu.express.service.TdExpressService;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.search.TdOrderShipmentSearchCriteria;
import com.tiandu.order.service.TdOrderLogService;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdOrderShipmentService;
import com.tiandu.order.service.TdOrderSkuService;
import com.tiandu.order.vo.OperResult;
import com.tiandu.payment.alipay.AlipayConfig;
import com.tiandu.payment.alipay.Constants;
import com.tiandu.payment.alipay.PaymentChannelAlipay;

/**
 * 
 * @author liuxinbing
 *
 */
@Controller
@RequestMapping("/mobile/order")
public class MOrderController extends BaseController {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private TdOrderService tdOrderService;
	
	@Autowired
	private TdExpressService tdExpressService;
	
	@Autowired
	private TdOrderShipmentService tdOrderShipmentService;
	
	@Autowired
	private TdOrderLogService tdOrderLogService;
	
	@Autowired
	private TdOrderSkuService tdOrderSkuService;
	
	@RequestMapping("/list")
	public String list(TdOrderSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("sc", sc) ;
	    return "/mobile/order/list";
	}
	
	@RequestMapping("/search")
	public String search(TdOrderSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		//获取主菜单
		sc.setUid(currUser.getUid());
		sc.setGetProductSku(true);
		sc.setPageSize(3);
		List<TdOrder> orderList = tdOrderService.findBySearchCriteria(sc);
	    modelMap.addAttribute("orderList", orderList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/mobile/order/listbody";
	}
	
	
	@RequestMapping("/refundlist")
	public String refundlist(TdOrderShipmentSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("sc", sc) ;
	    return "/mobile/order/refundlist";
	}
	
	@RequestMapping("/searchreturn")
	public String searchreturn(TdOrderShipmentSearchCriteria sc, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		//
		sc.setGetOrder(true);
		sc.setUid(currUser.getUid());
		sc.setType(Byte.valueOf("2"));
		sc.setPageSize(3);
		List<TdOrderShipment> shipList = tdOrderShipmentService.findBySearchCriteria(sc);
	    modelMap.addAttribute("shipList", shipList) ;
	    modelMap.addAttribute("sc", sc) ;
		return "/mobile/order/returnlistbody";
	}
	
	@RequestMapping("/detail{orderId}")
	public String detail(@PathVariable("orderId") Integer orderId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=orderId && orderId>0){
			order = tdOrderService.findDetail(orderId);
		}
		if(null==order || !order.getUserId().equals(currUser.getUid())){
		    return "redirect:404";
		}
		modelMap.addAttribute("order", order) ;
		return "/mobile/order/detail";
	}
	
	@RequestMapping("/applyrefund{orderId}")
	public String applyRefund(@PathVariable("orderId") Integer orderId,Integer skuId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=orderId && orderId>0){
			order = tdOrderService.findDetail(orderId);
		}
		if(null==order || !order.getUserId().equals(currUser.getUid())){
		    return "redirect:404";
		}
		modelMap.addAttribute("order", order) ;
		modelMap.addAttribute("sku", tdOrderSkuService.findOne(skuId));
		return "/mobile/order/applyrefund";
	}
	
	@RequestMapping("/refund")
	public String refund(TdOrderShipment shipment,Integer skuId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=shipment.getOrderId() && shipment.getOrderId()>0){
			order = tdOrderService.findDetail(shipment.getOrderId());
		}
		if(null==order || !order.getUserId().equals(currUser.getUid())){
		    return "redirect:404";
		}
		OperResult result = tdOrderService.applyRefundOrder(order, shipment,skuId);
		modelMap.addAttribute("result", result) ;
		return "/mobile/order/refund";
	}
	
	@RequestMapping("/refundtract{shipId}")
	public String refundtract(@PathVariable("shipId") Integer shipId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdOrderShipment shipment = null;
		TdOrder order = null;
		if(null!=shipId && shipId>0){
			shipment = tdOrderShipmentService.findOne(shipId);
		}
		if(null==shipment && null!= shipment.getOrderId()){
		    return "redirect:404";
		}
		order = tdOrderService.findOne(shipment.getOrderId());
		if(null==order || !order.getUserId().equals(currUser.getUid())){
			return "redirect:404";
		}		
		//查询物流公司
		TdExpressSearchCriteria sc = new TdExpressSearchCriteria();
		sc.setFlag(false);
		List<TdExpress> expressList = tdExpressService.findBySearchCriteria(sc);
		modelMap.addAttribute("expressList", expressList);		
	    modelMap.addAttribute("shipment", shipment) ;
		return "/mobile/order/refundtract";
	}
	@RequestMapping("/savetract")
	@ResponseBody
	public Map<String,String> savetract(TdOrderShipment ship, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> map = new HashMap<String, String>();
		TdUser currUser = this.getCurrentUser();
		Date now = new Date();
		TdOrder order = null;
		TdOrderShipment shipment = null;
		if(null!=ship.getId() && ship.getId()>0){
			shipment = tdOrderShipmentService.findOne(ship.getId());
		}
		if(null==shipment || null== shipment.getOrderId()){
			map.put("code", "0");
			map.put("msg", "录入物流单号失败：退款申请不存在！");
			return map;
		}
		order = tdOrderService.findOne(shipment.getOrderId());
		if(null==order || !order.getUserId().equals(currUser.getUid())){
			map.put("code", "0");
			map.put("msg", "录入物流单号失败：退款申请不存在！");
			return map;
		}
		TdOrderShipment saverecord = new TdOrderShipment();
		saverecord.setId(shipment.getId());
		saverecord.setTrackingId(ship.getTrackingId());
		saverecord.setTrackingNo(ship.getTrackingNo());
		saverecord.setUpdateBy(currUser.getUid());
		saverecord.setUpdateTime(now);
		saverecord.setStatus(Byte.valueOf("4"));
		tdOrderShipmentService.save(saverecord);
		map.put("code", "1");
		map.put("msg", "录入物流单号成功。");
		return map;
	}
	
	@RequestMapping("/complaint{orderId}")
	public String complaint(@PathVariable("orderId") Integer orderId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=orderId && orderId>0){
			order = tdOrderService.findDetail(orderId);
		}
		if(null==order || !order.getUserId().equals(currUser.getUid())){
			return "redirect:404";
		}
		modelMap.addAttribute("order", order) ;
		return "/mobile/order/applycomplaint";
	}
	
	@RequestMapping("/complaintorder")
	public String complaintOrder(TdComplaint complaint, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=complaint.getOrderId() && complaint.getOrderId()>0){
			order = tdOrderService.findDetail(complaint.getOrderId());
		}
		if(null==order || !order.getUserId().equals(currUser.getUid())){
			return "redirect:404";
		}
		OperResult result = tdOrderService.complaintOrder(order, complaint);
		modelMap.addAttribute("result", result) ;
		return "/mobile/order/complaint";
	}
	
	/**
	 * 确认收货操作
	 * @param ship
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/receiptorder")
	@ResponseBody
	public Map<String,String> receiptOrder(Integer orderId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> map = new HashMap<String, String>();
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=orderId && orderId>0){
			order = tdOrderService.findOne(orderId);
		}
		if(null==order || null== order.getOrderId() || order.getUserId()!=currUser.getUid()){
			map.put("code", "0");
			map.put("msg", "确认收货操作失败：订单申请不存在！");
			return map;
		}
		tdOrderService.receiptOrder(order, currUser);
		map.put("code", "1");
		map.put("msg", "确认收货操作成功。");
		return map;
	}
	
	/**
	 * 立即支付
	 * @author Max
	 * 
	 */
	@RequestMapping("/dopay{orderId}")
	public String dopay(@PathVariable Integer orderId,HttpServletRequest req,ModelMap map){
		
		String ua = req.getHeader("User-Agent");
		if(WebUtils.checkAgentIsMobile(ua)){
			req.setAttribute("type", "m");
		}
		
		if(null == orderId){
			return "redirect:404";
		}
		
		TdOrder order = tdOrderService.findOne(orderId);
		if(null == order){
			return "redirect:404";
		}
		
		// 系统配置
		map.addAttribute("system", getSystem());
		
		// 支付时间验证，订单生成24小时内
		Date cur = new Date();
		long temp = cur.getTime() - order.getCreateTime().getTime();
		if (temp > 1000 * 3600 * 24) {
			order.setOrderStatus((byte)-1);
			tdOrderService.save(order);
			return "/mobile/overtime";
		}
		
		// 非待支付订单
		if(order.getPayStatus() != 2){
			return "redirect:404";
		}
		
		// 支付金额
		req.setAttribute("totalAmount", order.getTotalAmount().toString());
		// 订单编号
		req.setAttribute("orderNo", order.getOrderNo());
		
		String payForm = "";
		Byte pay = order.getPaymentId();
		
		if(ConstantsUtils.ORDER_PAYMENT_ALIPAY.equals(pay)){
			// 支付宝支付
			PaymentChannelAlipay paymentChannelAlipay = new PaymentChannelAlipay();
			payForm = paymentChannelAlipay.getPayFormData(req);
		}
		System.err.println(payForm);
		map.addAttribute("payForm", payForm);
		
		return "/mobile/pay_form";
	}
	
	@RequestMapping(value = "/pay/notify_alipay")
    public void payNotifyAlipay(ModelMap map, HttpServletRequest req,
            HttpServletResponse resp) {
    	PaymentChannelAlipay payChannelAlipay = new PaymentChannelAlipay();
        payChannelAlipay.doResponse(req, resp);
    }
	
	@RequestMapping(value = "/pay/result_alipay")
    public String payResultAlipay( ModelMap map, HttpServletRequest req,
            HttpServletResponse resp) {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = req.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter
                .hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"),
                        AlipayConfig.CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }

        // 获取支付宝的返回参数
        String orderNo = "";
        String trade_status = "";
        try {
            // 商户订单号
            orderNo = new String(req.getParameter(Constants.KEY_OUT_TRADE_NO)
                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
            // 交易状态
            trade_status = new String(req.getParameter("trade_status")
                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        TdOrder order = tdOrderService.findByOrderNo(orderNo);
        String ua = req.getHeader("User-Agent");
        if (order == null) {
            // 订单不存在
        	// 触屏
        	
    		if(WebUtils.checkAgentIsMobile(ua)){
    			return "/touch/order_pay_failed";
    		}
            return "/client/order_pay_failed";
        }
        map.put("order", order);
        if (verify_result) {// 验证成功
            if ("TRADE_SUCCESS".equals(trade_status)) {

                // 订单支付成功
                tdOrderService.AfterPaySuccess(order);
                // 触屏
            	if(WebUtils.checkAgentIsMobile(ua)){
                    return "/touch/order_pay_success";
                }
                return "/client/order_pay_success";
            }
        }

        // 验证失败或者支付失败
        // 触屏
        if(WebUtils.checkAgentIsMobile(ua)){
            return "/touch/order_pay_failed";
        }
        return "/client/order_pay_failed";
    }
}
