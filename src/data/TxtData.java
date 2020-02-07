package data;

import main.Scan;
import model.Category;
import model.Question;

import java.io.*;
import java.lang.reflect.Array;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TxtData {

    private static String basicPath = System.getenv("APPDATA")+ File.separator
            +"LernApp"+File.separator+"Data"+File.separator;

    private static String getCategorieFilePath(Category category){
        return basicPath + category.getName() + ".txt";
    }

    public  static void deleteCategory (Category category, String bestaetigung){

        var diesCategory = getCategorieFilePath(category);
        var diesesFile = new  File(diesCategory);

        final boolean meineKategorien = category.getName().equals("Java") && category.getName().equals("Haupstädte");

        if (bestaetigung.equals("ja".toLowerCase()) && meineKategorien){
            diesesFile.delete();
        }
    }

    public static void editCategory (Category category){ // wird noch erweitert

        final boolean meineKategorien = category.getName().equals("Java") && category.getName().equals("Haupstädte");

        if (meineKategorien) {

            System.out.println("Category umbennen ? 1 eingeben.");
            System.out.println("Fragen bearbeiten ? 2 eingeben.");
            int auswahl = Scan.nextInt();


            if (auswahl == 1){
                deleteCategory(category,"ja");
                System.out.print("Wie lautet deinen gewünschten Namen: ");
                String newName = Scan.nextLine();
                category.setName(newName);
                saveCategory(category);
            }

            if(auswahl == 2){
                int indexOfCategory = 0;

                for(Question question : category){

                    System.out.println("Hier ist die Frage: ");
                    System.out.println(question.getFrage());

                    System.out.println("Bearbeite die Frage ? tippe \"1\" \nDie Frage beibehalten tippe  \"2\" ");
                    int abfrage = Scan.nextInt();

                    if(abfrage == 1){

                        for (int i = 0; i < question.indexLength(); i++){ // checke jedes index vom Question

                            System.out.println(question.getFrageEinzeln(i));
                            System.out.println("Edit ? 1\nDelete ? 2\nso lassen ? 3 \nFertig ? 4 ");
                            int wasJetzt = Scan.nextInt();

                            if(wasJetzt == 1){
                                System.out.print("Wie lautet deine Änderung: ");
                                String newIndexOfQuestion = Scan.nextLine();
                                question.setFrageEinzeln(i, newIndexOfQuestion);
                            }
                            if(wasJetzt == 2){
                                // kommt noch (Möglichkeit um eine Frage zu löschen)
                            }

                            if(wasJetzt == 3){

                                // ausgabe
                            }

                            if(wasJetzt == 4){ // TODO: keine Antworten mehr sollen gespeichert werden
                                category.set(indexOfCategory,question);
                                saveCategory(category);
                                break;
                            }
                        }
                        System.out.print("Die Antwort der Frage ist: ");
                        System.out.println(question.getAntwort());
                        System.out.println("Diese Antwort bearbeiten ? tippe \"1\" \nDiese Antwort behalten ? tippe \"2\" ");
                        int wasJetzt2 = Scan.nextInt();

                        if(wasJetzt2 == 1){
                            System.out.print("Wie lautet deine Änderung: ");
                            String neueAntwort = Scan.nextLine();
                            question.setAntwort(neueAntwort);

                        }

                        if(wasJetzt2 == 2){
                            // kommt noch eine Ausgabe
                        }

                        category.set(indexOfCategory,question);
                        saveCategory(category);
                        ++indexOfCategory;
                    }
                    if(abfrage == 2){
                        break;
                    }
                }
            }
        }
    }

    public static void saveCategory(Category category){
        var path = getCategorieFilePath(category);

        try{
            File file = new File(path);

            file.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(file);

            for (var question: category) {
               writer.println(question.getAntwort());
               writer.println(question.getFrage());
               writer.println();
            }

            writer.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static List<Category> loadAllCategories(){
        File dir = new File(basicPath);

        if(dir.exists()&&dir.exists()){
            var files = new ArrayList<File>(Arrays.asList(dir.listFiles()))
                    .stream().filter(file -> {
                String name = file.getName();

                if(name.length()>=4&&".txt".equals(name.substring(name.length()-4,name.length()).toLowerCase())){
                    return true;
                }
                return false;
            }).collect(Collectors.toList());

            var result = new ArrayList<Category>();

            for (var file: files){
                Category tempCategory = new Category();
                tempCategory.setName(file.getName().replace(".txt",""));

                try{
                    BufferedReader reader = new BufferedReader(new FileReader(file));

                    boolean newQuestion = true;
                    Question tempQuestion = null;
                    var questionLines = new ArrayList<String>();
                    for(String line = reader.readLine(); line != null ; line = reader.readLine()){
                        if(line.isBlank()){
                            if(tempQuestion!=null){
                                String[] fragenListe = questionLines.toArray(new String[questionLines.size()]);

                                tempQuestion.setFrage(fragenListe);
                                tempCategory.add(tempQuestion);
                                questionLines = new ArrayList<String>();
                                tempQuestion=null;
                            }
                            newQuestion = true;
                        }else if(newQuestion){
                            tempQuestion = new Question();
                            tempQuestion.setAntwort(line);
                            newQuestion = false;
                        }else{
                            questionLines.add(line);
                        }
                    }

                    reader.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }

                result.add(tempCategory);
            }
        return result;
        }

        return Collections.emptyList();
    }
    public static List<Category> initCategories(){
        var list = loadAllCategories();
        if(list.size()==0){
            list = TestData.getTestCategories();
            for(var category : list){
                saveCategory(category);
            }
        }
        return list;
    }
}
