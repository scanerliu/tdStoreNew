package com.tiandu.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tiandu.express.entity.TdExpress;

public class TdOrderShipment {
    private Integer id;

    private Integer orderId;

    private Byte type;

    private String trackingNo;

    private Integer trackingId;

    private String returnReason;

    private Byte cargoStatus;

    private Byte status;

    private Date createTime;

    private Integer createBy;

    private Date updateTime;

    private Integer updateBy;
    private String  trackingTrace;
    private String voucherImages;
    private BigDecimal returnAmount;
    private Integer returnCause;
    /**
     * 图片凭证，前台上传使用
     */
    private String[] voucherImageList;
    /**
     * 物流公司
     */
    private TdExpress trackExpress;
    /**
     * 发货，退货清单
     */
    private List<TdOrderShipmentItem> itemList;
    
    /**
     * 订单信息（查询退货订单时使用）
     */
    private TdOrder order;
    
    private Integer supplyId;
    
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo == null ? null : trackingNo.trim();
    }

    public Integer getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Integer trackingId) {
        this.trackingId = trackingId;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason == null ? null : returnReason.trim();
    }

    public Byte getCargoStatus() {
        return cargoStatus;
    }

    public void setCargoStatus(Byte cargoStatus) {
        this.cargoStatus = cargoStatus;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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

	public String getTrackingTrace() {
		return trackingTrace;
	}

	public void setTrackingTrace(String trackingTrace) {
		this.trackingTrace = trackingTrace;
	}

	public String getVoucherImages() {
		return voucherImages;
	}

	public void setVoucherImages(String voucherImages) {
		this.voucherImages = voucherImages;
	}

	public BigDecimal getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Integer getReturnCause() {
		return returnCause;
	}

	public void setReturnCause(Integer returnCause) {
		this.returnCause = returnCause;
	}

	public TdExpress getTrackExpress() {
		return trackExpress;
	}

	public void setTrackExpress(TdExpress trackExpress) {
		this.trackExpress = trackExpress;
	}

	public TdOrder getOrder() {
		return order;
	}

	public void setOrder(TdOrder order) {
		this.order = order;
	}

	public List<TdOrderShipmentItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<TdOrderShipmentItem> itemList) {
		this.itemList = itemList;
	}

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getVoucherImageList() {
		if(null==voucherImageList){
			if(StringUtils.isNotBlank(voucherImages)){
				voucherImageList = voucherImages.split(",");
			}
		}
		return voucherImageList;
	}

	public void setVoucherImageList(String[] voucherImageList) {
		this.voucherImageList = voucherImageList;
	}
    /**
     * 前台上传图片后，赋值给数据库属性voucherImages
     */
	public void initVoucherImages(){
		if(null!=voucherImageList && voucherImageList.length>0){
			StringBuffer sb = new StringBuffer();
			int i=0;
			for(String image : voucherImageList){
				if(StringUtils.isNotBlank(image)){
					if(i==0){
						sb.append(image);
					}else{
						sb.append(","+image);
					}
					i++;
				}
			}
			if(i>0){
				this.setVoucherImages(sb.toString());
			}
		}
	}
	
	 /**
     * 前台上传图片后，赋值给数据库属性voucherImages
     */
	public String getReturnCauseStr(){
		StringBuffer sb = new StringBuffer();
		if(null!=returnCause){
			switch(returnCause){
				case 1:
					sb.append("七天无理由退换货");
					break;
				case 2:
					sb.append("退运费");
					break;
				case 3:
					sb.append("收到商品破损");
					break;
				case 4:
					sb.append("商品错发/漏发");
					break;
				case 5:
					sb.append("商品需要维修");
					break;
				case 6:
					sb.append("收到商品与描述不符");
					break;
				case 7:
					sb.append("商品质量问题");
					break;
				case 8:
					sb.append("假冒品牌");
					break;
				case 9:
					sb.append("未收到货");
					break;
				default:
					break;
			}
		}
		return sb.toString();
	}
}