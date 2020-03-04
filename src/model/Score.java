package model;

import java.time.LocalDate;

public class Score {
    private int success = 0;
    private int failure = 0;

    private LocalDate dateSignature;
    private String categoryName;
    private int questionIndex;

    private Question question;

    public Score(){

    }

    public Score(LocalDate dateSignature, String categoryName, int questionIndex) {
        this.dateSignature = dateSignature;
        this.categoryName = categoryName;
        this.questionIndex = questionIndex;
    }

    public Score(String signature){
        String[] split = signature.split("|");
        this.dateSignature = LocalDate.parse(split[0]);
        this.categoryName = split[1];
        this.questionIndex = Integer.parseInt(split[2]);
    }

    public String getQuestionName(){
        return question.getFrage();
    }

    public String getSignature(){
        return getSignature(dateSignature, categoryName, questionIndex);
    }

    public static String getSignature(LocalDate dateSignature, String categoryName, int questionIndex){
        return dateSignature.toString()+"|"+categoryName+"|"+questionIndex;
    }

    public int getTotalTries(){
        return success+failure;
    }

    //region getter/setter
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public LocalDate getDateSignature() {
        return dateSignature;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public Question getQuestion() {return question;}

    public void setQuestion(Question question) {this.question = question;}

    //endregion
}
