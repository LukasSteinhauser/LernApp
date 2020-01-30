package data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Optional;

public class XMLData {
private static String basicPath = System.getenv("APPDATA")+ File.separator
         +"LernApp"+File.separator+"Data";

    public static boolean saveToDisk(String fileName, Object toSave){
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
    public static <T> Optional<T> loadFromDisk(Class<T> type, String fileName){
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
}
