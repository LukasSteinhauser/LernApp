package model;

import java.io.File;
import java.util.*;

public class UserProfile {
    public static final String basicPath = System.getenv("APPDATA") + File.separator
            + "LernApp" + File.separator + "Profiles" + File.separator;

    private String id = "default";

    private List<String> questionHistory = new ArrayList<>();

    private Map<String, Score> scores = new HashMap<>();



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

    public Map<String, Score> getScores() {
        return scores;
    }

    public void setScores(Map<String, Score> scores) {
        this.scores = scores;
    }
    //endregion
}
