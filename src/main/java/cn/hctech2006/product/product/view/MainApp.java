package cn.hctech2006.product.product.view;

import cn.hctech2006.product.product.view.controller.StageController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 对StageController实例化并且加载所有的窗口
 * 假设一个fxml文件就是一个窗口
 */
public class MainApp extends Application {
    //所属静态属性分别是每个界面的ID和资源文件的相对路径
    public static String mainViewID = "MainView";
    public static String mainViewRes="/MainView.fxml";
    public static String loginViewID = "LoginView";
    public static String loginViewRes="/LoginView.fxml";
    private StageController stageController;
    @Override
    public void start(Stage primaryStage) {

        //新建一个StageController控制器
       stageController = new StageController();
       //将主舞台交给控制器管理
        stageController.setPrimaryStage("primaryStage", primaryStage);

        //然后对StageController控制器进行实例化并用StageController对象加载fxml资源文件
        stageController.loadStage(loginViewID,loginViewRes);
        stageController.loadStage(mainViewID,mainViewRes);
        //显示MainView舞台
        stageController.setStage(loginViewID);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
