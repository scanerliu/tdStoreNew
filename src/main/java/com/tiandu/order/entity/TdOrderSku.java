package com.tiandu.order.entity;

import java.math.BigDecimal;

public class TdOrderSku {
    private Integer orderSkuId;

    private Integer orderId;

    private Integer productId;

    private Integer productSkuId;

    private Byte itemType;

    private String productName;

    private String productSkuCode;

    private String displaySpecifications;

    private Integer quantity;

    private BigDecimal price;

    private Integer backQuantity;

    public Integer getOrderSkuId() {
        return orderSkuId;
    }

    public void setOrderSkuId(Integer orderSkuId) {
        this.orderSkuId = orderSkuId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Integer productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Byte getItemType() {
        return itemType;
    }

    public void setItemType(Byte itemType) {
        this.itemType = itemType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode == null ? null : productSkuCode.trim();
    }

    public String getDisplaySpecifications() {
        return displaySpecifications;
    }

    public void setDisplaySpecifications(String displaySpecifications) {
        this.displaySpecifications = displaySpecifications == null ? null : displaySpecifications.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getBackQuantity() {
        return backQuantity;
    }

    public void setBackQuantity(Integer backQuantity) {
        this.backQuantity = backQuantity;
    }
}