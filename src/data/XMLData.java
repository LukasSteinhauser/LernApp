package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Optional;

public class XMLData {
private static String basicPath = System.getenv("APPDATA")+ File.pathSeparator
         +"LernApp"+File.pathSeparator+"Data";
    public static boolean saveToDisk(String fileName, Object toSave){
        XMLEncoder encoder =null;
        String path = basicPath+File.pathSeparator+fileName;
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
        }catch(FileNotFoundException fileNotFound){
return false;
        }
        encoder.writeObject(toSave);
        encoder.close();
        return true;
    }
    public static <T> Optional<T> loadFromDisk(Class<T> type, String fileName){
        XMLDecoder decoder=null;
        String path = basicPath+File.pathSeparator+fileName;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
        T result=(T)decoder.readObject();
        decoder.close();
        return Optional.of(result);
    }
}
