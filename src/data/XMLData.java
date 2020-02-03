package data;
// testPush
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class XMLData {
private static String basicPath = System.getenv("APPDATA")+ File.separator
         +"LernApp"+File.separator+"Data";

    /**Verwendet dies um Objekte auf der Festplatte zu speichern. Dabei nur den Dateinamen angeben (nicht den ganzen Pfad)
     * fileName: name der Datei am besten der Name des Objektes
     *
     * Das Objekt, dass gespeichert wird. Am besten nur Thema-Objekte speichern.
     */
    public static boolean saveDataFileToDisk(String fileName, Object toSave){
        XMLEncoder encoder =null;
        String path = basicPath+File.separator+fileName;
        try{
            File file = new File(path);

            file.getParentFile().mkdirs();

            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
        }catch(FileNotFoundException fileNotFound){
            return false;

        }
        encoder.writeObject(toSave);
        encoder.close();
        return true;
    }

    /**Verwenden um gespeicherte Objekte im Programm wieder her zu stellen.
     *
     * type: typ angeben
     *
     * fileName: gleichen Dateinamen wie oben angeben
     *
     * Beispiel für die Wiederherstellung eines Objektes vom Typ String,
     * welcher in der Datei "mystring.xml" gespeichert wurde:
     *
     * String mystring = XMLData.loadFromDisk(String.class, "mystring.xml");
     */
    public static <T> Optional<T> loadDataFileFromDisk(Class<T> type, String fileName){
        XMLDecoder decoder=null;
        String path = basicPath+File.separator+fileName;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
        T result=(T)decoder.readObject();
        decoder.close();
        return Optional.of(result);
    }

    /**Gibt alle Dateien in %APPDATA%\LernApp\Data zurück. Wenn keine Vorhanden, gibt es eine leere Liste zurück.
     *
     * Verwenden um die Themen zu erhalten.
     */
    public static List<File> getDataFiles(){
        var result = Collections.EMPTY_LIST;

        var dir = new File(basicPath);
        if(dir.exists()&&dir.exists()){
            result = new ArrayList<File>(Arrays.asList(dir.listFiles()));
        }

        return result;
    }
}
