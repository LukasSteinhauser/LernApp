package main;

import data.TestData;
import model.Category;
import model.Question;

import java.util.Scanner;

public class LernApp {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

//        var englisch = new Category();
//        englisch.add(new Question("frage 1:\n1     2 \n3     4", "1"));
//        englisch.add(new Question("frage 2", "2"));
//        englisch.add(new Question("frage 3", "3"));
//        englisch.add(new Question("frage 4", "4"));
//        englisch.add(new Question("frage 5", "5"));
//
//
//        System.out.println("Willkommen");
//        //TODO : Name einlesen
//
//        Category category1 = new Category();
//        category1.setName("englisch");
//        Category category2 = new Category();
//        category2.setName("Geographie");
//
//        System.out.print(" Folgende kategorien stehen zur Auswahl : ");
//        System.out.println("englisch, Geographie");
//        System.out.println(" Welches Thema möchtest du probieren");
//
////        var scanner = new Scanner(System.in);
////        var category = scanner.nextInt();
////
////        // TODO: checke ob kategorie == englisch oder Geographie
////        System.out.println(" du hast eingegeben:" + category);
////        // TODO: if ( ... )
//
//
//        int score=0;
//        System.out.println(englisch.get(0).getFrage());
//        Scanner input = new Scanner(System.in);
//        String anwort = input.nextLine();
//        if (anwort.equals(englisch.get(0).getAntwort())){ score++; }
//        else {  System.out.println ("Falsch,das richtige antwort wäre " +englisch.get(0).getAntwort()); }

        //Lukas Beispiel

        System.out.println("Welches Thema möchtest du?");

        var kategorien = TestData.getTestCategories();

        for(int i = 0; i<kategorien.size();++i){
            System.out.println(i+": "+kategorien.get(i).getName());
        }
        while(true){
            var eingabe = scanner.nextInt();
            if(kategorien.size()>=eingabe+1){
                for (Question question: kategorien.get(eingabe)) {
                    simpleQuestion(question);
                }
            }else{
                System.out.println("Bitte den Index eines vorhandenen Themas eingeben");
            }
        }
    }

    static void simpleQuestion(Question question){
        System.out.print(question.getFrage()+" ");
        var antwort = scanner.nextLine();

        System.out.println();
        if(question.getAntwort().equals(antwort)){
            System.out.println("Richtig!");
            System.out.println();
        }else{
            System.out.println("Falsch, die richtige Antwort lautet: "+question.getAntwort());
            System.out.println();
        }
    }
}
