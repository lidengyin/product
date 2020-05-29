package cn.hctech2006.product.product.view.controller;

import cn.hctech2006.product.product.ProductApplication;
import cn.hctech2006.product.product.bean.Operator;
import cn.hctech2006.product.product.common.Const;
import cn.hctech2006.product.product.common.ServerResponse;
import cn.hctech2006.product.product.common.TokenCache;
import cn.hctech2006.product.product.service.IOperatorService;
import cn.hctech2006.product.product.service.impl.OperatorServiceImpl;
import cn.hctech2006.product.product.view.MainApp;
import cn.hctech2006.product.product.view.MainView;
import cn.hctech2006.product.product.view.test.FrameApi;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.security.auth.login.CredentialException;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 登录窗口的View控制器
 */
@FXMLController
public class LoginViewController implements ControlledStage, Initializable {
    @Autowired
    private IOperatorService operatorService;
    @FXML private TextField username;
    @FXML private TextField password;
    private StageController myController;
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event){

    }

    @Override
    public void setStageController(StageController stageController) {
        this.myController=stageController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void goToMain(){
        System.out.println("username:"+username.getText());
        System.out.println("password:"+password.getText());
        ServerResponse response = operatorService.selectByUsernameAndPassword(username.getText().toString(), password.getText().toString());
        if (!response.isSuccess()){
            new Alert(Alert.AlertType.ERROR ,response.getMsg()
                    ,new ButtonType[]{ButtonType.CLOSE}).show();

        }else{
            Operator operator = (Operator) response.getData();

            TokenCache.setKey(Const.CURRENT_USER, operator);
            ProductApplication.showView(MainView.class);
        }

        //myController.setStage(MainApp.mainViewID, MainApp.loginViewID);

    }
}
