package cn.hctech2006.product.product.view.tv;

import javafx.beans.property.SimpleStringProperty;

public class InputTV {

    private SimpleStringProperty goodsName;

    private SimpleStringProperty inputId;

    private SimpleStringProperty providerName;

    private SimpleStringProperty price;
    //商品编号
    private SimpleStringProperty goodsId;

    private SimpleStringProperty number;

    private SimpleStringProperty allPrice;
    private SimpleStringProperty sponsor;
    private SimpleStringProperty operator;
    private SimpleStringProperty time;

    public InputTV(String goodsName, String inputId
            , String providerName, String price
            , String goodsId, String number
            , String allPrice, String sponsor
            , String operator,String time) {
        this.goodsName = new SimpleStringProperty(goodsName);
        this.inputId = new SimpleStringProperty(inputId);
        this.providerName = new SimpleStringProperty(providerName);
        this.price = new SimpleStringProperty(price);
        this.goodsId = new SimpleStringProperty(goodsId);
        this.number = new SimpleStringProperty(number);
        this.allPrice = new SimpleStringProperty(allPrice);
        this.sponsor = new SimpleStringProperty(sponsor);
        this.operator = new SimpleStringProperty(operator);
        this.time = new SimpleStringProperty(time);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getGoodsName() {
        return goodsName.get();
    }

    public SimpleStringProperty goodsNameProperty() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName.set(goodsName);
    }

    public String getInputId() {
        return inputId.get();
    }

    public SimpleStringProperty inputIdProperty() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId.set(inputId);
    }

    public String getProviderName() {
        return providerName.get();
    }

    public SimpleStringProperty providerNameProperty() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName.set(providerName);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getGoodsId() {
        return goodsId.get();
    }

    public SimpleStringProperty goodsIdProperty() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId.set(goodsId);
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getAllPrice() {
        return allPrice.get();
    }

    public SimpleStringProperty allPriceProperty() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice.set(allPrice);
    }

    public String getSponsor() {
        return sponsor.get();
    }

    public SimpleStringProperty sponsorProperty() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor.set(sponsor);
    }

    public String getOperator() {
        return operator.get();
    }

    public SimpleStringProperty operatorProperty() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator.set(operator);
    }
}
