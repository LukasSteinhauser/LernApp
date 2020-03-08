package data;

import model.Category;
import model.UserProfile;

import java.util.List;

public class Singleton {

    //region singleton overhead
    private static Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){
        if(singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }
    //endregion

    private UserProfile currentProfile;

    private List<String> profileSelection;

    public UserProfile getCurrentProfile(){
        if(currentProfile==null){
            currentProfile = JsonData.getUserProfile(null);
        }
        return currentProfile;
    }

    private List<Category> kategorien;

    public List<Category> getKategorien(){
        if(kategorien == null){
            kategorien = TxtData.initCategories();
        }
        return kategorien;
    }
}
