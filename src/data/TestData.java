package data;

import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    void foo(String b, String... a){

    }



    public static List<Category> getTestCategories(){
        //Objekt 1
        Category hauptstaedte = new Category();
        hauptstaedte.setName("Hauptstädte");
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von Deutschland?","Berlin"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von den Niederlanden?","Amsterdam"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von der Schweiz?","Bern"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von Syrien?","Damaskus"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von Griechenland?","Athen"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von Frankreich?","Paris"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von China?","Peking"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von den USA?","Washington D.C."));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von Russland?","Moskau"));
        hauptstaedte.add(new Question("Wie heißt die Hauptstadt von Großbritannien?","London"));

        var result = new ArrayList<Category>();
        result.add(hauptstaedte);
        Category java = new Category();
        java.setName("Java");
        java.add(new Question("Which of the below is valid way to instantiate an array in java? \n 1:int myArray [] = {1, 3, 5}; \n 2:int myArray [] [] = {1,2,3,4};\n 3:int [] myArray = {\"1\", \"2\", \"3\"};\n"  ,"1"));
        java.add(new Question("Which of the following statements is true?\n 1:Java has a huge devoloper community \n 2:Java is used only in NASA's space related applications \n 3:Java is used only in web and mobile applications\n", "1"));
        java.add(new Question("Which method is the starting point for all Java programs?","main" ));
        java.add(new Question("Fill the blank to declare a method called: hello() \n void _____()","hello"));
        java.add(new Question("Which method prints text in a Java program?\n1: out.writeText() \n2: System.out.println()\n3: System.printText()\n","2"));
        java.add(new Question("Sigle-line comments are created using:", "//"));
        java.add(new Question("Which variable type wold you use for a city name?", "String"));
        java.add(new Question(" What is the result of the following code ? \nint x =15;inty=4;\nint result=x/y;\nSystem.out.println(result);\n","3"));
        java.add(new Question("What value is stored in the result variable? \nint x =8;inty=5;\nint result=x%y;\nSystem.out.println(result);\n","3"));
        java.add(new Question("Fill the blank to print 11.\nint a=10;\n___a;\nSystem.out.println(a);\n","++"));

        result.add(java);
        return result;
    }
}
