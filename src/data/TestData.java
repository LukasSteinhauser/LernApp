package data;

import model.Category;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static List<Category> getTestCategories(){
        Category category = new Category();
        category.setName("Hauptstädte");
        category.add(new Question("Wie heißt die Hauptstatt von Deutschland?","Berlin"));
        category.add(new Question("Wie heißt die Hauptstatt von den Niederlanden?","Amsterdam"));
        category.add(new Question("Wie heißt die Hauptstatt von der Schweiz?","Bern"));
        category.add(new Question("Wie heißt die Hauptstatt von Syrien?","Damaskus"));
        category.add(new Question("Wie heißt die Hauptstatt von Griechenland?","Athen"));
        category.add(new Question("Wie heißt die Hauptstatt von Frankreich?","Paris"));
        category.add(new Question("Wie heißt die Hauptstatt von China?","Peking"));
        category.add(new Question("Wie heißt die Hauptstatt von den USA?","Washington D.C."));
        category.add(new Question("Wie heißt die Hauptstatt von Russland?","Moskau"));
        category.add(new Question("Wie heißt die Hauptstatt von Großbritannien?","London"));

        var result = new ArrayList<Category>();
        result.add(category);

        return result;
    }
}
