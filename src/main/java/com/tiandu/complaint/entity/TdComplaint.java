package com.tiandu.complaint.entity;

import java.util.Date;

import com.tiandu.common.entity.TdBaseEntity;
import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdOrder;

public class TdComplaint extends TdBaseEntity {
    private Integer id;

	private Integer uid;

	private Integer cuid;

	private Integer orderId;

	private String complaint;

	private String remark;

	private Byte status;

	private Date updateDate;
	
	private Date createTime;

	private Integer updateBy;
	
	/**
	 * 投诉人
	 */
	private TdUser complaintUser;
	/**
     * 供应商信息
     */
    private TdUser supplierUser;
    
    private TdUser updateUser;
    
    private TdOrder order;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint == null ? null : complaint.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public TdUser getComplaintUser() {
		return complaintUser;
	}

	public void setComplaintUser(TdUser complaintUser) {
		this.complaintUser = complaintUser;
	}

	public TdUser getSupplierUser() {
		return supplierUser;
	}

	public void setSupplierUser(TdUser supplierUser) {
		this.supplierUser = supplierUser;
	}

	public TdUser getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(TdUser updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public TdOrder getOrder() {
		return order;
	}

	public void setOrder(TdOrder order) {
		this.order = order;
	}

	
	
	
}