package cn.hctech2006.product.product.view.tv;

import javafx.beans.property.SimpleStringProperty;

public class GoodsTV {

    private SimpleStringProperty name;

    private SimpleStringProperty shorts;

    private SimpleStringProperty place;

    private SimpleStringProperty size;

    private SimpleStringProperty paceage;

    private SimpleStringProperty productCode;


    private SimpleStringProperty price;

    //商品编号
    private SimpleStringProperty goodsId;

    private SimpleStringProperty number;

    public GoodsTV(String name, String shorts
            , String place, String size
            , String paceage, String productCode
            , String price, String goodsId) {
        this.name = new SimpleStringProperty(name);
        this.shorts = new SimpleStringProperty(shorts);
        this.place = new SimpleStringProperty(place);
        this.size = new SimpleStringProperty(size);
        this.paceage = new SimpleStringProperty(paceage);
        this.productCode = new SimpleStringProperty(productCode);
        this.price = new SimpleStringProperty(price);
        this.goodsId = new SimpleStringProperty(goodsId);
    }
    public GoodsTV(String name, String shorts
            , String place, String size
            , String paceage, String productCode
            , String price, String goodsId,String number) {
        this.name = new SimpleStringProperty(name);
        this.shorts = new SimpleStringProperty(shorts);
        this.place = new SimpleStringProperty(place);
        this.size = new SimpleStringProperty(size);
        this.paceage = new SimpleStringProperty(paceage);
        this.productCode = new SimpleStringProperty(productCode);
        this.price = new SimpleStringProperty(price);
        this.goodsId = new SimpleStringProperty(goodsId);
        this.number = new SimpleStringProperty(number);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getShorts() {
        return shorts.get();
    }

    public SimpleStringProperty shortsProperty() {
        return shorts;
    }

    public void setShorts(String shorts) {
        this.shorts.set(shorts);
    }

    public String getPlace() {
        return place.get();
    }

    public SimpleStringProperty placeProperty() {
        return place;
    }

    public void setPlace(String place) {
        this.place.set(place);
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public String getPaceage() {
        return paceage.get();
    }

    public SimpleStringProperty paceageProperty() {
        return paceage;
    }

    public void setPaceage(String paceage) {
        this.paceage.set(paceage);
    }

    public String getProductCode() {
        return productCode.get();
    }

    public SimpleStringProperty productCodeProperty() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode.set(productCode);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsTV goodsTV = (GoodsTV) o;
        System.out.println("o:"+((GoodsTV) o).goodsId.getValue());
        System.out.println("goodsId:"+goodsId.getValue());
        System.out.println("执行equals:"+goodsId.getValue().equals(goodsTV.goodsId.getValue()));
        return goodsId != null ? goodsId.getValue().equals(goodsTV.goodsId.getValue()) : goodsTV.goodsId.getValue() == null;
    }

    @Override
    public int hashCode() {
        System.out.println("执行hashCode:");
        return 1;
    }
}
