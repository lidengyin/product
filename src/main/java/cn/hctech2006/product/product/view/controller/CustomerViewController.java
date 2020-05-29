package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.bean.Customer;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.service.ICustomerService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

@FXMLController
public class CustomerViewController implements Initializable {
    @Autowired
    private ICustomerService customerService;
    @FXML
    private TextField name;
    @FXML
    private TextField shorts;
    @FXML
    private TextField address;
    @FXML
    private TextField zip;
    @FXML
    private TextField telephone;
    @FXML
    private TextField fax;
    @FXML
    private TextField contacts;
    @FXML
    private TextField contactsTel;
    @FXML
    private TextField email;
    @FXML
    private TextField bank;
    @FXML
    private TextField account;
    @FXML
    private TextField available;
    @FXML
    private TextField name1;
    @FXML
    private TextField shorts1;
    @FXML
    private TextField address1;
    @FXML
    private TextField zip1;
    @FXML
    private TextField telephone1;
    @FXML
    private TextField fax1;
    @FXML
    private TextField contacts1;
    @FXML
    private TextField contactsTel1;
    @FXML
    private TextField email1;
    @FXML
    private TextField bank1;
    @FXML
    private TextField account1;
    @FXML
    private TextField available1;

    @FXML
    private ChoiceBox<Customer> choiceBox;
    @FXML
    public void addCustomer(){
        Customer customer = new Customer();
        customer.setAccount(account.getText());
        //customer.setCustomerId(UUID.randomUUID().toString());
        customer.setAddress(address.getText());
        customer.setAvailable(1);
        customer.setBank(bank.getText());
        customer.setAccount(account.getText());
        customer.setContacts(contacts.getText());
        customer.setContactsTel(contactsTel.getText());
        customer.setEmail(email.getText());
        customer.setFax(fax.getText());
        customer.setName(name.getText());
        customer.setShorts(shorts.getText());
        customer.setTelephone(telephone.getText());
        customer.setZip(zip.getText());
        ServerResponse response = customerService.insertOrUpdate(customer);
        if (!response.isSuccess()){
            new Alert(Alert.AlertType.ERROR ,response.getMsg()
                    ,new ButtonType[]{ButtonType.CLOSE}).show();
        }else{
            new Alert(Alert.AlertType.INFORMATION ,response.getMsg()
                    ,new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }
    @FXML
    protected void fillTextField(){
        //使用ChoiceBox的地方只需要使用getValue就可以得到一个复合对象
        Customer customer = choiceBox.getValue();
        name1.setText(customer.getName());
        address1.setText(customer.getAddress());
        shorts1.setText(customer.getShorts());
        zip1.setText(customer.getZip());
        telephone1.setText(customer.getTelephone());
        contacts1.setText(customer.getContacts());
        contactsTel1.setText(customer.getContactsTel());
        email1.setText(customer.getEmail());
        bank1.setText(customer.getBank());
        account1.setText(customer.getAccount());
        fax1.setText(customer.getFax());


    }
    public void updateCustomer(){
        Customer customer = choiceBox.getValue();
        customer.setAddress(address1.getText());
        customer.setAvailable(1);
        customer.setBank(bank1.getText());
        customer.setAccount(account1.getText());
        customer.setContacts(contacts1.getText());
        customer.setContactsTel(contactsTel1.getText());
        customer.setEmail(email1.getText());
        customer.setFax(fax1.getText());
        customer.setName(name1.getText());
        customer.setShorts(shorts1.getText());
        customer.setTelephone(telephone1.getText());
        customer.setZip(zip1.getText());
        ServerResponse response = customerService.insertOrUpdate(customer);
        if (response.isSuccess()){
            new Alert(Alert.AlertType.INFORMATION, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }

    public void deleteCustomer(){
        Customer customer = choiceBox.getValue();
        ServerResponse response = customerService.updateAvailable(customer.getCustomerId());
        if (response.isSuccess()){
            name1.setText(null);
            address1.setText(null);
            shorts1.setText(null);
            zip1.setText(null);
            telephone1.setText(null);
            contacts1.setText(null);
            contactsTel1.setText(null);
            email1.setText(null);
            bank1.setText(null);
            account1.setText(null);
            fax1.setText(null);
            assembleChoiceBox();
            new Alert(Alert.AlertType.INFORMATION, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
            //initialize();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerResponse response = customerService.selectAll();
        List<Customer> customerList = (List<Customer>) response.getData();
        //把需要绑定的对象放入到choiceBox对象集合中

        //choiceBox.getItems().removeAll(customerList);
        //choice
        choiceBox.getItems().addAll(customerList);
        //设置ChoiceBox对象的converterProperty属性，
        // 使用匿名内部类生成一个StringConverter对象重写toString方法
        choiceBox.converterProperty().set(new StringConverter<Customer>() {
            @Override
            public String toString(Customer object) {
                return object.getName();
            }

            @Override
            public Customer fromString(String string) {
                return null;
            }
        });

    }
    private void assembleChoiceBox(){
        System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
        //解决实时刷新问题
        choiceBox.getItems().remove(choiceBox.getSelectionModel().getSelectedIndex());

    }

}
