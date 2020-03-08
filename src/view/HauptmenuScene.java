package view;

import data.Singleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Category;
import model.UserProfile;
import util.ImageUtil;
import util.NodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HauptmenuScene {

    private HauptmenuScene() {
    }

    public static Scene newInstance(Stage primaryStage, List<Category> categoryData) {
        return hauptmenu(primaryStage, categoryData);
    }

    private static Scene hauptmenu(Stage primaryStage, List<Category> categoryData){
        if(categoryData == null){
            categoryData = Singleton.getInstance().getKategorien();
        }
        List<Category> richtigeKategorien = categoryData;

        VBox layout1 =new VBox ();

        // layout1 Nds
        List<RadioButton> rdCategory = new ArrayList<>();
        for (Category singlCategory :richtigeKategorien){
            RadioButton radioButton = new RadioButton(singlCategory.getName());
            radioButton.setTextFill(Color.web("#F0FFF0"));
            rdCategory.add(radioButton);
        }

        ToggleGroup group = new ToggleGroup();
        for (RadioButton rb : rdCategory ){
            rb.setToggleGroup(group);
        }

        TextField fragenAnzahlField = new TextField();
        fragenAnzahlField.setPromptText("wie viele Fragen sollen beantwortet werden ?");
        fragenAnzahlField.setPrefColumnCount(20);

        Label FragenAnzahl_Label = new Label("wie viele Fragen sollen beantwortet werden ?");
        FragenAnzahl_Label.setTextFill(Color.web("#F0FFF0"));
        FragenAnzahl_Label.setFont(Font.font("Arial",15));

        Button goBtn = new Button("go");
        goBtn.setOnAction(e-> {
            Category selection = NodeUtil.selectionFromRadioButtonGroup(group,richtigeKategorien, Category::getName);
            Collections.shuffle(selection);
            if (Integer.parseInt(fragenAnzahlField.getText()) <1 ||  Integer.parseInt(fragenAnzahlField.getText()) > selection.size() ){
//                primaryStage.setScene(scene1);//scheint unnötig zu sein?
                FragenAnzahl_Label.setText("Fragenanzahl soll zwischen 1 und"+selection.size());
                FragenAnzahl_Label.setTextFill(Color.web("#2EFE2E"));

            }
            else {
                Scene scene2 = QuizScene.newInstance(primaryStage,()->Integer.parseInt(fragenAnzahlField.getText()), selection, 0);
                primaryStage.setScene(scene2);
            }
        });

        Button add = new Button("Add");
        //TODO
//        add.setOnAction(e-> { primaryStage.setScene(ThemaBearbeitenScene.newInstance(NodeUtil.selectionFromRadioButtonGroup(group,richtigeKategorien,Category::getName),primaryStage));});


        Button delete = new Button("Delete");
        Button edit = new Button("Edit");



        Label themaWählen= new Label();
        themaWählen.setText("Wählen Sie ein Thema");
        themaWählen.setTextFill(Color.web("#F0FFF0"));
        themaWählen.setFont(Font.font("Arial",15));

        Label willkommen = new Label();
        willkommen.setText("Willkommen");
        willkommen.setTextFill(Color.web("#F0FFF0"));
        willkommen.setFont(Font.font("Arial",30));


        //layout1


        StackPane layout1StackP = new StackPane();
        StackPane layout1border = new StackPane();
        layout1border.getChildren().addAll(layout1);
        layout1StackP.getChildren().addAll(layout1border);
        layout1border.setPadding(new Insets(20,20,20,20));
        layout1border.setBackground(new Background(ImageUtil.MY_BG));
        layout1.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(4))));


        HBox toolBar = new HBox();
        toolBar.setSpacing(10);
        toolBar.getChildren().addAll(add,delete,edit);

        VBox rdVBox = new VBox();
        rdVBox.getChildren().addAll( rdCategory);

        layout1.setSpacing(10);
        layout1.setPadding(new Insets(60,10,10,75));
        layout1.getChildren().addAll(new StackPane(willkommen));
        layout1.getChildren().addAll(new StackPane(toolBar));
        layout1.getChildren().addAll(themaWählen);
        layout1.getChildren().addAll(rdVBox );
        layout1.getChildren().addAll(FragenAnzahl_Label);
        layout1.getChildren().addAll(new HBox(fragenAnzahlField, NodeUtil.LEERZEICHEN, goBtn));
        Scene result=new Scene(layout1StackP,500,350);

        return result;
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
