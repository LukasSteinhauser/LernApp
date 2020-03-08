package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Category;
import model.Question;
import model.UserProfile;
import util.ImageUtil;
import util.NodeUtil;

import java.util.function.IntSupplier;

public class QuizScene {

    private QuizScene() {
    }

    public static Scene newInstance(Stage primaryStage, IntSupplier fragenAnzahlSupplier, Category currentCategory, int indexOfQuestion) {
        return frageStellen(primaryStage, fragenAnzahlSupplier, currentCategory, indexOfQuestion);
    }

    public static Scene frageStellen(Stage primaryStage, IntSupplier fragenAnzahlSupplier, Category currentCategory, int indexOfQuestion){
        Question question = currentCategory.get(indexOfQuestion);

        StackPane layout2StackP = new StackPane();
        StackPane layout2border = new StackPane();
        layout2StackP.getChildren().addAll(layout2border);
        layout2border.setPadding(new Insets(20,20,20,20));

        VBox layout2Fragestellen =new VBox();
        layout2border.getChildren().addAll(layout2Fragestellen);
        layout2Fragestellen.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(4))));
        HBox antwortPrueferHBox = new HBox();
        antwortPrueferHBox.setPadding(new Insets(5,0,0,0));

        //layout2 Nds
        TextField input = new TextField();
        input.setPromptText("antwort:");
        input.setPrefColumnCount(20);


        Label frageTxt = new Label(question.getFrage());

        Label antwortPruefer = new Label( "");

        Button answerBtn = new Button();
        answerBtn.setText("answer");
        answerBtn.setOnAction(e-> {


            antwortPruefer.setText(input.getText()+" ist richtig");
            if(input.getText().equals(question.getAntwort())){
                antwortPruefer.setText(input.getText()+" ist richtig");
                answerBtn.setText("Next");
                antwortPrueferHBox.getChildren().addAll(ImageUtil.TRUE_VIEW,antwortPruefer);
            }else{
                antwortPruefer.setText("Falsch, die richtige Antwort lautet: "+ question.getAntwort());
                answerBtn.setText("Next");
                antwortPrueferHBox.getChildren().addAll(ImageUtil.FALSE_VIEW,antwortPruefer);
            }

            answerBtn.setOnAction(a->{
                int fragenAnzahl = fragenAnzahlSupplier.getAsInt();

                if (indexOfQuestion + 1 == fragenAnzahl ){
                    primaryStage.setScene(HauptmenuScene.newInstance(primaryStage,null));
                    answerBtn.setText("Hauptmenu");}
                else {
                    Scene nextSene = frageStellen(primaryStage, fragenAnzahlSupplier, currentCategory, indexOfQuestion + 1);
                    primaryStage.setScene(nextSene);
                }});

        });

        Button home = new Button("<-- zurück");
        home.setOnAction(e->primaryStage.setScene(HauptmenuScene.newInstance(primaryStage, null)));


        //layout2
        layout2border.setBackground(new Background(ImageUtil.MY_BG));

        layout2Fragestellen.setPadding(new Insets(60,10,10,75));
        layout2Fragestellen.getChildren().addAll( frageTxt,new HBox(input, NodeUtil.LEERZEICHEN,answerBtn),antwortPrueferHBox,home);
        frageTxt.setTextFill(Color.web("#F0FFF0"));
        antwortPruefer.setTextFill(Color.web("#F0FFF0"));

        Scene result1=new Scene(layout2StackP,500,350);

        return result1;
    }

//    public static Scene newInstance(UserProfile profile, Stage mainStage) {
//        Pane mainPanel = init(profile);
//        return new Scene(new ScrollPane(mainPanel),500,350);
//    }
//
//    public static Scene newInstance(UserProfile profile, double width, double height, Stage mainStage) {
//        Pane mainPanel = init(profile);
//        return new Scene(mainPanel, width, height);
//    }
}
