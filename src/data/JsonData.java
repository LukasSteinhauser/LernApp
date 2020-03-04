package data;

import com.google.gson.Gson;
import model.UserProfile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class JsonData {
    public static final String basicPath = System.getenv("APPDATA") + File.separator
            + "LernApp" + File.separator + "Profiles" + File.separator;

    public static void saveUserProfile(UserProfile profile){
        String path = basicPath+profile.getId()+".json";

        Gson gson = new Gson();

        File file = new File(path);

        file.getParentFile().mkdirs();

        String jsonString = gson.toJson(profile);

        try {
            FileUtils.write(file,jsonString,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserProfile getUserProfile(String profileName){
        if(profileName==null){
            profileName = UserProfile.defaultName;
        }

        File file = new File(basicPath+profileName+".json");

        if(file.exists()){

            try {
                String jsonString = FileUtils.readFileToString(file,"utf-8");
                Gson gson = new Gson();
                UserProfile profile = gson.fromJson(jsonString, UserProfile.class);
                return profile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return TestData.getDefaultProfile();
    }


}
