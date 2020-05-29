package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.ProductApplication;
import cn.hctech2006.product.product.bean.Operator;
import cn.hctech2006.product.product.common.Const;
import cn.hctech2006.product.product.common.TokenCache;
import cn.hctech2006.product.product.view.*;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;
@FXMLController
public class MainViewController implements ControlledStage, Initializable {
    private StageController myController;
    private static ConfigurableApplicationContext applicationContext;
    Operator operator = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        operator = (Operator) TokenCache.getKey(Const.CURRENT_USER);
        if(operator != null){
            System.out.println("当前登录用户:"+operator.getUsername());
        }

    }

    @FXML
    public void showCustomerView(){
        if (operator.getPower() <= 1){
            ProductApplication.showView(CustomerView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    @FXML
    public void showGoodsView(){
        if (operator.getPower() <= 1){
            ProductApplication.showView(GoodsView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }

    @FXML
    public void showProviderView(){
        if (operator.getPower() <= 1){
            ProductApplication.showView(ProviderView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }

    @FXML
    public void showProviderDetailsView(){
        if (operator.getPower() <= 1){
            ProductApplication.showView(ProviderDetailsView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    @FXML
    public void showCustomerDetailsView(){
        if (operator.getPower() <= 1 || operator.getPower()== Const.power.BUYER){
            ProductApplication.showView(CustomerDetailsView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    @FXML
    public void showInputView(){
        if (operator.getPower() <= 1 || operator.getPower() == Const.power.BUYER){
            ProductApplication.showView(InputView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    @FXML
    public void showInputDetailsView(){
        if (operator.getPower() <= 1 || operator.getPower() == Const.power.STORER){
            ProductApplication.showView(InputDetailsView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    @FXML
    public void showGoodsDetailsView(){
        if (operator.getPower() <= 1){
            ProductApplication.showView(GoodsDetailsView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }

    @FXML
    public void showOutputView(){
        if (operator.getPower() <= 1 || operator.getPower() == Const.power.BUYER){
            ProductApplication.showView(OutputView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    @FXML
    public void showOutputDetailsView(){
        if (operator.getPower() <= 1 || operator.getPower() == Const.power.STORER){
            ProductApplication.showView(OutputDetailsView.class, Modality.NONE);
        }else{
            new Alert(Alert.AlertType.ERROR, "权限不足或没有登录",new ButtonType[]{ButtonType.CLOSE}).show();
        }

    }
    @Override
    public void setStageController(StageController stageController) {
        this.myController=stageController;
    }
}
