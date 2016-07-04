package com.tiandu.product.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TdProduct {
    private Integer id;

    private Byte kind;

    private String name;

    private String title;

    private String code;

    private BigDecimal price;

    private Integer points;

    private Integer defaultSkuId;

    private Integer brandId;

    private Integer typeId;

    private Integer uid;

    private Boolean specification;

    private Byte status;

    private String keyword;

    private String imageUrl;

    private Date createTime;

    private Date updateTime;

    private Integer updateBy;

    private Integer newRecommend;

    private Integer hotRecommend;

    private Integer fineRecommend;

    private Integer typeRecommend;

    private Integer quantum;

    private Integer sort;

    private String tags;

    private Date startTime;

    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getKind() {
        return kind;
    }

    public void setKind(Byte kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getDefaultSkuId() {
        return defaultSkuId;
    }

    public void setDefaultSkuId(Integer defaultSkuId) {
        this.defaultSkuId = defaultSkuId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getNewRecommend() {
        return newRecommend;
    }

    public void setNewRecommend(Integer newRecommend) {
        this.newRecommend = newRecommend;
    }

    public Integer getHotRecommend() {
        return hotRecommend;
    }

    public void setHotRecommend(Integer hotRecommend) {
        this.hotRecommend = hotRecommend;
    }

    public Integer getFineRecommend() {
        return fineRecommend;
    }

    public void setFineRecommend(Integer fineRecommend) {
        this.fineRecommend = fineRecommend;
    }

    public Integer getTypeRecommend() {
        return typeRecommend;
    }

    public void setTypeRecommend(Integer typeRecommend) {
        this.typeRecommend = typeRecommend;
    }

    public Integer getQuantum() {
        return quantum;
    }

    public void setQuantum(Integer quantum) {
        this.quantum = quantum;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}