package com.tiandu.quartz;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiandu.custom.entity.TdAgent;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.custom.entity.TdUserCampaign;
import com.tiandu.custom.search.TdAgentSearchCriteria;
import com.tiandu.custom.search.TdUserCampaignCriteria;
import com.tiandu.custom.search.TdUserSearchCriteria;
import com.tiandu.custom.service.TdAgentService;
import com.tiandu.custom.service.TdUserCampaignService;
import com.tiandu.custom.service.TdUserService;
import com.tiandu.system.utils.ConfigUtil;

/*
 * 竞选活动统计三级会员总数及排名
 */
@Component("campaignCount")
public class CampaignCount {
	
	private final Logger logger = Logger.getLogger(getClass());

	
	@Autowired
	private TdUserService tdUserService;
	
	@Autowired
	private TdUserCampaignService tdUserCampaignService;
	
	@Autowired
	private TdAgentService tdAgentService;
	
	public void countCampain(){
		Map<String, String> smsConfig = ConfigUtil.getInstance().getSMSConfig();
		String campaignagentnumStr = smsConfig.get("campaignagentnum");
		String campaigncompanysuppliernumStr = smsConfig.get("campaigncompanysuppliernum");
		Integer campaignagentnum = 999999999;
		Integer campaigncompanysuppliernum = 999999999;
		try{
			campaignagentnum = Integer.parseInt(campaignagentnumStr);
			campaigncompanysuppliernum = Integer.parseInt(campaigncompanysuppliernumStr);			
		}catch(Exception e){
			logger.error("竞选活动统计失败，原因：条件人数解析失败");
			e.printStackTrace();
			return;
		}
		
		TdUserCampaignCriteria sc = new TdUserCampaignCriteria();
		sc.setFlag(false);
		int numRanked = 0; //已经参与排名的人数 
		List<TdUserCampaign> clist = tdUserCampaignService.findBySearchCriteria(sc);
		for(TdUserCampaign c : clist){
			if(c.getStatus().equals(Byte.valueOf("1"))){	
				numRanked ++;
			}
		}
		
		for(TdUserCampaign c : clist){
			if(c.getStatus().equals(Byte.valueOf("2"))){	
				int enterpriseNum = this.getDownOneUserNum(c.getUid(), c.getRegionId(), Byte.valueOf("2")); // 下一级会员中企业级供应商数量
				List<TdUser> ulist = this.getDownUser(c.getUid(), c.getRegionId()); // 下三级会员
				
				TdAgentSearchCriteria asc = new TdAgentSearchCriteria();
				asc.setFlag(false);
				for(TdUser u : ulist){
					asc.setUid(u.getUid());
					List<TdAgent> alist = tdAgentService.findBySearchCriteria(asc);
					if(alist == null || alist.size() == 0){
						ulist.remove(u);
					}else if(alist.size() == 1 && alist.get(0).getLevel() > Byte.valueOf("3")){
						ulist.remove(u);
					}
				}
				int agentNum = ulist.size(); // 三级代理数量
				if(enterpriseNum >= campaigncompanysuppliernum && agentNum >= campaignagentnum){
					c.setStatus(Byte.valueOf("1"));
					numRanked ++;
					c.setLevel(numRanked);
				}
				tdUserCampaignService.save(c);
			}
		}
	} 
	
	public Integer getDownOneUserNum(Integer uid, Integer regionId, Byte supplierType){
		Set<Integer> parentIdSet = new HashSet<>();
		TdUserSearchCriteria sc = new TdUserSearchCriteria();
		sc.setFlag(false);
		sc.setUregionId(regionId);
		sc.setSupplierType(supplierType);
		parentIdSet.add(uid); // 自己的id
		String parentIdsStr = formatSetToString(parentIdSet);
		sc.setParentIdsStr(parentIdsStr);
		List<TdUser> userList = tdUserService.findBySearchCriteria(sc);
		return userList.size();
	}
	
	public List<TdUser> getDownUser(Integer uid, Integer regionId){
		Set<Integer> parentIdSet = new HashSet<>();
		TdUserSearchCriteria sc = new TdUserSearchCriteria();
		sc.setFlag(false);
		sc.setUregionId(regionId);
		parentIdSet.add(uid); // 自己的id
		String parentIdsStr = formatSetToString(parentIdSet);
		sc.setParentIdsStr(parentIdsStr);
		List<TdUser> userList = tdUserService.findBySearchCriteria(sc);
		for(TdUser u : userList){
			parentIdSet.add(u.getUid());//自己的id + 下一级id
		}
		parentIdsStr = formatSetToString(parentIdSet);
		sc.setParentIdsStr(parentIdsStr);
		userList = tdUserService.findBySearchCriteria(sc);
		for(TdUser u : userList){
			parentIdSet.add(u.getUid());//自己的id + 下一级id + 下两级id
		}
		parentIdsStr = formatSetToString(parentIdSet);
		sc.setParentIdsStr(parentIdsStr);
		userList = tdUserService.findBySearchCriteria(sc);
		return userList;
	}
	
	public String formatSetToString(Set<Integer> set){
		String setStr = "";
		for(Integer s : set){
			setStr += "["+ s +"]";
		}
		return setStr;
	}
}
