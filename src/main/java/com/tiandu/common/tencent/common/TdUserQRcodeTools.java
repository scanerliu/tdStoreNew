package com.tiandu.common.tencent.common;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.common.utils.DateUtil;
import com.tiandu.common.utils.QRCodeImageUtils;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.system.entity.TdSystemConfig;
import com.tiandu.system.service.TdSystemConfigService;

public class TdUserQRcodeTools {
	
	//连接超时时间，默认10秒
    private static int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private static int connectTimeout = 30000;
    
    //图片大小
    private static int pictureSize = 120;
    
    private final Logger logger = Logger.getLogger(TdUserQRcodeTools.class);
    
    @Autowired
    private TdSystemConfigService tdSystemConfigService;
    
    @Autowired
    private TdUserService tdUserService;
    
    public void setTdSystemConfigService(TdSystemConfigService tdSystemConfigService) {
		this.tdSystemConfigService = tdSystemConfigService;
	}

	public void setTdUserService(TdUserService tdUserService) {
		this.tdUserService = tdUserService;
	}

	/**
     * 获取有效的accessToken
     * @return
     */
    public String validAccessToken()
    {
    	Date currenDate				= new Date();
    	String accessToken;
    	TdSystemConfig tokenConfig	= tdSystemConfigService.findByKey(ConstantsUtils.K_ACCESSTOKEN);
    	Long timelimit = (long)7100 * (long)1000;
    	if(tokenConfig == null)
    	{
    		accessToken	= getAccessToken();
    		tokenConfig = new TdSystemConfig();
    		tokenConfig.setConfigKey(ConstantsUtils.K_ACCESSTOKEN);
    		tokenConfig.setConfigType(4);
    		tokenConfig.setDataType((byte) 2);
    		tokenConfig.setConfigValue(accessToken);
    		tokenConfig.setUpdateBy(0);
    		tdSystemConfigService.save(tokenConfig);
    	}
    	else if (StringUtils.isBlank(tokenConfig.getConfigValue()) || timelimit.compareTo(currenDate.getTime() - tokenConfig.getUpdateTime().getTime()) <0)
    	{
    		accessToken = getAccessToken();
    		tokenConfig.setConfigValue(accessToken);
    		tdSystemConfigService.save(tokenConfig);
    		
    	}
    	else
    	{
    		accessToken = tokenConfig.getConfigValue();
    	}
    	return accessToken;
    }
	
