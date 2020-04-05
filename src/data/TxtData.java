package data;

import model.Thema;
import model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TxtData {

    private static String basicPath = System.getenv("APPDATA") + File.separator
            + "LernApp" + File.separator + "Data" + File.separator;

    private static String getCategorieFilePath(Thema thema) {
        return basicPath + thema.getName() + ".txt";
    }

    public static void deleteCategory(Thema thema) {

        var diesCategory = getCategorieFilePath(thema);
        var diesesFile = new File(diesCategory);

        diesesFile.delete();
    }

    public static void saveCategory(Thema thema) {
        var path = getCategorieFilePath(thema);

        try {
            File file = new File(path);

            file.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(file);

            for (var question : thema) {
                writer.println(question.getAntwort());
                writer.println(question.getFrage());
                writer.println();
            }

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Thema> loadAllCategories() {
        File dir = new File(basicPath);

        if (dir.exists()) {
            var files = new ArrayList<File>(Arrays.asList(dir.listFiles()))
                    .stream().filter(file -> {
                        String name = file.getName();

                        if (name.length() >= 4 && ".txt".equals(name.substring(name.length() - 4, name.length()).toLowerCase())) {
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());

            var result = new ArrayList<Thema>();

            for (var file : files) {
                Thema tempThema = new Thema();
                tempThema.setName(file.getName().replace(".txt", ""));

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
                                tempThema.add(tempQuestion);
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

                result.add(tempThema);
            }
            return result;
        }

        return Collections.emptyList();
    }

    public static List<Thema> initCategories() {
        var list = loadAllCategories();
        if (list.size() == 0) {
            list = SetupData.getTestCategories();
            for (var category : list) {
                saveCategory(category);
            }
        }
        return list;
    }
}
