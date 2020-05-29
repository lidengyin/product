package cn.hctech2006.product.product.view.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;

/**
 * 1.创建一个StageController控制器
 * StageController控制器主要是加载fxml资源文件和对应的Viewer控制器，
 * 生成stage对象以及对stage对象进行管理，因此StageController控制器对象也需要
 * 被注入到每个fxml的view控制器中
 */
public class StageController {
    //存储Stage对象
    private HashMap<String, Stage> stages = new HashMap<String, Stage>();

    /**
     * 把加载好的Stage放到Map中进行管理
     * @param name
     * @param stage
     */
    public void addStage(String name, Stage stage){
        stages.put(name, stage);
    }

    /**
     * 通过stages获取Stage对象
     * @param name
     * @return
     */
    public Stage getStage(String name){
        return stages.get(name);
    }

    /**
     * 保存主舞台
     * @param primaryStageName
     * @param primaryStage
     */
    public void setPrimaryStage(String primaryStageName, Stage primaryStage){
        this.addStage(primaryStageName, primaryStage);
    }

    /**
     *
     * @param name 注册好的xfml窗口文件
     * @param resources　资源地址
     * @param styles　可变参数，　init使用的初始化样式资源
     * @return　返回加载是否成功
     */
    public boolean loadStage(String name, String resources, StageStyle... styles){
        try{
            //加载XFML资源文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resources));
            //获取顶级画板
            Pane tempPane = (Pane) loader.load();
            //通过Loader获取FXML对应的ViewerCOntroller，
            // 并且将本stageController注入到VIewController中
            ControlledStage controlledStage = (ControlledStage) loader.getController();
            controlledStage.setStageController(this);
            //构建对应的Stage
            Scene tempScene = new Scene(tempPane);
            Stage tempStage = new Stage();
            tempStage.setScene(tempScene);
            //配置initStype
            for (StageStyle style : styles){
                tempStage.initStyle(style);
            }
            //将设置好的stage放到HashMap中
            this.addStage(name, tempStage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 显示Stage但是不隐藏任何Stage
     * @param name
     * @return
     */
    public boolean setStage(String name){
        this.getStage(name).show();
        return true;
    }

    /**
     * 显示Stage并且隐藏对应的窗口
     * @param show
     * @param close
     * @return
     */
    public boolean setStage(String show, String close){
        getStage(close).close();
        setStage(show);
        return true;

    }

    /**
     * 在Map中移除Stage加载对象
     * @param name
     * @return
     */
    public boolean unloadStage(String name){
        if (stages.remove(name) == null){
            System.out.println("窗口不存在请检查名称");
            return false;
        }
        System.out.println("窗口移除成功");
        return true;
    }
}
