package com.tiandu.order.entity;

public class TdOrderShipmentItem {
    private Integer id;

    private Integer shipmentId;

    private Integer orderSkuId;

    private Integer quantity;
    
    private TdOrderSku itemOrderSku;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Integer getOrderSkuId() {
        return orderSkuId;
    }

    public void setOrderSkuId(Integer orderSkuId) {
        this.orderSkuId = orderSkuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public TdOrderSku getItemOrderSku() {
		return itemOrderSku;
	}

	public void setItemOrderSku(TdOrderSku itemOrderSku) {
		this.itemOrderSku = itemOrderSku;
	}

    
}