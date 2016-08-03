package com.tiandu.payment.alipay;

import static com.tiandu.payment.util.PaymentUtil.getServerPath;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiandu.alipay.util.AlipayNotify;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.service.TdOrderPayService;
import com.tiandu.order.service.TdOrderService;
import com.tiandu.payment.PaymentChannel;
import com.tiandu.payment.core.AlipaySubmit;

@Service
public class PaymentChannelAlipay implements PaymentChannel {
    private static final String OUT_TRADE_NO_PARA = "out_trade_no";
    private static final String ORDER_NO_TB_PARA = "trade_no";
    private static final String ORDER_STATUS_PARA = "trade_status";
    private static final String ISO_ENCODING = "ISO-8859-1";

//    private static final String NEW_LINE = System.getProperty("line.seperator",
//            "\n");

    /*
     * 
     */
    private static final Logger paymentLogger = Logger.getLogger("paymentApi");
    
    
    @Autowired
    private TdOrderService tdOrderService;

    @Autowired
    private TdOrderPayService tdOrderPayService;
    
    @Override
    public String getPayFormData(HttpServletRequest request) {
        Map<String, String> requestParameters = new HashMap<String, String>();
        
        
        // 类型，添加支付名称
        String type = (String) request.getAttribute("type");
        if(null != type){
        	requestParameters.put(Constants.KEY_SERVICE, AlipayConfig.CREATE_MOBILE_SERVICE);
        	requestParameters.put(Constants.KEY_SHOW_URL, "www.chinacslm.cc/touch");
        }else{
        	requestParameters.put(Constants.KEY_SERVICE, AlipayConfig.CREATE_TRADE_SERVICE);
        }
        
        // 身份ID
        requestParameters.put(Constants.KEY_PARTNER, AlipayConfig.PARTNER);
        
        // 通知地址
        String serverPath = getServerPath(request);
        requestParameters.put(Constants.KEY_NOTIFY_URL, serverPath + "/mobile/order/pay/notify_alipay");
        requestParameters.put(Constants.KEY_RETURN_URL, serverPath + "/mobile/order/pay/result_alipay");
       
        // 编码
        requestParameters.put(Constants.KEY_CHARSET, AlipayConfig.CHARSET);
        //支付记录号码
        String payRecordId = (String) request.getAttribute("payRecordId");
        
        String orderId = (String) request.getAttribute("orderNo");
        // 商品名称，此处以订单号作为商品名称
        requestParameters.put(Constants.KEY_SUBJECT, orderId);
        
        // 订单号
        if(null != payRecordId)
        {
        	requestParameters.put(Constants.KEY_OUT_TRADE_NO, orderId + payRecordId);
        }else{
        	requestParameters.put(Constants.KEY_OUT_TRADE_NO, orderId);
        }
        // 支付类型 仅能为1（购买）
        requestParameters.put(Constants.KEY_PAYMENT_TYPE, AlipayConfig.PAYMENT_TYPE);

        // 订单金额取得
        String orderAmount = (String) request.getAttribute("totalAmount");
        /*
         * price、quantity能代替total_fee。
         * 即存在total_fee，就不能存在price和quantity；
         * 存在price、quantity，就不能存在total_fee
         * 
         */
        requestParameters.put(Constants.KEY_TOTAL_FEE, orderAmount); // 交易金额
        
        /*
         *  卖家支付宝
         *  此处可以用seller_id，seller_email，seller_account_name三个参数
         *  优先级递减
         */
        requestParameters.put(Constants.KEY_SELLER_ID, AlipayConfig.SELLER_ID);
        return AlipaySubmit.buildRequestForm(requestParameters,
                Constants.METHOD_POST, Constants.PAY_BUTTON_NAME);
    }

