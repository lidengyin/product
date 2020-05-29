package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.DO.InputDO;
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
import cn.hctech2006.product.product.view.tv.InputTV;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;


import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class InputDetailsViewController implements Initializable {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IProviderService providerService;
    @Autowired
    private IInputService iInputService;
    @Autowired
    private IOperatorService operatorService;
    @FXML private TableView<InputTV> tableView;
    @FXML private TableColumn<InputTV,String> inputIdCol;
    @FXML private TableColumn<InputTV,String> goodsNameCol;
    @FXML private TableColumn<InputTV,String> goodsIdCol;
    @FXML private TableColumn<InputTV,String> priceCol;
    @FXML private TableColumn<InputTV,String> numberCol;
    @FXML private TableColumn<InputTV,String> allPriceCol;
    @FXML private TableColumn<InputTV,String> providerCol;
    @FXML private TableColumn<InputTV,String> sponsorCol;
    @FXML private TableColumn<InputTV,String> operatorCol;
    @FXML private TableColumn<InputTV,String> timeCol;
    @FXML private ChoiceBox<String> goodsCB;
    @FXML private CheckBox checkBox;
    @FXML private DatePicker datePickerFrom;
    @FXML private DatePicker datePickerTo;
    @FXML TextField keyword;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Input> inputs = iInputService.selectAll();
        List<InputTV> inputTVList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Input input: inputs){
            Goods goods = goodsService.selectByGoodsId(input.getGoodsId());
            Provider provider = providerService.selectByProviderId(input.getProviderId());
            Operator operator = operatorService.selectByOperatorId(input.getOperatorId());
            tableView.getItems().add(new InputTV(
                goods.getName(),input.getInputId()
                ,provider.getName(),input.getPrice()+""
                    ,input.getGoodsId(), input.getNumber()+""
                    ,(input.getNumber()*input.getPrice())+"", input.getSponsor()
                    ,operator.getName(),formatter.format(input.getTime())
            ));
        }
        inputIdCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("inputId"));
        goodsIdCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("goodsId"));
        goodsNameCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("goodsName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("price"));
        numberCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("number"));
        allPriceCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("allPrice"));
        providerCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("providerName"));
        sponsorCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("sponsor"));
        operatorCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("operator"));
        timeCol.setCellValueFactory(new PropertyValueFactory<InputTV,String>("time"));
        goodsCB.converterProperty().set(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                return object;
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
        ServerResponse response = goodsService.selectAll();
        List<String> goodsList = new ArrayList<>();
        goodsList.add(Const.TBTYPE.GOODSNAME.getName());
        goodsList.add(Const.TBTYPE.GOODSSHORTS.getName());
        ObservableList<String> goodsObservableList = FXCollections.observableList(goodsList);
        goodsCB.setItems(goodsObservableList);

        datePickerFrom.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public String toString(LocalDate object) {
                if (object != null){
                    return formatter.format(object);
                }else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string!= null && !string.isEmpty()){
                    return LocalDate.parse(string,formatter);
                }else{
                    return null;
                }
            }
        });
        datePickerTo.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public String toString(LocalDate object) {
                if (object != null){
                    return formatter.format(object);
                }else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string!= null && !string.isEmpty()){
                    return LocalDate.parse(string,formatter);
                }else{
                    return null;
                }
            }
        });


    }
    @FXML
    private void showAll(){
        List<Input> inputs = iInputService.selectAll();
        assembleView(inputs);

    }
    @FXML
    public void find() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String type = goodsCB.getValue();
        String key = keyword.getText();

        String dateTo = datePickerTo.getEditor().getText();
        String dateFrom = datePickerFrom.getEditor().getText();
        Date to = null;
        Date from = null;
        if (checkBox.isSelected() && dateTo != null && dateFrom != null){
            to = sdf.parse(dateTo);
            from =sdf.parse(dateFrom);
        }

        InputDO inputDO = new InputDO();
        inputDO.setDateFrom(from);
        inputDO.setDateTo(to);
        inputDO.setGoodsName(key);

        if (type != null && key != null){
            List<Input> inputs = iInputService.selectInputBykeyWord(inputDO);
            assembleView(inputs);
        }else{
            new Alert(Alert.AlertType.ERROR, "关键字缺失", new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    private void assembleView(List<Input> inputs){
        List<InputTV> inputTVList = new ArrayList<>();
        for(Input input:inputs){
            Goods goods = goodsService.selectByGoodsId(input.getGoodsId());
            Provider provider = providerService.selectByProviderId(input.getProviderId());
            Operator operator = operatorService.selectByOperatorId(input.getOperatorId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            InputTV inputTV = new InputTV(
                    goods.getName(),input.getInputId()
                    ,provider.getName(),input.getPrice()+""
                    ,input.getGoodsId(), input.getNumber()+""
                    ,(input.getNumber()*input.getPrice())+"", input.getSponsor()
                    ,operator.getName(),formatter.format(input.getTime()
            ));
            inputTVList.add(inputTV);
        }
        ObservableList<InputTV> inputTVS = FXCollections.observableArrayList(inputTVList);
        tableView.setItems(inputTVS);
        goodsCB.getSelectionModel().clearSelection();
        datePickerFrom.getEditor().setText(null);
        datePickerTo.getEditor().setText(null);
        checkBox.setSelected(false);
        keyword.setText(null);

    }
}
