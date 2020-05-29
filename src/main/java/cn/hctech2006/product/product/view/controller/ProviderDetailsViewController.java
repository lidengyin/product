package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.bean.Customer;
import cn.hctech2006.product.product.bean.Provider;
import cn.hctech2006.product.product.common.Const;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.service.ICustomerService;
import cn.hctech2006.product.product.service.IProviderService;
import cn.hctech2006.product.product.view.tv.CustomerTV;
import cn.hctech2006.product.product.view.tv.ProviderTV;
import com.google.common.collect.Lists;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class ProviderDetailsViewController implements Initializable {
    @Autowired
    private IProviderService providerService;
    @FXML
    private ChoiceBox<String> typeCB;
    @FXML private TableView<ProviderTV> tableView;
    @FXML private TextField keyword;
    @FXML private TableColumn<ProviderTV,String> nameCol;
    @FXML private TableColumn<ProviderTV,String> providerIdCol;
    @FXML private TableColumn<ProviderTV,String> addressCol;
    @FXML private TableColumn<ProviderTV,String> shortsCol;
    @FXML private TableColumn<ProviderTV,String> zipCol;
    @FXML private TableColumn<ProviderTV,String> telephoneCol;
    @FXML private TableColumn<ProviderTV,String> faxCol;
    @FXML private TableColumn<ProviderTV,String> contactsCol;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<String> typeList = Lists.newArrayList();
        typeList.add(Const.TBTYPE.CUSTOMERNAME.getName());
        typeList.add(Const.TBTYPE.CUSTOMERSHORTS.getName());
        typeCB.getItems().addAll(typeList);
        ServerResponse response = providerService.selectAll();
        assembleTableview(response);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        providerIdCol.setCellValueFactory(new PropertyValueFactory<>("providerId"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        shortsCol.setCellValueFactory(new PropertyValueFactory<>("shorts"));
        zipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        faxCol.setCellValueFactory(new PropertyValueFactory<>("fax"));
        contactsCol.setCellValueFactory(new PropertyValueFactory<>("contacts"));

    }
    private void assembleTableview(ServerResponse response){
        List<Provider> providerList = (List<Provider>) response.getData();
        List<ProviderTV> providerTVList = Lists.newArrayList();
        for (Provider provider:providerList){
            providerTVList.add(new ProviderTV(provider.getName(), provider.getShorts()
                    ,provider.getAddress(), provider.getZip()
                    ,provider.getTelephone() ,provider.getFax()
                    ,provider.getContacts(), provider.getProviderId()));
        }
        ObservableList<ProviderTV> providerTVS = FXCollections.observableArrayList(providerTVList);
        tableView.setItems(providerTVS);

    }
    public void showByKeyword(){
        //tableView.setItems(null);
        String type = ((typeCB.getValue() != null)?typeCB.getValue():"");
        Provider provider = new Provider();
        if (type.equals(Const.TBTYPE.CUSTOMERNAME.getName())){
            String goodsName = keyword.getText();
            provider.setName(goodsName);
            ServerResponse response = providerService.selectByNameORShorts(provider);
            assembleTableview(response);
        }else if (type.equals(Const.TBTYPE.CUSTOMERSHORTS.getName())){
            String goodShorts = keyword.getText();
            provider.setShorts(goodShorts);
            ServerResponse response = providerService.selectByNameORShorts(provider);
            assembleTableview(response);

        }else{
            new Alert(Alert.AlertType.ERROR, "没有检测到查询条件",new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }
    public void showAll(){
        ServerResponse response = providerService.selectAll();
        assembleTableview(response);
    }
}
