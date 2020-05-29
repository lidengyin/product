package cn.hctech2006.product.product.bean;

import java.io.Serializable;

public class Goods implements Serializable {
    private Long id;

    private String name;

    private String shorts;

    private String place;

    private String size;

    private String paceage;

    private String productCode;

    private String promitCode;

    private Long price;

    private String memo;

    private String providerId;

    private Integer available;

    private String goodsId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShorts() {
        return shorts;
    }

    public void setShorts(String shorts) {
        this.shorts = shorts;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPaceage() {
        return paceage;
    }

    public void setPaceage(String paceage) {
        this.paceage = paceage;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPromitCode() {
        return promitCode;
    }

    public void setPromitCode(String promitCode) {
        this.promitCode = promitCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", shorts=").append(shorts);
        sb.append(", place=").append(place);
        sb.append(", size=").append(size);
        sb.append(", paceage=").append(paceage);
        sb.append(", productCode=").append(productCode);
        sb.append(", promitCode=").append(promitCode);
        sb.append(", price=").append(price);
        sb.append(", memo=").append(memo);
        sb.append(", providerId=").append(providerId);
        sb.append(", available=").append(available);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}