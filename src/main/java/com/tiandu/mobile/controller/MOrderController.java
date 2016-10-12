package com.tiandu.mobile.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiandu.alipay.util.AlipayNotify;
import com.tiandu.common.controller.BaseController;
import com.tiandu.common.tencent.common.Configure;
import com.tiandu.common.tencent.common.RandomStringGenerator;
import com.tiandu.common.tencent.common.Signature;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.common.utils.DateUtil;
import com.tiandu.common.utils.KuaiDiUtils;
import com.tiandu.common.utils.WebUtils;
import com.tiandu.complaint.entity.TdComplaint;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserAccount;
import com.tiandu.custom.entity.TdUserAccountLog;
import com.tiandu.custom.service.TdUserAccountService;
import com.tiandu.express.entity.TdExpress;
import com.tiandu.express.search.TdExpressSearchCriteria;
import com.tiandu.express.service.TdExpressService;
import com.tiandu.order.entity.TdJointOrder;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.entity.TdOrderShipment;
import com.tiandu.order.search.TdOrderSearchCriteria;
import com.tiandu.order.search.TdOrderShipmentSearchCriteria;
import com.tiandu.order.service.TdJointOrderService;
import com.tiandu.order.service.TdOrderLogService;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.order.service.TdOrderShipmentService;
import com.tiandu.order.service.TdOrderSkuService;
import com.tiandu.order.vo.OperResult;
import com.tiandu.order.vo.OrderCancel;
import com.tiandu.order.vo.PostageResponseVO;
import com.tiandu.order.vo.PostageVO;
import com.tiandu.payment.alipay.AlipayConfig;
import com.tiandu.payment.alipay.Constants;
import com.tiandu.payment.alipay.PaymentChannelAlipay;

