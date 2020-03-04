package data;

import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<Category> getTestCategories(){
        //Kategorie 1
        Category hauptstaedte = new Category();
        hauptstaedte.setName("Hauptstädte");
        hauptstaedte.add(new Question("Berlin", "Wie heißt die Hauptstadt von Deutschland?"));
        hauptstaedte.add(new Question("Amsterdam","Wie heißt die Hauptstadt von den Niederlanden?"));
        hauptstaedte.add(new Question("Bern","Wie heißt die Hauptstadt von der Schweiz?"));
        hauptstaedte.add(new Question("Damaskus","Wie heißt die Hauptstadt von Syrien?"));
        hauptstaedte.add(new Question("Athen", "Wie heißt die Hauptstadt von Griechenland?"));
        hauptstaedte.add(new Question("Paris","Wie heißt die Hauptstadt von Frankreich?"));
        hauptstaedte.add(new Question("Peking","Wie heißt die Hauptstadt von China?"));
        hauptstaedte.add(new Question("Washington D.C.","Wie heißt die Hauptstadt von den USA?"));
        hauptstaedte.add(new Question("Moskau", "Wie heißt die Hauptstadt von Russland?"));
        hauptstaedte.add(new Question("London","Wie heißt die Hauptstadt von Großbritannien?"));

        var mappeAllerKategorien = new ArrayList<Category>();
        mappeAllerKategorien.add(hauptstaedte);

        //Kategorie 2
        Category java = new Category();
        java.setName("Java");
        java.add(new Question("1","Which of the below is valid way to instantiate an array in java?", "1:int myArray [] = {1, 3, 5};", "2:int myArray [] [] = {1,2,3,4};", "3:int [] myArray = {\"1\", \"2\", \"3\"};"));
        java.add(new Question("1","Which of the following statements is true?","1:Java has a huge devoloper community", "2:Java is used only in NASA's space related applications ", "3:Java is used only in web and mobile applications" ));
        java.add(new Question("main","Which method is the starting point for all Java programs?"));
        java.add(new Question("hello","Fill the blank to declare a method called: hello()","void _____()"));
        java.add(new Question("2", "Which method prints text in a Java program?", "1: out.writeText()", "2: System.out.println()", "3: System.printText()"));
        java.add(new Question("//","Single-line comments are created using:"));
        java.add(new Question("String","Which variable type wold you use for a city name?"));
        java.add(new Question("3", "What is the result of the following code ?","int x =15; inty=4;","int result=x/y;","System.out.println(result);"));
        java.add(new Question("3","What value is stored in the result variable?", "int x =8; int y =5;","int result=x%y;","System.out.println(result);"));
        java.add(new Question("++","Fill the blank to print 11.","int a=10;","___a;","System.out.println(a);"));

        mappeAllerKategorien.add(java);
        return mappeAllerKategorien;
    }

    public static UserProfile getDefaultProfile() {
        UserProfile profile = new UserProfile();

        for(Category testCategory: getTestCategories()){

            CategoryScore catScore = new CategoryScore();
            profile.getScores().add(catScore);

            catScore.setCategory(testCategory);

            List<Score> scores = new ArrayList<>();

            for(Question question : testCategory){
                Score tempScore = new Score();
                tempScore.setQuestion(question);
                catScore.getScores().add(tempScore);
            }
        }

        return profile;
    }
}
