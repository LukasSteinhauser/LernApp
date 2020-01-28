package model;

import java.util.Map;

public interface Frage {

    public String getText();
    public Map<Integer, String> getMultipleChoices();
    public int getCorrectAnswer();
}
