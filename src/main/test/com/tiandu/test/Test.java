package com.tiandu.test;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.tiandu.custom.vo.ProfitInfo;

public class Test {

	public static void main(String[] args) {
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
		String str = " ";
		if(StringUtils.isNotBlank(str)){
			System.out.println("1");
		}else{
			System.out.println("2");
		}
	}

}
