package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.bean.Customer;
import cn.hctech2006.product.product.bean.Goods;
import cn.hctech2006.product.product.bean.Provider;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.service.IProviderService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class ProviderViewController implements Initializable {
    @Autowired
    private IProviderService providerService;
    @FXML
    private ChoiceBox<Provider> choiceBox;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerResponse response = providerService.selectAll();
        List<Provider> providerList = (List<Provider>) response.getData();
        choiceBox.getItems().addAll(providerList);
        choiceBox.converterProperty().set(new StringConverter<Provider>() {
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
    public void addProvider(){
        Provider provider = new Provider();
        provider.setAddress(address.getText());
        provider.setBank(bank.getText());
        provider.setContacts(contacts.getText());
        provider.setContactsTel(contactsTel.getText());
        provider.setEmail(email.getText());
        provider.setFax(fax.getText());
        provider.setName(name.getText());
        provider.setShorts(shorts.getText());
        provider.setTelephone(telephone.getText());
        provider.setZip(zip.getText());
        ServerResponse response = providerService.insertOrUpdate(provider);
        if (response.isSuccess()){
            new Alert(Alert.AlertType.INFORMATION, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }
    @FXML
    protected void fillTextField(){
        //使用ChoiceBox的地方只需要使用getValue就可以得到一个复合对象
        Provider provider = choiceBox.getValue();
        name1.setText(provider.getName());
        address1.setText(provider.getAddress());
        shorts1.setText(provider.getShorts());
        zip1.setText(provider.getZip());
        telephone1.setText(provider.getTelephone());
        contacts1.setText(provider.getContacts());
        contactsTel1.setText(provider.getContactsTel());
        email1.setText(provider.getEmail());
        bank1.setText(provider.getBank());
        //account1.setText(provider.getAccount());
        fax1.setText(provider.getFax());


    }
    public void updateProvider(){
        Provider provider = choiceBox.getValue();
        provider.setAddress(address1.getText());
        provider.setAvailable(1);
        provider.setBank(bank1.getText());
        //provider.setAccount(account1.getText());
        provider.setContacts(contacts1.getText());
        provider.setContactsTel(contactsTel1.getText());
        provider.setEmail(email1.getText());
        provider.setFax(fax1.getText());
        provider.setName(name1.getText());
        provider.setShorts(shorts1.getText());
        provider.setTelephone(telephone1.getText());
        provider.setZip(zip1.getText());
        ServerResponse response = providerService.insertOrUpdate(provider);
        if (response.isSuccess()){
            new Alert(Alert.AlertType.INFORMATION, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }

    public void deleteProvider(){
        Provider provider = choiceBox.getValue();
        ServerResponse response = providerService.updateAvailable(provider.getProviderId());
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
            //account1.setText(null);
            fax1.setText(null);
            assembleChoiceBox();
            new Alert(Alert.AlertType.INFORMATION, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
            //initialize();
        }else{
            new Alert(Alert.AlertType.ERROR, response.getMsg(), new ButtonType[]{ButtonType.CLOSE}).show();
        }
    }
    private void assembleChoiceBox(){
        System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
        //解决实时刷新问题
        choiceBox.getItems().remove(choiceBox.getSelectionModel().getSelectedIndex());

    }
}
