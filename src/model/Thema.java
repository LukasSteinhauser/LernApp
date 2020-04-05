package model;
import java.util.ArrayList;
import java.util.Objects;

public class Thema extends ArrayList<Question> {
    private String name ;


    public void setName(String name) {this.name = name; }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thema)) return false;
        if (!super.equals(o)) return false;
        Thema questions = (Thema) o;
        return Objects.equals(getName(), questions.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}