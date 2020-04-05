package main;

import data.TxtData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Thema;
import view.HauptmenuScene;

import java.util.*;

public class LernApp extends Application  {

    int questionCounter=0;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.getIcons().add(new Image("img/ico.png"));
        primaryStage.setTitle("Lern App");

        List<Thema> themaData = TxtData.initCategories();
        Scene scene1 = HauptmenuScene.newInstance(primaryStage, themaData);

        primaryStage.setScene(scene1);
        primaryStage.show();

    }
}

