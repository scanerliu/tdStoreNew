package com.tiandu.article.entity;

import java.util.Date;
import java.util.List;

import com.tiandu.custom.entity.TdUser;

public class TdArticleCategory {
	private Integer cid;

	private String name;

	private Integer parentId;

	private TdArticleCategory parent;

	private Integer articles;

	private Integer sort;

	private Byte status=1; // 默认启用

	private Date updateTime;

	private Integer updateBy;

	private TdUser updatePerson;
	
	private List<TdArticleTitle> articleList;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getArticles() {
		return articles;
	}

	public void setArticles(Integer articles) {
		this.articles = articles;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Byte getStatus() {
		return status;
	}

	// 1正常；2屏蔽
	public String getStatusDescription() {
		if (status.equals(Byte.valueOf("1"))) {
			return "正常";
		} else {
			return "屏蔽";
		}
	}

	public void setStatus(Byte status) {
		this.status = status;
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

	public TdArticleCategory getParent() {
		return parent;
	}

	public void setParent(TdArticleCategory parent) {
		this.parent = parent;
	}

	public TdUser getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(TdUser updatePerson) {
		this.updatePerson = updatePerson;
	}

	public List<TdArticleTitle> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<TdArticleTitle> articleList) {
		this.articleList = articleList;
	}

	
	
}