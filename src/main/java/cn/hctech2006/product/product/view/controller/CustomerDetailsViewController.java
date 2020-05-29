package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.bean.Customer;
import cn.hctech2006.product.product.bean.Goods;
import cn.hctech2006.product.product.common.Const;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.service.ICustomerService;
import cn.hctech2006.product.product.view.tv.CustomerTV;
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
public class CustomerDetailsViewController implements Initializable {
    @Autowired
    private ICustomerService customerService;
    @FXML private ChoiceBox<String> typeCB;
    @FXML private TableView<CustomerTV> tableView;
    @FXML private TextField keyword;
    @FXML private TableColumn<CustomerTV,String> nameCol;
    @FXML private TableColumn<CustomerTV,String> customerIdCol;
    @FXML private TableColumn<CustomerTV,String> addressCol;
    @FXML private TableColumn<CustomerTV,String> shortsCol;
    @FXML private TableColumn<CustomerTV,String> zipCol;
    @FXML private TableColumn<CustomerTV,String> telephoneCol;
    @FXML private TableColumn<CustomerTV,String> faxCol;
    @FXML private TableColumn<CustomerTV,String> contactsCol;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<String> typeList = Lists.newArrayList();
        typeList.add(Const.TBTYPE.CUSTOMERNAME.getName());
        typeList.add(Const.TBTYPE.CUSTOMERSHORTS.getName());
        typeCB.getItems().addAll(typeList);
        ServerResponse response = customerService.selectAll();
        assembleTableview(response);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        shortsCol.setCellValueFactory(new PropertyValueFactory<>("shorts"));
        zipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        faxCol.setCellValueFactory(new PropertyValueFactory<>("fax"));
        contactsCol.setCellValueFactory(new PropertyValueFactory<>("contacts"));

    }
    private void assembleTableview(ServerResponse response){
        List<Customer> customerList = (List<Customer>) response.getData();
        List<CustomerTV> customerTVList = Lists.newArrayList();
        for (Customer customer:customerList){
            customerTVList.add(new CustomerTV(customer.getName(), customer.getShorts()
            ,customer.getAddress(), customer.getZip()
            ,customer.getTelephone() ,customer.getFax()
            ,customer.getContacts(), customer.getCustomerId()));
        }
        ObservableList<CustomerTV> customerTVS = FXCollections.observableArrayList(customerTVList);
        tableView.setItems(customerTVS);

    }
    public void showByKeyword(){
        //tableView.setItems(null);
        String type = ((typeCB.getValue() != null)?typeCB.getValue():"");
        Customer customer = new Customer();
        if (type.equals(Const.TBTYPE.CUSTOMERNAME.getName())){
            String goodsName = keyword.getText();
            customer.setName(goodsName);
            ServerResponse response = customerService.selectByNameORShorts(customer);
            assembleTableview(response);
        }else if (type.equals(Const.TBTYPE.CUSTOMERSHORTS.getName())){
            String goodShorts = keyword.getText();
            customer.setShorts(goodShorts);
            ServerResponse response = customerService.selectByNameORShorts(customer);
            assembleTableview(response);

        }else{
            new Alert(Alert.AlertType.ERROR, "没有检测到查询条件",new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }
    public void showAll(){
        ServerResponse response = customerService.selectAll();
        assembleTableview(response);
    }
}
