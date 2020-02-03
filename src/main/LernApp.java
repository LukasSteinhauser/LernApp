package main;

import data.TestData;
import model.Category;
import model.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LernApp {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        //Lukas Beispiel


        List<Category> test = TestData.getTestCategories();

        Category cat = test.get(0);

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
                for (Question question: kategorien.get(gewaehlteZahl)) {
                    if(simpleQuestion(question)){
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

    static boolean simpleQuestion(Question question){

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
