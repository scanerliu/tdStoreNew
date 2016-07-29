package com.tiandu.system.utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiandu.system.service.TdSystemConfigService;

/**
 * 提供系统配置的统一的使用接口。Java程序内直接调用各个API。
 * @author liuxinbing
 *
 */
public class ConfigUtil {

	private  static ConfigUtil configUtil = new ConfigUtil();

	private static final Logger logger = Logger.getLogger(ConfigUtil.class);

	@Resource
	private TdSystemConfigService tdSystemConfigService;
	
	public void setTdSystemConfigService(TdSystemConfigService tdSystemConfigService) {
		this.tdSystemConfigService = tdSystemConfigService;
	}

	public static ConfigUtil getInstance() {
		if(configUtil==null){
			configUtil=new ConfigUtil();
		}
		return configUtil;
	}

	private ConfigUtil() {
	}

	protected final String getConfig(String key) {
		String confValue = tdSystemConfigService.getConfigMap().get(key);
		if (confValue == null) {
			logger.warn("Requesting config not found， register a default config. Config key is:[" + key + "].");
		}
		return confValue;
	}

	protected final Integer getConfig(String key, int defValue) {
		return new Integer(getConfig(key, "" + defValue));
	}

	protected final String getConfig(String key, String defValue) {
		String configValue = getConfig(key);
		return StringUtils.isNotBlank(configValue)?configValue:defValue;
	}

	protected final boolean getConfigAsBool(String key, boolean defValue) {
		return Boolean.parseBoolean(getConfig(key, "" + defValue));
	}

	protected final double getConfigAsDouble(String key, double defValue) {
		return Double.parseDouble(getConfig(key, "" + defValue));
	}

	protected final int getConfigAsInt(String key, int defValue) {
		return Integer.parseInt(getConfig(key, "" + defValue));
	}

	/**
	 * 上传媒体文件时候的根目录
	 * @return
	 */
	public String getMediaUploadPath() {
		return getConfig("ftprootdoc","upload");
	}

	/**
	 * 取得上传媒体文件目录全路径
	 * @param path 文件目录地址，需带/，如"/image/logo"
	 * @return
	 */
	public String getMediaUploadPathFull(String path) {
		String mediaCategoryStr = getMediaUploadPath();
		mediaCategoryStr += path;
		return mediaCategoryStr;
	}

	/**
	 * 图片服务器部署地址
	 */
	public String getImageUrl() {
		return getConfig("imageurl");
	}

	
	/**
	 * 默认日期显示格式，带时分秒的日期格式
	 * @return
	 */
	public String getDateTimePattern() {
		return getConfig("global.dateTimePattern", "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 默认日期显示格式，不带时分秒的日期格式
	 * @return
	 */
	public String getDatePattern() {
		return getConfig("global.datePattern", "yyyy-MM-dd");
	}
	
	/**
	 * 获取ftp服务器ip
	 * @return
	 */
	public String getFtpServerIp(){
		return getConfig("ftpserverip");
	}
	
	/**
	 * 获取ftp服务器端口号
	 * @return
	 */
	public String getFtpServerPort(){
		return getConfig("ftpserverport");
	}
	
	/**
	 * 获取ftp服务器账号
	 * @return
	 */
	public String getFtpUserName(){
		return getConfig("ftpusername");
	}
	
	/**
	 * 获取ftp服务器密码
	 * @return
	 */
	public String getFtpPassword(){
		return getConfig("ftppassword");
	}
	
	/**
	 * 获取下载媒体文件的根目录
	 * @return
	 */
	public String getMediaDownloadPath(){
		return getConfig("ftpdowndoc");
	}
	
	/**
	 *取得下载媒体文件目录全路径
	 * @param path 文件目录地址，需带/，如"/module"
	 * @return
	 */
	public String getMediaDownloadPathFull(String path){
		String mediaCategoryStr = getMediaDownloadPath();
		mediaCategoryStr += path;
		return mediaCategoryStr;
	}
		
	/**
	 * 获取通信加密密匙
	 * @return
	 */
	public String getSignKey(){
		return getConfig("signkey");
	}
	
	/**
	 * 获取短信配置
	 * @return
	 */
	public Map<String,String> getSMSConfig(){
		//第三方短信平台的服务器地址
		String smsresturl = getConfig("smsresturl");
		//开发者主账号
		String smsaccountsid = getConfig("smsaccountsid");
		//开发者主账号对应的令牌
		String smsauthtoken = getConfig("smsauthtoken");
		//应用Id
		String smsappid = getConfig("smsappid","0");
		Map<String,String> co = new HashMap<String,String>();
		co.put("smsresturl", smsresturl);
		co.put("smsaccountsid", smsaccountsid);
		co.put("smsauthtoken", smsauthtoken);		
		co.put("smsappid", smsappid);		
		return co;
	}
	
	
	/**
	 * 获取用户最多连续不登陆月数
	 * 默认1个月
	 * @return
	 */
	public Integer getUserMaxNoLoginMonth(){
		return getConfig("usermaxnologinmonth",1);
	}
	/**
	 * 普通商品积分可抵扣金额比例
	 * @return
	 */
	public Integer getCommonProductPointPercent(){
		return getConfig("commonproductpointpercent",0);
	}
	/**
	 * 部分积分兑换商品可抵扣金额比例
	 * @return
	 */
	public Integer getPartProductPointPercent(){
		return getConfig("partproductpointpercent",0);
	}
	/**
	 * 积分抵扣金额比例
	 * @return
	 */
	public Integer getIntegralExchangerate(){
		return getConfig("integralexchangerate",0);
	}
	/**
	 * 订单金额赠送积分比例
	 * @return
	 */
	public Integer getOrderDeliveryIntegral(){
		return getConfig("orderdeliveryintegral",0);
	}
	/**
	 * 系统是否启用700元的单类代理领取商品包
	 * @return
	 */
	public boolean isAgentProductUsePackage(){
		Integer config = getConfig("integralexchangerate",2);
		if(config.equals(1)){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取美化图片单价
	 * @return
	 */
	public Integer getImageProcessingPrice(){
		return getConfig("imageprocessingprice",5);
	}

}
