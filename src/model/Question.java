package model;

public class Question {

    private String frage;
    private String antwort;
    private String multiAntwort;

    public Question(){}

    public Question(String frage, String antwort) {

        this.frage = frage;
        this.antwort = antwort;

    }
    public Question(String frage, String antwort, String dieRichtigeAntwort ) {

        this.frage = frage;
        this.antwort = antwort;
        multiAntwort = dieRichtigeAntwort;

    }
    public String toString() { return frage + "-" + antwort; }


    public String getFrage() { return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }
}