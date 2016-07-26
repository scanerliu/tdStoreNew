package com.tiandu.product.entity;

import java.math.BigDecimal;

public class TdProductStat {
    private Integer productId;

    private Integer viewCount;

    private Integer buyCount;

    private Integer buyTimes;

    private Integer reviewCount;

    private Integer showreviewCount;

    private Integer positiveRate;

    private Integer neutralRate;

    private Integer negativeRate;

    private BigDecimal reviewScore;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getBuyTimes() {
        return buyTimes;
    }

    public void setBuyTimes(Integer buyTimes) {
        this.buyTimes = buyTimes;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getShowreviewCount() {
        return showreviewCount;
    }

    public void setShowreviewCount(Integer showreviewCount) {
        this.showreviewCount = showreviewCount;
    }

    public Integer getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(Integer positiveRate) {
        this.positiveRate = positiveRate;
    }

    public Integer getNeutralRate() {
        return neutralRate;
    }

    public void setNeutralRate(Integer neutralRate) {
        this.neutralRate = neutralRate;
    }

    public Integer getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(Integer negativeRate) {
        this.negativeRate = negativeRate;
    }

    public BigDecimal getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(BigDecimal reviewScore) {
        this.reviewScore = reviewScore;
    }
}