package data;

import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static List<Category> getTestCategories(){
        Category category = new Category();
        category.setName("Hauptstädte");
        category.add(new Question("Wie heißt die Hauptstadt von Deutschland?","Berlin"));
        category.add(new Question("Wie heißt die Hauptstadt von den Niederlanden?","Amsterdam"));
        category.add(new Question("Wie heißt die Hauptstadt von der Schweiz?","Bern"));
        category.add(new Question("Wie heißt die Hauptstadt von Syrien?","Damaskus"));
        category.add(new Question("Wie heißt die Hauptstadt von Griechenland?","Athen"));
        category.add(new Question("Wie heißt die Hauptstadt von Frankreich?","Paris"));
        category.add(new Question("Wie heißt die Hauptstadt von China?","Peking"));
        category.add(new Question("Wie heißt die Hauptstadt von den USA?","Washington D.C."));
        category.add(new Question("Wie heißt die Hauptstadt von Russland?","Moskau"));
        category.add(new Question("Wie heißt die Hauptstadt von Großbritannien?","London"));

        var result = new ArrayList<Category>();
        result.add(category);

        return result;
    }
}
