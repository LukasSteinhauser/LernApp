package data;
import model.Category;
import model.Question;
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
            System.out.println("\nNeue Frage ? bitte druecke n ");
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

                    userCategory.add(new Question(bekommeFrage,bekommeAntwort));
                }
                if(frageTyp == 2){
                    System.out.print("Frage stellen: ");
                    String multiFrage = eingabe.nextLine();

                    System.out.println("Nun die Möglichkeiten mit der Nummerierung (Z.b. a_ b_ c_)  eingeben  ");
                    System.out.print("Auswahl 1: ");
                    String auswahlA = eingabe.nextLine();
                    System.out.print("Auswahl 2: ");
                    String auswahlB = eingabe.nextLine();
                    System.out.print("Auswahl 3: ");
                    String auswahlC = eingabe.nextLine();
                    System.out.println("Die richtige Antwort eingeben: ");
                    String dieAntwort = eingabe.nextLine();
                    // TODO: User gibt die richtige Antwort als a,b oder c und hat die möglichkeit mehrmals zu versuchen.
                    userCategory.add(new Question(multiFrage,auswahlA + '\n' + auswahlB + '\n' + auswahlC, dieAntwort));
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
}
