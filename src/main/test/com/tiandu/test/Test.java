package com.tiandu.test;

import java.text.ParseException;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tiandu.product.vo.AttributeOptionsVO;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
//		System.out.println(System.currentTimeMillis());
		/*ProfitInfo profit = new ProfitInfo();
    	profit.setBuyUserName("test");
    	profit.setOrderNo("123456");
    	profit.setProductName("ddd");
    	profit.setProfit("200");
    	profit.setTotalAmount("8000");
    	Gson json = new Gson();
    	String relation = json.toJson(profit);
    	System.out.println(relation);
    	
    	ProfitInfo prof = json.fromJson(relation, ProfitInfo.class);
    	System.out.println(prof.getOrderNo());*/
		/*String str = " ";
		if(StringUtils.isNotBlank(str)){
			System.out.println("1");
		}else{
			System.out.println("2");
		}*/
		String spes = "[{\"sname\":\"颜色\",\"soptions\":[\"ree\"]},{\"sname\":\"尺码\",\"soptions\":[\"sdfsw\"]}]";
		AttributeOptionsVO  aos = null;
		JsonParser parser = new JsonParser();
		Gson gson = new Gson();
		JsonElement element = parser.parse(spes);
		JsonArray sarr = element.getAsJsonArray();
		Iterator it = sarr.iterator();
		while(it.hasNext()){
			JsonElement e = (JsonElement)it.next();
			aos = gson.fromJson(e, AttributeOptionsVO.class);
			System.out.println(aos.getSname());
		}
	}

}
