package main;

import data.TxtData;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class LernApp extends Application  {

    TextField bekommeKategorieNameField;
    Label bekommeKategorieLbl;
    Button bekommeKategorieBtn;

    Label frageTxt,leerzeichen,antwortPruefer, willkommen;
    TextField input,nameField;
    Button answerBtn, goBtn,add,delete,edit;

    ArrayList<RadioButton> rdCategory ;

    RadioButton freieFrageRb, multiChoiceRb;

    ToggleGroup group;
    Scene scene1,scene2,scene3;
    Image image=new Image("img/Background.jpg");
    ImageView imageView = new ImageView(image);
    ImageView imageView2 = new ImageView(image);
    Image trueImage =new Image("img/true.png",15, 15, false, false);
    Image falseImage =new Image("img/false.png",15, 15, false, false);
    ImageView trueView = new ImageView(trueImage);
    ImageView falseView = new ImageView(falseImage);

    int questionCounter=0;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.getIcons().add(new Image("img/ico.png"));
        primaryStage.setTitle("Lern App");

        List<Category> categoryData = TxtData.initCategories();
        scene1 = hauptmenu(primaryStage,categoryData);
        scene3 = AddCat(primaryStage);


        //Root Sturktur

        primaryStage.setScene(scene1);
        primaryStage.show();

    }
    public Scene hauptmenu(Stage primaryStage,List<Category> categoryData){


        // layout1 Nds
        rdCategory = new ArrayList<>();
        for (Category singlCategory :categoryData){
            RadioButton radioButton = new RadioButton(singlCategory.getName());
            radioButton.setTextFill(Color.web("#F0FFF0"));
            rdCategory.add(radioButton);
        }

        group = new ToggleGroup();
        for (RadioButton rb : rdCategory ){
            rb.setToggleGroup(group);
        }

        goBtn = new Button("go");
        goBtn.setOnAction(e-> {
            Category selection = selectionFromRadioButtonGroup(group,categoryData, Category::getName);
            scene2 = frageStellen(primaryStage, selection, 0);
            primaryStage.setScene(scene2);
        });

        add = new Button("Add");
        add.setOnAction(e-> { primaryStage.setScene(scene3);});

        delete = new Button("Delete");
        edit = new Button("Edit");

        leerzeichen = new Label();
        leerzeichen.setText("  ");

        willkommen = new Label();
        willkommen.setText("Willkommen");
        willkommen.setTextFill(Color.web("#F0FFF0"));
        willkommen.setFont(Font.font("Arial",30));

        nameField = new TextField();
        nameField.setPromptText("wie viele Fragen sollen beantwortet werden ?");
        nameField.setPrefColumnCount(20);

        //layout1


        VBox layout1 =new VBox ();



        Group layout1Group = new Group();
        layout1Group.getChildren().addAll(imageView,layout1);
        layout1Group.setAutoSizeChildren(true);

        HBox toolBar = new HBox();
        toolBar.setSpacing(10);
        toolBar.getChildren().addAll(add,delete,edit);


        VBox rdVBox = new VBox();
        rdVBox.getChildren().addAll( rdCategory);


        layout1.setSpacing(10);
        layout1.setPadding(new Insets(40,10,10,55));
        layout1.getChildren().addAll(new StackPane(willkommen));
        layout1.getChildren().addAll(new StackPane(toolBar));
        layout1.getChildren().addAll(rdVBox );
        layout1.getChildren().addAll(new HBox(nameField,leerzeichen, goBtn));
        Scene result=new Scene(layout1Group);

        return result;
    }
    public Scene frageStellen(Stage primaryStage, Category currentCategory, int indexOfQuestion){
        Question question = currentCategory.get(indexOfQuestion);

        Pane layout2Fragestellen =new Pane();
        HBox antwortPrueferHBox = new HBox();
        antwortPrueferHBox.setPadding(new Insets(5,0,0,0));

        //layout2 Nds
        input = new TextField();
        input.setPromptText("antwort:");
        input.setPrefColumnCount(20);


        frageTxt = new Label(question.getFrage());

        antwortPruefer = new Label( "");

        answerBtn = new Button();
        answerBtn.setText("answer");
        answerBtn.setOnAction(e-> {


            antwortPruefer.setText(input.getText()+" ist richtig");
            if(input.getText().equals(question.getAntwort())){
                antwortPruefer.setText(input.getText()+" ist richtig");
                answerBtn.setText("Next");
                antwortPrueferHBox.getChildren().addAll(trueView,antwortPruefer);
            }else{
                antwortPruefer.setText("Falsch, die richtige Antwort lautet: "+ question.getAntwort());
                answerBtn.setText("Next");
                antwortPrueferHBox.getChildren().addAll(falseView,antwortPruefer);
            }
            answerBtn.setOnAction(a->{
                Scene nextSene = frageStellen(primaryStage, currentCategory,indexOfQuestion+1);
                primaryStage.setScene(nextSene);
            });

        });



        //layout2




        Group layout2Group = new Group();
        layout2Group.getChildren().addAll(imageView,layout2Fragestellen);


        layout2Fragestellen.getChildren().addAll(answerBtn,input, frageTxt,antwortPrueferHBox);
        frageTxt.setLayoutX(50);frageTxt.setLayoutY(50);
        frageTxt.setTextFill(Color.web("#F0FFF0"));
        answerBtn.setLayoutX(295);answerBtn.setLayoutY(165);
        input.setLayoutX(50);input.setLayoutY(165);
        antwortPrueferHBox.setLayoutX(50);antwortPrueferHBox.setLayoutY(190);
        antwortPruefer.setTextFill(Color.web("#F0FFF0"));

        Scene result1=new Scene(layout2Group,400,235);

        return result1;
    }

    public <T> T selectionFromRadioButtonGroup(ToggleGroup group, Collection<T> source, Function<T,String> comparisonProperty){
        String selected = ((RadioButton)group.getSelectedToggle()).getText();
        for(T t : source){
            if(Objects.equals(comparisonProperty.apply(t),selected))return t;
        }
        return null;
    }


        public  Scene AddCat(Stage primaryStage){

        // AddCat Nds
            bekommeKategorieNameField= new TextField();
            bekommeKategorieNameField.setPromptText("Wie lautet der Name deiner Kategorie :");

            bekommeKategorieLbl = new Label("Wie lautet der Name deiner Kategorie :");
            bekommeKategorieLbl.setTextFill(Color.web("#F0FFF0"));
            ToggleGroup group = new ToggleGroup();
            freieFrageRb = new RadioButton("Frage mit freier Antwort");
            freieFrageRb.setToggleGroup(group);
            freieFrageRb.setSelected(true);
            freieFrageRb.setTextFill(Color.web("#F0FFF0"));
            multiChoiceRb = new RadioButton("multiple choice Frage");
            multiChoiceRb.setToggleGroup(group);
            multiChoiceRb.setTextFill(Color.web("#F0FFF0"));

            bekommeKategorieBtn = new Button("add");
            bekommeKategorieBtn.setOnAction(e ->{

            } );



        //layout AddCat
            VBox layout3 =new VBox();

            Group layout3Group = new Group();
            layout3Group.getChildren().addAll(imageView2,layout3);

            layout3.setSpacing(10);
            layout3.setPadding(new Insets(50,10,10,60));
            layout3.getChildren().addAll(bekommeKategorieLbl,bekommeKategorieNameField, this.freieFrageRb,multiChoiceRb,bekommeKategorieBtn);

            Scene result3=new Scene(layout3Group,400,235);

            return result3;
    }
}

