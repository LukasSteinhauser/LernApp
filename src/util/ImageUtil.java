package util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class ImageUtil {
    public static final BackgroundImage MY_BG = new BackgroundImage(new Image("img/Background1.png",250,175,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    private static final Image TRUE_IMAGE =new Image("img/true.png",15, 15, false, false);
    public static final ImageView TRUE_VIEW = new ImageView(TRUE_IMAGE);
    private static final Image FALSE_IMAGE =new Image("img/false.png",15, 15, false, false);
    public static final ImageView FALSE_VIEW = new ImageView(FALSE_IMAGE);
}