    @Override
    public void doResponse(HttpServletRequest request, HttpServletResponse resp) {
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        StringBuilder loggeMessage = new StringBuilder();
        for (Iterator<String> iter = requestParams.keySet().iterator(); 
                iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            loggeMessage.append(name).append("=").append(valueStr).append("&");
            params.put(name, valueStr);
        }
        // 消息记录处理
        loggeMessage.setLength(loggeMessage.length() - 1);

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        // 商户订单号
        String orderNo = null;

        // 支付宝交易号
        String trade_no = null;

        // 交易状态
        String trade_status = null;
        try {
            orderNo = new String(request.getParameter(OUT_TRADE_NO_PARA)
                    .getBytes(ISO_ENCODING), AlipayConfig.CHARSET);
            trade_no = new String(request.getParameter(ORDER_NO_TB_PARA)
                    .getBytes(ISO_ENCODING), AlipayConfig.CHARSET);
            trade_status = new String(request.getParameter(ORDER_STATUS_PARA)
                    .getBytes(ISO_ENCODING), AlipayConfig.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        
        PrintWriter out = null;
        try {
            paymentLogger.info(String.format("AlipayNotify:{%s}.", loggeMessage.toString()));
            // 获取支付宝的通知返回参数
            // 根据状态调用何时接口操作订单状态
            out = resp.getWriter();
            if (AlipayNotify.verify(params)) {// 验证成功
                paymentLogger.info("AlipayNotify:Accepted!");

                	TdOrder order = tdOrderService.findByOrderNo(orderNo);
                	if (OrderStatus.WAIT_PAY.equals(trade_status)) {
                		// 该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
                		
                		// 判断该笔订单是否在商户网站中已经做过处理
                		// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                		// 如果有做过处理，不执行商户的业务程序
                		paymentLogger.info(String.format(
                				"AlipayNotify:{%s}支付宝订单已经生成,用户未付款!",
                				orderNo));
                		if(order != null && order.getPayStatus() == 1) {
//                			order.setStatusId(2l);
                			tdOrderService.save(order);
                		}
                	} else if (OrderStatus.WAIT_SEND_GOODS.equals(trade_status)) {
                		// 该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
                		
                		// 判断该笔订单是否在商户网站中已经做过处理
                		// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                		// 如果有做过处理，不执行商户的业务程序
                		paymentLogger.info(String.format("AlipayNotify:{%s}用户已经付款给支付宝,系统自动发货!",
                				orderNo));
//                		if(order != null && (order.getStatusId() == 2 || order.getStatusId() == 8)) {
//                			order.setStatusId(3l);
//                			order.setPayTime(new Date());
//                			orderService.save(order);
//                		}
                		
                		
//                		sendConfirmGoods(FIRST_TIME, trade_no);
                	} else if (OrderStatus.WAIT_ONFIRM_GOODS.equals(trade_status)) {
                		// 该判断表示卖家已经发了货，但买家还没有做确认收货的操作
                		
                		// 判断该笔订单是否在商户网站中已经做过处理
                		// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                		// 如果有做过处理，不执行商户的业务程序
                		paymentLogger.info(String.format("AlipayNotify:{%s}等待买家确认收货!", 
                				orderNo));
//                		if(order != null && (order.getStatusId() == 3 || order.getStatusId() == 8)) {
//                			order.setStatusId(4l);
//                			order.setDeliveryTime(new Date());
//                			orderService.save(order);
//                		}
                	} else if (OrderStatus.FINISHED.equals(trade_status)) {
                		// 该判断表示买家已经确认收货，这笔交易完成
                		
                		// 判断该笔订单是否在商户网站中已经做过处理
                		// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                		// 如果有做过处理，不执行商户的业务程序
                		paymentLogger.info(String.format("AlipayNotify:{%s}用户确认收货完毕,支付款入帐门订单成功!", 
                				orderNo));
//                		if(order != null && (order.getStatusId() == 4 || order.getStatusId() == 8)) {
//                			order.setStatusId(5l);
//                			order.setFinishTime(new Date());
//                			orderService.save(order);
//                		}
                	} else {
                		// 交易中途结束
                		paymentLogger.info(String.format("AlipayNotify:{%s}订单中途取消,支付失败S", orderNo));
//                		if(order != null && (order.getStatusId() != 8)) {
//                			order.setStatusId(8l);
//                			order.setCancelTime(new Date());
//                			orderService.save(order);
//                		}
//                		
                	}
                out.println("success");// 请不要修改或删除
            } else {// 验证失败
                paymentLogger.info("AlipayNotify:Rejected!");
                out.println("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                out.close();
            }
        }
    }

//    private boolean sendConfirmGoods(int timeCount, String trade_no) {
//        try {
//            @SuppressWarnings({ "unchecked", "rawtypes" })
//            String result = (String)post(AlipayConfig.REAUESTURL,
//                    AlipayConfirmGoods.generatNameValuePair(trade_no),
//                    new AlipayConfirmGoodsHandler(), AlipayConfig.CHARSET);
//            paymentLogger.info(String.format("AlipayConfirmGoods:%s%s!", NEW_LINE, result));
//
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//            if (timeCount < MAX_TIME) {
//                sendConfirmGoods(timeCount++, trade_no);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            if (timeCount < MAX_TIME) {
//                sendConfirmGoods(timeCount++, trade_no);
//            }
//        }
//        return false;
//    }

    static class OrderStatus {
        // 买家已在支付宝交易管理中产生了交易记录，但没有付款
        public static String WAIT_PAY = "WAIT_BUYER_PAY";
        // 买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
        public static String WAIT_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
        // 卖家已经发了货，但买家还没有做确认收货的操作
        public static String WAIT_ONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
        // 买家已经确认收货，这笔交易完成
        public static String FINISHED = "TRADE_FINISHED";
    }
}
