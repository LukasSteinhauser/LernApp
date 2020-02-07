package data;
import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AddCategory {

    public static Category erstelleCategory(){
        Scanner eingabe = new Scanner(System.in);

        Category userCategory = new Category();
        System.out.print("Wie lautet der Name deiner Kategorie : ");
        String bekommeKategorieName = eingabe.nextLine();
        userCategory.setName(bekommeKategorieName);

        System.out.println("Nun fragen und Antworten zu der Kategorie " + userCategory.getName() + " hinzufuegen.");

        boolean wiederhole = true;
        while (wiederhole){
            System.out.println("\nNeue Frage ? bitte druecke n oder N ");
            System.out.println("Fertig ? beliebige Taste druecken !");
            char auswahl = eingabe.nextLine().charAt(0);

            if (auswahl == 'n' || auswahl == 'N'){

                System.out.println("Fuer Frage mit freier Antwort druecke 1.");
                System.out.println("Fuer multiple choice Frage druecke 2.");

                int frageTyp = eingabe.nextInt();
                eingabe.nextLine();

                if(frageTyp == 1){

                    System.out.print("Die Frage lautet: ");
                    String bekommeFrage = eingabe.nextLine();
                    System.out.print("Die Antwort lautet: ");
                    String bekommeAntwort = eingabe.nextLine();

                    userCategory.add(new Question(bekommeAntwort, bekommeFrage));
                }

                if(frageTyp == 2){
                    System.out.print("Frage stellen: ");
                    String frage = eingabe.nextLine();

                    System.out.println("\nNun die Moeglichkeiten mit der Nummerierung (Z.b. a_ b_ c_ ...)  eingeben. ");
                    System.out.println("Hinweis: Max der Moeglichkeiten ist 5.\nZum Beenden \"exit\" eingeben.\n");

                    List<String> frageUndMoeglicheAntworten = new ArrayList<String>();

                    frageUndMoeglicheAntworten.add(frage);

                    for(int i = 1; i < 6; i++){
                            System.out.println("Auswahl " + i + " :");
                            String line = eingabe.nextLine();

                            if ("exit".equals(line)){
                                break;
                        }
                        frageUndMoeglicheAntworten.add(line);
                    }

                    System.out.println("Die richtige Antwort eingeben: ");
                    String dieAntwort = eingabe.nextLine();
                    userCategory.add(new Question(dieAntwort, frageUndMoeglicheAntworten.toArray(new String[frageUndMoeglicheAntworten.size()])));
                }
            }

            else {
                wiederhole = false;
            }
        }
        System.out.println("Die Kategorie " + userCategory.getName() + " wurde erstellt\n" +
                "sie hat " + userCategory.size() + " Fragen & Antworten.\n");

        return userCategory;
    }

    public static List<Category> meineMappe(Category userCategory){

        var mappe = TxtData.initCategories();
        mappe.add(userCategory);

        TxtData.saveCategory(userCategory);

        return mappe;
    }

}
