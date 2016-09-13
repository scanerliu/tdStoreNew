package com.tiandu.product.search;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.tiandu.common.search.SearchCriteria;

public class TdProductCriteria extends SearchCriteria {

	private String name; // 商品名称
	private Byte status; // 商品状态，1-正常，2-待审核，3-审核不通过
	private Boolean onshelf; // 是否上架
	private Integer uid; // 供应商id
	private Byte kind; // 商品类型 1-普通商品，2-商品包，3-零元购，4-10元购，5-预售，6-秒杀商品'
	private Integer newRecommend; // 新品推荐
	private Integer hotRecommend; // 热门推荐
	private Integer fineRecommend; // 精品推荐
	private Integer typeRecommend; // 分类推荐
	private Boolean isNormalProduct = false;
	private Set<Integer> orderskuIdSet;

	private Integer typeId;//商品类型id
	private String  typeIdTree;//商品类型idTree
	private Integer brandId; //商品品牌id

	private List<Integer> productTypeIds;

	// 列表页排序 1-综合升序，2-综合降序，3-销量升序，4-销量降序，5-价格升序，6,-价格降序。
	private Integer orderby;

	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	
	// 秒杀结束时间
	private Date seckillEnd;

	private String keyword;
	
	private Integer stock; //库存状态，1-有库存
	private Integer postage;//邮费状态：1-包邮
	private BigDecimal startPrice;//开始价格
	private BigDecimal endPrice;//结束价格
	
	private Integer enjoyrecommend; //猜你喜欢推荐
	
	private BigDecimal acpe; //匹配价格
	
	private Integer pointType; //积分兑换类型：1-全积分兑换，2-部分积分兑换

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getOnshelf() {
		return onshelf;
	}

	public void setOnshelf(Boolean onshelf) {
		this.onshelf = onshelf;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Byte getKind() {
		return kind;
	}

	public void setKind(Byte kind) {
		this.kind = kind;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
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

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public List<Integer> getProductTypeIds() {
		return productTypeIds;
	}

	public void setProductTypeIds(List<Integer> productTypeIds) {
		this.productTypeIds = productTypeIds;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Boolean getIsNormalProduct() {
		return isNormalProduct;
	}

	public void setIsNormalProduct(Boolean isNormalProduct) {
		this.isNormalProduct = isNormalProduct;
	}

	public Set<Integer> getOrderskuIdSet() {
		return orderskuIdSet;
	}

	public void setOrderskuIdSet(Set<Integer> orderskuIdSet) {
		this.orderskuIdSet = orderskuIdSet;
	}

	public Date getSeckillEnd() {
		return seckillEnd;
	}

	public void setSeckillEnd(Date seckillEnd) {
		this.seckillEnd = seckillEnd;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPostage() {
		return postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public Integer getEnjoyrecommend() {
		return enjoyrecommend;
	}

	public void setEnjoyrecommend(Integer enjoyrecommend) {
		this.enjoyrecommend = enjoyrecommend;
	}
	
	public BigDecimal getAcpe() {
		return acpe;
	}

	public void setAcpe(BigDecimal acpe) {
		this.acpe = acpe;
	}

	public Integer getPointType() {
		return pointType;
	}

	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}

	// 列表页排序 1-综合升序，2-综合降序，3-销量升序，4-销量降序，5-价格升序，6,-价格降序。
	public String getOrderBySql() {
		String orderBySql = "p.sort desc";

		Integer orderType = this.orderby;
		switch (orderType) {
		/*case 1:
			orderBySql = "s.positive_rate desc , s.buy_count desc";
			break;
		// case 2:
		// orderBySql = "s.positive_rate desc and s.buy_count desc";
		// break;
		case 2:
			orderBySql = "s.buy_count desc";
			break;
		// case 4:
		// orderBySql = "s.buy_count desc";
		// break;
		case 3:
			orderBySql = "p.price desc";
			break;
		case 4:
			orderBySql = "p.price asc";
			break;*/
		case 1:
			orderBySql = orderBySql+",s.positive_rate desc , s.buy_count desc";
			break;
		case 2:
			orderBySql = orderBySql+",s.positive_rate asc , s.buy_count asc";
			break;
		case 3:
			 orderBySql = orderBySql+",s.buy_count desc";
			 break;
		case 4:
			orderBySql = orderBySql+",s.buy_count asc";
			break;
		case 5:
			orderBySql = "p.price desc";
			break;
		case 6:
			orderBySql = "p.price asc";
			break;
		default:
			break;
		}
		return orderBySql;
	}

}
