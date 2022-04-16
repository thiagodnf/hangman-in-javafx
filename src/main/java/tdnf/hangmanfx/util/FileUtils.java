package tdnf.hangmanfx.util;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.experimental.UtilityClass;
import tdnf.hangmanfx.model.Dictionary;

@UtilityClass
public class FileUtils {

    public static Dictionary loadDictionary(String language) {

        URL url = FileUtils.class.getResource("/dictionaries/" + language);

        try {
            return new ObjectMapper().readValue(url, Dictionary.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
//    public static FXMLLoader getFXMLLoader(String file) {
//        
//        URL url = FileUtils.class.getResource("/fxml/" + file);
//        
//        FXMLLoader loader = new FXMLLoader(url);
//
//        Parent root = null;
//
////        try {
////            return (Parent) loader.load();
////        } catch (Exception ex) {
////            throw new RuntimeException(ex);
////        }
//    }
}
