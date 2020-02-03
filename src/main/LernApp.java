package main;

import data.TestData;
import data.XMLData;
import model.Category;
import model.Question;

import java.util.*;

public class LernApp {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //Lukas Beispiel

        System.out.println("Welches Thema möchtest du?");
        System.out.println();

        var kategorien = TestData.getTestCategories();

        for(int i = 0; i<kategorien.size();++i){
            System.out.println(i+": "+kategorien.get(i).getName());
        }
        System.out.println("Exit: Programm beenden");
        System.out.println();

        while(true){

            System.out.print("Thema Nr: ");
            var eingabe = scanner.nextLine();
            int gewaehlteZahl = -2;

            if("exit".equals(eingabe.toLowerCase())){
                System.out.println("Auf wiedersehen ^^");
                break;
            }

            try{
                gewaehlteZahl = Integer.parseInt(eingabe);
            }catch(NumberFormatException ex){

            }


            if(kategorien.size()>=gewaehlteZahl+1){
                var richtigeAntworten = 0;
                Category gewaehlteKategorie = kategorien.get(gewaehlteZahl);

                Collections.shuffle(gewaehlteKategorie);

                int fragenAnzahl;
                Scanner scanner = new Scanner(System.in);
                while (true){
                    System.out.println("wie viele Fragen sollen zu dem Thema beantwortet werden ?");
                    fragenAnzahl = scanner.nextInt();
                    if (fragenAnzahl >10) {   System.out.println("Fragenanzahl darf nicht mehr als 10 sein");

                    }
                    else {
                        break;
                    }
                }






                for (Question question: gewaehlteKategorie.subList(0,fragenAnzahl)) {
                    if(showSimpleQuestion(question)){
                        ++richtigeAntworten;
                    }
                }

                System.out.println(richtigeAntworten+" von "+kategorien.get(gewaehlteZahl).size()+" richtige Antworten");
                System.out.println();
            }else{
                System.out.println("Bitte den Index eines vorhandenen Themas eingeben");
            }
        }
    }

    static boolean showSimpleQuestion(Question question){

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

        return question.getAntwort().equals(antwort);
    }
}
