package main;

import data.Singleton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Category;
import model.Question;
import util.ImageUtil;
import util.NodeUtil;
import view.HauptmenuScene;
import view.ThemaBearbeitenScene;

import java.util.*;
import java.util.function.IntSupplier;

public class LernApp extends Application  {


//    TextField bekommeKategorieNameField;
//    Label bekommeKategorieLbl,FragenAnzahl_Label,themaWählen;
//    Button bekommeKategorieBtn,home;
//
//    Label frageTxt,leerzeichen,antwortPruefer, willkommen;
//    TextField input, fragenAnzahlField;
//    Button answerBtn, goBtn,add,delete,edit;
//
//
//    ArrayList<RadioButton> rdCategory ;
//
//    RadioButton freieFrageRb;
//    RadioButton multiChoiceRb;
//
//    ToggleGroup group;
//    Scene scene1,scene2,scene3;

//    TextField fragenAnzahlField;

    int questionCounter=0;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.getIcons().add(new Image("img/ico.png"));
        primaryStage.setTitle("Lern App");

        List<Category> categoryData = Singleton.getInstance().getKategorien();
        Scene scene1 = HauptmenuScene.newInstance(primaryStage,categoryData);
//        Scene scene3 = AddCat(primaryStage);


        //Root Sturktur

        primaryStage.setScene(scene1);
        primaryStage.show();

    }



//        public  Scene AddCat(Stage primaryStage){
//
//        // AddCat Nds
//            TextField bekommeKategorieNameField= new TextField();
//            bekommeKategorieNameField.setPromptText("Wie lautet der Name deiner Kategorie :");
//
//            Label bekommeKategorieLbl = new Label("Wie lautet der Name deiner Kategorie :");
//            bekommeKategorieLbl.setTextFill(Color.web("#F0FFF0"));
//            ToggleGroup group = new ToggleGroup();
//            RadioButton freieFrageRb = new RadioButton("Frage mit freier Antwort");
//            freieFrageRb.setToggleGroup(group);
//            freieFrageRb.setSelected(true);
//            freieFrageRb.setTextFill(Color.web("#F0FFF0"));
//            RadioButton multiChoiceRb = new RadioButton("multiple choice Frage");
//            multiChoiceRb.setToggleGroup(group);
//            multiChoiceRb.setTextFill(Color.web("#F0FFF0"));
//
//            home = new Button("<-- zurück");
//            home.setOnAction(e-> { primaryStage.setScene(scene1);});
//
//            bekommeKategorieBtn = new Button("add");
//            bekommeKategorieBtn.setOnAction(e ->{
//
//            } );
//
//
//
//        //layout AddCat
//            StackPane layout3StackP = new StackPane();
//            StackPane layout3border = new StackPane();
//            layout3StackP.getChildren().addAll(layout3border);
//            layout3border.setPadding(new Insets(20,20,20,20));
//
//            VBox layout3 =new VBox();
//            layout3border.getChildren().addAll(layout3);
//            layout3border.setBackground(new Background(myBG));
//            layout3.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(4))));
//
//            layout3.setSpacing(10);
//            layout3.setPadding(new Insets(60,70,10,75));
//            layout3.getChildren().addAll(bekommeKategorieLbl,bekommeKategorieNameField, this.freieFrageRb,multiChoiceRb,bekommeKategorieBtn,home);
//
//            Scene result3=new Scene(layout3StackP,500,350);
//
//            return result3;
//    }
}

