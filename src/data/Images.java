package data;

import javafx.scene.image.Image;

import java.awt.*;

public class Images {

    /**Verwenden um ein im /img Ordner gespeichertes Bild zu erhalten.
     * Rufe die Methode mit dem Bildnamen, einschließlich Endung, aber ohne Pfad auf.
     * evtl. wird auf groß/kleinschreibung geachtet
     *
     * z.B: Images.getImage("TestBild.png")
     */
    public static Image getImage(String name){

        Image result = null;

        try{
            result= new Image("img/"+name);
        }catch(RuntimeException ex){
            ex.printStackTrace();
        }

        return result;

    }
}
