package com.tiandu.custom.vo;

import java.util.List;

/**
 * 评论实体类
 *
 */
public class CommentForm {
	/**
	 * 订单id
	 */
	private Integer orderId; 
	/**
	 * 商品id
	 */
	private Integer[] productId;
	/**
	 * 货品id
	 */
	private Integer[] skuId;
	/**
	 * 评论
	 */
	private String[] content;
	/**
	 * 评分
	 */
	private Byte[] score;
	/**
	 * 匿名评论
	 */
	private Boolean anonymous;
	/**
	 * 上传图片
	 */
	private List<String[]> images;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer[] getProductId() {
		return productId;
	}
	public void setProductId(Integer[] productId) {
		this.productId = productId;
	}
	public Integer[] getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer[] skuId) {
		this.skuId = skuId;
	}
	public String[] getContent() {
		return content;
	}
	public void setContent(String[] content) {
		this.content = content;
	}
	public Byte[] getScore() {
		return score;
	}
	public void setScore(Byte[] score) {
		this.score = score;
	}
	public List<String[]> getImages() {
		return images;
	}
	public void setImages(List<String[]> images) {
		this.images = images;
	}
	public Boolean getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}
	
}
