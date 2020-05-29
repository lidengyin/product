package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.bean.Goods;
import cn.hctech2006.product.product.bean.Input;
import cn.hctech2006.product.product.bean.Operator;
import cn.hctech2006.product.product.bean.Provider;
import cn.hctech2006.product.product.common.Const;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.service.IGoodsService;
import cn.hctech2006.product.product.service.IInputService;
import cn.hctech2006.product.product.service.IOperatorService;
import cn.hctech2006.product.product.service.IProviderService;
import cn.hctech2006.product.product.view.tv.GoodsTV;
import cn.hctech2006.product.product.view.tv.ProviderTV;
import com.google.common.collect.Lists;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@FXMLController
public class InputViewController implements Initializable {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IProviderService providerService;
    @Autowired
    private IInputService iInputService;
    @Autowired
    private IOperatorService operatorService;


    @FXML private ChoiceBox<Provider> providerCB;
    @FXML private ChoiceBox<Goods> goodsCB;
    @FXML private ChoiceBox<Operator> operatorCB;
    @FXML private TextField inputId;
    @FXML private TextField type;
    @FXML private TextField contacts;
    @FXML private TextField sponsor;
    @FXML private DatePicker datePicker;
    @FXML private TextField kindNumber;
    @FXML
    private TextField allNumber;
    @FXML private TextField allPrice;
    @FXML private TextField commit;
    @FXML private TextField operator;
    @FXML private TableView<GoodsTV> tableView;
    @FXML private TableColumn<GoodsTV,String> nameCol;
    @FXML private TableColumn<GoodsTV,String> numberCol;
    @FXML private TableColumn<GoodsTV,String> placeCol;
    @FXML private TableColumn<GoodsTV,String> priceCol;
    @FXML private TableColumn<GoodsTV,String> sizeCol;
    @FXML private TableColumn<GoodsTV,String> paceageCol;
    @FXML private TableColumn<GoodsTV,String> productCodeCol;
    @FXML private TableColumn<GoodsTV,String> goodsIdCol;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Operator operator1 = new Operator();
        operator1.setOperatorId("222");
        operator1.setName("李登印");

