package data;

import model.Category;
import model.Question;

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
        java.add(new Question("1",new String[]{"Which of the below is valid way to instantiate an array in java?", "1:int myArray [] = {1, 3, 5};", "2:int myArray [] [] = {1,2,3,4};", "3:int [] myArray = {\"1\", \"2\", \"3\"};"}));
        java.add(new Question("1",new String[]{"Which of the following statements is true?","1:Java has a huge devoloper community", "2:Java is used only in NASA's space related applications ", "3:Java is used only in web and mobile applications" }));
        java.add(new Question("main","Which method is the starting point for all Java programs?"));
        java.add(new Question("hello","Fill the blank to declare a method called: hello() \n void _____()"));
        java.add(new Question("2", new String[]{"Which method prints text in a Java program?", "1: out.writeText()", "2: System.out.println()", "3: System.printText()"}));
        java.add(new Question("//","Sigle-line comments are created using:"));
        java.add(new Question("String","Which variable type wold you use for a city name?"));
        java.add(new Question("3", new String[]{"What is the mappeAllerKategorien of the following code ?","\nint x =15; inty=4;\n int mappeAllerKategorien=x/y;\nSystem.out.println(mappeAllerKategorien);\n" }));
        java.add(new Question("3",new String[]{"What value is stored in the mappeAllerKategorien variable?", "\nint x =8; int y =5;\nint mappeAllerKategorien=x%y;\nSystem.out.println(mappeAllerKategorien);\n"}));
        java.add(new Question("++","Fill the blank to print 11.int a=10;\n___a;\nSystem.out.println(a);\n"));

        mappeAllerKategorien.add(java);
        return mappeAllerKategorien;
    }
}
