package view;

import data.TxtData;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.LernApp;
import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThemaBearbeitenScene {
    private ThemaBearbeitenScene(){}

    private static List<FrageBearbeitenNode> questionsNodes;

    public static Scene newInstance(Category category, Stage primaryStage){
        if(category==null){
            category = new Category();
            category.add(new Question());
        }
        Category savingCategory = category;

        String kategorieNameString = category.getName()!=null?category.getName():"";

        VBox topPane = new VBox();
        topPane.setSpacing(5);

        TextField themaName = new TextField(kategorieNameString);
        themaName.setPromptText("Thema:");

        HBox menu = new HBox();
        topPane.getChildren().add(menu);
        menu.setSpacing(2);
        Button back = new Button("Hauptmenü");
        back.setOnAction(e->primaryStage.setScene(HauptmenuScene.newInstance(primaryStage, null)));
        menu.getChildren().add(back);
        Button save = new Button("Speichern");
        back.setOnAction(event->{
            String newKategoriename = themaName.getText();
            if(newKategoriename!=null&&!newKategoriename.isBlank()){
                savingCategory.setName(newKategoriename);
                var fragen = questionsNodes.stream().flatMap(node->{
                    Question q = node.getNewQuestion();
                    if(q.getFrage()==null||q.getFrage().isBlank()){
                        return Stream.empty();
                    }else{
                        return Stream.of(q);
                    }
                }).collect(Collectors.toList());
                if(fragen.size()>0){
                    savingCategory.clear();
                    savingCategory.addAll(fragen);
                    TxtData.saveCategory(savingCategory);
                }else{
                    //TODO Notification Bitte mindestens eine Frage, mit nichtleerem Fragetext
                }
            }else{
                //TODO Notification Bitte dem Thema einen Namen geben
            }
        });
        menu.getChildren().add(save);

        topPane.getChildren().add(themaName);

        questionsNodes = new ArrayList<>();
        for(Question q : category){
            questionsNodes.add(new FrageBearbeitenNode((q)));
        }
        topPane.getChildren().addAll(questionsNodes);

        Button addButton = new Button("+");
        addButton.setOnAction(event->{
            FrageBearbeitenNode newNode = new FrageBearbeitenNode(null);
            questionsNodes.add(newNode);
            topPane.getChildren().add(topPane.getChildren().size()-1, newNode);
        });

        topPane.getChildren().add(addButton);

        Scene scene = new Scene(topPane,500,350);
        return scene;
    }

//    private static Node getQuestionEntry(Question q){
//        if(q == null){
//            q = new Question();
//        }
//        VBox result = new VBox();
//
//        TextArea frage = new TextArea(q.getFrage());
//
//        TextField antwort = new TextField(q.getAntwort());
//
//        result.getChildren().addAll(frage, antwort);
//
//        return result;
//    }

    static class FrageBearbeitenNode extends VBox{

        private TextArea frage;

        private TextField antwort;

        private Question question;

        FrageBearbeitenNode(Question q){
            if(q == null){
                q = new Question();
            }
            question = q;

            frage = new TextArea(q.getFrage());
            frage.setPrefColumnCount(4);
            frage.setPromptText("Fragetext:");

            antwort = new TextField(q.getAntwort());
            antwort.setPromptText("Antwort:");

            getChildren().addAll(frage, antwort);

            setSpacing(2);
        }

        Question getNewQuestion(){

            String antwortString = antwort.getText();
            if(antwortString!=null&&!antwortString.isBlank()){
                question.setAntwort(antwortString);
            }

            String frageString = frage.getText();
            if(frageString!=null&&!frageString.isBlank()){
                question.setFrage(frageString);
            }
            return question;
        }
    }

}
