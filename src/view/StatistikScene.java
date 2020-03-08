package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.ThemaStatistik;
import model.FrageStatistik;
import model.UserProfile;

public class StatistikScene {

    private UserProfile profile;

    private StatistikScene() {
    }

    public static Scene newInstance(UserProfile profile, Stage primaryStage) {
        Pane mainPanel = init(profile);
        return new Scene(new ScrollPane(mainPanel),500,350);
    }

    public static Scene newInstance(UserProfile profile, double width, double height, Stage primaryStage) {
        Pane mainPanel = init(profile);
        return new Scene(mainPanel, width, height);
    }

    private static Pane init(UserProfile profile) {

        VBox result = new VBox();
        result.setSpacing(5);
        for(ThemaStatistik cs : profile.getScores()){
            result.getChildren().add(newCategoryScoreDisplay(cs));
        }

        return result;
    }

    private static Node newCategoryScoreDisplay(ThemaStatistik themaStatistik) {
        TitledPane result = new TitledPane();
        result.setText(themaStatistik.getCategoryName());
        GridPane grid = new GridPane();
        result.setContent(grid);

        Label questionTextHeader = new Label("Frage");
        questionTextHeader.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(questionTextHeader,0,0);
        Label correctHeader = new Label("Richtig");
        correctHeader.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(correctHeader,1,0);
        Label wrongHeader = new Label("Falsch");
        wrongHeader.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.add(wrongHeader,2,0);


        var scoreList = themaStatistik.getScores();
        for (int i = 0;i<scoreList.size();++i) {
            FrageStatistik score = scoreList.get(i);

            Label questionLbl = new Label(score.getQuestionName());
            questionLbl.setWrapText(true);
            grid.add(questionLbl,0,i+1);

            String percents = score.getTotalTries()==0?"":" ("+(double)score.getSuccess()/score.getTotalTries()*100+"%)";
            Label correctLbl = new Label(score.getSuccess()+percents);
            correctLbl.setWrapText(true);
            grid.add(correctLbl,1,i+1);

            percents = score.getTotalTries()==0?"":" ("+(double)score.getFailure()/score.getTotalTries()*100+"%)";
            Label wrongLbl = new Label(score.getFailure()+percents);
            wrongLbl.setWrapText(true);
            grid.add(wrongLbl,2,i+1);

        }

        ColumnConstraints questionColConstr = new ColumnConstraints();
        questionColConstr.setPercentWidth(50);
        ColumnConstraints successColConstr = new ColumnConstraints();
        successColConstr.setPercentWidth(25);
        grid.getColumnConstraints().addAll(questionColConstr, successColConstr);

        return result;
    }
}