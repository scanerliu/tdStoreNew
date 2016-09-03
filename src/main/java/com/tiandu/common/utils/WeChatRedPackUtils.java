package com.tiandu.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.tiandu.custom.vo.WeChatRedPackRequest;
import com.tiandu.custom.vo.WeChatRedPackResponse;
import com.tiandu.custom.vo.WithDrawVO;
import com.tiandu.order.vo.OperResult;
import com.tiandu.product.vo.AttributeOptionsVO;

public class WeChatRedPackUtils {

	private final static Logger logger = Logger.getLogger(WeChatRedPackUtils.class);
	
	private final static String sendredpackapi = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	private final static String wishing = "一路上有你，创业更轻松！";
	private final static String act_name = "创客联盟";
	private final static String remark = "一路上有你，创业更轻松！";
	private final static String send_name = "创客联盟";
	private final static String scene_id = "PRODUCT_5";
	
	public static OperResult sendRedPack(WithDrawVO withDraw){
		OperResult result = new OperResult();
		/*StringBuffer xml = new StringBuffer();
		xml.append("<xml>");
		xml.append("<sign><![CDATA[E1EE61A91C8E90F299DE6AE075D60A2D]]></sign>");
		xml.append("<mch_billno><![CDATA[0010010404201411170000046545]]></mch_billno>");
		xml.append("<mch_id><![CDATA[888]]></mch_id><wxappid><![CDATA[wxcbda96de0b165486]]></wxappid>");
		xml.append("<send_name><![CDATA[send_name]]></send_name>");
		xml.append("<re_openid><![CDATA[onqOjjmM1tad-3ROpncN-yUfa6uI]]></re_openid>");
		xml.append("<total_amount><![CDATA[200]]></total_amount>");
		xml.append("<total_num><![CDATA[1]]></total_num>");
		xml.append("<wishing><![CDATA[恭喜发财]]></wishing>");
		xml.append("<client_ip><![CDATA[127.0.0.1]]></client_ip>");
		xml.append("<act_name><![CDATA[新年红包]]></act_name>");
		xml.append("<remark><![CDATA[新年红包]]></remark>");
		xml.append("<scene_id><![CDATA[PRODUCT_2]]></scene_id>");
		xml.append("<consume_mch_id><![CDATA[10000097]]></consume_mch_id>");
		xml.append("<nonce_str><![CDATA[50780e0cca98c8c8e814883e5caa672e]]></nonce_str>");
		xml.append("<risk_info>posttime%3d123123412%26clientversion%3d234134%26mobile%3d122344545%26deviceid%3dIOS</risk_info>");
		xml.append("</xml>");*/
		WeChatRedPackRequest request = new WeChatRedPackRequest();
		request.setAct_name(act_name);
		request.setWishing(wishing);
		request.setRemark(remark);
		request.setMch_billno(WebUtils.generateOrderNo());
		request.setWxappid(ConstantsUtils.WECHAT_APPID);
		request.setMch_id(ConstantsUtils.WECHAT_MCH_ID);
		request.setSend_name(send_name);
		request.setRe_openid(withDraw.getOpenId());
		request.setTotal_amount(withDraw.getAmount().multiply(new BigDecimal(100)).intValue());
		request.setTotal_num(1);
		request.setWishing(wishing);
		request.setClient_ip(withDraw.getClientIp());
		request.setScene_id(scene_id);
		request.setNonce_str(WebUtils.generateOrderNo());
		String sign = getSign(request);
		request.setSign(sign);
		//s生成xmlstring
		XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_"))); 
		xstream.processAnnotations(request.getClass());
		String xml = xstream.toXML(request);
		logger.error("wechat redpack send request xml:"+xml);
		//调微信接口
		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> codesponse = restTemplate.getForEntity(sendredpackapi, String.class);
//		System.out.println(codesponse.getBody());
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("text/html;charset=utf-8");
        headers.setContentType(type);
        HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);

        String response = null;
        try {
			response = restTemplate.postForObject(sendredpackapi, formEntity, String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			logger.error("wechat redpack send request error:"+e.getMessage() + " [xml]:"+xml);
		}
        if(null!=response){
        	logger.error("wechat redpack send response xml:"+response);
            //将xml装好为 WeChatRedPackResponse 对象
            try {
				xstream.processAnnotations(WeChatRedPackResponse.class);
				WeChatRedPackResponse wprespose = (WeChatRedPackResponse) xstream.fromXML(response);
				if(wprespose.getReturn_code().equals("SUCCESS")){//通信标识成功
					if(wprespose.getResult_code().equals("SUCCESS")){
						result.setFlag(true);
					}else{
						result.setFailMsg(wprespose.getErr_code_des());
					}
				}else{
					result.setFailMsg("发送红包失败："+wprespose.getReturn_msg());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("wechat redpack response error:"+e.getMessage() + " [xml]:"+response);
			}
        }
		return result;
	}
	
	/**
	 * 通过类属性反射获得签名
	 * @param request
	 * @return
	 */
	private static String getSign(WeChatRedPackRequest request){
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new TreeMap<String, String>();
		Field[] field = request.getClass().getDeclaredFields();
		for(int j=0 ; j<field.length ; j++){     //遍历所有属性
			String name = field[j].getName();    //获取属性的名字
			try {
				String upName = name.substring(0,1).toUpperCase()+name.substring(1);
				Method m = request.getClass().getMethod("get"+upName);
				String value = String.valueOf(m.invoke(request));
				if(StringUtils.isNotBlank(value)){
					map.put(name, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("获取"+request.toString()+"加密参数错误："+e.getMessage());
			}
		}
		Map<String, String> resultMap = sortMapByKey(map);	//按Key进行排序
		for (Map.Entry<String, String> entry : resultMap.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		sb.append("key="+ConstantsUtils.WECHAT_APPSECRET);
		String sign = MD5.md5(sb.toString()).toUpperCase();
		return sign;
	}
	
	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	private static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
	
	public static void main(String[] args) throws ParseException {
		WithDrawVO draw = new WithDrawVO();
		draw.setOpenId("tests");
		draw.setClientIp("testsip");
		draw.setAmount(new BigDecimal(10));
		sendRedPack(draw);
	}

}
