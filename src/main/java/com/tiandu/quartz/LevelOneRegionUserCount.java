package com.tiandu.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiandu.common.utils.ConstantsUtils;
import com.tiandu.common.utils.MessageSender;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.district.entity.TdDistrict;
import com.tiandu.district.service.TdDistrictService;
import com.tiandu.system.utils.ConfigUtil;

/*
 * 省直辖市会员人数统计及短信通知
 */
@Component("levelOneRegionUserCount")
public class LevelOneRegionUserCount {
	
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TdDistrictService tdDistrictService;
	
	@Autowired
	private TdUserService tdUserService;
	
	/*
	 * 产生统计省直辖市人数短信
	 */
	public String makeMessage(){
		String message = "";
		List<TdDistrict> levelOneDistrict = tdDistrictService.getDistrictByUpid(0);
		if(levelOneDistrict != null){
			TdUserSearchCriteria sc = new TdUserSearchCriteria();
			sc.setFlag(false);
			for(TdDistrict d : levelOneDistrict){
				sc.setUprovinceId(d.getId());
				List<TdUser> userList = tdUserService.findBySearchCriteria(sc);
				if(userList != null){
					message += d.getName() + ":" + userList.size() + "人，";
				}else{
					message += d.getName() + ":" + 0 + "人，";
				}
			}
		}
		if(!message.equals("")){
			message = message.substring(0, message.lastIndexOf("，"));
		}
		System.err.println(message);
		return message;
	}
	
	// 发送产生统计省直辖市人数短信
	public void sendMessage(String message){
		String phoneNums = ConfigUtil.getInstance().getCustomerStatisticsTelphone();
		if(phoneNums == null || phoneNums.equals("")){
			return;
		}else{
			String[] phones = phoneNums.split(",");
			List<String> phoneList = new ArrayList<>();
			for(String phone : phones){
				phoneList.add(phone);
			}
			List<String> datas = new ArrayList<>();
			datas.add("1");
			datas.add(message);
			MessageSender ms = new MessageSender();
			ms.init();
			boolean isSendSuccess = ms.send(phoneList, ConstantsUtils.SMS_TEMPLATE_CUSTOMERCOUNT, datas);
			if(!isSendSuccess){
				logger.error("省直辖市会员人数短信发送失败");		
			}
		}
	}
	
	
}
