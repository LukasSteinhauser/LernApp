package view;

import data.TxtData;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Thema;
import model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThemaBearbeitenScene {
    private ThemaBearbeitenScene(){}

    private static List<FrageBearbeitenNode> questionsNodes;

    public static Scene newInstance(Thema thema, Stage primaryStage){
        if(thema ==null){
            thema = new Thema();
            thema.add(new Question());
        }
        Thema savingThema = thema;

        String kategorieNameString = thema.getName()!=null? thema.getName():"";

        VBox topPane = new VBox();
        topPane.setSpacing(5);

        TextField themaName = new TextField(kategorieNameString);
        themaName.setPromptText("Thema:");

        HBox menu = new HBox();
        topPane.getChildren().add(menu);
        menu.setSpacing(2);
        Button back = new Button("Hauptmenü");
        back.setOnAction(e->{
            primaryStage.setScene(HauptmenuScene.newInstance(primaryStage, null));
        });
        menu.getChildren().add(back);
        Button save = new Button("Speichern");
        save.setOnAction(event->{
            String newKategoriename = themaName.getText();
            if(newKategoriename!=null&&!newKategoriename.isBlank()){
                savingThema.setName(newKategoriename);
                var fragen = questionsNodes.stream().flatMap(node->{
                    Question q = node.getNewQuestion();
                    if(q.getFrage()==null||q.getFrage().isBlank()){
                        return Stream.empty();
                    }else{
                        return Stream.of(q);
                    }
                }).collect(Collectors.toList());
                if(fragen.size()>0){
                    savingThema.clear();
                    savingThema.addAll(fragen);
                    TxtData.saveCategory(savingThema);
                }else{
                    Alert alert = new Alert(Alert.AlertType.NONE, "Bitte mindestens eine Frage mit Fragetext versehen.", ButtonType.OK);
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.NONE, "Bitte geben Sie dem Thema einen Namen.", ButtonType.OK);
                alert.showAndWait();
            }
        });
        menu.getChildren().add(save);

        topPane.getChildren().add(themaName);

        questionsNodes = new ArrayList<>();
        for(Question q : thema){
            FrageBearbeitenNode newNode = new FrageBearbeitenNode(q);
            newNode.delete.setOnAction(deleteEvent->{
                questionsNodes.remove(newNode);
                topPane.getChildren().remove(newNode);
            });
            questionsNodes.add(newNode);
        }
        topPane.getChildren().addAll(questionsNodes);

        Button addButton = new Button("+");
        addButton.setOnAction(event->{
            FrageBearbeitenNode newNode = new FrageBearbeitenNode(null);
            newNode.delete.setOnAction(deleteEvent->{
                questionsNodes.remove(newNode);
                topPane.getChildren().remove(newNode);
            });
            questionsNodes.add(newNode);
            topPane.getChildren().add(topPane.getChildren().size()-1, newNode);
        });

        topPane.getChildren().add(addButton);

        ScrollPane scroll = new ScrollPane(topPane);
        scroll.setFitToWidth(true);

        Scene scene = new Scene(scroll,1000,700);
        return scene;
    }

    static class FrageBearbeitenNode extends VBox{

        private static long idCounter = Long.MIN_VALUE;
        private static long getNextId(){
            return idCounter++;
        }

        private long id;

        private TextArea frage;

        private TextField antwort;

        private Question question;

        Button delete;

        FrageBearbeitenNode(Question q){
            id = getNextId();

            if(q == null){
                q = new Question();
            }
            question = q;

            frage = new TextArea(q.getFrage());
            frage.setPrefColumnCount(4);
            frage.setPromptText("Fragetext:");

            antwort = new TextField(q.getAntwort());
            antwort.setPromptText("Antwort:");

            delete = new Button("-");

            HBox hbox = new HBox(delete, frage);
            hbox.setSpacing(1);
            HBox.setHgrow(frage, Priority.ALWAYS);

            getChildren().addAll(hbox, antwort);

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FrageBearbeitenNode that = (FrageBearbeitenNode) o;

            return id == that.id;
        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }
    }

}
