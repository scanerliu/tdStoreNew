package com.tiandu.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiandu.custom.entity.TdMembership;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserMessage;
import com.tiandu.custom.search.TdMembershipSearchCriteria;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdMembershipService;
import com.tiandu.custom.service.TdUserCampaignService;
import com.tiandu.custom.service.TdUserMessageService;
import com.tiandu.custom.service.TdUserService;

/*
 * 会员等级刷新
 */
@Component("userLevelFlush")
public class UserLevelFlush {
	
	private final Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdUserCampaignService tdUserCampaignService;
	
	@Autowired
	private TdUserMessageService tdUserMessageService;
	
	@Autowired
	private TdMembershipService tdMembershipService;
	
	// 更新升级的普通会员
	public void updateUserLevelUped(){
		// 可用会员等级
		TdMembershipSearchCriteria msc = new TdMembershipSearchCriteria();
		msc.setFlag(true);
		msc.setStatus(Byte.valueOf("1"));
		List<TdMembership> membershipList = tdMembershipService.findBySearchCriteria(msc); //会员等级已按level降序排列
		
		// 满足升级的普通会员(分批查询)
		TdUserSearchCriteria sc = new TdUserSearchCriteria();
		sc.setFlag(false);
		sc.setUtype(Byte.valueOf("1"));
		int totalCount = tdUserService.getTotalCount(sc);
		sc.setPageSize(1000);
		sc.setTotalCount(totalCount);
		int totalPage = sc.getTotalPageCount();
		sc.setFlag(true);
		/*List<TdUser> userList = new ArrayList<>();
		for(int i = 1; i <= totalPage; i ++){
			sc.setPageNo(i);
			userList.addAll(tdUserService.findBySearchCriteria(sc));
		}
		for(TdUser user : userList){
			List<TdUser> downThreeUser = downThreeUserNum(user);
			if(downThreeUser == null){
				user.setDownThreeUserNum(0);
			}else{
				user.setDownThreeUserNum(downThreeUser.size());
			}
			for(TdMembership ms : membershipList){
				if(user.getDownThreeUserNum() >= ms.getUpgradeAgents()){
					user.setMembershipId(ms.getId());
					tdUserService.saveCustomer(user);
					break;
				}
				userList.remove(user);
			}
		}*/
		List<TdUser> userList = tdUserService.findBySearchCriteria(sc);
		if(null!=userList && userList.size()>0){
			for(TdUser user : userList){
				List<TdUser> downThreeUser = downThreeUserNum(user);
				if(downThreeUser == null){
					user.setDownThreeUserNum(0);
				}else{
					user.setDownThreeUserNum(downThreeUser.size());
				}
				for(TdMembership ms : membershipList){
					if(user.getDownThreeUserNum() >= ms.getUpgradeAgents()){
						user.setMembershipId(ms.getId());
						tdUserService.saveCustomer(user);
						break;
					}
				}
			}
			sendSystemMessage(userList);
		}
	}
	
	// 给升级的会员发送系统消息
	public boolean sendSystemMessage(List<TdUser> userList){
		boolean isSuccess = false;
		Date now = new Date();
		try{
			for(TdUser user : userList){
				TdUserMessage userMessage = new TdUserMessage();
				userMessage.setUid(user.getUid());
				userMessage.setMsgType(Byte.valueOf("1"));
				userMessage.setTitle("会员等级升级");
				TdMembership membership = tdMembershipService.findOne(user.getMembershipId());
				userMessage.setContent(membership.getTip());
				userMessage.setCreateTime(now);
				userMessage.setRelateId(0);
				userMessage.setStatus(Byte.valueOf("1"));
				tdUserMessageService.save(userMessage);
			}
			isSuccess = true;
		}catch(Exception e){
			logger.error("会员等级刷新定时任务执行异常");
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	// 获取下三级会员人数
	public List<TdUser> downThreeUserNum(TdUser user){
		Set<String> parentIdSet = new HashSet<>();
		parentIdSet.add(user.getUid().toString());
		TdUserSearchCriteria sc = new TdUserSearchCriteria();
		sc.setFlag(false);
		sc.setParentId(user.getUid());
		List<TdUser> userList = tdUserService.findBySearchCriteria(sc); // 下一级会员
		for(TdUser u : userList){
			parentIdSet.add(u.getUid().toString());
		}
		String parentIdsStr = idSetToString(parentIdSet);
		sc.setParentId(null);
		sc.setParentIdsStr(parentIdsStr);
		userList = tdUserService.findBySearchCriteria(sc); // 下两级会员
		for(TdUser u : userList){
			parentIdSet.add(u.getUid().toString());
		}
		String parentIdsStr2 = idSetToString(parentIdSet);
		sc.setParentIdsStr(parentIdsStr2);
		userList = tdUserService.findBySearchCriteria(sc); // 下三级会员
		return userList;
	}
	
	// 格式 [1][2]...
	public String idSetToString(Set<String> idSet){
		String idstr = "";
		for(String s : idSet){
			idstr += "[" + s + "]";
		}
		return idstr;
	}
	
}
