package com.tiandu.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.tiandu.system.utils.ConfigUtil;

/*
 * @Author L.Gao
 */
public class MessageSender {
	private String REST_URL;
	private String ACCOUNT_SID;
	private String AUTH_TOKEN;
	private String APP_ID;
	
	private final Logger logger = Logger.getLogger(getClass());

	public boolean init() {
		Map<String, String> smsConfig = ConfigUtil.getInstance().getSMSConfig();
		String smsresturl = smsConfig.get("smsresturl");
		String smsaccountsid = smsConfig.get("smsaccountsid");
		String smsauthtoken = smsConfig.get("smsauthtoken");
		String smsappid = smsConfig.get("smsappid");
		boolean isSuccess;
		if (StringUtils.isEmpty(smsresturl) || StringUtils.isEmpty(smsaccountsid) || StringUtils.isEmpty(smsauthtoken)
				|| StringUtils.isEmpty(smsappid)) {
			isSuccess = false;
		} else {
			this.REST_URL = smsresturl;
			this.ACCOUNT_SID = smsaccountsid;
			this.AUTH_TOKEN = smsauthtoken;
			this.APP_ID = smsappid;
			isSuccess = true;
		}
		return isSuccess;
	}

	public boolean send(List<String> phoneNums, String templateId, List<String> datas) {
		HashMap<String, Object> result = null;
		// 初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		restAPI.init(this.REST_URL, "8883");
		restAPI.setAccount(this.ACCOUNT_SID, this.AUTH_TOKEN);
		restAPI.setAppId(this.APP_ID);
		//发送
		result = restAPI.sendTemplateSMS(formatPhoneNums(phoneNums), templateId,
				datas.toArray(new String[datas.size()]));
		if("000000".equals(result.get("statusCode"))){
			return true;
		}else{
			//异常返回输出错误码和错误信息
			logger.error("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return false;
		}
	}

	public static String formatPhoneNums(List<String> phoneNums) {
		String phoneNumsStr = "";
		if (phoneNums != null) {
			for (int i = 0; i < phoneNums.size(); i++) {
				if (i < phoneNums.size() - 1) {
					phoneNumsStr += phoneNums.get(i) + ",";
				} else {
					phoneNumsStr += phoneNums.get(i);
				}
			}
		}
		return phoneNumsStr;
	}
	
	public static void main(String[] args) {
		MessageSender ms = new MessageSender();
		ms.init();
		List<String> phones = new ArrayList<>();
		phones.add("18323127224");
		phones.add("15818137316");
		List<String> datas = new ArrayList<>();
		datas.add("9999");
		datas.add("1");
		//ms.send(phones, "1", datas);
	}

}