        ServerResponse response = goodsService.selectAll();
        List<Goods> goodsList = (List<Goods>) response.getData();
        ObservableList<Goods> goodsObservableList = FXCollections.observableList(goodsList);
        goodsCB.setItems(goodsObservableList);
        goodsCB.converterProperty().set(new StringConverter<Goods>() {
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
        ObservableList<Provider> providerObservableList = FXCollections.observableList(providerList);
        providerCB.setItems(providerObservableList);
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

        List<Operator> operatorList = operatorService.selectAll();
        ObservableList<Operator> operatorObservableList = FXCollections.observableList(operatorList);
        operatorCB.setItems(operatorObservableList);
        operatorCB.converterProperty().set(new StringConverter<Operator>() {
            @Override
            public String toString(Operator object) {
                return object.getUsername();
            }

            @Override
            public Operator fromString(String string) {
                return null;
            }
        });


        numberCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("number"));
        priceCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("price"));
        placeCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("place"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("size"));
        paceageCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("paceage"));
        productCodeCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("productCode"));
        goodsIdCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("goodsId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<GoodsTV,String>("name"));

        inputId.setText(UUID.randomUUID().toString());
        //
        tableView.setEditable(true);

        numberCol.setCellFactory(TextFieldTableCell.forTableColumn());

        numberCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<GoodsTV, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<GoodsTV, String> event) {
                System.out.println("事件触发");
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setNumber(event.getNewValue());
                int num = 0;
                double price = 0;
                reFresh();


            }
        });
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public String toString(LocalDate object) {
                if (object != null){
                    return formatter.format(object);
                }else{
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()){
                    return LocalDate.parse(string,formatter);
                }else{
                    return null;
                }
            }
        });

    }

    public void onChooseGoods(){
        Goods goods1 = goodsCB.getValue();
       // System.out.println(goods1.getName());
if (goods1 != null){

    List<Goods> goodsList = Lists.newArrayList();
    goodsList.add(goods1);
    List<GoodsTV> goodsTVList = new ArrayList<>();
    for (Goods goods:goodsList){
        goodsTVList.add(new GoodsTV(goods.getName(), goods.getShorts()
                , goods.getPlace(),goods.getSize()
                ,goods.getPaceage(),goods.getProductCode()
                ,String.valueOf(goods.getPrice()),goods.getGoodsId(),"1"));
    }

    final ObservableList<GoodsTV> data = FXCollections.observableArrayList(goodsTVList);
    if (tableView.getItems() == null){
        tableView.setItems(data);
    }else{
        tableView.getItems().addAll(data);
    }
    reFresh();
}

    }
    public void onChooseProvider(){
        Provider provider = providerCB.getValue();
        if(provider != null){
            contacts.setText(provider.getContacts());
        }


    }
    public void onSave(){
        ObservableList<GoodsTV> goodsTVS = tableView.getItems();
        List<Input> inputs = Lists.newArrayList();
        for (GoodsTV goodsTV:goodsTVS){
            Input input = new Input();
            input.setInputId(inputId.getText());
            input.setNumber(Integer.parseInt(goodsTV.getNumber()));
            input.setProviderId(providerCB.getValue().getProviderId());
            input.setType(type.getText());
            input.setOperatorId(operatorCB.getValue().getOperatorId());
            ZoneId zoneId = ZoneId.systemDefault();

            ZonedDateTime zonedDateTime = datePicker.getValue().atStartOfDay(zoneId);
            Date date = Date.from(zonedDateTime.toInstant());
            input.setTime(date);
            input.setSponsor(sponsor.getText());
            input.setGoodsId(goodsTV.getGoodsId());
            input.setPrice(Long.parseLong(goodsTV.getPrice()));
            input.setCommit(commit.getText());
            inputs.add(input);

        }
        ServerResponse response = iInputService.insertOrUpdateInput(inputs);
        assembleChoiceBox();
        if (response.isSuccess()){

            new Alert(Alert.AlertType.INFORMATION, response.getMsg(),new ButtonType[]{ButtonType.CLOSE}).show();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(),new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }

    private void assembleChoiceBox(){
        System.out.println(goodsCB.getSelectionModel().getSelectedIndex());
        //解决实时刷新问题
//        goodsCB.getItems().remove(goodsCB.getSelectionModel().getSelectedIndex());
        providerCB.getSelectionModel().clearSelection();

        goodsCB.getSelectionModel().clearSelection();

        inputId.setText(UUID.randomUUID().toString());
        type.setText(null);
        contacts.setText(null);

        allNumber.setText(null);
        allPrice.setText(null);
        kindNumber.setText(null);
        //operator.setText(null);
        sponsor.setText(null);
        commit.setText(null);
        tableView.setItems(null);


    }
    public void onDelete(){
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
    }
    private void reFresh(){
        System.out.println("刷新");
        List<GoodsTV> goodsTVList = tableView.getItems();
        Set<GoodsTV> goodsTVSet = new HashSet<>();
        int price = 0, num = 0, kindNum = 0;
        for (GoodsTV goodsTV: goodsTVList){
            num+=Integer.parseInt(goodsTV.getNumber());
            price+=Double.parseDouble(goodsTV.getNumber())*Double.parseDouble(goodsTV.getPrice());
            goodsTVSet.add(goodsTV);
        }
        Iterator<GoodsTV> goodsTVIterator = goodsTVSet.iterator();
        while (goodsTVIterator.hasNext()){

            GoodsTV goodsTV = goodsTVIterator.next();
            System.out.println(goodsTV.getGoodsId());
            kindNum ++;
        }
        kindNumber.setText(kindNum+"");
        allNumber.setText(num+"");
        allPrice.setText(price+"");
    }


}




