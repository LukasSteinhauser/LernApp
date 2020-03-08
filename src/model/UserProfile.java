package model;

import java.io.File;
import java.util.*;

public class UserProfile {
    public static final String basicPath = System.getenv("APPDATA") + File.separator
            + "LernApp" + File.separator + "Profiles" + File.separator;

    public static final String defaultName = "default";

    private String id = defaultName;

    private List<String> questionHistory = new ArrayList<>();

    private List<ThemaStatistik> scores = new ArrayList<>();

    /**Für eine Frage Richtig/Falsch erhöhen
     */
    private boolean editStatistic(Category category, Question question, boolean correct){
        var optionalCs = scores.stream().filter(tempCs->category.getName().equals(tempCs.getCategoryName())).findFirst();
        if(optionalCs.isPresent()){
            var cs = optionalCs.get();

            var optionalScore = cs.getScores().stream().filter(tempScore->question.getFrage().equals(tempScore.getQuestionName())).findFirst();

            if(optionalScore.isPresent()){
                var score = optionalScore.get();
                if(correct){
                    score.setSuccess(score.getSuccess()+1);
                }else{
                    score.setFailure(score.getFailure()+1);
                }
                return true;
            }
        }
        return false;
    }

    //region getter/setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getQuestionHistory() {
        return questionHistory;
    }

    public void setQuestionHistory(List<String> questionHistory) {
        this.questionHistory = questionHistory;
    }

    public List<ThemaStatistik> getScores() {
        return scores;
    }

    public void setScores(List<ThemaStatistik> scores) {
        this.scores = scores;
    }
    //endregion
}
