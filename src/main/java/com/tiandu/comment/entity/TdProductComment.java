package com.tiandu.comment.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.tiandu.custom.entity.TdUser;
import com.tiandu.order.entity.TdOrder;
import com.tiandu.order.vo.SkuSpecialVO;
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
    
    /**
     * 属性键值对，显示时候使用
     */
    private List<SkuSpecialVO> specialList;

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
    
	public List<SkuSpecialVO> getSpecialList() {
		if(null!=this.specialList){
			return specialList;
		}
		List<SkuSpecialVO> slist = new ArrayList<SkuSpecialVO>();
		if(StringUtils.isNotEmpty(this.getSpecifications())){
			String opt = this.getSpecifications();
			JSONObject json;
			try {
				json = new JSONObject(opt);
				String[] keys = json.getNames(json);
				for(String key : keys){
					SkuSpecialVO special = new SkuSpecialVO();
					String val = (String) json.get(key);
					special.setSname(key);
					special.setSoption(val);
					slist.add(special);						
				}
				this.setSpecialList(slist);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		return specialList;
	}

	public void setSpecialList(List<SkuSpecialVO> specialList) {
		this.specialList = specialList;
	}
    
}