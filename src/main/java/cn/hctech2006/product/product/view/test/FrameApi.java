package cn.hctech2006.product.product.view.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FrameApi extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        GridPane gridPane = FXMLLoader.load(getClass().getClassLoader().getResource("LoginView.fxml"));
        Scene scene = new Scene(gridPane,350,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("进销存系统登录页面");
        primaryStage.show();
    }

    public void skipToLogin(){

    }
    public void skipToMain(){

    }
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
//        System.out.println(dir);
        launch(args);
    }
}
