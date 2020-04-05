package view;

import data.TxtData;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Thema;
import util.ImageUtil;
import util.NodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HauptmenuScene {

    private HauptmenuScene() {
    }

    public static Scene newInstance(Stage primaryStage, List<Thema> themaData) {
        return hauptmenu(primaryStage, themaData);
    }

    private static Scene hauptmenu(Stage primaryStage, List<Thema> themaData){
        if(themaData == null){
            themaData = TxtData.initCategories();
        }
        List<Thema> richtigeKategorien = themaData;

        VBox layout1 =new VBox ();

        // layout1 Nds
        List<RadioButton> rdCategory = new ArrayList<>();
        for (Thema singlThema :richtigeKategorien){
            RadioButton radioButton = new RadioButton(singlThema.getName());
            radioButton.setTextFill(Color.web("#F0FFF0"));
            rdCategory.add(radioButton);
        }

        ToggleGroup group = new ToggleGroup();
        for (RadioButton rb : rdCategory ){
            rb.setToggleGroup(group);
        }

        TextField fragenAnzahlField = new TextField();
        fragenAnzahlField.setPromptText("Wie viele Fragen sollen beantwortet werden ?");
        fragenAnzahlField.setPrefColumnCount(20);

        Label fragenAnzahl_Label = new Label("Wie viele Fragen sollen beantwortet werden ?");
        fragenAnzahl_Label.setTextFill(Color.web("#F0FFF0"));
        fragenAnzahl_Label.setFont(Font.font("Arial",15));

        Button goBtn = new Button("go");
        goBtn.setOnAction(e-> {
            Thema selection = NodeUtil.selectionFromRadioButtonGroup(group,richtigeKategorien, Thema::getName);
            if(selection==null){
                noThemaSelectedMessage();
                return;
            }
            Collections.shuffle(selection);
            Integer tempInt = null;
            try{
                tempInt = Integer.parseInt(fragenAnzahlField.getText());
            }catch(NumberFormatException ex) {
            }
            int parsedInt = tempInt!=null?tempInt:selection.size();
            if (parsedInt <1 ||  parsedInt > selection.size()){
                fragenAnzahl_Label.setText("Fragenanzahl soll zwischen 1 und "+selection.size()+" sein.");
                fragenAnzahl_Label.setTextFill(Color.web("#2EFE2E"));

            } else {
                Scene scene2 = QuizScene.newInstance(primaryStage,()->parsedInt, selection, 0);
                primaryStage.setScene(scene2);
            }
        });

        Button add = new Button("Neues Thema");
        add.setOnAction(e-> { primaryStage.setScene(ThemaBearbeitenScene.newInstance(null,primaryStage));});


        Button delete = new Button("Thema Löschen");
        delete.setOnAction(e->{
            Thema forDeletion = NodeUtil.selectionFromRadioButtonGroup(group,richtigeKategorien, Thema::getName);
            if(forDeletion!=null){
                TxtData.deleteCategory(forDeletion);
                primaryStage.setScene(HauptmenuScene.newInstance(primaryStage, null));
            }else{
                noThemaSelectedMessage();
            }
        });
        Button edit = new Button("Thema bearbeiten");
        edit.setOnAction(e-> {
            Thema cat = NodeUtil.selectionFromRadioButtonGroup(group,richtigeKategorien, Thema::getName);
            if(cat!=null){
                primaryStage.setScene(ThemaBearbeitenScene.newInstance(cat,primaryStage));
            }else{
                noThemaSelectedMessage();
            }
        });


        Label themaWaehlen= new Label();
        themaWaehlen.setText("Wählen Sie ein Thema:");
        themaWaehlen.setTextFill(Color.web("#F0FFF0"));
        themaWaehlen.setFont(Font.font("Arial",15));

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
        layout1.getChildren().addAll(themaWaehlen);
        layout1.getChildren().addAll(rdVBox );
        layout1.getChildren().addAll(fragenAnzahl_Label);
        layout1.getChildren().addAll(new HBox(fragenAnzahlField, NodeUtil.LEERZEICHEN, goBtn));
        Scene result=new Scene(layout1StackP,1000,700);

        return result;
    }
    private static void noThemaSelectedMessage(){
        Alert alert = new Alert(Alert.AlertType.NONE, "Bitte wählen Sie ein Thema aus.", ButtonType.OK);
        alert.showAndWait();
    }

}
