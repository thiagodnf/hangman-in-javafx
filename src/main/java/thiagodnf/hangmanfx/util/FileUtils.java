package thiagodnf.hangmanfx.util;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import thiagodnf.hangmanfx.model.Dictionary;

public class FileUtils {

    public static Dictionary loadDictionary(String language) {

        URL url = FileUtils.class.getResource("/dictionaries/" + language);

        try {
            return new ObjectMapper().readValue(url, Dictionary.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
