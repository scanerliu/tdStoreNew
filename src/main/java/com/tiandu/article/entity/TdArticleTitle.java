package com.tiandu.article.entity;

import java.util.Date;

import com.tiandu.custom.entity.TdUser;

public class TdArticleTitle {
	private Integer aid;

	private Integer cid;

	private TdArticleCategory tdArticleCategory;

	private Integer uid;

	private TdUser user;

	private Integer regionId;

	private String title;

	private String keyword;

	private String summary;

	private String imageUrl;

	private Byte status = 1; // 默认启用
	
	private Integer sort;

	private Byte hotRecommend = 2; // 默认初始化为非热门

	private Date updateTime;

	private Integer updateBy;

	private TdUser updatePerson;

	private String articleContent;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : keyword.trim();
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl == null ? null : imageUrl.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Byte getHotRecommend() {
		return hotRecommend;
	}

	public String getHotRecommendStr() {
		// 1热门，2非热门
		if (this.getHotRecommend().equals(Byte.valueOf("1"))) {
			return "热门";
		} else {
			return "非热门";
		}
	}

	public void setHotRecommend(Byte hotRecommend) {
		this.hotRecommend = hotRecommend;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public TdArticleCategory getTdArticleCategory() {
		return tdArticleCategory;
	}

	public void setTdArticleCategory(TdArticleCategory tdArticleCategory) {
		this.tdArticleCategory = tdArticleCategory;
	}

	public TdUser getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(TdUser updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public TdUser getUser() {
		return user;
	}

	public void setUser(TdUser user) {
		this.user = user;
	}

	//1正常，2屏蔽
	public String getStatusStr() {
		if(this.getStatus().equals(Byte.valueOf("1"))){
			return "正常";
		}else{
			return "屏蔽";
		}
	}
	
}