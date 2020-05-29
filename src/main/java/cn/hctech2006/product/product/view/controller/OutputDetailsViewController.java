package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.DO.InputDO;
import cn.hctech2006.product.product.DO.OutputDO;
import cn.hctech2006.product.product.bean.*;
import cn.hctech2006.product.product.common.Const;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.service.*;
import cn.hctech2006.product.product.view.tv.InputTV;
import cn.hctech2006.product.product.view.tv.OutputTV;
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
public class OutputDetailsViewController implements Initializable {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IProviderService providerService;
    @Autowired
    private IInputService iInputService;
    @Autowired
    IOutputService outputService;
    @Autowired
    private IOperatorService operatorService;
    @FXML private TableView<OutputTV> tableView;
    @FXML private TableColumn<OutputTV,String> inputIdCol;
    @FXML private TableColumn<OutputTV,String> goodsNameCol;
    @FXML private TableColumn<OutputTV,String> goodsIdCol;
    @FXML private TableColumn<OutputTV,String> priceCol;
    @FXML private TableColumn<OutputTV,String> numberCol;
    @FXML private TableColumn<OutputTV,String> allPriceCol;
    @FXML private TableColumn<OutputTV,String> providerCol;
    @FXML private TableColumn<OutputTV,String> sponsorCol;
    @FXML private TableColumn<OutputTV,String> operatorCol;
    @FXML private TableColumn<OutputTV,String> timeCol;
    @FXML private ChoiceBox<String> goodsCB;
    @FXML private CheckBox checkBox;
    @FXML private DatePicker datePickerFrom;
    @FXML private DatePicker datePickerTo;
    @FXML TextField keyword;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Output> outputs = outputService.selectAll();
        List<OutputTV> outputTVList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Output output: outputs){
            Goods goods = goodsService.selectByGoodsId(output.getGoodsId());
            Provider provider = providerService.selectByProviderId(output.getProviderId());
            Operator operator = operatorService.selectByOperatorId(output.getOperatorId());
            tableView.getItems().add(new OutputTV(
                    goods.getName(),output.getOutportId()
                    ,provider.getName(),output.getPrice()+""
                    ,output.getGoodsId(), output.getNumber()+""
                    ,(Integer.parseInt(output.getNumber())*output.getPrice())+"", output.getSponsor()
                    ,operator.getName(),formatter.format(output.getTime())
            ));
        }
        inputIdCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("outputId"));
        goodsIdCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("goodsId"));
        goodsNameCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("goodsName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("price"));
        numberCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("number"));
        allPriceCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("allPrice"));
        providerCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("providerName"));
        sponsorCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("sponsor"));
        operatorCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("operator"));
        timeCol.setCellValueFactory(new PropertyValueFactory<OutputTV,String>("time"));
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
        List<Output> outputs = outputService.selectAll();
        assembleView(outputs);

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

        OutputDO outputDO = new OutputDO();
        outputDO.setDateFrom(from);
        outputDO.setDateTo(to);
        outputDO.setGoodsName(key);

        if (type != null && key != null){
            List<Output> outputs = outputService.selectOutputBykeyWord(outputDO);
            assembleView(outputs);
        }else{
            new Alert(Alert.AlertType.ERROR, "关键字缺失", new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    private void assembleView(List<Output> outputs){
        List<OutputTV> outputTVList = new ArrayList<>();
        for(Output output : outputs){
            Goods goods = goodsService.selectByGoodsId(output.getGoodsId());
            Provider provider = providerService.selectByProviderId(output.getProviderId());
            Operator operator = operatorService.selectByOperatorId(output.getOperatorId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            OutputTV outputTV = new OutputTV(
                    goods.getName(),output.getOutportId()
                    ,provider.getName(),output.getPrice()+""
                    ,output.getGoodsId(), output.getNumber()+""
                    ,(Integer.parseInt(output.getNumber())*output.getPrice())+"", output.getSponsor()
                    ,operator.getName(),formatter.format(output.getTime()
            ));
            outputTVList.add(outputTV);
        }
        ObservableList<OutputTV> outputTVS = FXCollections.observableArrayList(outputTVList);
        tableView.setItems(outputTVS);
        goodsCB.getSelectionModel().clearSelection();
        datePickerFrom.getEditor().setText(null);
        datePickerTo.getEditor().setText(null);
        checkBox.setSelected(false);
        keyword.setText(null);

    }
}
