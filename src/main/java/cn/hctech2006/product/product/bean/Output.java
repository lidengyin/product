package cn.hctech2006.product.product.bean;

import java.io.Serializable;
import java.util.Date;

public class Output implements Serializable {
    private Long id;

    private String number;

    private Long price;

    private String comment;

    private String providerId;

    private Date time;

    private String operatorId;

    private String sponsor;

    private String type;

    private String goodsId;

    private String outportId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOutportId() {
        return outportId;
    }

    public void setOutportId(String outportId) {
        this.outportId = outportId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", price=").append(price);
        sb.append(", comment=").append(comment);
        sb.append(", providerId=").append(providerId);
        sb.append(", time=").append(time);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", sponsor=").append(sponsor);
        sb.append(", type=").append(type);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", outportId=").append(outportId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}