package model;
import java.util.ArrayList;

public class Category extends ArrayList<Question> {
    private String name ;


    public void setName(String name) {this.name = name; }

    public String getName(){
        return name;
    }
}