package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.bean.Goods;
import cn.hctech2006.product.product.bean.Provider;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.service.IGoodsService;
import cn.hctech2006.product.product.service.IProviderService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class GoodsViewController implements Initializable{
    @FXML private ChoiceBox<Provider> providerCB;
    @FXML private ChoiceBox<Goods> goodsCB;
    @FXML private TextField name;
    @FXML private TextField shorts;
    @FXML private TextField place;
    @FXML private TextField price;
    @FXML private TextField size;
    @FXML private TextField paceage;
    @FXML private TextField productCode;
    @FXML private TextField promitCode;
    @FXML private TextField memo;
    @FXML private ChoiceBox<Provider> providerCB1;
    @FXML private ChoiceBox<Goods> goodsCB1;
    @FXML private TextField name1;
    @FXML private TextField shorts1;
    @FXML private TextField place1;
    @FXML private TextField price1;
    @FXML private TextField size1;
    @FXML private TextField paceage1;
    @FXML private TextField productCode1;
    @FXML private TextField promitCode1;
    @FXML private TextField memo1;


    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IProviderService providerService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerResponse response = goodsService.selectAll();
        List<Goods> goodsList = (List<Goods>) response.getData();
        goodsCB1.getItems().addAll(goodsList);
        goodsCB1.converterProperty().set(new StringConverter<Goods>() {
            @Override
            public String toString(Goods object) {
                return object.getName();
            }
            @Override
            public Goods fromString(String string) {
                return null;
            }
        });
        response = providerService.selectAll();
        List<Provider> providerList = (List<Provider>) response.getData();
        providerCB1.getItems().addAll(providerList);
        providerCB1.converterProperty().set(new StringConverter<Provider>() {
            @Override
            public String toString(Provider object) {
                return object.getName();
            }
            @Override
            public Provider fromString(String string) {
                return null;
            }
        });
        providerCB.getItems().addAll(providerList);
        providerCB.converterProperty().set(new StringConverter<Provider>() {
            @Override
            public String toString(Provider object) {
                return object.getName();
            }
            @Override
            public Provider fromString(String string) {
                return null;
            }
        });
    }
    public void addGoods(){
        Goods goods = new Goods();
        goods.setMemo(memo.getText());
        goods.setName(name.getText());
        goods.setPaceage(paceage.getText());
        goods.setPlace(place.getText());
        goods.setPrice(Long.parseLong(price.getText()));
        goods.setProductCode(productCode.getText());
        goods.setPromitCode(promitCode.getText());
        goods.setShorts(shorts.getText());
        goods.setSize(size.getText());
        Provider provider = providerCB.getValue();
        goods.setProviderId(provider.getProviderId());
        ServerResponse response = goodsService.insertOrUpdate(goods);
        if (response.isSuccess()){
            new Alert(Alert.AlertType.INFORMATION, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    public void deleteGoods(){
        Goods goods = goodsCB1.getValue();
        ServerResponse response = goodsService.updateAvailable(goods.getGoodsId());
        if (response.isSuccess()){
            memo1.setText(null);
            name1.setText(null);
            paceage1.setText(null);
            //System.out.println(goods.getPlace());
            place1.setText(null);
            price1.setText(null);
            productCode1.setText(null);
            promitCode1.setText(null);
            shorts1.setText(null);
            size1.setText(null);
            assembleChoiceBox();
            new Alert(Alert.AlertType.INFORMATION, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }
    public void updateGoods(){
        Goods goods = goodsCB1.getValue();
        //Goods goods = new Goods();
        goods.setMemo(memo1.getText());
        goods.setName(name1.getText());
        goods.setPaceage(paceage1.getText());
        goods.setPlace(place1.getText());
        goods.setPrice(Long.parseLong(price1.getText()));
        goods.setProductCode(productCode1.getText());
        goods.setPromitCode(promitCode1.getText());
        goods.setShorts(shorts1.getText());
        goods.setSize(size1.getText());
        Provider provider = providerCB1.getValue();
        goods.setProviderId(provider.getProviderId());

        ServerResponse response = goodsService.insertOrUpdate(goods);
        if (response.isSuccess()){
            new Alert(Alert.AlertType.INFORMATION, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }
    public void showGoods(){
        Goods goods = goodsCB1.getValue();
        memo1.setText(goods.getMemo());
        name1.setText(goods.getName());
        paceage1.setText(goods.getPaceage());
        //System.out.println(goods.getPlace());
        place1.setText(goods.getPlace());
        price1.setText(goods.getPrice()+"");
        productCode1.setText(goods.getProductCode());
        promitCode1.setText(goods.getPromitCode());
        shorts1.setText(goods.getShorts());
        size1.setText(goods.getSize());

        ObservableList<Provider> providers = providerCB1.getItems();
        for (Provider provider : providers){
            System.out.println("供应商ID:"+provider.getProviderId());
            System.out.println("产品供应商:"+goods.getProviderId());
            System.out.println("供应商排序："+providerCB1.getItems().indexOf(provider));
            if (provider.getProviderId().equals(goods.getProviderId())){
                System.out.println("被选中供应商："+provider.getName());
                providerCB1.getSelectionModel().select(providerCB1.getItems().indexOf(provider));
            }
        }

    }

    private void assembleChoiceBox(){
        System.out.println(goodsCB1.getSelectionModel().getSelectedIndex());
        //解决实时刷新问题
        goodsCB1.getItems().remove(goodsCB1.getSelectionModel().getSelectedIndex());
        providerCB1.getSelectionModel().clearSelection();


    }
}
