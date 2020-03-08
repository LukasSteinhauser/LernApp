package model;

public class Question {

    private String[] frage;
    private String antwort;

    public Question(){

    }

    public Question(String antwort, String... frage) {

        this.frage = frage;
        this.antwort = antwort;

    }

    public String toString() {
        return frage + "-" + antwort;
    }

    public String getFrageEinzeln(int hier){
        return frage[hier];
    }

    public void setFrageEinzeln(int hier, String frage){
        this.frage[hier] = frage;
    }

    public int indexLength (){
        return frage.length;
    }

    public String getFrage() {
        if(frage == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str:frage) {
            sb.append(str).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public void setFrage(String... frage) {
        this.frage = frage;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }
}