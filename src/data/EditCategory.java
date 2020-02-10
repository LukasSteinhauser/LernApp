package data;

import main.Scan;
import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class EditCategory {

    public static void editFragen(Question question, Category category, int dieseFrage) {

        for (int i = 0; i < question.indexLength(); i++) { // checke jedes index vom Question

            System.out.println(question.getFrageEinzeln(i));
            if(i == 0){
                System.out.println("\nEdit ? 1\nso lassen ? 2 \nFertig ? 3 ");
            }

            else{
                System.out.println("\nEdit ? 1\nso lassen ? 2 \nFertig ? 3 \nDelete ? 4");
            }

            int auswahl = Scan.nextInt();

            if (auswahl == 1) {
                System.out.print("Wie lautet deine Änderung: ");
                String newIndexOfQuestion = Scan.nextLine();
                question.setFrageEinzeln(i, newIndexOfQuestion);
            }

            if (auswahl == 4) {

                if (question.indexLength() > 0 ){ // nur multiple choice Fragen

                    List<String> tempList = new ArrayList<String>();

                    for(int j = 0; j < question.indexLength(); j++){ // kopiere in ArrayList
                        tempList.add(question.getFrageEinzeln(j));
                    }

                    if(i > 0) {
                        tempList.remove(i);
                    }

                    String newList[] = tempList.toArray(new String[tempList.size()]);
                    question.setFrage(newList);

                }
            }

            if (auswahl == 2) {
                System.out.println("Okay !!\n");
            }

            if (auswahl == 3) {
                category.set(dieseFrage, question);
                TxtData.saveCategory(category);
                break;
            }
        }

        System.out.print("\nDie Antwort der Frage ist: " + question.getAntwort() + "\n\n");
        System.out.println("Diese Antwort bearbeiten ? tippe \"1\" \nDiese Antwort behalten ? tippe \"2\" ");
        int auswahl2 = Scan.nextInt();

        if (auswahl2 == 1) {
            System.out.print("Wie lautet deine Änderung: ");
            String neueAntwort = Scan.nextLine();
            question.setAntwort(neueAntwort);
        }
        if(auswahl2 == 2){
            System.out.println("Okay !!");
        }

        category.set(dieseFrage,question);
        TxtData.saveCategory(category);
    }
}