	/**
	 * 获取AccessToken
	 * @return
	 */
	public static String getAccessToken()
	{
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Configure.getAppid()+ "&secret=" + Configure.getAppSecret();
		try
		{
            URL urlGet				= new URL(url);
            HttpURLConnection http	= (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            
            InputStream inputStream	= http.getInputStream();
            int size				= inputStream.available();
            byte[] sizeBytes		= new byte[size];
            inputStream.read(sizeBytes);
            String message			= new String(sizeBytes, "UTF-8");
            
            JSONObject resultJson	= new JSONObject(message);
            String accessToken		= resultJson.getString("access_token");
//            String expiresIn		= resultJson.getString("expires_in");
            inputStream.close();
            return accessToken;

        }
		catch (Exception e)
		{
            e.printStackTrace(); 
        }
		return null;
	}
	
	/**
	 * * 获取二维码Url
	 * @param accessToken 微信凭证
	 * @param Uid 用户id
	 * @return 二维码Url
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	public String getQRcodeUrl(String accessToken,Integer Uid) throws JSONException, UnsupportedEncodingException
	{
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		url = url.replace("TOKEN", accessToken);
		
		String params = "{\"expire_seconds\": 2592000, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + Uid +"}}}";
		String ticketStr = sendPost(url, params);
		JSONObject resultJson	= new JSONObject(ticketStr);
//		String ticket = resultJson.getString("ticket");
		String QRcodeUrl = resultJson.getString("url");
		return QRcodeUrl;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param jsonStr
	 * @return 请求结果
	 * @throws UnsupportedEncodingException
	 */
	public String sendPost(String url,String jsonStr) throws UnsupportedEncodingException
	{
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        String result = null;

        HttpPost httpPost = new HttpPost(url);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(jsonStr, "UTF-8");
        httpPost.addHeader("Content-Type", "text/json");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        Util.log("executing request" + httpPost.getRequestLine());

        try {
        	CloseableHttpClient httpClientCustom = HttpClients.custom().build();
            HttpResponse response = httpClientCustom.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
        	logger.error("微信接口：http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
        	logger.error("微信接口：http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
        	logger.error("微信接口：http get throw SocketTimeoutException");

        } catch (Exception e) {
        	logger.error("微信接口：http get throw Exception");

        } finally {
            httpPost.abort();
        }

        return result;
	}
	
	/**
     * 生成二维码
     * @param content url
     * @param size 二维码大小，像素值
     * @param response
     */
    public void getQRCode(String content, String title, TdUser user, int size, HttpServletRequest request,HttpServletResponse response) {
    	//项目跟路径
    	String rootPath = request.getServletContext().getRealPath("/");
    	//推广背景图片
    	String targetImg = rootPath+"/static/default/images/shareback.jpg";
    	
    	//生成二维码图片
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        
        hints.put(EncodeHintType.MARGIN, 0);
        
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        for (int x = 0; x < width; x++) {
             for (int y = 0; y < height; y++) {
                   image.setRGB(x, y, bitMatrix.get(x, y) == true ? 
                   Color.BLACK.getRGB():Color.WHITE.getRGB());
            }
        }
       
        try {
	        //过期时间
	        Date expireDate = DateUtil.getAddDay(user.getQrcodeUpdate(), 29);
			String expire = DateUtil.convertDateToString(expireDate);
			//头像
			BufferedImage headimage = null;
			if(StringUtils.isBlank(user.getUavatar())){
				user.setUavatar(ConstantsUtils.DEFAULT_USER_AVATAR);
			}
			if(user.getUavatar().indexOf("http:")==0){
				headimage = ImageIO.read(new URL(user.getUavatar()));
			}else{
				try {
					headimage = ImageIO.read(new File(rootPath+user.getUavatar()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					headimage = ImageIO.read(new File(rootPath+ConstantsUtils.DEFAULT_USER_AVATAR));
				}
			}
			//标题
			if(StringUtils.isBlank(title)){
				title = "一路上有你，让创业更轻松！";
			}
			//生成推广图片
	        BufferedImage spreadimage = QRCodeImageUtils.genernateQRCodeImage(headimage, image, targetImg, user.getUnick(), title, expire);
	        //输出图片
            ImageIO.write(spreadimage, "png", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 根据Uid获取用户分享二维码Url
     * @param uId
     * @return
     * @throws UnsupportedEncodingException
     * @throws JSONException
     */
    public String validQRcodeUrl(Integer uId) throws UnsupportedEncodingException, JSONException
    {
    	String qrcodeUrl = null;
    	if(uId == null)
    	{
    		return null;
    	}
    	TdUser user = tdUserService.findOne(uId);
    	
    	if(user == null)
    	{
    		return null;
    	}
    	else if(isValidDate(user.getQrcodeUpdate()))
    	{
    		return user.getQrcodeUrl();
    	}
    	else
    	{
    		qrcodeUrl = this.getQRcodeUrl(this.validAccessToken(), uId);
    		user.setQrcodeUrl(qrcodeUrl);
    		user.setQrcodeUpdate(new Date());
    		tdUserService.saveCustomer(user);
    	}
    	return qrcodeUrl;
    }
    
    public void QRcodeByUidAndResponse(Integer uId, String title, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, JSONException
    {
//    	String QRcodeUrl = this.validQRcodeUrl(uId);
//    	this.getQRCode(QRcodeUrl, pictureSize, response);
    	String qrcodeUrl = null;
    	if(uId != null && uId >0)
    	{
    		TdUser user = tdUserService.findOne(uId);
    		if(user != null)
        	{
    			if(isValidDate(user.getQrcodeUpdate()))
            	{
            		qrcodeUrl = user.getQrcodeUrl();
            	}
            	else
            	{
            		qrcodeUrl = this.getQRcodeUrl(this.validAccessToken(), uId);
            		user.setQrcodeUrl(qrcodeUrl);
            		user.setQrcodeUpdate(new Date());
            		tdUserService.saveCustomer(user);
            	}
    			this.getQRCode(qrcodeUrl, title, user, pictureSize,request, response);
        	}
    		
    	}
    	
    }
    
    
    /**
     * 时间是否有效 30天有效
     * @param date
     * @return
     */
    public Boolean isValidDate(Date date)
    {
    	if(date == null)
    	{
    		return false;
    	}
    	
    	Date currentDate = new Date();
    	Long distirct = (long)2591400 *(long)1000;
    	Long dute = currentDate.getTime() - date.getTime();
    	return dute < distirct ? true : false;
    }
    
    

}
