package com.tiandu.comment.entity;

import java.util.Date;

import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.product.entity.TdProduct;

public class TdProductComment {
    private Integer id;

    private Integer uid;

    private Boolean anonymous;

    private Integer productId;

    private String specifications;

    private Byte score;

    private String content;

    private Byte status;

    private Date createTime;

    private String images;
    
    private Integer orderId;
    
    /**
     * 评论人
     */
    private TdUser commentUser;
    
    /**
     * 评论商品
     */
    private TdProduct product;
    
    /**
     * 评论订单
     */
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

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

	public TdUser getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(TdUser commentUser) {
		this.commentUser = commentUser;
	}

	public TdProduct getProduct() {
		return product;
	}

	public void setProduct(TdProduct product) {
		this.product = product;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public TdOrder getOrder() {
		return order;
	}

	public void setOrder(TdOrder order) {
		this.order = order;
	}
    
    
    
}