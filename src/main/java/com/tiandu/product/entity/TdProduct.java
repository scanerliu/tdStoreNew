package com.tiandu.product.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TdProduct {
	private Integer id;

	private Byte kind;

	private String name;

	private String title;

	private String code;

	private BigDecimal price;
	
	private BigDecimal postage;

	private Integer points;

	private Integer defaultSkuId = 1; // 默认搞成1

	private Integer brandId;

	private Integer typeId = 0;
	
	private String typeIdTree;

	private Integer uid;

	private Boolean specification = false;
	
	private String specificationats;
	
	private Boolean onshelf = false;

	private Byte status = 1;

	private String keyword;

	private String imageUrl;

	private Date createTime;

	private Date updateTime;

	private Integer updateBy;

	private Integer newRecommend = 0;

	private Integer hotRecommend = 0;

	private Integer fineRecommend = 0;

	private Integer typeRecommend = 0;

	private Integer quantum;

	private Integer sort = 0;

	private String tags;

	private Date startTime;

	private Date endTime;

	/**
	 * 商品的货品集合
	 */
	private List<TdProductSku> skuList;
	// 关联类别
	private TdProductType tdProductType;
	/**
	 * 积分兑换所需积分数量
	 */
	private Integer exchangepoints;
	
	/**
	 * 一口价参数接收 
	 */
	private String skuCode = "";
    private BigDecimal supplierPrice;
    private BigDecimal marketPrice;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    
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

	public BigDecimal getPostage() {
		return postage;
	}

	public void setPostage(BigDecimal postage) {
		this.postage = postage;
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

	public String getTypeIdTree() {
		return typeIdTree;
	}

	public void setTypeIdTree(String typeIdTree) {
		this.typeIdTree = typeIdTree;
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
	
	public String getSpecificationats() {
		return specificationats;
	}

	public void setSpecificationats(String specificationats) {
		this.specificationats = specificationats;
	}

	public Boolean getOnshelf() {
		return onshelf;
	}

	public void setOnshelf(Boolean onshelf) {
		this.onshelf = onshelf;
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

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
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

	public TdProductType getTdProductType() {
		return tdProductType;
	}

	public void setTdProductType(TdProductType tdProductType) {
		this.tdProductType = tdProductType;
	}
	
	public Integer getExchangepoints() {
		return exchangepoints;
	}

	public void setExchangepoints(Integer exchangepoints) {
		this.exchangepoints = exchangepoints;
	}

	public List<TdProductSku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<TdProductSku> skuList) {
		this.skuList = skuList;
	}

	/**
     * 获取状态文字说明
     * @return
     */
    public String getStatusStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getStatus()){
    		switch (this.getStatus()) {
			case 1:
				sb.append("正常");
				break;
			case 2:
				sb.append("待审核");
				break;
			case 3:
				sb.append("审核未通过");
				break;
			default:
				break;
			}
    	}
    	
    	return sb.toString();
    }
    
    public String getKindStr(){
    	//商品类型,1-普通商品，2-商品包，3-零元购，4-10元购，5-预售，6-秒杀商品    7-全积分兑换，8-部分积分兑换
    	if(this.getKind().equals(Byte.valueOf("1"))){
    		return "普通商品";
    	}else if(this.getKind().equals(Byte.valueOf("2"))){
    		return "商品包";
    	}else if(this.getKind().equals(Byte.valueOf("3"))){
    		return "零元购";
    	}else if(this.getKind().equals(Byte.valueOf("4"))){
    		return "10元购";
    	}else if(this.getKind().equals(Byte.valueOf("5"))){
    		return "预售";
    	}else if(this.getKind().equals(Byte.valueOf("6"))){
    		return "秒杀";
    	}else if(this.getKind().equals(Byte.valueOf("7"))){
    		return "全积分兑换";
    	}else if(this.getKind().equals(Byte.valueOf("8"))){
    		return "部分积分兑换";
    	}else{
    		return "";
    	}
    }
    
    /**
     * 获取上架状态文字说明
     * @return
     */
    public String getOnshelfStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getOnshelf()){
    		if(this.getOnshelf()) {
    			sb.append("已上架");
    		}else{
    			sb.append("已下架");
    		}
    	}
    	
    	return sb.toString();
    }

    public String getJoinStatusStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getStatus() && null!=this.getOnshelf()){
    		switch (this.getStatus()) {
				case 1:
					if(this.getOnshelf()) {
		    			sb.append("已上架");
		    		}else{
		    			sb.append("已下架");
		    		}
					break;
				case 2:
					sb.append("待审核");
					break;
				case 3:
					sb.append("审核未通过");
					break;
				default:
					break;
			}
    	}
    	return sb.toString();
    }
}