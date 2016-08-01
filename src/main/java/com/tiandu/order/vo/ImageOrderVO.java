package com.tiandu.order.vo;

import java.util.List;

import com.tiandu.product.entity.TdProduct;
import com.tiandu.product.entity.TdProductAttachment;

public class ImageOrderVO {

	/**
	 * 商品信息
	 */
	private TdProduct product;
	/**
	 * 图片数量
	 */
	private Integer imageNum;
	/**
	 * 图片处理单价
	 */
	private Integer price;
	/**
	 * 图片列表
	 */
	private List<TdProductAttachment> attachmentList;
	public TdProduct getProduct() {
		return product;
	}
	public void setProduct(TdProduct product) {
		this.product = product;
	}
	public Integer getImageNum() {
		return imageNum;
	}
	public void setImageNum(Integer imageNum) {
		this.imageNum = imageNum;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public List<TdProductAttachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<TdProductAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	/**
	 * 获取图片地址拼接字符串
	 * @return
	 */
	public String getAttachmentString(){
		StringBuffer sb = new StringBuffer();
		if(null!=this.getAttachmentList()){
			int i = 1;
			for(TdProductAttachment attach : this.getAttachmentList()){
				if(i==1){
					sb.append(attach.getAttachment());
				}else{
					sb.append(",");
					sb.append(attach.getAttachment());
				}
				i++;
			}
		}
		return sb.toString();
	}
}
