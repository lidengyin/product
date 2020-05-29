package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.bean.Goods;
import cn.hctech2006.product.product.common.Const;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.service.IGoodsService;
import cn.hctech2006.product.product.view.GoodsDetailsView;
import cn.hctech2006.product.product.view.tv.GoodsTV;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.catalina.Server;
import org.apache.commons.collections.list.GrowthList;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class GoodsDetailsViewController implements Initializable {
    @Autowired
    private IGoodsService goodsService;
    @FXML private TableView<GoodsTV> tableView;
    @FXML private TableColumn<GoodsTV,String> nameCol;
    @FXML private TableColumn<GoodsTV,String> shortsCol;
    @FXML private TableColumn<GoodsTV,String> placeCol;
    @FXML private TableColumn<GoodsTV,String> priceCol;
    @FXML private TableColumn<GoodsTV,String> sizeCol;
    @FXML private TableColumn<GoodsTV,String> paceageCol;
    @FXML private TableColumn<GoodsTV,String> productCodeCol;
    @FXML private TableColumn<GoodsTV,String> goodsIdCol;
    @FXML private TextField name;
    @FXML private ChoiceBox<String> typeCB;
    @FXML private ChoiceBox<String> equalCB;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerResponse response = goodsService.selectAll();
        List<Goods> goodsList = (List<Goods>) response.getData();
        List<GoodsTV> goodsTVList = new ArrayList<>();
        for (Goods goods:goodsList){
            goodsTVList.add(new GoodsTV(goods.getName(), goods.getShorts()
                    , goods.getPlace(),goods.getSize()
                    ,goods.getPaceage(),goods.getProductCode()
                    ,String.valueOf(goods.getPrice()),goods.getGoodsId()));
        }

        List<String> typeList = new ArrayList<>();
        typeList.add(Const.TBTYPE.GOODSNAME.getName());
        typeList.add(Const.TBTYPE.GOODSSHORTS.getName());
        List<String> equalList = new ArrayList<>();
        equalList.add("相等");
        equalList.add("不等");
        typeCB.getItems().addAll(typeList);
        equalCB.getItems().addAll(equalList);
        final ObservableList<GoodsTV> data = FXCollections.observableArrayList(goodsTVList);
        tableView.setItems(data);
        nameCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("name"));
        shortsCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("shorts"));
        priceCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("price"));
        placeCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("place"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("size"));
        paceageCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("paceage"));
        productCodeCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("productCode"));
        goodsIdCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("goodsId"));



    }
    public void showAllGoodsDetails(){
        ServerResponse response = goodsService.selectAll();
        assembleTableview(response);
    }
    public void showByKeyword(){
        tableView.setItems(null);
        String type = ((typeCB.getValue() != null)?typeCB.getValue():"");
        Goods goods = new Goods();
        if (type.equals(Const.TBTYPE.GOODSNAME.getName())){
            String goodsName = name.getText();
            goods.setName(goodsName);
            ServerResponse response = goodsService.selectByNameORShorts(goods);
            assembleTableview(response);
        }else if (type.equals(Const.TBTYPE.GOODSSHORTS.getName())){
            String goodShorts = name.getText();
            goods.setShorts(goodShorts);
            ServerResponse response = goodsService.selectByNameORShorts(goods);
            assembleTableview(response);

        }else{
            new Alert(Alert.AlertType.ERROR, "没有检测到查询条件",new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }

    private void assembleTableview(ServerResponse response){
        List<Goods> goodsList = (List<Goods>) response.getData();
        List<GoodsTV> goodsTVList = new ArrayList<>();
        for (Goods goods:goodsList){
            goodsTVList.add(new GoodsTV(goods.getName(), goods.getShorts()
                    , goods.getPlace(),goods.getSize()
                    ,goods.getPaceage(),goods.getProductCode()
                    ,String.valueOf(goods.getPrice()),goods.getGoodsId()));
        }
        final ObservableList<GoodsTV> data = FXCollections.observableArrayList(goodsTVList);
        tableView.setItems(data);
    }
}
