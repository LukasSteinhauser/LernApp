package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.LernApp;
import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class ThemaBearbeitenScene {
    private ThemaBearbeitenScene(){}

    public static Scene newInstance(Category category, Stage primaryStage){
        if(category==null){
            category = new Category();
            category.add(new Question());
        }

        String kategorieNameString = category.getName()!=null?category.getName():"";

        VBox topPane = new VBox();

        TextField kategorieName = new TextField(kategorieNameString);
        topPane.getChildren().add(kategorieName);

        List<Node> questionsNodes = new ArrayList<>();
        for(Question q : category){
            questionsNodes.add(getQuestionEntry(q));
        }
        topPane.getChildren().addAll(questionsNodes);

        Button addButton = new Button("+");
        addButton.setOnAction(event->{
            topPane.getChildren().add(topPane.getChildren().size()-2, getQuestionEntry(null));
        });

        topPane.getChildren().add(addButton);

        Button back = new Button("Hauptmenü");
        back.setOnAction(event->{
            //TODO zurück ins Hauptmenü
        });
        topPane.getChildren().add(back);

        Scene scene = new Scene(topPane,500,350);
        return scene;
    }

    private static Node getQuestionEntry(Question q){
        if(q == null){
            q = new Question();
        }
        VBox result = new VBox();

        TextArea frage = new TextArea(q.getFrage());

        TextField antwort = new TextField(q.getAntwort());

        result.getChildren().addAll(frage, antwort);

        return result;
    }

}