import cfca.sadk.cmbc.tools.CMBCDecryptKit;

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
	
	@Autowired
	private TdUserAccountService tdUserAccountService;
	
	@Autowired
	private TdJointOrderService tdJointOrderService;
	
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
		if(order.getOrderStatus().compareTo(Byte.valueOf("3"))>=0){
			TdOrderShipmentSearchCriteria sc = new TdOrderShipmentSearchCriteria();
			sc.setType(Byte.valueOf("1"));
			sc.setOrderId(order.getOrderId());
			List<TdOrderShipment> ordershimentList = tdOrderShipmentService.findBySearchCriteria(sc);
			TdOrderShipment ordershipment = null;
			if(null!=ordershimentList && ordershimentList.size()>0){
				ordershipment = ordershimentList.get(0);
			}
			modelMap.addAttribute("ordershipment", ordershipment) ;
		}
		modelMap.addAttribute("order", order) ;
		return "/mobile/order/detail";
	}
	
	@RequestMapping("/searchpostinfo")
	public String searchpostinfo(PostageVO postage, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		PostageResponseVO presp = null;
		if(null!=postage && StringUtils.isNotBlank(postage.getTrackingCom())&&StringUtils.isNotBlank(postage.getTrackingNo())){
			presp = KuaiDiUtils.query(postage);
		}
		modelMap.addAttribute("presp", presp);		
		return "/mobile/order/postinfo";
	}
	
	@RequestMapping("/cancelorder{orderId}")
	public String cancelorder(@PathVariable("orderId") Integer orderId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=orderId && orderId>0){
			order = tdOrderService.findDetail(orderId);
		}
		if(null==order || !order.getUserId().equals(currUser.getUid())){
			modelMap.addAttribute("errmsg", "订单未找到！") ;
		    return "/mobile/error";
		}
		modelMap.addAttribute("order", order) ;
		return "/mobile/order/cancelorder";
	}
	@RequestMapping("/ordercancel")
	@ResponseBody
	public Map<String,String> ordercancel(OrderCancel ordercancel, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Map<String, String> map = new HashMap<String, String>();
		TdUser currUser = this.getCurrentUser();
		TdOrder order = null;
		if(null!=ordercancel.getOrderId() && ordercancel.getOrderId()>0){
			order = tdOrderService.findOne(ordercancel.getOrderId());
		}
		if(null==order || !order.getUserId().equals(currUser.getUid())){
			map.put("code", "0");
			map.put("msg", "订单未找到！");
			return map;
		}
		if(ConstantsUtils.ORDER_STATUS_PAYED.compareTo(order.getOrderStatus())<0){//进入发货流程后的订单不能取消
			map.put("code", "0");
			map.put("msg", "订单已经发货，不能取消！");
			return map;
		}
		if(ConstantsUtils.ORDER_KIND_AGENTPRODUCT.equals(order.getOrderType()) && ConstantsUtils.ORDER_STATUS_PAYED.compareTo(order.getOrderStatus())<=0){//代理产品支付后不能取消
			map.put("code", "0");
			map.put("msg", "代理订单，不能取消！");
			return map;
		}
		ordercancel.setOperBy(currUser.getUid());
		ordercancel.setCreateTime(new Date());
		OperResult result = tdOrderService.cancelOrderByUser(order, ordercancel);
		if(result.isFlag()){
			map.put("code", "1");
			map.put("msg", "订单取消成功。");
		}else{
			map.put("code", "0");
			map.put("msg", "订单取消失败:"+result.getFailMsg());
		}
		return map;
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
	
	@RequestMapping("/gopay{orderId}")
	public String gopay(@PathVariable Integer orderId,Byte paymentId,HttpServletRequest req,ModelMap map)
	{
		if(null == orderId || null == paymentId){
			return "redirect:404";
		}
		TdOrder order = tdOrderService.findOne(orderId);
		if(null == order){
			return "redirect:404";
		}
		
		order.setPaymentId(paymentId);
		tdOrderService.save(order);
		return "redirect:/mobile/order/dopay"+orderId+"?type=agent";
	}
	
	/**
	 * 联合订单支付
	 * @author Max
	 *
	 * @param orderId
	 * @param req
	 * @param map
	 * @return
	 */
	@RequestMapping("/jointdopay{orderId}")
	public String jointOrderPay(@PathVariable Integer orderId,HttpServletRequest req,ModelMap map)
	{
		TdUser user = this.getCurrentUser();
		if(null == user){
			return "redirect:404";
		}
		
		String ua = req.getHeader("User-Agent");
		if(WebUtils.checkAgentIsMobile(ua)){
			req.setAttribute("type", "m");
		}
		
		if(null == orderId){
			return "redirect:404";
		}
		
		TdJointOrder torder = tdJointOrderService.findOne(orderId);
		if(null == torder){
			return "redirect:404";
		}
		// 系统配置
		map.addAttribute("system", getSystem());
		
		TdOrderSearchCriteria sc = new TdOrderSearchCriteria();
    	sc.setFlag(false);
    	sc.setJointId(torder.getId());
    	List<TdOrder> orderList = tdOrderService.findBySearchCriteria(sc);
    	if(null != orderList && orderList.size() > 0)
    	{
    		for (TdOrder tdOrder : orderList) {
    			if(!ConstantsUtils.ORDER_STATUS_NEW.equals(tdOrder.getOrderStatus())||ConstantsUtils.ORDER_PAY_STATUS_PAYED.equals(tdOrder.getPayStatus())){
    				return "/mobile/pay_failed";
    			}
    		}
    	}else{
    		return "/mobile/pay_failed";
    	}
		
		Byte pay = torder.getPaymentId();
		if(ConstantsUtils.ORDER_PAYMENT_ALIPAY.equals(pay)){
			// 支付宝支付
			// 支付金额
			req.setAttribute("totalAmount", torder.getAmount().toString());
			// 订单编号
			req.setAttribute("orderNo", torder.getJno());
			PaymentChannelAlipay paymentChannelAlipay = new PaymentChannelAlipay();
			map.addAttribute("payForm", paymentChannelAlipay.getPayFormData(req));
			
			return "/mobile/pay_form";
		}else if(ConstantsUtils.ORDER_PAYMENT_ACCOUNT.equals(pay)){
			// 钱包余额支付
			TdUserAccount account = tdUserAccountService.findByUid(user.getUid());
			// 钱包余额小于支付金额
			if(null == account || torder.getAmount().compareTo(account.getAmount()) > 0){
				return "/mobile/pay_failed";
			}
			jointOrderAccountPay(torder,account);
			map.addAttribute("torder", torder);
			
			return "/mobile/pay_success";
		}else if(ConstantsUtils.ORDER_PAYMENT_WEIXIN.equals(pay)){
			
			String openId = user.getJointId();
			if(null == openId || openId.contains("sys")){
				return "redirect:404";
			}
			// 微信支付
			Boolean wxPay = wxPay(orderId, openId,"jointOrder", req,  map);
			if(null == wxPay){
				return "redirect:404";
			}else if(wxPay){
				return "/mobile/pay_wx";
			}
		}else if(ConstantsUtils.ORDER_PAYMENT_UNIONPAY.equals(pay)){//银联支付
			// 支付金额
			req.setAttribute("totalAmount", torder.getAmount().toString());
			// 订单编号
			req.setAttribute("orderNo", torder.getJno());
			CMBCDecryptKit userKit = new CMBCDecryptKit();
	        try {
	        	String rootPath = req.getServletContext().getRealPath("/");
				int result = userKit.Initialize(rootPath+ConstantsUtils.CMBC_PAY_API_SM, ConstantsUtils.CMBC_PAY_API_PASS ,rootPath+ConstantsUtils.CMBC_PAY_API_CER);
			
				//版本号|订单号|交易金额|币种|交易日期|交易时间|商户代码|商户名称|二级商户号|通知地址|跳转地址|银行卡卡号|交易详细内容|商户预留信息|支付通道|借贷标识|商品类型|商品名称|备注
				String apidata = "1.0.0|"+torder.getJno()+"|"+torder.getAmount()+"|156|"+DateUtil.convertDateToString(torder.getCreateTime(), "yyyyMMdd")
				+"|"+DateUtil.convertDateToString(torder.getCreateTime(), "HHmmss")+"|"+ConstantsUtils.CMBC_PAY_API_CORPID+"|"+ConstantsUtils.CMBC_PAY_API_CORPNAME
				+"||http://www.yls77.com/order/pay/cmbc_notify|http://www.yls77.com/mobile/order/list||||2||||";
			
				String orderData = userKit.SignAndEncryptMessage(apidata);
				logger.error("cmbc 加密后数据如下："+apidata);
				map.addAttribute("orderData", orderData);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("cmbc api error："+e.getMessage());
			}
			return "/mobile/cmbcpay_form";
		}
		return "/mobile/pay_failed";
		
	}
	
	/**
	 * 立即支付
	 * @author Max
	 * 
	 */
	@RequestMapping("/dopay{orderId}")
	public String dopay(@PathVariable Integer orderId,String type,HttpServletRequest req,ModelMap map){
		TdUser user = this.getCurrentUser();
		if(null == user){
			return "redirect:404";
		}
		
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
		
		if(null == type){
			if(ConstantsUtils.ORDER_KIND_AGENTPRODUCT.equals(order.getOrderType()) || ConstantsUtils.ORDER_KIND_IMAGEPRODUCT.equals(order.getOrderType()))
			{
				map.addAttribute("order", order);
				return "/mobile/paylist";
			}
		}
		
		
		// 支付时间验证，订单生成24小时内
		Date cur = new Date();
		long temp = cur.getTime() - order.getCreateTime().getTime();
		if (temp > 1000 * 3600 * 24) {
			order.setOrderStatus((byte)-1);
			tdOrderService.save(order);
			return "/mobile/overtime";
		}
		
		// 非待支付订单
		if(order.getOrderStatus() != ConstantsUtils.ORDER_STATUS_NEW){
			//return "redirect:404";
			map.addAttribute("errmsg", "已支付的订单的不能进行支付操作！");
			return "/client/error";
		}
		
		if(ConstantsUtils.ORDER_STATUS_COMPLETE.equals(order.getOrderStatus())){
			map.addAttribute("errmsg", "已支付的订单的不能进行支付操作！");
			return "/client/error";
		}
		
		//已支付的订单的不能进行支付操作
		if(ConstantsUtils.ORDER_PAY_STATUS_PAYED.equals(order.getPayStatus())){
			map.addAttribute("errmsg", "已支付的订单的不能进行支付操作！");
			return "/client/error";
		}
		
		
		Byte pay = order.getPaymentId();
		
		if(ConstantsUtils.ORDER_PAYMENT_ALIPAY.equals(pay)){
			// 支付宝支付
			// 支付金额
			req.setAttribute("totalAmount", order.getPayAmount().toString());
			// 订单编号
			req.setAttribute("orderNo", order.getOrderNo());
			PaymentChannelAlipay paymentChannelAlipay = new PaymentChannelAlipay();
			
			map.addAttribute("payForm", paymentChannelAlipay.getPayFormData(req));
			
			return "/mobile/pay_form";
		}else if(ConstantsUtils.ORDER_PAYMENT_ACCOUNT.equals(pay)){
			// 钱包余额支付
			TdUserAccount account = tdUserAccountService.findByUid(user.getUid());
			// 钱包余额小于支付金额
			if(null == account || order.getPayAmount().compareTo(account.getAmount()) > 0){
				return "/mobile/pay_failed";
			}
			orderAccountPay(order,account);
			map.addAttribute("order", order);
			return "/mobile/pay_success";
			
		}else if(ConstantsUtils.ORDER_PAYMENT_WEIXIN.equals(pay)){
			
			String openId = user.getJointId();
			if(null == openId || openId.contains("sys")){
				return "redirect:404";
			}
			// 微信支付
			Boolean wxPay = wxPay(orderId, openId,"order", req,  map);
			if(null == wxPay){
				return "redirect:404";
			}else if(wxPay){
				return "/mobile/pay_wx";
			}
		}else if(ConstantsUtils.ORDER_PAYMENT_UNIONPAY.equals(pay)){
			CMBCDecryptKit userKit = new CMBCDecryptKit();
	        try {
	        	String rootPath = req.getServletContext().getRealPath("/");
				int result = userKit.Initialize(rootPath+ConstantsUtils.CMBC_PAY_API_SM, ConstantsUtils.CMBC_PAY_API_PASS ,rootPath+ConstantsUtils.CMBC_PAY_API_CER);
			
				//版本号|订单号|交易金额|币种|交易日期|交易时间|商户代码|商户名称|二级商户号|通知地址|跳转地址|银行卡卡号|交易详细内容|商户预留信息|支付通道|借贷标识|商品类型|商品名称|备注
				String apidata = "1.0.0|"+order.getOrderNo()+"|"+order.getPayAmount()+"|156|"+DateUtil.convertDateToString(order.getCreateTime(), "yyyyMMdd")
				+"|"+DateUtil.convertDateToString(order.getCreateTime(), "HHmmss")+"|"+ConstantsUtils.CMBC_PAY_API_CORPID+"|"+ConstantsUtils.CMBC_PAY_API_CORPNAME
				+"||http://www.yls77.com/order/pay/cmbc_notify|http://www.yls77.com/mobile/order/list||||2||||";
			
				String orderData = userKit.SignAndEncryptMessage(apidata);
				logger.error("cmbc 加密后数据如下："+apidata);
				map.addAttribute("orderData", orderData);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("cmbc api error："+e.getMessage());
			}
			return "/mobile/cmbcpay_form";
		}
		return "/mobile/pay_failed";
	}
	
	/**
	 * 
	 * @author Max
	 * 微信支付
	 * 
	 * @param orderId  支付ID
	 * @param openId 
	 * @param type 支付类型
	 * @param req
	 * @param map
	 * @return
	 */
	public Boolean wxPay(Integer orderId,String openId,String type,HttpServletRequest req,ModelMap map){
		//统一支付接口
		
		String body = "";
		String out_trade_no = "";
		int total_fee = 0;
		
		if("order".equalsIgnoreCase(type)){
			// 订单支付
			TdOrder order = tdOrderService.findOne(orderId);
			 
			body = "支付订单" + order.getOrderNo();
			out_trade_no = order.getOrderNo();
			total_fee = (int) Math.round(order.getPayAmount().doubleValue()*100);
		}
		else if("jointOrder".equalsIgnoreCase(type)){
			// 联合订单支付
			TdJointOrder order = tdJointOrderService.findOne(orderId);
			
			body = "支付订单"+order.getJno();
			out_trade_no = order.getJno();
			total_fee = (int) Math.round(order.getAmount().doubleValue()*100);
		}
		
		String noncestr = RandomStringGenerator.getRandomStringByLength(32);
		ModelMap signMap = new ModelMap();
		signMap.addAttribute("appid", Configure.getAppid());
		signMap.addAttribute("attach", "订单支付");
		signMap.addAttribute("body", body);
		signMap.addAttribute("mch_id", Configure.getMchid());
		signMap.addAttribute("nonce_str",noncestr);
		signMap.addAttribute("notify_url", "http://www.yls77.com/mobile/order/wx_notify");
		signMap.addAttribute("openid", openId);
		signMap.addAttribute("out_trade_no", out_trade_no);
		signMap.addAttribute("spbill_create_ip", "120.76.217.106");
		signMap.addAttribute("total_fee", total_fee);
		signMap.addAttribute("trade_type", "JSAPI");

		String mysign = Signature.getSign(signMap);

		String content = "<xml>\n" 
				+ "<appid>"+ Configure.getAppid()+ "</appid>\n"
				+ "<attach>订单支付</attach>\n"
				+ "<body>"+ body+ "</body>\n"
				+ "<mch_id>"+ Configure.getMchid()+ "</mch_id>\n"
				+ "<nonce_str>"+ noncestr+ "</nonce_str>\n"
				+ "<notify_url>http://www.yls77.com/mobile/order/wx_notify</notify_url>\n"
				+ "<openid>" + openId + "</openid>\n" 
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>\n"
				+ "<spbill_create_ip>120.76.217.106</spbill_create_ip>\n"
				+ "<total_fee>" + total_fee+ "</total_fee>\n" 
				+ "<trade_type>JSAPI</trade_type>\n"
				+ "<sign>" + mysign + "</sign>\n"
				+ "</xml>\n";
    	
    	System.err.println("Max:xml-----"+content);
    	
    	String return_code = null;
		String prepay_id = null;
		String result_code = null;
		String line = null;
		HttpsURLConnection urlCon = null;
		try
		{
			urlCon = (HttpsURLConnection) (new URL("https://api.mch.weixin.qq.com/pay/unifiedorder")).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("Content-Length",String.valueOf(content.getBytes().length));
			urlCon.setUseCaches(false);
			urlCon.getOutputStream().write(content.getBytes("utf-8"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

			while ((line = in.readLine()) != null)
			{
				System.out.println(": rline: " + line);
				if (line.contains("<return_code>"))
				{
					return_code = line.replaceAll(
							"<xml><return_code><\\!\\[CDATA\\[", "")
							.replaceAll("\\]\\]></return_code>", "");
				} 
				else if (line.contains("<prepay_id>")) 
				{
					prepay_id = line.replaceAll("<prepay_id><\\!\\[CDATA\\[",
							"").replaceAll("\\]\\]></prepay_id>", "");
				}
				else if (line.contains("<result_code>"))
				{
					result_code = line.replaceAll(
							"<result_code><\\!\\[CDATA\\[", "").replaceAll(
									"\\]\\]></result_code>", "");
				}
			}

			System.out.println("Max: return_code: " + return_code + "\n");
			System.out.println("Max: prepay_id: " + prepay_id + "\n");
			System.out.println("Max: result_code: " + result_code + "\n");

			if ("SUCCESS".equalsIgnoreCase(return_code)
					&& "SUCCESS".equalsIgnoreCase(result_code)
					&& null != prepay_id)
			{
				noncestr = RandomStringGenerator.getRandomStringByLength(32);

				String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
				String packageString = "prepay_id=" + prepay_id;
				String signType = "MD5";
				ModelMap returnsignmap = new ModelMap();
				returnsignmap.addAttribute("appId", Configure.getAppid());
				returnsignmap.addAttribute("timeStamp", timeStamp);
				returnsignmap.addAttribute("nonceStr", noncestr);
				returnsignmap.addAttribute("package", packageString);
				returnsignmap.addAttribute("signType", signType);

				
				String returnsign = Signature.getSign(returnsignmap);
				content = "<xml>\n" + 
				"<appid>" + Configure.getAppid() + "</appid>\n" + 
				"<nonceStr>" + noncestr + "</nonceStr>\n" + 
				"<package>" + packageString + "</package>\n" + 
				"<signType>" + signType + "</signType>\n" + 
				"<signType>" + returnsign + "</signType>\n" + 
				"<timeStamp>" + timeStamp + "</timeStamp>\n" +
				"</xml>\n";

				System.err.print("Max: returnPayData xml=" + content);
				map.addAttribute("appId", Configure.getAppid());
				map.addAttribute("timeStamp", timeStamp);
				map.addAttribute("nonceStr", noncestr);
				map.addAttribute("package", packageString);
				map.addAttribute("signType", signType);
				map.addAttribute("paySign", returnsign);
				
				return true;
			}else{
				return false;
			}
			
		}
		catch (IOException e)
		{
			return null;
		}
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
        // 系统配置
        map.addAttribute("system", getSystem());
        
        // 获取支付宝的返回参数
        String orderNo = "";
        String trade_status = "";
//        String trade_no = null;
        try {
            // 商户订单号
            orderNo = new String(req.getParameter(Constants.KEY_OUT_TRADE_NO)
                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
            // 交易状态
            trade_status = new String(req.getParameter("trade_status")
                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
//            trade_no = new String(req.getParameter("trade_no")
//                    .getBytes("ISO-8859-1"), AlipayConfig.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        String ua = req.getHeader("User-Agent");
        // 判断支付类型，含J为联合订单支付
        if(orderNo.contains("J")){
        	TdJointOrder jointOrder = tdJointOrderService.findByJno(orderNo);
        	
        	if (jointOrder == null) {
        		// 订单不存在
        		// 触屏
        		if(WebUtils.checkAgentIsMobile(ua)){
        			return "/mobile/pay_failed";
        		}
        		return "/client/order_pay_failed";
        	}
        	map.put("order", jointOrder);
        	if (verify_result) {// 验证成功
        		if ("TRADE_SUCCESS".equals(trade_status)) {
        			
        			// 订单支付成功
        			tdOrderService.AfterJointPaySuccess(jointOrder,params.toString());
        			// 触屏
        			if(WebUtils.checkAgentIsMobile(ua)){
        				return "/mobile/pay_success";
        			}
        			return "/client/order_pay_success";
        		}
        	}
        }else{
        	TdOrder order = tdOrderService.findByOrderNo(orderNo);
        	if (order == null) {
        		// 订单不存在
        		// 触屏
        		
        		if(WebUtils.checkAgentIsMobile(ua)){
        			return "/mobile/pay_failed";
        		}
        		return "/client/order_pay_failed";
        	}
        	map.put("order", order);
        	if (verify_result) {// 验证成功
        		if ("TRADE_SUCCESS".equals(trade_status)) {
        			
        			// 订单支付成功
        			tdOrderService.AfterPaySuccess(order,params.toString());
        			// 触屏
        			if(WebUtils.checkAgentIsMobile(ua)){
        				return "/mobile/pay_success";
        			}
        			return "/client/order_pay_success";
        		}
        	}
        }

        // 验证失败或者支付失败
        // 触屏
        if(WebUtils.checkAgentIsMobile(ua)){
            return "/mobile/pay_failed";
        }
        return "/client/order_pay_failed";
    }
	
    @RequestMapping(value = "/wx_notify")
    public void wx_notify(HttpServletResponse response,HttpServletRequest request) throws IOException
    {
    	System.out.println("Max: 回调方法触发！\n");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

		String line = null;
		String return_code = null;
		String result_code = null;
//		String noncestr = null;
		String out_trade_no = null;
		StringBuffer respText = new StringBuffer();

		try {
			while ((line = br.readLine()) != null) {
				respText.append(line);
				if (line.contains("<return_code>")) {
					return_code = line.replaceAll("<return_code><\\!\\[CDATA\\[", "") .replaceAll("\\]\\]></return_code>", "");
				} else if (line.contains("<out_trade_no>")) {
					out_trade_no = line.replaceAll("<out_trade_no><\\!\\[CDATA\\[", "").replaceAll("\\]\\]></out_trade_no>", "");
				} else if (line.contains("<result_code>")) {
					result_code = line.replaceAll("<result_code><\\!\\[CDATA\\[", "").replaceAll("\\]\\]></result_code>", "");
				}
			}
			logger.error("wechat pay xml:"+respText.toString());
			String resonx = new String(respText.toString().getBytes("ISO-8859-1"),"UTF-8");
			logger.error("wechat pay xml:"+resonx);
			if (return_code.contains("SUCCESS") && 
					result_code.contains("SUCCESS") && 
					null != out_trade_no)
			{
				// 联合订单支付
				if(out_trade_no.contains("J")){
		        	TdJointOrder jointOrder = tdJointOrderService.findByJno(out_trade_no);
		        	if (jointOrder != null) {
		        		// 订单支付成功
		        		tdOrderService.AfterJointPaySuccess(jointOrder,resonx);
		        	}
		        }else{
		        	TdOrder order = tdOrderService.findByOrderNo(out_trade_no);
		        	if (null != order)
		        	{
		        		tdOrderService.AfterPaySuccess(order,resonx);
		        	}
		        }
				
				String content = "<xml>\n"
						+ "<result_code>SUCCESS</result_code>\n"
						+ "<return_code></return_code>\n"
						+ "</xml>\n";

				System.out.print("Max: return xml=" + content + "\n");

				try {
					// 把xml字符串写入响应
					byte[] xmlData = content.getBytes();

					response.setContentType("text/xml");
					response.setContentLength(xmlData.length);

					ServletOutputStream os = response.getOutputStream();
					os.write(xmlData);

					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("wechat pay error:"+e.getMessage());
		} finally {
			br.close();
		}
    }
    
    /**
	 * 民生银行支付异步通知
	 * @param map
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/pay/cmbc_notify")
    public String cmbc_notify( ModelMap map, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = req.getParameterMap();
        // 系统配置
        map.addAttribute("system", getSystem());
        
        String ua = req.getHeader("User-Agent");
        
        String paramstr = req.getQueryString();
        logger.error("cmbc notity:"+paramstr);
        if(StringUtils.isNotBlank(paramstr)){
        	String[] param = paramstr.split("\\|");
        	String orderNo = param[0];
        	String billstatus = param[6];
        	String amount = param[3];
        	BigDecimal oamount = new BigDecimal(amount);
        	// 判断支付类型，含J为联合订单支付
            if(orderNo.contains("J")){
            	TdJointOrder jointOrder = tdJointOrderService.findByJno(orderNo);
            	if (jointOrder == null) {
            		logger.error("cmbc notity error order not exist:"+paramstr);
            		throw new Exception("order not exist!");
            	}
            	map.put("order", jointOrder);
            	if (billstatus.equals("0")&&jointOrder.getAmount().equals(oamount)) {// 验证成功
            			// 订单支付成功
            			tdOrderService.AfterJointPaySuccess(jointOrder,paramstr);
            			// 触屏
            			if(WebUtils.checkAgentIsMobile(ua)){
            				return "/mobile/pay_success";
            			}
            			return "/client/order_pay_success";
            	}else{
            		throw new Exception("pay error");
            	}
            }else{
            	TdOrder order = tdOrderService.findByOrderNo(orderNo);
            	if (order == null) {
            		logger.error("cmbc notity error order not exist:"+paramstr);
            		throw new Exception("order not exist!");
            	}
            	map.put("order", order);
            	if (billstatus.equals("0") && order.getPayAmount().equals(oamount)) {// 验证成功
        			// 订单支付成功
        			tdOrderService.AfterPaySuccess(order,params.toString());
        			// 触屏
        			if(WebUtils.checkAgentIsMobile(ua)){
        				return "/mobile/pay_success";
        			}
        			return "/client/order_pay_success";
            	}
            }
        }
        
        // 验证失败或者支付失败
        // 触屏
        if(WebUtils.checkAgentIsMobile(ua)){
            return "/mobile/pay_failed";
        }
        return "/client/order_pay_failed";
    }
    
    /**
     * 
     * @author Max
     * 余额付
     * 
     */
    private void orderAccountPay(TdOrder order, TdUserAccount account) {
		if(null ==order || null == account){
			return ;
		}
		
		BigDecimal payAmount = order.getPayAmount();
		BigDecimal amount = account.getAmount();
		BigDecimal decimal = new BigDecimal(0.00);
	
		
		// 记录
		TdUserAccountLog log = new TdUserAccountLog();
		log.setUpamount(BigDecimal.ZERO.subtract(payAmount));//支付转换为负数，账号金额支出减少
		log.setType(TdUserAccountLog.USERACCOUNTLOG_TYPE_ORDER_PAY);
		log.setCreateTime(new Date());
		log.setNote("订单:"+order.getOrderNo()+"支付");
		
		tdUserAccountService.addAmount(account, log);
		
		tdOrderService.AfterPaySuccess(order,"");
	}
    
    
    /**
     * 
     * @author Max
     * 联合订单余额后续
     * 
     */
    private void jointOrderAccountPay(TdJointOrder torder, TdUserAccount account) {
    	if(null ==torder || null == account){
			return ;
		}
    	
    	TdOrderSearchCriteria sc = new TdOrderSearchCriteria();
    	sc.setFlag(false);
    	sc.setJointId(torder.getId());
    	List<TdOrder> orderList = tdOrderService.findBySearchCriteria(sc);
    	
    	BigDecimal payAmount = torder.getAmount();
		BigDecimal amount = account.getAmount();
		BigDecimal decimal = new BigDecimal(0.00);
		
		account.setAmount(amount.subtract(payAmount));
		
		// 记录
		TdUserAccountLog log = new TdUserAccountLog();
		log.setUpamount(decimal.subtract(payAmount));
		log.setCreateTime(new Date());
		log.setType((byte)5);
		log.setNote("订单支付");
		
		tdUserAccountService.addAmount(account, log);
    	
    	if(null != orderList && orderList.size() > 0)
    	{
    		for (TdOrder tdOrder : orderList) {
    			tdOrderService.AfterPaySuccess(tdOrder,"");
    		}
    	}
		
	}
}
