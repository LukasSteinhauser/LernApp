package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryScore {

    private Map<Integer, Score> map = new HashMap<>();

    private Category category;

    private LocalDate lastEdited;

    private List<Score> scores = new ArrayList<>();

    public String getCategoryName(){
        return category.getName();
    }

    //region getter/setter
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDate lastEdited) {
        this.lastEdited = lastEdited;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public List<Score> getScores(){
        return scores;
    }
    //endregion
}