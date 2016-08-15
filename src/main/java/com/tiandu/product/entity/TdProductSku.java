package com.tiandu.product.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.tiandu.order.vo.SkuSpecialVO;

public class TdProductSku {
    private Integer id;

    private Integer productId;

    private String skuCode = "";

    private BigDecimal supplierPrice;

    private BigDecimal salesPrice;

    private BigDecimal marketPrice;

    private BigDecimal highPrice;

    private BigDecimal lowPrice;

    private String specifications;
    
    private Integer stock;

    private Byte status;

    private Date updateTime;

    private Integer updateBy;
    
    private TdProduct product;
    
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
    
	public TdProduct getProduct() {
		return product;
	}

	public void setProduct(TdProduct product) {
		this.product = product;
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
	
	public String getStatusStr(){
		if(this.getStatus().equals(Byte.valueOf("1"))){
			return "已上架";
		}else{
			return "已下架";
		}
	}
	
	public String getSpecStr(){
		String specStr = "";
		List<SkuSpecialVO> slist = this.getSpecialList();
		if(slist == null){
			return specStr;
		}
		for(int i = 0; i < slist.size(); i ++){
			specStr += "["+slist.get(i).getSname() + ":" + slist.get(i).getSoption() +"]";
			if(i < slist.size() - 1){
				specStr += "，";
			}
		}
		return specStr;
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
	/**
	 * 获取规格名称值key
	 * @return
	 */
	public String getSpecOptionsKey(){
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
			specStr += slist.get(i).getSname()+"("+slist.get(i).getSoption()+")";
			if(i < slist.size() - 1){
				specStr += ",";
			}
		}
		return specStr;
	}
	/**
	 * 由key值转换为json字符串
	 * 如：尺码_1.8米床用|颜色_蓝色 => {"尺码":"1.8米床用","颜色":"蓝色"}
	 * @param special
	 * @return
	 */
	public String getSpecialJsonBySpecialKey(String special){
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotBlank(special)){
			String[] arr = special.split("\\|");
			int i = 0;
			for(String str : arr){
				String[] names = str.split("_");
				if(i==0){
					sb.append("\""+names[0]+"\":\""+names[1]+"\"");
				}else{
					sb.append(","+"\""+names[0]+"\":\""+names[1]+"\"");
				}
				i++;
			}
			return '{'+sb.toString()+'}';
		}
		return "";
	}
    
	
    
}