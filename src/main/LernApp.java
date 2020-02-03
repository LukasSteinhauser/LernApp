package main;

import java.util.Scanner;

public class LernApp {
    public static void main(String[] args) {

        var englisch = new Category();
        englisch.add(new Question("frage 1:\n1     2 \n3     4", "1"));
        englisch.add(new Question("frage 2", "2"));
        englisch.add(new Question("frage 3", "3"));
        englisch.add(new Question("frage 4", "4"));
        englisch.add(new Question("frage 5", "5"));


        System.out.println("Willkommen");
        //TODO : Name einlesen

        Category category1 = new Category();
        category1.setName("englisch");
        Category category2 = new Category();
        category2.setName("Geographie");

        System.out.print(" Folgende kategorien stehen zur Auswahl : ");
        System.out.println("englisch, Geographie");
        System.out.println(" Welches Thema möchtest du probieren");

//        var scanner = new Scanner(System.in);
//        var category = scanner.nextInt();
//
//        // TODO: checke ob kategorie == englisch oder Geographie
//        System.out.println(" du hast eingegeben:" + category);
//        // TODO: if ( ... )


        int score=0;
        System.out.println(englisch.get(0).getFrage());
        Scanner input = new Scanner(System.in);
        String anwort = input.nextLine();
        if (anwort.equals(englisch.get(0).getAntwort())){ score++; }
        else {  System.out.println ("Falsch,das richtige antwort wäre " +englisch.get(0).getAntwort()); }

    }
}
