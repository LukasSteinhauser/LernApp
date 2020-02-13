package data;

import main.Scan;
import model.Category;
import model.Question;
import model.UserProfile;

import java.io.*;
import java.lang.reflect.Array;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TxtData {

    private static String basicPath = System.getenv("APPDATA") + File.separator
            + "LernApp" + File.separator + "Data" + File.separator;

    private static final String getCategorieFilePath(Category category) {
        return basicPath + category.getName() + ".txt";
    }

    public static void deleteCategory(Category category, String bestaetigung) {

        var diesCategory = getCategorieFilePath(category);
        var diesesFile = new File(diesCategory);

        final boolean meineKategorien = category.getName().equals("Java") && category.getName().equals("Haupstädte");

        if (bestaetigung.equals("ja".toLowerCase()) && !meineKategorien) {
            diesesFile.delete();
        }
    }

    public static void editCategory(Category category) {

        final boolean meineKategorien = category.getName().equals("Java") && category.getName().equals("Haupstädte");

        if (!meineKategorien) {
            System.out.println("Category umbennen ? 1 eingeben.");
            System.out.println("Fragen bearbeiten ? 2 eingeben.");
            System.out.println("Frage hinzufügen ? 3 eingeben.");
            int auswahl = Scan.nextInt();


            if (auswahl == 1) {

                deleteCategory(category, "ja");
                System.out.print("Wie lautet deinen gewünschten Namen: ");
                String newName = Scan.nextLine();

                category.setName(newName);
                saveCategory(category);
            }

            if (auswahl == 2) {

                for (int j = 0; j < category.size(); j++) {
                    Question question = category.get(j);

                    System.out.println("Hier ist die Frage: \n");
                    System.out.println(question.getFrage());

                    System.out.println("Bearbeite die Frage ? tippe \"1\" \nDie Frage beibehalten ? tippe  \"2\" \nDie Frage löschen ? tippe \"3\" ");
                    int abfrage = Scan.nextInt();

                    if (abfrage == 1) {
                        EditCategory.editFragen(question, category, j);
                    }

                    if (abfrage == 2) {
                        category.set(j, question);
                    }

                    if (abfrage == 3) {
                        category.remove(j);
                        j--;
                        saveCategory(category);
                    }
                }
            }

            if (auswahl == 3) {
                boolean wiederhole = true;
                while (wiederhole) {


                    System.out.println("Für Frage mit freier Antwort  \"1\" tippen \nFür multiple choice Frage \"2\" tippen");
                    int artDerFrage = Scan.nextInt();

                    if (artDerFrage == 1) {

                        System.out.print("Deine Frage: ");
                        String neueFrage = Scan.nextLine();

                        System.out.print("Deine Antwort: ");
                        String antwort = Scan.nextLine();

                        Question question = new Question(antwort, neueFrage);

                        category.add(question);
                        saveCategory(category);

                    }

                    if (artDerFrage == 2) {

                        System.out.print("Deine Frage: ");
                        String neueFrage = Scan.nextLine();

                        System.out.print("Wie viele mögliche Antworten werden vorgestellt ? ");
                        int elementenAnzahl = Scan.nextInt();
                        String frage[] = new String[elementenAnzahl + 1];

                        frage[0] = neueFrage;

                        for (int i = 1; i <= elementenAnzahl; i++) {

                            System.out.print("Auswahl Nr. " + i + ' ');
                            String eingabe = Scan.nextLine();
                            frage[i] = eingabe;
                        }

                        System.out.print("Deine Antwort: ");
                        String antwort = Scan.nextLine();

                        Question question = new Question(antwort, frage);

                        category.add(question);
                        saveCategory(category);
                    }

                    System.out.println("\nWeitere Fragen hinzufügen ? 1 Tippen\nFertig ? 2 Tippen");
                    int fertig = Scan.nextInt();

                    if(fertig == 1){
                        System.out.println("okay");
                    }
                    if(fertig == 2){
                        wiederhole = false;
                    }
                }
            }
        }
    }

    public static void saveCategory(Category category) {
        var path = getCategorieFilePath(category);

        try {
            File file = new File(path);

            file.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(file);

            for (var question : category) {
                writer.println(question.getAntwort());
                writer.println(question.getFrage());
                writer.println();
            }

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Category> loadAllCategories() {
        File dir = new File(basicPath);

        if (dir.exists() && dir.exists()) {
            var files = new ArrayList<File>(Arrays.asList(dir.listFiles()))
                    .stream().filter(file -> {
                        String name = file.getName();

                        if (name.length() >= 4 && ".txt".equals(name.substring(name.length() - 4, name.length()).toLowerCase())) {
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());

            var result = new ArrayList<Category>();

            for (var file : files) {
                Category tempCategory = new Category();
                tempCategory.setName(file.getName().replace(".txt", ""));

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));

                    boolean newQuestion = true;
                    Question tempQuestion = null;
                    var questionLines = new ArrayList<String>();
                    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                        if (line.isBlank()) {
                            if (tempQuestion != null) {
                                String[] fragenListe = questionLines.toArray(new String[questionLines.size()]);

                                tempQuestion.setFrage(fragenListe);
                                tempCategory.add(tempQuestion);
                                questionLines = new ArrayList<String>();
                                tempQuestion = null;
                            }
                            newQuestion = true;
                        } else if (newQuestion) {
                            tempQuestion = new Question();
                            tempQuestion.setAntwort(line);
                            newQuestion = false;
                        } else {
                            questionLines.add(line);
                        }
                    }

                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                result.add(tempCategory);
            }
            return result;
        }

        return Collections.emptyList();
    }

    public static List<Category> initCategories() {
        var list = loadAllCategories();
        if (list.size() == 0) {
            list = TestData.getTestCategories();
            for (var category : list) {
                saveCategory(category);
            }
        }
        return list;
    }

    public static void saveUserProfile(UserProfile profile){
        String path = UserProfile.basicPath + profile.getId() + ".txt";
        int historySize = 20;
        List<String> history = profile.getQuestionHistory();

        if(history.size()>historySize){
            history = history.subList(history.size()-20,history.size());
        }



        try {
            File file = new File(path);

            file.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(file);

            for (var historyEntry : history) {
                writer.println(historyEntry);
            }

            writer.println();

            for(var scoreEntry : profile.getScores().entrySet()){
                var score = scoreEntry.getValue();
                String writeString = scoreEntry.getKey() + ";" + score.getSuccess() + ";" + score.getFailure();
                writer.println(writeString);
            }

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
