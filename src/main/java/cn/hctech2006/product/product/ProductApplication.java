package cn.hctech2006.product.product;

import cn.hctech2006.product.product.bean.Provider;
import cn.hctech2006.product.product.util.SplashScreenCustom;
import cn.hctech2006.product.product.view.*;
import cn.hctech2006.product.product.view.controller.StageController;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication extends AbstractJavaFxApplicationSupport  {

    public static void main(String[] args) {
        //SpringApplication.run(ProductApplication.class, args);
        //多个舞台并存
        launch(ProductApplication.class, LoginView.class,new SplashScreenCustom(),args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }

    //所属静态属性分别是每个界面的ID和资源文件的相对路径
    public static String mainViewID = "MainView";
    public static String mainViewRes="/MainView.fxml";
    public static String loginViewID = "LoginView";
    public static String loginViewRes="/LoginView.fxml";
    private StageController stageController;



//    @Override
//    public void start(Stage primaryStage) {
//
//        //新建一个StageController控制器
//        stageController = new StageController();
//        //将主舞台交给控制器管理
//        stageController.setPrimaryStage("primaryStage", primaryStage);
//
//        //然后对StageController控制器进行实例化并用StageController对象加载fxml资源文件
//        stageController.loadStage(loginViewID,loginViewRes);
//        stageController.loadStage(mainViewID,mainViewRes);
//        //显示MainView舞台
//        stageController.setStage(loginViewID);
//    }
}
