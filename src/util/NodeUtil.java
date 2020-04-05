package util;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class NodeUtil {
    public static final Label LEERZEICHEN = new Label(" ");

    public static <T> T selectionFromRadioButtonGroup(ToggleGroup group, Collection<T> source, Function<T,String> comparisonProperty){
        if(group==null||source==null||comparisonProperty==null||group.getSelectedToggle()==null) return null;
        String selected = ((RadioButton)group.getSelectedToggle()).getText();
        for(T t : source){
            if(Objects.equals(comparisonProperty.apply(t),selected))return t;
        }
        return null;
    }
}
