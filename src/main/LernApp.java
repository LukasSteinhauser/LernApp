package main;

import data.AddCategory;
import data.TestData;
import data.TxtData;
import data.XMLData;
import model.Category;
import model.Question;

import java.util.*;

public class LernApp {

    public static void main(String[] args) {
        //Lukas Beispiel
        System.out.println("Eine Kategorie hinzufuegen? druecke 1");
        System.out.println("Weiter mit den Verfuegbaren Kategorien? druecke 2"); // verbessern
        int auswahl = Scan.nextInt();

        if (auswahl == 1){
            var newCategory = AddCategory.erstelleCategory();

            var getMeineMappe = TxtData.initCategories();
            getMeineMappe.add(newCategory);
            TxtData.saveCategory(newCategory);
        }
        else {
            System.out.println("okay"); //nur Test
        }

        System.out.println("Welches Thema möchtest du?\n");

        var kategorien = TxtData.initCategories();

        for(int i = 0; i< kategorien.size(); ++i){
            System.out.println(i+": "+kategorien.get(i).getName());
        }
        System.out.println("Exit: Programm beenden");
        System.out.println();

        while(true){

            System.out.print("Thema Nr: ");
            var eingabe = Scan.nextLine();
            int gewaehlteZahl = -2;

            if("exit".equals(eingabe.toLowerCase())){
                System.out.println("Auf wiedersehen ^^");
                break;
            }


            try{
                gewaehlteZahl = Integer.parseInt(eingabe);
            }catch(NumberFormatException ex){

            }

            if(gewaehlteZahl == -2) continue;

            if(kategorien.size()>=gewaehlteZahl+1){
                var richtigeAntworten = 0;
                Category gewaehlteKategorie = kategorien.get(gewaehlteZahl);

                Collections.shuffle(gewaehlteKategorie);

                int fragenAnzahl;
                Scanner scanner = new Scanner(System.in);
                while (true){
                    System.out.println("wie viele Fragen sollen zu dem Thema beantwortet werden ?");
                    fragenAnzahl = scanner.nextInt();
                    if (fragenAnzahl >kategorien.get(gewaehlteZahl).size()) {   System.out.println("Fragenanzahl darf nicht mehr als "+kategorien.get(gewaehlteZahl).size()+" sein");

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

                System.out.println(richtigeAntworten+" von "+fragenAnzahl+" richtige Antworten");
                System.out.println();
            }else{
                System.out.println("Bitte den Index eines vorhandenen Themas eingeben");
            }
        }
    }

    static boolean showSimpleQuestion(Question question){

        System.out.print(question.getFrage()+" ");
        var antwort = Scan.nextLine();

        System.out.println();
        if(question.getAntwort().equals(antwort)){
            System.out.println("Richtig!");
            System.out.println();
        }else{
            System.out.println("Falsch, die richtige Antwort ist: "+question.getAntwort());
            System.out.println();
        }

        return question.getAntwort().equals(antwort);
    }
}
