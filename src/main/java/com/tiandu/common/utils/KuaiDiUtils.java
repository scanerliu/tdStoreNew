package com.tiandu.common.utils;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tiandu.order.vo.PostageResponseDataVO;
import com.tiandu.order.vo.PostageResponseVO;
import com.tiandu.order.vo.PostageVO;

/**
 * 物流查询工具类
 *
 */
public class KuaiDiUtils {

	private final static Logger logger = Logger.getLogger(KuaiDiUtils.class);
	
	private final static String kuaidi_api_url = "http://poll.kuaidi100.com/poll/query.do";
	private final static String kuaidi_key = "DIggKZco9382";
	private final static String kuaidi_compno = "17ADF76AD26C7C60168D4B9FC9124C63";
	
	
	public static PostageResponseVO query(PostageVO postage){
		String param ="{\"com\":\""+postage.getTrackingCom()+"\",\"num\":\""+postage.getTrackingNo()+"\"}";
		String sign = com.tiandu.common.utils.kuaidi.MD5.encode(param+kuaidi_key+kuaidi_compno);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("param",param);
		params.put("sign",sign);
		params.put("customer",kuaidi_compno);
		
		//调微信接口
        Gson json =new Gson();
        logger.error("kuaidi send request:"+params.toString());
        String response = null;
        try {
			response = new com.tiandu.common.utils.kuaidi.HttpRequest().postData(kuaidi_api_url, params, "utf-8").toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("kuaidi send request error:"+e.getMessage() + " [json]:"+params.toString());
		}
        if(null!=response){
        	/*try {
				response = new String(response.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        	logger.error("kuaidi response:"+response);
            //将xml装好为 WeChatRedPackResponse 对象
        	PostageResponseVO presp = json.fromJson(response, PostageResponseVO.class);
        	return presp;
        }
		return null;
	}
	
	/*public static void main(String[] args){
		PostageVO postage= new PostageVO();
		postage.setTrackingCom("yuantong");
		postage.setTrackingNo("882789798090711360");
		PostageResponseVO res = query(postage);
		List<PostageResponseDataVO> data = res.getData();
		for(PostageResponseDataVO da : data){
			System.out.println(da.getFtime() + ":" + da.getContext());
		}
	}*/

}
