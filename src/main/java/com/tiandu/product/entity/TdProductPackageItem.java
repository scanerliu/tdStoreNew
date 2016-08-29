package com.tiandu.product.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.tiandu.order.vo.SkuSpecialVO;

public class TdProductPackageItem {
    private Integer id;

    private Integer productId;

    private Integer skuId;

    private Integer sort = 0;

    private Integer quantity;
    
    private Integer preprouductId;
    
    private String productName;
    
    private String productImage;
    
    private BigDecimal price;
    
    private String specifications;
    
    private String skuCode;
    
    /**
     * 属性键值对，显示时候使用
     */
    private List<SkuSpecialVO> specialList;
    
    private TdProduct prouduct;
    private TdProduct preProuduct;
    

    private TdProductSku productSku;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public Integer getPreprouductId() {
		return preprouductId;
	}

	public void setPreprouductId(Integer preprouductId) {
		this.preprouductId = preprouductId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public TdProduct getPreProuduct() {
		return preProuduct;
	}

	public void setPreProuduct(TdProduct preProuduct) {
		this.preProuduct = preProuduct;
	}

	public TdProduct getProuduct() {
		return prouduct;
	}

	public void setProuduct(TdProduct prouduct) {
		this.prouduct = prouduct;
	}

	public TdProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(TdProductSku productSku) {
		this.productSku = productSku;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
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
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return specialList;
	}

	public void setSpecialList(List<SkuSpecialVO> specialList) {
		this.specialList = specialList;
	}

	/**
	 * 获取规格配对key
	 * @return
	 */
	public String getSpecKey(){
		String specStr = "";
		List<SkuSpecialVO> slist = this.getSpecialList();
		if(slist == null){
			return specStr;
		}
		Collections.sort(slist, new Comparator<SkuSpecialVO>() {
            public int compare(SkuSpecialVO arg0, SkuSpecialVO arg1) {
                return arg0.getSname().compareTo(arg1.getSname());
            }
        });
		for(int i = 0; i < slist.size(); i ++){
			specStr += slist.get(i).getSname()+"_"+slist.get(i).getSoption();
			if(i < slist.size() - 1){
				specStr += "|";
			}
		}
		return specStr;
	}
	
    
}