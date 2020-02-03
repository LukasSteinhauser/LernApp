package data;

import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static List<Category> getTestCategories(){
        Category hauptstaedte = new Category();
        hauptstaedte.setName("Hauptstädte");
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von Deutschland?","Berlin"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von den Niederlanden?","Amsterdam"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von der Schweiz?","Bern"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von Syrien?","Damaskus"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von Griechenland?","Athen"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von Frankreich?","Paris"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von China?","Peking"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von den USA?","Washington D.C."));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von Russland?","Moskau"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstatt von Großbritannien?","London"));

        var result = new ArrayList<Category>();
        result.add(hauptstaedte);
        Category java = new Category();
        java.setName("Java");
        java.add(new Question("Which of the below is valid way to instantiate an array in java? \n 1:int myArray [] = {1, 3, 5}; \n 2:int myArray [] [] = {1,2,3,4};\n 3:int [] myArray = {\"1\", \"2\", \"3\"};"  ,"Berlin"));
        result.add(java);
        return result;
    }
}
